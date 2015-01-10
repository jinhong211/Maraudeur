
package MarauderPNS.user;


/**
 * Classe User, qui définit les personnes modélies sur la carte
 * @generated
 */

public abstract class User
{

	private int id;
	private Position position;

	public User(int hisId){
        id = hisId;
	}

    public void setPosition(int x, int y) {
        position.setX(x);
        position.setY(x);
    }

}

