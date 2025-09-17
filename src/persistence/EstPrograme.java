package persistence;

class EstPrograme{
    
    /**
     * stores a day for an event
     */
    private Journee uneJournee;
    /**
     * store the dps of an event
     */
    private DPS unDPS;
    
    /**
     *the constructor of the class
     *@param day the day of the event
     *@param dps the dps of the event
     */
    public EstPrograme(Journee day,DPS dps){
        this.uneJournee=day;
        this.unDPS=dps;
    }
    /**
     * changes the day of the event
     * @param j the new day of the event
     */
    public void setJournee(Journee j){
        this.uneJournee=j;
    }
    /**
     * changes the dps of the event
     * @param d the new dps
     */
    public void setDPS(DPS d){
        this.unDPS=d;
    }
    /**
     * get the current day attribute
     * @return the day stored in the attribue
     */
    public Journee getJournee(){
        return this.getJournee();
    }

    /**
     * get the current dps 
     * @return the dps attribute
     */
    public DPS getDPS(){
        return this.unDPS;
    }
}