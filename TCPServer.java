/*
 * This recieves commands from TCPClientConnection
 */
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class TCPServer {
	/*
	 * Setting port 1234 as a connection to the TCP Client
	 */
	int port = 1234;
	ServerSocket serverSock;
	ArrayList<TCPServerConnection> connections = new ArrayList<TCPServerConnection>();
	boolean shouldRun = true;

	public static void main(String[] args) {
		new TCPServer();
	}

	public TCPServer() {

		try {
			serverSock = new ServerSocket(port);
			while (shouldRun) {
				Socket sock = serverSock.accept();
				TCPServerConnection serverConnection = new TCPServerConnection(sock, this);
				serverConnection.start();
				connections.add(serverConnection);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}