/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aclt.genielog.rp.ihm;

import java.awt.Graphics;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JComponent;

/**
 *
 * @author Alexis Brenon
 * @author Cécilia Martin
 * @author Luc Chante
 * @author Tiphaine Teyssier
 */
public class RondPointPanel extends javax.swing.JPanel implements Observer {

	ArrayList<JComponent> voies = new ArrayList<JComponent>();

	/**
	 * Creates new form RondPointPanel
	 *
	 * @throws URISyntaxException
	 */
	public RondPointPanel() {
		initComponents();
		this.repaint();
	}

	public void ajouterVoie(JComponent voie) {
		voies.add(voie);
	}

	@Override
	public void update(Observable o, Object arg) {
		repaint();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		for (JComponent voie : voies) {
			voie.paint(g);
		}
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed"
	// desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		font = new javax.swing.JLabel();

		setMaximumSize(new java.awt.Dimension(600, 600));
		setMinimumSize(new java.awt.Dimension(600, 600));
		setName(""); // NOI18N
		setPreferredSize(new java.awt.Dimension(600, 600));

		font.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/aclt/genielog/rp/ihm/img/rond_point.png"))); // NOI18N
		font.setText("font");
		font.setAlignmentY(0.0F);
		font.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource(
				"/aclt/genielog/rp/ihm/img/rond_point.png"))); // NOI18N
		font.setMaximumSize(new java.awt.Dimension(600, 600));
		font.setMinimumSize(new java.awt.Dimension(600, 600));
		font.setPreferredSize(new java.awt.Dimension(600, 600));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				javax.swing.GroupLayout.Alignment.TRAILING,
				layout.createSequentialGroup()
						.addGap(0, 0, 0)
						.addComponent(font, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(0, 0, 0)));
		layout.setVerticalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				javax.swing.GroupLayout.Alignment.TRAILING,
				layout.createSequentialGroup()
						.addComponent(font, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(0, 0, 0)));
	}// </editor-fold>//GEN-END:initComponents
		// Variables declaration - do not modify//GEN-BEGIN:variables

	private javax.swing.JLabel font;
	// End of variables declaration//GEN-END:variables
}
