package com.example.library.controller;

import com.example.library.repository.RepozytoriumUzytkownikow;
import com.example.library.model.DaneUzytkownika;
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

public class KontrolerRejestracji {

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
    private RepozytoriumUzytkownikow repo = new RepozytoriumUzytkownikow();
        @FXML
    void zarejestruj(ActionEvent event) {

        String login = nameText.getText();
        String password = passwordText.getText();
        String code = codeText.getText();
        String passwordAgain = passwordAgainText.getText();
        if (repo.zarejestruj(login)) {
            notification.setText("Istnieje juz taki użytkownik!");
        }
        else if(!code.equals("123") || !password.equals(passwordAgain)) {
            notification.setText("Błedne dane!");
        }
        else {
            repo.dodaj(new DaneUzytkownika(null,login,password));
            notification.setText("Pomyślnie zarejestrowano");
        }
        }



    @FXML
    void clickOn(ActionEvent event) {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("logowanie.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Logowanie");
            stage.setScene(new Scene(fxmlLoader.load(), 800, 500));
            stage.show();
            ((Node)(event.getSource())).getScene().getWindow().hide();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

}
