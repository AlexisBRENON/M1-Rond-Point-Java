
import java.util.LinkedList;


/**
 *
 * @author tiph
 */
public class VoieExterne extends Route{
    private VoieInterne _interne;
    private LinkedList<Voiture> _voitures = new LinkedList<>();
    
    public VoieExterne(VoieInterne v){
	_interne = v;
    }

    public LinkedList<Voiture> getVoitures() {
	return _voitures;
    }
    
    /**
     * Indique si la voiture est la premiere sur la voie
     * @param v
     * @return 
     */
    public boolean isFirst(Voiture v){
	return _voitures.getFirst() == v;
    }
    
    public VoieInterne GetVoieInterne(){
	return _interne;
    }
    
    public void Sortir(Voiture v){
	_voitures.remove(v);
    }
    
    public void Rentrer(Voiture v){
	_voitures.addLast(v);
    }
    
}
