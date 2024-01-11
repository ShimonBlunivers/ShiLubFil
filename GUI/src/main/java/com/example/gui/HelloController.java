package com.example.gui;

import com.example.gui.Filters.Filter;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.scene.media.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;


public class HelloController {
    @FXML
    public Button fileButton;
    public TextArea appConsole;
    public ImageView importedImage;
    public static ImageView imageView;
    public Pane myImage;
    public Menu filterButton;
    public RadioButton originalImageRadio;
    public RadioButton editedImageRadio;
    public Button restoreImageButton;
    public static Image editedImage, originalImage;


    @FXML
    public void aboutWindow() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("about-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("About");
        stage.setResizable(false);
        stage.setScene(scene);
        addTextToConsole("Opened About");
        stage.show();
    }

    public void addTextToConsole(String string) {
        String consoleText = appConsole.getText();
        String setTextToConsole = consoleText + string + "\n";
        appConsole.setText(setTextToConsole);
        appConsole.setScrollTop(Double.MAX_VALUE); //this will scroll to the bottom

    }

    @FXML
    protected void restoreImage() {
        editedImage = originalImage;
        imageView.setImage(editedImage);
        originalImageRadio.setSelected(true);
    }
    @FXML
    protected void viewOriginalImage() {
        imageView.setImage(originalImage);
    }

    @FXML
    protected void viewEditedImage() {
        imageView.setImage(editedImage);
    }

    @FXML
    protected void chooseImage() {
        Window window = this.fileButton.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Image File");

        //Set extension filter
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("Image files (*.png *.jpg)", "*.PNG","*.JPG");
        fileChooser.getExtensionFilters().addAll(extFilterPNG);

        File selectedFile = fileChooser.showOpenDialog(window);

        if (selectedFile != null) {
            String path = selectedFile.toURI().toString();
            Image image = new Image(path);
            originalImage = image;
            editedImage = image;
            importedImage.setImage(image);
            addTextToConsole("Image Loaded Succesfully");
            lockButtons(false);
        }
    }

    public void lockButtons(boolean state) {
        restoreImageButton.setDisable(state);
        originalImageRadio.setDisable(state);
        editedImageRadio.setDisable(state);
        filterButton.setDisable(state);
    }
    @FXML
    public void saveCurrentImage() throws IOException {
        Window window = this.fileButton.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showSaveDialog(window);

        if (file != null) {
            String fileName = file.getName();
            if (!fileName.toUpperCase().endsWith(".PNG")) {
                file = new File(file.getAbsolutePath() + ".png");
            }

            WritableImage img =  importedImage.snapshot(new SnapshotParameters(), null);
            BufferedImage image = SwingFXUtils.fromFXImage(img, null);
            ImageIO.write(image, "png", file);

            addTextToConsole("Succesfuly saved");
        }
    }


    protected void playMeme(){
        String musicFile = "GUI/src/main/resources/com/example/gui/josh.mp3";     // For ksnapimu to funguje s GUI a lubosovi bez :)

        Media sound = new Media(new File(musicFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
        addTextToConsole("Meme Played");

    }

    @FXML
    public void initialize() {
        addTextToConsole("Initialization...");
        playMeme();
        importedImage.fitWidthProperty().bind(myImage.widthProperty());
        importedImage.fitHeightProperty().bind(myImage.heightProperty());
        imageView = importedImage;

        for (Filter filter : Filter.filters) {
            filter.addToMenu(filterButton, this);
        }

        addTextToConsole("Completed...");
    }
}