package aclt.genielog.rp;

/**
 * 
 * @author tiph
 */
class Voiture {

	private static int ID = 0;

	public static Voiture factory(RondPoint rp, int depart, int destination) {
		Voiture v = rp.ajouterVoiture(depart, destination);
		System.out.println(v + " entre sur " + depart + " et va sortir en " + destination);
		return v;
	}

	public void setEstSur(Voie estSur) {
		this.estSur = estSur;
	}

	private VoieExterne destination;
	private Voie estSur;

	private String name;

	public Voiture(VoieExterne dep, VoieExterne dest) {
		estSur = dep;
		destination = dest;
		name = "Voiture#" + (ID++);
	}

	public Voie getEstSur() {
		return estSur;
	}

	public VoieExterne getDestination() {
		return destination;
	}

	public void Avancer() {
		if (estSur instanceof VoieExterne) {
			if (((VoieExterne) estSur).isFirst(this)) {
				if (((VoieExterne) estSur).GetVoieInterne().LastSpot()) {
					((VoieExterne) estSur).GetVoieInterne().Sinserer(this);
					estSur = ((VoieExterne) estSur).GetVoieInterne();
					System.out.println(this + " s'insère sur " + estSur);
				}
			}
		} else if (estSur instanceof VoieInterne) {
			VoieInterne est = (VoieInterne) estSur;
			if (est.Croisement(this)) {
				if (destination == est.getSortie()) {
					Sortir();
					System.out.println(this + " sort sur " + estSur);
				} else {
					estSur = est.Continuer(this);
					System.out.println(this + " continue sur " + estSur);
				}
			} else {
				est.Avancer(this);
			}
		}
	}

	public void Sortir() {
		((VoieInterne) estSur).Sortir(this);
		estSur = destination;
	}

	@Override
	public String toString() {
		return name;
	}
}
