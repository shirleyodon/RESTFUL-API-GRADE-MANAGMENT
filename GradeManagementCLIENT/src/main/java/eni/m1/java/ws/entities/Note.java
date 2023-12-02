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
public class Note {
    private String numEtudiant, codeMatiere;
    private double note;
	
    public Note() {
	super();
	// TODO Auto-generated constructor stub
    }

    public Note(String numEtudiant, String codeMatiere, double note) {
	super();
	this.numEtudiant = numEtudiant;
	this.codeMatiere = codeMatiere;
	this.note = note;
    }

    public String getNumEtudiant() {
	return numEtudiant;
    }

    public void setNumEtudiant(String numEtudiant) {
	this.numEtudiant = numEtudiant;
    }

    public String getCodeMatiere() {
	return codeMatiere;
    }

    public void setCodeMatiere(String codeMatiere) {
	this.codeMatiere = codeMatiere;
    }

    public double getNote() {
	return note;
    }

    public void setNote(double note) {
	this.note = note;
    }

    @Override
    public String toString() {
	return "Note [numEtudiant=" + numEtudiant + ", codeMatiere=" + codeMatiere + ", note=" + note + "]";
    }
}
