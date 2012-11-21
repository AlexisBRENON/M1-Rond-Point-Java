package aclt.genielog.rp;

/**
 * Le Systeme
 * @author tiph
 */
public class RondPoint {
	private VoieExterne[] voieExternes;
	private VoieInterne[] voieInternes;

	public VoieExterne[] getVoieExternes() {
		return voieExternes;
	}

	public VoieInterne[] getVoieInternes() {
		return voieInternes;
	}

	/**
	 * Crée un rond point à 4 voie
	 * @param taille la taille entre les voies
	 */
	public RondPoint(int taille){
		int j;

		voieExternes = new VoieExterne[4];
		voieInternes = new VoieInterne[4];

		for(int i=0; i<4; i++){
			voieInternes[i] = new VoieInterne(taille);
			voieExternes[i] = new VoieExterne(voieInternes[i]);
		}

		for(int i=0; i<4;i++){
			j = (i + 1) % 4;
			voieInternes[i].config(voieInternes[j], voieExternes[j]);
		}
	}

	public void tourneInterne() {
		Voiture voitureDeTete = null;

		for (VoieInterne voieInterne: voieInternes) {
			voitureDeTete = voieInterne.circule(voitureDeTete, false);
		}
		voieInternes[0].circule(voitureDeTete, true);
	}

	public void tourneExterne() {
		for (VoieExterne voieExterne: voieExternes) {
			if (!voieExterne.getVoitures().isEmpty()) {
				voieExterne.getVoitures().getFirst().Avancer();
			}
		}
	}
}
