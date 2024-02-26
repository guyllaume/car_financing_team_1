package model;

public class Investor extends User{

    private String nomBanque;
    private String detailBancaire;
    private String risque;
    private String education;

    public Investor(){
        super();
    }
    /*public Investor(String nomComplet, String email, String password, String numeroTelephone, String nomBanque,
                    String detailBancaire, String risque, String education){
        super(nomComplet, email, password, numeroTelephone);
        this.nomBanque = nomBanque;
        this.detailBancaire = detailBancaire;
        this.risque = risque;
        this.education = education;
    }*/

    @Override
    public String toString(){
        String info = super.toString();
        info += "<br>Nom de Votre Banque : " + nomBanque
                + "<br>Vos détails bancaire : " + detailBancaire
                + "<br>Votre niveau de risque : " + risque
                + "<br>Votre niveau d'éducation comme investisseur : " + education;
        return info;
    }

    public String getNomBanque() {
        return nomBanque;
    }

    public void setNomBanque(String nomBanque) {
        this.nomBanque = nomBanque;
    }

    public String getDetailBancaire() {
        return detailBancaire;
    }

    public void setDetailBancaire(String detailBancaire) {
        this.detailBancaire = detailBancaire;
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
}
