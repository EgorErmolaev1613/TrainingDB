package com.example.demo1;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;


public class SignUpController {
    @FXML
    private RadioButton buttonMan;

    @FXML
    private RadioButton buttonWoman;


    @FXML
    private ToggleGroup MW;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button SignUpButton;

    @FXML
    private TextField SignUpCountry;

    @FXML
    private TextField SignUpLastName;

    @FXML
    private TextField SignUpName;

    @FXML
    private TextField SignUpLogin;

    @FXML
    private PasswordField SignUpPassword;

    @FXML
    void initialize() {

        SignUpButton.setOnAction(event -> {
        signUpNewUser();

        });
    }

    private void signUpNewUser() {
        DataBaseHandler dbHandler = new DataBaseHandler();
        String firstName = SignUpName.getText().toLowerCase();
        String lastName = SignUpLastName.getText().toLowerCase();
        String login = SignUpLogin.getText().toLowerCase();
        String password = SignUpPassword.getText();
        String location = SignUpCountry.getText().toLowerCase();
        String gender = "";
        if(buttonMan.isSelected())
            gender = "Мужской";
        else if (buttonWoman.isSelected())
            gender = "Женский";
        else System.out.println("Выберете один пол");
        User user = new User(firstName,lastName,login,password,location,gender);

        dbHandler.signUpUser(user);
        Stage closeWindow = (Stage) SignUpButton.getScene().getWindow();
        closeWindow.close();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("hello-view.fxml"));
        System.out.println("Регистрация прошла успешно");
        try {
            loader.load();
        }catch (IOException e ) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();

    }

}
