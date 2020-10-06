package View;

import Graphics.ProjectPane;
import Graphics.SidePane;
import Model.Draggable;
import Model.UserColorPalatte;
import Model.PngEncoder;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.Optional;

import static javafx.beans.binding.Bindings.*;

public class EditProj {


    private static boolean withBorder = false;
    private static Color[] baseColors = null;
    private static UserColorPalatte nearColors = null;
    private static SidePane sidePane = new SidePane(null);


    public static void start(Stage primaryStage,int projectWidth, int projectHeight){
        nearColors = new UserColorPalatte(baseColors);
        Pane root = new Pane();
        //sideoverlay exists in order to use the alignment property.
        StackPane sideOverlay = new StackPane();

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

        ScrollPane scrollPane = new ScrollPane();
        ProjectPane projectPane = new ProjectPane(projectWidth,projectHeight);
        scrollPane.setContent(projectPane);
        scrollPane.prefWidthProperty().bind(multiply(divide(root.widthProperty(),4),3));
        scrollPane.prefHeightProperty().bind(root.heightProperty());

        root.getChildren().add(scrollPane);

        /* Temporary bar chart for debugging purposes.
        CategoryAxis xAxis    = new CategoryAxis();
        xAxis.setLabel("X axis");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Y axis");

        BarChart barChart = new BarChart(xAxis, yAxis);

        barChart.setAnimated(false);

        XYChart.Series dataSeries1 =  new XYChart.Series();
        dataSeries1.getData().add(new XYChart.Data("X1", 100));
        dataSeries1.getData().add(new XYChart.Data("X2", 200));
        dataSeries1.getData().add(new XYChart.Data("X3", 300));
        dataSeries1.getData().add(new XYChart.Data("X4", 0));
        barChart.getData().add(dataSeries1);
        XYChart.Series dataSeries = new XYChart.Series();
        dataSeries.getData().add(new XYChart.Data("X1", 100));
        dataSeries.getData().add(new XYChart.Data("X4", 100));
        dataSeries.getData().add(new XYChart.Data("X2", 200));
        dataSeries.getData().add(new XYChart.Data("X3", 300));
        barChart.getData().add(dataSeries);
        Draggable.Nature barN = new Draggable.Nature(barChart);

        barChart.setLegendVisible(false);
        projectPane.getChildren().add(barChart);

        barChart.setOnMouseClicked(e->{
            scrollPane.prefWidthProperty().bind(multiply(divide(root.widthProperty(),4),3));
            sidePane.setMode(barChart);
        });

         */

        primaryStage.setFullScreen(true);
        primaryStage.setScene(new Scene(root,500,500));

        sidePane.getExportButton().setOnAction(e->{
            PngEncoder.exportPng(projectPane);

        });
        sidePane.getCloseButton().setOnMouseClicked(e-> {
                sidePane.setManaged(false);
                scrollPane.prefWidthProperty().bind(root.widthProperty());
            }
        );

        sidePane.getAddPieChartButton().setOnMouseClicked(e->{
            PieChart newPieChart = new PieChart();
            newPieChart.setLegendVisible(false);
            Draggable.Nature newPieNature = new Draggable.Nature(newPieChart);
            nearColors.applyPieChartColorSequence(newPieChart.getData());
            projectPane.getChildren().add(newPieChart);
            newPieChart.getData().add(new PieChart.Data("Data1",10));
            newPieChart.setOnMouseClicked(event1->{
                scrollPane.prefWidthProperty().bind(subtract(root.widthProperty(),sidePane.widthProperty()));
                sidePane.setMode(newPieChart);
            });
        });

        sidePane.getProjectSettingsButton().setOnMouseClicked(e->{
            sidePane.setMode(projectPane);
        });

        sidePane.getDeleteButton().setOnMouseClicked(e->{
            projectPane.getChildren().remove(sidePane.getSelectedItem());
            sidePane.setMode(projectPane);
        });

        System.out.println(baseColors);
        primaryStage.show();
        sidePane.setMode(projectPane);
        primaryStage.setFullScreen(true);

    }


    public static void setBaseColors(Color[] colors){
        baseColors = colors;
        nearColors = new UserColorPalatte(baseColors);
        sidePane.setPickColor(nearColors);
    }
    public static UserColorPalatte getNearColors(){return nearColors;}
    public static Color[] getBaseColors() {return baseColors;}
}
