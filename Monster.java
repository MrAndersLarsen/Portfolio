/**
 * Monster.
 * 
 * Denne klassen definerer et monster.
 */
public class Monster {

	private int helse;
	private int gull;

	/**
	 * Konstruktoer.
	 * 
	 * @param h
	 *            Helse.
	 * @param g
	 *            Mengde gull.
	 */
	Monster(int h, int g) {
		helse = h;
		gull = g;
	}

	/**
	 * Setter helsen til monsteret.
	 * 
	 * @param h
	 *            Helse.
	 */
	public void settHelse(int h) {
		helse = h;
	}

	/**
	 * Henter helsen til monsteret.
	 * 
	 * @return Helse.
	 */
	public int hentHelse() {
		return helse;
	}

	/**
	 * Setter mengde gull til monsteret.
	 * 
	 * @param g
	 *            Mengde gull.
	 */
	public void settGull(int g) {
		gull = g;
	}

	/**
	 * Henter mengde gull til monsteret.
	 * 
	 * @return Mengde gull.
	 */
	public int hentGull() {
		return gull;
	}

}
