import DAO.PersonDAO;
import DAO.PersonDAOImpl;
import config.PostgresSQLConfig;
import controller.ClientRegisterController;
import controller.InvestorRegisterController;
import model.Client;
import model.Investor;
import model.Person;
import view.LoginView;
import view.PictureView;
import view.RegisterView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {
    private CardLayout cardLayout = new CardLayout();
    private JPanel cardPanel = new JPanel(cardLayout);
    private JPanel buttonPanel = new JPanel(new FlowLayout());
    private JPanel centerPanel = new JPanel();
    private PictureView mainPanel = new PictureView("src/view/Voiture.jpg");
    private LoginView loginView = new LoginView();
    private RegisterView registerView = new RegisterView();
    private Client client = new Client();
    private Investor investor = new Investor();
    private ClientRegisterController crController;
    private InvestorRegisterController irController;

    public Main() {
        setTitle("Financement Automobile XYZ");
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel description = new JLabel("<html><br><br><br><br>Financement Automobile XYZ offrent des solutions personnalisées pour l'achat de véhicules neufs ou d'occasion. Ils comprennent des prêts, des options de location et des plans de paiement flexibles pour répondre aux besoins financiers individuels des clients.<br><br> Financement Automobile XYZ fournissent aussi des solutions de prêt et de gestion de fonds pour maximiser les rendements sur les investissements. Ils offrent des prêts sur marge, des lignes de crédit et des conseils financiers pour optimiser les portefeuilles d'investissement.</html>");
        description.setFont(new Font("Arial", Font.PLAIN, 12));
        description.setAlignmentX(CENTER_ALIGNMENT);
        description.setForeground(new Color(0x5B5B5B));

        JLabel numeroContact = new JLabel("Numéro téléphone : 450-555-6655");
        numeroContact.setAlignmentX(CENTER_ALIGNMENT);
        numeroContact.setForeground(new Color(0x5B5B5B));

        JButton loginButton = new JButton("Connexion");
        JButton registerButton = new JButton("Inscription");

        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);
        buttonPanel.setOpaque(false);

        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.add(description);
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(numeroContact);
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(buttonPanel);
        centerPanel.setOpaque(false);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20)); // Add spacing

        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        cardPanel.add(mainPanel, "Main");
        cardPanel.add(loginView, "Login");
        cardPanel.add(registerView, "Register");

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
            setSize(600, 400);
            cardLayout.show(cardPanel, "Main");
        });
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Login");
            }
        });

        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pack();
                setSize(800, getHeight()+25);
                cardLayout.show(cardPanel, "Register");
            }
        });

        irController = new InvestorRegisterController(investor, registerView);
        crController = new ClientRegisterController(client, registerView);

    }

    public static void main(String[] args) {
        PostgresSQLConfig.initializeDatabase();
        PersonDAO personDAO = new PersonDAOImpl();
        Person newPerson = new Person("John Doe", 30);
        Person newPerson1 = new Person("Eric", 25);
        Person newPerson2 = new Person("Loko", 10);
        Person newPerson3 = new Person("Doe", 20);
        Person newPerson4 = new Person("johnny", 22);
        Person newPerson5 = new Person("beatrice", 36);
        Person newPerson6 = new Person("parker", 19);


        personDAO.addPerson(newPerson);
        personDAO.addPerson(newPerson1);
        personDAO.addPerson(newPerson2);
        personDAO.addPerson(newPerson3);
        personDAO.addPerson(newPerson4);
        personDAO.addPerson(newPerson5);
        personDAO.addPerson(newPerson6);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Main frame = new Main();
                frame.setVisible(true);
            }
        });
    }
}
