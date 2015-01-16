package MarauderPNS.View;

import MarauderPNS.controller.Controller;
import MarauderPNS.controller.ControllerFootPrint;
import MarauderPNS.simulation.FootPrinter;
import MarauderPNS.simulation.Simulator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by jeremy on 13/01/15.
 */
public class WelcomePanel extends JToolBar implements ActionListener {

    private JButton launch = new JButton("Launch Simulation");
    private JButton ftprinter = new JButton("Launch FootPrint Simulation");
    private JButton simulate = new JButton("Simulate");
    private int height;
    private int width;

    public WelcomePanel(int height, int width)
    {
        this.height = height;
        this.width = width;
        launch.addActionListener(this);
        this.add(launch);
        ftprinter.addActionListener(this);
        this.add(ftprinter);
        simulate.addActionListener(this);
        this.add(simulate);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getActionCommand().equals("Launch Simulation")){
            Controller.get_instance().start_simulation(height, width);
        } else if(actionEvent.getActionCommand().equals("Launch FootPrint Simulation")){
            ControllerFootPrint.get_instance().start_footPrint(20, 20);
        } else if(actionEvent.getActionCommand().equals("Simulate")){
            Controller.get_instance().start_simulate(20, 20);
        }
    }
}
