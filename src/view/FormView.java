package view;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Objects;

public class FormView extends PictureView {
    private JPanel autoPanel;
    private JPanel pretPanel;
    private JPanel buttonPanel;
    private JLabel marqueLabel;
    private JTextField marqueField;
    private JLabel modeleLabel;
    private JTextField modeleField;
    private JLabel anneeLabel;
    private JTextField anneeField;
    private JLabel kmLabel;
    private JTextField kmField;
    private JLabel VINLabel;
    private JTextField VINField;
    private JLabel montantPretLabel;
    private JTextField montantPretField;
    private JLabel dureePretLabel;
    private JComboBox dureePretJCB;
    private JButton soumettrebutton;
    private JButton btnRetour;
    private GridBagConstraints gbc;

    public FormView() {
        super("src/view/Voiture.jpg");
        setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();

        //Panel utiliser pour encapsuler les informations d'un vehicule
        autoPanel = new JPanel(new GridBagLayout());
        autoPanel.setBorder(new TitledBorder("Information du vehicule"));
        autoPanel.setPreferredSize(new Dimension(500, 300));

        //Panel utiliser pour encapsuler les informations du pret
        pretPanel = new JPanel(new GridBagLayout());
        pretPanel.setBorder(new TitledBorder("Type de pret souhaite"));
        pretPanel.setPreferredSize(new Dimension(500, 150));

        //Panel utiliser pour encapsuler les informations du pret
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        //Label + Field pour marque du vehicule
        marqueLabel = new JLabel("Marque du vehicule:");
        marqueLabel.setForeground(new Color(22, 197, 144));
        marqueField = new JTextField();
        marqueField.setBackground(Color.LIGHT_GRAY);
        marqueField.setPreferredSize(new Dimension(200, marqueField.getPreferredSize().height));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(10, 0, 10, 5);
        autoPanel.add(marqueLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 5, 10, 0);
        autoPanel.add(marqueField, gbc);

        //Label + Field pour modele du vehicule
        modeleLabel = new JLabel("Modele du vehicule:");
        modeleLabel.setForeground(new Color(22, 197, 144));
        modeleField = new JTextField();
        modeleField.setBackground(Color.LIGHT_GRAY);
        modeleField.setPreferredSize(new Dimension(200, modeleField.getPreferredSize().height));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(10, 0, 10, 5);
        autoPanel.add(modeleLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 5, 10, 0);
        autoPanel.add(modeleField, gbc);

        //Label + Field pour l'annee du vehicule
        anneeLabel = new JLabel("Annee du vehicule:");
        anneeLabel.setForeground(new Color(22, 197, 144));
        anneeField = new JTextField();
        anneeField.setBackground(Color.LIGHT_GRAY);
        anneeField.setPreferredSize(new Dimension(200, anneeField.getPreferredSize().height));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(10, 0, 10, 5);
        autoPanel.add(anneeLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 5, 10, 0);
        autoPanel.add(anneeField, gbc);

        //Label + Field pour le kilometrage du vehicule
        kmLabel = new JLabel("Kilometrage du vehicule:");
        kmLabel.setForeground(new Color(22, 197, 144));
        kmField = new JTextField();
        kmField.setBackground(Color.LIGHT_GRAY);
        kmField.setPreferredSize(new Dimension(200, kmField.getPreferredSize().height));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(10, 0, 10, 5);
        autoPanel.add(kmLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 5, 10, 0);
        autoPanel.add(kmField, gbc);

        //Label + Field pour le VIN du vehicule
        VINLabel = new JLabel("VIN du vehicule:");
        VINLabel.setForeground(new Color(22, 197, 144));
        VINField = new JTextField();
        VINField.setBackground(Color.LIGHT_GRAY);
        VINField.setPreferredSize(new Dimension(200, VINField.getPreferredSize().height));
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(10, 0, 10, 5);
        autoPanel.add(VINLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 5, 10, 0);
        autoPanel.add(VINField, gbc);

        //Label + Field pour le montant du pret
        montantPretLabel = new JLabel("Montant du pret:");
        montantPretLabel.setForeground(new Color(22, 197, 144));
        montantPretField = new JTextField();
        montantPretField.setBackground(Color.LIGHT_GRAY);
        montantPretField.setPreferredSize(new Dimension(200, montantPretField.getPreferredSize().height));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(10, 0, 10, 5);
        pretPanel.add(montantPretLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 5, 10, 0);
        pretPanel.add(montantPretField, gbc);

        //Label + JCB pour la duree du pret
        dureePretLabel = new JLabel("Duree du pret:");
        dureePretLabel.setForeground(new Color(22, 197, 144));
        dureePretJCB = new JComboBox<String>(new String[]{"1", "2", "3", "4"});
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        pretPanel.add(dureePretLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        pretPanel.add(dureePretJCB, gbc);

        //Button sousmettre le formulaire
        soumettrebutton = new JButton("Soumettre");
        soumettrebutton.setPreferredSize(new Dimension(200, soumettrebutton.getPreferredSize().height));
        buttonPanel.add(soumettrebutton);

        //Button Retour à la page d'accueil
        btnRetour = new JButton("Retour à la page d'accueil");
        btnRetour.setPreferredSize(new Dimension(200, btnRetour.getPreferredSize().height));
        buttonPanel.add(btnRetour);

        //Ajout des panels dans la vue
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(autoPanel, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(pretPanel, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(buttonPanel, gbc);
    }

    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    public void addEnregistrerListener(ActionListener listener) {
        soumettrebutton.addActionListener(listener);
    }

    public void addRetourALaPagePrincipaleListener(ActionListener listener) {
        btnRetour.addActionListener(listener);
    }

    public String getMarque() {
        return marqueField.getText();
    }

    public String getModeleField() {
        return modeleField.getText();
    }

    public String getAnneeField() {
        return anneeField.getText();
    }

    public String getKmField() {
        return kmField.getText();
    }

    public String getVINField() {
        return VINField.getText();
    }

    public String getMontantPretField() {
        return montantPretField.getText();
    }

    public String getDureePretJCB() {
        return Objects.requireNonNull(dureePretJCB.getSelectedItem()).toString();
    }
}
