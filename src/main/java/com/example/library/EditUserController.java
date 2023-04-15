package com.example.library;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class EditUserController {

    @FXML
    private Button closeBtn;

    @FXML
    private Button editBtn;

    @FXML
    private TextField errorText;

    @FXML
    private TextField imieText;

    @FXML
    private TextField nazwiskoText;

    @FXML
    private TextField telText;

    @FXML
    void addClick(ActionEvent event) {

    }

    @FXML
    void close(ActionEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    void editClick(ActionEvent event) {

    }

}
