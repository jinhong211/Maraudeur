package MarauderPNS;

import MarauderPNS.communication.Client;
import MarauderPNS.user.Student;
import MarauderPNS.user.User;

import java.util.HashMap;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        System.out.println( "Hello World!" );
        System.out.println("Ca marche !");
        System.out.println("Je suis maintenant sur la branch Client");

        Client client = new Client();
        HashMap<Integer, User> result = client.beginSimulation();
        /*System.out.println(result.size());
        for (Integer id : result.keySet()) {
            client.saveAMove(id, result.get(id));
        }
        */
    }
}
