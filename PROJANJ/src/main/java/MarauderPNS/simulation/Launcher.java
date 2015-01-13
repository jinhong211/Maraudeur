package MarauderPNS.simulation;

import MarauderPNS.View.GridView;
import MarauderPNS.controller.Controller;

/**
 * Created by Quentin on 09/01/2015.
 */
public class Launcher {

    public static void main( String[] args ) {

        Controller controlller = Controller.get_instance();
        controlller.start_simulation(20,20);
    }
}
