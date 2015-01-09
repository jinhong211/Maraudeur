package MarauderPNS.communication;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.io.*;
import javax.net.ssl.HttpsURLConnection;
/**
 * The client class dedicated to communicating with the server, via the https protocol for now
 * A quick documention why it's different from http :
 * When using an https connection, the server responds to the initial connection by offering a list of encryption methods
 * it supports. In response, the client selects a connection method, and the client and server exchange certificates
 * to authenticate their identities. After this is done, both parties exchange the encrypted information after ensuring
 * that both are using the same key, and the connection is closed. In order to host https connections, a server must have
 * a public key certificate, which embeds key information with a verification of the key owner's identity.
 * Most certificates are verified by a third party so that clients are assured that the key is secure.
 */

public class Client
{
	private JSONGenerator jSONGenerator;

	public Client() {
        try {
            //Here is my URL
            String httpsURL = "https://www.gmail.com/";
            URL myurl = new URL(httpsURL);
            //Let's connect together !
            HttpsURLConnection con = (HttpsURLConnection) myurl.openConnection();
            //In a few steps, let's get what's on the server
            InputStream ins = con.getInputStream();
            InputStreamReader isr = new InputStreamReader(ins);
            BufferedReader in = new BufferedReader(isr);
            String inputLine;
            //what's on the server now ??
            while ((inputLine = in.readLine()) != null) {
                System.out.println(inputLine); //this will be what's in the JSON format
            }
            in.close();


            //This is how to get data from the server. Now, to send him some info :
        }
        catch (Exception e) {
            System.err.println("Exception !");
            e.printStackTrace();
        }

	}

}

