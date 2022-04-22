package com.example.form;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class OtherController {

@FXML
public void onVolverButtonClick(ActionEvent actionEvent) throws IOException {
    FormApplication.setRoot("form-view.fxml");

    }
}
