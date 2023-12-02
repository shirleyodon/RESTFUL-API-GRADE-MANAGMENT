package eni.m1.java.ws.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import eni.m1.java.ws.database.Connexion;
import eni.m1.java.ws.entities.Utilisateur;

/**
 *
 * @author shirleyodon
 */
public class ProfilDAO {
	private Connexion con;
	private Statement state;
	private PreparedStatement pState;
	private ResultSet result;
	
	public ProfilDAO() {
		super();
		con=new Connexion();
	}
	
	private PreparedStatement remplirStatement(PreparedStatement pState, Utilisateur profil, String oldLogin) {
		try {
			/*int nbParametre=pState.getParameterMetaData().getParameterCount();*/
			
			pState.setString(1, profil.getNom());
			pState.setString(2, profil.getPrenom());
			pState.setString(3, profil.getLogin());
			pState.setString(4, profil.getPwd());
			pState.setString(5, profil.getStatut());
			
			if(oldLogin != null)
				pState.setString(6, oldLogin);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return pState;	
	}
	
	private Utilisateur genererProfil(ResultSet result) {
		Utilisateur profil=new Utilisateur();
		try {
			profil.setNom(result.getString("NomUtilisateur"));
			profil.setPrenom(result.getString("PrenomUtilisateur"));
			profil.setLogin(result.getString("Login"));
			profil.setPwd(result.getString("Password"));
			profil.setStatut(result.getString("Statut"));
		}catch(Exception e) {
			e.printStackTrace();
		}
		return profil;
	}
	
	//AJOUT
	public int ajouterProfil(Utilisateur newProfile) {
		String query="INSERT INTO utilisateur(NomUtilisateur, PrenomUtilisateur, Login, Password, Statut) VALUES(?, ?, ?, ?, ?)";
		int added;
		try {
			pState=con.getConnexion().prepareStatement(query);
			added=remplirStatement(pState, newProfile, null).executeUpdate();
			
			pState.close();
		}catch(MySQLIntegrityConstraintViolationException e) {
			System.out.println("Le login existe deja : "+e.getLocalizedMessage());
			return -1;
		}catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
		
		return added;
	}
	
	//LISTAGE
	public List<Utilisateur> listUtilisateurs(){
		List<Utilisateur> utilisateurs=new ArrayList<Utilisateur>();
		String query="SELECT * FROM utilisateur";
		try {
			state=con.getConnexion().createStatement();
			result=state.executeQuery(query);
			while(result.next())
				utilisateurs.add(genererProfil(result));
			
			result.close();
			state.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return utilisateurs;
	}
	
	public Utilisateur obtenirUtilisateur(String login) {
		Utilisateur user=new Utilisateur();
		String query="SELECT * FROM utilisateur WHERE Login=?";
		try {
			pState=con.getConnexion().prepareStatement(query);
			pState.setString(1, login);
			result=pState.executeQuery();
			
			if(result.next())
				user=genererProfil(result);
			
			result.close();
			pState.close();
		}catch(Exception e) {
			e.printStackTrace();
		}	
		return user;
	}
	
	//MISE A JOUR
	public int mettreAjourProfil(String oldLogin, Utilisateur newProfile) {
		int added;
		if(oldLogin.equals(newProfile.getLogin())) {
			String query="UPDATE utilisateur SET NomUtilisateur=?, PrenomUtilisateur=?, Password=?, Statut=? WHERE Login= ?";
			try {
				pState=con.getConnexion().prepareStatement(query);
				pState.setString(1, newProfile.getNom());
				pState.setString(2, newProfile.getPrenom());
				pState.setString(3, newProfile.getPwd());
				pState.setString(4, newProfile.getStatut());
				pState.setString(5, oldLogin);
				added=pState.executeUpdate();
				
				pState.close();
			}catch(Exception e) {
				e.printStackTrace();
				return 2;
			}
		}else {
			String query="UPDATE utilisateur SET NomUtilisateur=?, PrenomUtilisateur=?, Login=?, Password=?, Statut=? WHERE Login= ?";
			try {
				pState=con.getConnexion().prepareStatement(query);
				added=remplirStatement(pState, newProfile, oldLogin).executeUpdate();
				
				pState.close();
			}catch(MySQLIntegrityConstraintViolationException e) {
				System.out.println("Le login existe deja : "+e.getLocalizedMessage());
				return -1;
			}catch(Exception e) {
				e.printStackTrace();
				return 2;
			}
		}
		
		/*
		 * Return meaning
		 * 	-1: a same login exist already
		 * 	0: oldLogin doesn't exist
		 * 	1: successfull update
		 * 	2: others errors
		 * */
		return added;
	}
	
	//SUPPRESSION
	public int supprimerProfil(String login) {
		String query="DELETE FROM utilisateur WHERE Login=?";
		int nb=0;
		try {
			pState=con.getConnexion().prepareStatement(query);
			pState.setString(1, login);
			nb=pState.executeUpdate();
			pState.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return nb;
	}
}
