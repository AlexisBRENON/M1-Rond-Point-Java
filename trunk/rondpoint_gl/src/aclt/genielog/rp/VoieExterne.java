package aclt.genielog.rp;

import java.util.LinkedList;

/**
 * @author Alexis Brenon
 * @author Cécilia Martin
 * @author Luc Chante
 * @author Tiphaine Teyssier
 */
class VoieExterne extends Voie {

	private static int ID = 0;

	/**
	 * Voie interne d'insertion dans le rond point.
	 */
	private VoieInterne interne;

	/**
	 * File d'attente des voitures.
	 */
	private LinkedList<Voiture> voitures = new LinkedList<Voiture>();

	VoieExterne(VoieInterne v) {
		super("Externe " + ID);
		ID++;
		interne = v;
	}

	/**
	 * Indique si la voiture est la premiere sur la voie
	 *
	 * @param v
	 * @return
	 */
	boolean isFirst(Voiture v) {
		return voitures.getFirst() == v;
	}

	VoieInterne getVoieInterne() {
		return interne;
	}

	void sortir(Voiture v) {
		voitures.remove(v);
	}

	void rentrer(Voiture v) {
		voitures.addLast(v);
	}

	void circule() {
		if (!voitures.isEmpty()) {
			voitures.getFirst().avancer();
		}
	}

}
