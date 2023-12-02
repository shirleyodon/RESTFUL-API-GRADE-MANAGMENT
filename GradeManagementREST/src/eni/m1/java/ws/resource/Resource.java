package eni.m1.java.ws.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import eni.m1.java.ws.dao.*;
import eni.m1.java.ws.entities.*;

@Path("/resources")
@Produces(MediaType.APPLICATION_JSON+"; charset=utf-8")
@Consumes(MediaType.APPLICATION_JSON)
public class Resource {
	private EtudiantDAO etudiantDAO;
	private MatiereDAO matiereDAO;
    private NoteDAO noteDAO;
    private BulletinDAO bulletinDAO;
    private ClassementDAO classementDAO;
    private AutentificationDAO authentificationDAO;
    private ProfilDAO profilDAO;
    private RegistreDAO registreDAO;
	
    public Resource(){
        etudiantDAO=new EtudiantDAO();
        matiereDAO=new MatiereDAO();
        noteDAO=new NoteDAO();
        bulletinDAO=new BulletinDAO();
        classementDAO=new ClassementDAO();
        authentificationDAO=new AutentificationDAO(); 
        profilDAO=new ProfilDAO();
        registreDAO=new RegistreDAO();
    }
    
//  E	T	U	D	I	A	N	T
    
    /*  AJOUT   ETUDIANT    */
    @POST
    @Path("/etudiants")
    public String addStudent(Etudiant et){
        return etudiantDAO.ajouterEtudiant(et);
    }
    
    /*  LISTAGE ETUDIANT    */
    @GET
    @Path("/etudiants/all")
    public List<Etudiant> allStudents(){
        return etudiantDAO.listEtudiant();
    }
    
    @GET
    @Path("/etudiants/all/{lev}")
    public List<Etudiant> studentsByLevel(@PathParam(value="lev") String lev){
        return etudiantDAO.listEtudiantParNiveau(lev);
    }
    
    @GET
    @Path("/etudiants")
    public List<Etudiant> studentsByKW(@QueryParam(value="kw") String kw){
        return etudiantDAO.listEtudiantParMC(kw);
    }
    
    @GET
    @Path("/etudiants/{num}")
    public Etudiant getStudent(@PathParam(value="num") String num){
        return etudiantDAO.obtenirEtudiant(num);
    }
    
    /*  MISE À JOUR ETUDIANT    */
    @PUT
    @Path("/etudiants/{num}")
    public int updateStudent(@PathParam(value="num") String num, Etudiant et){
        return etudiantDAO.mettreAjourEtudiant(num, et);
    }
    
    /*  SUPPRESSION ETUDIANT    */
    @DELETE
    @Path("/etudiants/{num}")
    public int deleteStudent(@PathParam(value="num") String num){
        return etudiantDAO.supprimerEtudiant(num);
    }
    
//  M	A	T	I	E	R	E
    
    /*	AJOUT	MATIERE		*/
    @POST
    @Path("/matieres")
    public String addSubject(Matiere mat) {
    	return matiereDAO.ajouterMatiere(mat);
    }
    
    /*  LISTAGE MATIERE    */
    @GET
    @Path("/matieres/all")
    public List<Matiere> allSubjects(){
    	return matiereDAO.listMatiere();
    }
    
    @GET
    @Path("/matieres")
    public List<Matiere> subjectsByKW(@QueryParam(value="kw") String kw){
    	return matiereDAO.listMatiereParMc(kw);
    }
    
    @GET
    @Path("/matieres/all/{coef}")
    public List<Matiere> subjectsByCoef(@PathParam(value="coef") int coef){
    	return matiereDAO.listMatiereParCoef(coef);
    }
    
    @GET
    @Path("/matieres/{codeMat}")
    public Matiere getSubject(@PathParam(value="codeMat") String codeMat) {
    	return matiereDAO.obtenirMatiere(codeMat);
    }
    
    /*  MISE À JOUR MATIERE   */
    @PUT
    @Path("/matieres/{codeMat}")
    public int updateSubject(@PathParam(value="codeMat") String codeMat, Matiere mat) {
    	return matiereDAO.mettreAjourMatiere(codeMat, mat);
    }
    
    /*	SUPPRESSION		MATIERE	*/
    @DELETE
    @Path("/matieres/{codeMat}")
    public int deleteSubject(@PathParam(value="codeMat") String codeMat) {
    	return matiereDAO.supprimerMatiere(codeMat);
    }
    
//  N	O	T	E
    
    /*	AJOUT	NOTE	*/
    @POST
    @Path("/notes")
    public int addGrade(Note note) {
    	return noteDAO.ajouterNote(note);
    }
    
    /*	LISTAGE NOTE	*/
    @GET
    @Path("/notes/all")
    public List<Note> allGrades(){
    	return noteDAO.listNote();
    }
    
    @GET
    @Path("/notes/all/{numEt}")
    public List<Note> gradeByStudent(@PathParam(value="numEt") String numEt){
    	return noteDAO.listNoteParEtudiant(numEt);
    }
    
    @GET
    @Path("/notes/all/{codeMat}/{niveau}")
    public List<Note> gradeBySubjectAndLevel(@PathParam(value="codeMat") String codeMat, @PathParam(value="niveau") String niveau){
    	return noteDAO.listNoteParMatiereEtNiveau(codeMat, niveau);
    }
    
    @GET
    @Path("/notes/{numEt}/{codeMat}")
    public Note getGrade(@PathParam(value= "numEt") String numEt, @PathParam(value= "codeMat") String codeMat) {
    	return noteDAO.obtenirNote(numEt, codeMat);
    }
    
    /*	MISE À JOUR		*/
    @PUT
    @Path("/notes/{numEt}/{codeMat}")
    public int updateGrade(@PathParam(value= "numEt") String numEt, @PathParam(value= "codeMat") String codeMat, double note) {
    	return noteDAO.mettreAjourNote(numEt, codeMat, note);
    }
    
    /*	SUPPRESSION		*/
    @DELETE
    @Path("/notes/{numEt}/{codeMat}")
    public int deleteGrade(@PathParam(value= "numEt") String numEt, @PathParam(value= "codeMat") String codeMat) {
    	return noteDAO.supprimerNote(numEt, codeMat);
    }
    
//	B	U	L	L	E	T	I	N
    
    @GET
    @Path("/bulletins/{numEt}")
    public Bulletin  getSchoolReport(@PathParam(value="numEt") String numEtudiant) {
    	return bulletinDAO.genererBulletin(numEtudiant);
    }
    
// 	C	L	A	S	S	E	M	E	N	T
    @GET
    @Path("/classements/all/{niveau}")
    public List<LigneClassement> getClassGrading(@PathParam(value="niveau") String niveau){
    	return classementDAO.genererClassement(niveau);
    }
    
//	A	U	T	H	E	N	T	I	F	I	C	A	T	I	O	N
    @POST
    @Path("/authentifications")
    public Utilisateur login(Utilisateur user) {
    	return authentificationDAO.seConnecter(user);
    }
    
//	P	R	O	F	I	L 		U	T	I	L	I	S	A	T	E	U	R
    
    /*	AJOUT	PROFIL	*/
    @POST
    @Path("/utilisateurs")
    public int addProfile(Utilisateur newProfile) {
    	return profilDAO.ajouterProfil(newProfile);
    }
    
    /*	LISTAGE PROFIL	*/
    @GET
    @Path("/utilisateurs/all")
    public List<Utilisateur> allProfiles(){
    	return profilDAO.listUtilisateurs();
    }
    
    @GET
    @Path("/utilisateurs/{login}")
    public Utilisateur getProfile(@PathParam(value="login") String login) {
    	return profilDAO.obtenirUtilisateur(login);
    }
    
    /*	MISE A JOUR PROFIL	*/
    @PUT
    @Path("/utilisateurs/{currentLogin}")
    public int updateProfile(@PathParam(value="currentLogin") String currentLogin, Utilisateur newProfile) {
    	return profilDAO.mettreAjourProfil(currentLogin, newProfile);
    }
    
    /*	SUPPRESSION PROFIL*/
    @DELETE
    @Path("/utilisateurs/{login}")
    public int deleteProfile(@PathParam(value="login") String login) {
    	return profilDAO.supprimerProfil(login);
    }
    
//	R	E	G	I	S	T	R	E
    
    /*	AJOUT REGISTRE	*/
    @POST
    @Path("/registres")
    public int addLog(Registre reg) {
    	return registreDAO.ajouterRegistre(reg);
    }
    
    /*	LISTAGE REGISTRE	*/
    @GET
    @Path("/registres/all")
    public List<Registre> allLogs(){
    	return registreDAO.listRegistre();
    }
    
    @GET
    @Path("/registres/retablir")
    public void restoreId() {
    	registreDAO.retablirColonneId();
    }
    
    /*	SUPPRESSION		*/
    @DELETE
    @Path("/registres/all")
    public int deleteLog() {
    	return registreDAO.supprimerRegistre();
    }
    
    @DELETE
    @Path("/registres/{id}")
    public int deleteLog(@PathParam(value="id") int id) {
    	return registreDAO.supprimerRegistre(id);
    }
}
