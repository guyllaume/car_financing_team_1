package model;

import java.time.LocalDate;

public class Client extends User{

    private String infoEmploi;
    private double revenuAnnuel;
    private int credit;
    private LocalDate dateNaissance;
    private String statutMarital;
    private LocalDate dateCanada;
    public Client(String nomComplet, String email, String password, String numeroTelephone, String infoEmploi,
                  double revenuAnnuel, int credit, LocalDate dateNaissance, String statutMarital, LocalDate dateCanada){
        super(nomComplet, email, password, numeroTelephone);
        this.infoEmploi = infoEmploi;
        this.revenuAnnuel = revenuAnnuel;
        this.credit = credit;
        this.dateNaissance = dateNaissance;
        this.statutMarital = statutMarital;
        this.dateCanada = dateCanada;
    }
    @Override
    public String toString(){
        String info = super.toString();
        info += "Vos informations d'emploi : " + infoEmploi
                + "Votre revenue annuel : " + revenuAnnuel
                + "Votre cote de credit : " + credit
                + "Votre statut marital : " + statutMarital
                + "Votre cote de credit : " + dateCanada;
        return info;
    }
    public String getInfoEmploi() {
        return infoEmploi;
    }

    public void setInfoEmploi(String infoEmploi) {
        this.infoEmploi = infoEmploi;
    }

    public double getRevenuAnnuel() {
        return revenuAnnuel;
    }

    public void setRevenuAnnuel(double revenuAnnuel) {
        this.revenuAnnuel = revenuAnnuel;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public String getStatutMarital() {
        return statutMarital;
    }

    public void setStatutMarital(String statutMarital) {
        this.statutMarital = statutMarital;
    }

    public LocalDate getDateCanada() {
        return dateCanada;
    }

    public void setDateCanada(LocalDate dateCanada) {
        this.dateCanada = dateCanada;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }
}
