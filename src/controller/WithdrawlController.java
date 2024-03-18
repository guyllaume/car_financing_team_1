package controller;

import DAO.TransactionsDAO;
import model.InvestorForm;
import model.Transaction;
import view.WithdrawlView;

import java.text.DecimalFormat;
import java.time.LocalDate;

public class WithdrawlController {
    private Transaction transaction;
    private TransactionsDAO transactionsDAO = new TransactionsDAO();

    public WithdrawlController(WithdrawlView wView, InvestorForm investorForm){
        wView.addRetirerListener(e -> {
            double castMontant = 0;
            try {
                castMontant = Double.parseDouble(wView.getMontantARetirer());
            }catch (RuntimeException er){
                wView.setMessageError("Veuillez entrer un montant valide");
            }
            if (castMontant > 0 && !hasMoreThanTwoDecimals(castMontant)) {
                if (castMontant <= investorForm.getSolde()) {
                    transaction = new Transaction();
                    transaction.setType("withdrawl");
                    transaction.setMontant(castMontant);
                    transaction.setTimeOfTransaction(LocalDate.now());
                    transaction.setCompteInvestorId(investorForm.getId());
                    transactionsDAO.addTransactions(transaction);
                }else{
                    wView.setMessageError("Le montant a retiré ne peut pas depasser votre solde");
                }
            }
            else{
                wView.setMessageError("Veuillez entrer un montant valide (plus que 0.00$)");
            }
        },0);
    }

    public void loadSolde(WithdrawlView wView, InvestorForm investorForm){
        wView.setsolde(new DecimalFormat("0.00").format(investorForm.getSolde()) + "$");
    }
    private boolean hasMoreThanTwoDecimals(double montant) {
        String strNumber = Double.toString(montant);
        int indexOfDecimal = strNumber.indexOf('.');

        //FALSE: Two or fewer decimals TRUE : More than two decimals
        return indexOfDecimal >= 0 && strNumber.length() - indexOfDecimal > 3;
    }
}
