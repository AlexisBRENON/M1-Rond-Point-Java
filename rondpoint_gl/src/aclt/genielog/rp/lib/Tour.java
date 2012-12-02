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

		next = System.currentTimeMillis() + 1000 / frequence;
		tour = tour + 1;
		Simulateur.log("Tour n°" + tour);
		getSimulateur().tourSuivant();

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
