package MarauderPNS.simulation;
import MarauderPNS.View.GridView;
import MarauderPNS.communication.Client;
import MarauderPNS.map.Field;
import MarauderPNS.user.*;

import java.util.*;

/**
 * The Simulator class, which contains the simulation
 */

public class Tet extends Observable {

    private Map<Integer, User> users;
    private Client client;
    private Field field;
    private List<Position> footSimulation;
    private User theOne;
    private int step = 0;

    public Tet(int height, int width, int IdUser){
        client = new Client();
        field = new Field(height,width);
        footSimulation = client.replaySomeone(IdUser);
        theOne = new Teacher(0,0);
        field.createField();
        field.placeWall();

        runBis();
    }

    /**
     * This method look over the hashmap and place all the user on the field. The Field handle where the user is placed.
     */
    private void placeUser() {
        field.place(theOne);
    }

    /**
     * This run a simulation of one step.
     */
    public void runOneStep() {
        System.out.println("Un pas logique");
        getLogicCoord(theOne);
        field.clear();

        placeUser();

        notifyObservers();
    }

    private void getLogicCoord(User user){
        Random rand = new Random();
        int x = user.getPosition().getX();
        int y = user.getPosition().getY();
        user.setPosition(x, y);
    }

    public void runBis(){
        System.out.println("lancement de la simulation");
        for(int i = 0; i < footSimulation.size(); i++) {
            theOne.setPosition(footSimulation.get(i).getX(), footSimulation.get(i).getY());
            field.clear();
            placeUser();
            notifyObservers();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * This run a simulation of ten step.
     */
    public void run() {
        System.out.println("lancement de la simulation");

        users = client.beginSimulation();
        placeUser();


        for(int i = 0; i < 50; i++){
            System.out.println("Dans la simulation");

            runOneStep();
            try {
                Thread.sleep(1000);
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
