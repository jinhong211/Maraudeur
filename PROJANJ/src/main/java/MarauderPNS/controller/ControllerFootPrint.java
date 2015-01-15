package MarauderPNS.controller;

import MarauderPNS.View.*;
import MarauderPNS.communication.Client;
import MarauderPNS.map.Field;
import MarauderPNS.simulation.FootPrinter;
import MarauderPNS.simulation.FootPrinter2;
import MarauderPNS.simulation.Simulator;
import MarauderPNS.user.Position;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Quentin on 13/01/2015.
 */
public class ControllerFootPrint {

    private GridSimulateView gridView;
    private FootPrinter2 simulator;
    private int height = 20;
    private int width = 20;

    private static ControllerFootPrint instance = null;

    public static ControllerFootPrint get_instance(){
        if(instance == null){
            instance = new ControllerFootPrint();
        }
        return instance;
    }

    private ControllerFootPrint() {}

    public void setField(Field field){ simulator.setField(field);}
    public Field getField(){
        return simulator.getField();
    }

    public void start_simulation(int height, int width){
        this.height = height;
        this.width = width;
        gridView = new GridSimulateView(height,width);
    }

    public void start_footPrint(int height, int width){
        System.out.println("start foot");
        FootPrintView footPrintView = new FootPrintView(20,20);
    }

    public void start_simulate(int height, int width){
        SimulateView simulateView = new SimulateView(20,20);
    }

    public void run(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                gridView= new GridSimulateView(20,20);
                simulator = new FootPrinter2(height, width, 1);
                Field field = simulator.getField();
                FieldFootPrintPanel fieldPanel = gridView.getField();
                System.out.println(fieldPanel);
                field.addObserver(fieldPanel);
                field.addObserver(gridView);
                gridView.repaint();
                System.out.println(field.countObservers());
                simulator.setField(field);
                simulator.addObserver(gridView);
                simulator.runBis();
            }
        });
        thread.start();
    }
}
