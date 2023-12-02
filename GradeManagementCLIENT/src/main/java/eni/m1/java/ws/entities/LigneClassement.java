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
public class LigneClassement {
    private String numEtudiant, nom, prenom;
    private double moyenne;
    private int rang;
	
    public LigneClassement() {
	super();
	// TODO Auto-generated constructor stub
    }
	
    public LigneClassement(Etudiant etudiant, double moyenne, int rang) {
	numEtudiant=etudiant.getNumEtudiant();
	nom=etudiant.getNom();
	prenom=etudiant.getPrenom();
	this.moyenne=moyenne;
	this.rang=rang;
    }

    public double getMoyenne() {
	return moyenne;
    }

    public void setMoyenne(double moyenne) {
	this.moyenne = moyenne;
    }

    public String getNumEtudiant() {
	return numEtudiant;
    }

    public String getNom() {
	return nom;
    }

    public String getPrenom() {
	return prenom;
    }
	
    public void setEtudiant(Etudiant etudiant) {
	numEtudiant=etudiant.getNumEtudiant();
	nom=etudiant.getNom();
        prenom=etudiant.getPrenom();
    }

    public int getRang() {
	return rang;
    }

    public void setRang(int rang) {
	this.rang = rang;
    }
}
