package MarauderPNS.controller;

import MarauderPNS.View.FieldPanel;
import MarauderPNS.View.FootPrintView;
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

    public void runBis(){
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
                simulator.runBis();
            }
        });
        thread.start();
    }
}
