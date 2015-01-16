package MarauderPNS.map;


import MarauderPNS.user.User;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * The Field class, where the map is defined, as a table.
 */

public class Field extends Observable
{

	private Square[][][] myTable;
    private int height;
    private int width;

	public Field(int width, int height){
        this.width = width;
        this.height = height;
        myTable = new Square[1][height][width];
		for (int i = 0; i<height;i++) {
            for (int j = 0; j<width;j++) {
                myTable[0][i][j] = new Square();
            }
        }

	}

    public void createField() {
        for (int i = 0; i<height;i++) {
            for (int j = 0; j<width;j++) {
                if(!myTable[0][i][j].getClass().equals(Wall.class)) {
                    myTable[0][i][j] = new Empty();
                }
            }
        }
    }

    /**
     * This method clear all the map.
     */
    public void clear() {
        for (int i = 0; i<height;i++) {
            for (int j = 0; j<width;j++) {
                myTable[0][i][j].getPopulation().clear();
            }
        }
        notifyObservers();
    }
    /**
     * This method place the wall on the map.
     */
    public void placeWall()  {
     /*   Wall wall = new Wall();
        System.out.println("TEST");
        for(int i = 0; i < 70; i++){
            placeWall(wall,0,i);
        }
        for(int i = 0; i < 30; i++) {
            placeWall(wall,i,70);
        }

        for(int i = 0; i < 70; i++) {
            placeWall(wall,i,0);
        }

        for(int i = 15; i < 50; i++ ){
            placeWall(wall, i,25);
        }

        for(int i = 0; i < 30; i++){
            placeWall(wall,15,i);
        }

        for(int i = 69; i > 31; i--){
            placeWall(wall,15,i);
        }
*/
        notifyObservers();
    }

    public void drawVerticalWall(ArrayList<int[]> tableau){
        for(int i = 0; i < tableau.size(); i++) {
            for(int k = tableau.get(i)[1];k <= tableau.get(i)[2];k++) {
                Wall wall = new Wall();
                placeWall(wall,tableau.get(i)[0],k);
            }
        }
        notifyObservers();
    }


    public void drawHorizontalWall(ArrayList<int[]> tableau) {
        for(int i = 0; i < tableau.size(); i++) {
            for(int k = tableau.get(i)[1]; k <=tableau.get(i)[2]; k++){
                Wall wall = new Wall();
                placeWall(wall, k, tableau.get(i)[0]);
            }
        }
        notifyObservers();
    }


    public void place(User user){

        myTable[0][user.getPosition().getX()][user.getPosition().getY()].add(user);
      //  System.out.println(this.countObservers());

       // notifyAll();
        //notify();
        setChanged();
 //       notifyObservers(new Object());
        notifyObservers();
    }

    public void placeWall(Wall wall, int x, int y) {
        myTable[0][x][y] = new Wall();
    }


    public Square[][][] getMyTable() {
        return myTable;
    }

    public void setMyTable(Square[][][] myTable) {
        this.myTable = myTable;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}

