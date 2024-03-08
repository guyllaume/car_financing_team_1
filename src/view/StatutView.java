package view;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class StatutView extends PictureView {
    private JPanel statusPanel;
    private JPanel buttonPanel;
    private JLabel marqueLabel;
    private JLabel marque;
    private JLabel modelLabel;
    private JLabel model;
    private JLabel anneeLabel;
    private JLabel annee;
    private JLabel kmLabel;
    private JLabel km;
    private JLabel statutDemandeLabel;
    private JLabel statutDemande;
    private JLabel statutProgressLabel;
    private JProgressBar statutProgressPB;
    private JButton btnRetour;
    private JButton btnModifier;
    private GridBagConstraints gbc;

    public StatutView() {
        super("src/view/Voiture.jpg");
        setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();

        //Panel utiliser pour encapsuler les informations d'un vehicule
        statusPanel = new JPanel(new GridBagLayout());
        statusPanel.setBorder(new TitledBorder("Statut du Financement"));
        statusPanel.setPreferredSize(new Dimension(500, 300));

        //Panel utiliser pour encapsuler les informations du pret
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        //Label + Label pour marque du vehicule
        marqueLabel = new JLabel("Marque du vehicule:");
        marqueLabel.setForeground(new Color(22, 197, 144));
        marque = new JLabel(" ");
        marque.setBackground(new Color(22, 197, 144));
        marque.setPreferredSize(new Dimension(200, marque.getPreferredSize().height));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(10, 0, 10, 5);
        statusPanel.add(marqueLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 5, 10, 0);
        statusPanel.add(marque, gbc);

        //Label + Label pour model du vehicule
        modelLabel = new JLabel("Modele du vehicule:");
        modelLabel.setForeground(new Color(22, 197, 144));
        model = new JLabel(" ");
        model.setBackground(new Color(22, 197, 144));
        model.setPreferredSize(new Dimension(200, model.getPreferredSize().height));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(10, 0, 10, 5);
        statusPanel.add(modelLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 5, 10, 0);
        statusPanel.add(model, gbc);

        //Label + Label pour annee du vehicule
        anneeLabel = new JLabel("Annee du vehicule:");
        anneeLabel.setForeground(new Color(22, 197, 144));
        annee = new JLabel(" ");
        annee.setBackground(new Color(22, 197, 144));
        annee.setPreferredSize(new Dimension(200, annee.getPreferredSize().height));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(10, 0, 10, 5);
        statusPanel.add(anneeLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 5, 10, 0);
        statusPanel.add(annee, gbc);

        //Label + Label pour km du vehicule
        kmLabel = new JLabel("Kilometrage du vehicule:");
        kmLabel.setForeground(new Color(22, 197, 144));
        km = new JLabel(" ");
        km.setBackground(new Color(22, 197, 144));
        km.setPreferredSize(new Dimension(200, km.getPreferredSize().height));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(10, 0, 10, 5);
        statusPanel.add(kmLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 5, 10, 0);
        statusPanel.add(km, gbc);

        //Label + Label pour statut du vehicule
        statutDemandeLabel = new JLabel("Etat de votre demande:");
        statutDemandeLabel.setForeground(new Color(22, 197, 144));
        statutDemande = new JLabel("En Cours");
        statutDemande.setBackground(new Color(22, 197, 144));
        statutDemande.setPreferredSize(new Dimension(200, statutDemande.getPreferredSize().height));
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(10, 0, 10, 5);
        statusPanel.add(statutDemandeLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 5, 10, 0);
        statusPanel.add(statutDemande, gbc);

        //Label + ProgressBar pour statut du financement
        statutProgressLabel = new JLabel("Progression du financement:");
        statutProgressLabel.setForeground(new Color(22, 197, 144));
        statutProgressPB = new JProgressBar(0,100);
        statutProgressPB.setStringPainted(true); // Show the percentage completed
        statutProgressPB.setValue(20);
        statutProgressPB.setPreferredSize(new Dimension(200, statutProgressPB.getPreferredSize().height));
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(10, 0, 10, 5);
        statusPanel.add(statutProgressLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 5, 10, 0);
        statusPanel.add(statutProgressPB, gbc);

        //Button Modifier le formulaire
        btnModifier = new JButton("Modifier la demande");
        btnModifier.setPreferredSize(new Dimension(200, btnModifier.getPreferredSize().height));
        btnModifier.setEnabled(false);
        buttonPanel.add(btnModifier);

        //Button Retour à la page d'accueil
        btnRetour = new JButton("Retour à la page d'accueil");
        btnRetour.setPreferredSize(new Dimension(200, btnRetour.getPreferredSize().height));
        buttonPanel.add(btnRetour);

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(statusPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(10, 0, 0, 0);
        add(buttonPanel, gbc);


    }
    public void setMessageError(String messageError) {JOptionPane.showMessageDialog(null, messageError);}

    public void addRetourALaPagePrincipaleListener(ActionListener listener) {
        btnRetour.addActionListener(listener);
    }

    public void setMarque(String marque) {
        this.marque.setText(marque);
    }
    public void setModel(String model) {
        this.model.setText(model);
    }
    public void setAnnee(String annee) {
        this.annee.setText(annee);
    }
    public void setKm(String km) {
        this.km.setText(km);
    }
}
