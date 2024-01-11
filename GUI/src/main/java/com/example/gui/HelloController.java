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
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.scene.media.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
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

    @FXML
    protected void generateImage() {

        int width = 1024;
        int height = 1024;

        WritableImage image = new WritableImage(width, height);
        PixelWriter pw = image.getPixelWriter();

        Random rn = new Random();

        double r;
        double g;
        double b;

        double modifierX = rn.nextDouble();
        double modifierY = rn.nextDouble();
        double frequenceModifier = rn.nextDouble(20) + 40;

        double modifierR = rn.nextDouble();
        double modifierG = rn.nextDouble();
        double modifierB = rn.nextDouble();

        int type = rn.nextInt(2);

        for (int y = 0; y < height; y++) for (int x = 0; x < width; x++) {

            double modR = modifierR;
            double modG = modifierG;
            double modB = modifierB;

            if (modR < 0.25) modR = rn.nextDouble();
            if (modG < 0.25) modG = rn.nextDouble();
            if (modB < 0.25) modB = rn.nextDouble();

            if (modR < rn.nextDouble() * 0.2 * Math.sin(x - y)) modifierR = rn.nextDouble();
            if (modG < rn.nextDouble() * 0.2 * Math.sin(x - y)) modifierG = rn.nextDouble();
            if (modB < rn.nextDouble() * 0.2 * Math.sin(x - y)) modifierB = rn.nextDouble();

            if (type == 0){
                int i = rn.nextInt(4);
                if (i == 0){
                    r = rn.nextDouble(256) * Math.sin(((x * modifierX) * (y * modifierY+1)) / frequenceModifier) * modR;
                    g = rn.nextDouble(256) * Math.sin(((x * modifierX) * (y * modifierY+1)) / frequenceModifier) * modG;
                    b = rn.nextDouble(256) * Math.sin(((x * modifierX) * (y * modifierY+1)) / frequenceModifier) * modB;
                } else if (i == 1) {
                    r = rn.nextDouble(256) * Math.sin(((x * modifierX) + (y * modifierY+1)) / frequenceModifier) * modR;
                    g = rn.nextDouble(256) * Math.sin(((x * modifierX) + (y * modifierY+1)) / frequenceModifier) * modG;
                    b = rn.nextDouble(256) * Math.sin(((x * modifierX) + (y * modifierY+1)) / frequenceModifier) * modB;

                } else if (i == 2) {
                    r = rn.nextDouble(256) * Math.sin(((x * modifierX) - (y * modifierY+1)) / frequenceModifier) * modR;
                    g = rn.nextDouble(256) * Math.sin(((x * modifierX) - (y * modifierY+1)) / frequenceModifier) * modG;
                    b = rn.nextDouble(256) * Math.sin(((x * modifierX) - (y * modifierY+1)) / frequenceModifier) * modB;

                } else {
                    r = rn.nextDouble(256) * Math.sin(((x * modifierX) / (y * modifierY+1)) / frequenceModifier) * modR;
                    g = rn.nextDouble(256) * Math.sin(((x * modifierX) / (y * modifierY+1)) / frequenceModifier) * modG;
                    b = rn.nextDouble(256) * Math.sin(((x * modifierX) / (y * modifierY+1)) / frequenceModifier) * modB;
                }
            } else if (type == 1) {
                r = rn.nextDouble(256) * Math.sin(((x * modifierX) - (y * modifierY+1)) / frequenceModifier) * modR;
                g = rn.nextDouble(256) * Math.sin(((x * modifierX) - (y * modifierY+1)) / frequenceModifier) * modG;
                b = rn.nextDouble(256) * Math.sin(((x * modifierX) - (y * modifierY+1)) / frequenceModifier) * modB;
            }
            else {

                r = rn.nextDouble(256) * Math.sin((-(x * modifierX) + (y * modifierY+1)) / frequenceModifier) * modR;
                g = rn.nextDouble(256) * Math.sin((-(x * modifierX) + (y * modifierY+1)) / frequenceModifier) * modG;
                b = rn.nextDouble(256) * Math.sin((-(x * modifierX) + (y * modifierY+1)) / frequenceModifier) * modB;
            }
            pw.setColor(x, y, Color.color(Math.abs((r / 256) % 1), Math.abs((g / 256) % 1), Math.abs((b / 256) % 1)));
        }


        originalImage = image;
        editedImage = image;
        importedImage.setImage(image);


        addTextToConsole("Image Generated Succesfully");
        lockButtons(false);
    }
}