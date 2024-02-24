import view.LoginView;
import view.RegisterView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {
    private CardLayout cardLayout = new CardLayout();
    private JPanel cardPanel = new JPanel(cardLayout);
    private JPanel mainPanel = new JPanel(new FlowLayout());
    private LoginView loginView = new LoginView();
    private RegisterView registerView = new RegisterView();

    public Main() {
        setTitle("Financement Automobile XYZ");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JButton loginButton = new JButton("Connexion");
        JButton registerButton = new JButton("Inscription");

        mainPanel.add(loginButton);
        mainPanel.add(registerButton);

        cardPanel.add(mainPanel, "Main");
        cardPanel.add(loginView, "Login");
        cardPanel.add(registerView, "Register");

        add(cardPanel);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Login");
            }
        });

        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setSize(800, 500);
                cardLayout.show(cardPanel, "Register");
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Main frame = new Main();
                frame.setVisible(true);
            }
        });
    }
}
