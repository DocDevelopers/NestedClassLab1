/**
 * Created by Doc on 2/2/16.
 */
interface Connection {
    ConnectionManager.PROTOCOLS DEFAULT_PROTOCOL = ConnectionManager.PROTOCOLS.HTTP;
    int getPort() throws ConnectionManager.ConnectionClosedException;
    String getIP() throws ConnectionManager.ConnectionClosedException;
    ConnectionManager.PROTOCOLS getProtocol();
    String connect() throws ConnectionManager.ConnectionClosedException;
    void close();
}
