package view;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

public class DepositView extends JPanel{
    private JPanel depositPanel;
    private JPanel buttonPanel;
    private JLabel nomBanqueLabel;
    private JLabel nomBanque;
    private JLabel numeroInstitutionLabel;
    private JLabel numeroInstitution;
    private JLabel numeroTransitLabel;
    private JLabel numeroTransit;
    private JLabel numeroCompteLabel;
    private JLabel numeroCompte;
    private JLabel montantAInvestirLabel;
    private JTextField montantAInvestir;
    private JButton btnInvestir;
    private JButton btnRetour;
    private GridBagConstraints gbc;
    public DepositView() {
        setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();

        //Panel utiliser pour encapsuler les informations d'un vehicule
        depositPanel = new JPanel(new GridBagLayout());
        depositPanel.setBorder(new TitledBorder("Dépôt"));
        depositPanel.setPreferredSize(new Dimension(500, 300));

        //Panel utiliser pour encapsuler les informations du pret
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        //Label + Label pour nom de la banque
        nomBanqueLabel = new JLabel("Nom de la banque:");
        nomBanqueLabel.setForeground(new Color(22, 197, 144));
        nomBanque = new JLabel(" ");
        nomBanque.setBackground(new Color(22, 197, 144));
        nomBanque.setPreferredSize(new Dimension(200, nomBanque.getPreferredSize().height));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(10, 0, 10, 5);
        depositPanel.add(nomBanqueLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 5, 10, 0);
        depositPanel.add(nomBanque, gbc);

        //Label + Label pour le numeroInstitution de la banque
        numeroInstitutionLabel = new JLabel("Numéro d'institution:");
        numeroInstitutionLabel.setForeground(new Color(22, 197, 144));
        numeroInstitution = new JLabel(" ");
        numeroInstitution.setBackground(new Color(22, 197, 144));
        numeroInstitution.setPreferredSize(new Dimension(200, numeroInstitution.getPreferredSize().height));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(10, 0, 10, 5);
        depositPanel.add(numeroInstitutionLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 5, 10, 0);
        depositPanel.add(numeroInstitution, gbc);

        //Label + Label pour le numeroTransit
        numeroTransitLabel = new JLabel("Numéro de transit:");
        numeroTransitLabel.setForeground(new Color(22, 197, 144));
        numeroTransit = new JLabel(" ");
        numeroTransit.setBackground(new Color(22, 197, 144));
        numeroTransit.setPreferredSize(new Dimension(200, numeroTransit.getPreferredSize().height));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(10, 0, 10, 5);
        depositPanel.add(numeroTransitLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 5, 10, 0);
        depositPanel.add(numeroTransit, gbc);

        //Label + Label pour le numero de compte
        numeroCompteLabel = new JLabel("Numéro de compte:");
        numeroCompteLabel.setForeground(new Color(22, 197, 144));
        numeroCompte = new JLabel(" ");
        numeroCompte.setBackground(new Color(22, 197, 144));
        numeroCompte.setPreferredSize(new Dimension(200, numeroCompte.getPreferredSize().height));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(10, 0, 10, 5);
        depositPanel.add(numeroCompteLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 5, 10, 0);
        depositPanel.add(numeroCompte, gbc);

        //Label + TextField pour le montant a investir
        montantAInvestirLabel = new JLabel("Montant:");
        montantAInvestirLabel.setForeground(new Color(22, 197, 144));
        montantAInvestir = new JTextField();
        montantAInvestir.setPreferredSize(new Dimension(200, numeroCompte.getPreferredSize().height));
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(10, 0, 10, 5);
        depositPanel.add(montantAInvestirLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 5, 10, 0);
        depositPanel.add(montantAInvestir, gbc);

        //Button Ajout du montant a Investir
        btnInvestir = new JButton("Ajouter");
        btnInvestir.setPreferredSize(new Dimension(200, btnInvestir.getPreferredSize().height));
        buttonPanel.add(btnInvestir);

        //Button Retour à la page de compte Investisseur
        btnRetour = new JButton("Retour");
        btnRetour.setPreferredSize(new Dimension(200, btnInvestir.getPreferredSize().height));
        buttonPanel.add(btnRetour);

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(depositPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(10, 0, 0, 0);
        add(buttonPanel, gbc);


    }
    public void setMessageError(String messageError) {JOptionPane.showMessageDialog(null, messageError);}

    public void addRetourALaPageInvestorAccountListener(ActionListener listener) {
        btnRetour.addActionListener(listener);
    }

    public void setnomBanque(String nomBanque) {
        this.nomBanque.setText(nomBanque);
    }
    public void setnumeroInstitution(String numeroInstitution) {
        this.numeroInstitution.setText(numeroInstitution);
    }
    public void setNumeroTransit(String numeroTransit) {
        this.numeroTransit.setText(numeroTransit);
    }
    public void setNumeroCompte(String NumeroCompte) {
        this.numeroCompte.setText(NumeroCompte);
    }
}
