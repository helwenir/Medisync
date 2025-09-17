package control;

import application.App;
import javafx.event.EventHandler;
import javafx.scene.control.DialogEvent;
import javafx.stage.WindowEvent;

public class ControllerClose implements EventHandler<WindowEvent>{

    private Controller c;
    private App a;

    public ControllerClose(Controller control,App app){
        this.c=control;
        this.a=app;

    }
    public void handle(WindowEvent event){
        if (event.getSource()==this.a.getLogOff()){
            this.c.closeLogOffView();
        }if(event.getSource()==this.a.getAddSecouriste()){
            this.c.closeAddSecouristeView();
        }if(event.getSource()==this.a.getRemoveSecouriste()){
            this.c.closeRemoveSecouristeView();
        }if(event.getSource()==this.a.getEditSecouriste()){
            this.c.closeEditSecouristeView();
        }if(event.getSource()==this.a.getAddDPS()){
            this.c.closeAddDpsView();
        }if(event.getSource()==this.a.getAddComp()){
            this.c.closeAddCompView();
        }
    }
}