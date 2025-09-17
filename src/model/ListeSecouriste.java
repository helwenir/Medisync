package model;

import java.util.ArrayList;

import model.dao.DAOAffectation;
import model.dao.DAOSecouriste;
import model.dao.DAOSecouriste_Journee;
import persistence.*;


public class ListeSecouriste{

    private ArrayList<String> nomSecouriste;
    private ArrayList<Secouriste> listSec;
    private int imax;
    private int imin;
    private DAOSecouriste daoSec;

    public ListeSecouriste(){
        this.daoSec = new DAOSecouriste();
        DAOSecouriste_Journee daoSecJou = new DAOSecouriste_Journee();
        this.listSec = this.daoSec.listSec();
        this.nomSecouriste = new ArrayList<>();
        DAOAffectation daoAff = new DAOAffectation();
        this.imax=0;
        for(Secouriste sec : this.listSec){
            sec.setDispo(daoSecJou.listDispoBySec(sec.getId()));
            sec.setDps(daoAff.findDpsBySec(sec.getId()));
            this.nomSecouriste.add(sec.toString());
        }
        this.imin=this.nomSecouriste.size();
    }

    public String getNomSecouristeUp() {
    // Décrémenter imin pour aller chercher le nom précédent dans la liste circulaire
        this.imin = (this.imin - 1 + this.nomSecouriste.size()) % this.nomSecouriste.size();
        this.imax = (this.imin + 4) % this.nomSecouriste.size();  // toujours 4 positions après imin (5 noms affichés)

        return this.nomSecouriste.get(this.imin);
    }

    public String getNomSecouristeDown() {
        // Incrémenter imax pour aller chercher le nom suivant dans la liste circulaire
        this.imax = (this.imax + 1) % this.nomSecouriste.size();
        this.imin = (this.imax - 4 + this.nomSecouriste.size()) % this.nomSecouriste.size();  // 4 positions avant imax

        return this.nomSecouriste.get(this.imax);
    }

    public String getNomSecouriste(){
        String ret;
        if(this.imax==this.getLength()){
            ret=this.nomSecouriste.get(this.imax);
            this.imax=0;
        }else{
            ret=this.nomSecouriste.get(this.imax);
            this.imax++;
        }
        return ret;
    }

    private int getLength(){
        return this.nomSecouriste.size()-1;
    }

    public void addNewSecouriste(String newName){
        this.nomSecouriste.add(newName);
    }

    public void removeSecouriste(String name){
        this.nomSecouriste.remove(name);
    }

    public ArrayList<String> getListSecouriste(){
        ArrayList<String> ret=new ArrayList<>();
        for (String s:this.nomSecouriste){
            ret.add(s);
        }
        return ret;
    } 

    public boolean ContainsName(String s){
        return this.nomSecouriste.contains(s);
    }

    public void lowerI(){
        this.imax--;
        this.imin--;
    }

    public Secouriste getSecByName(String name){

        for(int i=0; i<this.nomSecouriste.size(); i++){

            if(this.nomSecouriste.get(i).equals(name)){
                return this.listSec.get(i);
            }
        }

        return null;
    }

    public Secouriste getSecById(int id){

        for(Secouriste sec : this.listSec){
            if (sec.getId() == id){
                return sec;
            }
        }

        return null;
    }
}