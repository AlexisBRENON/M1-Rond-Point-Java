package aclt.genielog.rp.ihm;

import aclt.genielog.rp.Simulateur;

/**
 * Fenêtre principale de l'application.
 * C'est l'agrégat de tous nos Panel et de quelques composants simples.
 * @author alexis
 */
public class SimulateurUI extends javax.swing.JFrame {

    /**
     * Creates new form SimulateurUI
     */
    public SimulateurUI(Simulateur simulateur) {
        initComponents();
        this.simulateur = simulateur;
        this.repaint();
    }

    /**
     * Callback appelé par le panel d'ajout de voitures.
     * @param nbVoitures Nombre de voitures à ajouter
     * @param entree Voie d'entrée des voitures
     * @param sortie Voie de sortie des voitures
     */
    /*public void ajouterVoitures (int nbVoitures, VoieEnum entree, VoieEnum sortie) {
        simulateur.ajouterVoitures(nbVoitures, entree, sortie);
    }*/

    /**
     * Callback appelé par le panel de modification de vitesse.
     * @param vitesse Nouvelle vitesse de simulation
     */
    public void changeVitesseSimu (int vitesse) {
        //simulateur.changeVitesseSimu(vitesse);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fluxPanel = new FluxPanel(this);
        jSeparator1 = new javax.swing.JSeparator();
        ajoutPanel = new AjoutPanel(this);
        jSeparator2 = new javax.swing.JSeparator();
        vitessePanel = new VitessePanel(this);
        jSeparator3 = new javax.swing.JSeparator();
        goButton = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JSeparator();
        imagePanel = new RondPointPanel(this);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Simuron");
        setName("simuronFrame"); // NOI18N
        setResizable(false);

        fluxPanel.setBackground(new java.awt.Color(204, 204, 255));

        javax.swing.GroupLayout fluxPanelLayout = new javax.swing.GroupLayout(fluxPanel);
        fluxPanel.setLayout(fluxPanelLayout);
        fluxPanelLayout.setHorizontalGroup(
            fluxPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        fluxPanelLayout.setVerticalGroup(
            fluxPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 114, Short.MAX_VALUE)
        );

        ajoutPanel.setBackground(new java.awt.Color(204, 204, 255));

        javax.swing.GroupLayout ajoutPanelLayout = new javax.swing.GroupLayout(ajoutPanel);
        ajoutPanel.setLayout(ajoutPanelLayout);
        ajoutPanelLayout.setHorizontalGroup(
            ajoutPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        ajoutPanelLayout.setVerticalGroup(
            ajoutPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 112, Short.MAX_VALUE)
        );

        vitessePanel.setBackground(new java.awt.Color(204, 204, 255));

        javax.swing.GroupLayout vitessePanelLayout = new javax.swing.GroupLayout(vitessePanel);
        vitessePanel.setLayout(vitessePanelLayout);
        vitessePanelLayout.setHorizontalGroup(
            vitessePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        vitessePanelLayout.setVerticalGroup(
            vitessePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 112, Short.MAX_VALUE)
        );

        goButton.setText("Lecture");
        goButton.setToolTipText("Lancer/Arrêter la simulation.");

        jSeparator4.setOrientation(javax.swing.SwingConstants.VERTICAL);

        imagePanel.setBackground(new java.awt.Color(204, 204, 255));

        javax.swing.GroupLayout imagePanelLayout = new javax.swing.GroupLayout(imagePanel);
        imagePanel.setLayout(imagePanelLayout);
        imagePanelLayout.setHorizontalGroup(
            imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 407, Short.MAX_VALUE)
        );
        imagePanelLayout.setVerticalGroup(
            imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(imagePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(vitessePanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ajoutPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fluxPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator1)
                    .addComponent(jSeparator2)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(goButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(fluxPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ajoutPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(vitessePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(goButton, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jSeparator4)
            .addComponent(imagePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main (String args[]) {
        new SimulateurUI(new Simulateur(10)).setVisible(true);

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ajoutPanel;
    private javax.swing.JPanel fluxPanel;
    private javax.swing.JButton goButton;
    private javax.swing.JPanel imagePanel;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JPanel vitessePanel;
    // End of variables declaration//GEN-END:variables
    private Simulateur simulateur;
}