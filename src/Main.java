import config.PostgresSQLConfig;
import controller.*;
import model.Client;
import model.FinancingForm;
import model.Investor;
import model.InvestorForm;
import view.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {
    private CardLayout cardLayout = new CardLayout();
    private JPanel cardPanel = new JPanel(cardLayout);
    private JPanel buttonPanel = new JPanel(new FlowLayout());
    private JPanel centerPanel = new JPanel(new GridBagLayout());
    private PictureView mainPanel = new PictureView("src/view/Voiture.jpg");
    private LoginView loginView = new LoginView();
    private RegisterView registerView = new RegisterView();
    private FormView formView = new FormView();
    private StatutView statutView = new StatutView();
    private InvestorAccountView investorAccountView = new InvestorAccountView();
    private DepositView depositView = new DepositView();
    private WithdrawlView withdrawlView = new WithdrawlView();
    private Client client = new Client();
    private Investor investor = new Investor();
    private FinancingForm financingForm = new FinancingForm();
    private InvestorForm investorForm  = new InvestorForm();
    private ClientRegisterController crController;
    private InvestorRegisterController irController;
    private LoginToAccountController ltaController;
    private FinancingFormRegisterController ffrController;
    private StatutFormController sfController;
    private InvestorAccountController iaController;
    private DepositController dController;
    private WithdrawlController wController;

    public Main() {
        setTitle("Financement Automobile XYZ");
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel description = new JLabel("<html>Financement Automobile XYZ offrent des solutions personnalisées pour l'achat de véhicules neufs ou d'occasion. Ils comprennent des prêts, des options de location et des plans de paiement flexibles pour répondre aux besoins financiers individuels des clients.<br><br> Financement Automobile XYZ fournissent aussi des solutions de prêt et de gestion de fonds pour maximiser les rendements sur les investissements. Ils offrent des prêts sur marge, des lignes de crédit et des conseils financiers pour optimiser les portefeuilles d'investissement.</html>");
        description.setFont(new Font("Arial", Font.PLAIN, 12));
        description.setAlignmentX(CENTER_ALIGNMENT);
        description.setForeground(new Color(0x5B5B5B));
        description.setPreferredSize(new Dimension(400, 200));

        JLabel numeroContact = new JLabel("Numéro téléphone : 450-555-6655");
        numeroContact.setAlignmentX(CENTER_ALIGNMENT);
        numeroContact.setForeground(new Color(22, 197, 144));

        JButton loginButton = new JButton("Connexion");
        JButton registerButton = new JButton("Inscription");
        JButton statutButton = new JButton("Statut du Financement");

        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);
        buttonPanel.setOpaque(false);
        buttonPanel.setMaximumSize(new Dimension(200, 50));

        gbc.gridx = 0;
        gbc.gridy = 0;
        centerPanel.add(description, gbc);
        gbc.gridy = 1;
        centerPanel.add(numeroContact, gbc);
        gbc.gridy = 2;
        centerPanel.add(buttonPanel, gbc);
        gbc.gridy = 3;
        centerPanel.add(statutButton, gbc);
        gbc.gridy = 4;
        gbc.weighty = 1.0;
        centerPanel.add(Box.createVerticalGlue(), gbc);
        centerPanel.setOpaque(false);

        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        cardPanel.add(mainPanel, "Main");
        cardPanel.add(loginView, "Login");
        cardPanel.add(registerView, "Register");
        cardPanel.add(formView, "Formulaire");
        cardPanel.add(statutView, "Statut");
        cardPanel.add(investorAccountView, "InvestorAccount");
        cardPanel.add(depositView, "Deposit");
        cardPanel.add(withdrawlView, "Withdrawl");

        add(cardPanel);

        registerView.addEnregistrerListener(e ->{
            pack();
            setSize(800, getHeight()+25);
        });
        registerView.addRetourALaPagePrincipaleListener(e -> {
            setSize(600, 500);
            cardLayout.show(cardPanel, "Main");
        });
        loginView.addRetourALaPagePrincipaleListener(e -> {
            cardLayout.show(cardPanel, "Main");
        });
        formView.addRetourALaPagePrincipaleListener(e -> {
            cardLayout.show(cardPanel, "Main");
        });
        statutView.addRetourALaPagePrincipaleListener(e -> {
            cardLayout.show(cardPanel, "Main");
        });
        investorAccountView.addRetourALaPagePrincipaleListener(e -> {
            setSize(600, 500);
            cardLayout.show(cardPanel, "Main");
        });
        depositView.addRetourALaPageInvestorAccountListener(e -> {
            setSize(600, 600);
            iaController.loadOrCreateInvestorAccount(investorAccountView, investor, investorForm);
            cardLayout.show(cardPanel, "InvestorAccount");
        });
        withdrawlView.addRetourALaPageInvestorAccountListener(e -> {
            setSize(600, 600);
            iaController.loadOrCreateInvestorAccount(investorAccountView, investor, investorForm);
            cardLayout.show(cardPanel, "InvestorAccount");
        });

        loginButton.addActionListener(e -> cardLayout.show(cardPanel, "Login"));

        registerButton.addActionListener(e -> {
            pack();
            setSize(800, getHeight()+25);
            cardLayout.show(cardPanel, "Register");
        });

        irController = new InvestorRegisterController(investor, registerView);
        crController = new ClientRegisterController(client, registerView);
        ltaController = new LoginToAccountController(client,investor,loginView);
        ffrController = new FinancingFormRegisterController(client,financingForm,formView);
        sfController = new StatutFormController();
        iaController = new InvestorAccountController();
        dController = new DepositController(depositView, investorForm);
        wController = new WithdrawlController(withdrawlView, investorForm);

        investorAccountView.addInvestirListener(e -> {
            dController.loadDepositInfo(depositView, investor);
            cardLayout.show(cardPanel, "Deposit");
        });
        investorAccountView.addRetirerListener(e -> {
            wController.loadSolde(withdrawlView, investorForm);
            cardLayout.show(cardPanel, "Withdrawl");
        });
        loginView.addLoginListener(e -> {
            if (ltaController.getAccountTypeConnected().equals("client"))
                cardLayout.show(cardPanel, "Formulaire");
            if (ltaController.getAccountTypeConnected().equals("investor")) {
                setSize(600, 600);
                iaController.loadOrCreateInvestorAccount(investorAccountView, investor, investorForm);
                cardLayout.show(cardPanel, "InvestorAccount");
            }
        }, 1);
        withdrawlView.addRetirerListener(e -> {
            setSize(600, 600);
            iaController.loadOrCreateInvestorAccount(investorAccountView, investor, investorForm);
            cardLayout.show(cardPanel, "InvestorAccount");
        },1);
        depositView.addInvestirListener(e -> {
            setSize(600, 600);
            iaController.loadOrCreateInvestorAccount(investorAccountView, investor, investorForm);
            cardLayout.show(cardPanel, "InvestorAccount");
        },1);

        statutButton.addActionListener(e -> {
            if(sfController.loadFormStatus(client, statutView)) {
                cardLayout.show(cardPanel, "Statut");
            }
        });

    }

    public static void main(String[] args) {
        PostgresSQLConfig.initializeDatabase();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Main frame = new Main();
                frame.setVisible(true);
            }
        });
    }
}
