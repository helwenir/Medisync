package control;
import vue.VuePlanningSecouriste;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import jfxtras.scene.control.agenda.Agenda;
import java.time.LocalDateTime;
import model.PlanningModel;


public class planningControler implements EventHandler<ActionEvent>{
    private VuePlanningSecouriste accueil;
    private PlanningModel model;
    private Agenda agenda;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private String summary;
    private String location;
    private String typeAction;
    private String[] competences;
    public planningControler(VuePlanningSecouriste accueil, Agenda agenda, int month, int day, int hour, int minute, String summary, String location, String typeAction, String[] competences) {
        this.accueil = accueil;
        this.agenda = agenda;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.summary = summary;
        this.location = location;
        this.typeAction = typeAction;
        this.competences = competences;
        this.model = new PlanningModel();
    }
    public void handle(ActionEvent event){
        agenda = accueil.getAgenda();
        if (typeAction.equals("ajouter")) {
            ajouterRendezVous();
        } else if (typeAction.equals("supprimer")) {
            supprimerRendezVous();
        } 
    }

    public void ajouterRendezVous() {
        LocalDateTime debut = LocalDateTime.of(2025, month, day, hour, minute);
        LocalDateTime fin = LocalDateTime.of(2025, month, day, (hour+2), minute);
        String description = model.concatenateDescription(competences);
        accueil.addEventAgenda(debut, fin, summary, location, description);
    }

    public void supprimerRendezVous() {
        LocalDateTime dateHeureCible = LocalDateTime.of(2025, month, day, hour, minute);
        agenda.appointments().removeIf(appt -> appt.getStartLocalDateTime().equals(dateHeureCible));
    }
}
