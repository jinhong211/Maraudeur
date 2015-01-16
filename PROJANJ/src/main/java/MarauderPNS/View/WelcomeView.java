package MarauderPNS.View;

import MarauderPNS.map.Field;

import javax.swing.*;
import java.awt.*;

/**
 * Created by jeremy on 13/01/15.
 */
public class WelcomeView extends JFrame{

    private JPanel field;
    private WelcomePanel welcomePane;

    public WelcomeView(int heigh, int width) {
        welcomePane = new WelcomePanel(heigh, width);
        Graphics graphics = getContentPane().getGraphics();

        JLabel title = new JLabel("Carte du maraudeur",JLabel.CENTER);

        JPanel container = new JPanel();
        container.setLayout(new BorderLayout());
        container.add(title, BorderLayout.NORTH);
        container.add(welcomePane, BorderLayout.SOUTH);
        this.setContentPane(container);
        this.setTitle("Carte du maraudeur");
        //  this.setSize(this.getToolkit().getScreenSize());
        this.pack();
        JFrame.setDefaultLookAndFeelDecorated(true);
        //     this.setExtendedState(Frame.MAXIMIZED_BOTH);
        this.setLocationRelativeTo(null);
        container.requestFocus();
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    @Override
    public void repaint(){
        field.repaint();
    }
}
