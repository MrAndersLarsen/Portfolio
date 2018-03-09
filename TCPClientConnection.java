/*
 * This uses commands from TCPClient to send TCPServer
 */
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class TCPClientConnection extends Thread {

	public static void main(String[] args) {

	}

	Socket sock;
	DataInputStream datain;
	DataOutputStream dataout;
	boolean shouldrun = true;

	public TCPClientConnection(Socket socket, TCPClient tcpclient) {
		sock = socket;

	}

	public void sendStringToServer(String text) {
		try {
			dataout.writeUTF(text);
			dataout.flush();

		} catch (IOException e) {
			e.printStackTrace();
			close();
		}
	}

	public void run() {
		try {
			datain = new DataInputStream(sock.getInputStream());
			dataout = new DataOutputStream(sock.getOutputStream());
			while (shouldrun)
				try {
					while (datain.available() == 0) {

						try {
							Thread.sleep(1);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					String reply = datain.readUTF();
					System.out.println(reply);
				} catch (IOException e) {
					e.printStackTrace();
					close();
				}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void close() {
		try {
			datain.close();
			dataout.close();
			sock.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
