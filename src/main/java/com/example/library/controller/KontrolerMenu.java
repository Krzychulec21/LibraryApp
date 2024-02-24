package com.example.library.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class KontrolerMenu {

    @FXML
    private Button btnBook;

    @FXML
    private Button btnUser;
    @FXML
    private Button wypoBtn;

    @FXML
    void clickBook(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("bookMain.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Książki");
            stage.setScene(new Scene(fxmlLoader.load(), 1282, 757));
            stage.show();
            // Hide this current window (if this is what you want)
            ((Node)(event.getSource())).getScene().getWindow().hide();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }



    @FXML
    void clickUser(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("userMain.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Osoby");
            stage.setScene(new Scene(fxmlLoader.load(), 960, 600));
            stage.show();
            // Hide this current window (if this is what you want)
            ((Node)(event.getSource())).getScene().getWindow().hide();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
    @FXML
    void clickWypo(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("wypozyczenia.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Wypożyczenia");
            stage.setScene(new Scene(fxmlLoader.load(), 1287, 791));
            stage.show();
            // Hide this current window (if this is what you want)
            ((Node)(event.getSource())).getScene().getWindow().hide();
        }
        catch (IOException e) {
            e.printStackTrace();
        }


    }

}
