package MarauderPNS.simulation;
import MarauderPNS.communication.Client;
import MarauderPNS.controller.Controller;
import MarauderPNS.map.Field;
import MarauderPNS.map.Wall;
import MarauderPNS.user.Position;
import MarauderPNS.user.Student;
import MarauderPNS.user.Teacher;
import MarauderPNS.user.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

/**
 * The Simulator class, which contains the simulation
 */

public class Simulator extends Observable{
	private Map<Integer, User> users;
	private Client client;
	private Field field;
	private AStarSimulation starSimulation;
	private HashMap<Integer, List<Node>> aStar;


	public Simulator(int height, int width){
		aStar = new HashMap<>();
		client = new Client();
		field = new Field(height,width);
		starSimulation = new AStarSimulation(field);

		field.createField();
		//     users = new HashMap<>();
		//	generateUsers();
		field.placeWall();


	}

	/**
	 * Inutile pour le moment si on récupère les informations du serveur.
	 */
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



	/**
	 * This run a simulation of one step.
	 */
	public void runOneStep() {
		System.out.println("Un pas");
		for(Map.Entry<Integer, User> entry : users.entrySet()) {
	//		System.out.println("TEST");
			getLogicCoord(entry.getValue());
	//		System.out.println("TEST2");
	//		client.saveAMove(entry.getKey(),entry.getValue());
	//		System.out.println("TEST3");

		}
		field.clear();

		placeUser();

		notifyObservers();
	}

	private void getLogicCoord(User user){
		Random rand = new Random();
		int x = user.getPosition().getX();
		int y = user.getPosition().getY();
		int random = rand.nextInt(5);
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


	public void runBis(Position pos){
		users = client.beginSimulation();
		//	placeUser();
		int theUser = 0;
		for(Map.Entry<Integer, User> entry : users.entrySet()) {
			if(starSimulation.search(entry.getValue(), pos) == 1){
				aStar.put(entry.getKey(), starSimulation.getResult());
				theUser = entry.getKey();
			}
		}

		List<Node> solution = aStar.get((Object)theUser);
		for(int i = 0; i < solution.size(); i++) {
			users.get((Object) theUser).setPosition(solution.get(i).getX(), solution.get(i).getY());
			field.clear();
			placeUser();
			notifyObservers();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}


	}



	/**
	 * This run a simulation of ten step.
	 */
	public void run() {
		System.out.println("lancement de la simulation");

		users = client.beginSimulation();
		placeUser();


		for(int i = 0; i < 50; i++){
			System.out.println("Dans la simulation");

						runOneStep();
			try {
				Thread.sleep(1000);
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

