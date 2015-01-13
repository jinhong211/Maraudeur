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

    public void add(User user) {
        getPopulation().add(user);
    }

    public void remove(User user) {
        //TODO :
    }


}

