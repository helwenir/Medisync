package vue;

import java.util.List;

import com.fxgraph.graph.Graph;

import application.App;
import javafx.scene.Group;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;

import control.Controller;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.GrapheModele;
import model.dao.*;
import persistence.*;

public class VueCompetenceSecouriste extends BorderPane {
    
    private Controller control;
    private DAOUser daoUser;
    private DAOSecouriste daoSec;
    private String username;
    private User user;
    private Secouriste sec;
    private App app;

    private HBox topBar;
    private HBox centerPart;
    private Image logo;
    private GridPane mainPane;
    private Button planningButton;
    private Button compétencesButton;
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

    private HBox comp1Box;
    private Text comp1Text;
    private HBox comp2Box;
    private Text comp2Text;
    private HBox comp3Box;
    private Text comp3Text;
    private HBox comp4Box;
    private Text comp4Text;
    private HBox comp5Box;
    private Text comp5Text;

    private HBox compBox2;

    private HBox comp6Box;
    private Text comp6Text;
    private HBox comp7Box;
    private Text comp7Text;

    private HBox compBox3;

    private HBox comp8Box;
    private Text comp8Text;
    private HBox comp9Box;
    private Text comp9Text;

    private Graph graph;
    private VBox mainVbox;
    private Pane graphPane;
    private boolean isEditable = false;
    private GrapheModele g;
    
    private boolean firstDraw = true;


    public VueCompetenceSecouriste(Controller c,User u) {
        this.control = c;

        this.user = u;
        this.daoSec = new DAOSecouriste();
        this.sec = c.getApp().getListeSec().getSecById(this.user.getSecouriste());

        this.g = GrapheModele.getInstance();
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

        this.planningButton = new Button("Mon planning");
        this.planningButton.setMinWidth(130);
        this.planningButton.setStyle("-fx-background-radius: 25px; -fx-border-radius: 25px; -fx-background-color: white;");
        this.planningButton.setFont(Font.font("Arial", 28));
        this.planningButton.setTranslateX(-200);
        this.planningButton.setTranslateY(15);
        this.planningButton.setOnAction(this.control);

        this.compétencesButton = new Button("Mes compétences");
        this.compétencesButton.setMinWidth(130);
        this.compétencesButton.setStyle("-fx-background-radius: 25px; -fx-border-radius: 25px; -fx-background-color: white;");
        this.compétencesButton.setFont(Font.font("Arial", 28));
        this.compétencesButton.setTranslateX(-75);
        this.compétencesButton.setTranslateY(15);
        this.compétencesButton.setOnAction(this.control);

        this.nomSecouriste = new Label(this.sec.getPrenom()+" "+this.sec.getNom()+ "  \nSecouriste");
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

        this.topBar.getChildren().addAll(this.imageViewLogo, spacerLeft, this.planningButton, this.compétencesButton, spacerRight, this.nomSecouriste, this.deconnexionButton);

        this.centerBox = new VBox();

        this.box1 = new HBox();
        this.box1.setTranslateX(100);
        this.box1.setTranslateY(50);

        this.compText = new Text("Mes compétences");
        this.compText.setFont(Font.font("Arial", 32));

        this.editImage = new ImageView(new Image("file:../Image/pencil.png"));
        this.editImage.setFitHeight(75);
        this.editImage.setFitHeight(75);
        this.editImage.setPreserveRatio(true);

        this.editButton = new Button();
        this.editButton.setStyle("-fx-background-radius: 25; -fx-border-radius: 25px; -fx-background-color: white;");
        this.editButton.setGraphic(this.editImage);
        this.editButton.setMaxSize(150, 150);
        this.editButton.setTranslateY(-25);
        this.editButton.setTranslateX(1300);
        this.editButton.setOnAction(this.control);

        this.box1.getChildren().addAll(this.compText, this.editButton);

        this.compBox1 = new HBox();
        this.compBox1.setTranslateY(100);
        mainPane = new GridPane();
        graph = new Graph();
        graphPane = new Pane();
        centerPart.getChildren().add(graphPane);

        mainPane.add(graphPane, 0, 0);
        compBox1.getChildren().add(mainPane);

        this.centerBox.getChildren().addAll(this.box1, this.compBox1);

        this.setStyle("-fx-background-color: #f7f4f4");
        this.setTop(topBar);
        this.setCenter(this.centerBox);
        drawEdges(g);
        this.visibleProperty().addListener((obs, wasVisible, isNowVisible) -> {
            if (isNowVisible) {
                drawEdges(g);
            }
        });

    }

    
    public Button getDeconnexionButton() {
        return this.deconnexionButton;
    }

    public Button getPlanningButton() {
        return this.planningButton;
    }

    public Button getEditButton() {
        return this.editButton;
    }

    public void setIsEditable(boolean isEditable) {
        this.isEditable = isEditable;
    }

    public boolean GetIsEditable() {
        return this.isEditable;
    }

    public void drawEdges(GrapheModele modele) {

    graphPane.getChildren().clear();

    
    if (modele.getStyles() == null || modele.getStyles().isEmpty()) {
        System.out.println("Styles non définis. Application des styles par défaut.");
        for (int i = 0; i < 9; i++) {
            modele.getStyles().add("-fx-background-radius: 25; -fx-border-radius: 10px; -fx-background-color: white;");
        }
    }

    
    if (modele.getCells().isEmpty()) {
        System.out.println("Cellules vides. Création de nouvelles cellules.");
        List<String> savedStyles = modele.getStyles();
            modele.addCell(new Cell("Premiers Secours en \n équipe Niveau : 1", savedStyles.get(0), this, modele));
            modele.addCell(new Cell("Premiers Secours en \n équipe Niveau : 1", modele.getStyles().get(0), this, modele));
            modele.addCell(new Cell("Premiers Secours en \n équipe Niveaux : 2", modele.getStyles().get(0), this, modele));
            modele.addCell(new Cell("Chef d’équipe", modele.getStyles().get(0), this, modele));
            modele.addCell(new Cell("Chef de Poste", modele.getStyles().get(0), this, modele));
            modele.addCell(new Cell("Cadre Opérationnel", modele.getStyles().get(0), this, modele));
            modele.addCell(new Cell("Secouriste, \n Sauveteur Aquatique", modele.getStyles().get(0), this, modele));
            modele.addCell(new Cell("Conducteur de \n Véhicule de Premier \n Secours à Personnes", modele.getStyles().get(0), this, modele));
            modele.addCell(new Cell("Pilote Bateau Côtier", modele.getStyles().get(0), this, modele));
            modele.addCell(new Cell("Pilote Bateau Fluvial", modele.getStyles().get(0), this, modele));
    }

   
    List<Cell> allCells = modele.getCells();
    if (allCells.isEmpty()) {
        System.err.println("Erreur : Aucune cellule n'a été créée.");
        return;
    }

    try {
        Cell.layoutCells(allCells.subList(0, 7), 90, 0, 5);
        Cell.layoutCells(allCells.subList(7, allCells.size()), 800, 500, 5);
    } catch (Exception e) {
        System.err.println("Erreur lors de la disposition des cellules : " + e.getMessage());
        return;
    }

    
    int offsetX = 0;
    int offestY = 0;
    if (firstDraw) {
        offsetX = 100;
        offestY = 40;
        firstDraw = false;
    }
    try {
        graphPane.getChildren().add(createDirectedEdge(
                allCells.get(1).getCenterX(), allCells.get(1).getCenterY(),
                allCells.get(2).getCenterX(), allCells.get(2).getCenterY(), 200, 75, offsetX, offestY));
        graphPane.getChildren().add(createDirectedEdge(
                allCells.get(3).getCenterX(), allCells.get(3).getCenterY(),
                allCells.get(2).getCenterX(), allCells.get(2).getCenterY(), 200, 75, offsetX, offestY));
        graphPane.getChildren().add(createDirectedEdge(
                allCells.get(0).getCenterX(), allCells.get(0).getCenterY(),
                allCells.get(6).getCenterX(), allCells.get(6).getCenterY(), 200, 75, offsetX, offestY));
        graphPane.getChildren().add(createDirectedEdge(
                allCells.get(1).getCenterX(), allCells.get(1).getCenterY(),
                allCells.get(0).getCenterX(), allCells.get(0).getCenterY(), 200, 75, offsetX, offestY));
    } catch (Exception e) {
        System.err.println("Erreur lors de l'ajout des arêtes : " + e.getMessage());
        return;
    }

    
    List<String> savedStyles = modele.getStyles();
    for (int i = 0; i < allCells.size(); i++) {
        try {
            Cell cell = allCells.get(i);
            cell.setStyle(savedStyles.get(i));
            graphPane.getChildren().add(cell.getGraphic(null));
        } catch (Exception e) {
            System.err.println("Erreur lors de l'ajout de la cellule " + i + " : " + e.getMessage());
        }
    }
}


    public static Group createDirectedEdge(double startX, double startY, double endX, double endY, double nodeWidth, double nodeHeight, int offsetX, int offsetY) {
        double distanceX = endX - startX;
        double distanceY = endY - startY;

        double angle = Math.atan2(distanceY, distanceX);

        double halfWidth = nodeWidth / 2.0;
        double halfHeight = nodeHeight / 2.0;

        
        double safeDistanceX = distanceX != 0 ? distanceX : 0.0001;
        double safeDistanceY = distanceY != 0 ? distanceY : 0.0001;

        double scaleX = Math.abs(halfWidth / safeDistanceX);
        double scaleY = Math.abs(halfHeight / safeDistanceY);
        double scale = Math.min(scaleX, scaleY);

        
        double signX = Math.signum(distanceX);
        double signY = Math.signum(distanceY);

        
        double newStartX = startX + signX * halfWidth + offsetX;
        double newStartY = startY + signY * halfHeight + offsetY;

        
        double newEndX = endX - signX * halfWidth + offsetX;
        double newEndY = endY - signY * halfHeight + offsetY;

       
        Line line = new Line(newStartX, newStartY, newEndX, newEndY);
        line.setStroke(Color.BLACK);
        line.setStrokeWidth(2);

        
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
