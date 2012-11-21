/**
 *
 * @author tiph
 */
public class Voiture {

    private VoieExterne _destination;
    private Route _estsur;

    public Voiture(VoieExterne depart, VoieExterne destination) {
	_estsur = depart;
	_destination = destination;
    }

    public Route getEstSur() {
	return _estsur;
    }

    public void Avancer() {
	if (_estsur instanceof VoieExterne) {
	    if (((VoieExterne) _estsur).isFirst(this)) {
		if (((VoieExterne) _estsur).GetVoieInterne().LastSpot()) {
		    ((VoieExterne) _estsur).GetVoieInterne().Sinserer(this);
		}

	    }
	}
	else if(_estsur instanceof VoieInterne){
	    VoieInterne est = (VoieInterne)_estsur;
	    if(est.Croisement(this)){
		if(_destination == est.getSortie()){
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
	((VoieInterne)_estsur).Sortir(this);
    }
}
