package MarauderPNS.communication;


import MarauderPNS.user.Student;
import MarauderPNS.user.Teacher;
import MarauderPNS.user.User;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import sun.misc.IOUtils;

import java.io.BufferedReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.io.*;
import java.util.*;
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
 * Here however, our server has an auto signed certificate, so we must make sure that we still accept it.
 */

public class Client
{
	private JSONGenerator jSONGenerator;
    private PrintWriter out;
    private BufferedReader in;

    /**
     * Client constructor. All the code is useless for now,
     * kept as a model for when we'll have to write stuff to the server, or get stuff from him with a real JSOn request
     */
	public Client() {
        jSONGenerator = new JSONGenerator();

      /*  try {

            //Here is my URL
            String httpsURL = "https://www.gmail.com/";
            URL myurl = new URL(httpsURL);
            //WARNING : here, it is likely to crash because the certificate is self-signed
            HttpsURLConnection con = (HttpsURLConnection) myurl.openConnection();
            //In a few steps, let's get what's on the server
            InputStream ins = con.getInputStream();
            InputStreamReader isr = new InputStreamReader(ins);
            in = new BufferedReader(isr);
            String inputLine;
            //what's on the server now ??
            while ((inputLine = in.readLine()) != null) {
                System.out.println(inputLine); //this will be what's in the JSON format
            }
            //And if I want to send stg to the server ?
            OutputStream outs = con.getOutputStream();
            out = new PrintWriter(outs, true);
            out.println("That's for the server !");
            in.close();
            //This is how to get data from the server. Now, to send him some info :
        }
        catch (Exception e) {
            System.err.println("Exception !");
            e.printStackTrace();
        }
        */

	}


    //Besoin de se connecter ICI : maraudeur.neowutran.net/start_simulation
    //Valable seulement poru le 1er sprint, empêche de personnaliser al simulation...
    //Et pas de requête en JSON
    public HashMap<Integer, User> beginSimulation() {
        String iGet = "";
        /*try {
            String httpsURL = "maraudeur.neowutran.net/start_simulation";
            URL myurl = new URL(httpsURL);
            //WARNING : here, it is likely to crash because the certificate is self-signed
            HttpsURLConnection con = (HttpsURLConnection) myurl.openConnection();
            //In a few steps, let's get what's on the server
            InputStream ins = con.getInputStream();
            InputStreamReader isr = new InputStreamReader(ins);
            in = new BufferedReader(isr);
            String inputLine;
            //what's on the server now ??
            while ((inputLine = in.readLine()) != null) {
                iGet = (inputLine); //this will be what's in the JSON format
            }
            in.close();
            con.disconnect(); */

        HttpGet request = new HttpGet();
        request.setHeader("Accept", "application/json");
        HttpClient httpClient = HttpManager.getNewHttpClient();
        try {
            try {
                request.setURI(new URI("https://maraudeur.neowutran.net/start_simulation"));
            } catch (RuntimeException e) {
            }
            //Here, supposed to get the json stuff
            HttpResponse response = httpClient.execute(request);
            Scanner scanner = new Scanner(response.getEntity().getContent());
            while (scanner.hasNextLine()) {
                iGet += scanner.nextLine();
            }
        } catch (URISyntaxException | IOException e) {
        }
        catch(Exception e) {
            e.printStackTrace();
            System.err.println("Impossible de lire la réponse du serveur");
            iGet =  "{\"return\":" +
                    "[" +
                    "{\"user\":{\"id\":62,\"status\":\"Teacher\"}}," +
                    "{\"user\":{\"id\":63,\"status\":\"Student\"}}," +
                    "{\"user\":{\"id\":64,\"status\":\"Student\"}}," +
                    "{\"user\":{\"id\":65,\"status\":\"Student\"}}," +
                    "{\"user\":{\"id\":66,\"status\":\"Teacher\"}}," +
                    "{\"user\":{\"id\":67,\"status\":\"Teacher\"}}," +
                    "{\"user\":{\"id\":68,\"status\":\"Student\"}}," +
                    "{\"user\":{\"id\":69,\"status\":\"Teacher\"}}," +
                    "{\"user\":{\"id\":70,\"status\":\"Student\"}}," +
                    "{\"user\":{\"id\":71,\"status\":\"Teacher\"}}" +
                    "]" +
                    "}";

        }
        HashMap<Integer, String> listOfUsers = jSONGenerator.getNewSimulation(iGet);
        return createUsers(listOfUsers);
    }

    /**
     * Here, we already have a map with the id as key, plus a string which represents the status.
     * @param listOfUsers
     * @return the actual hashmap containing users
     */
    protected HashMap<Integer, User> createUsers(HashMap<Integer, String> listOfUsers) {
        HashMap<Integer, User> myUsers = new HashMap<>();
        for (Map.Entry<Integer, String> entry : listOfUsers.entrySet()) {
            if (entry.getValue().equalsIgnoreCase("student"))
                myUsers.put(entry.getKey(), new Student());
            else
                myUsers.put(entry.getKey(), new Teacher());
        }
        return myUsers;
    }


    /**
     * The method use to send to the server the position of a user we'd like to save.
     * What the server expects :  “params”:  {"id":1,"case":{"x":1,"y":1}, "time":1421052268}
     * @param user the User, with its position. The method takes in charge the time stamp.
     */
    public void saveAMove(int id, User user) {
        try {
            //This is the only line that got changed to bypass the ssl security
            HttpClient httpclient =HttpManager.getNewHttpClient();
            HttpPost httppost = new HttpPost("https://maraudeur.neowutran.net/add_position");

            // Request parameters and other properties.
            List<NameValuePair> params = new ArrayList<NameValuePair>(1);
            params.add(new BasicNameValuePair("params", jSONGenerator.saveTheMove(id, user)));
            System.out.println(jSONGenerator.saveTheMove(id, user));
            httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

    //Execute and get the response.
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();

        if (entity != null) {
            InputStream instream = entity.getContent();
            try {
                jSONGenerator.checkAnswer(instream);
            } finally {
                instream.close();
            }
        }
            out.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * The method to replay the steps of a user.
     * @param user the user we want
     */
    public void replaySomeone(User user) {

    }


}

