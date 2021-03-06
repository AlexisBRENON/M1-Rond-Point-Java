package aclt.genielog.rp.lib;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import aclt.genielog.rp.Simulateur;
import aclt.genielog.rp.ihm.FluxPanel.ViderVoieListener;
import aclt.genielog.rp.system.VoieEnum;

/**
 * @author Alexis Brenon
 * @author Cécilia Martin
 * @author Luc Chante
 * @author Tiphaine Teyssier
 */
public class Flux extends PausableThread implements ActionListener, ChangeListener,
		ViderVoieListener {

	private final VoieEnum maVoie;
	private AtomicInteger frequence;

	public Flux(Simulateur simulateur, VoieEnum voie) {
		super(simulateur);
		this.maVoie = voie;
		frequence = new AtomicInteger(0);
	}

	@Override
	public void execute() {
		long next;

		synchronized (this) {
			while (frequence.addAndGet(0) <= 0) {
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		next = System.currentTimeMillis() + 1000 / frequence.get();
		getSimulateur().actionPerformed(null, 1, maVoie, VoieEnum.ALEAT);

		asleep(next - System.currentTimeMillis());
	}

	/**
	 * Modification de la fréquence d'insertion des voitures.
	 */
	@Override
	public void stateChanged(ChangeEvent e) {
		JSpinner spinner = (JSpinner) e.getSource();
		frequence.getAndSet((Integer) spinner.getValue());
		if (0 < frequence.addAndGet(0)) {
			synchronized (this) {
				notify();
				unpause();
			}
		}
	}

	/**
	 * Action déclenchée quand on appuie sur "Lecture/Pause".
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		togglePause();
	}

	/**
	 * Action déclenchée quand on appuie sur le bouton "Vider"
	 */
	@Override
	public void vidagePerformed(ActionEvent e) {
		Simulateur.viderFileDAttente(maVoie);
		synchronized (this) {
				notifyAll();
				unpause();
			}
	}

}
