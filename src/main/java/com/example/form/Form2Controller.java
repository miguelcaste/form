package com.example.form;

import com.example.form.model.Persona;
import com.example.form.util.ConexionBBDD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Form2Controller {
    @FXML
    public TableView<Persona> tbvPersonas;
    @FXML
    public TableColumn<Persona,String> tbcNombre;
    @FXML
    public TableColumn<Persona,String> tbcApellidos;
    @FXML
    public TableColumn<Persona,Number> tbcEdad;// En JavaFX esta definido así

    @FXML
    public TextField txtNombre;
    @FXML
    public TextField txtApellidos;
    @FXML
    public TextField txtEdad;

// Conexión
    private ConexionBBDD connection;
    private ResultSet resultSet;




    ObservableList<Persona> personas = FXCollections.observableArrayList();

// Crea un la lista con varias personas de ejemplo
    private List<Persona> cargarDatos() throws SQLException {
        // Cargamos desde una base de datos
        connection=new ConexionBBDD();
        // Nos conectamos
        connection.getConexion();
        // Cargamos los datos
        resultSet=connection.ejecutarConsulta("SELECT * FROM personas");

        List<Persona> listaPersonas = new ArrayList<>();
        String nombre="";
        String apellidos="";
        int edad=0;

        while (resultSet.next()){
            nombre=resultSet.getString("nombre");
            apellidos=resultSet.getString("apellidos");
            edad=Integer.parseInt(resultSet.getString("edad"));
            listaPersonas.add(new Persona(nombre,apellidos,edad));
        }

        return listaPersonas;

    }


    // El método initialize (siempre ejecuta desde el inicio)
    @FXML
    private void initialize() throws SQLException {
        personas.addAll(cargarDatos());//carga los datos
        // Ir poniendo las columnas
        // Indicar a JavaFX que dato tiene cada columna
        // Cual atributo se asigna
        tbcNombre.setCellValueFactory(celda -> celda.getValue().nombreProperty());
        tbcApellidos.setCellValueFactory(celda -> celda.getValue().apellidosProperty());
        tbcEdad.setCellValueFactory(celda -> celda.getValue().edadProperty());

        tbvPersonas.setItems(personas);

    }


    public void onGuardarButtonClick(ActionEvent actionEvent) {
        String nombre=txtNombre.getText();
        String apellidos=txtNombre.getText();
        int edad=Integer.parseInt(txtEdad.getText());

        personas.add(new Persona(nombre,apellidos,edad));

    }

    public void onBorrarButtonClick(ActionEvent actionEvent) {
        Persona persona=tbvPersonas.getSelectionModel().getSelectedItem();
        personas.remove(persona);
    }
}
