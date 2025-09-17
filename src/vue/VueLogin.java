package vue;

import application.App;
import control.Controller;
import persistence.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class VueLogin extends BorderPane {
    private Controller control;
    private User user;
    private App app;

    private ImageView logoMedisync;
    private TextField usernameField;
    private Button clearUser;
    private StackPane userStack;
    private PasswordField passwordField;
    private Button clearPass;
    private StackPane passStack;
    private Button loginButton;
    private VBox formBox;
    private ImageView logoLVMH ;
    private VBox logo;
    private Label partenaire;
    private VBox partenaireBox;
    private VBox content;
    private BorderPane root ;
    
    
    // changer les extends pour juste avoit à switch la scene dans l'application
    public VueLogin(Controller c, App a) {
        this.control=c;
        this.user = null;
        this.app = a;
        /*################"LOGO MEDISYNC#######################" */
        this.logoMedisync = new ImageView(new Image("file:../Image/medisync_logo.png"));
        this.logoMedisync.setPreserveRatio(true);
        this.logoMedisync.setFitWidth(400);
        this.logoMedisync.setTranslateX(750);
        

        /*###########################TEXTFIELD IDENTIFIANT###################*/
        this.usernameField = new TextField();
        this.usernameField.setPromptText("Identifiant");
        this.usernameField.setFont(Font.font("Arial", 36));
        this.usernameField.setStyle("-fx-background-radius: 10;" + "-fx-border-radius: 10;"+"-fx-padding: 10 5 10 5;");
        // bouton avec la petite croix
        this.clearUser = new Button("✖");
        this.clearUser.setStyle("-fx-background-radius: 50%;" + "-fx-border-radius: 50;");
        this.clearUser.setFocusTraversable(false);
        this.clearUser.setOnAction(this.control);

        this.userStack = new StackPane();
        this.userStack.getChildren().addAll(this.usernameField, this.clearUser);
        this.userStack.setTranslateY(-75);

        // Positionner le bouton à droite du champ de texte
        StackPane.setAlignment(this.clearUser, Pos.CENTER_RIGHT);
        StackPane.setMargin(this.clearUser, new Insets(0, 10, 0, 0));

        /*#####################TEXTFIELD MOT DE PASSE#################### */
        this.passwordField = new PasswordField();
        this.passwordField.setPromptText("Mot de Passe");
        this.passwordField.setFont(Font.font("Arial", 36));
        this.passwordField.setStyle("-fx-background-radius: 10;" + "-fx-border-radius: 10;"+"-fx-padding: 10 5 10 5;");
        // bouton avec la petite croix
        this.clearPass = new Button("✖");
        this.clearPass.setStyle("-fx-background-radius: 50%;" + "-fx-border-radius: 50;");
        this.clearPass.setOnAction(this.control);


        this.passStack = new StackPane();
        this.passStack.getChildren().addAll(this.passwordField, this.clearPass);
        this.passStack.setTranslateY(-20);
        StackPane.setAlignment(this.clearPass, Pos.CENTER_RIGHT);
        StackPane.setMargin(this.clearPass, new Insets(0, 10, 0, 0));

        /*#####################"BOUTON DE CONNEXION############################" */
        this.loginButton = new Button("Connexion");
        this.loginButton.setFont(Font.font("Arial", 40));
        this.loginButton.setStyle("-fx-background-color: #001E61; -fx-text-fill: white;"+ "-fx-background-radius: 15;" + "-fx-border-radius: 15;");
        this.loginButton.setPrefWidth(300);
        this.loginButton.setTranslateY(20);
        this.loginButton.setPadding(new Insets(10, 20, 10, 20));
        this.loginButton.setOnAction(this.control);

        /*#######################"LA BOX BLEU############################" */
        this.formBox = new VBox(30, this.userStack, this.passStack, this.loginButton);
        this.formBox.setPadding(new Insets(50));
        this.formBox.setAlignment(Pos.CENTER);
        this.formBox.setMaxWidth(400);
        this.formBox.setMaxHeight(500);
        this.formBox.setStyle("-fx-background-color: #e7ecec; -fx-background-radius: 20; -fx-border-radius: 20; -fx-border-color: black;");
        this.formBox.setTranslateX(200);
        this.formBox.setTranslateY(-50);

        /*#######################LOGO LVMH##########################"" */
        this.logoLVMH= new ImageView(new Image("file:../Image/lvmh_logo.png"));
        this.logoLVMH.setPreserveRatio(true);
        this.logoLVMH.setFitWidth(150);
        this.logoLVMH.setFitHeight(150);
        this.logoLVMH.setTranslateX(-100);


        this.partenaire = new Label("PARTENAIRE PREMIUM");
        this.partenaire.setFont(Font.font("arial", 32));
        this.partenaire.setStyle("-fx-font-weight: bold;");
        this.partenaireBox = new VBox(this.partenaire,this.logoLVMH);
        this.partenaireBox.setAlignment(Pos.CENTER_RIGHT);
        this.partenaireBox.setSpacing(5);
        this.partenaireBox.setTranslateX(-200);
        this.partenaireBox.setTranslateY(200);
        

        /*#######################LA PAGE DE L'APPLICATION###########################""" */
        this.setMaxSize(1920,1000);
        this.setMinSize(1920,1000);
        this.setTop(this.logoMedisync);
        this.setCenter(this.formBox);
        this.setRight(this.partenaireBox);
        this.setStyle("-fx-background-color: #f7f4f4");
    }

    public Button getUserButton(){
        return this.clearUser;
    }

    public void clearUser(){
        this.usernameField.clear();
    }

    public Button getPassButton(){
        return this.clearPass;
    }

    public void clearPass(){
        this.passwordField.clear();
    }

    public Button getLoginButton(){
        return this.loginButton;
    }


    public String getLogin(){
        return this.usernameField.getText();
    }

    public String getPassword(){
        return this.passwordField.getText();
    }

    public User getUser(){
        return this.user;
    }
}