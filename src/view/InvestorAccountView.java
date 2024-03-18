package view;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

public class InvestorAccountView extends PictureView {
    private JPanel accountPanel;
    private JPanel transactionPanel;
    private JPanel buttonPanel;
    private JLabel montantTotalLabel;
    private JLabel montantTotal;
    private JList<String> transactionsList;
    private JScrollPane scrollPane;
    private JLabel soldeLabel;
    private JLabel solde;
    private JLabel informationInvestor;
    private JButton btnInvestir;
    private JButton btnRetirer;
    private JButton btnRetour;
    private GridBagConstraints gbc;

    public InvestorAccountView() {
        super("src/view/Voiture.jpg");
        setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();

        //Panel utiliser pour encapsuler les informations d'un vehicule
        accountPanel = new JPanel(new GridBagLayout());
        accountPanel.setBorder(new TitledBorder("Investor Account"));
        accountPanel.setPreferredSize(new Dimension(500, 450));

        //Panel utiliser pour encapsuler les informations du pret
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        //Panel utiliser pour encapsuler la liste de transaction
        transactionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        transactionPanel.setBorder(new TitledBorder("Transactions"));
        transactionPanel.setPreferredSize(new Dimension(300, 150));

        //Label pour information Investisseur
        informationInvestor = new JLabel(" ");
        informationInvestor.setBackground(new Color(22, 197, 144));
        informationInvestor.setMinimumSize(informationInvestor.getPreferredSize());
        informationInvestor.setMaximumSize(informationInvestor.getPreferredSize());
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 0, 10, 5);
        gbc.gridwidth = 2;
        accountPanel.add(informationInvestor, gbc);
        gbc.gridwidth = 1;

        //Label + Label pour montant total investi
        montantTotalLabel = new JLabel("Montant total investi:");
        montantTotalLabel.setForeground(new Color(22, 197, 144));
        montantTotal = new JLabel(" ");
        montantTotal.setBackground(new Color(22, 197, 144));
        montantTotal.setPreferredSize(new Dimension(100, montantTotal.getPreferredSize().height));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.insets = new Insets(10, 0, 10, 5);
        accountPanel.add(montantTotalLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(10, 5, 10, 0);
        accountPanel.add(montantTotal, gbc);

        //JList + ScrollPane + JPanel pour afficher toutes les transactions
        transactionsList = new JList<>();
        scrollPane = new JScrollPane(transactionsList);
        scrollPane.setPreferredSize(new Dimension(250, 100));
        transactionPanel.add(scrollPane);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 0, 10, 5);
        gbc.gridwidth = 2;
        accountPanel.add(transactionPanel, gbc);
        gbc.gridwidth = 1;

        //Label + Label pour le solde du compte
        soldeLabel = new JLabel("Solde:");
        soldeLabel.setForeground(new Color(22, 197, 144));
        solde = new JLabel(" ");
        solde.setBackground(new Color(22, 197, 144));
        solde.setPreferredSize(new Dimension(100, solde.getPreferredSize().height));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.insets = new Insets(10, 0, 10, 5);
        accountPanel.add(soldeLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(10, 5, 10, 0);
        accountPanel.add(solde, gbc);

        //Button Retour à la page d'accueil
        btnInvestir = new JButton("Investir");
        btnInvestir.setPreferredSize(new Dimension(200, btnInvestir.getPreferredSize().height));
        buttonPanel.add(btnInvestir);

        //Button Modifier le formulaire
        btnRetirer = new JButton("Retirer");
        btnRetirer.setPreferredSize(new Dimension(200, btnRetirer.getPreferredSize().height));
        buttonPanel.add(btnRetirer);

        //Button Retour à la page d'accueil
        btnRetour = new JButton("Retour à la page d'accueil");
        btnRetour.setPreferredSize(new Dimension(200, btnRetour.getPreferredSize().height));

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(accountPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(10, 0, 0, 0);
        add(buttonPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(2, 0, 0, 0);
        add(btnRetour, gbc);


    }
    public void setMessageError(String messageError) {JOptionPane.showMessageDialog(null, messageError);}

    public void addRetourALaPagePrincipaleListener(ActionListener listener) {
        btnRetour.addActionListener(listener);
    }
    public void addInvestirListener(ActionListener listener) {
        btnInvestir.addActionListener(listener);
    }
    public void addRetirerListener(ActionListener listener) {
        btnRetirer.addActionListener(listener);
    }
    public void setMontantTotal(String montantTotal) {
        this.montantTotal.setText(montantTotal);
    }
    public void setSolde(String solde) {
        this.solde.setText(solde);
    }
    public void setTransactionsList(List<String> transactions){ this.transactionsList.setListData(transactions.toArray(new String[transactions.size()]));}
    public void setInformationInvestor(String investorInfo) {
        this.informationInvestor.setText(investorInfo);
    }
}