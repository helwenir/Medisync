package control;

import vue.VuePlanningSecouriste;
import vue.VuePlanningAdmin;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import jfxtras.scene.control.agenda.Agenda;
import javafx.event.EventHandler;
import java.time.LocalDateTime;
import java.time.LocalDate;

public class planningKeyController implements EventHandler<KeyEvent> {

    private VuePlanningSecouriste vueSecouriste;
    private VuePlanningAdmin vueAdmin;
    private Agenda agenda;
    private LocalDate currentDate;

    // Constructeur pour VuePlanningSecouriste
    public planningKeyController(VuePlanningSecouriste vue) {
        this.vueSecouriste = vue;
    }

    // Constructeur pour VuePlanningAdmin
    public planningKeyController(VuePlanningAdmin vue) {
        this.vueAdmin = vue;
    }

    public void creerAgenda(Agenda a) {
        this.agenda = a;
    }

    @Override
    public void handle(KeyEvent event) {
        if (event.getEventType() == KeyEvent.KEY_PRESSED) {
            if (event.getCode() == KeyCode.D || event.getCode() == KeyCode.RIGHT) {
                this.changerJourR();
            } else if (event.getCode() == KeyCode.Q || event.getCode() == KeyCode.LEFT) {
                this.changerJourL();
            } else if (event.getCode().toString().equals("ENTER")) {
                if (vueSecouriste != null) {
                    this.vueSecouriste.getGridPane().requestFocus();
                } else if (vueAdmin != null) {
                    this.vueAdmin.getGridPane().requestFocus();
                }
            }
        }
    }
    
    public void changerJourR() {
        if (this.agenda != null) {
            this.agenda.setDisplayedLocalDateTime(agenda.getDisplayedLocalDateTime().plusWeeks(1));
            if (vueSecouriste != null) {
                vueSecouriste.setAgenda(agenda);
                vueSecouriste.updateDateLabel();
            } else if (vueAdmin != null) {
                vueAdmin.setAgenda(agenda);
                vueAdmin.updateDateLabel();
            }
        }
    }

    public void changerJourL() {
        if (this.agenda != null) {
            this.agenda.setDisplayedLocalDateTime(agenda.getDisplayedLocalDateTime().minusWeeks(1));
            if (vueSecouriste != null) {
                vueSecouriste.setAgenda(agenda);
                vueSecouriste.updateDateLabel();
            } else if (vueAdmin != null) {
                vueAdmin.setAgenda(agenda);
                vueAdmin.updateDateLabel();
            }
        }
    }

    public void goToday() {
        if (this.agenda != null) {
            this.agenda.setDisplayedLocalDateTime(agenda.getDisplayedLocalDateTime().of(2030, 2, 1, 0, 0));
            if (vueSecouriste != null) {
                vueSecouriste.setAgenda(agenda);
                vueSecouriste.updateDateLabel();
            } else if (vueAdmin != null) {
                vueAdmin.setAgenda(agenda);
                vueAdmin.updateDateLabel();
            }
        }
    }   
}

