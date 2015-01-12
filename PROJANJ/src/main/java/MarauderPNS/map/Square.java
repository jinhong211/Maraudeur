package MarauderPNS.map;


import java.util.ArrayList;
import java.util.List;

/**
 * The class Square, which is the modelisation of a place in the map, of variable size throughout the project
 */

public abstract class Square
{

	private List<Class> access;
	private int nbrPeople;

	public Square(){
		this.access = new ArrayList<>();
		this.nbrPeople = 0;
	}

	public void add() {
		// TODO : y a deux cas a traiter dans les filles
	}

	public List<Class> getAccess() {
		return access;
	}

	public void setAccess(List<Class> access) {
		this.access = access;
	}

	public int getNbrPeople() {
		return nbrPeople;
	}

	public void setNbrPeople(int nbrPeople) {
		this.nbrPeople = nbrPeople;
	}
}

