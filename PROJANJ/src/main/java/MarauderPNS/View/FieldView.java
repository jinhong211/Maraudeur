package MarauderPNS.view;


import javax.swing.*;
import java.awt.*;


/**
 * Created by Quentin on 09/01/2015.
 */
public class FieldView extends JPanel {

    private int height;
    private int width;
    private Graphics g;

    public FieldView(int height, int width, Graphics graphics) {
        this.height = height;
        this.width = width;
        this.g = graphics;
        showStatus();
    }

    public void showStatus() {
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                drawMark(col, row, Color.GRAY);
            }
        }
    }

    /**
     * Paint on grid location on this field in a given color.
     */
    public void drawMark(int x, int y, Color color) {


        g.setColor(color);
        g.fillRect(x * 4, y * 4, 4 - 1, 4 - 1);
    }

}
