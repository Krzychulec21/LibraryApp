module com.example.library {
    requires javafx.controls;
    requires javafx.fxml;
    requires fontawesomefx;
    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires java.naming;


    opens com.example.library to javafx.fxml,org.hibernate.orm.core;
    exports com.example.library;
    exports com.example.library.model;
    opens com.example.library.model to javafx.fxml, org.hibernate.orm.core;
    exports com.example.library.controller;
    opens com.example.library.controller to javafx.fxml, org.hibernate.orm.core;
    exports com.example.library.util;
    opens com.example.library.util to javafx.fxml, org.hibernate.orm.core;
    exports com.example.library.repository;
    opens com.example.library.repository to javafx.fxml, org.hibernate.orm.core;
}
