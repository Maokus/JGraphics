package Model;

import javafx.application.Application;
import javafx.collections.*;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.chart.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.logging.*;

//Class to save png file.
public class PngEncoder {
    public static void exportPng(Node n,double width, double height) {
        //Make snapshot parameters
        SnapshotParameters s = new SnapshotParameters();

        //set viewport as a rectangle.
        s.setViewport(new Rectangle2D(0,0,width,height));

        //Take snapshot
        WritableImage image = n.snapshot(s, null);

        //choose file location.
        FileChooser fileChooser = new FileChooser();

        //Check file extension
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG doc(*.png)", "*.png"));
        File f = fileChooser.showSaveDialog(null);
        if(!f.getName().contains(".")) {
            f = new File(f.getAbsolutePath() + ".png");
        }

        //Save image
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", f);
        } catch (IOException e) {
            System.out.println("Error exporting to png.");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Error exporting to png.");
            alert.show();
        }

    }

}