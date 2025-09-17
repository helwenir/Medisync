package vue;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import control.Controller;
public class LogOffVue extends BorderPane{
    private Controller c;
    private Text t;
    private Button confirmButton;
    private Button declineButton;
    private HBox buttonBox;
    public LogOffVue(Controller c,String message){
        this.c=c;
        this.setPrefSize(500, 400);
        this.t=new Text(message);
        this.t.setFont(Font.font("Arial",36));
        this.confirmButton=new Button("Confirmer");
        this.confirmButton.setStyle("-fx-background-radius: 25px; -fx-border-radius: 25px; -fx-border-color: black; -fx-background-color: C6E5B1;");
        this.confirmButton.setFont(Font.font("Arial",28));
        this.confirmButton.setTranslateX(-10);
        this.confirmButton.setTranslateY(-20);
        this.confirmButton.setOnAction(this.c);
        this.declineButton=new Button("Annuler");
        this.declineButton.setStyle("-fx-background-radius: 25px; -fx-border-radius: 25px; -fx-border-color: black; -fx-background-color: FF8585;");
        this.declineButton.setFont(Font.font("Arial",28));
        this.declineButton.setTranslateX(10);
        this.declineButton.setTranslateY(-20);
        this.declineButton.setOnAction(this.c);
        this.buttonBox=new HBox();
        this.buttonBox.getChildren().addAll(this.confirmButton,this.declineButton);
        this.buttonBox.setAlignment(Pos.CENTER);
        this.setCenter(this.t);
        this.setBottom(buttonBox);
    }

    public Button getConfirmButton(){
        return this.confirmButton;
    }

    public Button getDeclineButton(){
        return this.declineButton;
    }
    
}