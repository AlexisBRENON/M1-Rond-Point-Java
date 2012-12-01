package aclt.genielog.rp.system;

import java.awt.geom.AffineTransform;

public enum VoieEnum {
	NORD("Nord", 250, 30, -Math.PI / 2), OUEST("Ouest", 30, 300, Math.PI), SUD(
			"Sud", 300, 500, Math.PI / 2), EST("Est", 500, 250, .0), ALEAT(
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
		tx.rotate(theta, 29, 29);
		tx.scale(0.09, 0.09);
	}

	public AffineTransform getBaseTransfrom() {
		return new AffineTransform(tx);
	}

	@Override
	public String toString() {
		return name;
	}
}
