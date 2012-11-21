package aclt.genielog.rp;

import java.util.ArrayList;

/**
 * 
 * @author tiph
 */
public class Simulateur {

	public static void println(Object o) {
		System.out.println(o);
	}

	public static void println(int i) {
		System.out.println(i);
	}

	private final RondPoint rp;

	public Simulateur(int taille) {
		rp = new RondPoint(taille);
	}

	public void FaireUnTour() {
		Voiture first = rp.getVoieInternes()[0].getVehicules()[0];
		rp.getVoieInternes()[0].getVehicules()[0] = null;
		VoieInterne vi;
		Voiture v;

		for (int i = rp.getVoieInternes().length - 1; i >= 0; i--) {
			vi = rp.getVoieInternes()[i];
			for (int j = vi.getVehicules().length - 1; j >= 0; j--) {
				v = vi.getVehicules()[j];
				if (v != null) {
					v.Avancer();
				}
			}
		}

		if (first != null) {
			// first.Avancer(); // Päs bon car le vehicule peux etre ecraser
			// donc Avancer ne va pas le trouver et bug
			if (((VoieInterne) first.getEstSur()).getTaille() == 1) {
				VoieInterne est = (VoieInterne) first.getEstSur();
				if (first.getDestination() == est.getSortie()) {
					first.Sortir();
				} else {
					est.Continuer(first);
				}
			} else {
				((VoieInterne) first.getEstSur()).getVehicules()[1] = first;
			}
		}

		for (VoieExterne ve : rp.getVoieExternes()) {
			if (!ve.getVoitures().isEmpty()) {
				ve.getVoitures().getFirst().Avancer();
			}
		}
	}

	public static void main(String argv[]) {

		Simulateur sim = new Simulateur(1);
		ArrayList<Voiture> voitures = new ArrayList<Voiture>();
		for (int i = 0; i < 10; i = i + 1) {
			voitures.add(Voiture.factory(sim.rp, 0, 0));
		}

		for (int i = 0; i < 10; i = i + 1) {
			System.out.println(voitures);
			sim.FaireUnTour();
		}
	}
}
