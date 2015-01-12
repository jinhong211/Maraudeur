package MarauderPNS.map;


import MarauderPNS.user.User;

/**
 * The Field class, where the map is defined, as a table.
 */

public class Field
{

	private Square[][][] myTable;
    private int height;
    private int width;

	public Field(int height, int width){
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
    }

    public void place(User user){
        myTable[0][user.getPosition().getX()][user.getPosition().getY()].add(user);
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

