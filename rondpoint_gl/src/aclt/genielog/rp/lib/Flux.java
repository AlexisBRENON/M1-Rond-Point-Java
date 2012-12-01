package aclt.genielog.rp.lib;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import aclt.genielog.rp.Simulateur;
import aclt.genielog.rp.system.VoieEnum;

/**
 * @author Alexis Brenon
 * @author Cécilia Martin
 * @author Luc Chante
 * @author Tiphaine Teyssier
 */
public class Flux extends PausableThread implements ChangeListener, ActionListener {

	private final VoieEnum maVoie;
	private final AtomicInteger frequence;

	public Flux(Simulateur simulateur, VoieEnum voie) {
		super(simulateur);
		this.maVoie = voie;
		frequence = new AtomicInteger(0);
	}

	@Override
	public void run() {
		long start, lapse;
		while (true) {
			synchronized (this) {
				while (frequence.get() < 1) {
					try {
						wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			start = System.nanoTime();
			getSimulateur().ajout(1, maVoie, VoieEnum.ALEAT);
			lapse = (long) ((1 / frequence.doubleValue()) * 1000);
			pause(start, lapse, TimeUnit.MILLISECONDS);
		}
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		JSpinner spinner = (JSpinner) e.getSource();
		frequence.set((Integer) spinner.getValue());
		synchronized (this) {
			notify();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		getSimulateur().viderFileDAttente(maVoie);
	}

}
