package aclt.genielog.rp.system;

/**
 * @author Alexis Brenon
 * @author Cécilia Martin
 * @author Luc Chante
 * @author Tiphaine Teyssier
 */
class Voiture {

	private static int ID = 0;

	/**
	 * Crée une nouvelle voiture, l'insére sur la voie de départ à destination
	 * de la voie de destination.
	 *
	 * @param rp Le rond dans lequel on va insérer la voiture
	 * @param depart Le numéro de la voie de départ
	 * @param destination Le numéro de la voie d'arrivée
	 * @return La voiture nouvellement créée.
	 */
	static Voiture factory(RondPoint rp, VoieEnum depart, VoieEnum destination) {
		Voiture v = rp.ajouterVoiture(depart, destination);
		System.out.println(v + " entre sur " + depart + " et va sortir en " + destination);
		return v;
	}

	/**
	 * La voie de destination de la voiture.
	 */
	private VoieExterne destination;
	/**
	 * La voie sur laquelle la voiture se triouve actuellement.
	 */
	private Voie voieCourante;

	/**
	 * Le nom de la voiture (utile pour les logs de réroulement du simulateur)
	 */
	private String name;

	Voiture(VoieExterne dep, VoieExterne dest) {
		voieCourante = dep;
		destination = dest;
		name = "Voiture#" + (ID++);
	}

	/**
	 * Retourne la voie sur laquelle la voiture se trouve.
	 *
	 * @return
	 */
	Voie getEstSur() {
		return voieCourante;
	}

	/**
	 * Effectue l'action de circulation de la voiture.
	 */
	void avancer() {
		if (voieCourante instanceof VoieExterne) {
			avancer((VoieExterne) voieCourante);
		}
		else if (voieCourante instanceof VoieInterne) {
			avancer((VoieInterne) voieCourante);
		}
	}

	/**
	 * Effectue l'action de sortie de la voiture.
	 */
	void sortir() {
		((VoieInterne) voieCourante).sortir(this);
		voieCourante = destination;
	}

	/**
	 * Fait avancer la voiture sur la voie externe.
	 */
	private void avancer(VoieExterne voie) {
		if (voie.isFirst(this)) {
			if (voie.getVoieInterne().estLibre()) {
				voie.getVoieInterne().inserer(this);
				voieCourante = voie.getVoieInterne();
				System.out.println(this + " s'insère sur " + voieCourante);
			}
		}
	}

	/**
	 * Fait avancer la voiture sur la voie interne.
	 */
	private void avancer(VoieInterne voie) {
		if (voie.croisement(this)) {
			if (voie.maSortie(destination)) {
				sortir();
				System.out.println(this + " sort sur " + voieCourante);
			} else {
				voieCourante = voie.continuer(this);
				System.out.println(this + " continue sur " + voieCourante);
			}
		} else {
			voie.avancer(this);
		}
	}

	@Override
	public String toString() {
		return name;
	}
}
