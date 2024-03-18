package model;

public class Investor extends User {
    private String nomBanque;
    private int nbInstitution;
    private int nbTransit;
    private int nbCompte;
    private String risque;
    private String education;

    public Investor() {
        super();
    }

    public Investor(String nomComplet, String email, String password, String numeroTelephone, String nomBanque, int nbInstitution, int nbTransit, int nbCompte, String risque, String education) {
        super(nomComplet, email, password, numeroTelephone);
        this.nomBanque = nomBanque;
        this.nbInstitution = nbInstitution;
        this.nbTransit = nbTransit;
        this.nbCompte = nbCompte;
        this.risque = risque;
        this.education = education;
    }

    @Override
    public String toString() {
        String info = super.toString();
        info += "<br>Nom de Votre Banque : " + nomBanque
                + "<br>Votre numéro d'Institution bancaire : " + nbInstitution
                + "<br>Votre numéro de transit : " + nbTransit
                + "<br>Votre numéro de compte bancaire : " + nbCompte
                + "<br>Votre niveau de risque : " + risque
                + "<br>Votre niveau d'éducation comme investisseur : " + education;
        return info;
    }

    public String toStringInvestorOnly() {
        String info = "";
        info += "<html>Nom de Votre Banque : " + nomBanque
                + "<br>Votre numéro d'Institution bancaire : " + nbInstitution
                + "<br>Votre numéro de transit : " + nbTransit
                + "<br>Votre numéro de compte bancaire : " + nbCompte
                + "<br>Votre niveau de risque : " + risque
                + "<br>Votre niveau d'éducation comme investisseur : " + education+ "</html>";
        return info;
    }

    public String getNomBanque() {
        return nomBanque;
    }

    public void setNomBanque(String nomBanque) {
        this.nomBanque = nomBanque;
    }


    public String getRisque() {
        return risque;
    }

    public void setRisque(String risque) {
        this.risque = risque;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public int getNbInstitution() {
        return nbInstitution;
    }

    public void setNbInstitution(int nbInstitution) {
        this.nbInstitution = nbInstitution;
    }

    public int getNbTransit() {
        return nbTransit;
    }

    public void setNbTransit(int nbTransit) {
        this.nbTransit = nbTransit;
    }

    public int getNbCompte() {
        return nbCompte;
    }

    public void setNbCompte(int nbCompte) {
        this.nbCompte = nbCompte;
    }

}
