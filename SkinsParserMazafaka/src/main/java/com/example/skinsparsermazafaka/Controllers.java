package com.example.skinsparsermazafaka;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import parser.module.Parser;

import java.io.File;

public class Controllers {
    String directory = "";
    String game = "";
    @FXML
    private Label directoryField;
    @FXML
    private Label selectGame;
    @FXML
    public static ProgressBar progressBarLootFarm;
    @FXML
    public TextArea textArea;
    @FXML
    public ProgressBar progressBarMarket;

    @FXML
    public ProgressBar progressBarTM;


    @FXML
    protected void buttonSelectDOTA() {
        selectGame.setText("DOTA 2");
        selectGame.setStyle("-fx-text-fill: black;");
        this.game = "DOTA";
    }

    @FXML
    protected void buttonSelectCSGO() {

        selectGame.setText("CS GO");
        selectGame.setStyle("-fx-text-fill: black;");
        this.game = "CS";
    }

    @FXML
    protected void buttonSelectDirectory() {

            Stage stage = new Stage();
            DirectoryChooser directoryChooser = new DirectoryChooser();
            File selectedDirectory = directoryChooser.showDialog(stage);
            this.directory = selectedDirectory.getAbsolutePath();
            directoryField.setText(selectedDirectory.getAbsolutePath());
    }

    @FXML
    protected void buttonGoParsing() {

        if(this.game.equals("DOTA") || this.game.equals("CS") ){
            Parser parser = new Parser();
            parser.goParsing(this.directory, this.game);
        }
        else{
            selectGame.setStyle("-fx-text-fill: red;");
        }


        if(this.directory.equals(null)){
        }
        else{
            selectGame.setStyle("-fx-text-fill: red;");
        }

    }





}