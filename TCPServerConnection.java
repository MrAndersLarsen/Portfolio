/*
 * This sends the commands from TCPServer to TCPClient
 */
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class TCPServerConnection extends Thread {

	public static void main(String[] args) {

	}

	Socket socket;
	TCPServer server;
	DataInputStream datain;
	DataOutputStream dataout;
	boolean shouldRun = true;

	public TCPServerConnection(Socket socket, TCPServer tcpserver) {
		super("ServerConnectionThread");
		this.socket = socket;
		this.server = tcpserver;
	}

	public void sendStringToClient(String text) {
		try {
			dataout.writeUTF(text);
			dataout.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void sendStringToAllClients(String text) {
		for (int i = 0; i < server.connections.size(); i++) {
			TCPServerConnection serverConnection = server.connections.get(i);
			serverConnection.sendStringToClient(text);
		}
	}

	public void run() {
		try {
			datain = new DataInputStream(socket.getInputStream());
			dataout = new DataOutputStream(socket.getOutputStream());

			while (shouldRun) {
				while (datain.available() == 0) {
					Thread.sleep(1);
				}
				String textIn = datain.readUTF();
				sendStringToAllClients(textIn);
			}

			datain.close();
			dataout.close();
			socket.close();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

}
