package aclt.genielog.rp;

public enum VoieEnum {
	NORD("Nord"), OUEST("Ouest"), SUD("Sud"),  EST("Est"), ALEAT("Alétaoire");
	
	private final String name;
	
	VoieEnum(String name) {
		this.name = name;
	}
	
	public String toString() {
		return name;
	}
}
