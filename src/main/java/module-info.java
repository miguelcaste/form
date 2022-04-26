module com.example.form {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.form to javafx.fxml;
    exports com.example.form;
}