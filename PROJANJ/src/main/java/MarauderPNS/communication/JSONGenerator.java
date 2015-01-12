package MarauderPNS.communication;




import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
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
            Object obj = parser.parse(iGet);
            json = (JSONObject) obj;
            //On obtient le tableau de users
            JSONArray msg = (JSONArray) json.get("return");
            //On crée un itérateur pour le parcour
            Iterator<String> iterator = msg.iterator();
            // ma string : donc je la transforme en onbjet JSON encore
            //
            // {"user":{"id":62,"status":"Teacher"}}
            while (iterator.hasNext()) {
                obj = parser.parse(iterator.next());
                JSONObject theUser = (JSONObject)((JSONObject)obj).get("user");
                int hisId = (Integer)theUser.get("id");
                String hisStatus = (String)theUser.get("status");
                theUsers.put(hisId, hisStatus);
            }

        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return theUsers;
    }
}

