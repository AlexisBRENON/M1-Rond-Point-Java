package aclt.genielog.rp.lib;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import aclt.genielog.rp.Simulateur;

public class Tour extends PausableThread implements ActionListener, ChangeListener {

	private long frequence = 1;
	private int tour = 0;

	public Tour(Simulateur simulateur) {
		super(simulateur);
	}

	@Override
	public void execute() {
		long next;
		double step = frequence / 100.0;

		next = System.currentTimeMillis() + 1000 / frequence;
		tour = tour + 1;
		Simulateur.log("Tour n°" + tour);

		/*for (double i = 0.0; i < 1.0; i = i + step) {
			idle();
			getSimulateur().tourSuivant(i);
			asleep(Math.round(step * 1000));
		}*/
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
