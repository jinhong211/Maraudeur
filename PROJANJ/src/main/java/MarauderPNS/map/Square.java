package MarauderPNS.map;


import java.util.ArrayList;
import java.util.List;

/**
 * The class Square, which is the modelisation of a place in the map, of variable size throughout the project
 */

public abstract class Square
{

	protected List<Class> access;

	

	public Square(){
		access = new ArrayList<>();
	}

}

