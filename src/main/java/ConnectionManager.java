/**
 * Created by Doc on 2/2/16.
 */
public class ConnectionManager {
    public enum PROTOCOLS{
        HTTP, SSH, HTTPS, SFTP
    }
    private static final int MAX_CONNECTIONS = 100;
    private int NUM_OF_CONNECTIONS = 0;

    public Connection getConnection(String ip, PROTOCOLS protocol){
        if(NUM_OF_CONNECTIONS >= MAX_CONNECTIONS)
            return null;
        else {
            NUM_OF_CONNECTIONS++;
            return new ManagedConnection(ip, protocol);
        }
    }

    public Connection getConnection(String ip, int port){
        if(NUM_OF_CONNECTIONS >= MAX_CONNECTIONS)
            return null;
        else {
            NUM_OF_CONNECTIONS++;
            return new ManagedConnection(ip, port);
        }
    }

    public Connection getConnection(String ip, int port, PROTOCOLS protocol){
        if(NUM_OF_CONNECTIONS >= MAX_CONNECTIONS)
            return null;
        else {
            NUM_OF_CONNECTIONS++;
            return new ManagedConnection(ip, port, protocol);
        }
    }

    public static int getMaxConnections(){
        return MAX_CONNECTIONS;
    }


    private class ManagedConnection implements Connection{
        private String IP;
        private PROTOCOLS protocol;
        private int port;
        private boolean isClosed = false;

        private ManagedConnection(String mIP, PROTOCOLS mProtocol){
            this.IP = mIP;
            this.protocol = mProtocol;
        }

        private ManagedConnection(String mIP, int mPort){
            this(mIP, mPort, DEFAULT_PROTOCOL);
        }

        private ManagedConnection(String mIP, int mPort, PROTOCOLS mProtocol){
            this.IP = mIP;
            this.port = mPort;
            this.protocol = mProtocol;
        }

        public int getPort() throws ConnectionClosedException{
            if(isClosed)
                throw new ConnectionClosedException();
            else
                return this.port;
        }

        public String getIP() throws ConnectionClosedException {
            if(isClosed)
                throw new ConnectionClosedException();
            else
                return this.IP;
        }

        public PROTOCOLS getProtocol() {
            return this.protocol;
        }

        public String connect() throws ConnectionClosedException{
            if(isClosed)
                throw new ConnectionClosedException();
            else
                return "Connection opened at: PROTOCOL:"+this.getProtocol()+" IP:"+this.getIP()+" PORT:"+this.getPort();
        }

        public void close() {
            ConnectionManager.this.NUM_OF_CONNECTIONS--;
            isClosed = true;
        }
    }

    class ConnectionClosedException extends Exception{
        public ConnectionClosedException(){
            super("This connection has been closed. Make a new one by calling getConnection() on ConnectionManager");
        }
    }
}
