package com.example.library;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class loginController {

    @FXML
    private Button btnLogin;

    @FXML
    private Hyperlink linkbtn;
    @FXML
    private TextField errorLabel;
    @FXML
    private TextField loginText;

    @FXML
    private PasswordField passwordText;

    private LogowaniaRepository repo = new LogowaniaRepository();

    @FXML
    void clickOn(ActionEvent event) {

        try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("rejestracja.fxml"));
                Stage stage = new Stage();
                stage.setTitle("My New Stage Title");
                stage.setScene(new Scene(fxmlLoader.load(), 800, 500));
                stage.show();
                // Hide this current window (if this is what you want)
                ((Node) (event.getSource())).getScene().getWindow().hide();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void openMenu(ActionEvent event) {
        try {
            String login = loginText.getText();
            String haslo = passwordText.getText();
            if (repo.logowanie(login, haslo)) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("menu.fxml"));
                Stage stage = new Stage();
                stage.setTitle("My New Stage Title");
                stage.setScene(new Scene(fxmlLoader.load(), 600, 400));
                stage.show();
                // Hide this current window (if this is what you want)
                ((Node) (event.getSource())).getScene().getWindow().hide();
            }
         else {
             errorLabel.setText("Nieprawidłowe dane");
        }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
