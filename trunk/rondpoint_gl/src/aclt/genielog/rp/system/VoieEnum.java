package aclt.genielog.rp.system;

import java.awt.geom.AffineTransform;

/**
 * @author Alexis Brenon
 * @author Cécilia Martin
 * @author Luc Chante
 * @author Tiphaine Teyssier
 */
public enum VoieEnum {
	NORD("Nord", 250, 30, Math.PI / 2), OUEST("Ouest", 30, 300, 0.0), SUD("Sud",
			300, 500, -Math.PI / 2), EST("Est", 500, 250, Math.PI), ALEAT(
			"Alétaoire");

	private final String name;
	private final AffineTransform tx;

	VoieEnum(String name) {
		this.name = name;
		tx = null;
	}

	VoieEnum(String name, int dx, int dy, double theta) {
		this.name = name;
		tx = new AffineTransform();
		tx.translate(dx, dy);
		tx.rotate(theta, 30, 30);
	}

	public AffineTransform entreeTransfrom() {
		return new AffineTransform(tx);
	}

	public AffineTransform sortieTransfrom() {
		return new AffineTransform(tx);
	}

	@Override
	public String toString() {
		return name;
	}
}
