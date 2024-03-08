package controller;

import DAO.FinancingFormDAO;
import model.Client;
import model.FinancingForm;
import view.FormView;

public class FinancingFormRegisterController {
    private FinancingFormDAO financingFormDAO;

    public FinancingFormRegisterController(Client client, FinancingForm financingForm, FormView fv) {
        fv.addEnregistrerListener(e -> {
            try {
                if (!checkIfAllInfoEntered(fv))
                    throw new RuntimeException("Veuillez entrer des informations dans tous les champs");
                double castMontantPret;
                int castKm, castAnnee, castDureePret;
                try {
                    castMontantPret = Double.parseDouble(fv.getMontantPretField());
                } catch (NumberFormatException error) {
                    throw new RuntimeException("Entrer un montant de pret valide");
                }
                try {
                    castKm = Integer.parseInt(fv.getKmField());
                } catch (NumberFormatException error) {
                    throw new RuntimeException("Entrer un kilometrage valide");
                }
                try {
                    castAnnee = Integer.parseInt(fv.getAnneeField());
                } catch (NumberFormatException error) {
                    throw new RuntimeException("Entrer une annee de vehicule valide");
                }
                try {
                    castDureePret = Integer.parseInt(fv.getDureePretJCB());
                } catch (NumberFormatException error) {
                    throw new RuntimeException("Entrer une duree de pret valide");
                }
                if (!checkIfValidVin(fv.getVINField()))
                    throw new RuntimeException("Veuillez entrer un VIN valide (17 caractere)");
                if (!checkIfMontantPretIsValid(castMontantPret))
                    throw new RuntimeException("Le pret ne peut pas depasser 60 000");
                if (!checkIfValidkmAmount(castKm))
                    throw new RuntimeException("Le kilometrage de votre vehicule ne doit pas exceder 230 000");
                financingForm.setMarque(fv.getMarque());
                financingForm.setModele(fv.getModeleField());
                financingForm.setAnnee(castAnnee);
                financingForm.setKm(castKm);
                financingForm.setVIN(fv.getVINField());
                financingForm.setMontantPret(castMontantPret);
                financingForm.setDureePret(castDureePret);
                financingForm.setUserId(client.getId());
                financingFormDAO = new FinancingFormDAO();
                financingFormDAO.addFinancingForm(financingForm);

                fv.showErrorMessage("<html>Votre Formualire de financement c'est fait avec succès!</html>");

            } catch ( RuntimeException error) {
                fv.showErrorMessage(error.getMessage());
            }
        });
    }

    private boolean checkIfMontantPretIsValid(double montant) {
        return montant <= 60000 && montant > 0;
    }

    private boolean checkIfValidVin(String VIN) {
        VIN = VIN.replaceAll("\\s", "");
        return VIN.length() == 17 && VIN.matches("[a-zA-Z0-9]+");
    }

    private boolean checkIfValidkmAmount(int km) {
        return km >= 0 && km <= 230000;
    }

    private boolean checkIfAllInfoEntered(FormView fv) {
        return !fv.getMarque().isEmpty() && !fv.getDureePretJCB().isEmpty() && !fv.getKmField().isEmpty() && !fv.getAnneeField().isEmpty() && !fv.getModeleField().isEmpty() && !fv.getVINField().isEmpty() && !fv.getMontantPretField().isEmpty();
    }
}
