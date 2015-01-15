package MarauderPNS;

import MarauderPNS.communication.Client;
import MarauderPNS.readXML.ReadXMLMap;
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
import java.util.List;

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
       ReadXMLMap read =  new ReadXMLMap();
        read.readFile();
        List<int[]> theList = read.processCols();
        for (int i = 0; i< theList.size();i++) {
            int theCols[] = theList.get(i);
            System.out.println("column : " + theCols[0]);
            System.out.println("begin : " + theCols[1]);
            System.out.println("end : " + theCols[2]);

        }
    }
}
