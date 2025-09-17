package vue;

import control.*;
import persistence.*;
import model.dao.*;
import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import jfxtras.scene.control.agenda.Agenda;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.Scene;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import jfxtras.scene.control.agenda.Agenda;

import java.time.LocalDateTime;
import java.util.List;


import javafx.geometry.Orientation;
import javafx.scene.input.KeyEvent;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.ArrayList;

// changer les extends pour juste avoit à switch la scene dans l'application
public class VuePlanningSecouriste extends BorderPane {
    private DAOUser daoUser;
    private DAOSecouriste daoSec;
    private String username;
    private User user;
    private Secouriste sec;

    private Label blankLabel;

    private Controller control;

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

    private HBox planningBar;
    private Text planningText;
    private Button todayButton;
    private Button leftButton;
    private Label dateText;
    private Button rightButton;
    private TextField searchField;


    private VBox rightBox;
    private Text textChoix;
    private VBox ChoixBox;
    private RadioButton affectButton;
    private RadioButton dispButton;
    private ToggleGroup buttonGroup;
    private Text dispText;
    private TextField addField;
    private Button addButton;
    private ComboBox<String> rmBox;
    private Button deleteButton;

    private planningKeyController keyControleur;
    
    private GridPane gridPane;
    
    private Agenda agenda;

    private Label dateLabel;

    public VuePlanningSecouriste(Controller c, User u) {
        //Creation of the navBar
        this.control=c;
        this.daoUser = new DAOUser();
        this.daoSec = new DAOSecouriste();
        this.user = u;
        this.sec = c.getApp().getListeSec().getSecById(this.user.getSecouriste());
        this.keyControleur = new planningKeyController(this);
        this.control.setPlanningkeyController(this.keyControleur);


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

        //creation of the plannig bar
        this.planningBar=new HBox();
        this.planningBar.setTranslateY(50);
        this.planningBar.setTranslateX(50);
        
        this.planningText=new Text("Mon planning");
        this.planningText.setFont(Font.font("Arial",28));
        this.planningText.setTranslateY(10);

        this.todayButton=new Button("Aujourd'hui");
        this.todayButton.setStyle("-fx-background-radius: 25px; -fx-border-radius: 25px; -fx-background-color: white;");
        this.todayButton.setFont(Font.font("Arial", 28));
        this.todayButton.setTranslateX(80);
        this.todayButton.setOnAction(this.control);

        this.leftButton=new Button("<");
        this.leftButton.setStyle("-fx-background-color: #f7f4f4");  
        this.leftButton.setFont(Font.font("Arial",28));
        this.leftButton.setTranslateX(120);
        this.leftButton.setOnAction(this.control);
        
        this.dateLabel= new Label();
        this.dateLabel.setMaxWidth(370);
        this.dateLabel.setFont(Font.font("Arial",28));
        this.dateLabel.setTranslateY(10);
        this.dateLabel.setTranslateX(110);

        this.rightButton=new Button(">");
        this.rightButton.setStyle("-fx-background-color: #f7f4f4");  
        this.rightButton.setFont(Font.font("Arial",28));
        this.rightButton.setTranslateX(110);
        this.rightButton.setOnAction(this.control);
        
        this.searchField=new TextField();
        this.searchField.setPromptText("Recherchez une date");
        this.searchField.setStyle("-fx-background-radius: 25px; -fx-border-radius: 25px; -fx-background-color: white;");
        this.searchField.setFont(Font.font("Arial",28));
        this.searchField.setTranslateX(200);
        this.searchField.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal) {
                DateTimeFormatter parser = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                LocalDateTime date = LocalDateTime.parse(this.searchField.getText() + " 00:00", parser);
                this.agenda.setDisplayedLocalDateTime(date);
                this.updateDateLabel();
                }
        });
        this.searchField.setOnKeyPressed(keyControleur);
        this.planningBar.getChildren().addAll(this.planningText,this.todayButton,this.leftButton,this.dateLabel,this.rightButton,this.searchField);

        // création de la boite de droite
        
        this.rightBox=new VBox();
        this.rightBox.setPrefWidth(300);
        this.rightBox.setTranslateX(1450);
        this.rightBox.setTranslateY(50);
        
        this.textChoix=new Text("Selection du planning");
        this.textChoix.setFont(Font.font("Arial",32));

        this.ChoixBox=new VBox();
        this.ChoixBox.setStyle("-fx-background-radius: 25px; -fx-border-radius: 25px; -fx-background-color: white;");
        this.ChoixBox.setPrefSize(300, 200);
        this.ChoixBox.setMaxSize(300, 200);
        this.ChoixBox.setTranslateY(50);
        
        this.buttonGroup=new ToggleGroup();

        this.affectButton=new RadioButton();
        this.affectButton.setText("Affectations");
        this.affectButton.setSelected(true);
        this.affectButton.setFont(Font.font("Arial",28));
        this.affectButton.setToggleGroup(buttonGroup);
        this.affectButton.setTranslateX(30);
        this.affectButton.setTranslateY(25);
        this.affectButton.selectedProperty().addListener((obs, wasSelected, isNowSelected) -> {
            if (isNowSelected) {
                affectationsAgenda();
            } else {
                disponibilitesAgenda();
            }
        });

        this.dispButton=new RadioButton();
        this.dispButton.setText("Disponibilités");
        this.dispButton.setFont(Font.font("Arial",28));
        this.dispButton.setToggleGroup(buttonGroup);
        this.dispButton.setTranslateX(30);
        this.dispButton.setTranslateY(85);

        this.buttonGroup.selectToggle(this.affectButton);
        
        this.ChoixBox.getChildren().addAll(this.affectButton,this.dispButton);

        this.dispText=new Text("Mes Disponibilités");
        this.dispText.setFont(Font.font("Arial",32));
        this.dispText.setTranslateY(100);

        
        this.addField=new TextField();
        this.addField.setPromptText("Ajouter (jj/mm/aaaa)");
        this.addField.setStyle("-fx-background-radius: 25px; -fx-border-radius: 25px; -fx-background-color: white;-fx-font-size: 18 ;");
        this.addField.setPrefSize(300, 75);
        this.addField.setMaxWidth(300);
        this.addField.setTranslateY(50);
        
        this.addButton=new Button("Ajouter");
        this.addButton.setFont(Font.font("Arial", 24));
        this.addButton.setStyle("-fx-background-radius: 25px; -fx-border-radius: 25px; -fx-background-color: #5fc785;");
        this.addButton.setPrefSize(300, 75);
        this.addButton.setMaxWidth(300);
        this.addButton.setTranslateY(200);
        this.addButton.setOnAction(this.control);


        this.rmBox=new ComboBox<>();
        ArrayList<String> listComp = new ArrayList<>();
        ArrayList<Journee> listeCompetences = this.sec.getDispo();
        ObservableList<String> listeComp = FXCollections.observableArrayList();
        for (Journee jour : listeCompetences){
            listComp.add(jour.toString());
        }
        listeComp.setAll(listComp);
        this.rmBox.setItems(listeComp);
     
        this.rmBox.setPromptText("Supprimer");
        this.rmBox.setStyle("-fx-background-radius: 25px; -fx-border-radius: 25px; -fx-background-color: white; -fx-font-size: 18 ;");
        this.rmBox.setPrefSize(300, 75);
        this.rmBox.setTranslateY(100);

        this.deleteButton=new Button("Supprimer");
        this.deleteButton.setFont(Font.font("Arial", 24));
        this.deleteButton.setStyle("-fx-background-radius: 25px; -fx-border-radius: 25px; -fx-background-color: #ff8585;");
        this.deleteButton.setPrefSize(300, 75);
        this.deleteButton.setMaxWidth(300);
        this.deleteButton.setTranslateY(250);
        this.deleteButton.setOnAction(this.control);


        this.rightBox.getChildren().addAll(this.textChoix,this.ChoixBox,this.dispText,this.addField,this.rmBox,this.addButton,this.deleteButton);

        
        //ajout du calendrier
        this.gridPane=new GridPane(2,2);
        this.blankLabel=new Label();
        this.agenda = new Agenda();
        this.agenda.setAllowDragging(false);
        this.agenda.setAllowResize(false);
        affectationsAgenda();

        this.agenda.setDisplayedLocalDateTime(LocalDateTime.of(2030, 2, 1, 0, 0));

        String[] competences = {"Premiers secours", "Secourisme en équipe", "Gestion des urgences"};
        this.agenda.setEditAppointmentCallback(param -> {
            return null;
        });

        this.agenda.selectedAppointments().addListener(new ListenerPlanningDetailsSecouriste(this.control.getApp(),c));
        this.gridPane.getChildren().addAll(this.planningBar,this.blankLabel,this.agenda,this.rightBox);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE dd MMMM yyyy");
        LocalDateTime now = agenda.getDisplayedLocalDateTime();
        this.dateLabel.setText(now.format(formatter));

        gridPane.requestFocus();
        gridPane.addEventFilter(KeyEvent.KEY_PRESSED, keyControleur);

    
        this.agenda.setMinWidth(1400);
        this.agenda.setMinHeight(700);
        this.agenda.setTranslateX(0);
        this.agenda.setTranslateY(150);

        

        //ajout de la fenêtre finale
        this.setStyle("-fx-background-color: #f7f4f4");
        this.setTop(this.topBar);
        //this.setLeft(this.planningBar);
        this.setCenter(this.gridPane);
        //this.setBottom(this.this.agenda);
        //this.setRight(this.rightBox);
        this.keyControleur.creerAgenda(this.agenda);
    }

    public void addEventAgenda(LocalDateTime start, LocalDateTime end, String summary, String location, String description) {
        Agenda.Appointment appointment = new Agenda.AppointmentImplLocal()
                .withSummary(summary)
                .withStartLocalDateTime(start)
                .withEndLocalDateTime(end)
                .withDescription(description)
                .withLocation(location);
        this.agenda.appointments().add(appointment);

    }

    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
    }

    public Agenda getAgenda() {
        return this.agenda;
    }

    public Button getDeconnexionButton(){
        return this.deconnexionButton;
    }

    public Button getLeftButton(){
        return this.leftButton;
    }

    public Button getRightButton(){
        return this.rightButton;
    }

    public Button getTodayButton(){
        return this.todayButton;
    }

    public Button getCompetenceButton(){
        return this.compétencesButton;
    }

    public Button getPlanningButton(){
        return this.planningButton;
    }

    public GridPane getGridPane () {
        return this.gridPane;
    }

    public void updateDateLabel() {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE dd MMMM yyyy");
            LocalDateTime now = agenda.getDisplayedLocalDateTime();
            this.dateLabel.setText(now.format(formatter));
    }

    public Label getDateLabel(){
        return this.dateLabel;
    }

    public void affectationsAgenda(){
        this.agenda.appointments().clear();

        for(Agenda.Appointment agenda : this.listAffec()){
            this.agenda.appointments().add(agenda);
            this.agenda.setEditAppointmentCallback(param -> null);
        }
    }

    /*
    public void disponibilitesAgenda(){
        this.agenda.appointments().clear();
        Agenda.Appointment appointment = new Agenda.AppointmentImplLocal()
                .withStartLocalDateTime(LocalDateTime.of(2030, 1, 28, 0, 0).withHour(9).withMinute(0))
                .withEndLocalDateTime(LocalDateTime.of(2030, 1, 28, 0, 0).withHour(18).withMinute(0))
                .withSummary("quoi");
        this.agenda.appointments().add(appointment);
        this.agenda.setEditAppointmentCallback(param -> null);
        Agenda.Appointment appointment2 = new Agenda.AppointmentImplLocal()
                .withStartLocalDateTime(LocalDateTime.of(2030, 1, 30, 0, 0).withHour(10).withMinute(0))
                .withEndLocalDateTime(LocalDateTime.of(2030, 1, 30, 0, 0).withHour(12).withMinute(0))
                .withSummary("quoicoubeh");
        this.agenda.appointments().add(appointment2);
        this.agenda.setEditAppointmentCallback(param -> null);
    }
        */

    public void disponibilitesAgenda(){
        this.agenda.appointments().clear();

        for(Agenda.Appointment agenda : this.listdispo()){
            this.agenda.appointments().add(agenda);
            this.agenda.setEditAppointmentCallback(param -> null);
        }
    }

    public ArrayList<Agenda.Appointment> listdispo(){
        ArrayList<Agenda.Appointment> list = new ArrayList<>();

        for(Journee j : this.sec.getDispo()){

            Agenda.Appointment appointment = new Agenda.AppointmentImplLocal()
                .withStartLocalDateTime(LocalDateTime.of(j.getAnnee(), j.getMois(), j.getJour(), 0, 0).withHour(0).withMinute(0))
                .withEndLocalDateTime(LocalDateTime.of(j.getAnnee(), j.getMois(), j.getJour(), 0, 0).withHour(23).withMinute(59))
                .withSummary("dispo");
            
            list.add(appointment);
        }

        return list;
    }

    public ArrayList<Agenda.Appointment> listAffec(){

        ArrayList<Agenda.Appointment> list = new ArrayList<>();

        for(int i : this.sec.getDps()){

            DPS dps = this.control.getApp().getListeDps().dpsById(i);

            Agenda.Appointment appointment = new Agenda.AppointmentImplLocal()
                .withStartLocalDateTime(LocalDateTime.of(dps.getAnnee(), dps.getMois(), dps.getJour(), 0, 0).withHour(dps.getHoraireDepart()).withMinute(0))
                .withEndLocalDateTime(LocalDateTime.of(dps.getAnnee(), dps.getMois(), dps.getJour(), 0, 0).withHour(dps.getHoraireFin()).withMinute(59))
                .withSummary(dps.getSport());
            
            list.add(appointment);
        }

        return list;
    }

    public Button getSuppButton(){
        return this.deleteButton;
    }

    public Journee getRmBox(){
        return this.sec.getDispo().get(this.rmBox.getSelectionModel().getSelectedIndex());
    }

    public Secouriste getSecouriste(){
        return this.sec;
    }

    public Button getAddButton(){
        return this.addButton;
    }

    public String getDPSToADD(){
        return this.addField.getText();
    }

    public void updateCombox(){
        
        ArrayList<String> listComp = new ArrayList<>();
        ArrayList<Journee> listeCompetences = this.sec.getDispo();
        ObservableList<String> listeComp = FXCollections.observableArrayList();
        for (Journee jour : listeCompetences){
            listComp.add(jour.toString());
        }
        listeComp.setAll(listComp);
        this.rmBox.setItems(listeComp);
    }
}

