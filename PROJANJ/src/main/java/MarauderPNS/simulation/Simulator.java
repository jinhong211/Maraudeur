package MarauderPNS.simulation;


import MarauderPNS.map.Field;
import MarauderPNS.user.Student;
import MarauderPNS.user.Teacher;
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
		generateUsers();
	}


	private void generateUsers(){
		for(int i = 0; i < 5; i++){
			User user = new Teacher();
			users.put(i, user);
		}
		for(int i = 0; i < 5; i++){
			User user = new Student();
			users.put(i+5,user);
		}
	}

	public Map<Integer, User> getUsers() {
		return users;
	}

	public void setUsers(Map<Integer, User> users) {
		this.users = users;
	}

	public Field getField() {
		return field;
	}

	public void setField(Field field) {
		this.field = field;
	}

}

