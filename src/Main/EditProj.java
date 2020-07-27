package Main;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class EditProj {
    private static boolean withBorder = false;
    private static Color[] baseColors = null;
    public static void start(Stage primaryStage){
        Pane root = new Pane();
        VBox vb = new VBox();
        for(Color color: baseColors){
            Rectangle rect = new Rectangle();
            rect.setHeight(100);
            rect.setWidth(100);
            rect.setFill(color);
            vb.getChildren().add(rect);
        }
        root.getChildren().add(vb);
        primaryStage.setScene(new Scene(root,500,500));
        System.out.println(baseColors);
        primaryStage.show();
    }
    public static void setColor(Color[] colors){
        baseColors = colors;
    }
}
