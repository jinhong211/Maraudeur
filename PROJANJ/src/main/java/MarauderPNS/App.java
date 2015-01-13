package MarauderPNS;

import MarauderPNS.communication.Client;
import MarauderPNS.user.Student;
import MarauderPNS.user.User;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.cert.Certificate;
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
       // new App().testIt();
        /*System.out.println(result.size());
        for (Integer id : result.keySet()) {
            client.saveAMove(id, result.get(id));
        }
        */
    }
}
