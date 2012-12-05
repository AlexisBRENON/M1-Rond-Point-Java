package aclt.genielog.rp;

import java.awt.event.ActionEvent;
import java.util.Random;

import javax.swing.UIManager;

import aclt.genielog.rp.ihm.SimulateurUI;
import aclt.genielog.rp.ihm.AjoutPanel.AjoutVoituresListener;
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
public class Simulateur implements AjoutVoituresListener {

	private static Simulateur instance;

	public static Simulateur singleton() {
		return singleton(1);
	}

	public static Simulateur singleton(int taille) {
		if (instance == null) {
			instance = new Simulateur(taille);
		}
		return instance;
	}

	private static final Random random = new Random(System.nanoTime());

	/**
	 * Le rond point du simulateur.
	 */
	private final RondPoint rp;

	private final SimulateurUI UI;

	private final Tour tour;

	private Simulateur(int taille) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		rp = new RondPoint(taille);
		tour = new Tour(this);
		UI = new SimulateurUI(this);
		rp.updateUI(UI);
	}

	/**
	 * Vide la file d'attente d'une voie
	 *
	 * @param voie
	 *            La voie concernée
	 */
	public static void viderFileDAttente(VoieEnum voie) {
		singleton().rp.viderFile(voie);
	}

	/**
	 * Déclenche le tour suivant.
	 */
	public static void tourSuivant() {
		singleton().rp.tourSuivant();
	}

	/**
	 * Déclenche le tour suivant.
	 */
	public static void tourSuivant(double percent) {
		singleton().rp.tourSuivant(percent);
	}

	/**
	 * Démarre la simulation.
	 */
	public static void lancer() {
		Simulateur simulateur = singleton();
		simulateur.UI.setVisible(true);
		simulateur.tour.start();

		Flux nord = new Flux(simulateur, VoieEnum.NORD);
		Flux est = new Flux(simulateur, VoieEnum.EST);
		Flux sud = new Flux(simulateur, VoieEnum.SUD);
		Flux ouest = new Flux(simulateur, VoieEnum.OUEST);
		simulateur.UI.setFluxListener(nord, est, sud, ouest);
		simulateur.UI.setVitesseListener(simulateur.tour);
		simulateur.UI.setAjoutVoituresListener(simulateur);

		nord.start();
		est.start();
		sud.start();
		ouest.start();

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
	 * @param count
	 *            Le nombre de voiture à inserer
	 * @param in
	 *            La voie d'insertion
	 * @param out
	 *            Le voie de sortie.
	 */

	@Override
	public void actionPerformed(ActionEvent evt, int count, VoieEnum in, VoieEnum out) {
		VoieEnum e = in;
		VoieEnum s = out;

		for (int i = 0; i < count; i = i + 1) {
			if (in == VoieEnum.ALEAT) {
				e = voieAleatoire();
			}
			if (out == VoieEnum.ALEAT) {
				s = voieAleatoire();
			}

			Object v = rp.ajouterVoiture(e, s);
			log(v + " entre sur " + e + " et va sortir en " + s);
		}
	}

	public static void log(String s) {
		synchronized (random) {
			singleton().UI.dispLog(s + "\n");
		}
	}

	public static void main(String argv[]) {
		Simulateur.singleton(1).lancer();
	}
}