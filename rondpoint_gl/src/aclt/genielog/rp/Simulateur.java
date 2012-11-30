package aclt.genielog.rp;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import aclt.genielog.rp.system.RondPoint;
import aclt.genielog.rp.system.Stats;
import aclt.genielog.rp.system.VoieEnum;

/**
 * @author Alexis Brenon
 * @author Cécilia Martin
 * @author Luc Chante
 * @author Tiphaine Teyssier
 */
public class Simulateur extends Thread {

	private static final Random random = new Random(System.nanoTime());

	/**
	 * Le rond point du simulateur.
	 */
	private final RondPoint rp;

	/**
	 * La configuration du simulateur.
	 */
	private final Config config;

	public Simulateur(int taille) {
		rp = new RondPoint(taille);
		rp.attachStats();
		config = new Config(Arrays.asList(VoieEnum.values()));
	}

	/**
	 * Modifie la configuration actuelle du simulateur.
	 * 
	 * @param duration
	 *            Le nombre d'unité de temps pour la durée de la pause
	 *            entre deux tour.
	 * @param unit
	 *            L'unité de temps pour la pause entre deux tour.
	 */
	public void setConfig(long duration, TimeUnit unit) {
		config.duration = duration;
		config.unit = unit;
	}

	public void setConfig(VoieEnum voie, int flux) {
		synchronized (config) {
			config.flux.put(voie, flux);
			config.notifyAll();
		}
	}

	@Override
	public void run() {
		long start;

		final Thread parent = Thread.currentThread();
		Set<VoieEnum> voies = config.flux.keySet();
		for (VoieEnum v : voies) {
			final VoieEnum voie = v;
			new Thread() {
				@Override
				public void run() {
					while (!parent.isInterrupted()) {
						synchronized (config) {
							while (config.flux.get(voie) < 1) {
								try {
									config.wait();
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
						}
						long start = System.nanoTime();
						ajout(1, voie, VoieEnum.ALEAT);
						long lapse = (long) ((1 / (double) config.flux.get(voie)) * 1000);
						pause(start, lapse, TimeUnit.MILLISECONDS);
					}
				}
			}.start();
		}

		for (long tour = 1; true; tour = tour + 1) {
			start = System.nanoTime();
			Simulateur.log("Tour n°" + tour);
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

		Simulateur sim = new Simulateur(1);
		sim.start();
		final Stats stats = sim.rp.getStats();

		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					synchronized (random) {
						System.out.println("Voitures en attente: "
								+ stats.voituresEnAttente());
						System.out.println("Attente moyenne: "
								+ (stats.attenteMoyenne() / 1000) + "s");
						System.out.println("Voitures engagées: "
								+ stats.voituresEngagees());
						System.out.println("Voitures entrées: "
								+ stats.voituresEntrees());
						System.out.println("Voitures sorties: "
								+ stats.voituresSorties());
					}
					try {
						sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();

		sim.setConfig(VoieEnum.NORD, 2);

	}

	/**
	 * Classe de configuration du simulateur.
	 */
	private class Config {
		long duration = 1;
		TimeUnit unit = TimeUnit.SECONDS;
		final HashMap<VoieEnum, Integer> flux;

		public Config(Collection<VoieEnum> voies) {
			flux = new HashMap<VoieEnum, Integer>();
			for (VoieEnum voie : voies) {
				if (VoieEnum.ALEAT.compareTo(voie) != 0) {
					flux.put(voie, 0);
				}
			}
		}
	}
}