module com.example.library {
    requires javafx.controls;
    requires javafx.fxml;
    requires fontawesomefx;
    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires java.naming;


    opens com.example.library to javafx.fxml,org.hibernate.orm.core;
    exports com.example.library;
}