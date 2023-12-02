package eni.m1.java.ws.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import eni.m1.java.ws.database.Connexion;
import eni.m1.java.ws.entities.Registre;

/**
 *
 * @author shirleyodon
 */
public class RegistreDAO {
	private Connexion con;
    private Statement state;
    private PreparedStatement pState;
    private ResultSet result;
    
	public RegistreDAO() {
		con=new Connexion();
	}
	
	//AJOUT
	public int ajouterRegistre(Registre reg) {
		String query="INSERT INTO registre(Date, Heure, Login, Action, Cible) VALUES (?, ?, ?, ?, ?)";
		int nb=0;
		
		try {
			pState=con.getConnexion().prepareStatement(query);
			pState.setString(1, reg.getDate());
			pState.setString(2, reg.getHeure());
			pState.setString(3, reg.getLogin());
			pState.setString(4, reg.getAction());
			pState.setString(5, reg.getCible());
			
			nb=pState.executeUpdate();
			pState.close();
		}catch(Exception e){
            e.printStackTrace();
        }
		
		return nb;
	}
    
	//LISTAGE
	public List<Registre> listRegistre(){
		List<Registre> registres=new ArrayList<Registre>();
		String query="SELECT * FROM registre ORDER BY ID DESC";
		
		try {
			state=con.getConnexion().createStatement();
            result=state.executeQuery(query);
            
            while(result.next()) {
            	Registre reg=new Registre();
            	
            	reg.setId(result.getInt("ID"));
            	reg.setDate(result.getString("Date"));
            	reg.setHeure(result.getString("Heure"));
            	reg.setLogin(result.getString("Login"));
            	reg.setAction(result.getString("Action"));
            	reg.setCible(result.getString("Cible"));
            	
            	registres.add(reg);
            }
            
            result.close();
            state.close();
		}catch(Exception e){
            e.printStackTrace();
        }
		
		return registres;
	}
	
	//SUPPRESSION
	public int supprimerRegistre(int id) {
		String query="DELETE FROM registre WHERE ID=?";
        int nb=0;
        
        try{
        	pState=con.getConnexion().prepareStatement(query);
        	pState.setInt(1, id);
            nb=pState.executeUpdate();
            
            pState.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return nb;
	}
	
	public int supprimerRegistre() {
		String query="DELETE FROM registre";
        int nb=0;
        
        try{
            state=con.getConnexion().createStatement();
            nb=state.executeUpdate(query);
            state.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return nb;
	}
	
	//REMETTRE LE COMPTEUR � ZERO : id=1 pour le pr�mier log et ainsi de suite   
	private int logCount() {
		String query="SELECT COUNT(*) AS 'TotalLog' FROM registre";
		int nb=0;
		
		try {
			state=con.getConnexion().createStatement();
            result=state.executeQuery(query);
            
            if(result.next())
                nb=result.getInt("TotalLog");
            
            result.close();
            state.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return nb;
	}
	
	private void reinitialiserCompteur() {
		String query="ALTER TABLE registre AUTO_INCREMENT=0";
		
		try {
			state=con.getConnexion().createStatement();
			state.executeUpdate(query);
			state.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void retablirColonneId() {
		int dernier=logCount(), i=0;
		List<Registre> list=listRegistre();
		
		i=dernier;
		for(Registre reg: list) {
			reg.setId(i);
			i--;
		}
		
		supprimerRegistre();
		reinitialiserCompteur();
		
		for(int j=dernier-1; j>-1; j--)
			ajouterRegistre(list.get(j));
	}
}
