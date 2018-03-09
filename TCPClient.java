/*
 * This creates a client for communicating with a Server
 */

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class TCPClient {
/*
 * Setting port 1234 as a connection to the TCP Server
 */
	int port = 1234;
	TCPClientConnection clientconnection;

	public static void main(String[] args) {
		new TCPClient();
	}

	public TCPClient() {
		try {
			Socket h = new Socket("localhost", port);
			clientconnection = new TCPClientConnection(h, this);
			clientconnection.start();

			listenForInput();

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void listenForInput() {
		Scanner listener = new Scanner(System.in);
		while (true) {
			while (!listener.hasNextLine()) {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			String input = listener.nextLine();
			if (input.toLowerCase().equals("quit")) {
				break;
			}

			clientconnection.sendStringToServer(input);

		}

		listener.close();

	}

}