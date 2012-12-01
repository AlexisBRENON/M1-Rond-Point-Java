package aclt.genielog.rp.ihm;

import java.util.concurrent.TimeUnit;

import aclt.genielog.rp.lib.Tour;

/**
 * Il s'agit d'un componsant graphique regroupant tous les items utilisés
 * pour gérer la vitesse de la simulation.
 *
 * @author alexis
 */
public class VitessePanel extends javax.swing.JPanel {

	/**
	 * Creates new form VitessePanel
	 */
	public VitessePanel() {
		initComponents();
	}

	public void setListeners(Tour tour) {
		jComboBox1.addItemListener(tour);
		vitesseSpinner.addChangeListener(tour);
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed"
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titreLabel = new javax.swing.JLabel();
        vitesseSpinner = new javax.swing.JSpinner();
        jSeparator1 = new javax.swing.JSeparator();
        jComboBox1 = new javax.swing.JComboBox();

        titreLabel.setText("Temps de pause :");

        vitesseSpinner.setModel(new javax.swing.SpinnerNumberModel(Float.valueOf(1.0f), Float.valueOf(0.0f), Float.valueOf(1000.0f), Float.valueOf(0.5f)));
        vitesseSpinner.setToolTipText("Permet de modifier la vitesse de simulation.");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(TimeUnit.values()));
        jComboBox1.setSelectedItem(TimeUnit.SECONDS);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(layout.createSequentialGroup()
                .addComponent(titreLabel)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(vitesseSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(titreLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(vitesseSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

	private void vitesseSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {// GEN-FIRST:event_vitesseSpinnerStateChanged
		// parentFrame.changeVitesseSimu((Integer) vitesseSpinner.getValue());
	}// GEN-LAST:event_vitesseSpinnerStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel titreLabel;
    private javax.swing.JSpinner vitesseSpinner;
    // End of variables declaration//GEN-END:variables
}
