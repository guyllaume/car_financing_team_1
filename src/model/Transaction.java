package model;

import java.time.LocalDate;

public class Transaction {
    private int id;
    private String type;
    private LocalDate timeOfTransaction;
    private double montant;
    private int compteInvestorId;

    public Transaction() {

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getTimeOfTransaction() {
        return timeOfTransaction;
    }

    public void setTimeOfTransaction(LocalDate timeOfTransaction) {
        this.timeOfTransaction = timeOfTransaction;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public int getCompteInvestorId() {
        return compteInvestorId;
    }

    public void setCompteInvestorId(int compteInvestorId) {
        this.compteInvestorId = compteInvestorId;
    }

}
