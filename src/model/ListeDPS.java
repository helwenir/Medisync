package model;

import persistence.*;
import java.util.ArrayList;

import model.dao.DAODps;

public class ListeDPS {
    
    private ArrayList<DPS> listDps;

    public ListeDPS(){

        DAODps dao = new DAODps();
        this.listDps = dao.getListDps();

    }

    public ArrayList<DPS> listDps(){
        return this.listDps;
    }

    public void adddps(DPS dps){
        this.listDps.add(dps);
    }

    public void removeDps(DPS dps){
        this.listDps.remove(dps);
    }

    public DPS dpsById(int id){
        for (DPS dps : this.listDps){
            if(dps.getId() == id){
                return dps;
            }
        }

        return null;
    }

}
