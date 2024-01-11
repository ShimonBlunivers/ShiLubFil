package com.example.gui;

import com.example.gui.Filters.Filter;
import javafx.animation.FadeTransition;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.scene.media.*;
import javafx.util.Duration;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;


public class HelloController {
    @FXML
    public Button fileButton, applyMatrixFilter;
    public TextArea appConsole;
    @FXML
    public ImageView importedImage;
    public static ImageView imageView;
    @FXML
    public Pane myImage;
    @FXML
    public Menu exitButton, aboutButton;
    @FXML
    private Menu filterButton;
    public RadioButton originalImageRadio;
    public ToggleGroup group1;
    public RadioButton editedImageRadio;
    public Button restoreImageButton;
    public Button generateImage;
    public Button editMatrix;

    public Image editedImage, originalImage;

    public static ArrayList<Filter> usedFilters = new ArrayList<>();


    @FXML
    public void aboutWindow() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("about-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("About");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.isAlwaysOnTop();
        addTextToConsole("Opened About");
        stage.show();
    }

    public void addTextToConsole(String string){
        String consoleText = appConsole.getText();
        String setTextToConsole = consoleText  + string + "\n";
        appConsole.setText(setTextToConsole);
    }

    @FXML
    protected void restoreImage() {
        Filter.resetFilters();
        usedFilters = new ArrayList<Filter>();
    }
    @FXML
    protected void viewOriginalImage() {
        Filter.resetFilters();
    }

    @FXML
    protected void viewEditedImage() {
        for (Filter filter : usedFilters) filter.apply();
    }

    @FXML
    protected void chooseImage() {
        Filter.resetFilters();

        Window window = this.fileButton.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Image File");

        //Set extension filter
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

        File selectedFile = fileChooser.showOpenDialog(window);

        if (selectedFile != null) {
                String path = selectedFile.toURI().toString();
                Image image = new Image(path);
                originalImage = image;
                editedImage = image;
                importedImage.setImage(image);
                addTextToConsole("Image Loaded Succesfully");
        }
        lockButtons(false);
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
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
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


    public void onAction()
    {
        final MenuItem menuItem = new MenuItem();
        aboutButton.getItems().add(menuItem);
        aboutButton.addEventHandler(Menu.ON_SHOWN, event -> aboutButton.hide());
        aboutButton.addEventHandler(Menu.ON_SHOWING, event -> aboutButton.fire());
    }

    @FXML
    public void initialize() {
        addTextToConsole("Initialization...");
        playMeme();
        onAction();
        importedImage.fitWidthProperty().bind(myImage.widthProperty());
        importedImage.fitHeightProperty().bind(myImage.heightProperty());

        imageView = importedImage;

        for (Filter filter : Filter.filters) {
            filter.addToMenu(filterButton, this);
        }

        addTextToConsole("Completed...");
    }
}