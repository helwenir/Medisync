package persistence;

/**
 * This class create a day
 */
public class Journee{

    /**
     * The day of the date
     */
    private int jour;

    /**
     * The month of the date
     */
    private int mois;

    /**
     * The years of the date
     */
    private int annee;

    /**
     * Create an instance of journee
     * @param j
     * @param m
     * @param a
     */
    public Journee(int j, int m, int a){

        this.jour = j;
        this.mois = m;
        this.annee = a;

    }

    /**
     * Getter the day
     * @return the day of the date
     */
    public int getJour(){
        return this.jour;
    }

    /**
     * Getter the month
     * @return the month of the date
     */
    public int getMois(){
        return this.mois;
    }

    /**
     * Getter the years
     * @return the years of the date
     */
    public int getAnnee(){
        return this.annee;
    }

    public String toString(){
        return this.jour+ "/" +this.mois+ "/" +this.annee;
    }
}