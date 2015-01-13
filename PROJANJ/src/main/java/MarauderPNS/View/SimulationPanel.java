package MarauderPNS.View;

import MarauderPNS.controller.Controller;
import MarauderPNS.simulation.Simulator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by garance on 12/01/15.
 */
public class SimulationPanel extends JToolBar implements ActionListener {

    private JButton launch = new JButton("Launch");
    public SimulationPanel()
    {
        launch.addActionListener(this);
        this.add(launch);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
       if(actionEvent.getActionCommand().equals("Launch")){
           Controller.get_instance().run();
       }
    }
}
