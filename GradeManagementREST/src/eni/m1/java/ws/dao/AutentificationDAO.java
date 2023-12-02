package eni.m1.java.ws.dao;

import eni.m1.java.ws.entities.Utilisateur;

/**
 *
 * @author shirleyodon
 */
public class AutentificationDAO {
    private ProfilDAO profilDAO;
	
	public AutentificationDAO() {
		super();
		profilDAO=new ProfilDAO();
	}
	
	private Utilisateur obtenirProfil(Utilisateur user) {
		Utilisateur profil=profilDAO.obtenirUtilisateur(user.getLogin());
		
		if(profil.getLogin()!=null) {
			if(!profil.getPwd().equals(user.getPwd())) {
				profil.setPwd(null);
			}
		}
		return profil;
		
		/*
		 * 	Si le login est incorrect, on retourne un profil null
		 * 	Si le login et le pwd sont correct, on retourne un profil complet
		 * 	Si le login est correcte mais le pwd est erron�, on retourne un profil dont le pwd est null
		 */
	}
	
	public Utilisateur seConnecter(Utilisateur user) {
		return obtenirProfil(user);
	}
}
