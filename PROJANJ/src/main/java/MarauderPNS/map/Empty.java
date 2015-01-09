package MarauderPNS.map;


import MarauderPNS.user.Student;
import MarauderPNS.user.User;

/**
 * The Empty class, which modelises a square where certain people can be, since it is "empty"
 */

public class Empty extends Square
{
	
	private int nbOfPeople;

	public Empty(){
		super();
        access.add(User.class);
        nbOfPeople = 0;
	}

    public void addPerson(int nb) {
        if(nb > 0)
        nbOfPeople += nb;
    }

    public void removePerson(int nb) {
        if (nb > 0)
            nbOfPeople-= nb;
    }

}

