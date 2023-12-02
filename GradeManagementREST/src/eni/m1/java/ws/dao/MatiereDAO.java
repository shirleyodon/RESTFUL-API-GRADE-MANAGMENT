package eni.m1.java.ws.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import eni.m1.java.ws.database.Connexion;
import eni.m1.java.ws.entities.Matiere;

/**
 *
 * @author shirleyodon
 */
public class MatiereDAO {
	private Connexion con;
    private Statement state;
    private PreparedStatement pState;
    private ResultSet result;
	
    public MatiereDAO() {
		super();
		con=new Connexion();
	}
    
    private int codeDerniereMatiere() {
    	int num=0;
    	String query="SELECT COUNT(*) AS 'TotalMatiere' FROM matiere";
    	
    	try {
    		state=con.getConnexion().createStatement();
    		result=state.executeQuery(query);
    		if(result.next()) {
    			num=result.getInt("TotalMatiere");
    		}
    		result.close();
    		state.close();
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    	return num;
    }
    
    private String codeMatiereSuivant() {
    	int derCode=codeDerniereMatiere();
    	String codeSuivant="";
    	
    	if(derCode<9)
    		codeSuivant="M00"+String.valueOf(++derCode);
    	else if(derCode<99)
    		codeSuivant="M0"+String.valueOf(++derCode);
    	else
    		codeSuivant="M"+String.valueOf(++derCode);
    	
    	return codeSuivant;
    }
    
//    AJOUT
    public String ajouterMatiere(Matiere mat) {
    	String query="INSERT INTO matiere(CodeMatiere, Libelle, Coef) VALUES(?, ?, ?)";
    	String newCodeMat=codeMatiereSuivant();
    	String codeMatReturned=null;
    	
    	try {
    		pState=con.getConnexion().prepareStatement(query);
    		pState.setString(1, newCodeMat);
    		pState.setString(2, mat.getLibelle());
    		pState.setInt(3, mat.getCoef());
    		pState.executeUpdate();
    		
    		pState.close();
    		codeMatReturned=newCodeMat;
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    	return codeMatReturned;
    }
    
//    LISTAGE
    public List<Matiere> listMatiere(){
    	List<Matiere> matieres=new ArrayList<Matiere>();
    	String query="SELECT * FROM matiere ORDER BY CodeMatiere ASC";
    	
    	try {
    		state=con.getConnexion().createStatement();
    		result=state.executeQuery(query);
    		
    		while(result.next()) {
    			Matiere mat=new Matiere();
    			mat.setCodeMatiere(result.getString("CodeMatiere"));
    			mat.setLibelle(result.getString("Libelle"));
    			mat.setCoef(result.getInt("Coef"));
    			matieres.add(mat);
    		}
    		
    		result.close();
    		state.close();
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	return matieres;
    }
    
    public List<Matiere> listMatiereParMc(String mc){
    	List<Matiere> matieres=new ArrayList<Matiere>();
    	String newMC="%"+mc+"%";
    	String query="SELECT * FROM matiere WHERE Libelle LIKE ? ORDER BY CodeMatiere ASC";
    	
    	try {
    		pState=con.getConnexion().prepareStatement(query);
    		pState.setString(1, newMC);
    		result=pState.executeQuery();
    		
    		while(result.next()) {
    			Matiere mat=new Matiere();
    			mat.setCodeMatiere(result.getString("CodeMatiere"));
    			mat.setLibelle(result.getString("Libelle"));
    			mat.setCoef(result.getInt("Coef"));
    			matieres.add(mat);
    		}
    		result.close();
    		pState.close();
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    	return matieres;
    }
    
    public List<Matiere> listMatiereParCoef(int coef){
    	List<Matiere> matieres=new ArrayList<Matiere>();
    	String query="SELECT * FROM matiere WHERE Coef=? ORDER BY CodeMatiere ASC";
    	
    	try {
    		pState=con.getConnexion().prepareStatement(query);
    		pState.setInt(1, coef);
    		result=pState.executeQuery();
    		
    		while(result.next()) {
    			Matiere mat=new Matiere();
    			mat.setCodeMatiere(result.getString("CodeMatiere"));
    			mat.setLibelle(result.getString("Libelle"));
    			mat.setCoef(result.getInt("Coef"));
    			matieres.add(mat);
    		}
    		result.close();
    		pState.close();
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    	return matieres;
    }
    
    public Matiere obtenirMatiere(String codeMat) {
    	Matiere mat=new Matiere();
    	String query="SELECT * FROM matiere WHERE CodeMatiere=?";
    	
    	try {
    		pState=con.getConnexion().prepareStatement(query);
    		pState.setString(1, codeMat);
    		result=pState.executeQuery();
    		
    		if(result.next()) {
    			mat.setCodeMatiere(result.getString("CodeMatiere"));
    			mat.setLibelle(result.getString("Libelle"));
    			mat.setCoef(result.getInt("Coef"));
    		}
    		result.close();
    		pState.close();
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    	return mat;
    }
    
//    MISE A JOUR
    public int mettreAjourMatiere(String codeMat, Matiere mat) {
    	int updated=0;
    	String query="UPDATE matiere SET Libelle=?, Coef=? WHERE CodeMatiere=?";
    	
    	try {
    		pState=con.getConnexion().prepareStatement(query);
    		pState.setString(1, mat.getLibelle());
    		pState.setInt(2, mat.getCoef());
    		pState.setString(3, codeMat);
    		updated=pState.executeUpdate();
    		pState.close();
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    	return updated;
    }
    
//    SUPPRESSION
    public int supprimerMatiere(String codeMat) {
    	int deleted=0;
    	String query="DELETE FROM matiere WHERE CodeMatiere=?";
    	
    	try {
    		pState=con.getConnexion().prepareStatement(query);
    		pState.setString(1, codeMat);
    		deleted=pState.executeUpdate();
    		pState.close();
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    	return deleted;
    }
}
