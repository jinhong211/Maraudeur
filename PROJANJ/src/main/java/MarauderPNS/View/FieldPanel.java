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
                        g.setColor(new Color(0,0,0));
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
        int green = 210;
        int red = 210;
        int blue = 210;
        if(student > 0 && teacher == 0) {
            blue = 255;
            green = green - (student*20);
            red = red - (student*20);
            if(green < 0) {
                green = 0;
            }
            if(red < 0) {
                red = 0;
            }
        }
        if(teacher > 0 && student == 0) {
            red = 255;
            green = green - (teacher*20);
            blue = blue - (teacher*20);
            if(blue < 0) {
                blue = 0;
            }
            if(green < 0) {
                green = 0;
            }
        }
        if(teacher > 0 && student > 0) {
            red = 255 - (student*10);
            green = green - (((teacher+student)/2)*20);
            blue = 255 - (teacher*10);
            if(green < 0) {
                green = 0;
            }
            if(red < 0) {
                red = 0;
            }
            if(blue < 0) {
                blue = 0;
            }

        }

        g.setColor(new Color(red,green,blue));
    }

    @Override
    public void update(Observable o, Object arg) {
  //      System.out.println("Dans le field");
        this.field = Controller.get_instance().getField();
    }
}
