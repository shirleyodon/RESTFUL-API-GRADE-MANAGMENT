package eni.m1.java.ws.entities;

/**
 *
 * @author shirleyodon
 */
public class Matiere {
	private String codeMatiere, libelle;
	private int coef;
	
	public Matiere() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Matiere(String codeMatiere, String libelle, int coef) {
		super();
		this.codeMatiere = codeMatiere;
		this.libelle = libelle;
		this.coef = coef;
	}

	public Matiere(String libelle, int coef) {
		super();
		this.libelle = libelle;
		this.coef = coef;
	}

	public String getCodeMatiere() {
		return codeMatiere;
	}

	public void setCodeMatiere(String codeMatiere) {
		this.codeMatiere = codeMatiere;
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

	@Override
	public String toString() {
		return "Matiere [codeMatiere=" + codeMatiere + ", libelle=" + libelle + ", coef=" + coef + "]";
	}
	
}
