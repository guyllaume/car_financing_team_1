package controller;

import error.NotSelectedOption;
import model.Client;
import view.RegisterView;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

public class ClientRegisterController {

    public ClientRegisterController(Client client, RegisterView rv){
        rv.addEnregistrerListener(e -> {
            List<String> infoEntered;
            try{
                if(rv.getUserType().equals("Client")) {
                    infoEntered = rv.getClientInfo();
                    if(!checkIfValidEmail(infoEntered.get(1)))
                        throw new RuntimeException("Veuillez entre un email valide");
                    if (!checkIfInfoEnteredUser(infoEntered))
                        throw new RuntimeException("Veuillez Entrer vos informations dans tous les champs");
                    if (!checkIfPasswordRespectsCode(infoEntered.get(2)))
                        throw new RuntimeException("Votre mot de passe doit contenir plus de 7 caractères, \n" +
                                "incluant un caractère spécial, une lettre majuscule, et un chiffre");
                    if (!checkIfPasswordsAreEquals(infoEntered.get(2), infoEntered.get(3)))
                        throw new RuntimeException("Vos mot de passe doit être identique");

                    double castRevenu;
                    int castCredit, castDuree;
                    LocalDate castDateNaissance;
                    try {
                        castRevenu = Double.parseDouble(infoEntered.get(6));
                    } catch (NumberFormatException error) {
                        throw new RuntimeException("Entrer un revenu valide");
                    }
                    try {
                        castCredit = Integer.parseInt(infoEntered.get(7));
                    } catch (NumberFormatException error) {
                        throw new RuntimeException("Entrer un credit valide");
                    }
                    try {
                        castDateNaissance = LocalDate.parse(infoEntered.get(8));
                    } catch (DateTimeParseException error) {
                        throw new RuntimeException("Entrer une date de naissance valide");
                    }
                    try {
                        castDuree = Integer.parseInt(infoEntered.get(10));
                    } catch (NumberFormatException error) {
                        throw new RuntimeException("Entrer une durée(ans) valide");
                    }
                    client.setNomComplet(infoEntered.get(0));
                    client.setEmail(infoEntered.get(1));
                    client.setPassword(infoEntered.get(2));
                    client.setTelephone(infoEntered.get(4));
                    client.setInfoEmploi(infoEntered.get(5));
                    client.setRevenuAnnuel(castRevenu);
                    client.setCredit(castCredit);
                    client.setDateNaissance(castDateNaissance);
                    client.setStatutMarital(infoEntered.get(9));
                    client.setDateCanada(castDuree);

                    rv.setMessage("<html>Votre Inscription c'est fait avec succès!" + client.toString() + "</html>");
                }
            }catch ( NotSelectedOption | RuntimeException error){
                rv.setMessage(error.getMessage());
            }
        });
    }
    private boolean checkIfPasswordRespectsCode(String password){
        if (password.length()<8)
            return false;
        char c;
        boolean isUpperCase = false;
        boolean isDigit = false;
        boolean isSpecial = false;
        for (int i = 0; i < password.length(); i++) {
            c = password.charAt(i);
            if (Character.isUpperCase(c))
                isUpperCase = true;
            if (Character.isDigit(c))
                isDigit = true;
            if (!Character.isLetterOrDigit(c))
                isSpecial = true;
        }
        return isDigit && isSpecial && isUpperCase;
    }
    private boolean checkIfPasswordsAreEquals(String password, String confirmationPassword){
        return password.equals(confirmationPassword);
    }
    private boolean checkIfInfoEnteredUser(List<String> registeredInfos){
        for (String registeredInfo : registeredInfos){
            if(registeredInfo.isEmpty())
                return false;
        }
        return true;
    }
    private boolean checkIfValidEmail(String email){
        return email.contains("@") && (email.contains(".com") || email.contains(".ca"));
    }
    private boolean checkIfValidPhoneNumber(String phone){
        return true;
    }
}