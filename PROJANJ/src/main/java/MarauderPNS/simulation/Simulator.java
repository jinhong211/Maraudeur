package MarauderPNS.simulation;


import MarauderPNS.View.GridView;
import MarauderPNS.map.Field;
import MarauderPNS.map.Wall;
import MarauderPNS.user.Student;
import MarauderPNS.user.Teacher;
import MarauderPNS.user.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * The Simulator class, which contains the simulation
 */

public class Simulator extends Thread {
	private Map<Integer, User> users;
	private GridView grid;
	private int height;
	private int width;
	private Field field;

	private static Simulator instance = null;

	public static Simulator getInstance() {
		if (Simulator.instance == null) {
			instance = new Simulator(20,20);
		}
		return instance;
	}

	private Simulator(int height, int width){
		this.width = width;
		this.height = height;
        users = new HashMap<>();
        field = new Field(height,width);
		grid = new GridView(height,width,field);
		generateUsers();
		placeWall();
		field.createField();
		placeUser();

	}


	private void generateUsers(){
		for(int i = 0; i < 5; i++){
			User user = new Teacher();
			users.put(i, user);
		}
		for(int i = 0; i < 5; i++){
			User user = new Student(15,15);
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

	private void placeWall()  {
		for(int i = 0; i < 10; i++) {
			Wall wall = new Wall();
			field.placeWall(wall,10,i);
			field.placeWall(wall,i,15);
		}
	}

	/**
	 * This run a simulation of one step.
	 */
	public void runOneStep() {
		for(Map.Entry<Integer, User> entry : users.entrySet()) {
			getLogicCoord(entry.getValue());
		}
		field.clear();

		placeUser();

		grid.repaint();
	}

	private void getLogicCoord(User user){
		Random rand = new Random();
		int x = user.getPosition().getX();
		int y = user.getPosition().getY();
		int random = rand.nextInt(4);
		switch(random) {
				case 0:
					if (checkXPlus(user))
						user.setPosition(x + 1, y);
					break;
				case 1:
					if (checkXMoins(user))
						user.setPosition(x - 1, y);
					break;
				case 2:
					if (checkYPlus(user))
						user.setPosition(x, y + 1);
					break;
				case 3:
					if (checkYMoins(user))
						user.setPosition(x, y - 1);
					break;
				default:
					user.setPosition(x, y);
					break;
		}
	}

	/**
	 * This simulation create random coordinated for every user.
	 * @param user
	 */
	private void getCoordRand(User user){
		Random rand = new Random();
		int x = rand.nextInt(20);
		int y = rand.nextInt(20);
		user.setPosition(x,y);
	}


	/**
	 * This method check the coordinate X
	 * @param user
	 * @return
	 */
	private boolean checkXPlus(User user) {
		if(user.getPosition().getX() == field.getWidth() - 1) {
			return false;
		} else if(field.getMyTable()[0][user.getPosition().getX()+1][user.getPosition().getY()].getAccess().isEmpty()) {
			return false;
		}
		return true;
	}

	/**
	 * This method chec if the coordinate X is equal to 0
	 * @param user
	 * @return
	 */
	private boolean checkXMoins(User user) {
		if(user.getPosition().getX() == 0) {
			return false;
		}else if(field.getMyTable()[0][user.getPosition().getX()-1][user.getPosition().getY()].getAccess().isEmpty()) {
			return false;
		}
		return true;
	}


	/**
	 * This method check the coordinate Y
	 * @param user
	 * @return
	 */
	private boolean checkYPlus(User user) {
		if(user.getPosition().getY() == field.getHeight() - 1) {
			return false;
		}else if(field.getMyTable()[0][user.getPosition().getX()][user.getPosition().getY()+1].getAccess().isEmpty()) {
			return false;
		}
		return true;
	}

	/**
	 * This method check if the coordinate Y is equal to 0
	 * @param user
	 * @return
	 */
	private boolean checkYMoins(User user){
		int x = user.getPosition().getX();
		int y = user.getPosition().getY();
		if(user.getPosition().getY() ==  0) {
			return false;
		}else if(field.getMyTable()[0][user.getPosition().getX()][user.getPosition().getY()-1].getAccess().isEmpty()) {
			return false;
		}
		return true;
	}


	/**
	 * This run a simulation of ten step.
	 */
	public void run() {
		for(int i = 0; i < 50; i++){
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

