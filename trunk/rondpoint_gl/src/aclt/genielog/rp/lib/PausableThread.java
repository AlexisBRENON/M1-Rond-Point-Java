package aclt.genielog.rp.lib;

import java.util.concurrent.atomic.AtomicBoolean;

import aclt.genielog.rp.Simulateur;

public abstract class PausableThread extends Thread {

	private final Simulateur simulateur;
	private final AtomicBoolean paused = new AtomicBoolean(true);

	public PausableThread(Simulateur simulateur) {
		this.simulateur = simulateur;
	}

	protected Simulateur getSimulateur() {
		return simulateur;
	}

	/**
	 * Méthode à implémenter à la place de {@link Thread.run}.
	 */
	protected abstract void execute();

	/**
	 * Execute le Thread de manière continue.
	 */
	@Override
	public final void run() {
		while (true) {
			idle();
			execute();
		}
	}

	/**
	 * Met le Thread en pause.
	 * 
	 * @link Thread.wait
	 * @link Thread.notify
	 */
	protected final synchronized void idle() {
		while (paused.get()) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Test si le Thread est en pause.
	 * 
	 * @return true si le Thread est en pause, false sinon.
	 */
	public final boolean paused() {
		return paused.compareAndSet(true, true);
	}

	/**
	 * Met en pause le Thread.
	 */
	public final void pause() {
		if (paused.compareAndSet(false, true)) {
			synchronized (this) {
				notify();
			}
		}
	}

	/**
	 * Relance le Thread.
	 */
	public final void unpause() {
		if (paused.compareAndSet(true, false)) {
			synchronized (this) {
				notify();
			}
		}
	}

	/**
	 * Inverse l'état de pause du Thread.
	 * 
	 * @return true si le Thread passe en pause, false sinon.
	 */
	public final boolean togglePause() {
		if (paused.getAndSet(true)) {
			paused.getAndSet(false);
		}
		synchronized (this) {
			notify();
		}
		return paused.get();
	}

	/**
	 * Met le Thread en attente pendant un temps donné.
	 * 
	 * @param milliseconds
	 *            Le nombre milliseconds d'attente.
	 */
	public void asleep(long milliseconds) {
		long next = System.currentTimeMillis() + milliseconds;
		while (System.currentTimeMillis() < next) {
			try {
				sleep(next - System.currentTimeMillis());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
