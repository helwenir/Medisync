package persistence;

import model.dao.DAOSecouriste;
/**
 * Class representing a rescuer using the application.
 * Each rescuer has a unique ID for identification, a last name, a first name,
 * a date of birth, an email, a phone number, and an address.
 * 
 * @author J.Maillard
 */
public class User {

    /**
     * The unique ID of the rescuer.
     */
    private String username;

    /**
     * The last name of the rescuer.
     */
    private String password_hash;

    /**
     * The first name of the rescuer.
     */
    private String role;

    /**
     * The date of birth of the rescuer.
     */
    private int secouriste;
    private int admin;

    

    /*************************************************CONSTRUCTOR************************************ */
    /**
     * 
     * @param username
     * @param password
     * @param role
     * @param prenom
     * @param nom
     * @param Secouriste
     */
    public User(String username, String password, String role, int secouriste, int admin) {
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

        this.username = username;
        this.password_hash = password;
        this.role = role;
        this.secouriste = secouriste;
        this.admin = admin;

    }

    public User(String name, String prenom, String password, String role, int secouriste, int admin) {
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

        String user = name +"." +prenom;
        this.username = user.replaceAll("\\s+", "");
        this.password_hash = password;
        this.role = role;
        this.secouriste = secouriste;
        this.admin = admin;

    }

    /***********************************GETTERS************************************ */
    /**
     * Getter that retrieves the rescuer's skills.
     *
     * @return The rescuer's skills.
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Getter that retrieves the rescuer's ID.
     *
     * @return The ID of the rescuer.
     */
    public String getPassWord() {
        return this.password_hash;
    }


    public String getRole(){
        return this.role;
    }

    public int getSecouriste(){
        return this.secouriste;
    }

    public int getAdmin(){
        return this.admin;
    }

    /***************************************SETTERS*********************************************** */
 
}
