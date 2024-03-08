package controller;

import DAO.ClientDAOImpl;
import error.NotSelectedOption;
import model.Client;
import view.RegisterView;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

public class ClientRegisterController {

    private ClientDAOImpl clientDao;
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
                    if(!checkIfValidPhoneNumber(infoEntered.get(4)))
                        throw new RuntimeException("Veuillez entre un numero de telephone valide");

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
                    client.setNomComplet(infoEntered.get(0).trim());
                    client.setEmail(infoEntered.get(1).trim());
                    client.setPassword(infoEntered.get(2).trim());
                    client.setTelephone(changeTelephoneToNumbersOnly(infoEntered.get(4).trim()));
                    client.setInfoEmploi(infoEntered.get(5).trim());
                    client.setRevenuAnnuel(castRevenu);
                    client.setCredit(castCredit);
                    client.setDateNaissance(castDateNaissance);
                    client.setStatutMarital(infoEntered.get(9).trim());
                    client.setDateCanada(castDuree);
                    client.createSaltAndHash();
                    clientDao = new ClientDAOImpl();
                    clientDao.addClient(client);


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
        return changeTelephoneToNumbersOnly(phone).length() == 10;
    }
    private String changeTelephoneToNumbersOnly(String phone){return phone.replaceAll("\\D", "");}
}
