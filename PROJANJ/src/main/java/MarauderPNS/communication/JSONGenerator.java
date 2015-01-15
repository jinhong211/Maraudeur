package MarauderPNS.communication;

import MarauderPNS.map.Empty;
import MarauderPNS.map.Square;
import MarauderPNS.map.Wall;
import MarauderPNS.user.Position;
import MarauderPNS.user.User;
import com.google.gson.Gson;
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
     * @param heWentThere : the JSONString representing the different positions of the user
     * @return Map des User, dont la clef est le timestamp
     */
    public List<Position> getFootPrint(String heWentThere) {
        List<Position> positions = new ArrayList<>();
        try {
            Gson gson = new Gson();

            heWentThere = "{\"myReturn\":" +
                    "{\"myTrace\":" +
                    "[" +
                    "{\"time\":\"2015-01-12 08:44:28\",\"myCase\":{\"x\":1,\"y\":1}}," +
                    "{\"time\":\"2015-01-14 10:17:57\",\"myCase\":{\"x\":1,\"y\":1}}," +
                    "{\"time\":\"2015-01-14 10:17:58\",\"myCase\":{\"x\":2,\"y\":2}}," +
                    "{\"time\":\"2015-01-14 10:17:59\",\"myCase\":{\"x\":3,\"y\":3}}" +
                    "]" +
                    "}" +
                    "}";
            TraceData myTraceData = gson.fromJson(heWentThere, TraceData.class);
            TraceData.ReturnStuff myReturnStuff = myTraceData.getReturnStuff();
            ArrayList<TraceData.ReturnStuff.MyTrace> myTraces = myReturnStuff.getMyTrace();
            for (int i = 0; i<myTraces.size(); i++) {
                TraceData.ReturnStuff.MyTrace.MyCase myCase = myTraces.get(i).getMyCase();
                int x = myCase.getX();
                int y = myCase.getX();
                positions.add(new Position(x, y));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return positions;
    }

    public Square[][] initializeMap(String iGet) {
       // Gson gson = new Gson();
       // MyMap myMap = gson.fromJson(iGet, MyMap.class);
        Square [][] theMap = new Square[4][4];
        try {
            Object obj = JSONValue.parse(iGet);
            JSONArray myArrayOfCases = (JSONArray) ((JSONObject) obj).get("return");

            //On crée un itérateur pour le parcourir
            // "{\"myCase\":{\"x\":0,\"y\":0,\"type\":\"wallE\"}},"
            for (Object o : myArrayOfCases) {
                obj = JSONValue.parse(o.toString());
                JSONObject aUser = (JSONObject)((JSONObject) obj).get("myCase");
                Long theLongX = (Long) aUser.get("x");
                int theX = theLongX.intValue();
                Long theLongY = (Long) aUser.get("y");
                int theY = theLongY.intValue();
                String hisStatus = (String)aUser.get("type");
                if(hisStatus.equalsIgnoreCase("empty")) theMap[theX][theY] = new Empty();
                else  if(hisStatus.equalsIgnoreCase("wallE")) theMap[theX][theY] = new Wall();
                else  if(hisStatus.equalsIgnoreCase("wallI")) theMap[theX][theY] = new Wall();
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return new Square[0][];
    }

    public class TraceData {
        private ReturnStuff myReturn;

      public ReturnStuff getReturnStuff() {
          return myReturn;
      }
        public class ReturnStuff {
            private ArrayList<ReturnStuff.MyTrace> myTrace;

            public ArrayList<ReturnStuff.MyTrace> getMyTrace() {
                return myTrace;
            }
            public class MyTrace {
                private Object time;
                private MyCase myCase;

                public MyCase getMyCase() {
                    return myCase;
                }
                public class MyCase {
                    private int x;
                    private int y;

                    public int getX() {
                        return x;
                    }
                    public int getY() {
                        return y;
                    }
                }
            }
        }
    }

    public String askOneId(int id) {
        JSONObject theUser = new JSONObject();
        theUser.put("user_id", id);
        return theUser.toString();
    }
}

