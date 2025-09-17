package vue;

import java.util.ArrayList;
import java.util.List;

import com.fxgraph.graph.Graph;
import control.Controller;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.GrapheModele;
import persistence.Admin;
import persistence.Secouriste;
import persistence.User;
import javafx.scene.Group;
import javafx.scene.Scene;
import model.dao.*;
import vue.Cell;

public class VueEditCompetenceAdmin extends BorderPane {

        
    private Controller control;
    private DAOUser daoUser;
    private DAOSecouriste daoSec;
    private DAOAdmin daoAdmin;
    private User user;
    private String username;
    private Admin admin;
    private Secouriste sec;


    private HBox topBar;
    private HBox centerPart;
    private Image logo;
    private GridPane mainPane;
    private Button SecouristeButton;
    private Button DPSButton;
    private Button deconnexionButton;
    private Label nomSecouriste;
    private ImageView imageViewLogo;
    private ImageView imageViewDeconnexion;

    private VBox centerBox;
    private HBox box1;

    private Text compText;
    private Button editButton;
    private ImageView editImage;

    private HBox compBox1;

    private Button comp1Button;
    private Button comp2Button;
    private Button comp3Button;
    private Button comp4Button;
    private Button comp5Button;

    private HBox compBox2;

    private Button comp6Button;
    private Button comp7Button;

    private HBox compBox3;

    private Button comp8Button;
    private Button comp9Button;
    private Button saveButton;
    private Button addCompButton;

    private HBox blankBox;

    private Pane graphPane;
    private Graph graph;
    private VueCompetenceSecouriste vue;
    private GrapheModele g;

    public VueEditCompetenceAdmin(Controller c, VueCompetenceSecouriste vue,String username,User u) {

        this.control = c;
        this.daoUser = new DAOUser();
        this.daoAdmin = new DAOAdmin();
        this.daoSec = new DAOSecouriste();
        this.user = u;
        this.admin = this.daoAdmin.findById(this.user.getAdmin());
    

        this.vue = vue;
        this.g = GrapheModele.getInstance();
        this.admin = this.daoAdmin.findById(this.user.getAdmin());
        this.topBar = new HBox();
        this.topBar.setMaxWidth(1920);
        this.topBar.setStyle("-fx-background-color: #082361;");

        this.centerPart = new HBox(10);

        logo = new Image("file:../Image/medisync_logo_white.png");
        this.imageViewLogo = new ImageView(logo);
        this.imageViewLogo.setFitWidth(160);
        this.imageViewLogo.setFitHeight(160);
        this.imageViewLogo.setPreserveRatio(true);
        this.imageViewLogo.setTranslateX(100);

        this.SecouristeButton = new Button("Les Secouristes");
        this.SecouristeButton.setMinWidth(130);
        this.SecouristeButton.setStyle("-fx-background-radius: 25px; -fx-border-radius: 25px; -fx-background-color: white;");
        this.SecouristeButton.setFont(Font.font("Arial", 28));
        this.SecouristeButton.setTranslateX(-200);
        this.SecouristeButton.setTranslateY(15);
        this.SecouristeButton.setOnAction(this.control);

        this.DPSButton = new Button("Les DPS");
        this.DPSButton.setMinWidth(130);
        this.DPSButton.setStyle("-fx-background-radius: 25px; -fx-border-radius: 25px; -fx-background-color: white;");
        this.DPSButton.setFont(Font.font("Arial", 28));
        this.DPSButton.setTranslateX(-75);
        this.DPSButton.setTranslateY(15);
        this.DPSButton.setOnAction(this.control);

        this.nomSecouriste = new Label(this.admin.getPrenom()+ " "+this.admin.getNom()+ " \n Admin");
        this.nomSecouriste.setStyle("-fx-text-fill: white; -fx-font-size: 20px; -fx-alignment: center; -fx-text-alignment: center;");
        this.nomSecouriste.setMinWidth(140);
        this.nomSecouriste.setTranslateX(-400);
        this.nomSecouriste.setTranslateY(20);

        this.imageViewDeconnexion = new ImageView("file:../Image/sortie.png");
        this.imageViewDeconnexion.setFitHeight(80);
        this.imageViewDeconnexion.setFitWidth(80);
        this.imageViewDeconnexion.setPreserveRatio(true);

        this.deconnexionButton = new Button();
        this.deconnexionButton.setGraphic(this.imageViewDeconnexion);
        this.deconnexionButton.setMinWidth(40);
        this.deconnexionButton.setOnAction(this.control);
        this.deconnexionButton.setTranslateX(-150);
        this.deconnexionButton.setTranslateY(5);
        this.deconnexionButton.setStyle("-fx-background-radius: 25px; -fx-border-radius: 25px; -fx-background-color: white;");

        Region spacerLeft = new Region();
        Region spacerRight = new Region();
        HBox.setHgrow(spacerLeft, Priority.ALWAYS);
        HBox.setHgrow(spacerRight, Priority.ALWAYS);

        RowConstraints topPart = new RowConstraints();
        topPart.setPercentHeight(60);

        this.topBar.getChildren().addAll(this.imageViewLogo, spacerLeft, this.SecouristeButton, this.DPSButton, spacerRight, this.nomSecouriste, this.deconnexionButton);

        this.centerBox = new VBox();
        this.centerBox.setStyle(("-fx-background-color: #545454;"));

        this.box1 = new HBox();
        this.box1.setMinHeight(150);

        this.compText = new Text("Les compétences : "+username);
        this.compText.setFont(Font.font("Arial", 32));
        this.compText.setTranslateX(100);
        this.compText.setTranslateY(50);

        this.editImage = new ImageView(new Image("file:../Image/pencil.png"));
        this.editImage.setFitHeight(75);
        this.editImage.setFitHeight(75);
        this.editImage.setPreserveRatio(true);

        this.editButton = new Button();
        this.editButton.setStyle("-fx-background-radius: 25; -fx-border-radius: 25px; -fx-background-color: white;");
        this.editButton.setGraphic(this.editImage);
        this.editButton.setMaxSize(150, 75);
        this.editButton.setTranslateY(25);
        this.editButton.setTranslateX(1200);

        this.box1.getChildren().addAll(this.compText, this.editButton);
        this.box1.setStyle("-fx-background-color : #f7f4f4 ;");

        this.compBox1 = new HBox();
        this.compBox1.setTranslateY(50);
        this.compBox1.setStyle("-fx-background-color: #545454;");

        this.saveButton = new Button("Sauvegarder");
        this.saveButton.setFont(Font.font("Arial", 32));
        this.saveButton.setStyle("-fx-background-radius: 25; -fx-border-radius: 25px; -fx-background-color: #5fc785;-fx-text-alignment:center;");
        this.saveButton.setMinSize(300, 75);
        this.saveButton.setTranslateX(-1100);
        this.saveButton.setTranslateY(600);
        this.saveButton.setOnAction(this.control);

        this.addCompButton=new Button("Ajouter une compétence");
        this.addCompButton.setStyle("-fx-background-radius: 25; -fx-border-radius: 25px; -fx-background-color: #5f8fc7;-fx-text-alignment:center;");
        this.addCompButton.setFont(Font.font("Arial", 32));
        this.addCompButton.setMinSize(400, 75);
        this.addCompButton.setTranslateX(-100);
        this.addCompButton.setTranslateY(600);
        this.addCompButton.setOnAction(this.control);

        mainPane = new GridPane();
        graph = new Graph();
        graphPane = new Pane();

        mainPane.add(graphPane, 0, 0);
        compBox1.getChildren().addAll(mainPane, this.saveButton,this.addCompButton);

        this.setStyle("-fx-background-color: #f7f4f4");
        this.setTop(topBar);
        this.setCenter(this.centerBox);

        this.blankBox = new HBox();
        this.blankBox.setTranslateY(350);
        this.blankBox.setStyle("-fx-background-color: #f7f4f4;");
        this.blankBox.setMinSize(1920, 200);

        this.centerBox.getChildren().addAll(this.box1, this.compBox1, this.blankBox);

        this.setStyle("-fx-background-color: #f7f4f4");
        this.setTop(topBar);
        this.setCenter(this.centerBox);

        drawEdges(g);
    }

    public Button getDeconnexionButton() {
        return this.deconnexionButton;
    }

    public Button getSecouristeButton() {
        return this.SecouristeButton;
    }

    public Button getAddCompButton(){
        return this.addCompButton;
    }

    public Button getSaveButton(){
        return this.saveButton;
    }
    

    public void switchColorButton(Button b) {
        if (b.getStyle().equals("-fx-background-radius: 25; -fx-border-radius: 25px; -fx-background-color: #c6e5b1;-fx-text-alignment:center;")) {
            b.setStyle("-fx-background-radius: 25; -fx-border-radius: 25px; -fx-background-color: white;-fx-text-alignment:center;");
        } else if (b.getStyle().equals("-fx-background-radius: 25; -fx-border-radius: 25px; -fx-background-color: white;-fx-text-alignment:center;")) {
            b.setStyle("-fx-background-radius: 25; -fx-border-radius: 25px; -fx-background-color: #c6e5b1;-fx-text-alignment:center;");
        }
    }

    public Pane getPaneGraph() {
        return graphPane;
    }

    public void drawEdges(GrapheModele modele) {
        graphPane.getChildren().clear(); // vide le visuel

        for (Cell cell : modele.getCells()) {
            // Important : s'assurer que le noeud graphique de la cellule est bien généré ou mis à jour
            graphPane.getChildren().add(cell.getGraphic(null)); // ajouter la vue graphique au pane
        }
        // Initialisation des styles uniquement si nécessaire
        if (modele.getStyles() == null || modele.getStyles().isEmpty()) {
            for (int i = 0; i < 9; i++) {
                modele.getStyles().add("-fx-background-radius: 25; -fx-border-radius: 10px; -fx-background-color: white;");
            }
        }

        List<String> savedStyles = modele.getStyles(); // Pas besoin de copier
        graphPane.getChildren().clear(); // On vide uniquement l'affichage graphique

        // Si les cellules sont déjà créées, on les réutilise
        if (modele.getCells().isEmpty()) {
            modele.addCell(new Cell("Premiers Secours en \n équipe Niveau : 1", savedStyles.get(0), this.vue, modele));
            modele.addCell(new Cell("Premiers Secours en \n équipe Niveaux : 2", savedStyles.get(0), this.vue, modele));
            modele.addCell(new Cell("Chef d’équipe", savedStyles.get(0), this.vue, modele));
            modele.addCell(new Cell("Chef de Poste", savedStyles.get(0), this.vue, modele));
            modele.addCell(new Cell("Cadre Opérationnel", savedStyles.get(0), this.vue, modele));
            modele.addCell(new Cell("Secouriste, \n Sauveteur Aquatique", savedStyles.get(0), this.vue, modele));
            modele.addCell(new Cell("Conducteur de \n Véhicule de Premier \n Secours à Personnes", savedStyles.get(0), this.vue, modele));
            modele.addCell(new Cell("Pilote Bateau Côtier", savedStyles.get(0), this.vue, modele));
            modele.addCell(new Cell("Pilote Bateau Fluvial", savedStyles.get(0), this.vue, modele));
        }

        System.out.println("Edit Hashcode : " + modele.getCells().hashCode());
        List<Cell> allCells = modele.getCells();

        System.out.println(allCells.size());

        List<Cell> cells1 = allCells.subList(0, 7);
        List<Cell> cells2 = allCells.subList(7, allCells.size());

        // Disposition des cellules
        Cell.layoutCells(cells1, 90, 0, 5);
        Cell.layoutCells(cells2, 800, 500, 5);

        // Ajouter les nodes graphiques au pane
        for (int i = 0; i < allCells.size(); i++) {
            Cell cell = allCells.get(i);
            cell.setStyle(savedStyles.get(i));
            System.out.println("Cell Style Edit: " + cell.getStyle());
            graphPane.getChildren().add(cell.getGraphic(null));
        }

        // Arêtes entre les cellules
        graphPane.getChildren().add(createDirectedEdge(
                allCells.get(1).getCenterX(), allCells.get(1).getCenterY(),
                allCells.get(2).getCenterX(), allCells.get(2).getCenterY(), 200, 75));

        graphPane.getChildren().add(createDirectedEdge(
                allCells.get(3).getCenterX(), allCells.get(3).getCenterY(),
                allCells.get(2).getCenterX(), allCells.get(2).getCenterY(), 200, 75));

        graphPane.getChildren().add(createDirectedEdge(
                allCells.get(0).getCenterX(), allCells.get(0).getCenterY(),
                allCells.get(6).getCenterX(), allCells.get(6).getCenterY(), 200, 75));

        graphPane.getChildren().add(createDirectedEdge(
                allCells.get(1).getCenterX(), allCells.get(1).getCenterY(),
                allCells.get(0).getCenterX(), allCells.get(0).getCenterY(), 200, 75));
    }

    public static Group createDirectedEdge(double startX, double startY, double endX, double endY, double nodeWidth, double nodeHeight) {
        double distanceX = endX - startX;
        double distanceY = endY - startY;

        double angle = Math.atan2(distanceY, distanceX);

        double halfWidth = nodeWidth / 2.0;
        double halfHeight = nodeHeight / 2.0;

        // Pour éviter division par zéro quand distanceX ou distanceY = 0
        double safeDistanceX = distanceX != 0 ? distanceX : 0.0001;
        double safeDistanceY = distanceY != 0 ? distanceY : 0.0001;

        double scaleX = Math.abs(halfWidth / safeDistanceX);
        double scaleY = Math.abs(halfHeight / safeDistanceY);
        double scale = Math.min(scaleX, scaleY);

        // Direction selon le signe de distanceX et distanceY
        double signX = Math.signum(distanceX);
        double signY = Math.signum(distanceY);

        // Calcule nouveau point de départ, décalé dans la bonne direction
        double newStartX = startX + signX * halfWidth;
        double newStartY = startY + signY * halfHeight;

        // Calcule nouveau point d'arrivée, décalé dans la bonne direction
        double newEndX = endX - signX * halfWidth;
        double newEndY = endY - signY * halfHeight;

        // Ligne
        Line line = new Line(newStartX, newStartY, newEndX, newEndY);
        line.setStroke(Color.BLACK);
        line.setStrokeWidth(2);

        // Flèche
        double arrowLength = 10;

        double x1 = newEndX - arrowLength * Math.cos(angle - Math.PI / 6);
        double y1 = newEndY - arrowLength * Math.sin(angle - Math.PI / 6);

        double x2 = newEndX - arrowLength * Math.cos(angle + Math.PI / 6);
        double y2 = newEndY - arrowLength * Math.sin(angle + Math.PI / 6);

        Polygon arrowHead = new Polygon(
                newEndX, newEndY,
                x1, y1,
                x2, y2
        );
        arrowHead.setFill(Color.BLACK);

        return new Group(line, arrowHead);
    }
}
