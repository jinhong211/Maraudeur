package MarauderPNS.user;


/**
 * La classe Position, qui définit les coordonnées d'une case ou bien d'une personne sur la carte
 * @generated
 */

public class Position
{	
	private int x;
	private int y;

    
	public Position(int x, int y){
		this.x = x;
        this.y = y;
	}

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

