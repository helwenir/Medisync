package vue;
import model.dao.*;
import persistence.*;

import java.util.ArrayList;

import control.Controller;
import control.ScrollController;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.ListeSecouriste;


public class VueSecouristeAdmin extends BorderPane{

    private Controller control;
    private ScrollController scrollController;
    private Admin admin;
    private DAOUser daoUser;
    private DAOAdmin daoAdmin;
    private String username;
    private Secouriste sec;
    private User user;

    private ListeSecouriste l=new ListeSecouriste();

    private HBox topBar;
    private HBox centerPart;
    private Image logo;
    private GridPane mainPane;
    private Button SecouristeButton;
    private Button DPSButton;
    private Button deconnexionButton;
    private Label nomAdmin;
    private ImageView imageViewLogo;
    private ImageView imageViewDeconnexion;


    private VBox leftBox;
    private Text leftText;
    private Button addButton;
    private Button deleteButton;
    private Button editButton;
    
    private VBox centerBox;

    
    private HBox secouriste;
    private Text secouristeText;
    private HBox secouriste1;
    private Text secouriste1Text;
    private HBox secouriste2;
    private Text secouriste2Text;
    private HBox secouriste3;
    private Text secouriste3Text;
    private HBox secouriste4;
    private Text secouriste4Text;
    private HBox secouriste5;
    private Text secouriste5Text;
    

    private int i;
    private int i2;
    public VueSecouristeAdmin(Controller c, User u,ScrollController sc){
        this.i=0;
        this.i2=0;
        this.control = c;
        this.user = u;
        this.daoAdmin = new DAOAdmin();
        this.admin = this.daoAdmin.findById(this.user.getAdmin());
        this.scrollController=sc;

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

        this.DPSButton = new Button("Les DPS");
        this.DPSButton.setMinWidth(130);
        this.DPSButton.setStyle("-fx-background-radius: 25px; -fx-border-radius: 25px; -fx-background-color: white;");
        this.DPSButton.setFont(Font.font("Arial", 28));
        this.DPSButton.setTranslateX(-75);
        this.DPSButton.setTranslateY(15);
        this.DPSButton.setOnAction(this.control);

        this.nomAdmin = new Label(this.admin.getPrenom()+" "+this.admin.getNom()+ "  \n Admin");
        this.nomAdmin.setStyle("-fx-text-fill: white; -fx-font-size: 20px; -fx-alignment: center; -fx-text-alignment: center;");
        this.nomAdmin.setMinWidth(140);
        this.nomAdmin.setTranslateX(-400);
        this.nomAdmin.setTranslateY(20);

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

        this.topBar.getChildren().addAll(this.imageViewLogo, spacerLeft, this.SecouristeButton, this.DPSButton, spacerRight, this.nomAdmin, this.deconnexionButton);

        // creation of the left part of the view

        this.leftBox=new VBox();
        this.leftBox.setTranslateX(100);
        this.leftBox.setTranslateY(50);

        this.leftText=new Text("Les Secouristes");
        this.leftText.setFont(Font.font("Arial",38));
        
        this.addButton=new Button("Ajouter");
        this.addButton.setStyle("-fx-background-radius: 25px; -fx-border-radius: 25px; -fx-background-color: white;-fx-text-alignment: left;");
        this.addButton.setFont(Font.font("Arial",34));
        this.addButton.setTranslateX(300);
        this.addButton.setTranslateY(150);
        this.addButton.setMinSize(300,70);
        this.addButton.setOnAction(this.control);

        this.deleteButton= new Button("Supprimer");
        this.deleteButton.setStyle("-fx-background-radius: 25px; -fx-border-radius: 25px; -fx-background-color: white;-fx-text-alignment: left;");
        this.deleteButton.setFont(Font.font("Arial",34));
        this.deleteButton.setTranslateX(300);
        this.deleteButton.setTranslateY(250);
        this.deleteButton.setMinSize(300, 70);
        this.deleteButton.setOnAction(this.control);

        this.editButton=new Button("Modifier");
        this.editButton.setStyle("-fx-background-radius: 25px; -fx-border-radius: 25px; -fx-background-color: white;-fx-text-alignment: left;");
        this.editButton.setFont(Font.font("Arial",34));
        this.editButton.setTranslateX(300);
        this.editButton.setTranslateY(350);
        this.editButton.setMinSize(300, 70);
        this.editButton.setOnAction(this.control);
        

        this.leftBox.getChildren().addAll(this.leftText,this.addButton,this.deleteButton,this.editButton);

        this.centerBox=new VBox();
        this.centerBox.setTranslateY(200);
        this.centerBox.setTranslateX(800);
        

        this.secouriste=new HBox();
        this.secouriste.setMinSize(400, 100);
        this.secouriste.setMaxSize(400, 100);
        this.secouriste.setStyle("-fx-border-color: #000000;");
        this.secouristeText=new Text("Secouristes");
        this.secouristeText.setTranslateX(90);
        this.secouristeText.setTranslateY(20);
        this.secouristeText.setFont(Font.font("Arial",38));
        this.secouriste.getChildren().addAll(this.secouristeText);


        this.secouriste1=new HBox();
        this.secouriste1.setMinSize(400, 100);
        this.secouriste1.setMaxSize(400, 100);
        this.secouriste1.setStyle("-fx-border-color: #000000;");
        this.secouriste1Text=new Text(this.l.getNomSecouriste());
        this.secouriste1Text.setTranslateX(90);
        this.secouriste1Text.setTranslateY(20);
        this.secouriste1Text.setFont(Font.font("Arial",38));
        this.secouriste1.getChildren().addAll(this.secouriste1Text);


        this.secouriste2=new HBox();
        this.secouriste2.setMinSize(400, 100);
        this.secouriste2.setMaxSize(400, 100);
        this.secouriste2.setStyle("-fx-border-color: #000000;");
        this.secouriste2Text=new Text(this.l.getNomSecouriste());
        this.secouriste2Text.setTranslateX(90);
        this.secouriste2Text.setTranslateY(20);
        this.secouriste2Text.setFont(Font.font("Arial",38));
        this.secouriste2.getChildren().addAll(this.secouriste2Text);


        this.secouriste3=new HBox();
        this.secouriste3.setMinSize(400, 100);
        this.secouriste3.setMaxSize(400, 100);
        this.secouriste3.setStyle("-fx-border-color: #000000;");
        this.secouriste3Text=new Text(this.l.getNomSecouriste());
        this.secouriste3Text.setTranslateX(100);
        this.secouriste3Text.setTranslateY(20);
        this.secouriste3Text.setFont(Font.font("Arial",38));
        this.secouriste3.getChildren().addAll(this.secouriste3Text);


        this.secouriste4=new HBox();
        this.secouriste4.setMinSize(400, 100);
        this.secouriste4.setMaxSize(400, 100);
        this.secouriste4.setStyle("-fx-border-color: #000000;");
        this.secouriste4Text=new Text(this.l.getNomSecouriste());
        this.secouriste4Text.setTranslateX(100);
        this.secouriste4Text.setTranslateY(20);
        this.secouriste4Text.setFont(Font.font("Arial",38));
        this.secouriste4.getChildren().addAll(this.secouriste4Text);


        this.secouriste5=new HBox();
        this.secouriste5.setMinSize(400, 100);
        this.secouriste5.setMaxSize(400, 100);
        this.secouriste5.setStyle("-fx-border-color: #000000;");
        this.secouriste5Text=new Text(this.l.getNomSecouriste());
        this.secouriste5Text.setTranslateX(100);
        this.secouriste5Text.setTranslateY(20);
        this.secouriste5Text.setFont(Font.font("Arial",38));
        this.secouriste5.getChildren().addAll(this.secouriste5Text);

        this.centerBox.getChildren().addAll(this.secouriste,this.secouriste1,this.secouriste2,this.secouriste3,this.secouriste4,this.secouriste5);

        this.scrollController.setVueSecouristeAdmin(this);
        this.centerBox.setOnScroll(this.scrollController);
        this.setStyle("-fx-background-color: #f7f4f4;");
        this.setTop(this.topBar);
        this.setLeft(this.leftBox);
        this.setCenter(this.centerBox);
    }

    public Button getDeconnexionButton(){
        return this.deconnexionButton;
    }

    public Button getDPSButton(){
        return this.DPSButton;
    }

    public Button getAddButton(){
        return this.addButton;
    }

    public Button getDeleteButton(){
        return this.deleteButton;
    }

    public Button getEditButton(){
        return this.editButton;
    }

    public VBox getCenterBox(){
        return this.centerBox;
    }

    public void updateTextSecouristeDown(){
        if(this.i2==0){
            this.l.lowerI();
        }
        this.secouriste1Text.setText(this.secouriste2Text.getText());
        this.secouriste2Text.setText(this.secouriste3Text.getText());
        this.secouriste3Text.setText(this.secouriste4Text.getText());
        this.secouriste4Text.setText(this.secouriste5Text.getText());
        this.secouriste5Text.setText(this.l.getNomSecouristeDown());
        this.i2++;
    }

    public void updateTextSecouristeUp(){
        this.secouriste5Text.setText(this.secouriste4Text.getText());
        this.secouriste4Text.setText(this.secouriste3Text.getText());
        this.secouriste3Text.setText(this.secouriste2Text.getText());
        this.secouriste2Text.setText(this.secouriste1Text.getText());
        this.secouriste1Text.setText(this.l.getNomSecouristeUp());
       
    }

    public void addSecoursiteToList(String name,String firstName){
        String newS=name+" "+firstName;
        this.l.addNewSecouriste(newS);
    }

    public void removeSecouristeFromList(String name){
        this.l.removeSecouriste(name);
    }

    public ScrollController getScrollController(){
        return this.scrollController;
    }

    public ArrayList<String> getListSecouriste(){
        return this.l.getListSecouriste();
    }

    public void upadteTextAfterRemove(){
        if(this.i!=0){
            this.l.lowerI();
        }
        if (!this.l.ContainsName(this.secouriste1Text.getText())){
            this.secouriste1Text.setText(this.secouriste2Text.getText());
            this.secouriste2Text.setText(this.secouriste3Text.getText());
            this.secouriste3Text.setText(this.secouriste4Text.getText());
            this.secouriste4Text.setText(this.secouriste5Text.getText());
            this.secouriste5Text.setText(this.l.getNomSecouriste());
        }else if(!this.l.ContainsName(this.secouriste2Text.getText())){
            this.secouriste2Text.setText(this.secouriste3Text.getText());
            this.secouriste3Text.setText(this.secouriste4Text.getText());
            this.secouriste4Text.setText(this.secouriste5Text.getText());
            this.secouriste5Text.setText(this.l.getNomSecouriste());
        }else if(!this.l.ContainsName(this.secouriste3Text.getText())){
            this.secouriste3Text.setText(this.secouriste4Text.getText());
            this.secouriste4Text.setText(this.secouriste5Text.getText());
            this.secouriste5Text.setText(this.l.getNomSecouriste());
        }else if(!this.l.ContainsName(this.secouriste4Text.getText())){
            this.secouriste4Text.setText(this.secouriste5Text.getText());
            this.secouriste5Text.setText(this.l.getNomSecouriste());
        }else if(!this.l.ContainsName(this.secouriste5Text.getText())){
            this.secouriste5Text.setText(this.l.getNomSecouriste());
        }
        this.i++;
    }
}