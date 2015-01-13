package MarauderPNS.controller;

import MarauderPNS.View.GridView;
import MarauderPNS.communication.Client;
import MarauderPNS.map.Field;
import MarauderPNS.simulation.Simulator;

import javax.swing.*;

/**
 * Created by Quentin on 13/01/2015.
 */
public class Controller{

    private GridView gridView;
    private Simulator simulator;
    private int height;
    private int width;

    private static Controller instance = null;

    public static Controller get_instance(){
        if(instance == null){
            instance = new Controller();
        }
        return instance;
    }

    private Controller() {

    }

    public Field getField(){
        return simulator.getField();
    }

    public void start_simulation(int height, int width){
        this.height = height;
        this.width = width;
        gridView = new GridView(height,width);


    }

    public void run(){
        simulator = new Simulator(height, width);
        simulator.getField().addObserver(gridView.getField());
        simulator.addObserver(gridView);
        Thread thread = new Thread(simulator);
        thread.run();
    }
}
