package com.example.library.controller;


import com.example.library.util.PolaczenieBazowe;
import com.example.library.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.hibernate.type.SqlTypes.NULL;

public class KontrolerKsiazek {

    @FXML
    private ComboBox<Autor> autorChoiceBox;
    @FXML
    private TableView<Ksiazka> tableView;
    @FXML
    private TableColumn<Ksiazka, Void> editColumn;
    @FXML
    private TableColumn<Ksiazka, String> statusCol;

    @FXML
    private Button back;
    @FXML
    private Text infoAutor;

    @FXML
    private Text infoGatunek;

    @FXML
    private Text infoOkladka;
    @FXML
    private Button anulujObiektBtn;
    @FXML
    private Button zatwierdzSzukaniePoAutorBtn;
    @FXML
    private Button zatwierdzSzukaniePoIDBtn;
    @FXML
    private Button zatwierdzSzukaniePoTytulBtn;
    @FXML
    private Button dodajObiektGatunek;
    @FXML
    private Button dodajObiektBtn;
    @FXML
    private Text text1;

    @FXML
    private Text text2;
    @FXML
    private Text szukaniePoText;


    @FXML
    private TextField textField1;
    @FXML
    private TextField szukajField;


    @FXML
    private TextField textField2;
    @FXML
    private TextField info;

    @FXML
    private Text infoRok;

    @FXML
    private Text infoTytul;

    @FXML
    private Text infoWydawnictwo;
    @FXML
    private Button anulujDodawanieBtn;
    @FXML
    private Button dodajAutoraBtn;

    @FXML
    private ComboBox<Gatunek> gatunekComboBox;

    @FXML
    private Button btnAdd;


    @FXML
    private Button btnList;



    @FXML
    private Button anulujSzukanieBtn;




    @FXML
    private Button btnSearch;

    @FXML
    private TextField rokField;

    @FXML
    private TextField tytulField;
    @FXML
    private Button addBookBtn;
    @FXML
    private TextField danaAkcjaField;

    @FXML
    private ComboBox<Wydawnictwo> wydawnictwoChoiceBox;

    @FXML
    private TableColumn<Ksiazka, String> autorCol;
    @FXML
    private TableColumn<Ksiazka, Long> idCol;

    @FXML
    private TableColumn<Ksiazka, String> tytulCol;
    @FXML
    private TableColumn<Ksiazka, String> rokCol;
    @FXML
    private TableColumn<Ksiazka, String> okladkaCol;
    @FXML
    private TableColumn<Ksiazka, String> gatunekCol;
    @FXML
    private Button anulujSzukaniePoBtn;
    @FXML
    private Text bladSzukaniaText;

    @FXML
    private Button anulujEditBtn;
    @FXML
    private Button editBookBtn;
    @FXML
    private Button dodajWydawnictwoBtn;

    @FXML
    private TableColumn<Ksiazka, String> wydawnictwoCol;
    @FXML
    private RadioButton cienkaOkladka;
    @FXML
    private Button dodajObiektWydawnictwo;
    @FXML
    private Button dodajObiektGatuenk;
    @FXML
    private Button dodajGatunek;

    @FXML
    private Button szukajPoAutorzeBtn;

    @FXML
    private Button szukajPoIDBtn;

    @FXML
    private Button szukajPoTytlueBtn;

    @FXML
    private Text wyszukajPoText;



    @FXML
    private RadioButton grubaOkladka;
    @FXML
    private ToggleGroup wybor;
    @FXML
    private Text errorText;
    private Ksiazka ksiazkaEdit;

    @FXML
    private Text errorTextAddObjects;



    @FXML
    void anulujEdytowanie(ActionEvent event) {
        infoAutor.setVisible(false);
        infoGatunek.setVisible(false);
        infoOkladka.setVisible(false);
        infoRok.setVisible(false);
        infoTytul.setVisible(false);
        infoWydawnictwo.setVisible(false);
        tytulField.setVisible(false);
        rokField.setVisible(false);
        autorChoiceBox.setVisible(false);
        wydawnictwoChoiceBox.setVisible(false);
        gatunekComboBox.setVisible(false);
        grubaOkladka.setVisible(false);
        cienkaOkladka.setVisible(false);
        addBookBtn.setVisible(false);
        anulujDodawanieBtn.setVisible(false);
        infoTytul.setVisible(false);
        errorText.setVisible(false);
        danaAkcjaField.setVisible(false);
        anulujEditBtn.setVisible(false);
        editBookBtn.setVisible(false);
        dodajAutoraBtn.setVisible(false);


    }


    @FXML
    void edytujKsiazke(ActionEvent event) {
        int obecnyRok = LocalDate.now().getYear();
        RadioButton selected = (RadioButton) wybor.getSelectedToggle();
        if (selected == null || tytulField.getText().isEmpty() && tytulField.getText().isBlank() || !rokField.getText().matches("^[0-9]*$") ||
                autorChoiceBox.getValue() == null || wydawnictwoChoiceBox.getValue() == null || gatunekComboBox.getValue() == null || Integer.parseInt(rokField.getText()) > obecnyRok) {
            errorText.setText("Wszystkie pola muszą byc uzupelnione! Pamiętaj, że rok musi byc poprawny (może zawierac tylko cyfry i nie moze byc weikszy od obecnego roku ");
            errorText.setVisible(true);
        } else {
            String okladka = selected.getText();
            ksiazkaEdit.setRok(rokField.getText());
            ksiazkaEdit.setTytul(tytulField.getText());
            ksiazkaEdit.setAutor(autorChoiceBox.getSelectionModel().getSelectedItem());
            ksiazkaEdit.setOkladka(okladka);
            ksiazkaEdit.setGatunek(gatunekComboBox.getSelectionModel().getSelectedItem());
            ksiazkaEdit.setWydawnictwo(wydawnictwoChoiceBox.getSelectionModel().getSelectedItem());

            Transaction transaction = null;
            try (Session session = PolaczenieBazowe.otworzSesje()) {
                transaction = session.beginTransaction();
                session.update(ksiazkaEdit);
                transaction.commit();
            } catch (Exception e) {
                if (transaction != null && transaction.isActive())
                    transaction.rollback();
            }
            zaladujTabele();
            ukryjEdytowanie();

        }
    }


    @FXML
    void clickAdd(ActionEvent event) {
        danaAkcjaField.setText("Dodawanie");
        tytulField.clear();
        rokField.clear();
        autorChoiceBox.valueProperty().set(null);
        wydawnictwoChoiceBox.valueProperty().set(null);
        gatunekComboBox.valueProperty().set(null);
        grubaOkladka.setSelected(false);
        cienkaOkladka.setSelected(false);
        errorText.setText("");

        ukryjSzukanie();
        ukryjEdytowanie();
        pokazDodawanie();
    }

    @FXML
    void anulujDodawanie(ActionEvent event) {
        infoAutor.setVisible(false);
        infoGatunek.setVisible(false);
        infoOkladka.setVisible(false);
        infoRok.setVisible(false);
        infoTytul.setVisible(false);
        infoWydawnictwo.setVisible(false);
        tytulField.setVisible(false);
        rokField.setVisible(false);
        autorChoiceBox.setVisible(false);
        wydawnictwoChoiceBox.setVisible(false);
        gatunekComboBox.setVisible(false);
        grubaOkladka.setVisible(false);
        cienkaOkladka.setVisible(false);
        addBookBtn.setVisible(false);
        anulujDodawanieBtn.setVisible(false);
        infoTytul.setVisible(false);
        errorText.setVisible(false);
        danaAkcjaField.setVisible(false);
        dodajAutoraBtn.setVisible(false);
    }


    @FXML
    void clickList(ActionEvent event) {
        zaladujTabele();
        ukryjDodawanieAutora();
        ukryjDodawanie();
        errorText.setText("");
        infoAutor.setVisible(false);
        infoGatunek.setVisible(false);
        infoOkladka.setVisible(false);
        infoRok.setVisible(false);
        infoTytul.setVisible(false);
        infoWydawnictwo.setVisible(false);
        tytulField.setVisible(false);
        rokField.setVisible(false);
        autorChoiceBox.setVisible(false);
        wydawnictwoChoiceBox.setVisible(false);
        gatunekComboBox.setVisible(false);
        grubaOkladka.setVisible(false);
        cienkaOkladka.setVisible(false);
        addBookBtn.setVisible(false);
        anulujDodawanieBtn.setVisible(false);
        infoTytul.setVisible(false);
        errorText.setVisible(false);
        danaAkcjaField.setVisible(false);
        anulujEditBtn.setVisible(false);
        editBookBtn.setVisible(false);
        anulujSzukanieBtn.setVisible(false);
        dodajAutoraBtn.setVisible(false);
        ukryjNoweSzukanie();

    }

    @FXML
    void clickSearch(ActionEvent event) {
        anulujObiekt(event);
        danaAkcjaField.setText("Szukanie");
        tytulField.clear();
        rokField.clear();
        autorChoiceBox.valueProperty().set(null);
        wydawnictwoChoiceBox.valueProperty().set(null);
        gatunekComboBox.valueProperty().set(null);
        grubaOkladka.setSelected(false);
        cienkaOkladka.setSelected(false);
        errorText.setText("");
        ukryjEdytowanie();
        //pokazSzukanie();
        // obsluga nowego suzkania
        pokazNoweSzukanie();


    }

    @FXML
    void anulujSzukanie(ActionEvent event) {
        infoAutor.setVisible(false);
        infoGatunek.setVisible(false);
        infoOkladka.setVisible(false);
        infoRok.setVisible(false);
        infoTytul.setVisible(false);
        infoWydawnictwo.setVisible(false);
        tytulField.setVisible(false);
        rokField.setVisible(false);
        autorChoiceBox.setVisible(false);
        wydawnictwoChoiceBox.setVisible(false);
        gatunekComboBox.setVisible(false);
        grubaOkladka.setVisible(false);
        cienkaOkladka.setVisible(false);
        infoTytul.setVisible(false);
        errorText.setVisible(false);
        danaAkcjaField.setVisible(false);
        anulujSzukanieBtn.setVisible(false);


    }


    @FXML
    void dodajKsiazke(ActionEvent event) {

        int obecnyRok = LocalDate.now().getYear();
        RadioButton selected = (RadioButton) wybor.getSelectedToggle();
        if (selected == null || tytulField.getText().isEmpty() && tytulField.getText().isBlank() || !rokField.getText().matches("^[0-9]*$") ||
                autorChoiceBox.getValue() == null || wydawnictwoChoiceBox.getValue() == null || gatunekComboBox.getValue() == null || Integer.parseInt(rokField.getText()) > obecnyRok) {
            errorText.setText("Wszystkie pola muszą byc uzupelnione! Pamiętaj, że rok musi byc poprawny (może zawierac tylko cyfry i nie moze byc weikszy od obecnego roku ");
            errorText.setVisible(true);
        } else {
            String okladka = selected.getText();
            Ksiazka ksiazka = new Ksiazka(NULL, tytulField.getText(), rokField.getText(), okladka, autorChoiceBox.getSelectionModel().getSelectedItem(), wydawnictwoChoiceBox.getSelectionModel().getSelectedItem(), gatunekComboBox.getSelectionModel().getSelectedItem(), "dostepna");
            Transaction transaction = null;
            try (Session session = PolaczenieBazowe.otworzSesje()) {
                transaction = session.beginTransaction();
                session.persist(ksiazka);
                transaction.commit();
            } catch (Exception e) {
                if (transaction != null && transaction.isActive())
                    transaction.rollback();
            }
            zaladujTabele();
            ukryjDodawanie();
        }
    }

    private void zaladujTabele() {
        tableView.getItems().clear();
        Session session = PolaczenieBazowe.otworzSesje();
        String hql = "FROM Ksiazka ";
        Query query = session.createQuery(hql);
        List<Ksiazka> ksiazki = query.list();
        session.close();
        addButtonToTable();  // dodaj kolumnę z przyciskami do tabeli
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        tytulCol.setCellFactory(param -> {
            return new TableCell<Ksiazka, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    if (item == null || empty) {
                        setText(null);
                        setStyle("");
                    } else {
                        Text text = new Text(item);
                        text.setStyle("-fx-text-alignment:left;");

                        text.wrappingWidthProperty().bind(getTableColumn().widthProperty().subtract(35));
                        setGraphic(text);
                    }
                }
            };
        });
        tytulCol.setCellValueFactory(new PropertyValueFactory<>("tytul"));
        rokCol.setCellValueFactory(new PropertyValueFactory<>("rok"));
        wydawnictwoCol.setCellValueFactory(new PropertyValueFactory<>("wydawnictwo"));
        autorCol.setCellValueFactory(new PropertyValueFactory<>("autor"));
        okladkaCol.setCellValueFactory(new PropertyValueFactory<>("okladka"));
        gatunekCol.setCellValueFactory(new PropertyValueFactory<>("gatunek"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));

        tableView.getColumns().remove(editColumn);
        tableView.getColumns().add(editColumn);
        tableView.setItems(FXCollections.observableList(ksiazki));


    }


    @FXML
    void rozwijAutorow(MouseEvent event) {
        pobierzAutorow();
    }

    private void pobierzAutorow() {
        Session session = PolaczenieBazowe.otworzSesje();
        String hql = "FROM Autor ";
        Query query = session.createQuery(hql);
        List<Autor> autorzy = query.list();
        ObservableList<Autor> autorzyData = FXCollections.observableArrayList(autorzy);
        autorChoiceBox.setItems(autorzyData);
    }

    @FXML
    void rozwijWydawncitwa(MouseEvent event) {
        pobierzWydawncitwa();
    }

    private void pobierzWydawncitwa() {
        Session session = PolaczenieBazowe.otworzSesje();
        String hql = "FROM Wydawnictwo ";
        Query query = session.createQuery(hql);
        List<Wydawnictwo> wydawnictwa = query.list();
        ObservableList<Wydawnictwo> wydawnictwaData = FXCollections.observableArrayList(wydawnictwa);
        wydawnictwoChoiceBox.setItems(wydawnictwaData);
    }

    @FXML
    void rozwijGatunki(MouseEvent event) {
        Session session = PolaczenieBazowe.otworzSesje();
        String hql = "FROM Gatunek ";
        Query query = session.createQuery(hql);
        List<Gatunek> gatunki = query.list();
        ObservableList<Gatunek> gatunkiData = FXCollections.observableArrayList(gatunki);
        gatunekComboBox.setItems(gatunkiData);
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
            // Hide this current window (if this is what you want)
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addButtonToTable() {

        Callback<TableColumn<Ksiazka, Void>, TableCell<Ksiazka, Void>> cellFactory = new Callback<TableColumn<Ksiazka, Void>, TableCell<Ksiazka, Void>>() {
            @Override
            public TableCell<Ksiazka, Void> call(final TableColumn<Ksiazka, Void> param) {
                final TableCell<Ksiazka, Void> cell = new TableCell<Ksiazka, Void>() {

                    private final Button editColumnBtn = new Button("Edytuj");
                    private final Button btnDelete = new Button("Usuń");

                    {
                        editColumnBtn.setStyle("-fx-background-color: transparent; -fx-border-color: linear-gradient(to right, #d9b38c44, #ffffff44);" +
                                "-fx-border-radius: 10px; -fx-border-width: 2px 2px 2px 2px ");
                        btnDelete.setStyle("-fx-background-color: transparent; -fx-border-color: linear-gradient(to right, #d9b38c44, #ffffff44);" +
                                "-fx-border-radius: 10px; -fx-border-width: 2px 2px 2px 2px");

                        editColumnBtn.setOnAction((ActionEvent event) -> {
                            ksiazkaEdit = getTableView().getItems().get(getIndex());
                            danaAkcjaField.setText("Edytowanie");
                            tytulField.setText(ksiazkaEdit.getTytul());
                            rokField.setText(ksiazkaEdit.getRok());
                            autorChoiceBox.valueProperty().setValue(ksiazkaEdit.getAutor());
                            wydawnictwoChoiceBox.valueProperty().setValue(ksiazkaEdit.getWydawnictwo());
                            gatunekComboBox.valueProperty().setValue(ksiazkaEdit.getGatunek());

                            if (String.valueOf(ksiazkaEdit.getOkladka()).equals("Twarda"))
                                grubaOkladka.setSelected(true);
                            grubaOkladka.setVisible(true);
                            if (String.valueOf(ksiazkaEdit.getOkladka()).equals("Miękka"))
                                cienkaOkladka.setSelected(true);
                            ukryjSzukanie();
                            ukryjDodawanie();
                            pokazEdytowanie();


                        });
                        btnDelete.setOnAction((ActionEvent event) -> {
                            Ksiazka ksiazka = getTableView().getItems().get(getIndex());
                            if (ksiazka.getStatus().equals("wypozyczona")) {
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setTitle("Błąd");
                                alert.setHeaderText("Błąd usuwania");
                                alert.setContentText("Nie można usunac tej książki, ponieważ jest ona wypożyczona.");
                                alert.showAndWait();
                            } else {
                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                alert.setTitle("Usuwanie książki");
                                alert.setHeaderText("Czy na pewno chcesz usunąć tę książkę?\nSpowoduje to również usunięcie historii wypożyczeń tej książki w bazie");


                                Optional<ButtonType> result = alert.showAndWait();
                                if (result.get() == ButtonType.OK) {
                                    // ... user chose OK
                                    Session session2 = PolaczenieBazowe.otworzSesje();
                                    Transaction transaction2 = null;
                                    try {
                                        transaction2 = session2.beginTransaction();
                                        Query query = session2.createQuery("FROM Wypozyczenia where ksiazka= :ksiazka");
                                        query.setParameter("ksiazka",ksiazka);
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
                                    //
                                    Session session = PolaczenieBazowe.otworzSesje();
                                    Transaction transaction = null;
                                    try {
                                        transaction = session.beginTransaction();
                                        session.delete(ksiazka);
                                        transaction.commit();
                                    } catch (HibernateException e) {
                                        if (transaction != null) {
                                            transaction.rollback();
                                        }
                                        e.printStackTrace();
                                    } finally {
                                        session.close();
                                    }
                                    // usuwanie z tabeli
                                    tableView.getItems().remove(ksiazka);
                                    zaladujTabele();

                                } else {
                                    // ... user chose CANCEL or closed the dialog
                                    alert.close();
                                }

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

    @FXML
    void anulujObiekt(ActionEvent event) {
        ukryjDodawanieAutora();
        ukryjDodawanieWydawnictwa();
        ukryjDodawanieGatunku();


    }

    @FXML
    void dodajObiekt(ActionEvent event) {
        String imie = textField1.getText();
        String nazwisko = textField2.getText();
        if (textField1.getText().isEmpty() || textField1.getText().isBlank() || !textField1.getText().matches("^[a-zA-ZąćęłńóśźżĄĆĘŁŃÓŚŹŻ .-]+$") || textField2.getText().isEmpty() || textField2.getText().isBlank() || !textField2.getText().matches("^[a-zA-ZąćęłńóśźżĄĆĘŁŃÓŚŹŻ -]+$")) {
            errorTextAddObjects.setText("Pole nie może byc puste i musi zawierać same litery!");
            errorTextAddObjects.setVisible(true);
        } else {
            Transaction transaction = null;
            try (Session session = PolaczenieBazowe.otworzSesje()) {
                transaction = session.beginTransaction();
                Query query = session.createQuery("FROM Autor WHERE imie = :imie AND nazwisko = :nazwisko");
                query.setParameter("imie", imie);
                query.setParameter("nazwisko", nazwisko);
                Autor autorzy = (Autor) query.uniqueResult();

                // Jeśli zapytanie zwróciło rekord, oznacza to, że podane dane są prawidłowe
                if (autorzy != null) {
                    errorTextAddObjects.setText("Istnieje juz taki autor");
                    errorTextAddObjects.setVisible(true);
                } else {
                    Autor autor = new Autor(NULL, imie, nazwisko);
                    session.persist(autor);
                    transaction.commit();
                    ukryjDodawanieAutora();
                }

            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                e.printStackTrace();
            }
        }
    }

    @FXML
    void dodajAutora(ActionEvent event) {
        ukryjDodawanieGatunku();
        ukryjDodawanieWydawnictwa();
        info.setText("Dodaj autora");
        text1.setText("Imie: ");
        text2.setText("Nazwisko: ");
        text1.setVisible(true);
        text2.setVisible(true);
        textField1.setVisible(true);
        textField2.setVisible(true);
        dodajObiektBtn.setVisible(true);
        anulujObiektBtn.setVisible(true);
        info.setVisible(true);
        errorText.setText("");

    }

    @FXML
    void dodajObiektWydawnictwo(ActionEvent event) {
        String wydawnictwo = textField1.getText();
        if (textField1.getText().isBlank() || textField1.getText().isEmpty()) {
            errorTextAddObjects.setText("Pole nie moze byc puste!");
            errorTextAddObjects.setVisible(true);
        } else {
            Transaction transaction = null;
            try (Session session = PolaczenieBazowe.otworzSesje()) {
                transaction = session.beginTransaction();
                Query query = session.createQuery("FROM Wydawnictwo WHERE wydawnictwo = :wydawnictwo");
                query.setParameter("wydawnictwo", wydawnictwo);
                Wydawnictwo wydawnictwa = (Wydawnictwo) query.uniqueResult();

                // Jeśli zapytanie zwróciło rekord, oznacza to, że podane dane są prawidłowe
                if (wydawnictwa != null) {
                    errorTextAddObjects.setText("Istnieje juz takie wydawnictwo");
                    errorTextAddObjects.setVisible(true);
                } else {
                    Wydawnictwo wydaw = new Wydawnictwo(null, wydawnictwo);
                    session.persist(wydaw);
                    transaction.commit();
                    ukryjDodawanieWydawnictwa();
                }

            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                e.printStackTrace();
            }
        }
    }

    @FXML
    void dodajWydawnictwo(ActionEvent event) {
        ukryjDodawanieGatunku();
        ukryjDodawanieAutora();
        info.setText("Dodaj wydawnictwo");
        text1.setText("Nazwa: ");

        text1.setVisible(true);

        textField1.setVisible(true);

        dodajObiektWydawnictwo.setVisible(true);
        anulujObiektBtn.setVisible(true);
        info.setVisible(true);
        errorText.setText("");


    }

    @FXML
    void dodajGatunek(ActionEvent event) {
        ukryjDodawanieWydawnictwa();
        ukryjDodawanieAutora();
        info.setText("Dodaj Gatunek");
        text1.setText("Nazwa: ");

        text1.setVisible(true);

        textField1.setVisible(true);

        dodajObiektGatunek.setVisible(true);
        anulujObiektBtn.setVisible(true);
        info.setVisible(true);
        errorText.setText("");


    }

    @FXML
    void dodajObiektGatunek(ActionEvent event) {
        String gatunek = textField1.getText();
        if (textField1.getText().isEmpty() || textField1.getText().isBlank() || !textField1.getText().matches("^[a-zA-ZąćęłńóśźżĄĆĘŁŃÓŚŹŻ -]+$")) {
            errorTextAddObjects.setText("Pole nie może byc puste i musi zawierać same litery!");
            errorTextAddObjects.setVisible(true);
        } else {

            Transaction transaction = null;
            try (Session session = PolaczenieBazowe.otworzSesje()) {
                transaction = session.beginTransaction();
                Query query = session.createQuery("FROM Gatunek WHERE gatunek = :gatunek");
                query.setParameter("gatunek", gatunek);
                Gatunek gatunki = (Gatunek) query.uniqueResult();

                // Jeśli zapytanie zwróciło rekord, oznacza to, że podane dane są prawidłowe
                if (gatunki != null) {
                    errorTextAddObjects.setText("Istnieje juz taki gatunek literacki");
                    errorTextAddObjects.setVisible(true);
                } else {
                    Gatunek gatunekObject = new Gatunek(NULL, gatunek);
                    session.persist(gatunekObject);
                    transaction.commit();
                    ukryjDodawanieGatunku();
                }

            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                e.printStackTrace();
            }
        }

    }

    void ukryjDodawanieAutora() {
        text1.setVisible(false);
        text2.setVisible(false);
        textField1.setVisible(false);
        textField2.setVisible(false);
        dodajObiektBtn.setVisible(false);
        anulujObiektBtn.setVisible(false);
        info.setVisible(false);
        errorTextAddObjects.setVisible(false);
        dodajObiektWydawnictwo.setVisible(false);
        textField1.clear();
        textField2.clear();
    }

    void ukryjDodawanieWydawnictwa() {
        text1.setVisible(false);
        textField1.setVisible(false);
        dodajObiektWydawnictwo.setVisible(false);
        anulujObiektBtn.setVisible(false);
        info.setVisible(false);
        errorTextAddObjects.setVisible(false);
        dodajObiektWydawnictwo.setVisible(false);
        textField1.clear();

    }

    void ukryjDodawanieGatunku() {
        text1.setVisible(false);
        textField1.setVisible(false);
        dodajObiektGatunek.setVisible(false);
        anulujObiektBtn.setVisible(false);
        info.setVisible(false);
        errorTextAddObjects.setVisible(false);
        textField1.clear();

    }

    void ukryjDodawanie() {
        infoAutor.setVisible(false);
        infoGatunek.setVisible(false);
        infoOkladka.setVisible(false);
        infoRok.setVisible(false);
        infoTytul.setVisible(false);
        infoWydawnictwo.setVisible(false);
        tytulField.setVisible(false);
        rokField.setVisible(false);
        autorChoiceBox.setVisible(false);
        wydawnictwoChoiceBox.setVisible(false);
        gatunekComboBox.setVisible(false);
        grubaOkladka.setVisible(false);
        cienkaOkladka.setVisible(false);
        addBookBtn.setVisible(false);
        anulujDodawanieBtn.setVisible(false);
        infoTytul.setVisible(false);
        errorText.setVisible(false);
        danaAkcjaField.setVisible(false);
        dodajGatunek.setVisible(false);
        dodajWydawnictwoBtn.setVisible(false);
        dodajAutoraBtn.setVisible(false);
        ukryjDodawanieWydawnictwa();
        ukryjDodawanieGatunku();
        ukryjDodawanieAutora();
    }

    void pokazDodawanie() {
        infoAutor.setVisible(true);
        infoGatunek.setVisible(true);
        infoOkladka.setVisible(true);
        infoRok.setVisible(true);
        infoTytul.setVisible(true);
        infoWydawnictwo.setVisible(true);
        tytulField.setVisible(true);
        rokField.setVisible(true);
        autorChoiceBox.setVisible(true);
        wydawnictwoChoiceBox.setVisible(true);
        gatunekComboBox.setVisible(true);
        grubaOkladka.setVisible(true);
        cienkaOkladka.setVisible(true);
        addBookBtn.setVisible(true);
        anulujDodawanieBtn.setVisible(true);
        infoTytul.setVisible(true);
        errorText.setVisible(true);
        danaAkcjaField.setVisible(true);
        dodajGatunek.setVisible(true);
        dodajWydawnictwoBtn.setVisible(true);
        dodajAutoraBtn.setVisible(true);
        ukryjDodawanieGatunku();
        ukryjDodawanieWydawnictwa();
        ukryjDodawanieAutora();
    }

    void pokazSzukanie() {
        infoAutor.setVisible(true);
        infoGatunek.setVisible(true);
        infoOkladka.setVisible(true);
        infoRok.setVisible(true);
        infoTytul.setVisible(true);
        infoWydawnictwo.setVisible(true);
        tytulField.setVisible(true);
        rokField.setVisible(true);
        autorChoiceBox.setVisible(true);
        wydawnictwoChoiceBox.setVisible(true);
        gatunekComboBox.setVisible(true);
        grubaOkladka.setVisible(true);
        cienkaOkladka.setVisible(true);

        anulujSzukanieBtn.setVisible(true);
        infoTytul.setVisible(true);
        errorText.setText("");
        danaAkcjaField.setVisible(true);
    }

    void ukryjSzukanie() {
        infoAutor.setVisible(false);
        infoGatunek.setVisible(false);
        infoOkladka.setVisible(false);
        infoRok.setVisible(false);
        infoTytul.setVisible(false);
        infoWydawnictwo.setVisible(false);
        tytulField.setVisible(false);
        rokField.setVisible(false);
        autorChoiceBox.setVisible(false);
        wydawnictwoChoiceBox.setVisible(false);
        gatunekComboBox.setVisible(false);
        grubaOkladka.setVisible(false);
        cienkaOkladka.setVisible(false);

        anulujSzukanieBtn.setVisible(false);
        infoTytul.setVisible(false);
        errorText.setVisible(false);
        danaAkcjaField.setVisible(false);
        ukryjNoweSzukanie();
    }

    void pokazEdytowanie() {
        infoAutor.setVisible(true);
        infoGatunek.setVisible(true);
        infoOkladka.setVisible(true);
        infoRok.setVisible(true);
        infoTytul.setVisible(true);
        infoWydawnictwo.setVisible(true);
        tytulField.setVisible(true);
        rokField.setVisible(true);
        autorChoiceBox.setVisible(true);
        wydawnictwoChoiceBox.setVisible(true);
        gatunekComboBox.setVisible(true);
        grubaOkladka.setVisible(true);
        cienkaOkladka.setVisible(true);
        anulujEditBtn.setVisible(true);
        editBookBtn.setVisible(true);
        infoTytul.setVisible(true);
        errorText.setText("");
        danaAkcjaField.setVisible(true);
        dodajGatunek.setVisible(true);
        dodajWydawnictwoBtn.setVisible(true);
        dodajAutoraBtn.setVisible(true);
    }

    void ukryjEdytowanie() {
        ukryjDodawanie();
        editBookBtn.setVisible(false);
        anulujEditBtn.setVisible(false);
    }
    @FXML
    void szukajPoAutorze(ActionEvent event) {
        ukryjNoweSzukanie();
        pokazNoweSzukanie();
        szukaniePoText.setText("Wyszukaj po autorze: ");
     szukaniePoText.setVisible(true);
        //infoAutor.setVisible(true);
        autorChoiceBox.setVisible(true);
        zatwierdzSzukaniePoAutorBtn.setVisible(true);
        anulujSzukaniePoBtn.setVisible(true);

    }

    @FXML
    void szukajPoID(ActionEvent event) {
        ukryjNoweSzukanie();
        pokazNoweSzukanie();
        szukaniePoText.setText("Wyszukaj po ID: ");
        szukaniePoText.setVisible(true);
        anulujSzukaniePoBtn.setVisible(true);
        szukajField.setVisible(true);
        szukajPoIDBtn.setVisible(true);
        zatwierdzSzukaniePoIDBtn.setVisible(true);
        anulujSzukaniePoBtn.setVisible(true);
    }

    @FXML
    void szukajPoTytule(ActionEvent event) {
       ukryjNoweSzukanie();
       pokazNoweSzukanie();
        szukaniePoText.setVisible(true);
        szukaniePoText.setText("Wyszukaj po tytule: ");
        anulujSzukaniePoBtn.setVisible(true);
        szukajField.setVisible(true);
        zatwierdzSzukaniePoTytulBtn.setVisible(true);




    }
    void ukryjNoweSzukanie(){
        szukaniePoText.setText("");
        wyszukajPoText.setVisible(false);
        szukajPoAutorzeBtn.setVisible(false);
        szukajPoTytlueBtn.setVisible(false);
        szukajPoIDBtn.setVisible(false);
        szukajField.setVisible(false);
        szukaniePoText.setVisible(false);
        szukaniePoText.setText("");
        anulujSzukaniePoBtn.setVisible(false);
        autorChoiceBox.setVisible(false);
        szukajPoAutorzeBtn.setVisible(false);
        szukajPoTytlueBtn.setVisible(false);
        szukajPoIDBtn.setVisible(false);
        zatwierdzSzukaniePoAutorBtn.setVisible(false);
        zatwierdzSzukaniePoTytulBtn.setVisible(false);
        zatwierdzSzukaniePoIDBtn.setVisible(false);
        bladSzukaniaText.setVisible(false);
        szukajField.clear();

    }
    void pokazNoweSzukanie(){
        wyszukajPoText.setVisible(true);
        szukajPoAutorzeBtn.setVisible(true);
        szukajPoTytlueBtn.setVisible(true);
        szukajPoIDBtn.setVisible(true);
    }


    @FXML
    void zatwierdzSzukaniePoAutor(ActionEvent event)  // akcja jesli zatwierdzimy szukanie po wybranym autorze
    {
        Autor autor = autorChoiceBox.getSelectionModel().getSelectedItem();
        if (autor == null){
            bladSzukaniaText.setVisible(true);
            bladSzukaniaText.setText("Nie wybrales autora!");
        }
        else {
            tableView.getItems().clear();
            Session session = PolaczenieBazowe.otworzSesje(); // TO DO obsluga szukania gdy nie wybrano autora wydawnictwa agatunku itp - czegos z combo boxa
            String hql = "FROM Ksiazka e where  e.autor = :autor";
            Query query = session.createQuery(hql);
            query.setParameter("autor", autor);

            List<Ksiazka> ksiazki = query.list();
            session.close();
            session.close();
            addButtonToTable();  // dodaj kolumnę z przyciskami do tabeli
            idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
            tytulCol.setCellFactory(param -> {
                return new TableCell<Ksiazka, String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);

                        if (item == null || empty) {
                            setText(null);
                            setStyle("");
                        } else {
                            Text text = new Text(item);
                            text.setStyle("-fx-text-alignment:justify;");

                            text.wrappingWidthProperty().bind(getTableColumn().widthProperty().subtract(35));
                            setGraphic(text);
                        }
                    }
                };
            });
            tytulCol.setCellValueFactory(new PropertyValueFactory<>("tytul"));
            rokCol.setCellValueFactory(new PropertyValueFactory<>("rok"));
            wydawnictwoCol.setCellValueFactory(new PropertyValueFactory<>("wydawnictwo"));
            autorCol.setCellValueFactory(new PropertyValueFactory<>("autor"));
            okladkaCol.setCellValueFactory(new PropertyValueFactory<>("okladka"));
            gatunekCol.setCellValueFactory(new PropertyValueFactory<>("gatunek"));

            tableView.getColumns().remove(editColumn);
            tableView.getColumns().add(editColumn);
            tableView.setItems(FXCollections.observableList(ksiazki));

        }
    }
    @FXML
    void anulujSzukaniePo(ActionEvent event) //chowa przyciski dla szukania pon id,autorze,tytule
    {
    ukryjNoweSzukanie();
    }

    @FXML
    void zatwierdzSzukaniePoTytul(ActionEvent event)  // akcja jesli zatwierdzimy szukanie po wybranym autorze
    {
        String tytul = szukajField.getText();
        tableView.getItems().clear();
        Session session = PolaczenieBazowe.otworzSesje(); // TO DO obsluga szukania gdy nie wybrano autora wydawnictwa agatunku itp - czegos z combo boxa
        String hql = "FROM Ksiazka e where e.tytul LIKE :tytul";
        Query query = session.createQuery(hql);

        query.setParameter("tytul", "%" + tytul + "%");

        List<Ksiazka> ksiazki = query.list();
        session.close();
        session.close();
        addButtonToTable();  // dodaj kolumnę z przyciskami do tabeli
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        tytulCol.setCellFactory(param -> {
            return new TableCell<Ksiazka, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    if (item == null || empty) {
                        setText(null);
                        setStyle("");
                    } else {
                        Text text = new Text(item);
                        text.setStyle("-fx-text-alignment:justify;");

                        text.wrappingWidthProperty().bind(getTableColumn().widthProperty().subtract(35));
                        setGraphic(text);
                    }
                }
            };
        });
        tytulCol.setCellValueFactory(new PropertyValueFactory<>("tytul"));
        rokCol.setCellValueFactory(new PropertyValueFactory<>("rok"));
        wydawnictwoCol.setCellValueFactory(new PropertyValueFactory<>("wydawnictwo"));
        autorCol.setCellValueFactory(new PropertyValueFactory<>("autor"));
        okladkaCol.setCellValueFactory(new PropertyValueFactory<>("okladka"));
        gatunekCol.setCellValueFactory(new PropertyValueFactory<>("gatunek"));

        tableView.getColumns().remove(editColumn);
        tableView.getColumns().add(editColumn);
        tableView.setItems(FXCollections.observableList(ksiazki));


    }
    @FXML
    void zatwierdzSzukaniePoID(ActionEvent event)  // akcja jesli zatwierdzimy szukanie po wybranym autorze
    {
        String id = szukajField.getText();
        if (!id.matches("^[0-9]*$") || id.isEmpty() || id.isBlank()){
            bladSzukaniaText.setVisible(true);
            bladSzukaniaText.setText("ID może zawierać tylko cyfry!");
        }
        else{
            tableView.getItems().clear();
            Session session = PolaczenieBazowe.otworzSesje(); // TO DO obsluga szukania gdy nie wybrano autora wydawnictwa agatunku itp - czegos z combo boxa
            String hql = "FROM Ksiazka e where  e.id = :id ";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            List<Ksiazka> ksiazki = query.list();
            session.close();
            session.close();
            addButtonToTable();  // dodaj kolumnę z przyciskami do tabeli
            idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
            tytulCol.setCellFactory(param -> {
                return new TableCell<Ksiazka, String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);

                        if (item == null || empty) {
                            setText(null);
                            setStyle("");
                        } else {
                            Text text = new Text(item);
                            text.setStyle("-fx-text-alignment:justify;");

                            text.wrappingWidthProperty().bind(getTableColumn().widthProperty().subtract(35));
                            setGraphic(text);
                        }
                    }
                };
            });
            tytulCol.setCellValueFactory(new PropertyValueFactory<>("tytul"));
            rokCol.setCellValueFactory(new PropertyValueFactory<>("rok"));
            wydawnictwoCol.setCellValueFactory(new PropertyValueFactory<>("wydawnictwo"));
            autorCol.setCellValueFactory(new PropertyValueFactory<>("autor"));
            okladkaCol.setCellValueFactory(new PropertyValueFactory<>("okladka"));
            gatunekCol.setCellValueFactory(new PropertyValueFactory<>("gatunek"));

            tableView.getColumns().remove(editColumn);
            tableView.getColumns().add(editColumn);
            tableView.setItems(FXCollections.observableList(ksiazki));


        }
    }


}
