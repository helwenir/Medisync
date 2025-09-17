package persistence;

/**
 * Store datas from the table competences
 */
public class Competences {
    /**
     * Store the name of the competences
     */
    String intitule;

    public Competences(String intitule){
        this.intitule = intitule;
    }

    /**
     * Set the name of the competences
     * @param intitule name of the competences
     */
    public void setIntitule(String intitule) {
            this.intitule = intitule;
    }

    /**
     * Get the name of a precise competence
     */
    public String getIntitule() {
        return intitule;
    }

    
}
