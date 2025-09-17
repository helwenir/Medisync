package persistence;

import model.dao.DAOSecouriste;
import model.dao.DAOSecouriste_Journee;
import java.util.*;
/**
 * Class representing a rescuer using the application.
 * Each rescuer has a unique ID for identification, a last name, a first name,
 * a date of birth, an email, a phone number, and an address.
 * 
 * @author J.Maillard
 */
public class Secouriste {

    /**
     * The list of skills of the rescuer.
     */
    DAOSecouriste_Journee dao;
    private Competences[] competence;

    private ArrayList<Integer> listDps;
    private ArrayList<Journee> listDispo;

    /**
     * The unique ID of the rescuer.
     */
    private int id;

    /**
     * The last name of the rescuer.
     */
    private String nom;

    /**
     * The first name of the rescuer.
     */
    private String prenom;

    /**
     * The date of birth of the rescuer.
     */
    private String dateNaissance;

    /**
     * The email address of the rescuer.
     */
    private String email;

    /**
     * The phone number of the rescuer.
     */
    private String tel;

    /**
     * The address of the rescuer.
     */
    private String adresse;

    /*************************************************CONSTRUCTOR************************************ */
    /**
     * Constructor for the Secouriste class.
     *
     * @param competence The rescuer's skills.
     * @param id The ID of the rescuer.
     * @param nom The last name of the rescuer.
     * @param prenom The first name of the rescuer.
     * @param dateNaissance The date of birth of the rescuer.
     * @param email The email of the rescuer.
     * @param tel The phone number of the rescuer.
     * @param adresse The address of the rescuer.
     */
    public Secouriste(int id, String nom, String prenom, String dateNaissance, String email, String tel, String adresse) {
        /*if (id <= 0) {
            throw new IllegalArgumentException("ID must be a positive number.");
        }
        if (nom == null || nom.trim().isEmpty()) {
            throw new IllegalArgumentException("Last name cannot be empty.");
        }
        if (prenom == null || prenom.trim().isEmpty()) {
            throw new IllegalArgumentException("First name cannot be empty.");
        }
        if (dateNaissance == null || dateNaissance.trim().isEmpty()) {
            throw new IllegalArgumentException("Date of birth cannot be empty.");
        }
        if (email == null || !email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new IllegalArgumentException("Invalid email format.");
        }
        if (tel == null || !tel.matches("^\\+?[0-9]{8,15}$")) {
            throw new IllegalArgumentException("Invalid phone number format.");
        }
        if (adresse == null || adresse.trim().isEmpty()) {
            throw new IllegalArgumentException("Address cannot be empty.");
        }*/

        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.email = email;
        this.tel = tel;
        this.adresse = adresse;

        this.dao = new model.dao.DAOSecouriste_Journee();
        this.listDispo = dao.listDispoBySec(id);

    }

    public Secouriste(String nom, String prenom, String dateNaissance, String email, String tel, String adresse) {
        
        DAOSecouriste dao = new DAOSecouriste();

        this.id = dao.idDispo();
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.email = email;
        this.tel = tel;
        this.adresse = adresse;
        
    }

    /***********************************GETTERS************************************ */
    /**
     * Getter that retrieves the rescuer's skills.
     *
     * @return The rescuer's skills.
     */
    public Competences[] getCompetence() {
        return competence;
    }

    public ArrayList<Integer> getDps(){
        return this.listDps;
    }

    public ArrayList<Journee> getDispo(){
        return this.listDispo;
    }

    /**
     * Getter that retrieves the rescuer's ID.
     *
     * @return The ID of the rescuer.
     */
    public int getId() {
        return id;
    }

    /**
     * Getter that retrieves the rescuer's last name.
     *
     * @return The last name of the rescuer.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Getter that retrieves the rescuer's first name.
     *
     * @return The first name of the rescuer.
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Getter that retrieves the rescuer's date of birth.
     *
     * @return The date of birth of the rescuer.
     */
    public String getDateNaissance() {
        return dateNaissance;
    }

    /**
     * Getter that retrieves the rescuer's email.
     *
     * @return The email of the rescuer.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Getter that retrieves the rescuer's phone number.
     *
     * @return The phone number of the rescuer.
     */
    public String getTel() {
        return tel;
    }

    /**
     * Getter that retrieves the rescuer's address.
     *
     * @return The address of the rescuer.
     */
    public String getAdresse() {
        return adresse;
    }

    /***************************************SETTERS*********************************************** */
    /**
     * Setter that sets the rescuer's skills.
     *
     * @param competence The rescuer's skills.
     */
    public void setCompetence(Competences[] competence) {
        this.competence = competence;
    }

    /**
     * Setter that sets the rescuer's last name.
     *
     * @param nom The last name of the rescuer.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Setter that sets the rescuer's first name.
     *
     * @param prenom The first name of the rescuer.
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * Setter that sets the rescuer's date of birth.
     *
     * @param dateNaissance The date of birth of the rescuer.
     */
    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    /**
     * Setter that sets the rescuer's email.
     *
     * @param email The email of the rescuer.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Setter that sets the rescuer's phone number.
     *
     * @param tel The phone number of the rescuer.
     */
    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * Setter that sets the rescuer's address.
     *
     * @param adresse The address of the rescuer.
     */
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    
    public String toString(){
        return this.nom +" "+ this.prenom;
    }

    private boolean aCompetion(Competences c){

        String comp = c.getIntitule();

        for(Competences competence : this.competence){
            if(c.getIntitule().equals(competence.getIntitule())){
                return true;
            }
        }

        return false;
    }

    /*
     * -1 si deja dans la liste
     */
    public int addCompetence(Competences c){

        if(aCompetion(c)){
            return -1;
        
        }else{
            int taille = this.competence.length;
            Competences[] listComp = new Competences[taille+1];
            for(int i=0; i<taille; i++){
                listComp[i] = this.competence[i];
            }
            listComp[taille] = c;
            return 1;
        }
    }

    public int removeCompetence(Competences c) {
        int taille = this.competence.length;
        boolean trouve = false;

        for (int i = 0; i < taille; i++) {
            if (this.competence[i].getIntitule().equals(c.getIntitule())) {
                trouve = true;
                break;
            }
        }

        if (!trouve) {
            return -1; 
        }

        Competences[] nouvelleListe = new Competences[taille - 1];
        int index = 0;

        for (int i = 0; i < taille; i++) {
            if (!this.competence[i].getIntitule().equals(c.getIntitule())) {
                nouvelleListe[index++] = this.competence[i];
            }
        }

        this.competence = nouvelleListe;
        return 1;
    }

    public void addDps(DPS dps){
        if( !this.listDps.contains(dps.getId())){
            this.listDps.add(dps.getId());
        }
    }

    public void removeDps(DPS dps){
        for(int id : this.listDps){
            if(id == dps.getId()){
                this.listDispo.remove(id);
            }
            break;
        }
    }

    public void addDispo(Journee j){
        this.listDispo.add(j);
        DAOSecouriste_Journee daoSecouriste_Journee = new DAOSecouriste_Journee();
        daoSecouriste_Journee.create(this, j);
    }

    public void removeDispo(Journee j){
        System.out.println("remove");
        for(Journee jour : this.listDispo){
            if(j.getJour() == jour.getJour() && j.getMois()==jour.getMois() && j.getAnnee()==jour.getAnnee()){
                DAOSecouriste_Journee daoSecouriste_Journee = new DAOSecouriste_Journee();
                daoSecouriste_Journee.delete(this, j);
                this.listDispo.remove(jour);
                break;
            }
        }
    }

    public void setDispo(ArrayList<Journee> list){
        this.listDispo = list;
    }

    public void setDps(ArrayList<Integer> list){
        this.listDps = list;
    }
}

