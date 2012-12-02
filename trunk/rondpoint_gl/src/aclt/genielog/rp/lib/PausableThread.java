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

	protected abstract void execute();

	@Override
	public final void run() {
		while (true) {
			synchronized (this) {
				while (paused.get()) {
					try {
						wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			execute();
		}
	}

	public final void pause() {
		if (paused.compareAndSet(false, true)) {
			synchronized (this) {
				notify();
			}
		}
	}

	public final void unpause() {
		if (paused.compareAndSet(true, false)) {
			synchronized (this) {
				notify();
			}
		}
	}

	public final boolean togglePause() {
		if (paused.getAndSet(true)) {
			paused.getAndSet(false);
		}
		synchronized (this) {
			notify();
		}
		return paused.get();
	}

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
