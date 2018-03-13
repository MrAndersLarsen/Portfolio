import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ServerMaker {

	public static void main(String args[]) throws Exception {

		/*
		 * Lager en server og kjører runmetoden for å ta i bruk funksjonene som
		 * kreves av den obligatoriske oppgaven
		 */
		ServerMaker Server = new ServerMaker();
		Server.run();

	}

	public void run() throws Exception {

		/*
		 * Definerer port og ipadresse for å oppnå kontakt med klienter og
		 * oppretter en feltvariabel til å ta vare på beskjed fra eventuelle
		 * klienter
		 */
		final int Port = 6969;
		String MessageFromClient;
		ServerSocket ServerSocket = new ServerSocket(Port);
		System.out.println("Server is ready for connection on port: " + Port);

		while (true) {

			/*
			 * Godtar kontakt med en klient og skriver ut hvilken IP adresse
			 * klienten har
			 */
			Socket Socket = ServerSocket.accept();
			System.out.println("Connection established from: " + Socket.getInetAddress().getHostAddress());

			/*
			 * Tar i mot beskjeden fra klienten og formaterer den i upper case
			 * slik at server kommandoene blir kjørt riktig
			 */
			InputStreamReader InputStreamReader = new InputStreamReader(Socket.getInputStream());
			BufferedReader BufferedReader = new BufferedReader(InputStreamReader);
			MessageFromClient = BufferedReader.readLine().toUpperCase();

			/*
			 * Sjekker om det er en beskjed fra klienten, hvis det er så sjekkes
			 * det om hvilken kommando som er blitt sendt fra klienten
			 */
			if (MessageFromClient != null) {

				/*
				 * Oppretter noen feltvariabler og formateringer for hvordan
				 * beskjeden skal sendes tilbake til klienten, med informasjon
				 * om hvilken klient som gav hvilken kommando
				 */
				PrintStream PrintStream = new PrintStream(Socket.getOutputStream());
				Date Today = new Date();
				String Full = String.format("%tB %<tY %<tT", Today);
				String Date = String.format("%tB %<tY", Today);
				PrintStream.print("Request from this IP: " + Socket.getInetAddress().getHostAddress() + "\n");

				/*
				 * sjekker for kommando "FULL" og sender full dato og tidspunkt
				 * tilbake til klient med informasjon til serveren om hvilken
				 * kommando og ipadresse
				 */
				if (MessageFromClient.equals("FULL")) {

					PrintStream.println(Full);
					System.out.println("IP: " + Socket.getInetAddress().getHostAddress()
							+ "\nCommand: FULL\nMessage sent: " + Full);

					/*
					 * sjekker for kommando "FULL" og sender dato tilbake til
					 * klient med informasjon til serveren om hvilken kommando
					 * og ipadresse
					 */
				} else if (MessageFromClient.equals("DATE")) {

					PrintStream.println(Date);
					System.out.println("IP: " + Socket.getInetAddress().getHostAddress()
							+ "\nCommand: DATE\nMessage sent: " + Date);

					/*
					 * sjekker for kommando "TIME" og sender tidspunkt tilbake
					 * til klient med informasjon til serveren om hvilken
					 * kommando og ipadresse
					 */
				} else if (MessageFromClient.equals("TIME")) {

					SimpleDateFormat Time = new SimpleDateFormat("HH:mm:ss");
					PrintStream.println(Time.format(Today));
					System.out.println("IP: " + Socket.getInetAddress().getHostAddress()
							+ "\nCommand: TIME\nMessage sent: " + Time);

					/*
					 * sjekker for kommando "CLOSE" og stenger serveren der man
					 * samtidig lukker socketen, man gir også informasjon til
					 * serveren og klient om hvilken kommando og ipadresse
					 */
				} else if (MessageFromClient.equals("CLOSE")) {

					PrintStream.println("Server is closed");
					System.out.println("IP: " + Socket.getInetAddress().getHostAddress()
							+ "\nCommand: CLOSE\nMessage sent: Server is closed");
					Socket.close();
					break;

					/*
					 * Det er blitt sendt en ugyldig kommando til serveren og
					 * serveren svarer med en "unknown command" til klienten og
					 * samme beskjed til seg selv med IP adressen
					 */
				} else {
					System.out.println("Unknown command from this IP: " + Socket.getInetAddress().getHostAddress());
					PrintStream.println("Unknown command");

				}
			}
		}

		/*
		 * Serveren har nå gått ut av sin run loop og dermed avsluttes Serveren
		 */
		ServerSocket.close();
	}
}
