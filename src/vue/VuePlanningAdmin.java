package vue;

import javafx.application.Platform;
import control.*;
import model.dao.*;
import persistence.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
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
import javafx.collections.ListChangeListener;
import java.time.LocalDateTime;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.*;

import javafx.scene.input.KeyEvent;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;


// changer les extends pour juste avoit à switch la scene dans l'application
public class VuePlanningAdmin extends BorderPane {
    private DAOUser daoUser;
    private DAOAdmin daoAdmin;
    private DAOSecouriste daoSecouriste;
    private Label blankLabel;

    private Controller control;

    private User user;
    private Admin admin;
    private Secouriste secouriste;

    private HBox topBar;
    private HBox centerPart;
    private Image logo;
    private GridPane mainPane;
    private Button SecouristeButton;
    private Button DPSButton;
    private Button exportButton;
    private Label nomAdmin;
    private Button deconnexionButton;
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
    private RadioButton allDispButton;
    private ToggleGroup buttonGroup;
    private Text dispText;
    private ComboBox<String> SecouristeBox;

    private planningKeyController keyControleur;
    
    private GridPane gridPane;
    
    private Agenda agenda;

    private Label dateLabel;

    private Button addDPSButton;

    public VuePlanningAdmin(Controller c, User u) {
        
        this.control = c;
        this.daoUser = new DAOUser();
        this.daoAdmin = new DAOAdmin();
        this.daoSecouriste = new DAOSecouriste();
        this.user = u;
        this.admin = this.daoAdmin.findById(this.user.getAdmin());
    
        this.gridPane = new GridPane(2, 2);
        
        this.agenda = new Agenda();
        this.dateLabel = new Label();
        this.agenda.setAllowDragging(false);
        this.agenda.setAllowResize(false);
        this.agenda.setDisplayedLocalDateTime(LocalDateTime.of(2030, 2, 1, 0, 0));
        affectationsAgenda();


        this.keyControleur = new planningKeyController(this);
        this.keyControleur.creerAgenda(this.agenda);
        this.control.setPlanningkeyController(this.keyControleur);

        this.gridPane.setFocusTraversable(true);
        this.gridPane.addEventFilter(KeyEvent.KEY_PRESSED, keyControleur);
        Platform.runLater(() -> this.gridPane.requestFocus());

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

        
        this.nomAdmin = new Label(this.admin.getPrenom()+ " "+this.admin.getNom()+ " \n Admin");
        this.nomAdmin.setStyle("-fx-text-fill: white; -fx-font-size: 20px; -fx-alignment: center; -fx-text-alignment: center;");
        this.nomAdmin.setMinWidth(140);
        this.nomAdmin.setTranslateX(-400);
        this.nomAdmin.setTranslateY(20);

        this.imageViewDeconnexion = new ImageView("file:../Image/sortie.png");
        this.imageViewDeconnexion.setFitHeight(80); 
        this.imageViewDeconnexion.setFitWidth(80);  
        this.imageViewDeconnexion.setPreserveRatio(true);

        this.exportButton = new Button("Exporter les données");
        this.exportButton.setMinWidth(130);
        this.exportButton.setStyle("-fx-background-radius: 25px; -fx-border-radius: 25px; -fx-background-color: white;");
        this.exportButton.setFont(Font.font("Arial", 28));
        this.exportButton.setTranslateX(-300); // ajuster selon besoin
        this.exportButton.setTranslateY(20);
        this.exportButton.setOnAction(this.control);

        this.deconnexionButton = new Button();
        this.deconnexionButton.setGraphic(this.imageViewDeconnexion);
        this.deconnexionButton.setMinWidth(40);
        this.deconnexionButton.setOnAction(this.control);
        this.deconnexionButton.setTranslateX(-100);
        this.deconnexionButton.setTranslateY(5);
        this.deconnexionButton.setStyle("-fx-background-radius: 25px; -fx-border-radius: 25px; -fx-background-color: white;");

        

        
        Region spacerLeft = new Region();
        Region spacerRight = new Region();
        HBox.setHgrow(spacerLeft, Priority.ALWAYS);
        HBox.setHgrow(spacerRight, Priority.ALWAYS);
        

        RowConstraints topPart = new RowConstraints();
        topPart.setPercentHeight(60);

        this.topBar.getChildren().addAll(this.imageViewLogo, spacerLeft, this.SecouristeButton, this.DPSButton, spacerRight, this.nomAdmin,this.exportButton ,this.deconnexionButton);

        //creation of the plannig bar
        this.planningBar=new HBox();
        this.planningBar.setTranslateY(50);
        this.planningBar.setTranslateX(50);
        
        this.planningText=new Text("Nom Secouriste");
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
        this.affectButton.setFont(Font.font("Arial",28));
        this.affectButton.setToggleGroup(buttonGroup);
        this.affectButton.setTranslateX(30);
        this.affectButton.setTranslateY(15);
        this.affectButton.selectedProperty().addListener((obs, wasSelected, isNowSelected) -> {
            if (isNowSelected) {
                this.affectationsAgenda(); // C'est votre méthode pour les affectations d'un secouriste
            }
        });

        this.dispButton=new RadioButton();
        this.dispButton.setText("Disponibilités");
        this.dispButton.setFont(Font.font("Arial",28));
        this.dispButton.setToggleGroup(buttonGroup);
        this.dispButton.setTranslateX(30);
        this.dispButton.setTranslateY(40);
        this.dispButton.selectedProperty().addListener((obs, wasSelected, isNowSelected) -> {
            if (isNowSelected) {
                this.disponibilitesAgenda(); // C'est votre méthode pour les affectations d'un secouriste
            }
        });

        this.allDispButton=new RadioButton();
        this.allDispButton.setText("Toutes les DPS");
        this.allDispButton.setFont(Font.font("Arial",28));
        this.allDispButton.setToggleGroup(buttonGroup);
        this.allDispButton.setTranslateX(30);
        this.allDispButton.setTranslateY(65);
        this.allDispButton.selectedProperty().addListener((obs, wasSelected, isNowSelected) -> {
            if (isNowSelected) {
                this.affectationsDps(); // C'est votre méthode pour les affectations d'un secouriste
            }
        });

        this.buttonGroup.selectToggle(this.affectButton);

        this.ChoixBox.getChildren().addAll(this.affectButton,this.dispButton,this.allDispButton);

        this.dispText=new Text("Les Secouristes");
        this.dispText.setFont(Font.font("Arial",32));
        this.dispText.setTranslateY(100);

        
        this.SecouristeBox= new ComboBox<String>();
        this.SecouristeBox.setVisibleRowCount(5);
        System.out.println(this.daoSecouriste.listSec().size());
        ArrayList<String> listSec = new ArrayList<>();
        ObservableList<String> listSecouriste = FXCollections.observableArrayList();
        for (Secouriste sec : this.daoSecouriste.listSec()){
            listSec.add(sec.toString());
        }
        listSecouriste.setAll(listSec);
        this.SecouristeBox.setItems(listSecouriste);
        this.SecouristeBox.setPromptText("Sélectionner \n secouriste");
        this.SecouristeBox.setStyle("-fx-background-radius: 25px; -fx-border-radius: 25px; -fx-background-color: white;-fx-font-size: 20 ;-fx-text-alignment: left ;");
        this.SecouristeBox.setPrefSize(300, 100);
        this.SecouristeBox.setTranslateY(150);
        this.SecouristeBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            this.planningText.setText(newVal);
            this.secouriste = this.control.getApp().getListeSec().getSecByName(newVal);
        });
        
        this.addDPSButton=new Button("Ajouter une DPS");
        this.addDPSButton.setStyle("-fx-background-radius: 25px; -fx-border-radius: 25px; -fx-background-color: white;-fx-font-size: 20 ;-fx-text-alignment: left ;");
        this.addDPSButton.setFont(Font.font("Arial", 26));
        this.addDPSButton.setTranslateY(400);
        this.addDPSButton.setOnAction(this.control);

        this.rightBox.getChildren().addAll(this.textChoix,this.ChoixBox,this.dispText,this.SecouristeBox,this.addDPSButton);

        // Ajout des composants à la grille
        this.agenda.selectedAppointments().addListener(new ListenerPlanningDetailsAdmin(this.control.getApp(),this.control));
        this.blankLabel = new Label();
        this.gridPane.getChildren().addAll(this.planningBar, this.blankLabel, this.agenda, this.rightBox);

        // Configuration agenda taille et position
        this.agenda.setMinWidth(1400);
        this.agenda.setMinHeight(700);
        this.agenda.setTranslateX(0);
        this.agenda.setTranslateY(150);

        // Mise en place de la scène
        this.setStyle("-fx-background-color: #f7f4f4");
        this.setTop(this.topBar);
        this.setCenter(this.gridPane);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE dd MMMM yyyy");
        LocalDateTime now = agenda.getDisplayedLocalDateTime();
        this.dateLabel.setText(now.format(formatter));
        
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

    public Button getDPSButton(){
        return this.DPSButton;
    }


    public Button getSecouristeButton(){
        return this.SecouristeButton;
    }
    
    public Button getAddDPSButton(){
        return this.addDPSButton;
    }

    public Button getExportButton() {
        return this.exportButton;
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

        if(this.secouriste != null){
            for(Agenda.Appointment agenda : this.listAffec()){
                this.agenda.appointments().add(agenda);
                this.agenda.setEditAppointmentCallback(param -> null);
            }
        }

        
    }

    public ArrayList<Agenda.Appointment> listAffec(){

        ArrayList<Agenda.Appointment> list = new ArrayList<>();

        for(int i : this.secouriste.getDps()){

            DPS dps = this.control.getApp().getListeDps().dpsById(i);

            Agenda.Appointment appointment = new Agenda.AppointmentImplLocal()
                .withStartLocalDateTime(LocalDateTime.of(dps.getAnnee(), dps.getMois(), dps.getJour(), 0, 0).withHour(dps.getHoraireDepart()).withMinute(0))
                .withEndLocalDateTime(LocalDateTime.of(dps.getAnnee(), dps.getMois(), dps.getJour(), 0, 0).withHour(dps.getHoraireFin()).withMinute(59))
                .withSummary(dps.getSport());
            
            list.add(appointment);
        }

        return list;
    }

    public void disponibilitesAgenda(){
        this.agenda.appointments().clear();
        System.out.println("dispo");

        if(this.secouriste != null){
            System.out.println("secouriste !null");
            for(Agenda.Appointment agenda : this.listdispo()){
                this.agenda.appointments().add(agenda);
                this.agenda.setEditAppointmentCallback(param -> null);
            }
        }
        
    }

    public ArrayList<Agenda.Appointment> listdispo(){
        
        ArrayList<Agenda.Appointment> list = new ArrayList<>();

        for(Journee j : this.secouriste.getDispo()){

            Agenda.Appointment appointment = new Agenda.AppointmentImplLocal()
                .withStartLocalDateTime(LocalDateTime.of(j.getAnnee(), j.getMois(), j.getJour(), 0, 0).withHour(0).withMinute(0))
                .withEndLocalDateTime(LocalDateTime.of(j.getAnnee(), j.getMois(), j.getJour(), 0, 0).withHour(23).withMinute(59))
                .withSummary("dispo");
            
            list.add(appointment);
        }

        System.out.println("listdisp" +list.size());
        return list;
    }


    public void affectationsDps(){
        this.agenda.appointments().clear();
        System.out.println("dps");

        for(Agenda.Appointment agenda : this.listDps()){
            this.agenda.appointments().add(agenda);
            this.agenda.setEditAppointmentCallback(param -> null);
        }
    }

    public ArrayList<Agenda.Appointment> listDps(){

        ArrayList<Agenda.Appointment> list = new ArrayList<>();

        for(DPS dps : this.control.getApp().getListeDps().listDps()){
            System.out.println("listdps");

            Agenda.Appointment appointment = new Agenda.AppointmentImplLocal()
                .withStartLocalDateTime(LocalDateTime.of(dps.getAnnee(), dps.getMois(), dps.getJour(), 0, 0).withHour(dps.getHoraireDepart()).withMinute(0))
                .withEndLocalDateTime(LocalDateTime.of(dps.getAnnee(), dps.getMois(), dps.getJour(), 0, 0).withHour(dps.getHoraireFin()).withMinute(0))
                .withSummary(dps.getSport());
            
            list.add(appointment);

        }

        return list;
    }


    public void updateCombox(){
        ArrayList<String> listSec = new ArrayList<>();
        ObservableList<String> listSecouriste = FXCollections.observableArrayList();
        for (Secouriste sec : this.daoSecouriste.listSec()){
            listSec.add(sec.toString());
        }
        listSecouriste.setAll(listSec);
        this.SecouristeBox.setItems(listSecouriste);
    }
}