package MarauderPNS.View;

import MarauderPNS.controller.Controller;
import MarauderPNS.simulation.Simulator;

import javax.naming.ldap.Control;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by garance on 12/01/15.
 */
public class SimulationPanel extends JToolBar implements ActionListener {

    private JButton launch = new JButton("Launch");
    private JLabel legendTeacher = new JLabel("Faible concentration en professeur :");
    private LegendPanel text = new LegendPanel("test",Color.RED);

    public SimulationPanel()
    {
        JPanel container = new JPanel();
        container.setLayout(new GridLayout(20, 2));
        container.add(legendTeacher, "1");
        launch.addActionListener(this);
        container.add(text,"2");
        container.add(launch, "3");
        this.add(container);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
       if(actionEvent.getActionCommand().equals("Launch")){
           Controller.get_instance().run();
       }
    }
}
