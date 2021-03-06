package aclt.genielog.rp.system;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author Alexis Brenon
 * @author Cécilia Martin
 * @author Luc Chante
 * @author Tiphaine Teyssier
 */
class VoieExterne extends Voie implements ActionListener, Circulable {

	private final VoieEnum identifiant;

	private Circulable circuleTmp = new VoieArretee();
	private Circulable circule = new VoieNormale();

	private final AtomicBoolean accident = new AtomicBoolean(false);

	/**
	 * Voie interne d'insertion dans le rond point.
	 */
	private VoieInterne interne;

	/**
	 * Variable de sauvegarde temporaire utile à l'affichage
	 */
	private Voiture[] paintSortie = {null, null};

	/**
	 * Variable de sauvegarde temporaire utile à l'affichage
	 */
	private Voiture paintTete = null;

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

        VoieEnum getEnum() {
            return identifiant;
        }

	/**
	 * Déclenche la sortie du rond-point par cette voie pour une voiture.
	 *
	 * @param v
	 *            La voiture qui sort du rond-point.
	 */
	void sort(Voiture v) {
		v.sengager(this);
		paintSortie[0] = v;
	}

	/**
	 * Retire une voiture de la file d'attente.
	 */
	@Override
	void quitter(Voiture v) {
		voitures.remove(v);
		paintTete = null;
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
	public Voiture circule() {
		return circule.circule();
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
	 * Exécuté à la fin d'un tour complet d'affichage.
	 */
	public void postPaint() {
		paintSortie[1] = paintSortie[0];
		paintSortie[0] = null;
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
			if (circule instanceof VoieArretee || paintTete == voiture) {
				percent = 1.0;
			}
			switch (identifiant) {
			case NORD:
				dx = 240;
				dy = (int) Math.round(30.0 - 90.0 * (1.0 - percent));
				theta = Math.PI / 2.0;
				break;
			case OUEST:
				dx = (int) Math.round(30.0 - 90.0 * (1.0 - percent));
				dy = 290;
				theta = 0.0;
				break;
			case SUD:
				dx = 290;
				dy = (int) Math.round(510.0 + 90.0 * (1.0 - percent));
				theta = -Math.PI / 2.0;
				break;
			case EST:
				dx = (int) Math.round(510.0 + 90.0 * (1.0 - percent));
				dy = 240;
				theta = -Math.PI;
				break;
			default:
				throw new IllegalArgumentException();
			}

			AffineTransform tx = new AffineTransform();
			tx.translate(dx, dy);
			tx.rotate(theta, 30, 30);
			g2d.drawImage(voiture.getPicture(), tx, this);
			g2d.transform(new AffineTransform());
			if (percent == 1.0) {
				paintTete = voiture;
			}
		}

		if (paintSortie[1] != null) {
			switch (identifiant) {
			case NORD:
				dx = 290;
				dy = (int) Math.round(30.0 - 90.0 * percent);
				theta = -Math.PI / 2.0;
				break;
			case OUEST:
				dx = (int) Math.round(30 - 90.0 * percent);
				dy = 240;
				theta = -Math.PI;
				break;
			case SUD:
				dx = 240;
				dy = (int) Math.round(510 + 90.0 * percent);
				theta = Math.PI / 2.0;
				break;
			case EST:
				dx = (int) Math.round(510 + 90.0 * percent);
				dy = 290;
				theta = 0.0;
				break;
			default:
				throw new IllegalArgumentException();
			}

			AffineTransform tx = new AffineTransform();
			tx.translate(dx, dy);
			tx.rotate(theta, 30, 30);
			g2d.drawImage(paintSortie[1].getPicture(), tx, this);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Circulable swap = circule;
		circule = circuleTmp;
		circuleTmp = swap;
	}

	class VoieArretee implements Circulable {
		@Override
		public Voiture circule() {
			return null;
		}
	}

	class VoieNormale implements Circulable {
		@Override
		public Voiture circule() {
			Voiture voiture = null;

			if (accident.compareAndSet(false, false)) {
				if (voitures.isEmpty()) {
					return null;
				}

				voiture = voitures.peek();
				voiture.avancer();
			}

			return voiture;
		}

	}
}
