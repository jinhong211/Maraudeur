package MarauderPNS.View;

import MarauderPNS.map.Field;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Quentin on 10/01/2015.
 */
public class FieldPanel extends JPanel{

    private Field field;

    public FieldPanel(Field field) {
        this.field = field;
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for (int row = 0; row < 20; row++) {
            for (int col = 0; col < 20; col++) {
                if(field.getMyTable()[0][col][row].getPopulation().isEmpty()) {
                    g.setColor(Color.WHITE);
                    g.fillRect(col * 15, row * 15, 20, 20);
                    g.setColor(Color.gray);
                    g.drawRect(col * 15, row * 15, 20, 20);
                } else {
                    g.setColor(Color.BLUE);
                    g.fillRect(col * 15, row * 15, 20, 20);
                    g.setColor(Color.gray);
                    g.drawRect(col * 15, row * 15, 20, 20);
                }
            }
        }
    }
}
