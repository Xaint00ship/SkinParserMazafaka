package com.example.skinsparsermazafaka;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import parser.module.Parser;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("SkinsToolsScene.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Skins tools mazafaka");
        stage.setScene(scene);

        stage.show();
    }





    public static void main(String[] args) {
        launch();
    }
}