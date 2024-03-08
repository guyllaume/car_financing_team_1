package model;

public class FinancingForm {
    private String marque;
    private String modele;
    private int annee;
    private int km;
    private String VIN;
    private double montantPret;
    private int dureePret;
    private int userId;

    public FinancingForm(){

    }
    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public int getKm() {
        return km;
    }

    public void setKm(int km) {
        this.km = km;
    }

    public String getVIN() {
        return VIN;
    }

    public void setVIN(String VIN) {
        this.VIN = VIN;
    }

    public double getMontantPret() {
        return montantPret;
    }

    public void setMontantPret(double montantPret) {
        this.montantPret = montantPret;
    }

    public int getDureePret() {
        return dureePret;
    }

    public void setDureePret(int dureePret) {
        this.dureePret = dureePret;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
