import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Doc on 2/2/16.
 */
public class ConnectionManagerTest {
    private static final String EXPECTED_IP = "127.0.0.1";
    private static final int EXPECTED_PORT = 80;
    private static final ConnectionManager.PROTOCOLS EXPECTED_PROTOCOL = ConnectionManager.PROTOCOLS.HTTP;
    private static final String EXPECTED_CONNECTED_MSG = "Connection opened at: PROTOCOL:"+EXPECTED_PROTOCOL+" IP:"+EXPECTED_IP+" PORT:"+EXPECTED_PORT;

    @Test
    public void testGetConnection() throws Exception {
        ConnectionManager manager = new ConnectionManager();

        Connection myConnection = manager.getConnection(EXPECTED_IP, EXPECTED_PROTOCOL);

        assertEquals("IP not as expected", EXPECTED_IP, myConnection.getIP());
        assertEquals("PROTOCOL not as expected", EXPECTED_PROTOCOL, myConnection.getProtocol());
        assertEquals("PORT is supposed to be 0", 0, myConnection.getPort());
    }

    @Test
    public void testGetConnection1() throws Exception {
        ConnectionManager manager = new ConnectionManager();

        Connection myConnection = manager.getConnection(EXPECTED_IP, EXPECTED_PORT);


        assertEquals("IP not as expected", EXPECTED_IP, myConnection.getIP());
        assertEquals("PORT not as expected", EXPECTED_PORT, myConnection.getPort());
    }

    @Test
    public void testGetConnection2() throws Exception{
        ConnectionManager manager = new ConnectionManager();

        Connection myConnection = manager.getConnection(EXPECTED_IP, EXPECTED_PORT, EXPECTED_PROTOCOL);

        assertEquals("IP not as expected", EXPECTED_IP, myConnection.getIP());
        assertEquals("PORT not as expected", EXPECTED_PORT, myConnection.getPort());
        assertEquals("PROTOCOL not as expected", EXPECTED_PROTOCOL, myConnection.getProtocol());

    }
    @Test
    public void testMaxNumberOfConnections() throws Exception {
        ConnectionManager manager = new ConnectionManager();

        int MAX = ConnectionManager.getMaxConnections();

        //Create MAX connections.
        for(int i = 0; i<MAX; i++){
            assertNotNull("Connection manager returned null, should stil have connections available", manager.getConnection(EXPECTED_IP, EXPECTED_PORT));
        }

        //Create another connection and expect a null.
        Connection myConnection = manager.getConnection(EXPECTED_IP, EXPECTED_PORT);

        assertNull("Connection manger should not have anymore connections", myConnection);

    }

    @Test
    public void testGetPort() throws Exception {
        ConnectionManager manager = new ConnectionManager();
        Connection myConnection = manager.getConnection(EXPECTED_IP, EXPECTED_PORT, EXPECTED_PROTOCOL);
        assertEquals("PORT not as expected", EXPECTED_PORT, myConnection.getPort());
    }

    @Test
    public void testGetIP() throws Exception {
        ConnectionManager manager = new ConnectionManager();
        Connection myConnection = manager.getConnection(EXPECTED_IP, EXPECTED_PORT, EXPECTED_PROTOCOL);
        assertEquals("IP not as expected", EXPECTED_IP, myConnection.getIP());
    }

    @Test
    public void testGetProtocol() throws Exception {
        ConnectionManager manager = new ConnectionManager();
        Connection myConnection = manager.getConnection(EXPECTED_IP, EXPECTED_PORT, EXPECTED_PROTOCOL);
        assertEquals("PROTOCOL not as expected", EXPECTED_PROTOCOL, myConnection.getProtocol());
    }

    @Test
    public void testConnect() throws Exception {
        ConnectionManager manager = new ConnectionManager();
        Connection myConnection = manager.getConnection(EXPECTED_IP, EXPECTED_PORT, EXPECTED_PROTOCOL);

        assertEquals("Conneted message not as expected!", EXPECTED_CONNECTED_MSG, myConnection.connect());
    }


    @Test(expected = ConnectionManager.ConnectionClosedException.class)
    public void closeConnection() throws ConnectionManager.ConnectionClosedException {
        ConnectionManager manager = new ConnectionManager();
        Connection myConnection = manager.getConnection(EXPECTED_IP, EXPECTED_PORT, EXPECTED_PROTOCOL);

        myConnection.close();
        myConnection.getIP();
    }

    @Test(expected = ConnectionManager.ConnectionClosedException.class)
    public void closeConnection1() throws ConnectionManager.ConnectionClosedException {
        ConnectionManager manager = new ConnectionManager();
        Connection myConnection = manager.getConnection(EXPECTED_IP, EXPECTED_PORT, EXPECTED_PROTOCOL);

        myConnection.close();
        myConnection.getPort();
    }
}