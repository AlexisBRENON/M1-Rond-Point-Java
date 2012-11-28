package aclt.genielog.rp;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * 
 * @author tiph
 */
public class Simulateur extends Thread {
	
	private final RondPoint rp;

	public Simulateur(int taille) {
		rp = new RondPoint(taille);
	}

	@Override
	public void run() {
		for (long tour = 1; true; tour = tour + 1) {
			System.out.println("Tour n°" + tour);
			rp.tourneInterne();
			rp.tourneExterne();
			pause(1, TimeUnit.SECONDS);
		}
	}

	/**
	 * Mets en pause pendant un temps donné.
	 * 
	 * @param duration Le nombre d'unité de temps à attendre.
	 * @param unit L'untité de temps.
	 */
	private void pause(long duration, TimeUnit unit) {
		long pause;
		pause = TimeUnit.NANOSECONDS.toMillis(System.nanoTime()) + unit.toMillis(duration);
		while (TimeUnit.NANOSECONDS.toMillis(System.nanoTime()) < pause) {
			try {
				sleep(pause - TimeUnit.NANOSECONDS.toMillis(System.nanoTime()));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String argv[]) {

		Simulateur sim = new Simulateur(2);
		ArrayList<Voiture> voitures = new ArrayList<Voiture>();
		for (int i = 0; i < 3; i = i + 1) {
			voitures.add(Voiture.factory(sim.rp, 0, 0));
		}

		sim.start();
	}
}
