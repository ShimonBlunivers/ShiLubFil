package com.example.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import javafx.scene.media.*;
import java.io.File;

public class HelloController {
    @FXML
    public Button fileButton;
    public TextArea appConsole;

    @FXML
    public Pane myImage;

    protected  void addTextToConsole(String string){
        String consoleText = appConsole.getText();
        String setTextToConsole = consoleText  + string + "\n";
        appConsole.setText(setTextToConsole);

    }

    @FXML
    protected void chooseImage() {
        Window window = this.fileButton.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Image File");

        //Set extension filter
        FileChooser.ExtensionFilter extFilterAll = new FileChooser.ExtensionFilter("All files (*.*)", "*.*");
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterAll, extFilterJPG, extFilterPNG);

        File selectedFile = fileChooser.showOpenDialog(window);

        if (selectedFile != null) {
            String path = selectedFile.toURI().toString();
            String style = "-fx-background-image: url(" + path + ")";
            myImage.setStyle(style);
            addTextToConsole("Image Loaded Succesfully");

        }
    }

    protected  void playMeme(){
        String musicFile = "GUI/src/main/resources/com/example/gui/josh.mp3";     // For example

        Media sound = new Media(new File(musicFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setVolume(50);
        mediaPlayer.play();
    }

    @FXML
    public void initialize() {
        playMeme();    }
}