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
import javafx.stage.Stage;
import javafx.scene.chart.*;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.logging.*;

public class PngEncoder {
    public static void exportPng(Node n, String p) {
        WritableImage image = n.snapshot(new SnapshotParameters(), null);

        // TODO: probably use a file chooser here
        File file = new File(p);

        try {
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
        } catch (IOException e) {
            // TODO: handle exception here
        }
    }

}