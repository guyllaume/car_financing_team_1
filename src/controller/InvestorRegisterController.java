package controller;

import error.NotSelectedOption;
import model.Investor;
import view.RegisterView;

import java.util.List;

public class InvestorRegisterController {

    public InvestorRegisterController(Investor investor, RegisterView rv){
        rv.addEnregistrerListener(e -> {
            List<String> infoEntered;
            try{
                if(rv.getUserType().equals("Investisseur")) {
                    infoEntered = rv.getInvestorInfo();
                    if (!checkIfInfoEnteredUser(infoEntered))
                        throw new RuntimeException("Enter information in all fields");
                    if (!checkIfPasswordRespectsCode(infoEntered.get(2)))
                        throw new RuntimeException("Votre mot de passe doit contenir plus de 7 caractères, \n" +
                                "incluant un caractère spécial, une lettre majuscule, et un chiffre");
                    if (!checkIfPasswordsAreEquals(infoEntered.get(2), infoEntered.get(3)))
                        throw new RuntimeException("Vos mot de passe doit être identique");

                    investor.setNomComplet(infoEntered.get(0));
                    investor.setEmail(infoEntered.get(1));
                    investor.setPassword(infoEntered.get(2));
                    investor.setTelephone(infoEntered.get(4));
                    investor.setNomBanque(infoEntered.get(5));
                    investor.setDetailBancaire(infoEntered.get(6));
                    investor.setRisque(infoEntered.get(7));
                    investor.setEducation(infoEntered.get(8));

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
}
