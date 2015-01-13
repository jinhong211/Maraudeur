package MarauderPNS.View;

import MarauderPNS.map.Field;
import MarauderPNS.map.Wall;
import MarauderPNS.user.Student;
import MarauderPNS.user.Teacher;
import MarauderPNS.user.User;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Quentin on 10/01/2015.
 */
public class FieldPanel extends JPanel{

    private Field field;
    private Graphics g;

    public FieldPanel(Field field) {
        this.field = field;
    }

    @Override
    public void paintComponent(Graphics g){
        this.g = g;
        super.paintComponent(g);
        for (int row = 0; row < 20; row++) {
            for (int col = 0; col < 20; col++) {
                if(field.getMyTable()[0][col][row].getClass().equals(Wall.class)) {
                    g.setColor(Color.LIGHT_GRAY);
                    g.fillRect(col * 15, row * 15, 20, 20);
                    g.setColor(Color.gray);
                    g.drawRect(col * 15, row * 15, 20, 20);
                } else if(field.getMyTable()[0][col][row].getPopulation().isEmpty()) {
                    g.setColor(Color.WHITE);
                    g.fillRect(col * 15, row * 15, 20, 20);
                    g.setColor(Color.gray);
                    g.drawRect(col * 15, row * 15, 20, 20);
                } else {
                    paintUser(col, row, g);
 //                g.setColor(Color.BLUE);
                    g.fillRect(col * 15, row * 15, 20, 20);
                    g.setColor(Color.gray);
                   g.drawRect(col * 15, row * 15, 20, 20);
                }
            }
        }
    }

    /**
     * This method make the gradient of blue and red in function of concentration of teacher and student.
     * @param col
     * @param row
     * @param graphics
     */
    private void paintUser(int col, int row, Graphics graphics) {
        int student = 0;
        int teacher = 0;
        for(User user : field.getMyTable()[0][col][row].getPopulation()) {
            if(user instanceof Teacher) {
                teacher++;
            } else if(user instanceof Student) {
                student++;
            }
        }
        int green = 100;
        int red = 180;
        int blue = 180;
        if(student > 0) {
            blue = blue - (student * 25);
            if(blue < 50){
                blue = 50;
            }
        }
        if(teacher > 0) {
            red = red - (teacher * 25);
            if(red < 50) {
                red = 50;
            }
        }
        g.setColor(new Color(red,green,blue));
    }
}
