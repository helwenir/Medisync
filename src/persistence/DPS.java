package persistence;

import java.util.List;
import java.util.ArrayList;

import model.dao.DAODps;

/**
 * this class create a assignment schedule
 */
public class DPS{
	
	/**
	 * attribut of object DPS (long type)
	 */
	private int id;
	
	/**
	 * attribut of object DPS (int type)
	 */
	private int horaire_depart;
	
	/**
	 * attribut of object DPS (int type)
	 */
	private int horaire_fin;

	private ArrayList<Secouriste> listSec;

	private int j;
	private int m;
	private int a;
	private String site;
	private String sport;
	
	/**
	 * constructor method
	 * @param id -the id of DPS (long type)
	 * @param hDep -the departure time of DPS (int type)
	 * @param hFin -the end of schedule of DPS (int type)
	 */
	public DPS(int id, int hDep, int hFin, int j, int m, int a, String site, String sport){
		this.id = id;
		this.horaire_depart = hDep;
		this.horaire_fin = hFin;
		this.j = j;
		this.m = m;
		this.a = a;
		this.site = site;
		this.sport = sport;
	}

	public DPS( int hDep, int hFin, int j, int m, int a, String site, String sport){
		DAODps daoDps = new DAODps();
		List<Integer> ids = daoDps.getIdDps();

		if (ids.isEmpty()) {
			this.id = 1; // Aucun ID existant, on commence à 1
		} else {
			this.id = ids.get(ids.size() - 1) + 1; // Dernier ID + 1
		}
		System.out.println(this.id);
		this.horaire_depart = hDep;
		this.horaire_fin = hFin;
		this.j = j;
		this.m = m;
		this.a = a;
		this.site = site;
		this.sport = sport;
	}
	/**
	 * method getter which return the id of DPS
	 * @return (long type)
	 */	
	public int getId(){
		return this.id;
	}
	
	/**
	 * method getter which return departure time of DPS
	 * @return (int type)
	 */
	public int getHoraireDepart(){
		return this.horaire_depart;
	}
	
	/**
	 * method getter which return end of schedule of DPS
	 * @return (int type)
	 */
	public int getHoraireFin(){
		return this.horaire_fin;
	}

	public int getJour(){
		return this.j;
	}

	public int getMois(){
		return this.m;
	}

	public int getAnnee(){
		return this.a;
	}

	public String getSite(){
		return this.site;
	}

	public String getSport(){
		return this.sport;
	}
	
	/**
	 * method setter which modified the departure time
	 * @param dep -departure time (int type)
	 */
	public void setHoraireDepart(int dep){
		this.horaire_depart = dep;
	}
	
	/**
	 * method setter which modified the end of schedule
	 * @param fin -end of schedule (int type)
	 */
	public void setHoraireFin(int fin){
		this.horaire_fin = fin;
	}
}
