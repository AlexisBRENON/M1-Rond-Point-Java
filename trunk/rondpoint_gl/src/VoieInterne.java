/**
 *
 * @author tiph
 */
public class VoieInterne extends Route {

    private VoieInterne _suivante;
    private VoieExterne _sortie;
    private Voiture[] _vehicules;

    public Voiture[] getVehicules() {
	return _vehicules;
    }

    
    public VoieInterne(int taille) {
	_vehicules = new Voiture[taille];
    }
    
    public VoieExterne getSortie(){
	return _sortie;
    }

    public void config(VoieInterne suivante, VoieExterne sortie) {
	_suivante = suivante;
	_sortie = sortie;
    }

    /**
     *
     * @return true si il y a une place pour s'inserer false sinon
     */
    public boolean LastSpot() {
	return _vehicules[0] == null;
    }

    /**
     * Indique si la voiture est arrivé au croisement pour sortir
     *
     * @param v
     * @return
     */
    public boolean Croisement(Voiture v) {
	return _vehicules[_vehicules.length] == v;
    }

    /**
     * La voiture rentre dans le rond point
     *
     * @param v
     */
    public void Sinserer(Voiture v) {
	assert (v.getEstSur() instanceof VoieExterne) : "la voiture n'est pas sur une voie externe";
	
	    _vehicules[0] = v;
	    ((VoieExterne) v.getEstSur()).Sortir(v);
	

    }

    /**
     * La voiture sort du rond point
     *
     * @param v
     */
    public void Sortir(Voiture v) {
	//Verifier quelle est bien en droit de sortir (arriver au croisement)
	if (Croisement(v)) {
	    _vehicules[_vehicules.length] = null;
	}
    }

    /**
     * La voiture continue sur la voie interne suivante
     *
     * @param v
     */
    public void Continuer(Voiture v) {
	//Verifier quelle est bien en droit de sortir (arriver au croisement)
	_suivante._vehicules[0] = v;
	_vehicules[_vehicules.length] = null;
    }

    /**
     * Avance la voiture d'un tour
     *
     * @param v
     */
    public void Avancer(Voiture v) {
	int i = 0;
	while(!_vehicules[i].equals(v) && i < _vehicules.length){
	    i++;
	}
	_vehicules[i] = null;
	_vehicules[i+1] = v;
    }
}
