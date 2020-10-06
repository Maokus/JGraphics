package Model;

import javafx.application.Application;
import javafx.collections.*;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.*;
import javafx.scene.*;
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

public class PngEncoder {
    public static void exportPng(Node n) {
        WritableImage image = n.snapshot(new SnapshotParameters(), null);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                FileChooser fileChooser = new FileChooser();
                fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG doc(*.png)", "*.png"));

                File f = fileChooser.showSaveDialog(null);
                if(!f.getName().contains(".")) {
                    f = new File(f.getAbsolutePath() + ".png");
                }
                try {
                    ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", f);
                } catch (IOException e) {
                    // TODO: handle exception here
                }
            }
        });
        thread.run();

    }

}