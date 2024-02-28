package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView extends PictureView {
    private RetourALaPagePrincipale retourALaPagePrincipaleListener;
    private JButton btnRetour;
    private JLabel usernameLabel;
    private JTextField usernameField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel retourLabel;

    public LoginView() {
        super("src/view/Voiture.jpg");
        setLayout(new GridLayout(5, 2));

        usernameLabel = new JLabel("Email:");
        usernameField = new JTextField();
        add(usernameLabel);
        add(usernameField);

        passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();
        add(passwordLabel);
        add(passwordField);

        loginButton = new JButton("Login");
        add(loginButton);

        btnRetour = new JButton("Retour à la page d'accueil");
        add(btnRetour);


        btnRetour.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (retourALaPagePrincipaleListener != null) {
                    retourALaPagePrincipaleListener.retournerAlaPagePrincipale(); // Notify listener
                }
            }
        });

        setPreferredSize(new Dimension(800, 400));
    }

    public interface RetourALaPagePrincipale {
        void retournerAlaPagePrincipale();
    }

}
