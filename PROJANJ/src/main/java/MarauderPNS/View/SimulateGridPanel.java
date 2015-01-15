package MarauderPNS.View;

import MarauderPNS.controller.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by jeremy on 15/01/15.
 */
public class SimulateGridPanel extends JToolBar implements ActionListener {
    private JButton launch = new JButton("Launch");

    public SimulateGridPanel()
    {
        launch.addActionListener(this);
        this.add(launch);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getActionCommand().equals("Launch")){
           // Controller.get_instance().runFootPrint();
        }
    }
}
