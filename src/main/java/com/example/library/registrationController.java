package com.example.library;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class registrationController {

    @FXML
    private Button btnRegister;

    @FXML
    private Hyperlink btnlink;

    @FXML
    private TextField codeText;
    @FXML
    private TextField notification;

    @FXML
    private TextField nameText;

    @FXML
    private PasswordField passwordAgainText;

    @FXML
    private PasswordField passwordText;
    private LogowaniaRepository repo = new LogowaniaRepository();
        @FXML
    void zarejestruj(ActionEvent event) {

        String login = nameText.getText();
        String password = passwordText.getText();
        String code = codeText.getText();
        String passwordAgain = passwordAgainText.getText();
        if (repo.rejestracja(login)) {
            notification.setText("Istnieje juz taki uzytkownik");
        }
        else if(!code.equals("123") || !password.equals(passwordAgain)) {
            notification.setText("bledne dane");
        }
        else {
            repo.add(new Logowania(null,login,password));
            notification.setText("zarejestroeany");
        }
        }



    @FXML
    void clickOn(ActionEvent event) {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("logowanie.fxml"));
            Stage stage = new Stage();
            stage.setTitle("My New Stage Title");
            stage.setScene(new Scene(fxmlLoader.load(), 800, 500));
            stage.show();
            // Hide this current window (if this is what you want)
            ((Node)(event.getSource())).getScene().getWindow().hide();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

}