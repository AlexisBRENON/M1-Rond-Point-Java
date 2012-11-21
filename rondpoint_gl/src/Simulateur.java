/**
 *
 * @author tiph
 */
public class Simulateur {

    private RondPoint rp;

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
	    //first.Avancer(); // Päs bon car le vehicule peux etre ecraser donc Avancer ne va pas le trouver et bug
	    ((VoieInterne)first.getEstSur()).getVehicules()[1] = first;
	}
	for (VoieExterne ve : rp.getVoieExternes()) {
	    ve.getVoitures().getFirst().Avancer();
	}
    }
}
