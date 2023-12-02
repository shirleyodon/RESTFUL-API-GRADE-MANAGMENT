package eni.m1.java.ws.entities;

/**
 *
 * @author shirleyodon
 */
public class Ligne {
	private String libelle;
	private int coef;
	private double note, notePonderee;
	
	public Ligne() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Ligne(String libelle, int coef, double note, double notePonderee) {
		super();
		this.libelle = libelle;
		this.coef = coef;
		this.note = note;
		this.notePonderee = notePonderee;
	}

	public Ligne(String libelle, int coef) {
		super();
		this.libelle = libelle;
		this.coef = coef;
		this.note = 0;
		this.notePonderee = 0;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public int getCoef() {
		return coef;
	}

	public void setCoef(int coef) {
		this.coef = coef;
	}

	public double getNote() {
		return note;
	}

	public void setNote(double note) {
		this.note = note;
	}

	public double getNotePonderee() {
		return notePonderee;
	}

	public void setNotePonderee(double notePonderee) {
		this.notePonderee = notePonderee;
	}

	@Override
	public String toString() {
		return "Ligne [libelle=" + libelle + ", coef=" + coef + ", note=" + note + ", notePonderee=" + notePonderee + "]";
	}
	
}
