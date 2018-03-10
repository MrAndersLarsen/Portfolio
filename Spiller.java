/**
 * Spiller.
 * 
 * Denne klassen definerer spilleren, med rom.
 */
public class Spiller {

	private String navn;
	private Rom iRom;
	private int helse;
	private TingSamling sekk;

	/**
	 * Konstruktoer.
	 * 
	 * @param n
	 *            Navn.
	 * @param r
	 *            Rom.
	 * @param h
	 *            Helse.
	 */
	Spiller(String n, Rom r, int h) {
		navn = n;
		iRom = r;
		helse = h;
		sekk = new TingSamling();
	}

	/**
	 * Setter spillerens navn.
	 * 
	 * @param n
	 *            Navn.
	 */
	public void settNavn(String n) {
		navn = n;
	}

	/**
	 * Henter spillerens navn.
	 * 
	 * @return Navn.
	 */
	public String hentNavn() {
		return navn;
	}

	/**
	 * Setter spillerens helse.
	 * 
	 * @param h
	 *            Helse.
	 */
	public void settHelse(int h) {
		helse = h;
	}

	/**
	 * Henter spillerens helse.
	 * 
	 * @return Helse.
	 */
	public int hentHelse() {
		return helse;
	}

	/**
	 * Setter rommet spilleren er i.
	 * 
	 * @param r
	 *            Rom.
	 */
	public void settRom(Rom r) {
		iRom = r;
	}

	/**
	 * Henter rommet spilleren er i.
	 * 
	 * @return Rom.
	 */
	public Rom hentRom() {
		return iRom;
	}

	/**
	 * Henter spillers samling av ting.
	 * 
	 * @return Spillers samling av ting.
	 */
	public TingSamling hentSekk() {
		return sekk;
	}

	/**
	 * Spilleren gaar mot oest, og hvilket rom spilleren er i oppdateres.
	 * 
	 * @return true dersom spilleren beveget seg, og false ellers.
	 */
	public boolean gaaMotOest() {
		if (iRom == null) {
			return false;
		}
		Rom r = iRom.hentOest();
		if (r == null) {
			System.out.println("Det er ingen rom mot oest!");
			return false;
		}
		iRom = r;
		System.out.println(navn + " gikk mot oest.");
		return true;
	}

	/**
	 * Spilleren gaar mot vest, og hvilket rom spilleren er i oppdateres.
	 * 
	 * @return true dersom spilleren beveget seg, og false ellers.
	 */
	public boolean gaaMotVest() {
		if (iRom == null) {
			return false;
		}
		Rom r = iRom.hentVest();
		if (r == null) {
			System.out.println("Det er ingen rom mot vest!");
			return false;
		}
		iRom = r;
		System.out.println(navn + " gikk mot vest.");
		return true;
	}

	/**
	 * Spilleren gaar mot nord, og hvilket rom spilleren er i oppdateres.
	 * 
	 * @return true dersom spilleren beveget seg, og false ellers.
	 */
	public boolean gaaMotNord() {
		if (iRom == null) {
			return false;
		}
		Rom r = iRom.hentNord();
		if (r == null) {
			System.out.println("Det er ingen rom mot nord!");
			return false;
		}
		iRom = r;
		System.out.println(navn + " gikk mot nord.");
		return true;
	}

	/**
	 * Spilleren gaar mot soer, og hvilket rom spilleren er i oppdateres.
	 * 
	 * @return true dersom spilleren beveget seg, og false ellers.
	 */
	public boolean gaaMotSoer() {
		if (iRom == null) {
			return false;
		}
		Rom r = iRom.hentSoer();
		if (r == null) {
			System.out.println("Det er ingen rom mot soer!");
			return false;
		}
		iRom = r;
		System.out.println(navn + " gikk mot soer.");
		return true;
	}

	/**
	 * Beregner poeng. Dette er sum av antall av ting i sekken til spilleren.
	 * 
	 * @return Antall poeng.
	 */
	public int beregnPoeng() {
		int poeng = 0;
		for (int i = 0; i < sekk.hentAntallTing(); i++) {
			poeng += sekk.hentTingFraIndeks(i).hentAntall();
		}
		return poeng;
	}

}
