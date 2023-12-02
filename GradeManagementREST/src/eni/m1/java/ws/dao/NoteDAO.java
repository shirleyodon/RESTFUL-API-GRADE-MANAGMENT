package eni.m1.java.ws.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import eni.m1.java.ws.database.Connexion;
import eni.m1.java.ws.entities.Etudiant;
import eni.m1.java.ws.entities.Matiere;
import eni.m1.java.ws.entities.Note;

/**
 *
 * @author shirleyodon
 */
public class NoteDAO {
	private Connexion con;
    private Statement state;
    private PreparedStatement pState;
    private ResultSet result;
    private EtudiantDAO etudiantDAO;
    private MatiereDAO matiereDAO;
    
    public NoteDAO() {
    	con=new Connexion();
    	etudiantDAO=new EtudiantDAO();
        matiereDAO=new MatiereDAO();
    }
    
//    AJOUT
    public int ajouterNote(Note note) {
    	String query="INSERT INTO notes(NumEtudiant, CodeMatiere, Note) values(?, ?, ?)";
    	int added=0;
    	
    	Etudiant et=etudiantDAO.obtenirEtudiant(note.getNumEtudiant());
    	Matiere mat=matiereDAO.obtenirMatiere(note.getCodeMatiere());
    	Note entreeBaseDonnees;
    	
    	if(et.getNumEtudiant()==null || mat.getCodeMatiere()==null) {
    		//Pour eviter d'attribuer une note � un etudiant ou � une mati�re qui n'existe pas
    		
    		if(et.getNumEtudiant()==null && mat.getCodeMatiere()==null) added=-3;
            else if(mat.getCodeMatiere()==null) added=-2;
            else added=-1;
    		
    	}else{
    		entreeBaseDonnees=obtenirNote(et.getNumEtudiant(), mat.getCodeMatiere());
    		
    		if(entreeBaseDonnees.getNumEtudiant()==null){
    			//Pour s'assurer qu'une entree ayant les m�mes cl�s primaires que "note" n'existe pas d�j� dans la base de donn�es
    			try {
            		pState=con.getConnexion().prepareStatement(query);
            		pState.setString(1, note.getNumEtudiant());
            		pState.setString(2, note.getCodeMatiere());
            		pState.setDouble(3, note.getNote());
            		added=pState.executeUpdate();
            		
            		pState.close();
            	}catch(Exception e) {
            		e.printStackTrace();
            	}
    		}
    	}
    	
    	return added;
    }
    
//    LISTAGE
    public List<Note> listNote(){
    	List<Note> notes=new ArrayList<Note>();
    	String query="SELECT * FROM notes ORDER BY NumEtudiant ASC , CodeMatiere ASC";
    	
    	try {
    		state=con.getConnexion().createStatement();
    		result=state.executeQuery(query);
    		
    		while(result.next()) {
    			Note note=new Note();
    			note.setNumEtudiant(result.getString("NumEtudiant"));
    			note.setCodeMatiere(result.getString("CodeMatiere"));
    			note.setNote(result.getDouble("Note"));

    			notes.add(note);
    		}
    		
    		result.close();
    		state.close();
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    	return notes;
    }
    
    public List<Note> listNoteParEtudiant(String numEtudiant){
    	List<Note> notes=new ArrayList<Note>();
    	String query="SELECT* FROM notes WHERE NumEtudiant=? ORDER BY CodeMatiere ASC";
    	
    	try {
    		pState=con.getConnexion().prepareStatement(query);
    		pState.setString(1, numEtudiant);
    		result=pState.executeQuery();
    		
    		while(result.next()) {
    			Note note=new Note();
    			
    			note.setNumEtudiant(result.getString("NumEtudiant"));
    			note.setCodeMatiere(result.getString("CodeMatiere"));
    			note.setNote(result.getDouble("Note"));
    			
    			notes.add(note);
    		}
    		
    		result.close();
    		pState.close();
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    	return notes;
    }
    
    public List<Note> listNoteParMatiereEtNiveau(String codeMatiere, String niveau){
    	List<Note> notes=new ArrayList<Note>();
    	String query="SELECT* FROM notes WHERE CodeMatiere=? AND NumEtudiant IN (SELECT NumEtudiant FROM etudiant WHERE Niveau=?) ORDER BY NumEtudiant ASC";
    	
    	try {
    		pState=con.getConnexion().prepareStatement(query);
    		pState.setString(1, codeMatiere);
    		pState.setString(2, niveau);
    		result=pState.executeQuery();
    		
    		while(result.next()) {
    			Note note=new Note();
    			
    			note.setNumEtudiant(result.getString("NumEtudiant"));
    			note.setCodeMatiere(result.getString("CodeMatiere"));
    			note.setNote(result.getDouble("Note"));
    			
    			notes.add(note);
    		}
    		
    		result.close();
    		pState.close();
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    	return notes;
    }
    
    public Note obtenirNote(String numEtudiant, String codeMatiere) {
    	Note note=new Note();
    	String query="SELECT* FROM notes WHERE NumEtudiant=? AND CodeMatiere=?";
    	
    	try {
    		pState=con.getConnexion().prepareStatement(query);
    		pState.setString(1, numEtudiant);
    		pState.setString(2, codeMatiere);
    		result=pState.executeQuery();
    		
    		if(result.next()) {
    			note.setNumEtudiant(result.getString("NumEtudiant"));
    			note.setCodeMatiere(result.getString("CodeMatiere"));
    			note.setNote(result.getDouble("Note"));
    		}
    		
    		result.close();
    		pState.close();
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    	return note;
    }
    
//	MISE � JOUR
    public int mettreAjourNote(String numEtudiant, String codeMatiere, double note) {
    	String query="UPDATE notes SET Note=? WHERE NumEtudiant=? AND CodeMatiere=?";
    	int updated=0;
    	
    	try {
    		pState=con.getConnexion().prepareStatement(query);
    		pState.setDouble(1, note);
    		pState.setString(2, numEtudiant);
    		pState.setString(3, codeMatiere);
    		updated=pState.executeUpdate();
    		
    		pState.close();
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    	return updated;
    }
    
//	SUPPRESSION
    public int supprimerNote(String numEtudiant, String codeMatiere) {
    	String query="DELETE FROM notes WHERE NumEtudiant=? and CodeMatiere=?";
    	int deleted=0;
    	
    	try {
    		pState=con.getConnexion().prepareStatement(query);
    		pState.setString(1, numEtudiant);
    		pState.setString(2, codeMatiere);
    		deleted=pState.executeUpdate();
    		
    		pState.close();
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    	return deleted;
    }
}
