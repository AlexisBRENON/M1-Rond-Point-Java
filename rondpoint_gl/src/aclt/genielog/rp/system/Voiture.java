package aclt.genielog.rp.system;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.util.Observable;

import javax.imageio.ImageIO;

import aclt.genielog.rp.Simulateur;
import aclt.genielog.rp.system.Stats.Route;

/**
 * @author Alexis Brenon
 * @author Cécilia Martin
 * @author Luc Chante
 * @author Tiphaine Teyssier
 */
class Voiture extends Observable {

	private static int ID = 0;

	private static String[] images = { null, null, null, null };

	static {
		images[0] = "/aclt/genielog/rp/ihm/img/noire.png";
		images[1] = "/aclt/genielog/rp/ihm/img/verte.png";
		images[2] = "/aclt/genielog/rp/ihm/img/jaune.png";
		images[3] = "/aclt/genielog/rp/ihm/img/rouge.png";
	}

	/**
	 * La voie de destination de la voiture.
	 */
	private VoieExterne destination;

	/**
	 *
	 */
	private VoieExterne destinationForcee;

	/**
	 * La voie sur laquelle la voiture se triouve actuellement.
	 */
	private Voie voieCourante;

	private Route route;

	/**
	 * Le nom de la voiture (utile pour les logs de déroulement du simulateur)
	 */
	private String name;

	private Image picture;

	Voiture(VoieExterne dest) {
		name = "Voiture#" + (ID++);
		destination = dest;
	}

	void sengager(VoieExterne voie) {
		if (picture == null) {
			try {
				int ord = voie.getEnum().ordinal();
				String path = getClass().getResource(images[ord]).toURI().getPath();
				picture = ImageIO.read(new File(path));
				char[] sortie = destination.getEnum().toString().toCharArray();
				Graphics2D g2d = (Graphics2D) picture.getGraphics();
				g2d.setColor(Color.BLACK);
				g2d.rotate(-Math.PI / 2.0, 30, 30);
				g2d.drawChars(sortie, 0, 1, 28, 30);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		majVoieSuivante(voie);
	}

	protected void majVoieSuivante(Voie voie) {
		if (route == null) {
			route = new Route(voie);
			setChanged();
			notifyObservers(route);
		}
		else if (route.setTo(voie)) {
			setChanged();
			notifyObservers(route);
		}
		voieCourante = voie;
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
		((VoieInterne) voieCourante).quitter(this);
		majVoieSuivante(voieCourante);
	}

	/**
	 * Fait avancer la voiture sur la voie externe.
	 */
	private void avancer(VoieExterne voie) {
		if (voie.isFirst(this)) {
			if (voie.getVoieInterne().estLibre()) {
				voieCourante.quitter(this);
				voie.getVoieInterne().entrer(this);
				majVoieSuivante(voie.getVoieInterne());
				Simulateur.log(this + " s'insère sur " + voieCourante);
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
				Simulateur.log(this + " sort sur " + voieCourante);
			}
			else {
				majVoieSuivante(voie.continuer(this));
				Simulateur.log(this + " continue sur " + voieCourante);
			}
		}
		else {
			voie.avancer(this);
		}
	}

	Image getPicture() {
		return picture;
	}

	@Override
	public String toString() {
		return name;
	}

	void toggleForcerSortie(VoieExterne voieDeSortie) {
		if (destinationForcee == null) {
			destinationForcee = destination;
			destination = voieDeSortie;
			Simulateur.log(this + " sortie forcée vers " + voieDeSortie);
		}
		else {
			destination = destinationForcee;
			destinationForcee = null;
			Simulateur.log(this + " reprend son chemin normal");
		}
	}
}
