package MarauderPNS.View;

import MarauderPNS.map.Field;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Quentin on 09/01/2015.
 */
public class GridView extends JFrame {

    public GridView(int heigh, int width, Field grid) {

        Graphics graphics = getContentPane().getGraphics();

        JPanel field = new FieldPanel(grid);
        field.setLayout(new FlowLayout());
        field.setPreferredSize(new Dimension(20*15,20*15));
        JLabel title = new JLabel("Carte du maraudeur",JLabel.CENTER);
        JLabel time = new JLabel("Test :" + System.currentTimeMillis(), JLabel.CENTER);

        JPanel container = new JPanel();
        container.setLayout(new BorderLayout());
        container.add(title, BorderLayout.NORTH);
        container.add(field, BorderLayout.CENTER);
        container.add(time, BorderLayout.SOUTH);
        this.getContentPane().setLayout(new FlowLayout());
        this.getContentPane().add(container);
       this.setTitle(ViewData.title);
        JFrame.setDefaultLookAndFeelDecorated(true);
        this.pack();
        this.setLocationRelativeTo(null);
        container.requestFocus();
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}