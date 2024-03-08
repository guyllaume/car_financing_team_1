package controller;

import DAO.ClientDAOImpl;
import DAO.InvestorDAOImpl;
import DAO.UserDAOImpl;
import model.Client;
import model.Investor;
import model.User;
import view.LoginView;
import view.RegisterView;

import javax.swing.*;
import java.awt.*;

public class LoginToAccountController {

    private Client client;
    private Investor investor;
    private InvestorDAOImpl investorDAO;
    private ClientDAOImpl clientDAO;
    public LoginToAccountController(Client client, Investor investor, JPanel cardPanel, CardLayout cardLayout, LoginView lv){
        lv.addLoginListener(e -> {
            if (checkIfValidEmail(lv.getUsername())) {
                if (lv.getSelectedAccountType().equals("Client")) {
                    clientDAO = new ClientDAOImpl();
                    this.client = clientDAO.getClientByEmail(lv.getUsername());
                    if (this.client != null) {
                        if (this.client.verifyPassword(lv.getPassword())) {
                            client.setId(this.client.getId());
                            client.setClientId(this.client.getClientId());
                            client.setCredit(this.client.getCredit());
                            client.setDateCanada(this.client.getDateCanada());
                            client.setInfoEmploi(this.client.getInfoEmploi());
                            client.setDateNaissance(this.client.getDateNaissance());
                            client.setStatutMarital(this.client.getStatutMarital());
                            client.setRevenuAnnuel(this.client.getRevenuAnnuel());
                            client.setNomComplet(this.client.getNomComplet());
                            client.setEmail(this.client.getEmail());
                            client.setPassword(this.client.getPassword());
                            client.setSalt(this.client.getSalt());
                            client.setTelephone(this.client.getTelephone());
                            lv.showErrorMessage("Login Succesfull!!");
                            cardLayout.show(cardPanel, "Formulaire");
                        } else {
                            lv.showErrorMessage("The password doesn't match the one on the account");
                        }
                    } else {
                        lv.showErrorMessage("The account entered doesn't exist");
                    }
                }else{
                    investorDAO = new InvestorDAOImpl();
                    this.investor = investorDAO.getInvestorByEmail(lv.getUsername());
                    if (this.investor != null) {
                        if (this.investor.verifyPassword(lv.getPassword())) {
                            investor.setId(this.investor.getId());
                            investor.setInvestorId(this.investor.getInvestorId());
                            investor.setRisque(this.investor.getRisque());
                            investor.setEducation(this.investor.getEducation());
                            investor.setNomBanque(this.investor.getNomBanque());
                            investor.setDetailBancaire(this.investor.getDetailBancaire());
                            investor.setNomComplet(this.investor.getNomComplet());
                            investor.setEmail(this.investor.getEmail());
                            investor.setPassword(this.investor.getPassword());
                            investor.setSalt(this.investor.getSalt());
                            investor.setTelephone(this.investor.getTelephone());
                            lv.showErrorMessage("Login Succesfull!!");
                            cardLayout.show(cardPanel, "Formulaire");
                        } else {
                            lv.showErrorMessage("The password doesn't match the one on the account");
                        }
                    } else {
                        lv.showErrorMessage("The account entered doesn't exist");
                    }
                }
            }else{
                lv.showErrorMessage("The email entered is not valid");
            }
        });

    }

    private boolean checkIfValidEmail(String email){
        return email.contains("@") && (email.contains(".com") || email.contains(".ca"));
    }
}
