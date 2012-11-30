package aclt.genielog.rp.system;

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

	void sort(Voiture v) {
		v.sengager(this);
	}

	@Override
	void quitter(Voiture v) {
		voitures.remove(v);
	}

	@Override
	synchronized void entrer(Voiture v) {
		voitures.addLast(v);
		v.sengager(this);
	}

	void circule() {
		if (!voitures.isEmpty()) {
			voitures.getFirst().avancer();
		}
	}

	public synchronized int vider() {
		int taille = voitures.size();
		voitures.clear();
		return taille;
	}

}
