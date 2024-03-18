package controller;

import DAO.InvestorFormDAO;
import DAO.TransactionsDAO;
import model.Client;
import model.Investor;
import model.InvestorForm;
import model.Transaction;
import view.InvestorAccountView;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class InvestorAccountController {
    private InvestorForm investorForm;
    private InvestorFormDAO investorFormDAO = new InvestorFormDAO();
    private List<Transaction> transactions;
    private List<String> transactionsToString;
    private TransactionsDAO transactionsDAO = new TransactionsDAO();

    public InvestorAccountController(){

    }
    public void loadOrCreateInvestorAccount(InvestorAccountView iaView, Investor investor, InvestorForm investorForm) {
        if (!getFormFromDao(investor)) {
            investorFormDAO.createInvestorAccount(investor.getId());
            getFormFromDao(investor);
        }
        investorForm.setId(this.investorForm.getId());
        investorForm.setUserId(this.investorForm.getUserId());
        investorForm.setSolde(this.investorForm.getSolde());
        iaView.setInformationInvestor(investor.toStringInvestorOnly());
        transactionsToString = new ArrayList<>();
        transactions = transactionsDAO.getAllTransactions(investorForm.getId());
        investorForm.setTransactions(transactions);
        for (Transaction transaction : transactions){
            transactionsToString.add(transaction.toString());
        }
        iaView.setMontantTotal(new DecimalFormat("0.00").format(investorForm.getMontantInvesti()) + "$");
        iaView.setTransactionsList(transactionsToString);
        iaView.setSolde(new DecimalFormat("0.00").format(investorForm.getSolde()) + "$");
    }

    private boolean getFormFromDao(Investor investor) {
        investorForm = investorFormDAO.getInvestorAccount(investor.getId());
        return investorForm != null;
    }
}
