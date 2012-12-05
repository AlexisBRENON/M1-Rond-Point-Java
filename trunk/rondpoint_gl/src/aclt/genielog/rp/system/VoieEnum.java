package aclt.genielog.rp.system;

import java.awt.geom.AffineTransform;

/**
 * @author Alexis Brenon
 * @author Cécilia Martin
 * @author Luc Chante
 * @author Tiphaine Teyssier
 */
public enum VoieEnum {
	NORD("Nord"), OUEST("Ouest"), SUD("Sud"), EST("Est"), ALEAT(
			"Aléatoire");

	private final String name;
	private final AffineTransform tx;

	VoieEnum(String name) {
		this.name = name;
		tx = null;
	}

	@Override
	public String toString() {
		return name;
	}
}
