package MarauderPNS.communication;


import MarauderPNS.user.Student;
import MarauderPNS.user.Teacher;
import MarauderPNS.user.User;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class ClientTest {
    JSONGenerator myGen;
    String iGet;
    Client client;

    @Before
    public void setUp() throws Exception {
        client = new Client();
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
    public void testCreateUsers() throws Exception {
        HashMap<Integer, String> theMap = myGen.getNewSimulation(iGet);
        HashMap<Integer, User> theActualUsers = client.createUsers(theMap);
        assertEquals(10, theActualUsers.size());
        assertEquals(Teacher.class, theActualUsers.get(62).getClass());
        assertEquals(Student.class, theActualUsers.get(63).getClass());
        assertEquals(Student.class, theActualUsers.get(65).getClass());
        assertEquals(Teacher.class, theActualUsers.get(71).getClass());
    }

}