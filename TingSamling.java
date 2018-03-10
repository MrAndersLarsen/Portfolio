/**
 * TingSamling.
 * 
 * Denne klassen definerer samling av ting.
 */
public class TingSamling {

	private static final int maksAntallTing = 10;
	private Ting[] ting;
	private int indeksNyTing;

	/**
	 * Konstruktoer.
	 */
	TingSamling() {
		ting = new Ting[maksAntallTing];
		indeksNyTing = 0;
	}

	/**
	 * Legger til en ting i samlingen. Dersom tingen allerede er i samlingen saa
	 * legges det til antallet av tingen.
	 * 
	 * @param nyTing
	 *            Ting som skal legges til i samlingen.
	 * @return true om vellykket, og ellers false.
	 */
	public boolean leggTilTing(Ting nyTing) {
		// Om tingen finnes i listen, oek antall.
		Ting gammelTing = hentTingFraNavn(nyTing.hentNavn());
		if (gammelTing != null) {
			int antall = gammelTing.hentAntall();
			antall += nyTing.hentAntall();
			gammelTing.settAntall(antall);
			return true;
		}
		// Om tingen maa legges til, sjekk at det er plass i listen, og
		// hvis ikke, gi feil.
		if (indeksNyTing == maksAntallTing) {
			System.out.println("Samlingen av ting er full!");
			return false;
		}
		ting[indeksNyTing] = nyTing;
		indeksNyTing++;
		return true;
	}

	/**
	 * Fjern ting fra samlingen.
	 * 
	 * @param i
	 *            Indeks til tingen som skal fjernes.
	 * @return true om vellykket, og ellers false.
	 */
	public boolean fjernTing(int i) {
		if (i < 0 || i >= indeksNyTing) {
			return false;
		}
		indeksNyTing--;
		ting[i] = ting[indeksNyTing];
		ting[indeksNyTing] = null;
		return true;
	}

	/**
	 * Henter antall ting.
	 * 
	 * @return Antall ting.
	 */
	public int hentAntallTing() {
		return indeksNyTing;
	}

	/**
	 * Henter en ting ut fra indeks.
	 * 
	 * @param i
	 *            Indeks.
	 * @return Ting, eller null om indeks er ugyldig.
	 */
	public Ting hentTingFraIndeks(int i) {
		if (i < 0 || i >= indeksNyTing) {
			return null;
		}
		return ting[i];
	}

	/**
	 * Henter en ting ut fra navn.
	 * 
	 * @param n
	 *            Navn.
	 * @return Indeks til ting, eller -1 om den ikke er funnet.
	 */
	public int hentTingIndeksFraNavn(String n) {
		// Se om tingen finnes i samlingen.
		for (int i = 0; i < indeksNyTing; i++) {
			// Om den finnes, returner tingen.
			if (ting[i].hentNavn().equals(n)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Henter en ting ut fra navn.
	 * 
	 * @param n
	 *            Navn.
	 * @return Ting, eller null om den ikke er funnet.
	 */
	public Ting hentTingFraNavn(String n) {
		int indeks = hentTingIndeksFraNavn(n);
		if (indeks == -1)
			return null;
		else
			return ting[indeks];
	}

	/**
	 * Skriver ut samlingen av ting.
	 */
	public String toString() {
		String s = "";
		for (int i = 0; i < indeksNyTing; i++) {
			if (i > 0)
				s += ", ";
			s += ting[i].hentNavn() + " (x" + ting[i].hentAntall() + ")";
		}
		return s;
	}

}
