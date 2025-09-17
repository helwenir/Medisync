package vue;

import control.Controller;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import persistence.*;

public class VueAddSecouriste extends BorderPane{

    private Controller control;

    
    private Text Title;

    private VBox leftBox;

    private Text nameText;
    private TextField nameTextField;
    private Button clearName;

    private Text firstNameText;
    private TextField firstNameTextField;
    private Button clearFirstName;

    private Text passText;
    private PasswordField passTextField;
    private Button clearPass;

    
    private VBox centerBox;

    // date de naissance , télephon , adresse , email

    private Text dateText;
    private TextField dateTextField;
    private Button dateClear;

    private Text phoneText;
    private TextField phoneTextField;
    private Button phoneClear;

    private Text mailText;
    private TextField mailTextField;
    private Button mailClear;

    private Text addrText;
    private TextField addrTextField;
    private Button addrClear;

    private Button saveButton;


    public VueAddSecouriste(Controller c){
        this.control=c;
        this.Title=new Text("Ajouter : ");
        this.Title.setFont(Font.font("Arial",30));
        this.Title.setTranslateX(20);
        this.Title.setTranslateY(20);

        this.nameText=new Text("NOM");
        this.nameText.setFont(Font.font("Arial",20));
        this.nameTextField=new TextField();
        this.nameTextField.setPromptText("Nom");
        

        this.clearName=new Button("X");
        this.clearName.setFont(Font.font("Arial",8));
        this.clearName.setStyle("-fx-background-radius: 25px;");
        this.clearName.setTranslateX(50);
        this.clearName.setOnAction(this.control);
        StackPane namePane=new StackPane();
        namePane.setStyle("-fx-background-radius: 25px; -fx-border-radius: 25px; -fx-background-color: white;");
        namePane.getChildren().addAll(this.nameTextField,this.clearName);

        this.firstNameText=new Text("Prénom");
        this.firstNameText.setFont(Font.font("Arial", 20));
        this.firstNameText.setTranslateY(20);

        this.firstNameTextField=new TextField();
        this.firstNameTextField.setPromptText("Prénom");

        this.clearFirstName=new Button("X");
        this.clearFirstName.setFont(Font.font("Arial",8));
        this.clearFirstName.setStyle("-fx-background-radius: 25px;");
        this.clearFirstName.setTranslateX(50);
        this.clearFirstName.setOnAction(this.control);

        StackPane firstNamePane=new StackPane();
        firstNamePane.getChildren().addAll(this.firstNameTextField,this.clearFirstName);
        firstNamePane.setStyle("-fx-background-radius: 25px; -fx-border-radius: 25px; -fx-background-color: white;");
        firstNamePane.setTranslateY(20);

        this.passTextField=new PasswordField();
        this.passTextField.setPromptText("Mot de passe");
        this.passTextField.setFont(Font.font("Arial", 16));

        this.passText=new Text("Mot de passe");
        this.passText.setFont(Font.font("Arial",20));
        this.passText.setTranslateY(50);
        this.clearPass=new Button("X");
        this.clearPass.setFont(Font.font("Arial",8));
        this.clearPass.setStyle("-fx-background-radius: 25px;");
        this.clearPass.setTranslateX(50);
        this.clearPass.setOnAction(this.control);

        StackPane passPane=new StackPane();
        passPane.getChildren().addAll(this.passTextField,this.clearPass);
        passPane.setStyle("-fx-background-radius: 25px; -fx-border-radius: 25px; -fx-background-color: white");
        passPane.setTranslateY(50);

        this.leftBox=new VBox();
        this.leftBox.getChildren().addAll(this.nameText,namePane,this.firstNameText,firstNamePane,this.passText,passPane);
        this.leftBox.setTranslateX(50);
        this.leftBox.setTranslateY(50);
        this.leftBox.setMaxSize(150, 200);
        this.centerBox=new VBox();
        this.centerBox.setMaxSize(150, 200);
        
        this.dateText=new Text("Date de naissance");
        this.dateText.setFont(Font.font("Arial", 20));
        this.dateText.setTranslateY(-80);
        this.dateText.setTranslateX(-150);
        this.dateTextField=new TextField();
        this.dateTextField.setPromptText("Date de naissance");
        this.dateClear=new Button("x");
        this.dateClear.setFont(Font.font("Arial",8));
        this.dateClear.setStyle("-fx-background-radius: 25px;");
        this.dateClear.setTranslateX(60);
        this.dateClear.setOnAction(this.control);
        StackPane datePane = new StackPane();
        datePane.setTranslateY(-80);
        datePane.setTranslateX(-150);
        datePane.getChildren().addAll(this.dateTextField,this.dateClear);
        datePane.setStyle("-fx-background-radius: 25px; -fx-border-radius: 25px; -fx-background-color: white");

        this.phoneText=new Text("Téléphone");
        this.phoneText.setFont(Font.font("Arial", 20));
        this.phoneText.setTranslateY(-70);
        this.phoneText.setTranslateX(-150);
        this.phoneTextField=new TextField();
        this.phoneTextField.setPromptText("Numéro de téléphone");
        this.phoneClear=new Button("x");
        this.phoneClear.setFont(Font.font("Arial",8));
        this.phoneClear.setStyle("-fx-background-radius: 25px;");
        this.phoneClear.setTranslateX(60);
        this.phoneClear.setOnAction(this.control);
        StackPane phonePane = new StackPane();
        phonePane.setTranslateY(-70);
        phonePane.setTranslateX(-150);
        phonePane.getChildren().addAll(this.phoneTextField,this.phoneClear);
        phonePane.setStyle("-fx-background-radius: 25px; -fx-border-radius: 25px; -fx-background-color: white");

        this.addrText=new Text("Adresse");
        this.addrText.setFont(Font.font("Arial", 20));
        this.addrText.setTranslateY(-60);
        this.addrText.setTranslateX(-150);
        this.addrTextField=new TextField();
        this.addrTextField.setPromptText("Adresse");
        this.addrClear=new Button("x");
        this.addrClear.setFont(Font.font("Arial",8));
        this.addrClear.setStyle("-fx-background-radius: 25px;");
        this.addrClear.setTranslateX(60);
        this.addrClear.setOnAction(this.control);
        StackPane addrPane = new StackPane();
        addrPane.setTranslateY(-60);
        addrPane.setTranslateX(-150);
        addrPane.getChildren().addAll(this.addrTextField,this.addrClear);
        addrPane.setStyle("-fx-background-radius: 25px; -fx-border-radius: 25px; -fx-background-color: white");

        this.mailText=new Text("Mail");
        this.mailText.setFont(Font.font("Arial", 20));
        this.mailText.setTranslateY(-50);
        this.mailText.setTranslateX(-150);
        this.mailTextField=new TextField();
        this.mailTextField.setPromptText("Mail");
        this.mailClear=new Button("x");
        this.mailClear.setFont(Font.font("Arial",8));
        this.mailClear.setStyle("-fx-background-radius: 25px;");
        this.mailClear.setTranslateX(60);
        this.mailClear.setOnAction(this.control);
        StackPane mailPane = new StackPane();
        mailPane.setTranslateY(-50);
        mailPane.setTranslateX(-150);
        mailPane.getChildren().addAll(this.mailTextField,this.mailClear);
        mailPane.setStyle("-fx-background-radius: 25px; -fx-border-radius: 25px; -fx-background-color: white");
        
        this.centerBox.getChildren().addAll(this.dateText,datePane,this.phoneText,phonePane,this.addrText,addrPane,this.mailText,mailPane);
        this.centerBox.setTranslateX(150);
        this.centerBox.setTranslateY(40);

        this.saveButton=new Button("Sauvegardez");
        this.saveButton.setFont(Font.font("Arial",20));
        this.saveButton.setStyle("-fx-background-radius: 25px; -fx-border-radius: 25px; -fx-background-color: #5fc785;");
        this.saveButton.setTranslateY(-50);
        this.saveButton.setTranslateX(175);
        this.saveButton.setOnAction(this.control);
        

        this.setTop(this.Title);
        this.setLeft(this.leftBox);
        this.setCenter(this.centerBox);

        this.setBottom(this.saveButton);

        this.setStyle("-fx-background-color: #f7f4f4;");
        this.setMinSize(500, 400);

    }


    public Button getSaveButton(){
        return this.saveButton;
    }

    public Button getNameClear(){
        return this.clearName;
    }

    public Button getFirstNameClear(){
        return this.clearFirstName;
    }

    

    public Button getPassClear(){
        return this.clearPass;
    }

    public Button getDateClear(){
        return this.dateClear;
    }

    public Button getPhoneClear(){
        return this.phoneClear;
    }

    public Button getAddrClear(){
        return this.addrClear;
    }

    public Button getMailClear(){
        return this.mailClear;
    }


    public void clearName(){
        this.nameTextField.clear();
    }

    public void clearFirstName(){
        this.firstNameTextField.clear();
    }

    public void clearPass(){
        this.passTextField.clear();
    }

    public void clearDate(){
        this.dateTextField.clear();
    }

    public void clearPhone(){
        this.phoneTextField.clear();
    }

    public void clearAddr(){
        this.addrTextField.clear();
    }

    public void clearMail(){
        this.mailTextField.clear();
    }

    public String getName(){
        return this.nameTextField.getText();   
    }

    public String getFirstName(){
        return this.firstNameTextField.getText();
    }

    public String getPassWord(){
        return this.passTextField.getText();
    }

    public String getDateNaissance(){
        return this.dateTextField.getText();
    }

    public String getTel(){
        return this.phoneTextField.getText();
    }

    public String getAdresse(){
        return this.mailTextField.getText();
    }

    public String getMail(){
        return this.mailTextField.getText();
    }
}