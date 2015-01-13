package MarauderPNS.View;

import MarauderPNS.controller.Controller;
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
    private Thread ftprint = new Thread();

    public WelcomePanel()
    {
        launch.addActionListener(this);
        this.add(launch);
        ftprinter.addActionListener(this);
        this.add(ftprinter);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getActionCommand().equals("Launch Simulation")){
            Controller controlller = Controller.get_instance();
            controlller.start_simulation(20,20);
        } else if(actionEvent.getActionCommand().equals("Launch FootPrint Simulation")){
            FootPrintView footPrintView = new FootPrintView(20,20);
        }
    }
}
