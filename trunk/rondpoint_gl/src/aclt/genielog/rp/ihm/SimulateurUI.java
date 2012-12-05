package aclt.genielog.rp.ihm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observer;

import javax.swing.JComponent;

import aclt.genielog.rp.Simulateur;
import aclt.genielog.rp.ihm.AjoutPanel.AjoutVoituresListener;
import aclt.genielog.rp.lib.Flux;
import aclt.genielog.rp.lib.Tour;
import java.util.Observable;

/**
 * Fenêtre principale de l'application.
 * C'est l'agrégat de tous nos Panel et de quelques composants simples.
 *
 * @author Alexis Brenon
 * @author Cécilia Martin
 * @author Luc Chante
 * @author Tiphaine Teyssier
 */
public class SimulateurUI extends javax.swing.JFrame implements Observer {

	/**
	 * Creates new form SimulateurUI
	 */
	public SimulateurUI(Simulateur simulateur) {
		initComponents();
		jButton1.addActionListener(new ActionListener() {
			private final String[] titles = { "Lecture", "Pause" };
			private int status = 0;

			@Override
			public void actionPerformed(ActionEvent e) {
				status = (status + 1) % 2;
				jButton1.setText(titles[status]);
			}
		});
	}

	/**
	 * Ajoute une voie (de type JComponent) à peindre sur le rond-point.
	 *
	 * @param voie
	 *            La voie a peindre.
	 */
	public void ajouterVoie(JComponent voie) {
		rondPointPanel.ajouterVoie(voie);
	}

	public void ajouterAccidentListener(ActionListener voie) {
		fluxPanel.addAccidentListener(voie);
	}

	public Observer getPanel() {
		return rondPointPanel;
	}

        public void update (Observable o, Object arg) {
            rondPointPanel.update(o, arg);
            statPanel.update(o, arg);
        }

	/**
	 * Attributs au panel des flux les listeners pour les actions des boutons et des
	 * spinners.
	 *
	 * @param nord
	 *            Le listener de la voie nord
	 * @param est
	 *            Le listener de la voie est
	 * @param sud
	 *            Le listener de la voie sud
	 * @param ouest
	 *            Le listener de la voie ouest
	 */
	public void setFluxListener(Flux nord, Flux est, Flux sud, Flux ouest) {
		fluxPanel.setListeners(nord, est, sud, ouest, null);
		jButton1.addActionListener(nord);
		jButton1.addActionListener(est);
		jButton1.addActionListener(sud);
		jButton1.addActionListener(ouest);
	}

	/**
	 * Définie le listener pour le bouton play/pause.
	 *
	 * @param playPause
	 *            Le listener
	 */
	public void setVitesseListener(Tour tour) {
		vitessePanel.setListeners(tour);
		jButton1.addActionListener(tour);
	}

	/**
	 * Définie le listener pour l'ajout de voitures.
	 *
	 * @param listener
	 *            Le listener
	 */
	public void setAjoutVoituresListener(AjoutVoituresListener listener) {
		ajoutPanel.addAjoutVoituresListener(listener);
	}

	public void dispLog(String s) {
		this.logPanel.addLine(s);
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed"
	// <editor-fold defaultstate="collapsed"
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        jButton1 = new javax.swing.JButton();
        rondPointPanel = new aclt.genielog.rp.ihm.RondPointPanel();
        logPanel = new aclt.genielog.rp.ihm.LogPanel();
        vitessePanel = new aclt.genielog.rp.ihm.VitessePanel();
        ajoutPanel = new aclt.genielog.rp.ihm.AjoutPanel();
        fluxPanel = new aclt.genielog.rp.ihm.FluxPanel();
        jSeparator2 = new javax.swing.JSeparator();
        statPanel = new aclt.genielog.rp.ihm.statPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Simuron");
        setName("simuronFrame"); // NOI18N

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jButton1.setText("Lecture");
        jButton1.setToolTipText("Lire/Arrêter la simulation.");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(rondPointPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator2)
                    .addComponent(statPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(vitessePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(logPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ajoutPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fluxPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(fluxPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ajoutPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(vitessePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(logPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(rondPointPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(statPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private aclt.genielog.rp.ihm.AjoutPanel ajoutPanel;
    private aclt.genielog.rp.ihm.FluxPanel fluxPanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private aclt.genielog.rp.ihm.LogPanel logPanel;
    private aclt.genielog.rp.ihm.RondPointPanel rondPointPanel;
    private aclt.genielog.rp.ihm.statPanel statPanel;
    private aclt.genielog.rp.ihm.VitessePanel vitessePanel;
    // End of variables declaration//GEN-END:variables
}
