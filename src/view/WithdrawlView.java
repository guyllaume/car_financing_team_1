package view;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class WithdrawlView extends JPanel {
    private JPanel withdrawPanel;
    private JPanel buttonPanel;
    private JLabel soldeLabel;
    private JLabel solde;
    private JLabel montantARetirerLabel;
    private JTextField montantARetirer;
    private JButton btnRetirer;
    private JButton btnRetour;
    private GridBagConstraints gbc;

    public WithdrawlView() {
        setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();

        //Panel utiliser pour encapsuler les informations d'un vehicule
        withdrawPanel = new JPanel(new GridBagLayout());
        withdrawPanel.setBorder(new TitledBorder("Retrait"));
        withdrawPanel.setPreferredSize(new Dimension(500, 300));

        //Panel utiliser pour encapsuler les informations du pret
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        //Label + Label pour le solde
        soldeLabel = new JLabel("Solde:");
        soldeLabel.setForeground(new Color(22, 197, 144));
        solde = new JLabel(" ");
        solde.setBackground(new Color(22, 197, 144));
        solde.setPreferredSize(new Dimension(200, solde.getPreferredSize().height));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(10, 0, 10, 5);
        withdrawPanel.add(soldeLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 5, 10, 0);
        withdrawPanel.add(solde, gbc);

        //Label + TextField pour le montant a retirer
        montantARetirerLabel = new JLabel("Montant:");
        montantARetirerLabel.setForeground(new Color(22, 197, 144));
        montantARetirer = new JTextField();
        montantARetirer.setPreferredSize(new Dimension(200, solde.getPreferredSize().height));
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(10, 0, 10, 5);
        withdrawPanel.add(montantARetirerLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 5, 10, 0);
        withdrawPanel.add(montantARetirer, gbc);

        //Button retirer le montant
        btnRetirer = new JButton("Retirer");
        btnRetirer.setPreferredSize(new Dimension(200, btnRetirer.getPreferredSize().height));
        buttonPanel.add(btnRetirer);

        //Button Retour à la page de compte Investisseur
        btnRetour = new JButton("Retour");
        btnRetour.setPreferredSize(new Dimension(200, btnRetirer.getPreferredSize().height));
        buttonPanel.add(btnRetour);

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(withdrawPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(10, 0, 0, 0);
        add(buttonPanel, gbc);


    }

    public void setMessageError(String messageError) {
        JOptionPane.showMessageDialog(null, messageError);
    }

    public void addRetourALaPageInvestorAccountListener(ActionListener listener) {
        btnRetour.addActionListener(listener);
    }

    public void setsolde(String solde) {
        this.solde.setText(solde);
    }
}
