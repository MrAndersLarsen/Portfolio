import java.util.Scanner;
import java.util.Random;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * Eventyrspill.
 * 
 * Deler som skal utvides er merket med "TODO:".
 */
public class TekstEventyr {

	static int antallMonstre = 0;
	static int antallRom = 0;
	static Rom startRom = null;

	/**
   * Lar spilleren bruke ting fra sekken.
   * 
   * @param tastaur Tastatur for innlesing av kommandoer.
   * @param spiller Spilleren.
   * @param rom     Rommet.
   */
  private static void brukFraSekk(Scanner tastatur,Spiller spiller, Rom rom){
    // TODO Skriv ut hva som er i sekken, og spoer om hva spilleren vil bruke.
	  System.out.print("Ting som er i sekken: " spiller.hentSekk());
    // TODO Se etter ting i sekken, og om funnet, se om den kan brukes.
    // TODO For helsedrikk, legg 1 til spillerens helse.
    // TODO For bombe, trekk 1 fra monsterets helse dersom det er et monster i rommet.
  }

	/**
	 * Kjoerer kamp med monster. Dersom bruker velger aa avslutte saa returnerer
	 * metoden false.
	 * 
	 * @param tastaur
	 *            Tastatur for innlesing av kommandoer.
	 * @param rand
	 *            Generator for semi-tilfeldige verdier.
	 * @param spiller
	 *            Spilleren.
	 * @param monster
	 *            Monsteret.
	 * @param rom
	 *            Rommet.
	 * @return true dersom spillet fortsetter, eller false om spiller skriver
	 *         "avslutt".
	 */
	private static boolean slossMedMonster(Scanner tastatur, Random rand, Spiller spiller, Monster monster, Rom rom) {
		// Skriv ut helse
		System.out.println(spiller.hentNavn() + " sin helse: " + spiller.hentHelse());
		System.out.println("Monsterets helse: " + monster.hentHelse());

		// Hent spillervalg
		int spillerValg = 0;
		String[] valgMuligheter = { "stein", "saks", "papir" };
		System.out.println("Angrip med stein, saks eller papir, skriv sekk for aa bruke noe fra sekken, eller avslutt");
		// Hent spillervalg i løkke inntil gyldig valg er gitt
		// 0: stein
		// 1: saks
		// 2: papir
		boolean gyldigValg = false;
		while (!gyldigValg) {
			String spillerValgStreng = tastatur.nextLine();
			if (spillerValgStreng.equals("avslutt")) {
				return false;
			} else if (spillerValgStreng.equals("sekk")) {
				brukFraSekk(tastatur, spiller, rom);
				return true;
			}
			for (int v = 0; v < 3; v++) {
				if (spillerValgStreng.equals(valgMuligheter[v])) {
					gyldigValg = true;
					spillerValg = v;
					break;
				}
			}
			if (!gyldigValg) {
				System.out.println("Ugyldig valg");
			}
		}

		// Hent datavalg og skriv ut valg
		int dataValg = rand.nextInt(3);
		System.out.println("Spillervalg: " + valgMuligheter[spillerValg]);
		System.out.println("Monstervalg: " + valgMuligheter[dataValg]);

		// Anvend spilleregler
		if (spillerValg == dataValg) {
			System.out.println("Uavgjort");
		} else if (dataValg == (spillerValg + 1) % 3) {
			System.out.println(spiller.hentNavn() + " vant runden");
			monster.settHelse(monster.hentHelse() - 1);
		} else if ((dataValg + 1) % 3 == spillerValg) {
			System.out.println("Monsteret vant runden");
			spiller.settHelse(spiller.hentHelse() - 1);
		}

		return true;
	}

	/**
	 * Lar spilleren ta ting fra rommet.
	 * 
	 * @param tastaur
	 *            Tastatur for innlesing av kommandoer.
	 * @param spiller
	 *            Spilleren.
	 * @param rom
	 *            Rommet.
	 * @param taTekst
	 *            Navnet paa tingen som skal taes fra rommet.
	 */
	private static void taFraRom(Scanner tastatur, Spiller spiller, Rom rom, String taTekst) {
		// TODO Finn tingen ut fra navn.
		int indeks = rom.hentTingSamling().hentTingIndeksFraNavn(taTekst);
		if (indeks == -1) {
			System.out.print("Tingen finnes ikke i rommet.");
		} else {
			// TODO Ta tingen bort fra rommet.
			Ting tingen = rom.hentTingSamling().hentTingFraIndeks(indeks);
			rom.hentTingSamling().fjernTing(indeks);
			// TODO Legg tingen i spillerens sekk.
			spiller.hentSekk().leggTilTing(tingen);
		}
	}

	/**
	 * Lar spilleren navigere rom. Dersom bruker velger aa avslutte saa
	 * returnerer metoden false.
	 * 
	 * @param tastaur
	 *            Tastatur for innlesing av kommandoer.
	 * @param spiller
	 *            Spilleren.
	 * @param rom
	 *            Rommet.
	 * @return true dersom spillet fortsetter, eller false om spiller skriver
	 *         "avslutt".
	 */
	private static boolean navigerRom(Scanner tastatur, Spiller spiller, Rom rom) {
		rom.seIRommet();
		System.out.println(
				"Skriv hvor du vil gaa (oest, vest, nord, soer), ta og navn paa ting for aa ta noe i rommet, sekk for aa bruke noe fra sekken, eller avslutt:");
		String valgTekst = tastatur.nextLine();
		String[] deler = valgTekst.split(" ");
		if (valgTekst.equals("oest")) {
			spiller.gaaMotOest();
		} else if (valgTekst.equals("vest")) {
			spiller.gaaMotVest();
		} else if (valgTekst.equals("nord")) {
			spiller.gaaMotNord();
		} else if (valgTekst.equals("soer")) {
			spiller.gaaMotSoer();
		} else if (deler[0].equals("ta")) {
			if (deler.length == 2)
				taFraRom(tastatur, spiller, rom, deler[1]);
			else
				System.out.println(
						"For aa ta noe, skriv ta fulgt av navn paa tingen. Tingens navn kan ikke ha mellomrom.");
		} else if (valgTekst.equals("sekk")) {
			brukFraSekk(tastatur, spiller, rom);
		} else if (valgTekst.equals("avslutt")) {
			return false;
		} else {
			System.out.println("Ukjent kommando.");
		}
		return true;
	}

	/**
	 * Lager test-brett.
	 */
	private static void lagTestBrett() {
		// Lag rom
		startRom = new Rom();
		Rom v1Rom = new Rom();
		Rom vn1Rom = new Rom();
		Rom vnn1Rom = new Rom();
		Rom vs1Rom = new Rom();
		Rom.kobleVestOest(v1Rom, startRom);
		Rom.kobleNordSoer(vn1Rom, v1Rom);
		Rom.kobleNordSoer(vnn1Rom, vn1Rom);
		Rom.kobleNordSoer(v1Rom, vs1Rom);
		Rom o1Rom = new Rom();
		Rom on1Rom = new Rom();
		Rom os1Rom = new Rom();
		Rom.kobleVestOest(startRom, o1Rom);
		Rom.kobleNordSoer(on1Rom, o1Rom);
		Rom.kobleNordSoer(o1Rom, os1Rom);
		antallRom = 8;

		// Legg inn ting
		v1Rom.hentTingSamling().leggTilTing(new Ting("gull", 3));
		o1Rom.hentTingSamling().leggTilTing(new Ting("gull", 2));
		vs1Rom.hentTingSamling().leggTilTing(new Ting("helsedrikk", 1));
		o1Rom.hentTingSamling().leggTilTing(new Ting("bombe", 1));

		// Lag monstre
		vnn1Rom.settMonster(new Monster(1, 1));
		vs1Rom.settMonster(new Monster(1, 2));
		on1Rom.settMonster(new Monster(1, 1));
		os1Rom.settMonster(new Monster(1, 2));
		antallMonstre = 4;
	}

	/**
	 * Laster brett fra fil.
	 * 
	 * @param filBane
	 *            Filbane for brettet som skal lastes.
	 * @return true om vellykket, eller false om feil oppstod.
	 */
	private static boolean lesBrettFraFil(String filBane) {
		// TODO Aapne fil for lesing.
		// TODO Les inn foerste linje: antall rom; indeks for start-rom.
		// TODO Lag rom.
		// TODO Les inn koblinger mellom rom.
		// TODO Les inn monstre i rom.
		// TODO Les inn ting i rom.
		// TODO Sett startRom til rommet som spilleren starter i.
		// TODO Sett antallMonstre til antall monstre i brettet.
		// TODO Sett antallRom til antall rom i brettet.
		return true;
	}

	/**
	 * Programmet starter her.
	 */
	public static void main(String[] args) {
		Scanner tastatur = new Scanner(System.in);
		Random rand = new Random();

		System.out.println("Velkommen til teksteventyret!");

		antallMonstre = 0;

		// Hent spillernavn og lag spiller
		System.out.println("Hva heter du?");
		String navn = tastatur.nextLine();
		// Spoer om spilleren vil laste brett fra en fil eller bruke testbrett
		System.out.println(
				"Hvilket brett vil du spille? Skriv navn paa fil, eller @ for aa bruke innebygget test-brett:");
		String brettNavn = tastatur.nextLine();
		if (brettNavn.equals("@")) {
			lagTestBrett();
		} else {
			// Last brett fra fil
			if (!lesBrettFraFil(brettNavn)) {
				return;
			}
		}

		Spiller spiller = new Spiller(navn, startRom, 3);
		System.out.println(spiller.hentNavn() + " finner seg selv i en verden med " + antallRom + " rom og "
				+ antallMonstre + " monstre, med kun stein/saks/papir til forsvar. " + spiller.hentNavn() + " har "
				+ spiller.hentHelse()
				+ " helsepoeng. Forsoek aa samle saa mye som mulig i sekken, og aa bekjempe alle monstrene. Lykke til!");

		// Kjoer spill-loekke
		boolean ferdig = false;
		while (!ferdig) {
			Rom iRom = spiller.hentRom();
			Monster romMonster = iRom.hentMonster();
			if (romMonster != null) {
				// Dersom det er et monster i rommet saa maa det bekjempes.
				System.out.println("Det er et monster i rommet!");
				if (!slossMedMonster(tastatur, rand, spiller, romMonster, iRom)) {
					ferdig = true;
				}
				// Om monsteret slo spilleren saa er spillet slutt.
				if (spiller.hentHelse() == 0) {
					System.out.println(spiller.hentNavn() + " har tapt spillet.");
					System.out.println("I sekk: " + spiller.hentSekk());
					System.out.println("Poeng: " + spiller.beregnPoeng());
					ferdig = true;
					// Om spilleren slo monsteret saa ta bort monsteret fra
					// rommet.
				} else if (romMonster.hentHelse() == 0) {
					System.out.println(spiller.hentNavn() + " bekjempet monsteret!");
					// La monsteret legge igjen gull i rommet.
					int monsterGull = romMonster.hentGull();
					iRom.hentTingSamling().leggTilTing(new Ting("gull", monsterGull));
					System.out.println("Monsteret la fra seg " + monsterGull + " gull.");
					// Ta bort det bekjempede monsteret.
					iRom.settMonster(null);
					antallMonstre--;
					if (antallMonstre == 0) {
						System.out.println(spiller.hentNavn() + " har bekjempet alle monstrene.");
						System.out.println(spiller.hentNavn() + " har vunnet spillet!");
						spiller.hentSekk().leggTilTing(new Ting("gull", monsterGull));
						System.out.println(spiller.hentNavn() + " tok gullet fra det siste monsteret.");
						System.out.println("I sekk: " + spiller.hentSekk());
						System.out.println("Poeng: " + spiller.beregnPoeng());
						ferdig = true;
					} else {
						System.out.println("Det er " + antallMonstre + " monstre igjen i brettet.");
					}
				}
			} else {
				// Om det ikke er en kamp med monster saa kan vi utforske.
				if (!navigerRom(tastatur, spiller, iRom)) {
					ferdig = true;
				}
			}
		}

	}

}
