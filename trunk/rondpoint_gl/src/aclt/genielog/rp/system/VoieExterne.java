package aclt.genielog.rp.system;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author Alexis Brenon
 * @author Cécilia Martin
 * @author Luc Chante
 * @author Tiphaine Teyssier
 */
class VoieExterne extends Voie {

	private final VoieEnum identifiant;

	/**
	 * Voie interne d'insertion dans le rond point.
	 */
	private VoieInterne interne;

	private Voiture sortie;

	/**
	 * File d'attente des voitures.
	 */
	private ConcurrentLinkedQueue<Voiture> voitures = new ConcurrentLinkedQueue<Voiture>();

	/**
	 * Constructeur
	 * 
	 * @param v
	 *            La voie interne à laquelle cette voie est reliée.
	 */
	VoieExterne(VoieEnum id, VoieInterne v) {
		super(id.name());
		identifiant = id;
		interne = v;
	}

	/**
	 * Indique si la voiture est la premiere sur la voie
	 * 
	 * @param v
	 * @return
	 */
	boolean isFirst(Voiture v) {
		return voitures.peek() == v;
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
		sortie = v;
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
	void entrer(Voiture v) {
		voitures.offer(v);
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

		voiture = voitures.peek();
		voiture.avancer();

		return voiture;
	}

	/**
	 * Vide complètement la voie.
	 * 
	 * @return Le nombre de voitures supprimées
	 */
	@Override
	public int vider() {
		int taille = voitures.size();
		voitures.clear();
		return taille;
	}

	/**
	 * Retourne la tranformation de base pour l'affichage des voitures dans cette
	 * voie.
	 * 
	 * @return La tranformation minimale pour cette voie.
	 */
	@Override
	public void paint(Graphics g) {
		int dx, dy;
		double theta;

		Graphics2D g2d = (Graphics2D) g;

		Voiture voiture = voitures.peek();
		if (voiture != null) {
			switch (identifiant) {
			case NORD:
				dx = 240;
				dy = 30;
				theta = Math.PI / 2.0;
				break;
			case OUEST:
				dx = 30;
				dy = 290;
				theta = 0.0;
				break;
			case SUD:
				dx = 290;
				dy = 510;
				theta = -Math.PI / 2.0;
				break;
			case EST:
				dx = 510;
				dy = 240;
				theta = -Math.PI;
				break;
			default:
				throw new IllegalArgumentException();
			}

			g2d.transform(new AffineTransform());
			AffineTransform tx = new AffineTransform();
			tx.translate(dx, dy);
			tx.rotate(theta, 30, 30);
			g2d.transform(tx);
			g2d.drawImage(voiture.getPicture(), 0, 0, this);
		}

		if (sortie != null) {
			switch (identifiant) {
			case NORD:
				dx = 290;
				dy = 30;
				theta = -Math.PI / 2.0;
				break;
			case OUEST:
				dx = 30;
				dy = 240;
				theta = -Math.PI;
				break;
			case SUD:
				dx = 240;
				dy = 510;
				theta = Math.PI / 2.0;
				break;
			case EST:
				dx = 510;
				dy = 290;
				theta = 0.0;
				break;
			default:
				throw new IllegalArgumentException();
			}

			g2d.transform(new AffineTransform());
			AffineTransform tx = new AffineTransform();
			tx.translate(dx, dy);
			tx.rotate(theta, 30, 30);
			g2d.transform(tx);
			g2d.drawImage(sortie.getPicture(), 0, 0, this);
			sortie = null;
		}
	}
}
