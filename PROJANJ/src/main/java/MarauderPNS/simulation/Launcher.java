package MarauderPNS.simulation;

import MarauderPNS.View.GridView;

/**
 * Created by Quentin on 09/01/2015.
 */
public class Launcher {

    public static void main( String[] args ) {
        Simulator simulator = new Simulator();
        GridView grid = new GridView(500,500,simulator.getField());
        simulator.runOneStep();
        grid.repaint();
        grid.revalidate();
        System.out.println("TEEEEST");
    }
}
