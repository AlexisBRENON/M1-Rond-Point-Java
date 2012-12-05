package aclt.genielog.rp.system;

import java.util.Collection;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Class des statistiques du rond-point.
 *
 * @author Alexis Brenon
 * @author Cécilia Martin
 * @author Luc Chante
 * @author Tiphaine Teyssier
 */
public class Stats implements Observer {

	private final HashMap<VoieEnum, AtomicInteger> voituresEnAttentes;
	private final HashMap<VoieEnum, AtomicLong> attenteParVoie;
	private final HashMap<VoieEnum, AtomicInteger> voituresEngageesParVoies;
	private final HashMap<VoieEnum, AtomicInteger> voituresEntrees;
	private final HashMap<VoieEnum, AtomicInteger> voituresSorties;
	private final AtomicInteger voituresEngagees;

	Stats(Collection<VoieEnum> voies) {
		voituresEnAttentes = new HashMap<VoieEnum, AtomicInteger>();
		attenteParVoie = new HashMap<VoieEnum, AtomicLong>();
		voituresEngageesParVoies = new HashMap<VoieEnum, AtomicInteger>();
		voituresEntrees = new HashMap<VoieEnum, AtomicInteger>();
		voituresSorties = new HashMap<VoieEnum, AtomicInteger>();
		voituresEngagees = new AtomicInteger(0);

		for (VoieEnum voie : voies) {
			voituresEnAttentes.put(voie, new AtomicInteger(0));
			attenteParVoie.put(voie, new AtomicLong(0));
			voituresEngageesParVoies.put(voie, new AtomicInteger(0));
			voituresEntrees.put(voie, new AtomicInteger(0));
			voituresSorties.put(voie, new AtomicInteger(0));
		}
	}

	/**
	 * Retourne le nmbre total de voitures actuellement en attente sur les voies
	 * externes.
	 *
	 * @return Le nombre total de voiture en attente.
	 */
	public int voituresEnAttente() {
		return sommeInt(voituresEnAttentes);
	}

	/**
	 * Retourne l'attente moyenne avant de rentrer dans le rond-point (toutes voies
	 * d'origines confondues).
	 *
	 * @return Le temp d'attente moyen.
	 */
	public double attenteMoyenne() {
		return sommeLong(attenteParVoie)
				/ (double) sommeInt(voituresEngageesParVoies);
	}

	/**
	 * Retourne le nombre total de voitures engagées sur les voies externes. Cette
	 * valeur inclus les voitures actullement en attente, celles engagées et celles
	 * déjà sorites. Mais le total n'est calculé à partir des ces valeurs, il est
	 * mis à jour à chaque ajout de nouvelle voiture.
	 *
	 * @return Le nombre de voitures engagées sur les voies externes.
	 */
	public int voituresEntrees() {
		return sommeInt(voituresEntrees);
	}

	/**
	 * Retourne le nombre total de voitures ayant atteint leur voie de sortie et qui
	 * sont sorties du rond-point.
	 *
	 * @return Le nombre de voiture qui ont quittées le rond-point.
	 */
	public int voituresSorties() {
		return sommeInt(voituresSorties);
	}

	/**
	 * Retourne le nombre de voitures actuellement dans le rond-point.
	 *
	 * @return Retourne le nombre de voitures actuellement dans le rond-point.
	 */
	public int voituresEngagees() {
		return voituresEngagees.get();
	}

	/**
	 * Retourne le nombre de voitures en attente sur une voie donnée.
	 *
	 * @param voie
	 *            La voie pour laquelle on veut connaitre le nombre.
	 * @return Retourne le nombre de voitures en attente sur une voie.
	 */
	public int voituresEnAttente(VoieEnum voie) {
		return voituresEnAttentes.get(voie).get();
	}

	/**
	 * Retourne le temps d'attente moyen pour une voie donnée.
	 *
	 * @param voie
	 *            La voie pour laquelle on veut connaitre le nombre.
	 * @return Retourne le temps d'attente moyen pour une voie donnée.
	 */
	public double attenteMoyenne(VoieEnum voie) {
		return (double) attenteParVoie.get(voie).get()
				/ (double) voituresEngageesParVoies.get(voie).get();
	}

	/**
	 * Retourne le nombre de voitures qui sont entrées par cette voie dans le rond-
	 * point. Soit les voitures en attente sur la voie plus celles qui se sont
	 * engagées dans le rond-point par cette voie.
	 *
	 * @param voie
	 *            La voie pour laquelle on veut connaitre le nombre.
	 * @return Retourne le nombre de voitures qui sont entrées par cette voie.
	 */
	public int voituresEntrees(VoieEnum voie) {
		return voituresEntrees.get(voie).get();
	}

	/**
	 * Retourne le nombre de voitures qui ont quittées le rond-point par cette voie.
	 *
	 * @param voie
	 *            La voie pour laquelle on veut connaitre le nombre.
	 * @return Retourne le nombre de voitures qui ont quittées le rond-point par
	 *         cette voie.
	 */
	public int voituresSorties(VoieEnum voie) {
		return voituresSorties.get(voie).get();
	}

	/**
	 * Corrige les stats par rapport à un vidage des voie.
	 *
	 * @param voie
	 *            La voie vidée
	 * @param taille
	 */
	void vidageVoie(VoieEnum voie, int delta) {
		voituresEnAttentes.get(voie).addAndGet(-delta);
	}

	void reset() {
		Set<VoieEnum> voies = voituresEnAttentes.keySet();
		for (VoieEnum voie : voies) {
			voituresEnAttentes.put(voie, new AtomicInteger(0));
			attenteParVoie.put(voie, new AtomicLong(0));
			voituresEngageesParVoies.put(voie, new AtomicInteger(0));
			voituresEntrees.put(voie, new AtomicInteger(0));
			voituresSorties.put(voie, new AtomicInteger(0));
		}
		voituresEngagees.set(0);
	}

	@Override
	public void update(Observable o, Object arg) {
		Route route = (Route) arg;
		if (route.to == null) {
			VoieEnum ve = (VoieEnum) ((VoieExterne)route.from).getEnum();
			voituresEnAttentes.get(ve).incrementAndGet();
			voituresEntrees.get(ve).incrementAndGet();
		}
		else if (route.from instanceof VoieExterne
				&& route.to instanceof VoieInterne) {
			VoieEnum ve = (VoieEnum) ((VoieExterne)route.from).getEnum();
			voituresEnAttentes.get(ve).decrementAndGet();
			voituresEngagees.incrementAndGet();
			attenteParVoie.get(ve).addAndGet(route.toAt - route.fromAt);
			voituresEngageesParVoies.get(ve).incrementAndGet();
		}
		else if (route.from instanceof VoieInterne
				&& route.to instanceof VoieExterne) {
			VoieEnum ve = (VoieEnum) ((VoieExterne)route.to).getEnum();
			voituresSorties.get(ve).incrementAndGet();
			voituresEngagees.decrementAndGet();
		}
	}

	/**
	 * Somme toutes les valeurs d'un HashMap.
	 *
	 * @param map
	 *            Le HashMap qui contient les valeurs.
	 * @return La somme des valeurs.
	 */
	private static int sommeInt(HashMap<VoieEnum, AtomicInteger> map) {
		int somme = 0;
		for (AtomicInteger value : map.values()) {
			somme = somme + value.get();
		}
		return somme;
	}

	/**
	 * Somme toutes les valeurs d'un HashMap.
	 *
	 * @param map
	 *            Le HashMap qui contient les valeurs.
	 * @return La somme des valeurs.
	 */
	private static long sommeLong(HashMap<VoieEnum, AtomicLong> map) {
		long somme = 0;
		for (AtomicLong value : map.values()) {
			somme = somme + value.get();
		}
		return somme;
	}

	/**
	 * Classe qui servent à générer les statistiques. C'est l'object passé en
	 * argument à l'Observer Stats, par le biais des Observable Voiture.
	 */
	static class Route {
		private Voie from;
		private long fromAt = 0;
		private Voie to;
		private long toAt = 0;

		Route(Voie from) {
			this.from = from;
			fromAt = System.currentTimeMillis();
		}

		boolean setTo(Voie to) {
			if (to.equals(this.to)) {
				return false;
			}
			if (this.to != null) {
				this.from = this.to;
				this.fromAt = this.toAt;
			}
			this.to = to;
			toAt = System.currentTimeMillis();
			return true;
		}

		public long getFromTime() {
			return fromAt;
		}

		public long getToTime() {
			return toAt;
		}
	}
}