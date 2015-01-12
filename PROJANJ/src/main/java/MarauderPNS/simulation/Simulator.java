package MarauderPNS.simulation;


import MarauderPNS.View.GridView;
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
	private Map<Integer, User> users;
	private GridView grid;
	private Field field;

	public Simulator(){
        users = new HashMap<>();
        field = new Field();
		grid = new GridView(500,500,field);
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
		for(Map.Entry<Integer, User> entry : users.entrySet()) {
			Random rand = new Random();
			int x = rand.nextInt(20);
			int y = rand.nextInt(20);
			entry.getValue().setPosition(x,y);
			System.out.println(x);
			System.out.println(y);
		}
		field.clear();

		placeUser();

		grid.repaint();
	}


	public void runSimulation() {
		for(int i = 0; i < 10; i++){
			runOneStep();
			try {
				Thread.sleep(new Long(1000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
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

