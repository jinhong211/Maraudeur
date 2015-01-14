package MarauderPNS.communication;

import MarauderPNS.user.Position;
import MarauderPNS.user.User;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import sun.misc.IOUtils;

import java.io.InputStream;
import java.io.StringWriter;
import java.sql.Timestamp;
import java.util.*;

public class JSONGenerator {
    public JSONGenerator(){
	}


    /**
     * The method to ask the server to start a new simulation, with parameters : useless for the first sprint
     * @return the json string
     */
    public String askNewSimulation() {
        return null;
    }

    /**
     * The method to translate from JSON to an array the list of users I asked for, to begin the simulation
     * @param iGet
     * @return the array of String, containing users
     */
    public HashMap<Integer, String> getNewSimulation(String iGet) {
         HashMap<Integer, String> theUsers = new HashMap<>();
        try {
            Object obj = JSONValue.parse(iGet);
            System.out.println(iGet);
            //On obtient le tableau de users
           JSONArray myArrayOfUsers = (JSONArray) ((JSONObject) obj).get("return");

            //On crée un itérateur pour le parcourir
            // ma string : donc je la transforme en onbjet JSON encore
            // {"user":{"id":62,"status":"Teacher"}}
            for (Object o : myArrayOfUsers) {
                obj = JSONValue.parse(o.toString());
                JSONObject aUser = (JSONObject)((JSONObject) obj).get("user");
                Long hislongId = (Long) aUser.get("id");
                int hisId = hislongId.intValue();
                String hisStatus = (String)aUser.get("status");
                theUsers.put(hisId, hisStatus);
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return theUsers;
    }

    /**
     * The method to create the json object to ask a move to be saved.
     * Must look like : “params”:  {"id":1,"case":{"x":1,"y":1}, "time":1421052268}
     * @param user
     * @return
     */
    public String saveTheMove(int id, User user) {
        java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());

        JSONObject theDetails = new JSONObject();
        JSONObject thePosition = new JSONObject();

        thePosition.put("x", user.getPosition().getX());
        thePosition.put("y", user.getPosition().getY());

        theDetails.put("id", id);
        theDetails.put("case", thePosition);
        theDetails.put("time",  currentTimestamp.getTime());
        return theDetails.toString();
    }

    public void checkAnswer(InputStream instream) {
        //We want to get that :  { “return” : { “code” : 200 } }
        Scanner s = new Scanner(instream).useDelimiter("\\A");
        try {
            if (s.hasNext()) assert(s.next().equals("{\"return\":{\"code\":200}}"));
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * The method to check the footprints the database sent us.
     * It is supposed to give us
     * {“return” : { [ “trace” : {“case” :{“x”:1,”y”:1}, “time” : “112345687”} ] ,... } }


     {"return":{"trace":[{"time":"2015-01-12 08:44:28","case":{"x":1,"y":1}},{"time":"2015-01-14 10:17:57","case":{"x":1,"y":1}},{"time":"2015-01-14 10:17:58","case":{"x":2,"y":2}},{"time":"2015-01-14 10:17:59","case":{"x":3,"y":3}}]}}
     * @param heWentThere : the JSONString representing the different positions of the user
     * @return Map des User, dont la clef est le timestamp
     */
    public List<Position> getFootPrint(String heWentThere) {
        List<Position> positions = new ArrayList<>();
        try {
            Object obj = JSONValue.parse(heWentThere);
            if (((JSONArray)obj).size() == 0) {
                heWentThere = "{\"return\":" +
                        "{\"trace\":" +
                        "[" +
                        "{\"time\":\"2015-01-12 08:44:28\",\"case\":{\"x\":1,\"y\":1}}," +
                        "{\"time\":\"2015-01-14 10:17:57\",\"case\":{\"x\":1,\"y\":1}}," +
                        "{\"time\":\"2015-01-14 10:17:58\",\"case\":{\"x\":2,\"y\":2}}," +
                        "{\"time\":\"2015-01-14 10:17:59\",\"case\":{\"x\":3,\"y\":3}}" +
                        "]" +
                        "}" +
                        "}";
                obj = JSONValue.parse(heWentThere);
            }
            System.out.println("He went there : " +heWentThere);
            //On obtient l'objet trace, qui contient un tableau

            JSONArray trace = (JSONArray) ((JSONObject)obj).get(0);
            System.out.println(trace.get(0));
           // JSONArray myArrayOfMoves = (JSONArray) ((JSONObject) trace).get("trace");
            //On a maintenant le tableau de déplacements, qui contient pour chaque ligne,
            //On a à chaque fois : "{\"time\":\"2015-01-12 08:44:28\",\"case\":{\"x\":1,\"y\":1}}," +
            for (Object o : trace) {
                obj = JSONValue.parse(o.toString());
                JSONObject theCase = (JSONObject) ((JSONObject) obj).get("case");

                obj = JSONValue.parse(theCase.toString());
                Object x = ((JSONObject) obj).get("x");
                Object y = ((JSONObject) obj).get("y");

                Long hisLongX = (Long) x;
                int hisX = hisLongX.intValue();
                Long hisLongY = (Long) y;
                int hisY = hisLongY.intValue();

                positions.add(new Position(hisX, hisY));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return positions;
    }

    public String askOneId(int id) {
        JSONObject theUser = new JSONObject();
        theUser.put("user_id", id);
        return theUser.toString();
    }
}

