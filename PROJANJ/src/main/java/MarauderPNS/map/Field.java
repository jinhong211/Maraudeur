package MarauderPNS.map;


import MarauderPNS.user.User;

/**
 * The Field class, where the map is defined, as a table.
 */

public class Field
{

	private Square[][][] myTable;

	public Field(){
        myTable = new Square[1][20][20];
		for (int i = 0; i<20;i++) {
            for (int j = 0; j<20;j++) {
                myTable[0][i][j] = new Empty();
            }
        }

	}

    public void clear() {
        for (int i = 0; i<20;i++) {
            for (int j = 0; j<20;j++) {
                myTable[0][i][j].getPopulation().clear();
            }
        }
    }

    public void place(User user){
        myTable[0][user.getPosition().getX()][user.getPosition().getY()].add(user);
    }

    public Square[][][] getMyTable() {
        return myTable;
    }

    public void setMyTable(Square[][][] myTable) {
        this.myTable = myTable;
    }
}

