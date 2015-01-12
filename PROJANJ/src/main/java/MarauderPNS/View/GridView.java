package MarauderPNS.View;

import MarauderPNS.map.Field;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Quentin on 09/01/2015.
 */
public class GridView extends JFrame {

    public GridView(int heigh, int width, Field grid) {

        JPanel field = new FieldPanel(grid);
        JLabel title = new JLabel("Carte du maraudeur",JLabel.CENTER);
        JLabel time = new JLabel("Test :" + System.currentTimeMillis(), JLabel.CENTER);

        Container container = getContentPane();
        container.add(title,BorderLayout.NORTH);
        container.add(field, BorderLayout.CENTER);
        container.add(time, BorderLayout.SOUTH);
      //  this.add(grid, BorderLayout.CENTER);
        this.repaint();


        pack();
        this.setTitle(ViewData.title);
      //  this.setMinimumSize(new Dimension(500,500));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}