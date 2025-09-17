package persistence;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

/**
 * Store datas from the table Besoin and make the link between DPS and competences
 */
public class Besoin {
    /**
     * Store the number of peoples needed for a DPS and a precise competences
     */
    private int dps;
    private String comp;
    private int nombre;

    public Besoin(int idDps, String comp, int nombre){
        this.dps = idDps;
        this.comp = comp;
        this.nombre = nombre;
    }

    /*
     public void setNombre(int[] idDPS, String[] idComp, int[] nombre) {
        for (int i = 0; i < idDPS.length; i++) {
            HashMap<Competences, Integer> mapDPSComp = new HashMap<Competences, Integer>();
            mapDPSComp.put(idComp[i], (Integer)nombre[i]);
            this.nombre.put((int)idDPS[i], mapDPSComp);
        }
     }
*/

    /**
     * Get the number of peoples needed for a DPS and a precise competence
     */
    public int getNombre() {
        return this.nombre;
    }

    public String getComp(){
        return this.comp;
    }

    public int getDps(){
        return this.dps;
    }

    public void addCompetence(int valeur) {
        this.nombre += valeur;
    }



}
