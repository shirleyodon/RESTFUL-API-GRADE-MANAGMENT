package eni.m1.java.ws.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import eni.m1.java.ws.database.Connexion;
import eni.m1.java.ws.entities.Etudiant;

/**
 *
 * @author shirleyodon
 */
public class EtudiantDAO{
    private Connexion con;
    private Statement state;
    private PreparedStatement pState;
    private ResultSet result;
    
    public EtudiantDAO(){
        con=new Connexion();
    }
    
    private int numDernierEtudiant(){
        int num=0;
        String query="SELECT COUNT(*) AS 'TotalEtudiant' FROM etudiant";
        
        try{
            state=con.getConnexion().createStatement();
            result=state.executeQuery(query);
            
            if(result.next())
                num=result.getInt("TotalEtudiant");
            
            result.close();
            state.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return num;
    }
    
    private String numEtudiantSuivant(){
        int derNum=numDernierEtudiant();
        String numSuivant="";
        
        if(derNum<9)
            numSuivant="E00"+String.valueOf(++derNum);
        else if(derNum<99)
            numSuivant="E0"+String.valueOf(++derNum);
        else
            numSuivant="E"+String.valueOf(++derNum);
        
        return numSuivant;
    }
    
    public boolean isStudentExist(String numEtudiant) {
    	if(obtenirEtudiant(numEtudiant).getNumEtudiant() == null)
    		return false;
    	else
    		return true;
    }
    
    //AJOUT
    public String ajouterEtudiant(Etudiant et){
    	String query="INSERT INTO etudiant(NumEtudiant, Nom, Prenom, Niveau) VALUES(?, ?, ?, ?)";
        String newNum=numEtudiantSuivant();
        String numReturned=null;
        
        try{
            pState=con.getConnexion().prepareStatement(query);
            pState.setString(1, newNum);
            pState.setString(2, et.getNom());
            pState.setString(3, et.getPrenom());
            pState.setString(4, et.getNiveau());
            pState.executeUpdate();
            
            pState.close();
            numReturned=newNum;
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return numReturned;
    }
    
    //LISTAGE
    public List<Etudiant> listEtudiant(){
        List<Etudiant> etudiants=new ArrayList<Etudiant>();
        String query="SELECT * FROM etudiant ORDER BY NumEtudiant ASC";
        
        try{
            state=con.getConnexion().createStatement();
            result=state.executeQuery(query);

            while(result.next()){
                Etudiant et=new Etudiant();

                et.setNumEtudiant(result.getString("NumEtudiant"));
                et.setNom(result.getString("Nom"));
                et.setPrenom(result.getString("Prenom"));
                et.setNiveau(result.getString("Niveau"));

                etudiants.add(et);
            }
            
            result.close();
            state.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return etudiants;  
    }
    
    public List<Etudiant> listEtudiantParNiveau(String niveau){
        List<Etudiant> etudiants=new ArrayList<Etudiant>();
        String query="SELECT * FROM etudiant WHERE Niveau=? ORDER BY NumEtudiant ASC";
        
        try{
            pState=con.getConnexion().prepareStatement(query);
            pState.setString(1, niveau);
            result=pState.executeQuery();
            
            while(result.next()){
                Etudiant et=new Etudiant();

                et.setNumEtudiant(result.getString("NumEtudiant"));
                et.setNom(result.getString("Nom"));
                et.setPrenom(result.getString("Prenom"));
                et.setNiveau(result.getString("Niveau"));

                etudiants.add(et);
            }
            
            result.close();
            pState.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return etudiants;
    }
    
    public List<Etudiant> listEtudiantParMC(String mc){
        List<Etudiant> etudiants=new ArrayList<Etudiant>();
        String newMC="%"+mc+"%";
        String query="SELECT * FROM etudiant WHERE Nom LIKE ? OR Prenom LIKE ? ORDER BY NumEtudiant ASC";
        
        try{
            pState=con.getConnexion().prepareStatement(query);
            pState.setString(1, newMC);
            pState.setString(2, newMC);
            result=pState.executeQuery();
            
            while(result.next()){
                Etudiant et=new Etudiant();

                et.setNumEtudiant(result.getString("NumEtudiant"));
                et.setNom(result.getString("Nom"));
                et.setPrenom(result.getString("Prenom"));
                et.setNiveau(result.getString("Niveau"));

                etudiants.add(et);
            }
            
            result.close();
            pState.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return etudiants;
    }
    
    public Etudiant obtenirEtudiant(String num){
        Etudiant et=new Etudiant();
        String query="SELECT * FROM etudiant WHERE NumEtudiant=?";
        
        try{
            pState=con.getConnexion().prepareStatement(query);
            pState.setString(1, num);
            result=pState.executeQuery();
            
            if(result.next()){
                et.setNumEtudiant(result.getString("NumEtudiant"));
                et.setNom(result.getString("Nom"));
                et.setPrenom(result.getString("Prenom"));
                et.setNiveau(result.getString("Niveau"));
            }
            
            result.close();
            pState.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return et;
    }
    
    //MISE � JOUR
    public int mettreAjourEtudiant(String num, Etudiant et){
        String query="UPDATE etudiant SET Nom=?, Prenom=?, Niveau=? WHERE NumEtudiant=?";
        int nb=0;
        
        try{
            pState=con.getConnexion().prepareStatement(query);
            pState.setString(1, et.getNom());
            pState.setString(2, et.getPrenom());
            pState.setString(3, et.getNiveau());
            pState.setString(4, num);
            nb=pState.executeUpdate();
            
            pState.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return nb;
    }
    
    //SUPPRESSION
    public int supprimerEtudiant(String num){
        String query="DELETE FROM etudiant WHERE NumEtudiant=?";
        int nb=0;
        
        try{
            pState=con.getConnexion().prepareStatement(query);
            pState.setString(1, num);
            nb=pState.executeUpdate();
            
            pState.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return nb;
    }
}
