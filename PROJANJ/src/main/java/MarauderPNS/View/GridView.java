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

        JPanel container = new JPanel();
        container.setLayout(new BorderLayout());
        container.add(title, BorderLayout.NORTH);
        container.add(field, BorderLayout.CENTER);
        container.add(time, BorderLayout.SOUTH);
        this.setContentPane(container);
        this.setTitle(ViewData.title);
        this.setSize(this.getToolkit().getScreenSize());
        this.pack();
        JFrame.setDefaultLookAndFeelDecorated(true);
        this.setExtendedState(Frame.MAXIMIZED_BOTH);
        this.setLocationRelativeTo(null);
        container.requestFocus();
        this.setVisible(true);
    }
}