package MarauderPNS.controller;

import MarauderPNS.View.*;
import MarauderPNS.communication.Client;
import MarauderPNS.map.Field;
import MarauderPNS.simulation.FootPrinter;
import MarauderPNS.simulation.Simulator;
import MarauderPNS.user.Position;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Quentin on 13/01/2015.
 */
public class Controller{

    private GridView gridView;
    private GridSimulateView gridSimulateView;
    private Simulator simulator;
    private int height = 20;
    private int width = 20;

    private static Controller instance = null;

    public static Controller get_instance(){
        if(instance == null){
            instance = new Controller();
        }
        return instance;
    }

    private Controller() {

    }

    public void setField(Field field){ simulator.setField(field);}
    public Field getField(){
        return simulator.getField();
    }

    public void start_simulation(int height, int width){
        this.height = height;
        this.width = width;
        gridView = new GridView(height,width);
    }

    public void start_footPrint(int height, int width){
        FootPrintView footPrintView = new FootPrintView(20,20);
    }

    public void start_simulate(int height, int width){
        SimulateView simulateView = new SimulateView(20,20);
    }

    public void run(){
       Thread thread = new Thread(new Runnable() {
           @Override
           public void run() {
               simulator = new Simulator(height, width);
               Field field = simulator.getField();
               FieldPanel fieldPanel = gridView.getField();
               System.out.println(fieldPanel);
               field.addObserver(fieldPanel);
               field.addObserver(gridView);
               gridView.repaint();
               System.out.println(field.countObservers());
               simulator.setField(field);
               simulator.addObserver(gridView);
               simulator.run();
           }
       });
        thread.start();
    }

    /*public void runFootPrint(){
        System.out.println("I make footprint");
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                FootPrinter footPrinter = new FootPrinter(height, width, 0);
                Field field = footPrinter.getField();
                FieldPanel fieldPanel = gridSimulateView.getField();
                System.out.println(fieldPanel);
                field.addObserver(fieldPanel);
                field.addObserver(gridSimulateView);
                gridSimulateView.repaint();
                System.out.println(field.countObservers());
                footPrinter.setField(field);
                footPrinter.addObserver(gridSimulateView);
                footPrinter.runBis();
            }
        });
        thread.start();
    }*/

    public void runBis(final Position pos){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                gridView = new GridView(height,width);
                simulator = new Simulator(height, width);
                Field field = simulator.getField();
                FieldPanel fieldPanel = gridView.getField();
                System.out.println(fieldPanel);
                field.addObserver(fieldPanel);
                field.addObserver(gridView);
                gridView.repaint();
                System.out.println(field.countObservers());
                simulator.setField(field);
                simulator.addObserver(gridView);
                simulator.runBis(pos);
            }
        });
        thread.start();
    }
}
