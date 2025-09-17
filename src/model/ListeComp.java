package model;

import java.util.*;

import model.dao.DAOCompetences;
import persistence.Competences;

public class ListeComp {

    private ArrayList<Competences> listComp ;

    public ListeComp(){
        DAOCompetences dao = new DAOCompetences();
        this.listComp = dao.listComp();
    }

    public ArrayList<Competences> getListComp(){
        return this.listComp;
    }

    public void addComp(Competences comp){
        this.listComp.add(comp);
    }

    public void remove(Competences comp){
        this.listComp.remove(comp);
    }
    
}
