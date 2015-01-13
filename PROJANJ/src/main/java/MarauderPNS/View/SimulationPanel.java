package MarauderPNS.View;

import MarauderPNS.simulation.Simulator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by garance on 12/01/15.
 */
public class SimulationPanel extends JToolBar implements ActionListener {

    private JButton launch = new JButton("Launch");
    private Thread simulation;
    public SimulationPanel(Thread t)
    {
        simulation = t;
        launch.addActionListener(this);
        this.add(launch);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
       if(actionEvent.getActionCommand().equals("Launch")){
           simulation.start();

       }
    }
}
