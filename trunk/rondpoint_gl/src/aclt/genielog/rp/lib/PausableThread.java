package aclt.genielog.rp.lib;

import java.util.concurrent.TimeUnit;

import aclt.genielog.rp.Simulateur;

public class PausableThread extends Thread {

	private final Simulateur simulateur;

	public PausableThread(Simulateur simulateur) {
		this.simulateur = simulateur;
	}

	protected Simulateur getSimulateur() {
		return simulateur;
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
	protected void pause(long start, long duration, TimeUnit unit) {
		long pause;
		pause = start + unit.toNanos(duration);
		while (System.nanoTime() < pause) {
			try {
				sleep(TimeUnit.NANOSECONDS.toMillis(pause - System.nanoTime()));
			} catch (InterruptedException e) {
			}
		}
	}

}
