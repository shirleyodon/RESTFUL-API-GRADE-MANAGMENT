/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eni.m1.java.ws.entities;

/**
 *
 * @author shirleyodon
 */
public class Etudiant {
    private String numEtudiant, nom, prenom, niveau;

    public Etudiant() {
    }

    public Etudiant(String nom, String prenom, String niveau) {
        this.nom = nom;
        this.prenom = prenom;
        this.niveau = niveau;
    }

    public Etudiant(String numEtudiant, String nom, String prenom, String niveau) {
        this.numEtudiant = numEtudiant;
        this.nom = nom;
        this.prenom = prenom;
        this.niveau = niveau;
    }

    public String getNumEtudiant() {
        return numEtudiant;
    }

    public void setNumEtudiant(String numEtudiant) {
        this.numEtudiant = numEtudiant;
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

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    @Override
    public String toString() {
        return "Etudiant{" + "numEtudiant=" + numEtudiant + ", nom=" + nom + ", prenom=" + prenom + ", niveau=" + niveau + '}';
    }
}
