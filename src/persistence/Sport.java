/**
 * package name of sport class
 */
package persistence;

/**
 * class which create sport and return
 */
public class Sport{
	
	/**
	 * attribut of object sport (String type)
	 */
	private String Code;
	
	/**
	 * attribut of object sport (String type)
	 */
	private String nom;
	
	/**
	 * constructor method 
	 * @param c -Code of sport (String type)
	 * @param n -name of sport (String type)
	 */
	public Sport(String c, String n){
		this.Code = c;
		this.nom = n;
	}
	
	/**
	 * method getter which return the Code of sport
	 * @return (String type)
	 */
	public String getCode(){
		return this.Code;
	}
	
	/**
	 * method getter which return the name of sport
	 * @return (String type)
	 */
	public String getNom(){
		return this.nom;
	}
	
	/**
	 * method setter which modified the Code of sport
	 * @param c -Code of sport (String type) 
	 */
	public void setCode(String c){
		this.Code = c;
		
	}
	
	/**
	 * method setter which modified the name of sport
	 * @param n -name of sport (String type) 
	 */
	public void setNom(String n){
		this.nom = n;
		
	}
}
			
