package aclt.genielog.rp;
/**
 *
 * @author tiph
 */
class Voiture {

	public static Voiture factory(RondPoint rp, int depart, int destination) {
		Voiture v = new Voiture(rp.getVoieExternes()[depart], rp.getVoieExternes()[destination]);
		rp.getVoieExternes()[depart].Rentrer(v);
		return v;
	}

	private VoieExterne destination;
	private Voie estSur;

	public Voiture(VoieExterne dep, VoieExterne dest) {
		estSur = dep;
		destination = dest;
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
				}

			}
		}
		else if(estSur instanceof VoieInterne){
			VoieInterne est = (VoieInterne)estSur;
			estSur = est;
			if(est.Croisement(this)){
				if(destination == est.getSortie()){
					Sortir();
				}else{
					est.Continuer(this);
				}
			}else{
				est.Avancer(this);
			}
		}
	}

	public void Sortir() {
		((VoieInterne)estSur).Sortir(this);
		estSur = destination;
	}

	@Override
	public String toString() {
		return estSur + " -> " + destination;
	}
}
