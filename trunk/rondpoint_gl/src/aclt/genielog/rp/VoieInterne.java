package aclt.genielog.rp;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

/**
 *
 * @author tiph
 */
class VoieInterne extends Voie {

	private static int ID = 0;

	private VoieInterne _suivante;
	private VoieExterne _sortie;
	private Voiture[] _vehicules;
	private int taille;

	public Voiture[] getVehicules() {
		return _vehicules;
	}

	public int getTaille() {
		return taille;
	}

	public VoieInterne(int taille) {
		super("Interne " + ID);
		ID++;
		_vehicules = new Voiture[taille];
		this.taille = taille;
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
		return _vehicules[_vehicules.length - 1] == v;
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
			_vehicules[_vehicules.length - 1] = null;
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
		_vehicules[_vehicules.length - 1] = null;
		v.setEstSur(_suivante);
	}

	/**
	 * Avance la voiture d'un tour
	 *
	 * @param v
	 */
	public void Avancer(Voiture v) {
		int i = 0;
		while(i < _vehicules.length && !v.equals(_vehicules[i])){
			i++;
		}
		_vehicules[i] = null;
		_vehicules[i+1] = v;
	}
}

class FileVoieInterne implements Queue<Voiture> {

	private Voiture[] liste;

	FileVoieInterne(int taille) {
		liste = new Voiture[taille];
	}

	@Override
	public boolean add(Voiture e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Voiture element() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean offer(Voiture e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Voiture peek() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Voiture poll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Voiture remove() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addAll(Collection<? extends Voiture> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterator<Voiture> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}

}