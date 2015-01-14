package MarauderPNS.View;

import MarauderPNS.controller.Controller;

import javax.swing.*;
import MarauderPNS.user.Position;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by jeremy on 14/01/15.
 */

/*

 */
public class SimulateView extends JFrame {

    private JPanel field;
    private SimulatePanel simulatePanel;

    public SimulateView(int heigh, int width) {
        simulatePanel = new SimulatePanel();
        Graphics graphics = getContentPane().getGraphics();
        JLabel title = new JLabel("Simulate a footPrint",JLabel.CENTER);

        JPanel container = new JPanel();
        container.setLayout(new BorderLayout());
        container.add(title, BorderLayout.NORTH);
        simulatePanel.setPreferredSize(new Dimension(250, 100));
        container.add(simulatePanel, BorderLayout.EAST);
        this.setContentPane(container);
        this.setTitle("Simulate footprint");
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



    public class SimulatePanel extends JToolBar implements ActionListener {

        private JTextField setX = new JTextField();
        private JTextField setY = new JTextField();
        private JLabel forX = new JLabel("Position X");
        private JLabel forY = new JLabel("Position Y");
        private JButton simulate = new JButton("Simulate");
        private JPanel contentX = new JPanel();
        private JPanel contentY = new JPanel();

        public SimulatePanel()
        {
            contentX.setLayout(new BorderLayout());
            contentX.add(forX,BorderLayout.WEST);
            contentX.add(setX, BorderLayout.CENTER);
            contentY.setLayout(new BorderLayout());
            contentY.add(forY,BorderLayout.WEST);
            contentY.add(setY,BorderLayout.CENTER);
            setX.addActionListener(this);
            setY.addActionListener(this);
            simulate.addActionListener(this);
            this.setLayout(new GridLayout(3, 1));
            this.add(contentX,BorderLayout.NORTH);
            this.add(contentY,BorderLayout.CENTER);
            this.add(simulate,BorderLayout.SOUTH);
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            Position pos = new Position(Integer.parseInt(setX.getText()),Integer.parseInt(setY.getText()));
            if(actionEvent.getActionCommand().equals("Simulate")){
                Controller.get_instance().runBis(pos);
            }
        }
    }
}
