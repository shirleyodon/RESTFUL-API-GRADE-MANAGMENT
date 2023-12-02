package eni.m1.java.ws.entities;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author shirleyodon
 */
public class Bulletin {
	private Etudiant etudiant;
	private String observation;
	private double moyenne;
	private List<Ligne> lignes=new ArrayList<Ligne>();
	
	public Bulletin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Bulletin(Etudiant etudiant, String observation, double moyenne, List<Ligne> lignes) {
		super();
		this.etudiant = etudiant;
		this.observation = observation;
		this.moyenne = moyenne;
		this.lignes = lignes;
	}

	public Etudiant getEtudiant() {
		return etudiant;
	}

	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public double getMoyenne() {
		return moyenne;
	}

	public void setMoyenne(double moyenne) {
		this.moyenne = moyenne;
	}

	public List<Ligne> getLignes() {
		return lignes;
	}

	public void setLignes(List<Ligne> lignes) {
		this.lignes = lignes;
	}
	
	public void ajouterLigne(Ligne ligne) {
		this.lignes.add(ligne);
	}

}
