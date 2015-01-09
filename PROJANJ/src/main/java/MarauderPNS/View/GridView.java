package MarauderPNS.view;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Quentin on 09/01/2015.
 */
public class GridView extends JFrame {

    public GridView(int heigh, int width) {
        this.setTitle(ViewData.title);
        this.setMinimumSize(new Dimension(500,500));
        this.setLocationRelativeTo(null);

        this.setVisible(true);
    }
}