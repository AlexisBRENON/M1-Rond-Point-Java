package aclt.genielog.rp;
/**
 *
 * @author tiph
 */
abstract class Voie {

	private String name;

	Voie(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Voie " + name;
	}
}
