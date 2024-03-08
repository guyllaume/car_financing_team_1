package controller;

import DAO.FinancingFormDAO;
import model.Client;
import model.FinancingForm;
import view.StatutView;

public class StatutFormController {
    private FinancingFormDAO financingFormDAO = new FinancingFormDAO();
    private FinancingForm financingForm;
    public StatutFormController(){}
    public void loadFormStatus(Client client, StatutView sv){
        if(verifyConnection(client)){
            if (getFormFromDao(client)){
                sv.setMarque(financingForm.getMarque());
                sv.setModel(financingForm.getModele());
                sv.setAnnee(String.valueOf(financingForm.getAnnee()));
                sv.setKm(String.valueOf(financingForm.getKm()));
            }else {
                sv.setMessageError("No Financing form found");
            }
        }else{
            sv.setMessageError("No account connected to the application");
        }
    }

    private boolean verifyConnection(Client client){
        return client.getId() != 0;
    }

    private boolean getFormFromDao(Client client){
        financingForm = financingFormDAO.getFinancingFormByUserID(client.getId());
        return financingForm != null;
    }
}
