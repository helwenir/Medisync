package vue;

import control.Controller;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class VueDetailEvent extends BorderPane{

    private Controller control;

    private Text nomDiscipline;
    private VBox centerBox;
    private Text lieuxText;
    private Text horaire;
    private Text compText;
    private Text compDemandeText;
    private Text secouristeText;
    private Text secouristeAffecteText;

    private Button editButton;

    public VueDetailEvent(String discipline,String lieux,String horaire,String comp,String secouriste,boolean admin,Controller c){

        this.control=c;

        this.nomDiscipline=new Text(discipline);
        this.nomDiscipline.setFont(Font.font("Arial", 40));
        this.nomDiscipline.setFill(Color.WHITE);

        String txtLieux="Lieux : "+lieux;
        this.lieuxText=new Text(txtLieux);
        this.lieuxText.setFont(Font.font("Arial", 34));
        this.lieuxText.setFill(Color.WHITE);
        
        String txtHoraire="Horaire : "+horaire;
        this.horaire=new Text(txtHoraire);
        this.horaire.setFont(Font.font("Arial", 34));
        this.horaire.setFill(Color.WHITE);

        this.compText=new Text("Compétences : ");
        this.compText.setFont(Font.font("Arial", 34));
        this.compText.setFill(Color.WHITE);

        this.compDemandeText=new Text(comp);
        this.compDemandeText.setFont(Font.font("Arial", 34));
        this.compDemandeText.setFill(Color.WHITE);

        this.secouristeText=new Text("Secouristes : ");
        this.secouristeText.setFont(Font.font("Arial", 34));
        this.secouristeText.setFill(Color.WHITE);

        this.secouristeAffecteText=new Text(secouriste);
        this.secouristeAffecteText.setFont(Font.font("Arial", 34));
        this.secouristeAffecteText.setFill(Color.WHITE);

        this.centerBox=new VBox();
        this.centerBox.setTranslateY(30);
        this.centerBox.getChildren().addAll(this.lieuxText,this.horaire,this.compText,this.compDemandeText,this.secouristeText,this.secouristeAffecteText);
        

        ImageView editImage=new ImageView(new Image("file:../Image/pencil.png"));
        editImage.setFitHeight(75);
        editImage.setFitHeight(75);
        editImage.setPreserveRatio(true);

        this.editButton=new Button();
        this.editButton.setStyle("-fx-background-radius: 25; -fx-border-radius: 25px; -fx-background-color: white;");
        this.editButton.setGraphic(editImage);
        this.editButton.setMaxSize(75, 75);
        this.editButton.setTranslateX(350);
        this.editButton.setTranslateY(-30);
        this.control.setVueDetailEvent(this);
        this.editButton.setOnAction(this.control);

        this.setMinSize(500, 400);
        this.setStyle("-fx-background-color:  #082361;");
        this.setTop(this.nomDiscipline);
        this.setCenter(this.centerBox);
        if(admin){
            this.setBottom(this.editButton);
        }

    }

    public Button getEditButton(){
        return this.editButton;
    }
}