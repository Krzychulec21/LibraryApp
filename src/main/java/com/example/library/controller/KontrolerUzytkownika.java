package com.example.library.controller;


import com.example.library.util.PolaczenieBazowe;
import com.example.library.model.Osoba;
import com.example.library.model.Wypozyczenia;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import org.hibernate.Transaction;

import org.hibernate.query.Query;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class KontrolerUzytkownika {
    @FXML
    private TableView<Osoba> tableView;
    @FXML
    private Button anulujBtn;
    @FXML
    private TextField infoField;

    @FXML
    private TextField nameField;
    @FXML
    private Text errorText;
    @FXML
    private Button anulujDodawanieBtn;
    @FXML
    private Button zatwierdzDodawanieBtn;
    @FXML
    private TextField surnameField;
    @FXML
    private TextField telField;

    @FXML
    private Button zatwierdzBtn;


    @FXML
    private TableColumn<Osoba, Integer> idCol;

    @FXML
    private TableColumn<Osoba, String> imieCol;

    @FXML
    private TableColumn<Osoba, String> nazwiskoCol;

    @FXML
    private TableColumn<Osoba, String> telCol;

    @FXML
    private TableColumn<Osoba, Void> editColumn;


    @FXML
    private Button btnAdd;


    @FXML
    private Button back;
    @FXML
    private Button anulujSzukanieBtn;
    @FXML
    private Button zatwierdzSzukanieBtn;


    @FXML
    private Button btnList;

    @FXML
    private Button btnRemove;

    @FXML
    private Button btnSearch;

    private Osoba osobaEdit;


    @FXML
    void clickAdd(ActionEvent event) throws IOException {
        errorText.setVisible(false);
        errorText.setText("");
        infoField.setText("Dodawanie");
        nameField.setVisible(true);
        telField.setVisible(true);
        surnameField.setVisible(true);
        zatwierdzDodawanieBtn.setVisible(true);
        anulujDodawanieBtn.setVisible(true);
        infoField.setVisible(true);
        anulujSzukanieBtn.setVisible(false);
        zatwierdzSzukanieBtn.setVisible(false);
        anulujBtn.setVisible(false);
        zatwierdzBtn.setVisible(false);
        nameField.clear();
        surnameField.clear();
        telField.clear();

    }

    @FXML
    void anuluj(ActionEvent event) {
        errorText.setVisible(false);
        errorText.setText("");
        nameField.setVisible(false);
        telField.setVisible(false);
        surnameField.setVisible(false);
        anulujBtn.setVisible(false);
        zatwierdzBtn.setVisible(false);
        infoField.setVisible(false);
    }

    @FXML
    void zatwierdz(ActionEvent event) {
        osobaEdit.setImie(nameField.getText());
        osobaEdit.setNazwisko(surnameField.getText());
        osobaEdit.setTelefon(telField.getText());
        Osoba klonOs = null;
        if (nameField.getText().matches("^[a-zA-ZąćęłńóśźżĄĆĘŁŃÓŚŹŻ -]+$") && !nameField.getText().isEmpty() && !nameField.getText().isBlank() &&
                surnameField.getText().matches("^[a-zA-ZąćęłńóśźżĄĆĘŁŃÓŚŹŻ -]+$") && !surnameField.getText().isEmpty() && !surnameField.getText().isBlank() && !telField.getText().isBlank() &&
                !telField.getText().isEmpty() && telField.getText().matches("[0-9]+") && telField.getText().length() == 9) {
            Session session = PolaczenieBazowe.otworzSesje();
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                Query query = session.createQuery("FROM Osoba WHERE imie = :imie AND nazwisko = :nazwisko AND telefon = :telefon");
                query.setParameter("imie", nameField.getText());
                query.setParameter("nazwisko", surnameField.getText());
                query.setParameter("telefon", telField.getText());
                Osoba os = (Osoba) query.uniqueResult();
                klonOs = os;
                if (os == null)
                    session.update(osobaEdit);
                else {
                    errorText.setText("Istnieje juz taka osoba");
                    errorText.setVisible(true);
                }
                transaction.commit();
            } catch (HibernateException e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                e.printStackTrace();
            } finally {
                session.close();
            }
            if (klonOs==null) {
                loadDataIntoTableView();
                nameField.setVisible(false);
                telField.setVisible(false);
                surnameField.setVisible(false);
                anulujBtn.setVisible(false);
                zatwierdzBtn.setVisible(false);
                infoField.setVisible(false);
            }}
        else {
                errorText.setText("Imie i nazwisko moze zawierac tylko litery a numer cyfry (9) i nie mogą byc puste!");
                errorText.setVisible(true);

            }
    }


    @FXML
    void clickList(ActionEvent event) {
        errorText.setVisible(false);
        errorText.setText("");
        loadDataIntoTableView();

    }


    @FXML
    void clickSearch(ActionEvent event) {
        errorText.setText("");
        errorText.setVisible(false);
        nameField.setVisible(true);
        telField.setVisible(true);
        surnameField.setVisible(true);
        anulujSzukanieBtn.setVisible(true);
        zatwierdzSzukanieBtn.setVisible(true);
        infoField.setVisible(true);
        anulujBtn.setVisible(false);
        zatwierdzBtn.setVisible(false);
        zatwierdzDodawanieBtn.setVisible(false);
        anulujDodawanieBtn.setVisible(false);
        nameField.setText("");
        surnameField.setText("");
        telField.setText("");
        infoField.setText("Szukaj użytkownika");
    }

    @FXML
    void anulujSzukanie(ActionEvent event) {
        errorText.setVisible(false);
        errorText.setText("");
        nameField.setVisible(false);
        telField.setVisible(false);
        surnameField.setVisible(false);
        anulujSzukanieBtn.setVisible(false);
        zatwierdzSzukanieBtn.setVisible(false);
        infoField.setVisible(false);
    }

    @FXML
    void zatwierdzSzukanie(ActionEvent event) {
        String imie = nameField.getText();
        String nazwisko = surnameField.getText();
        String tel = telField.getText();
       if (imie.matches("^[a-zA-ZąćęłńóśźżĄĆĘŁŃÓŚŹŻ -]+$") && !imie.isEmpty()
               || nazwisko.matches("^[a-zA-ZąćęłńóśźżĄĆĘŁŃÓŚŹŻ -]+$") && !nazwisko.isEmpty()|| tel.matches("[0-9]+") && !tel.isEmpty()) {
           tableView.getItems().clear();
           Session session = PolaczenieBazowe.otworzSesje();
           String hql = "FROM Osoba where imie like '%" + imie + "%' and nazwisko like '%" + nazwisko + "%' and telefon like '%" + tel + "%'";
           Query query = session.createQuery(hql);
           List<Osoba> osoby = query.list();
           session.close();
           addButtonToTable();  // dodaj kolumnę z przyciskami do tabeli
           idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
           imieCol.setCellValueFactory(new PropertyValueFactory<>("imie"));
           nazwiskoCol.setCellValueFactory(new PropertyValueFactory<>("nazwisko"));
           telCol.setCellValueFactory(new PropertyValueFactory<>("telefon"));
           tableView.getColumns().remove(editColumn);
           tableView.getColumns().add(editColumn);
           tableView.setItems(FXCollections.observableList(osoby));
           imieCol.setCellFactory(TextFieldTableCell.forTableColumn());
           errorText.setVisible(false);
       }
       else {
           errorText.setText("Imie i nazwisko moze zawierac tylko litery a numer telefonu cyfry");
           errorText.setVisible(true);
       }
    }

    @FXML
    void zatwierdzDodawanie(ActionEvent event) {
        Osoba klonOs = null;
        if (nameField.getText().matches("^[a-zA-ZąćęłńóśźżĄĆĘŁŃÓŚŹŻ -]+$") && !nameField.getText().isEmpty() && !nameField.getText().isBlank() &&
                surnameField.getText().matches("^[a-zA-ZąćęłńóśźżĄĆĘŁŃÓŚŹŻ -]+$") && !surnameField.getText().isEmpty() && !surnameField.getText().isBlank() && !telField.getText().isBlank() &&
        !telField.getText().isEmpty() && telField.getText().matches("[0-9]+") && telField.getText().length() == 9) {
            Osoba osoba = new Osoba(null, nameField.getText(), surnameField.getText(), telField.getText());

            Session session = PolaczenieBazowe.otworzSesje();
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                Query query = session.createQuery("FROM Osoba WHERE imie = :imie AND nazwisko = :nazwisko AND telefon = :telefon");
                query.setParameter("imie", nameField.getText());
                query.setParameter("nazwisko", surnameField.getText());
                query.setParameter("telefon", telField.getText());
                Osoba os = (Osoba) query.uniqueResult();
                klonOs = os;
                if (os == null)
                    session.persist(osoba);
                else {
                    errorText.setText("Istnieje juz taka osoba");
                    errorText.setVisible(true);
                }
                transaction.commit();
            } catch (HibernateException e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                e.printStackTrace();
            } finally {
                session.close();
            }
            if (klonOs == null) {
                loadDataIntoTableView();
                nameField.setVisible(false);
                telField.setVisible(false);
                surnameField.setVisible(false);
                zatwierdzDodawanieBtn.setVisible(false);
                anulujDodawanieBtn.setVisible(false);
                infoField.setVisible(false);
                nameField.clear();
                surnameField.clear();
                telField.clear();
                errorText.setVisible(false);
            }
        }
        else {
            errorText.setText("Imie i nazwisko moze zawierac tylko litery a numer cyfry (9) i nie mogą byc puste!");
            errorText.setVisible(true);

        }

    }
    @FXML
    void anulujDodawanie(ActionEvent event){
        errorText.setVisible(false);
        errorText.setText("");
        nameField.setVisible(false);
        telField.setVisible(false);
        surnameField.setVisible(false);
        zatwierdzDodawanieBtn.setVisible(false);
        anulujDodawanieBtn.setVisible(false);
        infoField.setVisible(false);


    }

    @FXML
    void clickBack(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("menu.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Menu");
            stage.setScene(new Scene(fxmlLoader.load(), 600, 400));
            stage.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void loadDataIntoTableView() {

        tableView.getItems().clear();
        Session session = PolaczenieBazowe.otworzSesje();
        String hql = "FROM Osoba ";
        Query query = session.createQuery(hql);
        List<Osoba> osoby = query.list();
        session.close();
        addButtonToTable();  // dodaj kolumnę z przyciskami do tabeli
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        imieCol.setCellValueFactory(new PropertyValueFactory<>("imie"));
        nazwiskoCol.setCellValueFactory(new PropertyValueFactory<>("nazwisko"));
        telCol.setCellValueFactory(new PropertyValueFactory<>("telefon"));
        tableView.getColumns().remove(editColumn);
        tableView.getColumns().add(editColumn);
        tableView.setItems(FXCollections.observableList(osoby));
        nameField.setVisible(false);
        telField.setVisible(false);
        surnameField.setVisible(false);
        anulujSzukanieBtn.setVisible(false);
        zatwierdzSzukanieBtn.setVisible(false);
        infoField.setVisible(false);
        anulujBtn.setVisible(false);
        zatwierdzBtn.setVisible(false);
        zatwierdzDodawanieBtn.setVisible(false);
        anulujDodawanieBtn.setVisible(false);
        errorText.setVisible(false);
        errorText.setText("");

    }

    private void addButtonToTable() {

        Callback<TableColumn<Osoba, Void>, TableCell<Osoba, Void>> cellFactory = new Callback<TableColumn<Osoba, Void>, TableCell<Osoba, Void>>() {
            @Override
            public TableCell<Osoba, Void> call(final TableColumn<Osoba, Void> param) {
                final TableCell<Osoba, Void> cell = new TableCell<Osoba, Void>() {

                    private final Button editColumnBtn = new Button("Edytuj");
                    private final Button btnDelete = new Button("Usuń");

                    {
                        editColumnBtn.setStyle("-fx-background-color: transparent; -fx-border-color: linear-gradient(to right, #d9b38c44, #ffffff44);" +
                                "-fx-border-radius: 10px; -fx-border-width: 2px 2px 2px 2px ");
                        btnDelete.setStyle("-fx-background-color: transparent; -fx-border-color: linear-gradient(to right, #d9b38c44, #ffffff44);" +
                                "-fx-border-radius: 10px; -fx-border-width: 2px 2px 2px 2px");

                        editColumnBtn.setOnAction((ActionEvent event) -> {
                            Osoba osoba = getTableView().getItems().get(getIndex());
                            osobaEdit = osoba;

                            errorText.setVisible(false);
                            errorText.setText("");
                            nameField.setText(osoba.getImie());
                            surnameField.setText(osoba.getNazwisko());
                            telField.setText(osoba.getTelefon());
                            nameField.setVisible(true);
                            telField.setVisible(true);
                            surnameField.setVisible(true);
                            anulujBtn.setVisible(true);
                            zatwierdzBtn.setVisible(true);
                            infoField.setVisible(true);
                            zatwierdzDodawanieBtn.setVisible(false);
                            anulujDodawanieBtn.setVisible(false);
                            infoField.setText("Edycja uzytwkonika");
                            anulujSzukanieBtn.setVisible(false);
                            zatwierdzSzukanieBtn.setVisible(false);


                        });
                        btnDelete.setOnAction((ActionEvent event) -> {
                            List<Wypozyczenia> stanOs= null;
                            nameField.setVisible(false);
                            telField.setVisible(false);
                            surnameField.setVisible(false);
                            anulujSzukanieBtn.setVisible(false);
                            zatwierdzSzukanieBtn.setVisible(false);
                            infoField.setVisible(false);
                            anulujBtn.setVisible(false);
                            zatwierdzBtn.setVisible(false);
                            zatwierdzDodawanieBtn.setVisible(false);
                            anulujDodawanieBtn.setVisible(false);
                            Osoba osoba = getTableView().getItems().get(getIndex());
                            //dodatkowe okno allert
                            Session session = PolaczenieBazowe.otworzSesje();
                            Transaction transaction = null;
                            try {
                                transaction = session.beginTransaction();
                                Query query = session.createQuery("FROM Wypozyczenia where osoba= :osoba and dataOddania is null ");
                                query.setParameter("osoba", osoba);
                                List<Wypozyczenia> wypo = (List<Wypozyczenia>) query.list();
                                stanOs=wypo;
                                transaction.commit();
                            } catch (HibernateException e) {
                                if (transaction != null) {
                                    transaction.rollback();
                                }
                                e.printStackTrace();
                            } finally {
                                session.close();
                            }
                            //
                            if (stanOs.isEmpty()) {
                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                alert.setTitle("Usuwanie uzytkownika");
                                alert.setHeaderText("Czy na pewno chcesz usunąć użytkownika " + osoba.getImie() + " " + osoba.getNazwisko() + " ?\nSpowoduje to również usunięcie danych na temat wypożyczonych przez niego książek w bazie ");
                                Optional<ButtonType> result = alert.showAndWait();
                                if (result.get() == ButtonType.OK) {
                                    // ... user chose OK
                                    Session session2 = PolaczenieBazowe.otworzSesje();
                                    Transaction transaction2 = null;
                                    try {
                                        transaction2 = session2.beginTransaction();
                                        Query query = session2.createQuery("FROM Wypozyczenia where osoba= :osoba");
                                        query.setParameter("osoba",osoba);
                                        List<Wypozyczenia> wypo = query.list();
                                        for(Wypozyczenia w : wypo) {
                                            session2.delete(w);
                                        }
                                        transaction2.commit();
                                    } catch (HibernateException e) {
                                        if (transaction2!= null) {
                                            transaction2.rollback();
                                        }
                                        e.printStackTrace();
                                    } finally {
                                        session2.close();
                                    }
                                    //
                                    Session session1 = PolaczenieBazowe.otworzSesje();
                                    Transaction transaction1 = null;
                                    try {
                                        transaction1 = session1.beginTransaction();
                                        session1.delete(osoba);
                                        transaction1.commit();
                                    } catch (HibernateException e) {
                                        if (transaction1 != null) {
                                            transaction1.rollback();
                                        }
                                        e.printStackTrace();
                                    } finally {
                                        session1.close();
                                    }

                                    tableView.getItems().remove(osoba);

                                } else {
                                    alert.close();
                                }
                            }
                            else {
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setTitle("Błąd");
                                alert.setHeaderText("Błąd usuwania");
                                alert.setContentText("Nie można usunac tej osoby, ponieważ wypożycza ona obecnie książkę.");
                                alert.showAndWait();
                            }
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            HBox hBox = new HBox(editColumnBtn, btnDelete);
                            hBox.setSpacing(10);
                            hBox.setPadding(new Insets(1));
                            setGraphic(hBox);
                        }
                    }
                };
                return cell;
            }
        };
        editColumn.setCellFactory(cellFactory);
    }


}







