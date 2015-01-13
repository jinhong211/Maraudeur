package MarauderPNS.View;

import MarauderPNS.controller.Controller;
import MarauderPNS.map.Field;
import MarauderPNS.map.Wall;
import MarauderPNS.user.Student;
import MarauderPNS.user.Teacher;
import MarauderPNS.user.User;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Quentin on 10/01/2015.
 */
public class FieldPanel extends JPanel implements Observer{

    private Graphics g;
    private Field field = null;
    public FieldPanel() {
    }

    @Override
    public void paintComponent(Graphics g){
        this.g = g;
        super.paintComponent(g);
        if(field == null){
            for (int row = 0; row < 20; row++) {
                for (int col = 0; col < 20; col++) {
                    g.setColor(Color.WHITE);
                    g.fillRect(col * 15, row * 15, 20, 20);
                    g.setColor(Color.gray);
                    g.drawRect(col * 15, row * 15, 20, 20);

                }
            }

        }else {
            for (int row = 0; row < 20; row++) {
                for (int col = 0; col < 20; col++) {
                    if (field.getMyTable()[0][col][row].getClass().equals(Wall.class)) {
                        g.setColor(Color.LIGHT_GRAY);
                        g.fillRect(col * 15, row * 15, 20, 20);
                        g.setColor(Color.gray);
                        g.drawRect(col * 15, row * 15, 20, 20);
                    } else if (field.getMyTable()[0][col][row].getPopulation().isEmpty()) {
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

    @Override
    public void update(Observable o, Object arg) {
        this.field = Controller.get_instance().getField();
    }
}
