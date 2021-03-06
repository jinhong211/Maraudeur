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
public class FootPrintPanel extends JToolBar implements ActionListener {

    private JTextField toComplete = new JTextField();
    private JButton ftprinter = new JButton("Choose Client");

    public FootPrintPanel()
    {
        ftprinter.addActionListener(this);
        this.add(ftprinter);
        this.add(toComplete);
        toComplete.setSize(10000,toComplete.getHeight());
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getActionCommand().equals("Choose Client")){
            ControllerFootPrint.get_instance().run();
        }
    }
}
