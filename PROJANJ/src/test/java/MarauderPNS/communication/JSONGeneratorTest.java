package MarauderPNS.communication;


import MarauderPNS.user.Student;
import MarauderPNS.user.Teacher;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class JSONGeneratorTest {

JSONGenerator myGen;
    String iGet;

    @Before
    public void setUp() throws Exception {
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
        assertEquals(result.get(62),"Teacher");
        assertEquals(result.get(63),"Student");
        assertEquals(result.get(65),"Student");
        assertEquals(result.get(71),"Teacher");


    }
}