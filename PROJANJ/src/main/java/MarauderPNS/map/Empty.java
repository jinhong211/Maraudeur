package MarauderPNS.map;


import MarauderPNS.user.User;

/**
 * The Empty class, which modelises a square where certain people can be, since it is "empty"
 */

public class Empty extends Square
{
	
	public Empty(){
		super();
        getAccess().add(User.class);
	}

    public void add() {
        addPerson(1);
    }

    public void remove() {
        removePerson(1);
    }

    public void addPerson(int nb) {
        if(nb > 0) {
            int population = getNbrPeople() + nb;
            setNbrPeople(population);
        }
    }

    public void removePerson(int nb) {
        if(nb > 0) {
            int population = getNbrPeople() - nb;
            setNbrPeople(population);
        }
    }

}

