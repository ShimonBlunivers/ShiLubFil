package com.example.gui;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import javafx.scene.media.*;
import javafx.util.Duration;

import java.io.File;

public class HelloController {
    @FXML
    public Button fileButton;
    public TextArea appConsole;
    @FXML
    public ImageView importedImage;
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
                Image image = new Image(path);
                importedImage.setImage(image);
                addTextToConsole("Image Loaded Succesfully");
        }
    }

    protected void filterGray() {
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setSaturation(44); // increase red by 50%
        importedImage.setEffect(colorAdjust);
        FadeTransition fade = new FadeTransition(Duration.seconds(2), importedImage);
        fade.setFromValue(1.0); // fully opaque
        fade.setToValue(0.0); // fully transparentfade.play();
        ColorAdjust grayscale = new ColorAdjust();
        grayscale.setSaturation(-1.0); // fully desaturatedImageView imageView = new ImageView(new Image("path/to/image.png"));
        importedImage.setEffect(grayscale);
    }

    protected  void playMeme(){
        String musicFile = "GUI/src/main/resources/com/example/gui/josh.mp3";     // For ksnapimu to funguje s GUI a lubosovi bez :)

        Media sound = new Media(new File(musicFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
    }



    @FXML
    public void initialize() {
        playMeme();
        importedImage.fitWidthProperty().bind(myImage.widthProperty());
        importedImage.fitHeightProperty().bind(myImage.heightProperty());
    }
}