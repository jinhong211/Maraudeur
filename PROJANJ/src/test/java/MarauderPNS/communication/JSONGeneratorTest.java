package MarauderPNS.communication;


import MarauderPNS.user.Student;
import MarauderPNS.user.User;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class JSONGeneratorTest {

JSONGenerator myGen;
    String iGet;
    JSONParser parser;

    @Before
    public void setUp() throws Exception {
        parser = new JSONParser();

        myGen = new JSONGenerator();
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

    @Test
    public void testGetNewSimulation() throws Exception {
        HashMap<Integer, String> result = myGen.getNewSimulation(iGet);
        assertEquals(10, result.size());
        assertEquals(result.get(62), "Teacher");
        assertEquals(result.get(63), "Student");
        assertEquals(result.get(65), "Student");
        assertEquals(result.get(71), "Teacher");
    }

    @Test
    public void testNewMove() throws Exception {
        User aStudent = new Student();
        String result = myGen.saveTheMove(23, aStudent);
        //On récupère la string sous forme d'objet :
        Object obj = JSONValue.parse(result);
        //On la transforme en objet JSON :
        JSONObject theMove = (JSONObject) obj;
        //Check we can get the ID back :
        assertEquals((long)23, theMove.get("id"));
        //Check we can get the position back :
        JSONObject coords = (JSONObject)theMove.get("case");
        assertEquals((long) 15, coords.get("x"));
        assertEquals((long)15, coords.get("y"));
    }

  /* to be tested yet
    @Test
    public void testGetFootPrint() throws Exception {

        String myString = "{\"return\":{[\"trace\":" +
                    "{\"case\":{\"x\":1,\"y\":1},\"time\":\"112345687\"}] ,... } }";

        InputStream is = new ByteArrayInputStream( myString.getBytes() );
    }
*/
    /*
    public HashMap<Integer, User> getFootPrint(InputStream instream, User user) {
        java.util.Scanner s = new java.util.Scanner(instream).useDelimiter("\\A");
        HashMap<Integer, User> footprints = new HashMap<>();
        try {
            while (s.hasNext()) {
                Object obj = JSONValue.parse(s.next());
                //On obtient le tableau de traces
                JSONArray myArrayOfFootPrints = (JSONArray) ((JSONObject) obj).get("trace");
                //On crée un itérateur pour le parcourir
                // ma string : donc je la transforme en onbjet JSON encore
                // {"user":{"id":62,"status":"Teacher"}}
                for (Object o : myArrayOfFootPrints) {
                    obj = JSONValue.parse(o.toString());
                    JSONObject aTrace = (JSONObject) obj;
                    JSONObject coords = (JSONObject)aTrace.get("case");
                    Long theLongTime = (Long)aTrace.get("time");
                    int theTime = theLongTime.intValue();
                    user.setPosition(Integer.parseInt(coords.get("x").toString()), Integer.parseInt(coords.get("x").toString()));
                    footprints.put(theTime, user);
                }
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return footprints;
    }


     */


}