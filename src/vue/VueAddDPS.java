package vue;

import java.util.ArrayList;

import control.Controller;
import control.ScrollController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.dao.*;
import persistence.*;

public class VueAddDPS extends BorderPane{

    private Controller control;
    private ScrollController scrollController;

    
    private Text Title;

    private VBox leftBox;

    private Text idText;
    private TextField idTextField;
    private Button clearID;

    private Text heureDebutText;
    private TextField heureDebutTextField;
    private Button clearHeureDebut;

    private Text heureFinText;
    private TextField heureFinField;
    private Button clearHeureFin;

    
    private VBox centerBox;


    
    private Text siteText;
    private ComboBox<String> siteBox;

    private Text sportText;
    private ComboBox<String> sportBox;

    private Text outCompText;
    private HBox compbox;
    private Text innerCompText;
    private Button addButton;
    private Button removeButton;


    private Button saveButton;

    private ArrayList<String> listComp;
    private ArrayList<String> listCompChose;
    private int imin;
    private int imax;


    public VueAddDPS(Controller c,ScrollController sc){
        this.listComp=new ArrayList<>();
        for(int i=0;i<15;i++){
            this.listComp.add("Compétence "+i);
        }
        this.imin=this.listComp.size()-1;
        this.imax=1;
        this.control=c;
        this.scrollController=sc;
        
        this.Title=new Text("Ajouter : ");
        this.Title.setFont(Font.font("Arial",30));
        this.Title.setTranslateX(20);
        this.Title.setTranslateY(20);

        this.idText=new Text("Jour");
        this.idText.setFont(Font.font("Arial",20));
        this.idTextField=new TextField();
        this.idTextField.setPromptText("jj/mm/aaaa");
        

        this.clearID=new Button("X");
        this.clearID.setFont(Font.font("Arial",8));
        this.clearID.setStyle("-fx-background-radius: 25px;");
        this.clearID.setTranslateX(50);
        this.clearID.setOnAction(this.control);
        StackPane IDpane=new StackPane();
        IDpane.setStyle("-fx-background-radius: 25px; -fx-border-radius: 25px; -fx-background-color: white;");
        IDpane.getChildren().addAll(this.idTextField,this.clearID);

        this.heureDebutText=new Text("Heure de départ");
        this.heureDebutText.setFont(Font.font("Arial", 20));
        this.heureDebutText.setTranslateY(20);

        this.heureDebutTextField=new TextField();
        this.heureDebutTextField.setPromptText("Heure de Départ");

        this.clearHeureDebut=new Button("X");
        this.clearHeureDebut.setFont(Font.font("Arial",8));
        this.clearHeureDebut.setStyle("-fx-background-radius: 25px;");
        this.clearHeureDebut.setTranslateX(50);
        this.clearHeureDebut.setOnAction(this.control);

        StackPane heureDebutPane=new StackPane();
        heureDebutPane.getChildren().addAll(this.heureDebutTextField,this.clearHeureDebut);
        heureDebutPane.setStyle("-fx-background-radius: 25px; -fx-border-radius: 25px; -fx-background-color: white;");
        heureDebutPane.setTranslateY(20);

        this.heureFinField=new TextField();
        this.heureFinField.setPromptText("Heure de Fin");
        this.heureFinField.setFont(Font.font("Arial", 16));

        this.heureFinText=new Text("Heure de Fin");
        this.heureFinText.setFont(Font.font("Arial",20));
        this.heureFinText.setTranslateY(50);
        this.clearHeureFin=new Button("X");
        this.clearHeureFin.setFont(Font.font("Arial",8));
        this.clearHeureFin.setStyle("-fx-background-radius: 25px;");
        this.clearHeureFin.setTranslateX(50);
        this.clearHeureFin.setOnAction(this.control);

        StackPane heureFinePane=new StackPane();
        heureFinePane.getChildren().addAll(this.heureFinField,this.clearHeureFin);
        heureFinePane.setStyle("-fx-background-radius: 25px; -fx-border-radius: 25px; -fx-background-color: white");
        heureFinePane.setTranslateY(50);

        this.leftBox=new VBox();
        this.leftBox.getChildren().addAll(this.idText,IDpane,this.heureDebutText,heureDebutPane,this.heureFinText,heureFinePane);
        this.leftBox.setTranslateX(40);
        this.leftBox.setTranslateY(40);
        this.leftBox.setMaxSize(150, 200);

        this.siteText=new Text("Site");
        this.siteText.setFont(Font.font("Arial", 20));
        this.siteBox=new ComboBox<String>();

        DAOSite daoSite = new DAOSite();
        ArrayList<String> listSite = new ArrayList<>();
        ObservableList<String> listeSite = FXCollections.observableArrayList();
        for (Site site : daoSite.listSite()){
            listSite.add(site.getNom());
        }
        listeSite.setAll(listSite);
        this.siteBox.setItems(listeSite);
        this.siteBox.setMaxWidth(150);
        this.siteBox.setPromptText("Site");
        this.siteBox.setStyle("-fx-background-radius: 25px; -fx-border-radius: 25px; -fx-background-color: white;-fx-font-size: 16;");

        this.sportText=new Text("Sport");
        this.sportText.setFont(Font.font("Arial", 20));
        this.sportText.setTranslateY(10);
        this.sportBox=new ComboBox<String>();

        DAOSport daoSport = new DAOSport();
        ArrayList<String> listSport = new ArrayList<>();
        ObservableList<String> listeSport = FXCollections.observableArrayList();
        for (Sport sport : daoSport.listSport()){
            listSport.add(sport.getNom());
            System.out.println("sport");
        }
        listeSport.setAll(listSport);
        this.sportBox.setItems(listeSport);
        this.sportBox.setMaxWidth(150);


        this.sportBox.setPromptText("Sport");
        this.sportBox.setStyle("-fx-background-radius: 25px; -fx-border-radius: 25px; -fx-background-color: white;-fx-font-size: 16;");
        this.sportBox.setTranslateY(10);


        this.outCompText=new Text("Compétence");
        this.outCompText.setFont(Font.font("Arial", 20));
        this.outCompText.setTranslateY(20);

        this.compbox=new HBox();
        this.compbox.setStyle("-fx-background-radius: 25px; -fx-border-radius: 25px; -fx-background-color: white;-fx-font-size: 16;");
        this.compbox.setTranslateY(20);
        this.compbox.setPrefSize(200, 50);
        this.compbox.setMaxSize(200, 50);
        

        this.innerCompText=new Text(this.listComp.get(0));
        this.innerCompText.setFont(Font.font("Arial", 20));
        this.innerCompText.setTranslateY(15);


        this.addButton=new Button("+");
        this.addButton.setFont(Font.font("Arial", 12));
        this.addButton.setStyle("-fx-background-radius: 25px; -fx-border-radius: 25px; -fx-background-color: #5fc785;");
        this.addButton.setOnAction(this.control);

        this.removeButton=new Button("-");
        this.removeButton.setFont(Font.font("Arial", 12));
        this.removeButton.setStyle("-fx-background-radius: 25px; -fx-border-radius: 25px; -fx-background-color: #ff8585;");
        this.removeButton.setTranslateY(25);
        this.removeButton.setTranslateX(22);
        this.removeButton.setOnAction(this.control);

        this.compbox.getChildren().addAll(this.innerCompText,this.removeButton,this.addButton);
        this.centerBox=new VBox();
        this.centerBox.getChildren().addAll(this.siteText,this.siteBox,this.sportText,this.sportBox,this.outCompText,this.compbox);
        this.centerBox.setTranslateX(150);
        this.centerBox.setTranslateY(40);


        this.saveButton=new Button("Sauvegardez");
        this.saveButton.setFont(Font.font("Arial",20));
        this.saveButton.setStyle("-fx-background-radius: 25px; -fx-border-radius: 25px; -fx-background-color: #5fc785;");
        this.saveButton.setTranslateY(-50);
        this.saveButton.setTranslateX(175);
        this.saveButton.setOnAction(this.control);
        
        this.compbox.setOnScroll(this.scrollController);
        this.scrollController.setAddDPS(this);
        
        this.setTop(this.Title);
        this.setLeft(this.leftBox);
        this.setCenter(this.centerBox);

        this.setBottom(this.saveButton);

        this.setStyle("-fx-background-color: #f7f4f4;");
        this.setMinSize(500, 400);

        this.listCompChose=new ArrayList<>();
        
    }


    public Button getSaveButton(){
        return this.saveButton;
    }

    public Button getIdClear(){
        return this.clearID;
    }

    public Button getHeureDebutClear(){
        return this.clearHeureDebut;
    }

    public Button getAddButton(){
        return this.addButton;
    }

    public Button getRemoveButton(){
        return this.removeButton;
    }

    public Button getHeureFinClear(){
        return this.clearHeureFin;
    }

    public void clearID(){
        this.idTextField.clear();
    }

    public void clearHeureDebut(){
        this.heureDebutTextField.clear();
    }

    public void clearHeureFin(){
        this.heureFinField.clear();
    }
    public String getID(){
        return this.idTextField.getText();   
    }

    public String getHeureDebut(){
        return this.heureDebutTextField.getText();
    }

    public String getHeureFin(){
        return this.heureFinField.getText();
    }

    public String getJour(){
        return this.idTextField.getText();   
    }

    public String getSport(){
        DAOSport dao = new DAOSport();
        return dao.getCodeByNom(this.sportBox.getValue());
    }

    public String getSite(){
        DAOSite dao = new DAOSite();
        return dao.getCodeByNom(this.siteBox.getValue());
    }

    public HBox getCompBox(){
        return this.compbox;
    }

    public void updateTextUp(){
        this.innerCompText.setText(this.listComp.get(this.imin));
        this.imin = (this.imin - 1 + this.listComp.size()) % this.listComp.size();
        this.imax = (this.imin + 1) % this.listComp.size(); // imax est toujours 2 positions après imin

    }

    public void updateTextDown(){
        this.innerCompText.setText(this.listComp.get(this.imax));

        this.imax = (this.imax + 1) % this.listComp.size();
        this.imin = (this.imax - 1 + this.listComp.size()) % this.listComp.size(); // imin est 2 positions avant imax
    }

    public void addCompToList(){
        String comp=this.innerCompText.getText();
        if(!(this.listCompChose.contains(comp))){
            this.listCompChose.add(comp);
        }
        System.out.println(comp);
    }

    public void removeCompToList(){
        String comp=this.innerCompText.getText();
        if(this.listCompChose.contains(comp)){
            this.listCompChose.remove(comp);
        }
        System.out.println(comp);
    }
}