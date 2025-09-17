package vue;

import java.util.ArrayList;
import java.util.List;

import com.fxgraph.graph.ICell;
import com.fxgraph.graph.Graph;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.GrapheModele;
import javafx.scene.layout.Region;
import control.Controller;
import application.App;

public class Cell implements ICell {

    private Text text;
    private List<ICell> parents = new ArrayList<>();
    private List<ICell> children = new ArrayList<>();
    private StackPane wrapper;
    private String style;
    private VueCompetenceSecouriste vueCompetenceSecouriste;
    private GrapheModele modele;

    private double x = 100;
    private double y = 100;

    public Cell(String name, String style, VueCompetenceSecouriste vueCompetenceSecouriste, GrapheModele modele) {
        this.modele = modele;
        this.vueCompetenceSecouriste = vueCompetenceSecouriste;
        this.text = new Text(name);
        this.text.setFont(Font.font("Arial", 15));
        this.text.setStyle("-fx-text-alignment:center;");
        this.text.setTranslateX(5);
        //this.text.setTranslateY(10);
        this.wrapper = new StackPane(text);
        this.wrapper.setPrefSize(200, 75);
        setStyle(style);
        wrapper.setOnMousePressed(null);
        wrapper.setOnMouseDragged(null);
        wrapper.setOnMouseReleased(null);

        this.wrapper.setOnMouseClicked(e -> {
            boolean isEditable = vueCompetenceSecouriste.GetIsEditable();
            if (isEditable) {
                System.out.println("Cell clicked: " + getStyle());
                swapStyle();
                System.out.println("Cell clicked2: " + getStyle());
                modele.modifyStyle(modele.getNoms().indexOf(getName()), getStyle());
                System.out.println("Modele Style After Switch : " + modele.getStyles().get(modele.getNoms().indexOf(getName())));
            }
        });
    }

    public String getName() {
        return text.getText();
    }

    public void setStyle(String style) {
        this.style = style;
        this.wrapper.setStyle(style);
    }

    public String getStyle() {
        return this.style;
    }

    public void swapStyle() {
        if (this.style.contains("lightgreen")) {
            System.out.println("Cell to white");
            this.style = "-fx-background-radius: 25; -fx-border-radius: 10px; -fx-background-color: white;";
        } else {
            System.out.println("Cell to green");
            this.style = "-fx-background-radius: 25; -fx-border-radius: 10px; -fx-background-color: lightgreen;";
        }
     this.wrapper.setStyle (style);
    }

@Override
public Region getGraphic(Graph graph) {
        return wrapper;
    }

    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
        wrapper.setLayoutX(x);
        wrapper.setLayoutY(y);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void removeCellChild(ICell cell) {
        children.remove(cell);
    }

    public List<ICell> getCellParents() {
        return new ArrayList<>(parents);
    }

    public List<ICell> getCellChildren() {
        return new ArrayList<>(children);
    }

    public void addCellParent(ICell cell) {
        if (!parents.contains(cell)) {
            parents.add(cell);
        }
    }

    public void addCellChild(ICell cell) {
        if (!children.contains(cell)) {
            children.add(cell);
        }
    }

    public static void layoutCells(List<Cell> cells, double startX, double startY, int maxPerRow) {
        for (int i = 0; i < cells.size(); i++) {
            int col = i % maxPerRow;
            int row = i / maxPerRow;

            double x = startX + 300 * col;
            double y = startY + 150 * row;

            cells.get(i).setPosition(x, y);
        }
    }

    public double getCenterX() {
        return wrapper.getLayoutX() + wrapper.getWidth() / 2;
    }

    public double getCenterY() {
        return wrapper.getLayoutY() + wrapper.getHeight() / 2;
    }

}
