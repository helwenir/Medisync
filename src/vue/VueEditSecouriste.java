package vue;

import java.util.ArrayList;


import control.*;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import persistence.User;

public class VueEditSecouriste extends BorderPane{

    private ArrayList<String> nomSecouriste;

    private ScrollController scrollController;
    private Controller control;

    private Text title;

    private TextField searchTextField;
    private VBox centerBox;

    private ToggleGroup buttonGroup;
    private RadioButton ButtonSecouriste1;
    private RadioButton ButtonSecouriste2;
    private RadioButton ButtonSecouriste3;

    private Button editButton;

    private int imax;
    private int imin;
    public VueEditSecouriste(Controller c,ScrollController sc,ArrayList<String> l){

        
        this.control=c;
        this.scrollController=sc;
        this.scrollController.setEditVue(this);
        this.nomSecouriste=l;
        this.imax=3;
        this.imin=this.nomSecouriste.size()-1;

        this.title=new Text("Supprimer");
        this.title.setFont(Font.font("Arial", 36));
        this.title.setTranslateX(20);
        this.title.setTranslateY(20);

        this.centerBox=new VBox();

        this.searchTextField=new TextField();
        this.searchTextField.setPromptText("Recherchez un secouriste");
        this.searchTextField.setFont(Font.font("Arial", 12));

        this.buttonGroup=new ToggleGroup();

        this.ButtonSecouriste1=new RadioButton(this.nomSecouriste.get(0));
        this.ButtonSecouriste1.setToggleGroup(this.buttonGroup);
        this.ButtonSecouriste1.setFont(Font.font("Arial", 24));
        this.ButtonSecouriste1.setStyle("-fx-border-color: #000000;");
        this.ButtonSecouriste1.setTranslateY(10);

        this.ButtonSecouriste2=new RadioButton(this.nomSecouriste.get(1));
        this.ButtonSecouriste2.setToggleGroup(this.buttonGroup);
        this.ButtonSecouriste2.setFont(Font.font("Arial", 24));
        this.ButtonSecouriste2.setStyle("-fx-border-color: #000000;");
        this.ButtonSecouriste2.setTranslateY(30);

        this.ButtonSecouriste3=new RadioButton(this.nomSecouriste.get(2));
        this.ButtonSecouriste3.setToggleGroup(this.buttonGroup);
        this.ButtonSecouriste3.setFont(Font.font("Arial", 24));
        this.ButtonSecouriste3.setStyle("-fx-border-color: #000000;");
        this.ButtonSecouriste3.setTranslateY(50);

        this.buttonGroup.selectToggle(this.ButtonSecouriste1);


        this.centerBox.getChildren().addAll(this.searchTextField,this.ButtonSecouriste1,this.ButtonSecouriste2,this.ButtonSecouriste3);
        this.centerBox.setOnScroll(this.scrollController);
        this.centerBox.setMaxSize(300, 250);
        this.centerBox.setTranslateY(30);

        this.editButton=new Button("Modifier les \n compétences");
        this.editButton.setFont(Font.font("Arial", 20));
        this.editButton.setStyle("-fx-background-radius: 25px; -fx-border-radius: 25px; -fx-background-color: #5fc785;");
        this.editButton.setOnAction(this.control);
        this.editButton.setTranslateX(120);
        this.editButton.setTranslateY(-10);

        this.setTop(this.title);
        this.setCenter(this.centerBox);
        this.setBottom(this.editButton);

        this.setPrefSize(500, 400);

    }

    public VBox getCenterBox(){
        return this.centerBox;
    }

    public Button getEditButton(){
        return this.editButton;
    }
    /*
    public void updateTextSecouristeUp(){
        this.ButtonSecouriste3.setText(this.ButtonSecouriste2.getText());
        this.ButtonSecouriste2.setText(this.ButtonSecouriste1.getText());
        if (this.imin==0){
            this.ButtonSecouriste1.setText(this.nomSecouriste.get(this.imin));
            this.imin=this.nomSecouriste.size()-1;
            this.imax=2;
        }else{
            this.ButtonSecouriste1.setText(this.nomSecouriste.get(this.imin));
            this.imin--;
            if (this.imax-1==0){
                this.imax=this.nomSecouriste.size()-1;
            }else{
                this.imax--;
            }
        }
    }

    public void updateTextSecouristeDown(){
        this.ButtonSecouriste1.setText(this.ButtonSecouriste2.getText());
        this.ButtonSecouriste2.setText(this.ButtonSecouriste3.getText());
        if (this.imax==this.nomSecouriste.size()-1){
            this.ButtonSecouriste3.setText(this.nomSecouriste.get(this.imax));
            this.imax=0;
            this.imin=this.nomSecouriste.size()-2;
        }else{
            this.ButtonSecouriste3.setText(this.nomSecouriste.get(this.imax));
            this.imax++;
            if (this.imin+1==this.nomSecouriste.size()){
                this.imin=0;
            }else{
                this.imin++;
            }
        }
    }
    */
   public void updateTextSecouristeUp() {
    // Rotation des textes vers le bas
        this.buttonGroup.selectToggle(null);
        this.ButtonSecouriste3.setText(this.ButtonSecouriste2.getText());
        this.ButtonSecouriste2.setText(this.ButtonSecouriste1.getText());

        // Mise à jour de imin avec rotation circulaire
        this.imin = (this.imin - 1 + this.nomSecouriste.size()) % this.nomSecouriste.size();
        this.imax = (this.imin + 2) % this.nomSecouriste.size(); // imax est toujours 2 positions après imin

        // Mise à jour du bouton du haut
        this.ButtonSecouriste1.setText(this.nomSecouriste.get(this.imin));
    }

    public void updateTextSecouristeDown() {
        // Rotation des textes vers le haut
        this.buttonGroup.selectToggle(null);
        this.ButtonSecouriste1.setText(this.ButtonSecouriste2.getText());
        this.ButtonSecouriste2.setText(this.ButtonSecouriste3.getText());

        // Mise à jour de imax avec rotation circulaire
        this.imax = (this.imax + 1) % this.nomSecouriste.size();
        this.imin = (this.imax - 2 + this.nomSecouriste.size()) % this.nomSecouriste.size(); // imin est 2 positions avant imax

        // Mise à jour du bouton du bas
        this.ButtonSecouriste3.setText(this.nomSecouriste.get(this.imax));
    }


    private RadioButton getCurrentSelectedButton(){
        return (RadioButton)this.buttonGroup.getSelectedToggle();
    }

    public String getNameSecouristeEdit(){
        String ret= this.getCurrentSelectedButton().getText();
        return ret;
    }

    public User getUserSelected(){
        User ret= new User(this.getCurrentSelectedButton().getText(), null, null, 1, 0);
        return ret;
    }

    
}