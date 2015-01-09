package MarauderPNS.simulation;


import MarauderPNS.map.Field;
import MarauderPNS.user.User;

import java.util.HashMap;
import java.util.Map;

/**
 * The Simulator class, which contains the simulation
 */

public class Simulator
{
	public Map<Integer, User> users;

	public Field field;
	

	public Simulator(){
        users = new HashMap<>();
        field = new Field();
	}


}

