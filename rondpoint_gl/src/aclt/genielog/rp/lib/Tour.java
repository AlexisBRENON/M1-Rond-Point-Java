package aclt.genielog.rp.lib;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import aclt.genielog.rp.Simulateur;

public class Tour extends PausableThread implements ActionListener, ChangeListener {

	private long frequence = 60;
	private int tour = 0;

	public Tour(Simulateur simulateur) {
		super(simulateur);
	}

	@Override
	public void execute() {
		int step = 100;
		long next = System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(1) / frequence;
		long latence = TimeUnit.MINUTES.toMillis(1) / frequence / step;
		tour = tour + 1;
		Simulateur.log("Tour n°" + tour);

		for (int i = 0; i < step; i = i + 1) {
			idle();
			Simulateur.tourSuivant((double)i / (double)step);
			asleep(latence);
		}
		Simulateur.tourSuivant();

		asleep(next - System.currentTimeMillis());
	}

	/**
	 * Action déclenchée quand on appuie sur "Lecture/Pause".
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		togglePause();
	}

	/**
	 * Modification de la vitesse de la simulation.
	 */
	@Override
	public void stateChanged(ChangeEvent e) {
		frequence = (Integer) ((JSpinner) e.getSource()).getValue();
	}
}
