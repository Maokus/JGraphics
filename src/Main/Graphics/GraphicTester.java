package Main.Graphics;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GraphicTester extends Application {
    public void start(Stage primaryStage){
        Pane root = new Pane();
        VBox vb = new VBox();
        vb.getChildren().add(
                new BarGraph(200,100,180,
                        new Color[]{Color.rgb(255,0,0),Color.rgb(255,125,0)},new double[]{80,20})
        );
        ((BarGraph)vb.getChildren().get(0)).setBorder(Color.rgb(125,0,0),5);
        vb.getChildren().add(new Legend(new Color[]{Color.rgb(255,0,0),Color.rgb(255,125,0)},
                new String[]{"Good people","Bad people"},20));
        root.getChildren().add(vb);

        primaryStage.setScene(new Scene(root,200,400));
        primaryStage.show();
    }

    public static void main(String[] args){
        launch();
    }
}
