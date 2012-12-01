package aclt.genielog.rp.ihm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import aclt.genielog.rp.lib.Flux;

/**
 * Il s'agit d'un componsant graphique regroupant tous les items utilisés
 * pour gérer les flux de voitures dans la simulation.
 * 
 * @author alexis
 */
public class FluxPanel extends javax.swing.JPanel {
	public FluxPanel() {
		initComponents();
		this.setVisible(true);
		nordButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				nordSpinner.setValue(0);
			}
		});
		estButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				estSpinner.setValue(0);
			}
		});
		sudButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sudSpinner.setValue(0);
			}
		});
		ouestButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ouestSpinner.setValue(0);
			}
		});
	}

	public void setListeners(Flux nord, Flux est, Flux sud, Flux ouest, Object crash) {
		nordSpinner.addChangeListener(nord);
		nordButton.addActionListener(nord);
		estSpinner.addChangeListener(est);
		estButton.addActionListener(est);
		sudSpinner.addChangeListener(sud);
		sudButton.addActionListener(sud);
		ouestSpinner.addChangeListener(ouest);
		ouestButton.addActionListener(ouest);
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

		titreLabel = new javax.swing.JLabel();
		nordLabel = new javax.swing.JLabel();
		nordSpinner = new javax.swing.JSpinner();
		nordButton = new javax.swing.JButton();
		ouestLabel = new javax.swing.JLabel();
		ouestSpinner = new javax.swing.JSpinner();
		ouestButton = new javax.swing.JButton();
		sudLabel = new javax.swing.JLabel();
		sudSpinner = new javax.swing.JSpinner();
		sudButton = new javax.swing.JButton();
		estLabel = new javax.swing.JLabel();
		estSpinner = new javax.swing.JSpinner();
		estButton = new javax.swing.JButton();
		crashButton = new javax.swing.JButton();
		jSeparator1 = new javax.swing.JSeparator();

		titreLabel.setText("Flux :");

		nordLabel.setText("Nord :");
		nordLabel.setToolTipText("Voie du haut.");

		nordSpinner.setModel(new javax.swing.SpinnerNumberModel(0, 0, 100, 1));
		nordSpinner
				.setToolTipText("Nombre de voitures arrivant de cette voie à chaque déplacement.");

		nordButton.setText("Vider");
		nordButton.setToolTipText("Réinitialiser le flux d'entrée.");

		ouestLabel.setText("Ouest :");
		ouestLabel.setToolTipText("Voie de gauche.");

		ouestSpinner.setModel(new javax.swing.SpinnerNumberModel(0, 0, 100, 1));
		ouestSpinner
				.setToolTipText("Nombre de voitures arrivant de cette voie à chaque déplacement.");

		ouestButton.setText("Vider");
		ouestButton.setToolTipText("Réinitialiser le flux d'entrée.");

		sudLabel.setText("Sud :");
		sudLabel.setToolTipText("Voie du bas.");

		sudSpinner.setModel(new javax.swing.SpinnerNumberModel(0, 0, 100, 1));
		sudSpinner
				.setToolTipText("Nombre de voitures arrivant de cette voie à chaque déplacement.");

		sudButton.setText("Vider");
		sudButton.setToolTipText("Réinitialiser le flux d'entrée.");

		estLabel.setText("Est :");
		estLabel.setToolTipText("Voie de droite.");

		estSpinner.setModel(new javax.swing.SpinnerNumberModel(0, 0, 100, 1));
		estSpinner
				.setToolTipText("Nombre de voitures arrivant de cette voie à chaque déplacement.");

		estButton.setText("Vider");
		estButton.setToolTipText("Réinitialiser le flux d'entrée.");

		crashButton.setText("Car crash");
		crashButton.setToolTipText("Tout réinitialiser.");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(
														crashButton,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addGroup(
														layout.createSequentialGroup()
																.addGap(12, 12, 12)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING,
																				false)
																				.addComponent(
																						nordLabel,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						Short.MAX_VALUE)
																				.addComponent(
																						ouestLabel,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						Short.MAX_VALUE)
																				.addComponent(
																						sudLabel,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						Short.MAX_VALUE)
																				.addComponent(
																						estLabel,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						Short.MAX_VALUE))
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING)
																				.addGroup(
																						layout.createSequentialGroup()
																								.addGroup(
																										layout.createParallelGroup(
																												javax.swing.GroupLayout.Alignment.LEADING)
																												.addComponent(
																														estSpinner)
																												.addComponent(
																														sudSpinner,
																														javax.swing.GroupLayout.Alignment.TRAILING)
																												.addComponent(
																														ouestSpinner,
																														javax.swing.GroupLayout.Alignment.TRAILING))
																								.addPreferredGap(
																										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																								.addGroup(
																										layout.createParallelGroup(
																												javax.swing.GroupLayout.Alignment.LEADING)
																												.addComponent(
																														ouestButton,
																														javax.swing.GroupLayout.Alignment.TRAILING)
																												.addComponent(
																														sudButton,
																														javax.swing.GroupLayout.Alignment.TRAILING)
																												.addComponent(
																														estButton,
																														javax.swing.GroupLayout.Alignment.TRAILING)))
																				.addGroup(
																						javax.swing.GroupLayout.Alignment.TRAILING,
																						layout.createSequentialGroup()
																								.addComponent(
																										nordSpinner)
																								.addPreferredGap(
																										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																								.addComponent(
																										nordButton))))
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		titreLabel)
																.addGap(0,
																		0,
																		Short.MAX_VALUE))
												.addComponent(jSeparator1))
								.addContainerGap()));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap(
										javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(titreLabel)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(nordLabel)
												.addComponent(
														nordSpinner,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(nordButton))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(ouestLabel)
												.addComponent(
														ouestSpinner,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(ouestButton))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(
														sudSpinner,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(sudButton)
												.addComponent(sudLabel))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(estLabel)
												.addComponent(
														estSpinner,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(estButton))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(crashButton)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jSeparator1,
										javax.swing.GroupLayout.PREFERRED_SIZE, 10,
										javax.swing.GroupLayout.PREFERRED_SIZE)));
	}// </editor-fold>//GEN-END:initComponents
		// Variables declaration - do not modify//GEN-BEGIN:variables

	private javax.swing.JButton crashButton;
	private javax.swing.JButton estButton;
	private javax.swing.JLabel estLabel;
	private javax.swing.JSpinner estSpinner;
	private javax.swing.JSeparator jSeparator1;
	private javax.swing.JButton nordButton;
	private javax.swing.JLabel nordLabel;
	private javax.swing.JSpinner nordSpinner;
	private javax.swing.JButton ouestButton;
	private javax.swing.JLabel ouestLabel;
	private javax.swing.JSpinner ouestSpinner;
	private javax.swing.JButton sudButton;
	private javax.swing.JLabel sudLabel;
	private javax.swing.JSpinner sudSpinner;
	private javax.swing.JLabel titreLabel;
	// End of variables declaration//GEN-END:variables
}
