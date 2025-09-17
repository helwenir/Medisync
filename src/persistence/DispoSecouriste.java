package persistence;

/**
 * This class represents a rescuer's availability  
 * Identified by a rescuer and a day  
 */
class DispoSecouriste{

    /**
     * The rescuer who is available  
     */
    private Secouriste secouriste;
    /**
     * The day on which they are available  
     */
    private Journee date;


    /**
     * Class constructor  
     * @param s the rescuer to be made available  
     * @param j the day they are available  
     */
    public DispoSecouriste(Secouriste s, Journee j){
        this.secouriste = s;
        this.date = j;
    }

    /**
     * Gets the secouriste
     * @return the secouriste
     */
    public Secouriste getSecouriste(){
        return this.secouriste;
    }

    /**
     * Gets the date
     * @return the date
     */
    public Journee getJournee(){
        return this.date;
    }
}