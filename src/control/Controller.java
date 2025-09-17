package control;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.DialogEvent;
import javafx.scene.input.*;
import javafx.scene.layout.Pane;
import jfxtras.scene.control.agenda.Agenda;

import vue.*;
import persistence.*;
import model.GrapheModele;
import model.dao.*;
import java.util.ArrayList;
import javafx.stage.FileChooser;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.*;
import javafx.stage.Stage;

import application.App;
import application.App;

public class Controller implements EventHandler<ActionEvent> {
    private App app;
    private VueLogin vueLogin;
    private VuePlanningSecouriste vuePlanningSecouriste;
    private VueCompetenceSecouriste vueCompSecouriste;
    private LogOffVue lov;
    private Controller control;
    private DAOUser daoUser;
    private DAOSecouriste daoSec;
    private String username;
    private Secouriste sec;
    private User currentUser;

    private GrapheModele grapheModele;

    private VueEditCompetence Vec;
    private VuePlanningAdmin vuePlanningAdmin;
    private VueSecouristeAdmin vueSecouristeAdmin;
    private VueAddSecouriste vueAddSecouriste;
    private VueRemoveSecouriste vueRemoveSecouriste;
    private VueEditSecouriste vueEditSecouriste;
    private VueEditCompetenceAdmin vueEditCompetenceAdmin;
    private VueAddDPS vueAddDPS;
    private VueAddCompetence vueAddCompetence;
    private VueDetailEvent vueDetailEvent;
    private VueEditDPS vueEditDPS;
    private ArrayList<Button> listbuttonSwitchColor;

    private Agenda agenda;
    private ScrollController sc;
    private planningKeyController controller;

    public Controller(App application) {
        this.app = application;
        this.daoUser = new DAOUser();
        this.daoSec = new DAOSecouriste();
        this.listbuttonSwitchColor = new ArrayList<>();
        this.grapheModele = GrapheModele.getInstance();
        this.sc=new ScrollController();
    }

    public void SetVueLogin(VueLogin v){
        this.vueLogin=v;
    }

    public void setPlanningkeyController(planningKeyController k){
        this.controller=k;
    }

    @Override
    public void handle(ActionEvent event){
        
        if (this.vueLogin!=null){
            if(event.getSource()==this.vueLogin.getPassButton()){
                this.vueLogin.clearPass();
            }
            if(event.getSource()==this.vueLogin.getUserButton()){
                this.vueLogin.clearUser();
            }
            if(event.getSource()==this.vueLogin.getLoginButton()){
                this.currentUser = this.daoUser.veriferIdentifiants(this.vueLogin.getLogin(), this.vueLogin.getPassword());
            if (this.currentUser != null){
                System.out.println("connection");

                if(this.currentUser.getRole().equals("admin")){
                    this.vuePlanningAdmin = new VuePlanningAdmin(this, currentUser);
                    this.app.switchScene(vuePlanningAdmin);
                } else {
                    this.vuePlanningSecouriste = new VuePlanningSecouriste(this, currentUser);
                    this.app.switchScene(this.vuePlanningSecouriste);
                }
            } else { 
                System.out.println("Login failed");
            }
            }else{
                this.vueLogin.clearUser();
                this.vueLogin.clearPass();

            }
        }

        if (this.vuePlanningSecouriste!=null){
            if (event.getSource()==this.vuePlanningSecouriste.getDeconnexionButton()) {
                if (this.lov==null){            
                    this.lov=new LogOffVue(this, "Voulez-vous vraiment \n vous déconnecter ? ");
                    this.app.addLogOff(lov);
                }
            }if(event.getSource()==this.vuePlanningSecouriste.getCompetenceButton()){
                if (this.vueCompSecouriste!=null){
                    this.app.switchScene(vueCompSecouriste);
                }else{
                    this.vueCompSecouriste = new VueCompetenceSecouriste(this, this.currentUser);
                    this.app.switchScene(this.vueCompSecouriste);
                }
            }
            if(event.getSource()==this.vuePlanningSecouriste.getTodayButton()){
                System.out.println("todayButton");
                this.controller.goToday();
                this.vuePlanningSecouriste.updateDateLabel();
                
            }if(event.getSource()==this.vuePlanningSecouriste.getLeftButton()){
                System.out.println("leftButton");
                this.controller.changerJourL();
                this.vuePlanningSecouriste.updateDateLabel();
                
            }if(event.getSource()==this.vuePlanningSecouriste.getRightButton()){
                System.out.println("rightButton");
                this.controller.changerJourR();
                this.vuePlanningSecouriste.updateDateLabel();
            }if(event.getSource() == this.vuePlanningSecouriste.getSuppButton()){
                System.out.println("remove");
                this.vuePlanningSecouriste.getSecouriste().removeDispo(this.vuePlanningSecouriste.getRmBox());
                this.vuePlanningSecouriste.updateCombox();
                this.vuePlanningSecouriste.disponibilitesAgenda();
            }if(event.getSource() == this.vuePlanningSecouriste.getAddButton()){
                System.out.println("add");
                String[] s=this.vuePlanningSecouriste.getDPSToADD().split("/");
                int day=Integer.parseInt(s[0]);
                int month=Integer.parseInt(s[1]);
                int year=Integer.parseInt(s[2]);
                Journee j=new Journee(day,month,year);
                this.vuePlanningSecouriste.getSecouriste().addDispo(j);
                 this.vuePlanningSecouriste.updateCombox();
                 this.vuePlanningSecouriste.disponibilitesAgenda();
            }
        }   

        if (this.lov!=null){
            if (event.getSource()==this.lov.getConfirmButton()) {
                this.lov=null;
                this.app.switchScene(vueLogin);
                this.app.clearLogOff();
                this.vuePlanningSecouriste=null;
                this.vueCompSecouriste=null;
                this.Vec=null;
                this.vuePlanningAdmin=null;
            }else if (event.getSource()==this.lov.getDeclineButton()) {
                this.lov=null;
                this.app.clearLogOff();
            }
        }
        
        if (this.vueCompSecouriste!=null){
            if (event.getSource()==this.vueCompSecouriste.getPlanningButton()){
                if (this.vuePlanningSecouriste!=null){
                    this.app.switchScene(this.vuePlanningSecouriste);
                }else{
                    this.vuePlanningSecouriste=new VuePlanningSecouriste(this, null);
                    this.app.switchScene(this.vuePlanningSecouriste);
                }
            }if(event.getSource()==this.vueCompSecouriste.getDeconnexionButton()){
                if (this.lov==null){            
                    this.lov=new LogOffVue(this, "Voulez-vous vraiment \n vous déconnecter ? ");
                    this.app.addLogOff(lov);
                }
            }if(event.getSource()==this.vueCompSecouriste.getEditButton()){
                if (this.Vec == null) {
                    this.Vec = new VueEditCompetence(this, this.vueCompSecouriste,currentUser);
                }
                this.app.switchScene(this.Vec);
                this.Vec.drawEdges(grapheModele);
                this.Vec.getSaveButton().setVisible(true);
                this.vueCompSecouriste.setIsEditable(true);
            }
        }

        if (this.Vec != null) {
            if (event.getSource() == this.Vec.getSaveButton()){
                Pane pane1 = this.Vec.getVueCompScene();
                System.out.println("balls");
                this.app.switchScene(pane1);

                if (pane1 instanceof VueCompetenceSecouriste vueComp) {
                    vueComp.drawEdges(grapheModele);
                    vueComp.setIsEditable(false);
                }
                listbuttonSwitchColor.clear();
            }
            if (event.getSource() == this.Vec.getDeconnexionButton()) {
                if (this.lov == null) {
                    this.lov = new LogOffVue(this, "Voulez-vous vraiment \n vous déconnecter ? ");
                    this.app.addLogOff(lov);
                }
            }
        }

        if (this.vuePlanningAdmin!=null){
            if (event.getSource() == this.vuePlanningAdmin.getExportButton()) {
                    this.exportToCSV();
            }
            if(event.getSource()==this.vuePlanningAdmin.getDeconnexionButton()){
                if (this.lov==null){            
                    this.lov=new LogOffVue(this, "Voulez-vous vraiment \n vous déconnecter ? ");
                    this.app.addLogOff(lov);
                }
            }if(event.getSource()==this.vuePlanningAdmin.getLeftButton()){
                System.out.println("Left Button");
                this.controller.changerJourL();
                this.vuePlanningAdmin.updateDateLabel();
            }if(event.getSource()==this.vuePlanningAdmin.getRightButton()){
                System.out.println("Right Button");
                this.controller.changerJourR();
                this.vuePlanningAdmin.updateDateLabel();
            }if (event.getSource()==this.vuePlanningAdmin.getTodayButton()){
                System.out.println("Today Button");
                this.controller.goToday();
                this.vuePlanningAdmin.updateDateLabel();
            }
            if (event.getSource()==this.vuePlanningAdmin.getSecouristeButton()){
                if (this.vueSecouristeAdmin==null){
                    this.vueSecouristeAdmin=new VueSecouristeAdmin(this, this.currentUser,this.sc);
                    this.app.switchScene(this.vueSecouristeAdmin);
                }else{
                    this.app.switchScene(this.vueSecouristeAdmin);
                }
            }if(event.getSource()==this.vuePlanningAdmin.getAddDPSButton()){
                if(this.vueAddDPS==null){
                    this.vueAddDPS=new VueAddDPS(this,this.sc);
                    this.app.addPopupAddDPS(this.vueAddDPS);
                }
            }
        }

        if (this.vueSecouristeAdmin!=null){
            if (event.getSource()==this.vueSecouristeAdmin.getDeconnexionButton()){
                if (this.lov==null){            
                    this.lov=new LogOffVue(this, "Voulez-vous vraiment \n vous déconnecter ? ");
                    this.app.addLogOff(lov);
                }
            }if (event.getSource()==this.vueSecouristeAdmin.getDPSButton()){
                if (vuePlanningAdmin==null){
                    this.vuePlanningAdmin=new VuePlanningAdmin(this, null);
                    this.app.switchScene(this.vuePlanningAdmin);
                }else{
                    this.app.switchScene(this.vuePlanningAdmin);
                }
            }if (event.getSource()==this.vueSecouristeAdmin.getAddButton()){
                if(this.vueAddSecouriste==null){
                    this.vueAddSecouriste=new VueAddSecouriste(this);
                    this.app.addPopupAddSecouriste(this.vueAddSecouriste);
                }
            }if (event.getSource()==this.vueSecouristeAdmin.getDeleteButton()){
                if(this.vueRemoveSecouriste==null){
                    this.vueRemoveSecouriste=new VueRemoveSecouriste(this, this.vueSecouristeAdmin.getScrollController(), this.vueSecouristeAdmin.getListSecouriste());
                    this.app.addPopupRemoveSecouriste(this.vueRemoveSecouriste);
                }
            }if (event.getSource()==this.vueSecouristeAdmin.getEditButton()){
                if(this.vueEditSecouriste==null){
                    this.vueEditSecouriste=new VueEditSecouriste(this, this.vueSecouristeAdmin.getScrollController(), this.vueSecouristeAdmin.getListSecouriste());
                    this.app.addPopupEditSecouriste(this.vueEditSecouriste);
                }
            }
        }



        if(this.vueAddSecouriste!=null){
            if(event.getSource()==this.vueAddSecouriste.getNameClear()){
                this.vueAddSecouriste.clearName();
            }if(event.getSource()==this.vueAddSecouriste.getFirstNameClear()){
                this.vueAddSecouriste.clearFirstName();
            }if(event.getSource()==this.vueAddSecouriste.getPassClear()){
                this.vueAddSecouriste.clearPass();
            }if(event.getSource()==this.vueAddSecouriste.getDateClear()){
                this.vueAddSecouriste.clearDate();
            }if(event.getSource()==this.vueAddSecouriste.getMailClear()){
                this.vueAddSecouriste.clearMail();
            }if(event.getSource()==this.vueAddSecouriste.getPhoneClear()){
                this.vueAddSecouriste.clearPhone();
            }if(event.getSource()==this.vueAddSecouriste.getAddrClear()){
                this.vueAddSecouriste.clearAddr();
            }if(event.getSource()==this.vueAddSecouriste.getMailClear()){
                this.vueAddSecouriste.clearMail();
            }if(event.getSource()==this.vueAddSecouriste.getSaveButton()){
                String name=this.vueAddSecouriste.getName();
                String firstName=this.vueAddSecouriste.getFirstName();
                Secouriste sec = new Secouriste(name, firstName, this.vueAddSecouriste.getDateNaissance(), this.vueAddSecouriste.getMail(), this.vueAddSecouriste.getTel(), this.vueAddSecouriste.getAdresse());
                this.daoSec.create(sec);
                User user = new User(name, firstName, this.vueAddSecouriste.getPassWord(), "secouriste", sec.getId(), 0);
                this.daoUser.createCompteSec(user);
                this.app.closeAddSecouristeView();
                this.vueAddSecouriste=null;
                this.vueSecouristeAdmin.addSecoursiteToList(firstName,name);
            }
        }if(this.vueRemoveSecouriste!=null){
            if(event.getSource()==this.vueRemoveSecouriste.getRemoveButton()){
                String name=this.vueRemoveSecouriste.getNameSecouristeRemove();
                this.app.closeRemoveSecouristeView();
                this.vueRemoveSecouriste=null;
                this.daoSec.delete(name);
                this.vueSecouristeAdmin.removeSecouristeFromList(name);
                this.vueSecouristeAdmin.upadteTextAfterRemove();
                this.vuePlanningAdmin.updateCombox();
            }
        }

        if (this.vueEditSecouriste!=null){
            if(event.getSource()==this.vueEditSecouriste.getEditButton()){
                if (this.vueEditCompetenceAdmin==null){
                    this.vueCompSecouriste=new VueCompetenceSecouriste(this,this.vueEditSecouriste.getUserSelected());
                    this.vueEditCompetenceAdmin=new VueEditCompetenceAdmin(this,this.vueCompSecouriste,this.vueEditSecouriste.getNameSecouristeEdit(),this.currentUser);
                    this.app.closeEditSecouristeView();
                    this.vueEditSecouriste=null;
                }
                this.app.switchScene(this.vueEditCompetenceAdmin);
                this.vueEditCompetenceAdmin.drawEdges(grapheModele);
                this.vueEditCompetenceAdmin.getSaveButton().setVisible(true);
                this.vueCompSecouriste.setIsEditable(true);
            }
        }


        if(this.vueAddDPS!=null){
            if(event.getSource()==this.vueAddDPS.getIdClear()){
                this.vueAddDPS.clearID();
            }if(event.getSource()==this.vueAddDPS.getHeureDebutClear()){
                this.vueAddDPS.clearHeureDebut();
            }if(event.getSource()==this.vueAddDPS.getHeureFinClear()){
                this.vueAddDPS.clearHeureFin();
            }if(event.getSource()==this.vueAddDPS.getSaveButton()){
                
                if(this.vueAddDPS.getSite()!= null && this.vueAddDPS.getSport() != null){
                    try{
                        int hDe = Integer.parseInt(this.vueAddDPS.getHeureDebut());
                        int hFi = Integer.parseInt(this.vueAddDPS.getHeureFin());

                        String[] jour = this.vueAddDPS.getJour().split("/");  
                        int j =  Integer.parseInt(jour[0]);
                        int m  = Integer.parseInt(jour[1]);
                        int a = Integer.parseInt(jour[2]);

                        String site = this.vueAddDPS.getSite();
                        String sport = this.vueAddDPS.getSport();

                        DPS dps = new DPS(hDe, hFi, j, m, a, site, sport);
                        DAODps dao = new DAODps();
                        dao.create(dps);
                        this.app.getListeDps().adddps(dps);

                    }catch(NumberFormatException e){
                        e.printStackTrace();
                        System.out.println("dps non valide");
                    }
                }
                this.app.closeAddDpsView();
                this.vueAddDPS=null;
            }
        }

        if (this.vueEditCompetenceAdmin != null) {
            if (event.getSource() == this.vueEditCompetenceAdmin.getDeconnexionButton()) {
                if (this.lov == null) {
                    this.lov = new LogOffVue(this, "Voulez-vous vraiment \n vous déconnecter ? ");
                    this.app.addLogOff(lov);
                }
            }if(event.getSource()==this.vueEditCompetenceAdmin.getAddCompButton()){
                if(this.vueAddCompetence==null){
                    this.vueAddCompetence=new VueAddCompetence(this);
                    this.app.addCompView(this.vueAddCompetence);
                }
            }
            if (event.getSource() == this.vueEditCompetenceAdmin.getSaveButton()){
                this.app.switchScene(this.vueSecouristeAdmin);
                this.vueEditCompetenceAdmin=null;
            }   
        }

        if(this.vueAddCompetence!=null){
            if(event.getSource()==this.vueAddCompetence.getSaveButton()){
                this.vueAddCompetence=null;
                this.app.closeAddCompView();
            }
        }
        if(this.vueDetailEvent!=null){
            if(event.getSource()==vueDetailEvent.getEditButton()){
                this.vueEditDPS=new VueEditDPS(this,this.sc);
                this.app.addPopupEditDPS(this.vueEditDPS);
                this.app.closeDetailEventVue();
                this.vueDetailEvent=null;
            }
        }

        if(this.vueEditDPS!=null){
            if(event.getSource()==this.vueEditDPS.getIdClear()){
                this.vueEditDPS.clearID();
            }if(event.getSource()==this.vueEditDPS.getHeureDebutClear()){
                this.vueAddDPS.clearHeureDebut();
            }if(event.getSource()==this.vueEditDPS.getHeureFinClear()){
                this.vueEditDPS.clearHeureFin();
            }if(event.getSource()==this.vueEditDPS.getEditButton()){
                this.app.closeEditDpsView();
                this.vueAddDPS=null;
            }if(event.getSource()==this.vueEditDPS.getDeleteButton()){
                this.app.closeEditDpsView();
                this.vueAddDPS=null;
            }
        }

    }

    public void closeLogOffView(){
        this.lov=null;
    }

    public void closeAddSecouristeView(){
        this.vueAddSecouriste=null;   
    }

    public void closeRemoveSecouristeView(){
        this.vueRemoveSecouriste=null;   
    }

    public void closeEditSecouristeView(){
        this.vueEditSecouriste=null;   
    }

    public void closeAddDpsView(){
        this.vueAddDPS=null;   
    }

    public void closeAddCompView(){
        this.vueAddCompetence=null;   
    }

    public App getApp(){
        return this.app;
    }

    public void setVueDetailEvent(VueDetailEvent vue){
        this.vueDetailEvent=vue;
    }

    public void exportToCSV() {
        String jdbcURL = "jdbc:mysql://localhost:3306/bd_sae_secouristes";
        String username = "admin";
        String password = "admin_hash";

        String sql = "SELECT * FROM users";

        try (
            Connection conn = DriverManager.getConnection(jdbcURL, username, password);
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql)
        ) {
            // Sélectionner un fichier via JavaFX
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Enregistrer sous...");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichier CSV", "*.csv"));
            Stage stage = new Stage();
            java.io.File file = fileChooser.showSaveDialog(stage);

            if (file == null) return;

            try (PrintWriter csvWriter = new PrintWriter(new FileWriter(file))) {
                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();

                // Écriture des en-têtes
                for (int i = 1; i <= columnCount; i++) {
                    csvWriter.print(metaData.getColumnName(i));
                    if (i < columnCount) csvWriter.print(",");
                }
                csvWriter.println();

                // Écriture des données
                while (resultSet.next()) {
                    for (int i = 1; i <= columnCount; i++) {
                        csvWriter.print(resultSet.getString(i));
                        if (i < columnCount) csvWriter.print(",");
                    }
                    csvWriter.println();
                }

                System.out.println("Exportation réussie !");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}