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

	/**
	 * Constructeur
	 * 
	 * @param v
	 *            La voie interne à laquelle cette voie est reliée.
	 */
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

	/**
	 * Retoune la voie interne accessible par cette voie.
	 * 
	 * @return La voie interne du rond-point accessible.
	 */
	VoieInterne getVoieInterne() {
		return interne;
	}

	/**
	 * Déclenche la sortie du rond-point par cette voie pour une voiture.
	 * 
	 * @param v
	 *            La voiture qui sort du rond-point.
	 */
	void sort(Voiture v) {
		v.sengager(this);
	}

	/**
	 * Retire une voiture de la file d'attente.
	 */
	@Override
	void quitter(Voiture v) {
		voitures.remove(v);
	}

	/**
	 * Ajoute une voiture en queue de la file d'attente.
	 */
	@Override
	synchronized void entrer(Voiture v) {
		voitures.addLast(v);
		v.sengager(this);
	}

	/**
	 * Déclenche l'avancement de la voiture en tête de file.
	 * 
	 * @return La voiture qui sort de cette voie et entre dans le rond-point.
	 */
	@Override
	Voiture circule() {
		Voiture voiture;

		if (voitures.isEmpty()) {
			return null;
		}

		voiture = voitures.getFirst();
		voiture.avancer();

		return voiture;
	}

	/**
	 * Vide complètement la voie.
	 * 
	 * @return Le nombre de voitures supprimées
	 */
	@Override
	public synchronized int vider() {
		int taille = voitures.size();
		voitures.clear();
		return taille;
	}

}
