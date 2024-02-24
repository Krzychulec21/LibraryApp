package com.example.library.controller;

import com.example.library.util.PolaczenieBazowe;
import com.example.library.model.Autor;
import com.example.library.model.Ksiazka;
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

public class KontrolerWypozyczen {

    @FXML
    private TableColumn<Ksiazka, Autor> bookAuthorCol;

    @FXML
    private TableColumn<Ksiazka, Long> bookIdCol;

    @FXML
    private TableColumn<Ksiazka, String> bookTitleCol;

    @FXML
    private Button loadAllBooksBtn;

    @FXML
    private TextField wypoBookIdField;

    @FXML
    private TextField wypoUserIdField;
    @FXML
    private Button szukajBookIDBtn;

    @FXML
    private Button szukajUserIDBtn;
    @FXML
    private TextField fieldInfo;
    @FXML
    private TextField nazwiskoField;
    @FXML
    private TextField tytulField;
    @FXML
    private Text errorSzukajText;
    @FXML
    private Text errorWypoText;
    @FXML
    private Button szukajPoNazwiskuBtn;

    @FXML
    private Button szukajPoTytuleBtn;


    @FXML
    private Button loadAllUsersBtn;

    @FXML
    private TableView<Ksiazka> tableBooks;

    @FXML
    private TableView<Osoba> tableUsers;

    @FXML
    private TableView<Wypozyczenia> tableWypo;

    @FXML
    private TableColumn<Osoba, Long> userIdCol;

    @FXML
    private TableColumn<Osoba, String> userNameCol;

    @FXML
    private TableColumn<Osoba, String> userSurnameCol;
    @FXML
    private TextField fieldUserID;


    @FXML
    private TextField fieldBookID;

    @FXML
    private Button loadSelectedBooksBtn;
    @FXML
    private Button polaczBtn;

    @FXML
    private Button loadSelectedUsersBtn;
    @FXML
    private Button backBtn;
    @FXML
    private Button loadTableBtn;
    //tabela wypozyczen kolumny
    @FXML
    private TableColumn<Wypozyczenia, Void> editColumn;

    @FXML
    private TableColumn<Wypozyczenia, String> lendBookAuthorCol;

    @FXML
    private TableColumn<Wypozyczenia, Long> lendBookIdCol;

    @FXML
    private TableColumn<Wypozyczenia, String> lendBookTitleCol;

    @FXML
    private TableColumn<Wypozyczenia, LocalDate> lendDataOddCol;

    @FXML
    private TableColumn<Wypozyczenia, LocalDate> lendDataWypoCol;

    @FXML
    private TableColumn<Wypozyczenia, Long> lendIdCol;

    @FXML
    private TableColumn<Ksiazka, Long> lendUserIdCol;

    @FXML
    private TableColumn<Wypozyczenia, String> lendUserNameCol;

    @FXML
    private TableColumn<Wypozyczenia, String> lendUserSurnameCol;


    @FXML
    void loadAllBooks(ActionEvent event) {
        errorSzukajText.setVisible(false);
        tableBooks.getItems().clear();
        String status = "dostepna";
        Session session = PolaczenieBazowe.otworzSesje();
        String hql = "From Ksiazka where status = :status";
        Query query = session.createQuery(hql);
        query.setParameter("status", status);
        List<Ksiazka> ksiazki = query.list();
        session.close();
        bookIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        bookTitleCol.setCellFactory(param -> {
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
        bookTitleCol.setCellValueFactory(new PropertyValueFactory<>("tytul"));
        bookAuthorCol.setCellValueFactory(new PropertyValueFactory<>("autor"));
        tableBooks.setItems(FXCollections.observableList(ksiazki));

    }

    @FXML
    void loadAllUsers(ActionEvent event) {
        tableUsers.getItems().clear();
        Session session = PolaczenieBazowe.otworzSesje();
        String hql = "FROM Osoba ";
        Query query = session.createQuery(hql);
        List<Osoba> osoby = query.list();
        session.close();
        //  addButtonToTable();  // dodaj kolumnę z przyciskami do tabeli
        userIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        userNameCol.setCellValueFactory(new PropertyValueFactory<>("imie"));
        userSurnameCol.setCellValueFactory(new PropertyValueFactory<>("nazwisko"));
        tableUsers.setItems(FXCollections.observableList(osoby));
        errorSzukajText.setVisible(false);

    }

    @FXML
    void loadSelectedBooks(ActionEvent event) {
        errorSzukajText.setVisible(false);
        String id = fieldBookID.getText();
        if (id.matches("^[0-9]*$")) {
            String status = "dostepna";
            tableBooks.getItems().clear();
            Session session = PolaczenieBazowe.otworzSesje();
            String hql = "FROM Ksiazka where id = :id and status = :status";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            query.setParameter("status", status);
            List<Ksiazka> ksiazki = query.list();
            session.close();
            bookIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
            bookTitleCol.setCellFactory(param -> {
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
            bookTitleCol.setCellValueFactory(new PropertyValueFactory<>("tytul"));
            bookAuthorCol.setCellValueFactory(new PropertyValueFactory<>("autor"));
            tableBooks.setItems(FXCollections.observableList(ksiazki));
            fieldBookID.clear();
        } else {
            errorSzukajText.setVisible(true);
        }

    }

    @FXML
    void loadSelectedUsers(ActionEvent event) {
        errorSzukajText.setVisible(false);
        String id = fieldUserID.getText();
        if (id.matches("^[0-9]*$")) {
            tableUsers.getItems().clear();
            Session session = PolaczenieBazowe.otworzSesje();
            String hql = "FROM Osoba where id= :id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            List<Osoba> osoby = query.list();
            session.close();
            userIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
            userNameCol.setCellValueFactory(new PropertyValueFactory<>("imie"));
            userSurnameCol.setCellValueFactory(new PropertyValueFactory<>("nazwisko"));
            tableUsers.setItems(FXCollections.observableList(osoby));

        } else {
            errorSzukajText.setText("Dozwolone są tylko cyfry!");
            errorSzukajText.setVisible(true);
        }


    }

    @FXML
    void polacz(ActionEvent event) {
        Ksiazka selectedBook = tableBooks.getSelectionModel().getSelectedItem();
        Osoba selectedUser = tableUsers.getSelectionModel().getSelectedItem();
        if (selectedUser == null || selectedBook == null) {
            fieldInfo.setText("Osoba jak i ksiazka musza byc wybrane!");
            fieldInfo.setVisible(true);
        } else {
            selectedBook.setStatus("wypozyczona");
            Wypozyczenia noweWypo = new Wypozyczenia(NULL, selectedUser, selectedBook, LocalDate.now(), null);
            Session session = PolaczenieBazowe.otworzSesje();
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                session.persist(noweWypo);
                session.update(selectedBook);
                transaction.commit();
            } catch (HibernateException e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                e.printStackTrace();
            } finally {
                session.close();
            }
        }
        zaladujTabele();
        loadAllUsers(event);
        loadAllBooks(event);
    }

    @FXML
    void loadTable(ActionEvent event) {
        zaladujTabele();
        errorSzukajText.setVisible(false);


    }

    private void addButtonToTable() {

        Callback<TableColumn<Wypozyczenia, Void>, TableCell<Wypozyczenia, Void>> cellFactory = new Callback<TableColumn<Wypozyczenia, Void>, TableCell<Wypozyczenia, Void>>() {
            @Override
            public TableCell<Wypozyczenia, Void> call(final TableColumn<Wypozyczenia, Void> param) {
                final TableCell<Wypozyczenia, Void> cell = new TableCell<Wypozyczenia, Void>() {

                    private final Button editColumnBtn = new Button("Oddano");


                    {
                        editColumnBtn.setStyle("-fx-background-color: transparent; -fx-border-color: linear-gradient(to right, #d9b38c44, #ffffff44);" +
                                "-fx-border-radius: 10px; -fx-border-width: 2px 2px 2px 2px ");


                        editColumnBtn.setOnAction((ActionEvent event) -> {
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Oddanie książki");
                            alert.setHeaderText("Czy na pewno chcesz zatwierdzić oddanie książki?");


                            Optional<ButtonType> result = alert.showAndWait();
                            if (result.get() == ButtonType.OK) {
                                Wypozyczenia selectedWypo = tableWypo.getItems().get(getIndex());
                                Ksiazka selectedBook = selectedWypo.getKsiazka();
                                selectedBook.setStatus("dostepna");
                                selectedWypo.setDataOddania(LocalDate.now());
                                Session session = PolaczenieBazowe.otworzSesje();
                                Transaction transaction = null;
                                try {
                                    transaction = session.beginTransaction();
                                    session.update(selectedWypo);
                                    session.update(selectedBook);
                                    transaction.commit();
                                } catch (HibernateException e) {
                                    if (transaction != null) {
                                        transaction.rollback();
                                    }
                                    e.printStackTrace();
                                } finally {
                                    session.close();
                                }


                                zaladujTabele();
                            } else {
                                alert.close();
                            }
                        });


                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            Wypozyczenia wypo = getTableView().getItems().get(getIndex());
                            Ksiazka ksiazka = wypo.getKsiazka();
                            if (ksiazka.getStatus().equals("wypozyczona") && wypo.getDataOddania() == null) {
                                HBox hBox = new HBox(editColumnBtn);
                                hBox.setSpacing(10);
                                hBox.setPadding(new Insets(1));
                                setGraphic(hBox);
                            } else {
                                setGraphic(null);
                            }
                        }
                    }
                };
                return cell;
            }
        };
        editColumn.setCellFactory(cellFactory);
    }

    private void zaladujTabele() {
        wypoUserIdField.clear();
        wypoBookIdField.clear();
        errorWypoText.setVisible(false);
        tableWypo.getItems().clear();
        Session session = PolaczenieBazowe.otworzSesje();
        String hql = "FROM Wypozyczenia ";
        Query query = session.createQuery(hql);
        List<Wypozyczenia> wypo = query.list();
        session.close();
        addButtonToTable();  // dodaj kolumnę z przyciskami do tabeli
        lendIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        lendUserIdCol.setCellValueFactory(new PropertyValueFactory<>("idOsoby"));
        lendUserNameCol.setCellValueFactory(new PropertyValueFactory<>("imieOsoby"));
        lendUserSurnameCol.setCellValueFactory(new PropertyValueFactory<>("nazwiskoOsoby"));
        lendBookIdCol.setCellValueFactory(new PropertyValueFactory<>("idKsiazki"));
        lendBookTitleCol.setCellFactory(param -> {
            return new TableCell<Wypozyczenia, String>() {
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
        lendBookTitleCol.setCellValueFactory(new PropertyValueFactory<>("tytulKsiazki"));
        lendBookAuthorCol.setCellValueFactory(new PropertyValueFactory<>("nazwaAutora"));
        lendDataWypoCol.setCellValueFactory(new PropertyValueFactory<>("dataWypozyczenia"));
        lendDataOddCol.setCellValueFactory(new PropertyValueFactory<>("dataOddania"));
        tableWypo.setItems(FXCollections.observableList(wypo));
    }

    @FXML
    void backToMenu(ActionEvent event) {
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

    @FXML
    void szukajWypoBook(ActionEvent event) {
        errorWypoText.setVisible(false);
        String id = wypoBookIdField.getText();
        if (id.matches("^[0-9]*$")) {
            errorWypoText.setVisible(false);
            tableWypo.getItems().clear();
            Session session = PolaczenieBazowe.otworzSesje();
            String hql = "FROM Wypozyczenia where ksiazka.id = :id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            List<Wypozyczenia> wypo = query.list();
            session.close();
            addButtonToTable();  // dodaj kolumnę z przyciskami do tabeli
            lendIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
            lendUserIdCol.setCellValueFactory(new PropertyValueFactory<>("idOsoby"));
            lendUserNameCol.setCellValueFactory(new PropertyValueFactory<>("imieOsoby"));
            lendUserSurnameCol.setCellValueFactory(new PropertyValueFactory<>("nazwiskoOsoby"));
            lendBookIdCol.setCellValueFactory(new PropertyValueFactory<>("idKsiazki"));
            lendBookTitleCol.setCellFactory(param -> {
                return new TableCell<Wypozyczenia, String>() {
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
            lendBookTitleCol.setCellValueFactory(new PropertyValueFactory<>("tytulKsiazki"));
            lendBookAuthorCol.setCellValueFactory(new PropertyValueFactory<>("nazwaAutora"));
            lendDataWypoCol.setCellValueFactory(new PropertyValueFactory<>("dataWypozyczenia"));
            lendDataOddCol.setCellValueFactory(new PropertyValueFactory<>("dataOddania"));
            tableWypo.setItems(FXCollections.observableList(wypo));
        } else {
            errorWypoText.setVisible(true);
        }

    }


    @FXML
    void szukajWypoUser(ActionEvent event) {
        errorWypoText.setVisible(false);
        String id = wypoUserIdField.getText();
        if (id.matches("^[0-9]*$")) {
            errorWypoText.setVisible(false);
            tableWypo.getItems().clear();
            Session session = PolaczenieBazowe.otworzSesje();
            String hql = "FROM Wypozyczenia where osoba.id = :id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            List<Wypozyczenia> wypo = query.list();
            session.close();
            addButtonToTable();  // dodaj kolumnę z przyciskami do tabeli
            lendIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
            lendUserIdCol.setCellValueFactory(new PropertyValueFactory<>("idOsoby"));
            lendUserNameCol.setCellValueFactory(new PropertyValueFactory<>("imieOsoby"));
            lendUserSurnameCol.setCellValueFactory(new PropertyValueFactory<>("nazwiskoOsoby"));
            lendBookIdCol.setCellValueFactory(new PropertyValueFactory<>("idKsiazki"));
            lendBookTitleCol.setCellFactory(param -> {
                return new TableCell<Wypozyczenia, String>() {
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
            lendBookTitleCol.setCellValueFactory(new PropertyValueFactory<>("tytulKsiazki"));
            lendBookAuthorCol.setCellValueFactory(new PropertyValueFactory<>("nazwaAutora"));
            lendDataWypoCol.setCellValueFactory(new PropertyValueFactory<>("dataWypozyczenia"));
            lendDataOddCol.setCellValueFactory(new PropertyValueFactory<>("dataOddania"));
            tableWypo.setItems(FXCollections.observableList(wypo));
        } else {
            errorWypoText.setVisible(true);
        }

    }

    @FXML
    void szukajPoNazwisku(ActionEvent event) {
        String nazwisko = nazwiskoField.getText();
       if (nazwisko.matches("^[a-zA-ZąćęłńóśźżĄĆĘŁŃÓŚŹŻ -]+$")) {
            errorSzukajText.setVisible(false);
           tableUsers.getItems().clear();
           Session session = PolaczenieBazowe.otworzSesje();
           String hql = "FROM Osoba where nazwisko like '%" + nazwisko + "%'";
           Query query = session.createQuery(hql);
           List<Osoba> osoby = query.list();
           session.close();
           userIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
           userNameCol.setCellValueFactory(new PropertyValueFactory<>("imie"));
           userSurnameCol.setCellValueFactory(new PropertyValueFactory<>("nazwisko"));
           tableUsers.setItems(FXCollections.observableList(osoby));
           fieldUserID.clear();
       }
       else {
           errorSzukajText.setText("Nazwisko nie może zawierać liczb i znaków specjalnych!");
           errorSzukajText.setVisible(true);
       }



    }

    @FXML
    void szukajPoTytule(ActionEvent event) {
        errorSzukajText.setVisible(false);
            String tytul = tytulField.getText();
            String status = "dostepna";
            tableBooks.getItems().clear();
            Session session = PolaczenieBazowe.otworzSesje();
            String hql = "FROM Ksiazka where tytul like '%" + tytul + "%' and status = :status";
            Query query = session.createQuery(hql);
            query.setParameter("status", status);
            List<Ksiazka> ksiazki = query.list();
            session.close();
            bookIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
            bookTitleCol.setCellFactory(param -> {
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
            bookTitleCol.setCellValueFactory(new PropertyValueFactory<>("tytul"));
            bookAuthorCol.setCellValueFactory(new PropertyValueFactory<>("autor"));
            tableBooks.setItems(FXCollections.observableList(ksiazki));
            fieldBookID.clear();

    }
}
