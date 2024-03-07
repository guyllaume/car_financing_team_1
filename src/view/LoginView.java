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
    private JComboBox userTypeJCB;
    private GridBagConstraints gbc;

    public LoginView() {
        super("src/view/Voiture.jpg");
        setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 10, 5);

        usernameLabel = new JLabel("Email:");
        usernameLabel.setForeground(new Color(22, 197, 144));
        usernameField = new JTextField();
        usernameField.setBackground(Color.LIGHT_GRAY);
        usernameField.setPreferredSize(new Dimension(200, usernameField.getPreferredSize().height));
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(usernameLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(usernameField, gbc);

        passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(new Color(22, 197, 144));
        passwordField = new JPasswordField();
        passwordField.setBackground(Color.LIGHT_GRAY);
        passwordField.setPreferredSize(new Dimension(200, passwordField.getPreferredSize().height));
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(passwordLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(passwordField, gbc);

        userTypeJCB = new JComboBox<String>(new String[]{"Client", "Investisseur"});
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2; // Span two columns
        add(userTypeJCB, gbc);
        gbc.gridwidth = 1;

        loginButton = new JButton("Login");
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(loginButton, gbc);


        btnRetour = new JButton("Retour à la page d'accueil");
        btnRetour.setPreferredSize(new Dimension(200, btnRetour.getPreferredSize().height));
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(btnRetour, gbc);
    }

    public void addRetourALaPagePrincipaleListener(ActionListener listener) {
        btnRetour.addActionListener(listener);
    }

    public void addLoginListener(ActionListener listener) {
        loginButton.addActionListener(listener);
    }

    public String getUsername() {
        return usernameField.getText();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }
    public String getSelectedAccountType() {
        return String.valueOf(userTypeJCB.getSelectedItem());
    }

    public void showErrorMessage(String message){
        JOptionPane.showMessageDialog(null,message);
    }
}
