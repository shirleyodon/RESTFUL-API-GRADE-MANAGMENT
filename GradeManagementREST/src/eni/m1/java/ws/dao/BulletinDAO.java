package eni.m1.java.ws.dao;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;

import eni.m1.java.ws.entities.Bulletin;
import eni.m1.java.ws.entities.Ligne;
import eni.m1.java.ws.entities.Matiere;
import eni.m1.java.ws.entities.Note;

/**
 *
 * @author shirleyodon
 */
public class BulletinDAO {
	private EtudiantDAO etudiantDAO;
	private MatiereDAO matiereDAO;
	private NoteDAO noteDAO;
	private Bulletin bulletin;
	
	public BulletinDAO() {
		etudiantDAO=new EtudiantDAO();
		matiereDAO= new MatiereDAO();
		noteDAO= new NoteDAO();
		bulletin= new Bulletin();
	}
	
	/*	L'objectif est de collecter des informations sur :
	 * 		_ le proprietaire du bulletin [definirEtudiant()]
	 * 		_ chacune de ses notes [remplirLignesNotes()]
	 * 		_ sa moyenne [calculerMoyenne()]
	 * 		_ son observation [remplirObservation()]
	 * 		_ pour enfin g�n�rer le bulletin [genererBulletin()]
	 */
	
	private void definirEtudiant(String numEtudiant) {
		this.bulletin.setEtudiant(etudiantDAO.obtenirEtudiant(numEtudiant));
	}
	
	private void remplirLignesNotes() {
		List<Matiere> listMatiere=matiereDAO.listMatiere();
		
		for(Matiere mat: listMatiere) {
			//Recuperer chaque note pour chaque matiere
			Note note=noteDAO.obtenirNote(this.bulletin.getEtudiant().getNumEtudiant(), mat.getCodeMatiere());
			
			if(note.getNumEtudiant() == null) {
				//Si l'entr�e note[numEtudiant-codeMatiere] n'existe pas c-�-d que l'etudiant n'a pas de note sur la matiere
				this.bulletin.ajouterLigne(new Ligne(mat.getLibelle(), mat.getCoef()));
				
			}else {
				double notePonderee=mat.getCoef()*note.getNote();
				this.bulletin.ajouterLigne(new Ligne(mat.getLibelle(), mat.getCoef(), note.getNote(), notePonderee));
			}
		}
	}
	
	private void calculerMoyenne() {
		int sommeCoef=0;
		double sommeNotePonderee=0, moyenne;
		
		for(Ligne ligne: this.bulletin.getLignes()) {
			sommeCoef+=ligne.getCoef();
			sommeNotePonderee+=ligne.getNotePonderee();
		}
		
		moyenne=sommeNotePonderee/sommeCoef;
		this.bulletin.setMoyenne(formatMoyenne(moyenne));
	}
	
	private double formatMoyenne(double moyenne) {
		String moyenneFormatted, pattern="#.##";//La moyenne sera arrondie de deux chiffres apr�s la virgule
		DecimalFormat df=new DecimalFormat(pattern);
		
		df.setRoundingMode(RoundingMode.HALF_UP);
		int i=df.format(moyenne).indexOf(',');
		
		if(i != -1)
			moyenneFormatted=df.format(moyenne).substring(0, i)+"."+df.format(moyenne).substring(i+1);
		else
			moyenneFormatted=df.format(moyenne);
		
		return Double.valueOf(moyenneFormatted);
	}
	
	private void remplirObservation() {
		if(this.bulletin.getMoyenne()>=10)
			this.bulletin.setObservation("ADMIS");
		else if(this.bulletin.getMoyenne()>=7.5)
			this.bulletin.setObservation("REDOUBLE");
		else
			this.bulletin.setObservation("EXCLU");
	}
	
	public Bulletin genererBulletin(String numEtudiant) {
		if(etudiantDAO.isStudentExist(numEtudiant)) {
			this.definirEtudiant(numEtudiant);
			this.remplirLignesNotes();
			this.calculerMoyenne();
			this.remplirObservation();
		}
		
		return this.bulletin;
	}
}
