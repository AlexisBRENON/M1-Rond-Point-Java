package aclt.genielog.rp;

import java.util.ArrayList;

/**
 * Le Systeme
 * @author tiph
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

	/**
	 * Crée un rond point à 4 voie
	 * @param taille la taille entre les voies
	 */
	RondPoint(int taille){
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
			voieInternes.get(j).config(suivante, sortie);
		}
	}
	
	/**
	 * Crée une voiture au départ de la voie depart, sortant à la voie destination
	 * 
	 * @param depart Numéro de la voie de départ
	 * @param destination Numéro de la voie de sortie
	 * @return Retourne la voiture nouvellement crée.
	 */
	Voiture ajouterVoiture(int depart, int destination) {
		VoieExterne entree, sortie;
		
		if (voieExternes.size() < depart) {
			throw new IllegalArgumentException("depart doit être inférieur à " + voieExternes.size());
		}
		if (voieExternes.size() < destination) {
			throw new IllegalArgumentException("destination doit être inférieur à " + voieExternes.size());
		}
		
		entree = voieExternes.get(depart);
		sortie = voieExternes.get(destination);
		Voiture voiture = new Voiture(entree, sortie);
		entree.rentrer(voiture);
		return voiture;
	}

	void tourneInterne() {
		Voiture voitureDeTete = null;

		for (VoieInterne voieInterne: voieInternes) {
			voitureDeTete = voieInterne.circule(voitureDeTete, false);
		}
		voieInternes.get(0).circule(voitureDeTete, true);
	}

	void tourneExterne() {
		for (VoieExterne voieExterne: voieExternes) {
			if (!voieExterne.getVoitures().isEmpty()) {
				voieExterne.getVoitures().getFirst().Avancer();
			}
		}
	}
}
