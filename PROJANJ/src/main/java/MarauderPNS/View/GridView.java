package MarauderPNS.View;

import MarauderPNS.map.Field;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Quentin on 09/01/2015.
 */
public class GridView extends JFrame implements Observer {


    public FieldPanel getField() {
        return field;
    }

    private FieldPanel field;
    private SimulationPanel simulationPanel;

    public GridView(int heigh, int width) {
        simulationPanel = new SimulationPanel();
        Graphics graphics = getContentPane().getGraphics();

        field = new FieldPanel();
        field.setPreferredSize(new Dimension(20*15, 20*15));
        JLabel title = new JLabel("Carte du maraudeur",JLabel.CENTER);
        JLabel time = new JLabel("Test :" + System.currentTimeMillis(), JLabel.CENTER);

        JPanel container = new JPanel();
        container.setLayout(new BorderLayout());
        container.add(title, BorderLayout.NORTH);
        container.add(field, BorderLayout.CENTER);
        container.add(simulationPanel, BorderLayout.EAST);
        container.add(time, BorderLayout.SOUTH);
        this.setContentPane(container);
        this.setTitle("TEST");
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

    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }
}
