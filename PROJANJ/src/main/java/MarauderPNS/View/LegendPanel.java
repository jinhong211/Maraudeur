package MarauderPNS.View;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Quentin on 15/01/2015.
 */
public class LegendPanel extends JPanel {


    private String text;

    public LegendPanel(String text, Color color) {
        this.text = text;
        JLabel legend = new JLabel(text);
        this.setLayout(new BorderLayout());
        this.add(legend,BorderLayout.EAST);




    }



}
