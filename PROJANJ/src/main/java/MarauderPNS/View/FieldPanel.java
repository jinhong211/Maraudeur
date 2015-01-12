package MarauderPNS.View;

import MarauderPNS.map.Field;
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
                if(field.getMyTable()[0][col][row].getPopulation().isEmpty()) {
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
     * @param x
     * @param y
     * @param graphics
     */
    private void paintUser(int x, int y, Graphics graphics) {
        int student = 0;
        int teacher = 0;
        for(User user : field.getMyTable()[0][x][y].getPopulation()) {
            if(user.getClass() == Teacher.class) {
                teacher++;
            } else if(user.getClass() == Student.class) {
                student++;
            }
        }
        int green = 0;
        int red = 0;
        int blue = 0;
        if(student > 0) {
            blue = student * 10;
            if(blue > 255){
                blue = 255;
            }
        }
        if(teacher > 0) {
            red =  teacher * 10;
            if(red > 255) {
                red = 255;
            }
        }
        g.setColor(new Color(red,green,blue));
    }
}
