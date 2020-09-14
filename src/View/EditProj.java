package View;

import Graphics.SidePane;
import Model.PickColor;
import Model.PngEncoder;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Optional;

import static javafx.beans.binding.Bindings.divide;

public class EditProj {


    private static boolean withBorder = false;
    private static Color[] baseColors = null;
    private static PickColor nearColors = null;


    public static void start(Stage primaryStage){
        primaryStage.setFullScreen(true);
        nearColors = new PickColor(baseColors);
        Pane root = new Pane();
        //sideoverlay exists in order to use the alignment property.
        StackPane sideOverlay = new StackPane();
        SidePane sidePane = new SidePane(nearColors);

        //Make a sidepane to edit stuff
        sidePane.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        //make the sidepane always take up a  fourth of the window
        sideOverlay.prefHeightProperty().bind(root.heightProperty());
        sideOverlay.prefWidthProperty().bind(root.widthProperty());
        sidePane.prefHeightProperty().bind(root.heightProperty());
        sidePane.maxWidthProperty().bind(divide(root.widthProperty(),4));

        sideOverlay.setAlignment(sidePane, Pos.CENTER_RIGHT);
        sideOverlay.getChildren().add(sidePane);

        root.getChildren().add(sideOverlay);

        //Placeholder
        VBox vb = new VBox();

        PieChart pc = new PieChart();
        pc.getData().add(new PieChart.Data("Data1",10));
        pc.getData().add(new PieChart.Data("Data2",20));
        pc.setLegendVisible(false);
        nearColors.applyPieChartColorSequence(pc.getData());
        sidePane.setMode(root);
        vb.getChildren().add(pc);
        root.getChildren().add(vb);

        pc.setOnMouseClicked(e->{
            sidePane.setMode(pc);
        });

        CategoryAxis xAxis    = new CategoryAxis();
        xAxis.setLabel("X axis");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Y axis");

        BarChart barChart = new BarChart(xAxis, yAxis);

        XYChart.Series dataSeries1 = new XYChart.Series();
        dataSeries1.getData().add(new XYChart.Data("X1", 100));
        dataSeries1.getData().add(new XYChart.Data("X2", 200));
        dataSeries1.getData().add(new XYChart.Data("X3", 300));
        barChart.getData().add(dataSeries1);
        XYChart.Series dataSeries = new XYChart.Series();
        dataSeries.getData().add(new XYChart.Data("X1", 100));
        dataSeries.getData().add(new XYChart.Data("X4", 100));
        dataSeries.getData().add(new XYChart.Data("X2", 200));
        dataSeries.getData().add(new XYChart.Data("X3", 300));
        barChart.getData().add(dataSeries);

        barChart.setLegendVisible(false);
        vb.getChildren().add(barChart);

        barChart.setOnMouseClicked(e->{
            sidePane.setMode(barChart);
        });

        for(Color c: nearColors.getColors()){
            Rectangle r = new Rectangle();
            r.setHeight(10);
            r.setWidth(10);
            r.setFill(c);
            vb.getChildren().add(r);
        }

        primaryStage.setFullScreen(true);
        primaryStage.setScene(new Scene(root,500,500));
        sidePane.getExportButton().setOnAction(e->{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                root.getChildren().remove(sideOverlay);
                PngEncoder.exportPng(root,"src/Assets/saves/p.png");
                root.getChildren().add(sideOverlay);
            } else {
                alert.close();
            }

        });
        System.out.println(baseColors);
        primaryStage.show();

    }


    public static void setBaseColors(Color[] colors){
        baseColors = colors;
    }
    public static PickColor getNearColors(){return nearColors;}
    public static Color[] getBaseColors() {return baseColors;}
}
