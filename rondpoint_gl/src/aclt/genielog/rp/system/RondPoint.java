package aclt.genielog.rp.system;

import java.util.ArrayList;

/**
 * @author Alexis Brenon
 * @author Cécilia Martin
 * @author Luc Chante
 * @author Tiphaine Teyssier
 */
public class RondPoint {
	/**
	 * Liste des voies externes (entrée/sortie) du rond point.
	 */
	private final ArrayList<VoieExterne> voieExternes;

	/**
	 * Liste des voies internes du rond point.
	 */
	private final ArrayList<VoieInterne> voieInternes;

	private Stats stats = null;

	/**
	 * Crée un rond point à 4 voie
	 * 
	 * @param taille
	 *            la taille entre les voies
	 */
	RondPoint(int taille) {
		int j, i;
		VoieInterne vi, suivante;
		VoieExterne sortie;

		voieExternes = new ArrayList<VoieExterne>();
		voieInternes = new ArrayList<VoieInterne>();

		for (i = 0; i < 4; i = i + 1) {
			vi = new VoieInterne(taille);
			voieInternes.add(vi);
			voieExternes.add(new VoieExterne(vi));
		}

		for (i = 0; i < 4; i = i + 1) {
			j = (i + 1) % 4;
			suivante = voieInternes.get(j);
			sortie = voieExternes.get(j);
			voieInternes.get(i).config(suivante, sortie);
		}
	}

	void attachStats() {
		stats = new Stats(voieExternes);
	}

	public Stats getStats() {
		return stats;
	}

	/**
	 * Crée une voiture au départ de la voie depart, sortant à la voie destination
	 * 
	 * @param depart
	 *            Numéro de la voie de départ
	 * @param destination
	 *            Numéro de la voie de sortie
	 * @return Retourne la voiture nouvellement crée.
	 */
	Voiture ajouterVoiture(VoieEnum depart, VoieEnum destination) {
		VoieExterne entree, sortie;

		entree = voieExternes.get(depart.ordinal());
		sortie = voieExternes.get(destination.ordinal());
		Voiture voiture = new Voiture(sortie);
		if (stats != null) {
			voiture.addObserver(stats);
		}
		entree.entrer(voiture);
		return voiture;
	}

	/**
	 * Déclenche la circulation des voitures sur les voies internes.
	 */
	void tourneInterne() {
		Voiture voitureDeTete = null;

		for (VoieInterne voieInterne : voieInternes) {
			voitureDeTete = voieInterne.circule(voitureDeTete, false);
		}
		voieInternes.get(0).circule(voitureDeTete, true);
	}

	/**
	 * Déclenche la circulation des voitures sur les voies externes.
	 */
	void tourneExterne() {
		for (VoieExterne voieExterne : voieExternes) {
			voieExterne.circule();
		}
	}
}
