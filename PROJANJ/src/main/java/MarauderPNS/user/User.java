
package MarauderPNS.user;


/**
 * Classe User, qui définit les personnes modélies sur la carte
 * @generated
 */

public abstract class User
{

	private Position position;

    public User(Position pos){
        this.position = pos;
    }

    //TODO :  Mettre la pos defaut 5,5 dans l'interface.
    public User(){
        this.position = new Position(5,5);
    }


    public void setPosition(int x, int y) {
        position.setX(x);
        position.setY(x);
    }

}

