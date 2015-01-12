package MarauderPNS.simulation;


import MarauderPNS.map.Field;
import MarauderPNS.user.Student;
import MarauderPNS.user.Teacher;
import MarauderPNS.user.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

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
		placeUser();
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

	/**
	 * This method look over the hashmap and place all the user on the field. The Field handle where the user is placed.
	 */
	private void placeUser() {
		for(Map.Entry<Integer, User> entry : users.entrySet()){
			field.place(entry.getValue());
		}
	}


	public void runOneStep() {
		Random random = Randomizer.getRandom();
		Random random2 = Randomizer.getRandom();
		for(Map.Entry<Integer, User> entry : users.entrySet()) {
			System.out.println(entry.getValue().getPosition().getY());
			System.out.println(entry.getValue().getPosition().getX());
			int x = random.nextInt(20);
			int y = random2.nextInt(20);
			entry.getValue().setPosition(x,y);


			System.out.println(entry.getValue().getPosition().getY());
			System.out.println(entry.getValue().getPosition().getX());
		}
		placeUser();
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

