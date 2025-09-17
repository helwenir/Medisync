package application;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import vue.VueLogin;
import control.Controller;
import control.ControllerClose;

import model.*; 

public class App extends Application{
    private Stage s;
    private Stage logOff;
    private Stage addSecouriste;
    private Stage removeSecouriste;
    private Stage editSecouriste;
    private Stage detailEvent;
    private Stage addDPS;
    private Stage editDPS;
    private Stage addComp;
    private ControllerClose c2;

    private ListeComp listeComp ;
    private ListeDPS listeDps;
    private ListeSecouriste listeSecouriste;

    public void start(Stage stage){
        Controller c=new Controller(this);
        this.listeComp = new ListeComp();
        this.listeDps = new ListeDPS();
        this.listeSecouriste = new ListeSecouriste();
        this.c2=new ControllerClose(c, this);

        
        this.s=stage;
        this.s.setTitle("Medisync");
        VueLogin v=new VueLogin(c, this);
        c.SetVueLogin(v);
        Scene scene=new Scene(v);
        this.s.setFullScreen(false);
        this.s.setScene(scene);
        this.s.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void switchScene(Pane vue){
        // voir pour fair s.getScene.getRoot();
        this.s.getScene().setRoot(vue);
    }
    public void addLogOff(Pane pop){
        Scene scene = new Scene(pop);
        this.logOff=new Stage();
        this.logOff.setTitle("MediSync");
        this.logOff.setFullScreen(false); // je sais pas par quelle magie noire ça fonctionne mais sur windows faut l'argument à false et sur linux à true
        this.logOff.setScene(scene);
        this.logOff.centerOnScreen();
        this.logOff.show();
        this.logOff.sizeToScene();
        this.logOff.setOnCloseRequest(this.c2);
    }

    public void clearLogOff(){
        this.logOff.close();
        this.logOff=null;
    }

    public Stage getLogOff(){
        return this.logOff;
    }
    

    public void addPopupAddSecouriste(Pane vue){
        Scene s=new Scene(vue);
        this.addSecouriste=new Stage();
        this.addSecouriste.setTitle("MediSync");
        this.addSecouriste.setFullScreen(false);
        this.addSecouriste.setScene(s);
        this.addSecouriste.show();
        this.addSecouriste.setOnCloseRequest(this.c2);
    }

    public Stage getAddSecouriste(){
        return this.addSecouriste;
    }

    public void closeAddSecouristeView(){
        this.addSecouriste.close();
        this.addSecouriste=null;
    }
    

    public void addPopupRemoveSecouriste(Pane vue){
        Scene s=new Scene(vue);
        this.removeSecouriste=new Stage();
        this.removeSecouriste.setTitle("MediSync");
        this.removeSecouriste.setFullScreen(false);
        this.removeSecouriste.setScene(s);
        this.removeSecouriste.show();
        this.removeSecouriste.setOnCloseRequest(this.c2);
    }

    public Stage getRemoveSecouriste(){
        return this.removeSecouriste;
    }

    public void closeRemoveSecouristeView(){
        this.removeSecouriste.close();
        this.removeSecouriste=null;
    }

    public void addPopupEditSecouriste(Pane vue){
        Scene s=new Scene(vue);
        this.editSecouriste=new Stage();
        this.editSecouriste.setTitle("MediSync");
        this.editSecouriste.setFullScreen(false);
        this.editSecouriste.setScene(s);
        this.editSecouriste.show();
        this.editSecouriste.setOnCloseRequest(this.c2);
    }

    public Stage getEditSecouriste(){
        return this.editSecouriste;
    }

    public void closeEditSecouristeView(){
        this.editSecouriste.close();
        this.editSecouriste=null;
    }

    public void addPopupDetailEvent(Pane vue){
        Scene s=new Scene(vue);
        this.detailEvent=new Stage();
        this.detailEvent.setTitle("MediSync");
        this.detailEvent.setFullScreen(false);
        this.detailEvent.setScene(s);
        this.detailEvent.show();
    }

    public void closeDetailEventVue(){
        this.detailEvent.close();
        this.detailEvent=null;
    }

    public void addPopupAddDPS(Pane vue){
        Scene s=new Scene(vue);
        this.addDPS=new Stage();
        this.addDPS.setTitle("MediSync");
        this.addDPS.setFullScreen(false);
        this.addDPS.setScene(s);
        this.addDPS.show();
        this.addDPS.setOnCloseRequest(this.c2);
    }

    

    public Stage getAddDPS(){
        return this.addDPS;
    }

    public void closeAddDpsView(){
        this.addDPS.close();
        this.addDPS=null;
    }

    public void addPopupEditDPS(Pane vue){
        Scene s=new Scene(vue);
        this.addDPS=new Stage();
        this.addDPS.setTitle("MediSync");
        this.addDPS.setFullScreen(false);
        this.addDPS.setScene(s);
        this.addDPS.show();
        this.addDPS.setOnCloseRequest(this.c2);
    }

    public Stage getEditDPS(){
        return this.addDPS;
    }

    public void closeEditDpsView(){
        this.addDPS.close();
        this.addDPS=null;
    }

    public void addCompView(Pane vue){
        Scene s=new Scene(vue);
        this.addComp=new Stage();
        this.addComp.setTitle("MediSync");
        this.addComp.setFullScreen(false);
        this.addComp.setScene(s);
        this.addComp.show();
        this.addComp.setOnCloseRequest(this.c2);
    }

    public Stage getAddComp(){
        return this.addComp;
    }

    public void closeAddCompView(){
        this.addComp.close();
        this.addComp=null;
    }

    public ListeComp getListComp(){
        return this.listeComp;
    }

    public ListeDPS getListeDps(){
        return this.listeDps;
    }

    public ListeSecouriste getListeSec(){
        return this.listeSecouriste;
    }
}