package com.example.demo1;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Animations.Shake;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class HelloController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button AuthSignInButton;

    @FXML
    private TextField loginField;

    @FXML
    private Button LoginSignUpButton;

    @FXML
    private PasswordField passwordField;

    @FXML
    void initialize() {
        AuthSignInButton.setOnAction(event -> {
            String loginText = "" + loginField.getText().trim();
            String loginPassword = "" + passwordField.getText().trim();

            if (!loginText.equals("") && !loginPassword.equals("")){
                loginUser(loginText,loginPassword);
            }else
                System.out.println("Login and password is empty");
        });
       LoginSignUpButton.setOnAction(event -> {
            openNewScene("hello-view.fxml");
       });
    }

    private void loginUser(String loginText, String loginPassword)  {
        DataBaseHandler dbHandler = new DataBaseHandler();
        User user = new User();
        user.setUserName(loginText);
        user.setPassword(loginPassword);
        ResultSet result = dbHandler.getUser(user);

        int counter = 0;

        try {
            while (result.next()){
                counter++;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        if (counter >= 1){
            openNewScene("GreetWindow.fxml");

        }else {
            Shake userLoginAnim = new Shake (loginField);
            Shake userPasswordAnim = new Shake (passwordField);
            userLoginAnim.playAnimation();
            userPasswordAnim.playAnimation();

        }
    }
    public void openNewScene (String window) {
            LoginSignUpButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(window));
            try {
                loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage ();
            stage.setScene(new Scene(root));
            stage.showAndWait();
    }
}
