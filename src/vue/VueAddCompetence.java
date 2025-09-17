package vue;

import java.util.ArrayList;

import control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import persistence.Competences;

public class VueAddCompetence extends BorderPane {

    private Controller control;
    private Text title;
    
    private VBox centerBox;
    private Text nomCompText;
    private TextField nomCompField;
    
    private Text compRequieredText;
    private ComboBox<String> compRequieredBox;

    private Button saveButton;

    public VueAddCompetence(Controller c){
        this.control=c;

        this.title=new Text("Ajout d'une Compétence");
        this.title.setFont(Font.font("Arial", 36));
        this.title.setTranslateX(25);
        this.title.setTranslateY(10);

        this.nomCompText= new Text("Nouvelle compétence");
        this.nomCompText.setFont(Font.font("Arial", 25));

        this.nomCompField=new TextField();
        this.nomCompField.setPromptText("Compétence");
        this.nomCompField.setFont(Font.font("Arial", 20));
        this.nomCompField.setMinSize(250, 40);
        this.nomCompField.setMaxSize(250, 40);


        this.compRequieredText=new Text("Compétence Requise");
        this.compRequieredText.setFont(Font.font("Arial", 25));
        this.compRequieredText.setTranslateY(30);

        this.compRequieredBox=new ComboBox<>();
        this.compRequieredBox.setPromptText("Compétence");
        this.compRequieredBox.setStyle("-fx-background-radius: 25; -fx-border-radius: 25px; -fx-background-color:#ffffff;-fx-font-size: 20;");
        this.compRequieredBox.setTranslateY(30);
        ArrayList<String> listComp = new ArrayList<>();
        ObservableList<String> listeCompetence = FXCollections.observableArrayList();
        for (Competences comp : this.control.getApp().getListComp().getListComp()){
            listComp.add(comp.getIntitule());
        }
        listeCompetence.setAll(listComp);
        this.compRequieredBox.setItems(listeCompetence);

        this.centerBox=new VBox();
        this.centerBox.getChildren().addAll(this.nomCompText,this.nomCompField,this.compRequieredText,this.compRequieredBox);
        this.centerBox.setTranslateX(50);
        this.centerBox.setTranslateY(40);

        this.saveButton= new Button("Sauvegarder");
        this.saveButton.setStyle("-fx-background-radius: 25; -fx-border-radius: 25px; -fx-background-color: #5fc785;-fx-text-alignment:center;");
        this.saveButton.setFont(Font.font("Arial", 30));
        this.saveButton.setTranslateX(130);
        this.saveButton.setTranslateY(-30);
        this.saveButton.setOnAction(this.control);

        this.setMinSize(500, 400);

        this.setTop(this.title);
        this.setCenter(this.centerBox);
        this.setBottom(this.saveButton);
    }

    public Button getSaveButton(){
        return this.saveButton;
    }
}