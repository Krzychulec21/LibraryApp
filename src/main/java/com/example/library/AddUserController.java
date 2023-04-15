package com.example.library;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;

public class AddUserController {
    @FXML
    private Button addBtn;
    @FXML
    private Button closeBtn;
    @FXML
    private TextField imieText;

    @FXML
    private TextField nazwiskoText;
    @FXML
    private TextField errorText;

    @FXML
    private TextField telText;


    private OsobaRepository repo = new OsobaRepository();

        @FXML
        void addClick(ActionEvent event) {
            String imie = imieText.getText();
            String nazwisko = nazwiskoText.getText();
            String telefon = telText.getText();
            if (imie.equals("") || nazwisko.equals("") || telefon.equals("")) {
                errorText.setText("Pole nie moze byc puste");
                errorText.setVisible(true);
            } else {
                repo.add(new Osoba(null, imie, nazwisko, telefon));
                errorText.setText("dodano nowego uzytwkonika");
                errorText.setStyle("");
                errorText.setVisible(true);
            }

        }

    @FXML
    void close(ActionEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();

    }

}


