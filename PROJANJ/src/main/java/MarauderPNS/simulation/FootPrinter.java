package MarauderPNS.simulation;
        import MarauderPNS.View.GridView;
        import MarauderPNS.communication.Client;
        import MarauderPNS.map.Field;
        import MarauderPNS.map.Wall;
        import MarauderPNS.user.Position;
        import MarauderPNS.user.Teacher;
        import MarauderPNS.user.User;

        import java.util.List;
        import java.util.Map;
        import java.util.Observable;

/**
 * The Simulator class, which contains the simulation
 */

public class FootPrinter extends Observable {
    private Map<Integer, User> users;
    private GridView grid;
    private int height;
    private int width;
    private Field field;
    private Client client;
    private int step = 0;
    private User theOne;
    private List<Position> footPrint;
    //private HashMap<Integer, Position> footPrint;

    public FootPrinter(int height, int width, int IdUser){
        this.width = width;
        this.height = height;
        client = new Client();
        //users = client.beginSimulation();
        //users = new HashMap<>();
        // users = client.beginSimulation();
        field = new Field(height,width);
        grid = new GridView(height,width);
        field.addObserver(grid);
        grid.repaint();
        theOne = new Teacher();
        //System.out.println(users.toString());
        footPrint = client.replaySomeone(IdUser);
        System.out.println(footPrint.toString());
        placeWall();
        field.createField();
        this.setField(field);
        this.addObserver(grid);
        placeUser();
        run();
    }

    /**
     * This method look over the hashmap and place all the user on the field. The Field handle where the user is placed.
     */
    private void placeUser() {
        field.place(theOne);
    }

    /**
     * This method place the wall on the map.
     */
    private void placeWall()  {
        for(int i = 0; i < 10; i++) {
            Wall wall = new Wall();
            field.placeWall(wall,10,i);
            field.placeWall(wall,i,15);
        }
    }

    /**
     * This run a simulation of one step.
     */
    public void runOneStep() {
        getLogicCoord(theOne);
        field.clear();

        placeUser();

        grid.repaint();
    }

    private void getLogicCoord(User user){
        System.out.println(" x:" + footPrint.get(step).getX() + "    y:" + footPrint.get(step).getY());
        user.setPosition(footPrint.get(step).getX(),footPrint.get(step).getY());
    }

    /**
     * This run a simulation of ten step.
     */
    public void run() {

        for(int i = 0; i < footPrint.size(); i++){
            runOneStep();
            step++;
            try {
                Thread.sleep(new Long(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

}

