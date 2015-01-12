package MarauderPNS.communication;




import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class JSONGenerator {
    private JSONObject json;
    private JSONParser parser;

    public JSONGenerator(){
		json = new JSONObject();
        parser = new JSONParser();
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
        //Ici, je récupère une liste, où chaque case contient : {"user":{"id":62,"status":"Teacher"}}
        //NON ! Besoin d'un JSONOBJECT ! Que je peux décoder au fur & à mesure. Utiliser plutôt JSONSIMPLE du coup ! (aussi google)
        HashMap<Integer, String> theUsers = new HashMap<>();
        try {
            Object obj = JSONValue.parse(iGet);
            //On obtient le tableau de users
           JSONArray myArrayOfUsers = (JSONArray) ((JSONObject) obj).get("return");
            //On crée un itérateur pour le parcour
            // ma string : donc je la transforme en onbjet JSON encore
            // {"user":{"id":62,"status":"Teacher"}}
            for (Object o : myArrayOfUsers) {
                obj = JSONValue.parse(o.toString());
                JSONObject aUser = (JSONObject) obj;
                JSONObject theUser = (JSONObject)aUser.get("user");
                Long hislongId = (Long)theUser.get("id");
                int hisId = hislongId.intValue();
                String hisStatus = (String)theUser.get("status");
                theUsers.put(hisId, hisStatus);
            }

            /*
            String s="[0,{\"1\":{\"2\":{\"3\":{\"4\":[5,{\"6\":7}]}}}}]";
  Object obj=JSONValue.parse(s);
  JSONArray array=(JSONArray)obj;
  System.out.println("======the 2nd element of array======");
  System.out.println(array.get(1));
  System.out.println();

  JSONObject obj2=(JSONObject)array.get(1);
  System.out.println("======field \"1\"==========");
  System.out.println(obj2.get("1"));
             */

        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return theUsers;
    }
}

