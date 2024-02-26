package view;

import error.NotSelectedOption;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RegisterView extends PictureView {
    private String userSelected;
    private JPanel mainPanel;
    private JPanel northPanel;
    private JPanel westUserPanel;
    private JPanel eastClientPanel;
    private JPanel eastInvestorPanel;
    private CardLayout eastCards;
    private JPanel eastPanel;
    private JPanel southPanel;
    private JPanel southPanelButtons;
    private RetourALaPagePrincipale retourALaPagePrincipaleListener;
    private JButton btnRetour;
    private JButton btnEnregistrer;
    private JLabel title;
    private JComboBox<String> userTypeJCB;
    private JTextField nomComplet;
    private JTextField email;
    private JTextField mdp;
    private JTextField confirmationMdp;
    private JTextField telephone;
    private JComboBox<Integer> dayComboBox;
    private JComboBox<String> monthComboBox;
    private JComboBox<Integer> yearComboBox;
    private ButtonGroup buttonGroupMarital;
    private JTextArea detailEmploiTA;
    private JTextField revenuAnnuel;
    private JTextField noteCredit;
    private JTextField dureeCanada;
    private ButtonGroup buttonGroupRisque;
    private ButtonGroup buttonGroupEducation;
    private JTextArea detailBancaireTA;
    private JTextField nomBanque;
    private JLabel registeredMessage;
    private JRadioButton optionCelibataire;
    private JRadioButton optionMarie;
    private JRadioButton optionPeuRisque;
    private JRadioButton optionRisque;
    private JRadioButton optionTresRisque;
    private JRadioButton optionPeu;
    private JRadioButton optionMoyen;
    private JRadioButton optionBeaucoup;
    private JPanel niveauRisquePanel;
    private JPanel niveauEducPanel;
    private JPanel statutMaritalPanel;
    public RegisterView() {
        super("src/view/Voiture.jpg");
        //Declaration des Panels parents pour organiser les composants
        mainPanel = new JPanel();
        northPanel = new JPanel();
        westUserPanel = new JPanel();
        eastClientPanel = new JPanel();
        eastInvestorPanel = new JPanel();
        eastCards = new CardLayout();
        eastPanel = new JPanel(eastCards);
        southPanel = new JPanel();
        southPanelButtons = new JPanel();


        //Initialisation des LayoutManager pour chaque panel
        mainPanel.setLayout(new BorderLayout());
        northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.Y_AXIS));
        eastClientPanel.setLayout(new BoxLayout(eastClientPanel, BoxLayout.Y_AXIS));
        eastInvestorPanel.setLayout(new BoxLayout(eastInvestorPanel, BoxLayout.Y_AXIS));
        westUserPanel.setLayout(new BoxLayout(westUserPanel, BoxLayout.Y_AXIS));
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.Y_AXIS));
        southPanelButtons.setLayout(new FlowLayout(FlowLayout.CENTER));

        //Adding empty JPanel for the cards as the empty JComboOption
        eastPanel.add(new JPanel(), "Empty");

        // Ajout des composants de northPanel
        title = new JLabel("Page d'Inscription");
        title.setAlignmentX(CENTER_ALIGNMENT);
        northPanel.add(title);
        userTypeJCB = new JComboBox<String>(new String[]{"", "Client", "Investisseur"});
        northPanel.add(userTypeJCB);
        mainPanel.add(northPanel, BorderLayout.NORTH); // Ajout de northPanel au mainPanel

        //Ajout des composants dans leur Panel Row
        nomComplet = new JTextField(15);
        nomComplet.setBorder(null);
        nomComplet.setPreferredSize(new Dimension(0,22));
        nomComplet.setBackground(new Color(238, 238, 238));
        email = new JTextField(15);
        email.setBorder(null);
        email.setPreferredSize(new Dimension(0,22));
        email.setBackground(new Color(238, 238, 238));
        mdp = new JTextField(15);
        mdp.setBorder(null);
        mdp.setPreferredSize(new Dimension(0,22));
        mdp.setBackground(new Color(238, 238, 238));
        confirmationMdp = new JTextField(15);
        confirmationMdp.setBorder(null);
        confirmationMdp.setPreferredSize(new Dimension(0,22));
        confirmationMdp.setBackground(new Color(238, 238, 238));
        telephone = new JTextField(15);
        telephone.setPreferredSize(new Dimension(0,22));
        telephone.setBorder(null);
        telephone.setBackground(new Color(238, 238, 238));
        List<JPanel> userRows = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            userRows.add(new JPanel(new FlowLayout(FlowLayout.LEFT)));
        }
        userRows.get(0).add(new JLabel("<html><body style='width: 100px'>Nom Complet :</body></html>"));
        userRows.get(0).add(nomComplet);
        userRows.get(1).add(new JLabel("<html><body style='width: 100px'>Email :</body></html>"));
        userRows.get(1).add(email);
        userRows.get(2).add(new JLabel("<html><body style='width: 100px'>Mot de Passe :</body></html>"));
        userRows.get(2).add(mdp);
        userRows.get(3).add(new JLabel("<html><body style='width: 100px'>Confirmation du Mot de Passe :</body></html>"));
        userRows.get(3).add(confirmationMdp);
        userRows.get(4).add(new JLabel("<html><body style='width: 100px'>Telephone :</body></html>"));
        userRows.get(4).add(telephone);

        for(JPanel userRow : userRows){
            westUserPanel.add(userRow);
        }

        mainPanel.add(westUserPanel, BorderLayout.WEST); // Ajout de northPanel au mainPanel

        //Creation de JComboBox pour entrer une date de naissance
        JPanel dateNaissancePanel = new JPanel();
        dateNaissancePanel.setLayout(new FlowLayout());
        dayComboBox = new JComboBox<>();
        monthComboBox = new JComboBox<>();
        yearComboBox = new JComboBox<>();

        for (int i = 1; i <= 31; i++) {
            dayComboBox.addItem(i);
        }

        for (int i = LocalDate.now().getYear(); i >= 1900; i--) {
            yearComboBox.addItem(i);
        }

        String[] months = new String[]{"Janvier", "Février", "Mars", "Avril", "Mai", "Juin",
                "Juillet", "Août", "Septembre", "Octobre", "Novembre", "Décembre"};
        for (String month : months) {
            monthComboBox.addItem(month);
        }

        dateNaissancePanel.add(dayComboBox);
        dateNaissancePanel.add(monthComboBox);
        dateNaissancePanel.add(yearComboBox);

        //Creation des JRadioButtons pour le statut marital
        statutMaritalPanel = new JPanel();
        statutMaritalPanel.setLayout(new FlowLayout());

        optionCelibataire = new JRadioButton("Célibataire");
        optionMarie = new JRadioButton("Marié");

        buttonGroupMarital = new ButtonGroup();
        buttonGroupMarital.add(optionCelibataire);
        buttonGroupMarital.add(optionMarie);

        optionCelibataire.setBackground(new Color(200, 200, 200));
        optionMarie.setBackground(new Color(200, 200, 200));

        statutMaritalPanel.add(optionCelibataire);
        statutMaritalPanel.add(optionMarie);

        statutMaritalPanel.setBackground(new Color(200, 200, 200));

        //Ajout des composants de eastClientPanel

        detailEmploiTA = new JTextArea(2,15);
        detailEmploiTA.setLineWrap(true);
        detailEmploiTA.setWrapStyleWord(true);
        detailEmploiTA.setBackground(new Color(238, 238, 238));
        revenuAnnuel = new JTextField(15);
        revenuAnnuel.setBorder(null);
        revenuAnnuel.setPreferredSize(new Dimension(0,22));
        revenuAnnuel.setBackground(new Color(238, 238, 238));
        noteCredit = new JTextField(15);
        noteCredit.setBorder(null);
        noteCredit.setPreferredSize(new Dimension(0,22));
        noteCredit.setBackground(new Color(238, 238, 238));
        dureeCanada = new JTextField(15);
        dureeCanada.setBorder(null);
        dureeCanada.setPreferredSize(new Dimension(0,22));
        dureeCanada.setBackground(new Color(238, 238, 238));
        List<JPanel> clientRows = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            clientRows.add(new JPanel(new FlowLayout(FlowLayout.LEFT)));
        }
        clientRows.get(0).add(new JLabel("<html><body style='width: 100px'>Informations sur votre emploi :"));
        clientRows.get(0).add(detailEmploiTA);
        clientRows.get(1).add(new JLabel("<html><body style='width: 100px'>Revenu Annuel :"));
        clientRows.get(1).add(revenuAnnuel);
        clientRows.get(2).add(new JLabel("<html><body style='width: 100px'>Note de Crédit :"));
        clientRows.get(2).add(noteCredit);
        clientRows.get(3).add(new JLabel("<html><body style='width: 100px'>Date de naissance :"));
        clientRows.get(3).add(dateNaissancePanel);
        clientRows.get(4).add(new JLabel("<html><body style='width: 100px'>Statut marital :"));
        clientRows.get(4).add(statutMaritalPanel);
        clientRows.get(5).add(new JLabel("<html><body style='width: 100px'>Établi au Canada depuis combien d'année :"));
        clientRows.get(5).add(dureeCanada);

        for(JPanel clientRow : clientRows){
            eastClientPanel.add(clientRow);
        }
        eastPanel.add(eastClientPanel, "Client");

        //Creation des JRadioButtons pour le niveau de risque souhaite
        niveauRisquePanel = new JPanel();
        niveauRisquePanel.setLayout(new FlowLayout());

        optionPeuRisque = new JRadioButton("Peu de risque");
        optionRisque = new JRadioButton("Risqué");
        optionTresRisque = new JRadioButton("Très risqué");

        optionPeuRisque.setBackground(new Color(200, 200, 200));
        optionRisque.setBackground(new Color(200, 200, 200));
        optionTresRisque.setBackground(new Color(200, 200, 200));

        buttonGroupRisque = new ButtonGroup();
        buttonGroupRisque.add(optionPeuRisque);
        buttonGroupRisque.add(optionRisque);
        buttonGroupRisque.add(optionTresRisque);

        niveauRisquePanel.add(optionPeuRisque);
        niveauRisquePanel.add(optionRisque);
        niveauRisquePanel.add(optionTresRisque);

        niveauRisquePanel.setBackground(new Color(200, 200, 200));

        //Creation des JRadioButtons pour le niveau d'éducation
        niveauEducPanel = new JPanel();
        niveauEducPanel.setLayout(new FlowLayout());

        optionPeu = new JRadioButton("Peu");
        optionMoyen = new JRadioButton("Moyen");
        optionBeaucoup = new JRadioButton("Beaucoup");

        optionPeu.setBackground(new Color(200, 200, 200));
        optionMoyen.setBackground(new Color(200, 200, 200));
        optionBeaucoup.setBackground(new Color(200, 200, 200));

        buttonGroupEducation = new ButtonGroup();
        buttonGroupEducation.add(optionPeu);
        buttonGroupEducation.add(optionMoyen);
        buttonGroupEducation.add(optionBeaucoup);

        niveauEducPanel.add(optionPeu);
        niveauEducPanel.add(optionMoyen);
        niveauEducPanel.add(optionBeaucoup);

        niveauEducPanel.setBackground(new Color(200, 200, 200));


        //Ajout des composants de westInvestorPanel

        detailBancaireTA = new JTextArea(2,15);
        detailBancaireTA.setLineWrap(true);
        detailBancaireTA.setWrapStyleWord(true);
        nomBanque = new JTextField(15);
        nomBanque.setBorder(null);
        nomBanque.setPreferredSize(new Dimension(0,22));
        nomBanque.setBackground(new Color(238, 238, 238));
        List<JPanel> investorRows = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            investorRows.add(new JPanel(new FlowLayout(FlowLayout.LEFT)));
        }
        investorRows.get(0).add(new JLabel("<html><body style='width: 100px'>Nom de votre banque :"));
        investorRows.get(0).add(nomBanque);
        investorRows.get(1).add(new JLabel("<html><body style='width: 100px'>Détails bancaires :"));
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

        //Initialisation des messages d'erreur ou de complétion du compte
        registeredMessage = new JLabel();
        registeredMessage.setAlignmentX(CENTER_ALIGNMENT);
        southPanel.add(registeredMessage);

        //Initialisation de bouton retour a la page d'acceuil et enregistrer
        btnRetour = new JButton("Retour à la page d'acceuil");
        btnEnregistrer = new JButton("Créer un compte");

        southPanelButtons.add(btnRetour);
        southPanelButtons.add(btnEnregistrer);

        southPanel.add(southPanelButtons);

        mainPanel.add(southPanel, BorderLayout.SOUTH);

        //Make panels with different Colors
        mainPanel.setBackground(new Color(168, 168, 168, 128));
        setAllComponentChildsAsTransparent(mainPanel);

        add(mainPanel);

        //All ActionListener
        btnRetour.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (retourALaPagePrincipaleListener != null) {
                    retourALaPagePrincipaleListener.retournerAlaPagePrincipale(); // Notify listener
                }
            }
        });
        userTypeJCB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                userSelected = userTypeJCB.getSelectedItem().toString();
                if (userSelected.equals("Client"))
                    eastCards.show(eastPanel, "Client");
                else if(userSelected.equals("Investisseur"))
                    eastCards.show(eastPanel, "Investor");
                else
                    eastCards.show(eastPanel, "Empty");
                revalidate();
                repaint();
            }
        });

    }
    public interface RetourALaPagePrincipale {
        void retournerAlaPagePrincipale();
    }
    public void ajoutDuListener(RetourALaPagePrincipale listener) {
        this.retourALaPagePrincipaleListener = listener;
    }
    public void addEnregistrerListener(ActionListener listener){
        btnEnregistrer.addActionListener(listener);
    }
    private List<String> getUserInfo(){
        List<String> userInfos = new ArrayList<>();
        userInfos.add(nomComplet.getText());
        userInfos.add(email.getText());
        userInfos.add(mdp.getText());
        userInfos.add(confirmationMdp.getText());
        userInfos.add(telephone.getText());
        return userInfos;
    }
    public List<String> getClientInfo() throws NotSelectedOption {
        JRadioButton jrbSelected;
        int month = 0;
        switch (monthComboBox.getSelectedItem().toString()){
            case "Janvier":
                month = 1;
                break;
            case "Février":
                month = 2;
                break;
            case "Mars":
                month = 3;
                break;
            case "Avril":
                month = 4;
                break;
            case "Mai":
                month = 5;
                break;
            case "Juin":
                month = 6;
                break;
            case "Juillet":
                month = 7;
                break;
            case "Août":
                month = 8;
                break;
            case "Septembre":
                month = 9;
                break;
            case "Octobre":
                month = 10;
                break;
            case "Novembre":
                month = 11;
                break;
            case "Décembre":
                month = 12;
                break;
        }
        List<String> clientInfos = new ArrayList<>(getUserInfo());
        clientInfos.add(detailEmploiTA.getText());
        clientInfos.add(revenuAnnuel.getText());
        clientInfos.add(noteCredit.getText());
        clientInfos.add(String.valueOf(LocalDate.of(
                (Integer) yearComboBox.getSelectedItem(),
                month,
                (Integer) dayComboBox.getSelectedItem())));

        if (optionCelibataire.isSelected())
            jrbSelected = optionCelibataire;
        else if (optionMarie.isSelected())
            jrbSelected = optionMarie;
        else
            throw new NotSelectedOption("Veuillez Choisir un statut marital");
        clientInfos.add(jrbSelected.getText());
        clientInfos.add(dureeCanada.getText());
        return clientInfos;
    }

    public List<String> getInvestorInfo() throws NotSelectedOption {
        JRadioButton jrbSelected;
        List<String> investorInfos = new ArrayList<>(getUserInfo());
        investorInfos.add(nomBanque.getText());
        investorInfos.add(detailBancaireTA.getText());

        if (optionPeuRisque.isSelected())
            jrbSelected = optionPeuRisque;
        else if(optionRisque.isSelected())
            jrbSelected = optionRisque;
        else if(optionTresRisque.isSelected())
            jrbSelected = optionTresRisque;
        else
            throw new NotSelectedOption("Veuillez Choisir un niveau de risque");

        investorInfos.add(jrbSelected.getText());

        if (optionPeu.isSelected())
            jrbSelected = optionPeu;
        else if(optionMoyen.isSelected())
            jrbSelected = optionMoyen;
        else if(optionBeaucoup.isSelected())
            jrbSelected = optionBeaucoup;
        else
            throw new NotSelectedOption("Veuillez Choisir un niveau d'éducation financier");
        investorInfos.add(jrbSelected.getText());
        return investorInfos;
    }
    public String getUserType(){
        userSelected = userTypeJCB.getSelectedItem().toString();
        if (userSelected.equals("Client"))
            return "Client";
        else if(userSelected.equals("Investisseur"))
            return "Investisseur";
        else
            return "";
    }

    public void setMessage(String message){
        registeredMessage.setText(message);
    }

    public void setAllComponentChildsAsTransparent(JPanel panel){
        for (Component component : panel.getComponents()){
            try {
                if (component instanceof JPanel) {
                    if(component != niveauRisquePanel && component != niveauEducPanel && component != statutMaritalPanel) { // Add all panels with custom background
                        ((JPanel) component).setOpaque(false);
                        component.setBackground(new Color(0, 0, 0, 0));
                        setAllComponentChildsAsTransparent((JPanel) component);
                    }
                }
            }catch (Exception ignored){}
        }
    }
}
