package MarauderPNS.communication;


import MarauderPNS.user.Position;
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
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.sound.sampled.Line;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.io.*;
import java.net.URL;
import java.util.*;

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

public class Client {
    private JSONGenerator jSONGenerator;

    /**
     * Client constructor. All the code is useless for now,
     * kept as a model for when we'll have to write stuff to the server, or get stuff from him with a real JSOn request
     */
    public Client() {
        jSONGenerator = new JSONGenerator();
    }


    //Besoin de se connecter ICI : maraudeur.neowutran.net/start_simulation
    //Valable seulement poru le 1er sprint, empêche de personnaliser al simulation...
    //Et pas de requête en JSON
    public HashMap<Integer, User> beginSimulation() {

        String iGet = "";

        HttpClient client = HttpClientBuilder.create().build();
        HttpGet httpget = new HttpGet("https://maraudeur.neowutran.net/start_simulation?human_number=100");
        // Request parameters and other properties.
        HashMap<Integer, String> listOfUsers;
        //Execute and get the response.
        HttpResponse response;
        try {
            response = client.execute(httpget);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                InputStream instream = entity.getContent();
                BufferedReader br = new BufferedReader(
                        new InputStreamReader((instream)));
                String output;
                while ((output = br.readLine()) != null) {
                    iGet += output;
                }
                instream.close();
            }
        } catch (Exception ioException) {
            iGet = "{\"return\":" +
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
        listOfUsers = jSONGenerator.getNewSimulation(iGet);
        return createUsers(listOfUsers);

    }

    /**
     * Here, we already have a map with the id as key, plus a string which represents the status.
     *
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
     *
     * @param user the User, with its position. The method takes in charge the time stamp.
     */
    public void saveAMove(int id, User user) {
        try {
            HttpClient client = HttpClientBuilder.create().build();
            HttpPost httppost = new HttpPost("https://maraudeur.neowutran.net/add_position");
            List<NameValuePair> params = new ArrayList<>(1);
            params.add(new BasicNameValuePair("params", jSONGenerator.saveTheMove(id, user)));
            httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
            HttpResponse response = client.execute(httppost);
            checkAnswer(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void checkAnswer(HttpResponse response) {
        try {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                InputStream instream = entity.getContent();
                jSONGenerator.checkAnswer(instream);
                instream.close();
            }
        } catch (java.io.IOException ioException) {
            ioException.printStackTrace();
        }
    }

    /**
     * The method to replay the steps of a user.
     *
     * @param id the id of the user we want
     */
    public List<Position> replaySomeone(int id) {
        String iWentThere = "";
        try {
            HttpClient client = HttpClientBuilder.create().build();
            HttpPost httppost = new HttpPost("https://maraudeur.neowutran.net/get_footprints");
            List<NameValuePair> params = new ArrayList<>(1);
            params.add(new BasicNameValuePair("params", jSONGenerator.askOneId(id)));
            httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
            HttpResponse response = client.execute(httppost);
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                InputStream instream = entity.getContent();
                BufferedReader br = new BufferedReader(
                        new InputStreamReader((instream)));
                String output;
                while ((output = br.readLine()) != null) {
                    iWentThere += output;
                }
                instream.close();
            }
        }
                catch (Exception e) {
                    iWentThere = "{\"myReturn\":" +
                            "{\"myTrace\":" +
                            "[" +
                            "{\"time\":\"2015-01-12 08:44:28\",\"myCase\":{\"x\":1,\"y\":1}}," +
                            "{\"time\":\"2015-01-14 10:17:57\",\"myCase\":{\"x\":1,\"y\":1}}," +
                            "{\"time\":\"2015-01-14 10:17:58\",\"myCase\":{\"x\":2,\"y\":2}}," +
                            "{\"time\":\"2015-01-14 10:17:59\",\"myCase\":{\"x\":3,\"y\":3}}" +
                            "]" +
                            "}" +
                            "}";
                }

        return jSONGenerator.getFootPrint(iWentThere);
    }

}

