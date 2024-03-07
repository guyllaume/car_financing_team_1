package model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class User {
    private int id;
    private int clientId;
    private int investorId;
    private String nomComplet;
    private String email;
    private String password;
    private byte[] salt;
    private String telephone;

    public User(){}
    public User(String nomComplet, String email, String password, String telephone){
        this.nomComplet = nomComplet;
        this.email = email;
        createSalt();
        hashPassword(password, salt);
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

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getInvestorId() {
        return investorId;
    }

    public void setInvestorId(int investorId) {
        this.investorId = investorId;
    }

    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }
    private void hashPassword(String password, byte[] salt){
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.update(salt);
            byte[] hashBytes = digest.digest(password.getBytes());

            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            password = sb.toString();
        }catch (NoSuchAlgorithmException e){
            System.out.println("Something went wrong with the hashing process..");
        }
        this.password = password;
    }
    private void createSalt(){
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        this.salt = salt;
    }

    public void createSaltAndHash(){
        createSalt();
        hashPassword(password, salt);
    }

    public boolean verifyPassword(String candidatePassword){
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.update(salt);
            byte[] hashBytes = digest.digest(candidatePassword.getBytes());

            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            candidatePassword = sb.toString();
            return candidatePassword.equals(password);
        }catch (NoSuchAlgorithmException e){
            System.out.println("Something went wrong with the hashing process..");
        }
        return false;
    }

}
