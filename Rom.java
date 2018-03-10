/**
 * Rom.
 * 
 * Denne klassen definerer rom ved hjelp av liste.
 */
public class Rom {

	private Rom romOest;
	private Rom romVest;
	private Rom romNord;
	private Rom romSoer;
	private Monster romMonster;
	private TingSamling tingSamling;

	/**
	 * Konstruktoer.
	 */
	Rom() {
		tingSamling = new TingSamling();
	}

	/**
	 * Skriver ut hvilke naborom rommet har.
	 */
	public void seIRommet() {
		if (tingSamling.hentAntallTing() > 0) {
			System.out.println("Ting i rommet: " + tingSamling);
		} else {
			System.out.println("Det er ingen ting i rommet");
		}
		if (romOest != null) {
			System.out.println("Det er et rom mot oest");
		}
		if (romVest != null) {
			System.out.println("Det er et rom mot vest");
		}
		if (romSoer != null) {
			System.out.println("Det er et rom mot soer");
		}
		if (romNord != null) {
			System.out.println("Det er et rom mot nord");
		}
	}

	/**
	 * Setter monsteret i rommet.
	 * 
	 * @param m
	 *            Monsteret.
	 */
	public void settMonster(Monster m) {
		romMonster = m;
	}

	/**
	 * Henter monsteret i rommet.
	 * 
	 * @return Monsteret.
	 */
	public Monster hentMonster() {
		return romMonster;
	}

	/**
	 * Setter rommet til oest.
	 * 
	 * @param r
	 *            Rom.
	 */
	public void settOest(Rom r) {
		romOest = r;
	}

	/**
	 * Henter rommet til oest.
	 * 
	 * @return Rom.
	 */
	public Rom hentOest() {
		return romOest;
	}

	/**
	 * Setter rommet til vest.
	 * 
	 * @param r
	 *            Rom.
	 */
	public void settVest(Rom r) {
		romVest = r;
	}

	/**
	 * Henter rommet til vest.
	 * 
	 * @return Rom.
	 */
	public Rom hentVest() {
		return romVest;
	}

	/**
	 * Setter rommet til nord.
	 * 
	 * @param r
	 *            Rom.
	 */
	public void settNord(Rom r) {
		romNord = r;
	}

	/**
	 * Henter rommet til nord.
	 * 
	 * @return Rom.
	 */
	public Rom hentNord() {
		return romNord;
	}

	/**
	 * Setter rommet til soer.
	 * 
	 * @param r
	 *            Rom.
	 */
	public void settSoer(Rom r) {
		romSoer = r;
	}

	/**
	 * Henter rommet til soer.
	 * 
	 * @return Rom.
	 */
	public Rom hentSoer() {
		return romSoer;
	}

	/**
	 * Lenker et rom til vest med et til oest.
	 * 
	 * @param vest
	 *            Rommet til vest.
	 * @param oest
	 *            Rommet til oest.
	 */
	public static void kobleVestOest(Rom vest, Rom oest) {
		if (oest != null) {
			oest.settVest(vest);
		}
		if (vest != null) {
			vest.settOest(oest);
		}
	}

	/**
	 * Lenker et rom til nord med et til soer.
	 * 
	 * @param nord
	 *            Rommet til nord.
	 * @param soer
	 *            Rommet til soer.
	 */
	public static void kobleNordSoer(Rom nord, Rom soer) {
		if (nord != null) {
			nord.settSoer(soer);
		}
		if (soer != null) {
			soer.settNord(nord);
		}
	}

	/**
	 * Henter rommets samling av ting.
	 * 
	 * @return Rommets samling av ting.
	 */
	public TingSamling hentTingSamling() {
		return tingSamling;
	}

}
