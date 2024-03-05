package model;

public class User {
    private int id;
    private String nomComplet;
    private String email;
    private String password;
    private String telephone;

    public User(){}
    public User(String nomComplet, String email, String password, String telephone){
        this.nomComplet = nomComplet;
        this.email = email;
        this.password = password;
        this.telephone = telephone;
    }

    @Override
    public String toString(){
        String info = "<br>Nom complet : " + this.nomComplet
                + "<br>Votre Courriel : " + this.email
                + "<br>Votre Mot De Passe : " + this.password
                + "<br>Votre numero de telephone : " + this.telephone;
        return info;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getNomComplet() {
        return nomComplet;
    }

    public void setNomComplet(String nomComplet) {
        this.nomComplet = nomComplet;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
