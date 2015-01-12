package MarauderPNS.communication;


import MarauderPNS.user.Student;
import MarauderPNS.user.User;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.junit.Before;
import org.junit.Test;

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


    //{"id":1,"case":{"x":1,"y":1}, "time":1421052268}
    @Test
    public void testNewMove() throws Exception {
/*        User aStudent = new Student();
        String result = myGen.saveTheMove(23, aStudent);
        JSONObject obj=new JSONObject();
        obj.put("params","result");
        String obj2 = obj.toJSONString();
        Object obj3=JSONValue.parse(obj2);
        JSONArray array=(JSONArray)obj3;
        System.out.println(array.get(1));

        JSONObject obj2 = (JSONObject)obj.get("params");


        System.out.println("coucou" +obj2.get("id"));
        assertEquals(23, obj2.get("id"));
        //Position is 5, 5 by default
        JSONObject position = (JSONObject)obj2.get("case");
        assertEquals(aStudent.getPosition().getX(),position.get("x"));
        assertEquals(aStudent.getPosition().getY(),position.get("y")); */
    }

}