package MarauderPNS.simulation;


import MarauderPNS.View.GridView;
import MarauderPNS.communication.Client;
import MarauderPNS.map.Field;
import MarauderPNS.map.Wall;
import MarauderPNS.user.Position;
import MarauderPNS.user.User;

import java.util.HashMap;
import java.util.Map;

/**
 * The Simulator class, which contains the simulation
 */

public class FootPrinter extends Thread {
    private Map<Integer, User> users;
    private GridView grid;
    private int height;
    private int width;
    private Field field;
    private Client client;
    private int step = 0;
    private Integer timestampList[];
    private User theOne;
    private HashMap<Integer, Position> footPrint;


    public FootPrinter(int height, int width, int IdUser){
        this.width = width;
        this.height = height;
        client = new Client();
        users = client.beginSimulation();
        field = new Field(height,width);
        grid = new GridView(height,width,field, this);
        theOne = users.get(IdUser);
        footPrint = client.replaySomeone(IdUser, theOne);
        timestampList = footPrint.keySet().toArray(new Integer[0]);
        Integer temp[] = timestampList;
        int compare = timestampList[0];
        for(int myTemp : temp) {
            if(compare < myTemp) compare = myTemp;
        }
        timestampList[0] = compare;
        for(int myTimeStamp : timestampList){
            for(int myTemp : temp) {
                if(compare < myTemp && compare > myTimeStamp) compare = myTemp;
            }
            myTimeStamp = compare;
        }

        placeWall();
        field.createField();
        placeUser();

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
        getLogicCoord();
        field.clear();

        placeUser();

        grid.repaint();
    }

    private void getLogicCoord(){
        Position pos = getOldestPos();
        theOne.setPosition(pos.getX(), pos.getY());
    }

    public Position getOldestPos() {
        System.out.println(timestampList[0]);
        return footPrint.get(timestampList[step++]);
    }

    /**
     * This run a simulation of ten step.
     */
    public void run() {
        for(int i = 0; i < 50; i++){
            runOneStep();
            try {
                Thread.sleep(new Long(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

