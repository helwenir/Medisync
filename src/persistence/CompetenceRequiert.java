package persistence;

/**
 * Class that lists the skills required 
 * to obtain another skill.
 * 
 * @author M.MATHOURAIS
 */
public class CompetenceRequiert {
    
    /**
     * The desired skill.
     */
    private Competences laCompetence;
    
    /**
     * The skill required to obtain the desired skill.
     */
    private Competences competenceRequise;

    /***
     * Constructor of the class, instantiates the skill hierarchy.
     * @param compReq the required skill
     * @param laComp the desired skill
     */
    public CompetenceRequiert(Competences laComp, Competences compReq ){
        this.laCompetence = laComp;
        this.competenceRequise = compReq;
    }

    /**
     * Gets the desired skill.
     * @return the desired skill
     */
    public Competences getLaCompetence(){
        return this.laCompetence;
    }

    /**
     * Gets the required skill.
     * @return the required skill
     */
    public Competences getCompetenceRequiert(){
        return this.competenceRequise;
    }
}
