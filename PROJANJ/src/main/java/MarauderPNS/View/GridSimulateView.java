package MarauderPNS.View;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by jeremy on 15/01/15.
 */
public class GridSimulateView  extends JFrame implements Observer {
    public FieldFootPrintPanel getField() {
        return field;
    }

    private FieldFootPrintPanel field;
    private SimulateGridPanel simulationPanel;

    public GridSimulateView(int height, int width) {
        simulationPanel = new SimulateGridPanel();
        Graphics graphics = getContentPane().getGraphics();

        field = new FieldFootPrintPanel();
        field.setPreferredSize(new Dimension(height*15, width*15));
        JLabel title = new JLabel("Carte du maraudeur",JLabel.CENTER);
        JLabel time = new JLabel("Test :" + System.currentTimeMillis(), JLabel.CENTER);

        JPanel container = new JPanel();
        container.setLayout(new BorderLayout());
        container.add(title, BorderLayout.NORTH);
        container.add(field, BorderLayout.CENTER);
        container.add(simulationPanel, BorderLayout.EAST);
        container.add(time, BorderLayout.SOUTH);
        this.setContentPane(container);
        this.setTitle("Carte du maraudeur");
        //  this.setSize(this.getToolkit().getScreenSize());
        this.pack();
        JFrame.setDefaultLookAndFeelDecorated(true);
        //     this.setExtendedState(Frame.MAXIMIZED_BOTH);
        this.setLocationRelativeTo(null);
        container.requestFocus();
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    @Override
    public void repaint(){
        field.repaint();
    }

    @Override
    public void update(Observable o, Object arg) {
        //  System.out.println("Dans la grid");
        repaint();
    }
}
