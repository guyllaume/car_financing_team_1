package controller;

import DAO.ClientDAOImpl;
import DAO.InvestorDAOImpl;
import error.NotSelectedOption;
import model.Investor;
import view.RegisterView;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

public class InvestorRegisterController {
    private InvestorDAOImpl investorDAO;

    public InvestorRegisterController(Investor investor, RegisterView rv){
        rv.addEnregistrerListener(e -> {
            List<String> infoEntered;
            try{
                if(rv.getUserType().equals("Investisseur")) {
                    infoEntered = rv.getInvestorInfo();
                    if (!checkIfInfoEnteredUser(infoEntered))
                        throw new RuntimeException("Enter information in all fields");
                    if(!checkIfValidEmail(infoEntered.get(1)))
                        throw new RuntimeException("Veuillez entre un email valide");
                    if (!checkIfPasswordRespectsCode(infoEntered.get(2)))
                        throw new RuntimeException("Votre mot de passe doit contenir plus de 7 caractères, \n" +
                                "incluant un caractère spécial, une lettre majuscule, et un chiffre");
                    if (!checkIfPasswordsAreEquals(infoEntered.get(2), infoEntered.get(3)))
                        throw new RuntimeException("Vos mot de passe doit être identique");
                    if(!checkIfValidPhoneNumber(infoEntered.get(4)))
                        throw new RuntimeException("Veuillez entre un numero de telephone valide");

                    int castNbInstitution, castNbTransit, castNbCompte;
                    try {
                        castNbInstitution = Integer.parseInt(infoEntered.get(6));
                    } catch (NumberFormatException error) {
                        throw new RuntimeException("Entrer un numero d'institution valide");
                    }
                    try {
                        castNbTransit = Integer.parseInt(infoEntered.get(7));
                    } catch (NumberFormatException error) {
                        throw new RuntimeException("Entrer un numero de transit valide");
                    }
                    try {
                        castNbCompte = Integer.parseInt(infoEntered.get(8));
                    } catch (NumberFormatException error) {
                        throw new RuntimeException("Entrer un numero de compte valide");
                    }
                    if(String.valueOf(castNbInstitution).length() != 3)
                        throw new RuntimeException("Veuillez entrer un numero d'institution valide (xxx)");
                    if(String.valueOf(castNbTransit).length() != 5)
                        throw new RuntimeException("Veuillez entrer un numero de transit valide (xxxxx)");
                    if(String.valueOf(castNbCompte).length() > 11 || castNbCompte == 0)
                        throw new RuntimeException("Veuillez entrer un numero de compte valide (entre 1 et 11 chiffre)");

                    investorDAO = new InvestorDAOImpl();
                    if (investorDAO.getInvestorByEmail(infoEntered.get(1).trim()) != null)
                        throw new RuntimeException("Ce compte existe deja");
                    investor.setNomComplet(infoEntered.get(0).trim());
                    investor.setEmail(infoEntered.get(1).trim());
                    investor.setPassword(infoEntered.get(2).trim());
                    investor.setTelephone(changeTelephoneToNumbersOnly(infoEntered.get(4).trim()));
                    investor.setNomBanque(infoEntered.get(5).trim());
                    investor.setNbInstitution(castNbInstitution);
                    investor.setNbTransit(castNbTransit);
                    investor.setNbCompte(castNbCompte);
                    investor.setRisque(infoEntered.get(9).trim());
                    investor.setEducation(infoEntered.get(10).trim());
                    investor.createSaltAndHash();
                    investorDAO.addInvestor(investor);

                    rv.setMessage("<html>Votre Inscription c'est fait avec succès!" + investor.toString() + "</html>");
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
