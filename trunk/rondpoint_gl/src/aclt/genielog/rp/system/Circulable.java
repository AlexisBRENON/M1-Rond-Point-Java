package aclt.genielog.rp.system;

public interface Circulable {
	/**
	 * Gére la circulation sur la voie pour un tour
	 *
	 * @return La voiture qui sort de cette voie pour la voie suivante.
	 */
	Voiture circule();
}
