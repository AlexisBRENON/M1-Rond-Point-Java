package aclt.genielog.rp;

import java.util.LinkedList;

/**
 * 
 * @author tiph
 */
class VoieExterne extends Voie {

	private static int ID = 0;

	private VoieInterne interne;
	private LinkedList<Voiture> voitures = new LinkedList<Voiture>();

	public VoieExterne(VoieInterne v) {
		super("Externe " + ID);
		ID++;
		interne = v;
	}

	public LinkedList<Voiture> getVoitures() {
		return voitures;
	}

	/**
	 * Indique si la voiture est la premiere sur la voie
	 * 
	 * @param v
	 * @return
	 */
	public boolean isFirst(Voiture v) {
		return voitures.getFirst() == v;
	}

	public VoieInterne GetVoieInterne() {
		return interne;
	}

	public void Sortir(Voiture v) {
		voitures.remove(v);
	}

	public void Rentrer(Voiture v) {
		voitures.addLast(v);
	}

}
