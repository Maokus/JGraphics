package Graphics;

import Model.PngEncoder;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GraphicTester extends Application {
    public void start(Stage primaryStage){
        PieChart pc = new PieChart();
        pc.getData().add(new PieChart.Data("H",1));
        Pane p = new Pane();
        p.getChildren().add(pc);


        primaryStage.setScene(new Scene(p,1000,1000));

        PngEncoder.exportPng(p,"src/Assets/p.png");
        primaryStage.show();
    }

    public static void main(String[] args){
        launch();
    }
}
