package MarauderPNS.map;


import MarauderPNS.simulation.Simulator;

/**
 * The Field class, where the map is defined, as a table.
 */

public class Field
{

	private Square[][][] myTable;

	public Field(){
		for (int i = 0; i<20;i++) {
            for (int j = 0; j<20;j++) {
                myTable[0][i][j] = new Square();
            }
        }

	}

}

