package model;

import java.util.ArrayList;
import java.util.List;
import vue.Cell;

public class GrapheModele {

    // Instance unique statique
    private static GrapheModele instance;

    private List<Cell> cells;
    private List<String> styles;

    // Constructeur privé pour empêcher l'instanciation directe
    private GrapheModele() {
        cells = new ArrayList<>();
        styles = new ArrayList<>();
    }

    // Méthode publique pour récupérer l'instance unique (lazy instantiation)
    public static synchronized GrapheModele getInstance() {
        if (instance == null) {
            instance = new GrapheModele();
        }
        return instance;
    }

    public void addCell(Cell cell) {
        ArrayList<String> names = getNoms();
        if (!names.contains(cell.getName())) {
            cells.add(cell);
            styles.add(cell.getStyle());
        }
    }

    public ArrayList<String> getNoms() {
        ArrayList<String> names = new ArrayList<>();
        for (int i = 0; i < this.cells.size(); i++) {
            names.add(cells.get(i).getName());
        }
        return names;
    }

    public List<Cell> getCells() {
        return this.cells;
    }

    public List<String> getStyles() {
        return this.styles;
    }

    public void modifyStyle(int index, String newStyle) {
        if (index >= 0 && index < styles.size()) {
            styles.set(index, newStyle);
        }
    }

    // Optionnel : méthode pour réinitialiser les données (utile pour tests ou reset)
    public void clear() {
        cells.clear();
        styles.clear();
    }
}
