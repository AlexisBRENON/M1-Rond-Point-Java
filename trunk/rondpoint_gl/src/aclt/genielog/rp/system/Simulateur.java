package aclt.genielog.rp.system;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author Alexis Brenon
 * @author Cécilia Martin
 * @author Luc Chante
 * @author Tiphaine Teyssier
 */
public class Simulateur extends Thread {

	/**
	 * Le rond point du simulateur.
	 */
	private final RondPoint rp;

	/**
	 * La configuration du simulateur.
	 */
	private final Config config = new Config();

	public Simulateur(int taille) {
		rp = new RondPoint(taille);
	}

	/**
	 * Modifie la configuration actuelle du simulateur.
	 *
	 * @param duration Le nombre d'unité de temps pour la durée de la pause
	 * 			entre deux tour.
	 * @param unit L'unité de temps pour la pause entre deux tour.
	 */
	public void setConfig(long duration, TimeUnit unit) {
		config.duration = duration;
		config.unit = unit;
	}

	@Override
	public void run() {
		long start;
		for (long tour = 1; true; tour = tour + 1) {
			start = System.nanoTime();
			System.out.println("Tour n°" + tour);
			rp.tourneInterne();
			rp.tourneExterne();
			pause(start, config.duration, config.unit);
		}
	}

	/**
	 * Mets en pause pendant le temp défini dans la configuration depuis le
	 * temps donnée.
	 *
	 * @param start
	 *            La date en nanosecondes à laquelle a commencé le tour.
	 * @param unit
	 *            L'untité de temps.
	 */
	private void pause(long start, long duration, TimeUnit unit) {
		long pause;
		pause = start + unit.toNanos(duration);
		while (System.nanoTime() < pause) {
			try {
				sleep(TimeUnit.NANOSECONDS.toMillis(pause - System.nanoTime()));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Choisi une voie (Nord, Sud, Est, Ouest) aléatoirement.
	 *
	 * @return Une voie choisie aléatoirement.
	 */
	private static VoieEnum voieAleatoire() {
		Random r = new Random();
		VoieEnum v=VoieEnum.NORD;
		switch (r.nextInt(4)) {
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
	 * @param nb_voitures Le nombre de voiture à inserer
	 * @param entree La voie d'insertion
	 * @param sortie Le voie de sortie.
	 */
	public synchronized void ajout(int nb_voitures, VoieEnum entree, VoieEnum sortie) {

		for (int i = 0; i < nb_voitures; i = i + 1) {
			if (entree == VoieEnum.ALEAT) {
				entree = voieAleatoire();
			}
			if (sortie == VoieEnum.ALEAT) {
				sortie = voieAleatoire();
			}

			Voiture.factory(rp, entree, sortie);
		}
	}

	public static void main(String argv[]) {

		Simulateur sim = new Simulateur(1);
		sim.start();
		sim.ajout(5, VoieEnum.ALEAT, VoieEnum.ALEAT);
	}

	/**
	 * Classe de configuration du simulateur.
	 */
	private class Config {
		long duration = 1;
		TimeUnit unit = TimeUnit.SECONDS;
	}
}