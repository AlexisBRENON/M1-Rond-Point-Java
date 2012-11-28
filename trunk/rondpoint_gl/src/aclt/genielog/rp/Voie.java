package aclt.genielog.rp;

/**
 * Classe abstraite Voie.
 *
 * Une voie est une partie du rond sur laquelle les voitures se déplacent.
 *
 * @author Alexis Brenon
 * @author Cécilia Martin
 * @author Luc Chante
 * @author Tiphaine Teyssier
 */
abstract class Voie {

	/**
	 * Le nom de la voie (utile pour les logs de fonctionnement).
	 */
	private String name;

	Voie(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Voie " + name;
	}
}
