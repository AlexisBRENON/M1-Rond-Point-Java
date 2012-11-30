package aclt.genielog.rp.lib;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import aclt.genielog.rp.Simulateur;

public class Tour extends PausableThread implements ActionListener, ItemListener,
		ChangeListener {

	private AtomicBoolean paused = new AtomicBoolean(true);
	private int duration = 1;
	private TimeUnit unit = TimeUnit.SECONDS;

	public Tour(Simulateur simulateur) {
		super(simulateur);
	}

	@Override
	public void run() {
		long start;

		for (long tour = 1; true; tour = tour + 1) {

			if (paused.get()) {
				synchronized (this) {
					while (paused.get()) {
						try {
							wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}

			start = System.nanoTime();
			Simulateur.log("Tour n°" + tour);
			getSimulateur().tourSuivant();

			pause(start, duration, unit);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (paused.getAndSet(true)) {
			paused.getAndSet(false);
		}
		synchronized (this) {
			((JButton) e.getSource()).setText(paused.get() ? "Lecture" : "Pause");
			notify();
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		unit = (TimeUnit) e.getItem();
		synchronized (this) {
			this.interrupt();
		}
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		duration = (Integer) ((JSpinner) e.getSource()).getValue();
		synchronized (this) {
			this.interrupt();
		}
	}
}
