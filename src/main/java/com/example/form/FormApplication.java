package com.example.form;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class FormApplication extends Application {
    //Escena
    private static Scene scene;



    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(FormApplication.class.getResource("form-view.fxml"));
        scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(FormApplication.class.getResource(fxml));
        scene.setRoot(fxmlLoader.load());
    }


    public static void main(String[] args) {
        launch();
    }
}