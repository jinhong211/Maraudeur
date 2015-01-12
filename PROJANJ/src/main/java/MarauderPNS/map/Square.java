package MarauderPNS.map;


import MarauderPNS.user.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * The class Square, which is the modelisation of a place in the map, of variable size throughout the project
 */

public abstract class Square
{

	private List<Class> access;
	private List<User> population;

	public Square(){
		population = new ArrayList<User>() {
		};
		this.access = new ArrayList<>();
		// TODO : gérer la liste d'acces à la salle.
	}

	public void add(User user) {
		// TODO : y a deux cas a traiter dans les filles
	}

	public List<Class> getAccess() {
		return access;
	}

	public void setAccess(List<Class> access) {
		this.access = access;
	}

	public List<User> getPopulation() {
		return population;
	}

	public void setPopulation(List<User> population) {
		this.population = population;
	}
}

