import config.PostgresSQLConfig;
import controller.*;
import model.Client;
import model.FinancingForm;
import model.Investor;
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
    private Client client = new Client();
    private Investor investor = new Investor();
    private FinancingForm financingForm = new FinancingForm();
    private ClientRegisterController crController;
    private InvestorRegisterController irController;
    private LoginToAccountController ltaController;
    private FinancingFormRegisterController ffrController;
    private StatutFormController sfController;

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
        gbc.gridy = 4; // Adjust as needed
        gbc.weighty = 1.0; // Add vertical space
        centerPanel.add(Box.createVerticalGlue(), gbc);
        centerPanel.setOpaque(false);

        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        cardPanel.add(mainPanel, "Main");
        cardPanel.add(loginView, "Login");
        cardPanel.add(registerView, "Register");
        cardPanel.add(formView, "Formulaire");
        cardPanel.add(statutView, "Statut");

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
            setSize(600, 500);
            cardLayout.show(cardPanel, "Main");
        });
        formView.addRetourALaPagePrincipaleListener(e -> {
            setSize(600, 500);
            cardLayout.show(cardPanel, "Main");
        });
        statutView.addRetourALaPagePrincipaleListener(e -> {
            setSize(600, 500);
            cardLayout.show(cardPanel, "Main");
        });

        loginButton.addActionListener(e -> cardLayout.show(cardPanel, "Login"));

        registerButton.addActionListener(e -> {
            pack();
            setSize(800, getHeight()+25);
            cardLayout.show(cardPanel, "Register");
        });

        irController = new InvestorRegisterController(investor, registerView);
        crController = new ClientRegisterController(client, registerView);
        ltaController = new LoginToAccountController(client,investor,cardPanel,cardLayout,loginView);
        ffrController = new FinancingFormRegisterController(client,financingForm,formView);
        sfController = new StatutFormController();

        statutButton.addActionListener(e -> {
            sfController.loadFormStatus(client, statutView);
            cardLayout.show(cardPanel, "Statut");
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
