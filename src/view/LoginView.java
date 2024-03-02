package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class LoginView extends PictureView {
    private JButton btnRetour;
    private JLabel usernameLabel;
    private JTextField usernameField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JPanel loginButtonPanel;
    private JPanel btnRetourPanel;

    public LoginView() {
        super("src/view/Voiture.jpg");
        setLayout(new GridLayout(5, 2));

        usernameLabel = new JLabel("Email:");
        usernameField = new JTextField();
        usernameLabel.setBorder(BorderFactory.createEmptyBorder(0,200,0,0));
        usernameField.setBorder(BorderFactory.createMatteBorder(20,0,20,100, Color.WHITE));
        usernameField.setBackground(Color.LIGHT_GRAY);
        add(usernameLabel);
        add(usernameField);

        passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();
        passwordLabel.setBorder(BorderFactory.createEmptyBorder(0,200,0,0));
        passwordField.setBorder(BorderFactory.createMatteBorder(20,0,20,100, Color.WHITE));
        passwordField.setBackground(Color.LIGHT_GRAY);
        add(passwordLabel);
        add(passwordField);

        loginButton = new JButton("Login");
        loginButton.setPreferredSize(new Dimension(200,30));
        loginButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        loginButtonPanel.setOpaque(false);
        loginButtonPanel.add(loginButton);
        add(loginButtonPanel);

        btnRetour = new JButton("Retour à la page d'accueil");
        btnRetour.setPreferredSize(new Dimension(200,30));
        btnRetourPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnRetourPanel.setOpaque(false);
        btnRetourPanel.add(btnRetour);
        add(btnRetourPanel);

        loginButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Username : " + usernameField.getText() + "\nPassword : " + new String(passwordField.getPassword()));
        });
    }
    public void addRetourALaPagePrincipaleListener(ActionListener listener){
        btnRetour.addActionListener(listener);
    }

    private void infoEnteredToString(){

    }
}
