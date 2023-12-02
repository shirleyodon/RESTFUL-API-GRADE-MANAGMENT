package eni.m1.java.ws.entities;

/**
 *
 * @author shirleyodon
 */
public class Utilisateur {
    private String nom, prenom, login, pwd, statut;

    public Utilisateur() {
	super();
	// TODO Auto-generated constructor stub
    }

    public Utilisateur(String login, String pwd) {
	super();
	this.login = login;
	this.pwd = pwd;
    }

    public Utilisateur(String nom, String prenom, String login, String pwd) {
        this.nom = nom;
        this.prenom = prenom;
        this.login = login;
        this.pwd = pwd;
    }

    public Utilisateur(String nom, String prenom, String login, String pwd, String statut) {
        this.nom = nom;
        this.prenom = prenom;
        this.login = login;
        this.pwd = pwd;
        this.statut = statut;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getLogin() {
	return login;
    }

    public void setLogin(String login) {
	this.login = login;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
	this.pwd = pwd;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    @Override
    public String toString() {
        String hiddenPwd="";
        for(int i=0; i<pwd.length(); i++)
            hiddenPwd+="*";
        
        return "Utilisateur{" + "nom=" + nom + ", prenom=" + prenom + ", login=" + login + ", pwd=" + hiddenPwd + ", statut=" + statut + '}';
    }
}
