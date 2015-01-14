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
public class FootPrintPanel extends JToolBar implements ActionListener {

    private JTextField toComplete = new JTextField();
    private JButton ftprinter = new JButton("Choose Client");
    private Thread ftprint = new Thread();

    public FootPrintPanel()
    {
        toComplete.setSize(10000,toComplete.getHeight());
        ftprinter.addActionListener(this);
        this.add(ftprinter);
        this.add(toComplete);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getActionCommand().equals("Choose Client")){
            FootPrinter footPrinter = new FootPrinter(20,20, Integer.parseInt(toComplete.getText()));
        }
    }
}
