
/**
 * Le Systeme
 * @author tiph
 */
public class RondPoint {
    private VoieExterne[] _voie_externes;
    private VoieInterne[] _voie_internes;

    public VoieExterne[] getVoieExternes() {
	return _voie_externes;
    }

    public VoieInterne[] getVoieInternes() {
	return _voie_internes;
    }
    
    /**
     * Crée un rond point à 4 voie
     * @param taille la taille entre les voies
     */
    public RondPoint(int taille){
	_voie_externes = new VoieExterne[4];
	_voie_internes = new VoieInterne[4];
	for(int i=0; i<4; i++){
	    _voie_internes[i] = new VoieInterne(taille);
	    _voie_externes[i] = new VoieExterne(_voie_internes[i]);
	}
	for(int i=0; i<4;i++){
	    if((i+1) < 3){
		_voie_internes[i].config(_voie_internes[i+1], _voie_externes[i+1]);
	    }
	    else{
		_voie_internes[i].config(_voie_internes[0], _voie_externes[0]);
	    }
	    
	}
    }
    
    
    
}
