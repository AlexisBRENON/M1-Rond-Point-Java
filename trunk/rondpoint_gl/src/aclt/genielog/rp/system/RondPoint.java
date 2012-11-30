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
	private final ArrayList<VoieExterne> voiesExternes;

	/**
	 * Liste des voies internes du rond point.
	 */
	private final ArrayList<VoieInterne> voiesInternes;

	private Stats stats = null;

	/**
	 * Crée un rond point à 4 voie
	 * 
	 * @param taille
	 *            la taille entre les voies
	 */
	public RondPoint(int taille) {
		int j, i;
		VoieInterne vi, suivante;
		VoieExterne sortie;

		voiesExternes = new ArrayList<VoieExterne>();
		voiesInternes = new ArrayList<VoieInterne>();

		for (i = 0; i < 4; i = i + 1) {
			vi = new VoieInterne(taille);
			voiesInternes.add(vi);
			voiesExternes.add(new VoieExterne(vi));
		}

		for (i = 0; i < 4; i = i + 1) {
			j = (i + 1) % 4;
			suivante = voiesInternes.get(j);
			sortie = voiesExternes.get(j);
			voiesInternes.get(i).config(suivante, sortie);
		}
	}

	public void attachStats() {
		stats = new Stats(voiesExternes);
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
	public Voiture ajouterVoiture(VoieEnum depart, VoieEnum destination) {
		VoieExterne entree, sortie;

		entree = voiesExternes.get(depart.ordinal());
		sortie = voiesExternes.get(destination.ordinal());
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
	public void tourneInterne() {
		Voiture voitureDeTete = null;

		for (VoieInterne voieInterne : voiesInternes) {
			voitureDeTete = voieInterne.circule(voitureDeTete, false);
		}
		voiesInternes.get(0).circule(voitureDeTete, true);
	}

	/**
	 * Déclenche la circulation des voitures sur les voies externes.
	 */
	public void tourneExterne() {
		for (VoieExterne voieExterne : voiesExternes) {
			voieExterne.circule();
		}
	}

	/**
	 * Vide la file d'attente d'une voie
	 * 
	 * @param voie
	 *            La voie concernée
	 */
	public void viderFile(VoieEnum voie) {
		VoieExterne v = voiesExternes.get(voie.ordinal());
		int taille = v.vider();
		stats.vidageVoie(v, taille);
	}
}
