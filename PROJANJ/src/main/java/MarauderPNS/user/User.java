
package MarauderPNS.user;


/**
 * Classe User, qui définit les personnes modélies sur la carte
 * @generated
 */

public abstract class User
{

	private Position position;

    public User(int x, int y){
        Position pos = new Position(x,y);
        this.position = pos;
    }

    //TODO :  Mettre la pos defaut 5,5 dans l'interface.
    public User(){
        this.position = new Position(5,5);
    }


    public void setPosition(int x, int y) {
        position.setX(x);
        position.setY(y);
    }


    public Position getPosition() {
        return position;
    }
}

