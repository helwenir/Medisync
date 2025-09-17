package control;

import javafx.collections.ListChangeListener;
import javafx.collections.ListChangeListener.Change;
import jfxtras.scene.control.agenda.Agenda;
import jfxtras.scene.control.agenda.Agenda.Appointment;
import javafx.collections.ListChangeListener.*;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import application.App;
import model.PlanningModel;
import vue.VueDetailEvent;

public class ListenerPlanningDetailsAdmin implements ListChangeListener<Agenda.Appointment> {

    private VueDetailEvent VueDetailEvent;
    private App a;
    private Controller c;

    public ListenerPlanningDetailsAdmin(App a,Controller c){
        this.a=a;
        this.c=c;
    }

    @Override
    public void onChanged(Change<? extends Agenda.Appointment> appointmentsList) {
        PlanningModel model = new PlanningModel();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH'h'mm", Locale.FRENCH);
        while (appointmentsList.next()) {
            if (appointmentsList.wasAdded()) {
                for (Agenda.Appointment app : appointmentsList.getAddedSubList()) {
                    this.VueDetailEvent=new VueDetailEvent(app.getSummary() + " :", app.getLocation(),  app.getStartLocalDateTime().format(formatter) + " - " + app.getEndLocalDateTime().format(formatter), app.getDescription(), null,true,c);
                    this.a.addPopupDetailEvent(this.VueDetailEvent);
                }
            }
        }
    }
}
