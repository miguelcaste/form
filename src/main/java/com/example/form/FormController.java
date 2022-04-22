package com.example.form;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;

public class FormController {

    ObservableList<String> lista = FXCollections.observableArrayList("Miguel");



    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtApellidos;

    @FXML
    private ListView lstAlumnos;

    @FXML
    protected void onGuardarButtonClick() throws IOException {
//        FormApplication.setRoot("other-view.fxml");
        lstAlumnos.getItems().add(txtNombre.getText());
    }
    @FXML
    public void onBorrarButtonClick(ActionEvent actionEvent) {
        Object objeto=lstAlumnos.getSelectionModel().getSelectedItem();
        lstAlumnos.getItems().remove(objeto);


    }
}