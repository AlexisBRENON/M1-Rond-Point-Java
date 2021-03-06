package aclt.genielog.rp.system;

import javax.swing.JComponent;

/**
 * Classe abstraite Voie.
 *
 * Une voie est une partie du rond sur laquelle les voitures se déplacent.
 *
 * @author Alexis Brenon
 * @author Cécilia Martin
 * @author Luc Chante
 * @author Tiphaine Teyssier
 */
abstract class Voie extends JComponent implements Circulable {

	/**
	 * Le nom de la voie (utile pour les logs de fonctionnement).
	 */
	private String name;

	Voie(String name) {
		this.name = name;
	}

	/**
	 * Prépare l'affichage concernant l'animation entre le tour précédent et le tour
	 * suivnat.
	 *
	 * @param percent
	 *            Pourcentage d'avancement de l'animation.
	 */
	abstract void prePaint(double percent);

	/**
	 * Insère une voiture sur la voie
	 *
	 * @param v
	 *            La voiture a insérer
	 */
	abstract void quitter(Voiture v);

	/**
	 * Retire une voiture de la voie
	 *
	 * @param v
	 *            La voiture a retirer
	 */
	abstract void entrer(Voiture v);

	/**
	 * Vide complètement la voie.
	 *
	 * @return Le nombre de voitures supprimées
	 */
	abstract int vider();

	@Override
	public String toString() {
		return "Voie " + name;
	}
}