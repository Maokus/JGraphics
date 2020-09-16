package Graphics;
/*
This sidepane class allows the user to edit the contents of graphics shown in the project.
When a graphic is clicked, the method setMode is called with the Node clicked.
The appropriate initializer class will then be called to change the contents of the pane.

The current supported graphic classes are:
 * Pie chart
 * Bar Graph (Half implemented)

 the export but
 */
import Model.PickColor;
import View.EditProj;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import org.w3c.dom.Text;

import java.util.ArrayList;

import static javafx.beans.binding.Bindings.divide;

public class SidePane extends Pane {
    LatoButton exportButton = new LatoButton("Export project",15);
    LatoButton closeButton = new LatoButton("Close Side Pane",15);
    PickColor pickColor;

    public SidePane(PickColor pickColor){
        this.pickColor = pickColor;
    }

    public void setMode(Node type){
        getChildren().clear();
        if(type instanceof PieChart) {
            setModePieChart((PieChart) type);
        }else if(type instanceof BarChart){
            setModeBarChart((BarChart) type);
        }
    }

    private void setModePieChart(PieChart currPie){
        GridPane holder = new GridPane();
        Label pane1 = new Label("Labels");
        pane1.setPadding(new Insets(10));
        pane1.setStyle("-fx-background-color: lightgrey;");
        pane1.prefWidthProperty().bind(divide(widthProperty(),2));
        Label pane2 = new Label("Data");
        pane2.setPadding(new Insets(10));
        pane2.setStyle("-fx-background-color: lightgrey;");
        pane2.prefWidthProperty().bind(divide(widthProperty(),2));
        holder.add(pane1,0,0);
        holder.add(pane2,1,0);
        ArrayList<TextField> dataTypes = new ArrayList<TextField>();
        ArrayList<TextField> dataValues = new ArrayList<TextField>();
        for(int i=1; i<currPie.getData().size()+1;i++){
            TextField dataType = new TextField();
            dataType.setText(currPie.getData().get(i-1).getName());
            dataType.prefWidthProperty().bind(divide(widthProperty(),2));
            dataTypes.add(dataType);

            TextField dataValue = new TextField();
            dataValue.setText(String.valueOf(currPie.getData().get(i-1).getPieValue()));
            dataValue.prefWidthProperty().bind(divide(widthProperty(),2));
            dataValues.add(dataValue);
            holder.add(dataType,0,i);
            holder.add(dataValue,1,i);


            dataType.setPadding(new Insets(10));
            dataValue.setPadding(new Insets(10));
            dataType.setStyle("-fx-background-insets: 5px;");
            dataValue.setStyle("-fx-background-insets: 5px;");
        }

        LatoButton confirmButton = new LatoButton("Confirm",15);
        LatoButton addNewRowButton = new LatoButton("Add new row",15);
        LatoButton removeRowButton = new LatoButton("Remove last row",15);
        confirmButton.setAlignment(Pos.CENTER);
        addNewRowButton.setAlignment(Pos.CENTER);
        removeRowButton.setAlignment(Pos.CENTER);

        addNewRowButton.setOnAction(e->{
            getChildren().clear();
            currPie.getData().add(new PieChart.Data("",0));
            setModePieChart(currPie);
        });

        removeRowButton .setOnAction(e->{
            getChildren().clear();
            currPie.getData().remove(currPie.getData().size()-1);
            setModePieChart(currPie);
        });

        confirmButton.setOnAction(e->{
            for(int i=0;i<currPie.getData().size();i++){
                try {
                    currPie.getData().add(new PieChart.Data(dataTypes.get(i).getText(),
                            Double.valueOf(dataValues.get(i).getText())));
                    currPie.getData().remove(0);
                }catch(Exception ex){
                    ex.printStackTrace();
                }
            }
            pickColor.applyPieChartColorSequence(currPie.getData());
        });

        holder.add(addNewRowButton,0,currPie.getData().size()+1);
        holder.add(removeRowButton,0,currPie.getData().size()+2);
        holder.add(confirmButton,1,currPie.getData().size()+1);
        holder.add(exportButton,1,currPie.getData().size()+2);
        holder.setOpacity(0);
        getChildren().add(holder);
        FadeTransition f = new FadeTransition(Duration.seconds(0.5),holder);
        f.setFromValue(0);
        f.setToValue(1);
        f.play();
    }

    private void setModeBarChart(BarChart currBarChart){

        int currInd = 0;
        GridPane holder = new GridPane();
        ArrayList<ArrayList<TextField>> nameseries = new ArrayList<>();
        ArrayList<ArrayList<TextField>> dataseries = new ArrayList<>();
        ArrayList<LatoButton> addRowButtons = new ArrayList<LatoButton>();
        ArrayList<LatoButton> removeRowButtons = new ArrayList<LatoButton>();
        LatoButton confirmButton = new LatoButton("Confirm",15);
        LatoButton exportButton = new LatoButton("Export",15);


        for (int i=0; i<currBarChart.getData().size();i++) {
            nameseries.add(new ArrayList<TextField>());
            dataseries.add(new ArrayList<TextField>());

            Label seriesLabel = new Label("Series "+String.valueOf(i+1));
            seriesLabel.setPadding(new Insets(10));
            seriesLabel.setStyle("-fx-background-color: lightgrey;");
            seriesLabel.prefWidthProperty().bind(divide(widthProperty(),2));
            holder.add(seriesLabel,0,currInd);

            Pane seriesPane = new Pane();
            holder.add(seriesPane,1,currInd);
            seriesPane.setStyle("-fx-background-color: lightgrey;");
            seriesPane.prefWidthProperty().bind(divide(widthProperty(),2));

            currInd += 1;

            for(XYChart.Data xydata: (ObservableList<XYChart.Data>)
                    ((XYChart.Series)
                            currBarChart.getData().get(i)).getData()){
                TextField name = new TextField();
                TextField data = new TextField();
                name.setPadding(new Insets(10));
                data.setPadding(new Insets(10));
                name.setStyle("-fx-background-insets: 5px;");
                data.setStyle("-fx-background-insets: 5px;");
                name.prefWidthProperty().bind(divide(widthProperty(),2));
                data.prefWidthProperty().bind(divide(widthProperty(),2));
                name.setText(xydata.getXValue().toString());
                data.setText(xydata.getYValue().toString());
                nameseries.get(i).add(name);
                dataseries.get(i).add(data);
                holder.add(name, 0, currInd);
                holder.add(data, 1, currInd);
                currInd++;
            }
            LatoButton addRowButton = new LatoButton("Add new row", 14);
            LatoButton removeRowButton = new LatoButton("Remove last row", 14);
            holder.add(addRowButton,0,currInd);
            holder.add(removeRowButton,1,currInd);
            currInd++;
            addRowButtons.add(addRowButton);
            removeRowButtons.add(removeRowButton);

        }
        holder.add(confirmButton,0,currInd);
        holder.add(exportButton,1,currInd);
        currInd += 1;
        getChildren().add(holder);

        //Confirming
        //TODO: check validity of numbers entered in data field
        confirmButton.setOnMouseClicked(e->{
            currBarChart.getData().clear();
            for(int i = 0; i<nameseries.size();i++){
                XYChart.Series currSeries = new XYChart.Series();
                for(int j = 0; j<nameseries.get(i).size();j++) {
                    currSeries.getData().add(new XYChart.Data(nameseries.get(i).get(j).getText(),
                            Integer.parseInt(dataseries.get(i).get(j).getText())));
                }
                currBarChart.getData().add(currSeries);
            }
        });

        FadeTransition f = new FadeTransition(Duration.seconds(0.5),holder);
        f.setFromValue(0);
        f.setToValue(1);
        f.play();
    }

    private void setModePane(Pane currPane){
        VBox vBox = new VBox();
        PickColor pc = EditProj.getNearColors();
        for(int i=0; i<pc.getColors().size();i++){
            Rectangle currRect = new Rectangle();
            vBox.getChildren().add(currRect);
            currRect.widthProperty().bind(widthProperty());
            currRect.setHeight(20);
            currRect.setFill(pc.getColors().get(i));
        }
        getChildren().add(vBox);
        getChildren().add(exportButton);
        getChildren().add(closeButton);
    }
    public LatoButton getExportButton(){
        return exportButton;
    }
    public LatoButton getCloseButton(){
        return closeButton;
    }


}
