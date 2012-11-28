/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aclt.genielog.rp.ihm;

import aclt.genielog.rp.Simulateur;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author alexis
 */
public class SimulateurUI2 extends JFrame{
    // Variables declaration - do not modify
    private javax.swing.JPanel ajoutPanel;
    private javax.swing.JPanel fluxPanel;
    private javax.swing.JPanel imagePanel;
    private javax.swing.JPanel vitessePanel;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JPanel controlPanel;
    private javax.swing.JButton goButton;
    private Simulateur simulateur;
    // End of variables declaration

    public SimulateurUI2 (Simulateur simulateur) {
        this.simulateur = simulateur;
        this.initComponents();
    }

    private void initComponents () {
        ajoutPanel = new AjoutPanel(this);
        fluxPanel = new FluxPanel(this);
        imagePanel = new RondPointPanel(this);
        vitessePanel = new VitessePanel(this);

        goButton = new JButton("Lecture");

        mainPanel = new JPanel(new BorderLayout(10, 0));
        controlPanel = new JPanel(new GridLayout(4, 1, 0, 10));

        controlPanel.add(fluxPanel);
        controlPanel.add(ajoutPanel);
        controlPanel.add(vitessePanel);
        controlPanel.add(goButton);

        mainPanel.add(imagePanel, BorderLayout.WEST);
        mainPanel.add(controlPanel, BorderLayout.EAST);

        this.setContentPane(mainPanel);
    }

    public static void main (String args[]) {
        new SimulateurUI2(new Simulateur(10)).setVisible(true);
    }
}
