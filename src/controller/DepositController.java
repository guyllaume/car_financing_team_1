package controller;

import DAO.InvestorFormDAO;
import DAO.TransactionsDAO;
import model.Investor;
import model.InvestorForm;
import model.Transaction;
import view.DepositView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DepositController {
    private Transaction transaction;
    private TransactionsDAO transactionsDAO = new TransactionsDAO();

    public DepositController(DepositView dView, InvestorForm investorForm){
        dView.addInvestirListener(e -> {
            double castMontant = 0;
            try {
                castMontant = Double.parseDouble(dView.getMontantAInvestir());
            }catch (RuntimeException er){
                dView.setMessageError("Veuillez entrer un montant valide");
            }
            if (castMontant > 0 && !hasMoreThanTwoDecimals(castMontant)) {
                transaction = new Transaction();
                transaction.setType("deposit");
                transaction.setMontant(castMontant);
                transaction.setTimeOfTransaction(LocalDate.now());
                transaction.setCompteInvestorId(investorForm.getId());
                transactionsDAO.addTransactions(transaction);
            }
            else{
                dView.setMessageError("Veuillez entrer un montant valide (plus que 0.00$)");
            }
        },0);
    }

    public void loadDepositInfo(DepositView dView, Investor investor){
        dView.setnomBanque(investor.getNomBanque());
        dView.setnumeroInstitution(String.valueOf(investor.getNbInstitution()));
        dView.setNumeroTransit(String.valueOf(investor.getNbTransit()));
        dView.setNumeroCompte(String.valueOf(investor.getNbCompte()));
    }
    private boolean hasMoreThanTwoDecimals(double montant) {
        String strNumber = Double.toString(montant);
        int indexOfDecimal = strNumber.indexOf('.');

        //FALSE: Two or fewer decimals TRUE : More than two decimals
        return indexOfDecimal >= 0 && strNumber.length() - indexOfDecimal > 3;
    }
}
