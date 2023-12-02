package eni.m1.java.ws.dao;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import eni.m1.java.ws.entities.Bulletin;
import eni.m1.java.ws.entities.Etudiant;
import eni.m1.java.ws.entities.Ligne;
import eni.m1.java.ws.entities.LigneClassement;
import eni.m1.java.ws.entities.Matiere;
import eni.m1.java.ws.entities.Note;

/**
 *
 * @author shirleyodon
 */
public class ClassementDAO {
	private List<LigneClassement> lignesClassement;
	private EtudiantDAO etudiantDAO;
	private MatiereDAO matiereDAO;
	private NoteDAO noteDAO;
	
	public ClassementDAO() {
		lignesClassement=new ArrayList<LigneClassement>();
		etudiantDAO=new EtudiantDAO();
		matiereDAO=new MatiereDAO();
		noteDAO=new NoteDAO();
	}
	
	private void calculerMoyenne(Bulletin bulletin) {
		int sommeCoef=0;
		double sommeNotePonderee=0, moyenne;
		
		for(Ligne ligne: bulletin.getLignes()) {
			sommeCoef+=ligne.getCoef();
			sommeNotePonderee+=ligne.getNotePonderee();
		}
		
		moyenne=sommeNotePonderee/sommeCoef;		
		bulletin.setMoyenne(formatMoyenne(moyenne));
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
	
	private Bulletin genererBulletin(Etudiant etudiant) {
		Bulletin bulletin=new Bulletin();
		bulletin.setEtudiant(etudiant);
		List<Matiere> listMatiere=matiereDAO.listMatiere();
		
		for(Matiere matiere: listMatiere) {
			Note note=noteDAO.obtenirNote(etudiant.getNumEtudiant(), matiere.getCodeMatiere());
			
			if(note.getNumEtudiant()==null) {
				//Si l'entr�e note[numEtudiant-codeMatiere] n'existe pas c-�-d que l'etudiant n'a pas de note sur la matiere
				bulletin.ajouterLigne(new Ligne(matiere.getLibelle(), matiere.getCoef()));
			}else {
				double notePonderee=matiere.getCoef()*note.getNote();
				bulletin.ajouterLigne(new Ligne(matiere.getLibelle(), matiere.getCoef(), note.getNote(), notePonderee));
			}
		}
		
		calculerMoyenne(bulletin);
		return bulletin;
	}
	
	private boolean egaliteMoyenne(LigneClassement i, LigneClassement j) {
		return (i.getMoyenne()==j.getMoyenne());
	}
	
	private int obtenirNumEtudiantEnEntier(LigneClassement ligne) {
		return Integer.valueOf(ligne.getNumEtudiant().substring(1, ligne.getNumEtudiant().length())).intValue();
	}
	
	/*
	 * T	R	I 		P	A	R 		S	E	G	M	E	N	T	A	T	I	O	N
	 */
	
	/*
	 * DEBUT
	 */
	
	private void permut(List<LigneClassement> lignes, int indiceI, int indiceJ) {
		LigneClassement valeurPrecedenteI=lignes.get(indiceI);
		lignes.set(indiceI, lignes.get(indiceJ));
		lignes.set(indiceJ, valeurPrecedenteI);
	}
	
	private int segmentation(List<LigneClassement> lignes, int inf, int sup) {
		int pi=inf, i=inf+1, j=sup;
		LigneClassement pivot=lignes.get(pi);
		
		while(i<=j) {
			if(lignes.get(i).getMoyenne() >= pivot.getMoyenne()) {
				if(egaliteMoyenne(lignes.get(i), pivot)) {
					int numEtudiantI=obtenirNumEtudiantEnEntier(lignes.get(i)),
						numEtudiantPivot=obtenirNumEtudiantEnEntier(pivot);
					
					if(numEtudiantI > numEtudiantPivot) {
						pi=i;
						pivot=lignes.get(pi);
					}
				}
				i++;
			}else {
				permut(lignes, i, j);
				j--;
			}
		}
		
		permut(lignes, pi, j);
		pi=j;
		
		return pi;
	}
	
	private void triParSegmentation(List<LigneClassement> lignes, int inf, int sup) {
		int pivot;
		
		if(inf < sup) {
			pivot=segmentation(lignes, inf, sup);
			triParSegmentation(lignes, inf, pivot-1);
			triParSegmentation(lignes, pivot+1, sup);
		}
	}
	
	/*
	 * FIN
	 */
	
	private void ordonnerLignesClassement(List<LigneClassement> lignes) {
		triParSegmentation(lignes, 0, lignes.size()-1);
	}
	
	private void ajouterRang(List<LigneClassement> lignes) {
		/*On travaille sur des donn�es (lignes) d�j� ordonn�es*/
		if(lignes.size()!=0) {
			lignes.get(0).setRang(1);
			/*	La clause if(lignes.size()>1)	est g�rer par (j<lignes.size())*/
			
			for(int i=0, j=1; j<lignes.size(); i++, j++) {
				if(egaliteMoyenne(lignes.get(i), lignes.get(j)))
					lignes.get(j).setRang(lignes.get(i).getRang());
				else
					lignes.get(j).setRang(lignes.get(i).getRang()+1);
			}
		}
	}
	
	public List<LigneClassement> genererClassement(String niveau){
		List<Etudiant> listEtudiant=etudiantDAO.listEtudiantParNiveau(niveau);
		
		for(Etudiant etudiant: listEtudiant) {
			LigneClassement newLigne=new LigneClassement();
			Bulletin bulletin=genererBulletin(etudiant);
			
			newLigne.setEtudiant(bulletin.getEtudiant());
			newLigne.setMoyenne(bulletin.getMoyenne());
			
			lignesClassement.add(newLigne);
		}
		
		ordonnerLignesClassement(lignesClassement);
		ajouterRang(lignesClassement);
		
		return this.lignesClassement;
	}
}
