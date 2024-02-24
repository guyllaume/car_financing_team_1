package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RegisterView extends JPanel {
    private String userSelected;
    private JPanel mainPanel;
    private JPanel northPanel;
    private JPanel westUserPanel;
    private JPanel eastClientPanel;
    private JPanel eastInvestorPanel;
    private CardLayout eastCards;
    private JPanel eastPanel;
    public RegisterView() {
        //Declaration des Panels parents pour organiser les composants
        mainPanel = new JPanel();
        northPanel = new JPanel();
        westUserPanel = new JPanel();
        eastClientPanel = new JPanel();
        eastInvestorPanel = new JPanel();
        eastCards = new CardLayout();
        eastPanel = new JPanel(eastCards);

        //Initialisation des LayoutManager pour chaque panel
        mainPanel.setLayout(new BorderLayout());
        northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.Y_AXIS));
        eastClientPanel.setLayout(new BoxLayout(eastClientPanel, BoxLayout.Y_AXIS));
        eastInvestorPanel.setLayout(new BoxLayout(eastInvestorPanel, BoxLayout.Y_AXIS));
        westUserPanel.setLayout(new BoxLayout(westUserPanel, BoxLayout.Y_AXIS));

        //Adding empty JPanel for the cards as the empty JComboOption
        eastPanel.add(new JPanel(), "Empty");

        // Ajout des composants de northPanel
        northPanel.add(new JLabel("Page d'Inscription"));
        JComboBox<String> userTypeJCB = new JComboBox<String>(new String[]{"", "Client", "Investisseur"});
        northPanel.add(userTypeJCB);
        mainPanel.add(northPanel, BorderLayout.NORTH); // Ajout de northPanel au mainPanel

        //Ajout des composants dans leur Panel Row
        List<JPanel> userRows = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            userRows.add(new JPanel(new FlowLayout(FlowLayout.LEFT)));
        }
        userRows.get(0).add(new JLabel("<html><body style='width: 80px'>Nom Complet :</body></html>"));
        userRows.get(0).add(new JTextField(15));
        userRows.get(1).add(new JLabel("<html><body style='width: 80px'>Email :</body></html>"));
        userRows.get(1).add(new JTextField(15));
        userRows.get(2).add(new JLabel("<html><body style='width: 80px'>Mot de Passe :</body></html>"));
        userRows.get(2).add(new JTextField(15));
        userRows.get(3).add(new JLabel("<html><body style='width: 80px'>Confirmation du Mot de Passe :</body></html>"));
        userRows.get(3).add(new JTextField(15));
        userRows.get(4).add(new JLabel("<html><body style='width: 80px'>Telephone :</body></html>"));
        userRows.get(4).add(new JTextField(15));

        for(JPanel userRow : userRows){
            westUserPanel.add(userRow);
        }

        mainPanel.add(westUserPanel, BorderLayout.WEST); // Ajout de northPanel au mainPanel

        //Creation de JComboBox pour entrer une date de naissance
        JPanel dateNaissancePanel = new JPanel();
        dateNaissancePanel.setLayout(new FlowLayout());
        JComboBox<Integer> dayComboBox = new JComboBox<>();
        JComboBox<String> monthComboBox = new JComboBox<>();
        JComboBox<Integer> yearComboBox = new JComboBox<>();

        for (int i = 1; i <= 31; i++) {
            dayComboBox.addItem(i);
        }

        for (int i = LocalDate.now().getYear(); i >= 1900; i--) {
            yearComboBox.addItem(i);
        }

        String[] months = new String[]{"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        for (String month : months) {
            monthComboBox.addItem(month);
        }

        dateNaissancePanel.add(dayComboBox);
        dateNaissancePanel.add(monthComboBox);
        dateNaissancePanel.add(yearComboBox);

        //Creation des JRadioButtons pour le statut marital
        JPanel statutMaritalPanel = new JPanel();
        statutMaritalPanel.setLayout(new FlowLayout());

        JRadioButton optionCelibataire = new JRadioButton("Célibataire");
        JRadioButton optionMarie = new JRadioButton("Marié");

        ButtonGroup buttonGroupMarital = new ButtonGroup();
        buttonGroupMarital.add(optionCelibataire);
        buttonGroupMarital.add(optionMarie);

        statutMaritalPanel.add(optionCelibataire);
        statutMaritalPanel.add(optionMarie);

        //Ajout des composants de eastClientPanel

        List<JPanel> clientRows = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            clientRows.add(new JPanel(new FlowLayout(FlowLayout.LEFT)));
        }
        clientRows.get(0).add(new JLabel("<html><body style='width: 150px'>Informations sur votre emploi :"));
        JTextArea detailEmploiTA = new JTextArea(2,15);
        detailEmploiTA.setLineWrap(true);
        detailEmploiTA.setWrapStyleWord(true);
        clientRows.get(0).add(detailEmploiTA);
        clientRows.get(1).add(new JLabel("<html><body style='width: 150px'>Revenu Annuel :"));
        clientRows.get(1).add(new JTextField(15));
        clientRows.get(2).add(new JLabel("<html><body style='width: 150px'>Note de Crédit :"));
        clientRows.get(2).add(new JTextField(15));
        clientRows.get(3).add(new JLabel("<html><body style='width: 150px'>Date de naissance :"));
        clientRows.get(3).add(dateNaissancePanel);
        clientRows.get(4).add(new JLabel("<html><body style='width: 150px'>Statut marital :"));
        clientRows.get(4).add(statutMaritalPanel);
        clientRows.get(5).add(new JLabel("<html><body style='width: 150px'>Établi au Canada depuis combien d'année :"));
        clientRows.get(5).add(new JTextField(15));

        for(JPanel clientRow : clientRows){
            eastClientPanel.add(clientRow);
        }
        eastPanel.add(eastClientPanel, "Client");

        //Creation des JRadioButtons pour le niveau de risque souhaite
        JPanel niveauRisquePanel = new JPanel();
        niveauRisquePanel.setLayout(new FlowLayout());

        JRadioButton optionPeuRisque = new JRadioButton("Peu de risque");
        JRadioButton optionRisque = new JRadioButton("Risqué");
        JRadioButton optionTresRisque = new JRadioButton("Très risqué");

        ButtonGroup buttonGroupRisque = new ButtonGroup();
        buttonGroupRisque.add(optionPeuRisque);
        buttonGroupRisque.add(optionRisque);
        buttonGroupRisque.add(optionTresRisque);

        niveauRisquePanel.add(optionPeuRisque);
        niveauRisquePanel.add(optionRisque);
        niveauRisquePanel.add(optionTresRisque);

        //Creation des JRadioButtons pour le niveau d'éducation
        JPanel niveauEducPanel = new JPanel();
        niveauEducPanel.setLayout(new FlowLayout());

        JRadioButton optionPeu = new JRadioButton("Peu");
        JRadioButton optionMoyen = new JRadioButton("Moyen");
        JRadioButton optionBeaucoup = new JRadioButton("Beaucoup");

        ButtonGroup buttonGroupEducation = new ButtonGroup();
        buttonGroupRisque.add(optionPeu);
        buttonGroupRisque.add(optionMoyen);
        buttonGroupRisque.add(optionBeaucoup);

        niveauEducPanel.add(optionPeu);
        niveauEducPanel.add(optionMoyen);
        niveauEducPanel.add(optionBeaucoup);


        //Ajout des composants de westInvestorPanel

        List<JPanel> investorRows = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            investorRows.add(new JPanel(new FlowLayout(FlowLayout.LEFT)));
        }
        investorRows.get(0).add(new JLabel("<html><body style='width: 100px'>Nom de votre banque :"));
        investorRows.get(0).add(new JTextField(15));
        investorRows.get(1).add(new JLabel("<html><body style='width: 100px'>Détails bancaires :"));
        JTextArea detailBancaireTA = new JTextArea(2,15);
        detailBancaireTA.setLineWrap(true);
        detailBancaireTA.setWrapStyleWord(true);
        investorRows.get(1).add(detailBancaireTA);
        investorRows.get(2).add(new JLabel("<html><body style='width: 100px'>Niveau de risque souhaité :"));
        investorRows.get(2).add(niveauRisquePanel);
        investorRows.get(3).add(new JLabel("<html><body style='width: 100px'>Niveau d'éducation en invesstissement :"));
        investorRows.get(3).add(niveauEducPanel);

        for(JPanel investortRow : investorRows){
            eastInvestorPanel.add(investortRow);
        }
        eastPanel.add(eastInvestorPanel, "Investor");


        mainPanel.add(eastPanel, BorderLayout.EAST);
        add(mainPanel);


        userTypeJCB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                userSelected = userTypeJCB.getSelectedItem().toString();
                if (userSelected.equals("Client"))
                    eastCards.show(eastPanel, "Client");
                else if(userSelected.equals("Investisseur"))
                    eastCards.show(eastPanel, "Investor");
                else
                    eastCards.show(eastPanel, "Empty");
            }
        });

    }
}
