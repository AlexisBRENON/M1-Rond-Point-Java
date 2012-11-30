package aclt.genielog.rp.lib;

import java.util.concurrent.TimeUnit;

import aclt.genielog.rp.Simulateur;
import aclt.genielog.rp.system.VoieEnum;

/**
 * @author Alexis Brenon
 * @author Cécilia Martin
 * @author Luc Chante
 * @author Tiphaine Teyssier
 */
public class Flux extends PausableThread {

	private final Simulateur simulateur;
	private final VoieEnum maVoie;

	public Flux(Simulateur simulateur, VoieEnum voie) {
		this.simulateur = simulateur;
		this.maVoie = voie;
	}

	@Override
	public void run() {
		int hz;
		long start, lapse;
		while (!simulateur.isInterrupted()) {
			synchronized (maVoie) {
				hz = simulateur.getConfig(maVoie);
				while (hz < 1) {
					try {
						wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					hz = simulateur.getConfig(maVoie);
				}
			}
			start = System.nanoTime();
			simulateur.ajout(1, maVoie, VoieEnum.ALEAT);
			lapse = (long) ((1 / (double) hz) * 1000);
			pause(start, lapse, TimeUnit.MILLISECONDS);
		}
	}

}
