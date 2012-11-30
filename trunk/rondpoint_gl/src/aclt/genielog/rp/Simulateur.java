package aclt.genielog.rp;

import java.util.Random;

import javax.swing.UIManager;

import aclt.genielog.rp.ihm.SimulateurUI;
import aclt.genielog.rp.lib.Flux;
import aclt.genielog.rp.lib.Tour;
import aclt.genielog.rp.system.RondPoint;
import aclt.genielog.rp.system.VoieEnum;

/**
 * @author Alexis Brenon
 * @author Cécilia Martin
 * @author Luc Chante
 * @author Tiphaine Teyssier
 */
public class Simulateur {

	private static final Random random = new Random(System.nanoTime());

	/**
	 * Le rond point du simulateur.
	 */
	private final RondPoint rp;

	private final SimulateurUI UI;

	private final Tour tour;

	public Simulateur(int taille) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		rp = new RondPoint(taille);
		rp.attachStats();
		tour = new Tour(this);
		UI = new SimulateurUI(this);
	}

	/**
	 * Vide la file d'attente d'une voie
	 * 
	 * @param voie
	 *            La voie concernée
	 */
	public void viderFile(VoieEnum voie) {
		rp.viderFile(voie);
	}

	/**
	 * Déclenche le tour suivant.
	 */
	public void tourSuivant() {
		rp.tourneInterne();
		rp.tourneExterne();
	}

	/**
	 * Démarre la simulation.
	 */
	public void lancer() {
		UI.setVisible(true);
		tour.start();

		Flux nord = new Flux(this, VoieEnum.NORD);
		Flux est = new Flux(this, VoieEnum.EST);
		Flux sud = new Flux(this, VoieEnum.SUD);
		Flux ouest = new Flux(this, VoieEnum.OUEST);
		UI.setFluxListener(nord, est, sud, ouest);
		nord.start();
		est.start();
		sud.start();
		ouest.start();

		UI.setVitesseListener(tour);
	}

	/**
	 * Choisi une voie (Nord, Sud, Est, Ouest) aléatoirement.
	 * 
	 * @return Une voie choisie aléatoirement.
	 */
	private static VoieEnum voieAleatoire() {
		VoieEnum v = VoieEnum.NORD;
		switch (random.nextInt(4)) {
		case 0:
			v = VoieEnum.NORD;
			break;
		case 1:
			v = VoieEnum.EST;
			break;
		case 2:
			v = VoieEnum.SUD;
			break;
		case 3:
			v = VoieEnum.OUEST;
			break;
		}
		return v;

	}

	/**
	 * Ajoute des voitures dans le rond point.
	 * Si entree et sortie sont différents d'{@link VoieEnum.ALEAT}
	 * 
	 * @param nb_voitures
	 *            Le nombre de voiture à inserer
	 * @param entree
	 *            La voie d'insertion
	 * @param sortie
	 *            Le voie de sortie.
	 */
	public void ajout(int nb_voitures, VoieEnum entree, VoieEnum sortie) {

		VoieEnum e = entree;
		VoieEnum s = sortie;

		for (int i = 0; i < nb_voitures; i = i + 1) {
			if (entree == VoieEnum.ALEAT) {
				e = voieAleatoire();
			}
			if (sortie == VoieEnum.ALEAT) {
				s = voieAleatoire();
			}

			Object v = rp.ajouterVoiture(e, s);
			log(v + " entre sur " + e + " et va sortir en " + s);
		}
	}

	public static void log(String s) {
		synchronized (random) {
			System.out.println(s);
		}
	}

	public static void main(String argv[]) {
		new Simulateur(1).lancer();
	}
}