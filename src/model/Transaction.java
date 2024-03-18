package model;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Transaction {
    private int id;
    private String type;
    private LocalDate timeOfTransaction;
    private double montant;
    private int compteInvestorId;

    public Transaction() {}
    @Override
    public String toString() {
        return typeToFrench() + new DecimalFormat("0.00").format(montant) + "$ le " + totLongForm();
    }
    private String totLongForm(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.CANADA_FRENCH);
        return timeOfTransaction.format(formatter);
    }
    private String typeToFrench(){
        return type.equals("deposit") ? "Investi " : "Retiré ";
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
