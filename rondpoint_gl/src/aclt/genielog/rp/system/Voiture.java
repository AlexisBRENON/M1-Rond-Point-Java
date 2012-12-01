package aclt.genielog.rp.system;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Observable;
import java.util.Random;

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

	private static Image rouge = null;
	private static Image noire = null;
	private static Image verte = null;
	private static Image jaune = null;

	static {
		Class<Voiture> cv = Voiture.class;
		String path = "/aclt/genielog/rp/ihm/img/rouge.png";
		try {
			path = cv.getResource(path).toURI().getPath();
			rouge = ImageIO.read(new File(path));
			path = "/aclt/genielog/rp/ihm/img/noire.png";
			path = cv.getResource(path).toURI().getPath();
			noire = ImageIO.read(new File(path));
			path = "/aclt/genielog/rp/ihm/img/verte.png";
			path = cv.getResource(path).toURI().getPath();
			verte = ImageIO.read(new File(path));
			path = "/aclt/genielog/rp/ihm/img/jaune.png";
			path = cv.getResource(path).toURI().getPath();
			jaune = ImageIO.read(new File(path));
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * La voie de destination de la voiture.
	 */
	private VoieExterne destination;
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
		switch (new Random().nextInt(4)) {
		case 0:
			picture = rouge;
			break;
		case 1:
			picture = noire;
			break;
		case 2:
			picture = verte;
			break;
		case 3:
		default:
			picture = jaune;
		}
	}

	void sengager(VoieExterne voie) {
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
}
