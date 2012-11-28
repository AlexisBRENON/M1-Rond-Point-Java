package aclt.genielog.rp;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 
 * @author tiph
 */
public class Simulateur extends Thread {

	private final RondPoint rp;

	public Simulateur(int taille) {
		rp = new RondPoint(taille);
	}

	@Override
	public void run() {
		for (long tour = 1; true; tour = tour + 1) {
			System.out.println("Tour n°" + tour);
			rp.tourneInterne();
			rp.tourneExterne();
			pause(1, TimeUnit.SECONDS);
		}
	}

	/**
	 * Mets en pause pendant un temps donné.
	 * 
	 * @param duration
	 *            Le nombre d'unité de temps à attendre.
	 * @param unit
	 *            L'untité de temps.
	 */
	private void pause(long duration, TimeUnit unit) {
		long pause;
		pause = TimeUnit.NANOSECONDS.toMillis(System.nanoTime())
				+ unit.toMillis(duration);
		while (TimeUnit.NANOSECONDS.toMillis(System.nanoTime()) < pause) {
			try {
				sleep(pause - TimeUnit.NANOSECONDS.toMillis(System.nanoTime()));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public VoieEnum voieAleatoire() {
		Random r = new Random();
		VoieEnum v=VoieEnum.NORD;
		switch (r.nextInt(4)) {
		case 0:
			v = VoieEnum.NORD;
			break;
		case 1:
			v = VoieEnum.EST;
			break;
		case 2:
			v = VoieEnum.SUD;
			break;
		case 3:
			v = VoieEnum.OUEST;
			break;
		}
		return v;
		

	}

	public void ajout(int n, VoieEnum entree, VoieEnum sortie) {
		int entreeInt, sortieInt, i;

		if(entree == VoieEnum.ALEAT && sortie == VoieEnum.ALEAT){
			for(i=0; i<n ; i++){
				entree = voieAleatoire();
				sortie = voieAleatoire();
				entreeInt = entree.ordinal();
				sortieInt = sortie.ordinal();
				Voiture.factory(rp, entreeInt, sortieInt);
			}
		}
		
		else if (entree == VoieEnum.ALEAT) {
			sortieInt = sortie.ordinal();
			for(i=0; i<n ; i++){
				entree = voieAleatoire();
				entreeInt = entree.ordinal();
				Voiture.factory(rp, entreeInt, sortieInt);
			}		
		}

		else if (sortie == VoieEnum.ALEAT) {
			entreeInt = entree.ordinal();
			for(i=0; i<n ; i++){
				sortie = voieAleatoire();
				sortieInt = sortie.ordinal();
				Voiture.factory(rp, entreeInt, sortieInt);
			}
		}
		
		else{
			entreeInt = entree.ordinal();
			sortieInt = sortie.ordinal();
			for (i = 0; i < n; i++) {
				Voiture.factory(rp, entreeInt, sortieInt);
			}
		}
	}

	public static void main(String argv[]) {

		Simulateur sim = new Simulateur(2);
		//ArrayList<Voiture> voitures = new ArrayList<Voiture>();
		// for (int i = 0; i < 3; i = i + 1) {
		// voitures.add(Voiture.factory(sim.rp, 0, 0));
		// }

		sim.start();
		sim.ajout(5, VoieEnum.ALEAT, VoieEnum.ALEAT);
	}
}
