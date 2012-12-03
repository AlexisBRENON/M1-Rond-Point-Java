package aclt.genielog.rp.system;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

/**
 * @author Alexis Brenon
 * @author Cécilia Martin
 * @author Luc Chante
 * @author Tiphaine Teyssier
 */
class VoieInterne extends Voie {

	private static int ID = 0;

	/**
	 * Voie interne suivante dans le rond point.
	 */
	private VoieInterne voieSuivante;
	/**
	 * Voie de sortie
	 */
	private VoieExterne voieDeSortie;
	/**
	 * Liste des voitures actuellement sur la voie interne
	 */
	private Voiture[] vehicules;

	private int id;

	/**
	 * Constructeur
	 * 
	 * @param taille
	 *            La taille de la file interne de voiture (nombre de voitures pouvant
	 *            se trouver simultanément dans un quart de rond-point).
	 */
	VoieInterne(int taille) {
		super("Interne " + ID);
		id = ID;
		ID++;
		vehicules = new Voiture[taille];
	}

	/**
	 * Retourne si la voie de sortie accessible par cette voie interne est la
	 * même que la voie passé en paramètre.
	 * 
	 * @param sortie
	 *            La voie extern attendue
	 * @return Si la voie externe attendu correspond à celle accessible par
	 *         cette voie.
	 */
	boolean maSortie(VoieExterne sortie) {
		return voieDeSortie.equals(sortie);
	}

	/**
	 * Attribue la voie interne suivante et la voie externe (de sortie).
	 * 
	 * @param suivante
	 *            La voie interne suivante
	 * @param sortie
	 *            La voie de sortie du rond-point.
	 */
	void config(VoieInterne suivante, VoieExterne sortie) {
		voieSuivante = suivante;
		voieDeSortie = sortie;
	}

	/**
	 * Retourne s'il est possible de s'insérer dans cette voie. c.a.d. la
	 * première place est libre.
	 * 
	 * @return true si il y a une place pour s'inserer, false sinon.
	 */
	boolean estLibre() {
		return vehicules[0] == null;
	}

	/**
	 * Teste si la voiture donnée est au croisement de cette voie, de la voie
	 * interne suivante et la voie externe accessible par cette voie.
	 * 
	 * @param v
	 *            La voiture pour laquelle on veut savoir si elle est au croisement.
	 * @return true si la voiture est au croisement, false sinon.
	 */
	boolean croisement(Voiture v) {
		return vehicules[vehicules.length - 1] == v;
	}

	/**
	 * La voiture s'insère dans le rond point par cette voie.
	 * 
	 * @param v
	 *            La voiture a insérer
	 */
	@Override
	void entrer(Voiture v) {
		vehicules[0] = v;
	}

	/**
	 * La voiture sort du rond point
	 * 
	 * @param v
	 */
	@Override
	void quitter(Voiture v) {
		// Verifier quelle est bien en droit de sortir (arriver au croisement)
		if (croisement(v)) {
			vehicules[vehicules.length - 1] = null;
			voieDeSortie.sort(v);
		}
	}

	/**
	 * Voiture en attente d'insertion sur la voie suivante.
	 */
	// TODO trouver une autre implémentation ce n'est pas très beau d'avoir un
	// attribut qui sert de variable temporaire.
	private Voiture waittingForInsertion = null;

	/**
	 * La voiture continue sur la voie interne suivante
	 * 
	 * @param v
	 * @return
	 */
	VoieInterne continuer(Voiture v) {
		vehicules[vehicules.length - 1] = null;
		waittingForInsertion = v;
		return voieSuivante;
	}

	/**
	 * Avance la voiture à l'intérieur de la voie elle passe de l'emplacement i à
	 * l'emplacement i+1.
	 * 
	 * @param v
	 *            La voiture qui avance
	 */
	void avancer(Voiture v) {
		System.out.println("avancer(" + v + ")");
		int i = 0;
		while (i < vehicules.length && !v.equals(vehicules[i])) {
			i++;
		}
		vehicules[i] = null;
		vehicules[i + 1] = v;
	}

	/**
	 * Gére la circulation sur la voie pour un tour
	 * 
	 * @return La voiture qui sort de cette voie pour la voie suivante.
	 */
	@Override
	synchronized Voiture circule() {
		Voiture v;
		int length = vehicules.length;

		for (int i = length - 1; i >= 0; i = i - 1) {
			v = vehicules[i];
			if (v != null) {
				v.avancer();
			}
		}

		if (waittingForInsertion == null) {
			return null;
		}

		v = waittingForInsertion;
		waittingForInsertion = null;
		return v;
	}

	/**
	 * Vide complètement la voie.
	 * 
	 * @return Le nombre de voitures supprimées
	 */
	@Override
	synchronized int vider() {
		int compte = 0;
		for (int i = 0; i < vehicules.length; i = i + 1) {
			compte = compte + (vehicules[i] == null ? 0 : 1);
			vehicules[i] = null;
		}
		return compte;
	}

	/**
	 * Variable telporaire pour l'affichage de l'animation.
	 */
	private double percent = 1.0;

	/**
	 * Prépare l'affichage concernant l'animation entre le tour précédent et le tour
	 * suivnat.
	 * 
	 * @param percent
	 *            Pourcentage d'avancement de l'animation.
	 */
	@Override
	public void prePaint(double percent) {
		this.percent = percent;
	}

	/**
	 * Retourne la tranformation de base pour l'affichage des voitures dans cette
	 * voie.
	 * 
	 * @return La tranformation minimale pour cette voie.
	 */
	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		double div = 4.0 * vehicules.length;
		for (int i = 0; i < vehicules.length; i = i + 1) {
			Voiture voiture = vehicules[i];
			if (voiture != null) {
				g2d.transform(new AffineTransform());
				AffineTransform tx = new AffineTransform();
				double theta = (3 - id) * Math.PI / 2.0 - 2.0 * (i + percent) / div
						* Math.PI;

				tx.translate(420, 270);
				tx.rotate(-Math.PI / 2.0, 30, 30);
				tx.rotate(theta, 30, -120);
				g2d.drawImage(voiture.getPicture(), tx, this);
			}
		}
	}
}
