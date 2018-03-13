import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientMaker {

	public static void main(String args[]) throws Exception{
		
		/*
		 * Lager en klient og kjører runmetoden for å ta i bruk funksjonene som kreves av
		 * den obligatoriske oppgaven
		 */
		ClientMaker Client = new ClientMaker();
		Client.run();
		
	}
	
	public void run() throws Exception{
		
		/*
		 * Definerer port og ipadresse for å oppnå kontakt med serveren
		 */
		final int Port = 6969;
		Socket Socket = new Socket("localhost", Port);
		
		/*
		 * Sender beskjed/kommando til serveren man har fått kontakt med
		 */
		PrintStream PrintStream = new PrintStream(Socket.getOutputStream());
		Scanner tastatur = new Scanner(System.in);
		System.out.println("Connected with server on port: "  + Port + "\nWrite your command: ");
		String MessageToServer = tastatur.next();
		System.out.println("Client command sent to server: " + MessageToServer);
		PrintStream.println(MessageToServer);
		
		/*
		 * Tar i mot beskjeder fra serveren
		 */
		InputStreamReader InputStreamReader = new InputStreamReader(Socket.getInputStream());
		BufferedReader BufferedReader = new BufferedReader(InputStreamReader);
		
		/*
		 * Oppretter en while løkke for å kunne håndtere printf setninger som blir sendt fra server
		 * der man formaterer dato
		 */
		Scanner PrintfToPrintln = new Scanner(BufferedReader);
		System.out.println("From Server:");
		while (PrintfToPrintln.hasNextLine()) {
		  System.out.println(PrintfToPrintln.nextLine());
		}
		
		/*
		 * Lukker alle scanner og socket
		 */
		PrintfToPrintln.close();
		tastatur.close();
		Socket.close();
		
	}
	
}
