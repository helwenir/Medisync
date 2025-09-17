package persistence;

public class Site{
    /**
     * store the 
     */
    private String code; 
    /**
     * the name of the site
     */
    private String nom;
    /**
     * strore the longitude 
     */
    private double longitude;
    /**
     * store the latitudde
     */
    private double latitude;

    /**
     * the constructor of the class
     * @param code the codename 
     * @param nom the nam of the site
     * @param longi the longitude of the site
     * @param lati the latitude of  the site
     */
    public Site(String code,String nom,double longi,double lati){
        /*
        if(code==null || code.isEmpty()){
            this.code="pas de code";
        }else{
            this.code=code;
        }if(nom==null || nom.isEmpty()){
            this.nom="no name";
        }else{
            this.nom=nom;
        }
        if(longi<-180 || longi>180){
            this.longitude=0;
        }else{
            this.longitude=longi;
        }if(lati<-180 || lati>180){
            this.latitude=0;
        }else{
            this.latitude=lati;
        }
            */
        this.code=code;
        this.nom=nom;
        this.longitude=longi;
        this.latitude=lati;
    }
    /**
     * changes the code attribute
     * @param c the new code
     */
    public void setCode(String c){
        /*
        if(c==null || c.isEmpty()){
            System.out.println("erreur de code , le code n'a pas changé");
        }else{
            this.code=c;
        }
        */
        this.code=c;
    }
    /**
     * changes the nom attribure
     * @param n the new name
     */
    public void setNom(String n){
        /**       if(n==null || n.isEmpty()){
            System.out.println("erreur de nom , le nom n'a pas changé");
        }else{
            this.nom=n;
        }
         */
        this.nom=n;
    }

    /**
     * changes the longitude
     * @param l the new longitude
     */
    public void setLongitude(float l){
        /*
        if(l>180 || l<-180){
            this.longitude=0;
        }else{
            this.longitude=l;
        */
    }

    /**
     * changes the latitude
     * @param l the new latidue
     */
    public void setLatitude(float l){
        /*
        if(l>180 || l<-180){
            this.latitude=0;
        }else{
            this.latitude=l;
        }
        */
        this.latitude=l;
    }

    /**
     * gets the current code
     * @return the attribute code
     */
    public String getCode(){
        return this.code;
    }

    /**
     * get the current name
     * @return the name attribute
     */
    public String getNom(){
        return this.nom;
    }

    /**
     * get the current longitude
     * @return the longitude attribute
     */
    public double getLongitude(){
        return this.longitude;
    }

    /**
     * gets the current latidute
     * @return the lattitude attribute
     */
    public double getLatitude(){
        return this.latitude;
    }
} 