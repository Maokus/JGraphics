package Graphics;

import Model.PickColor;
import View.EditProj;
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

import java.util.ArrayList;

import static javafx.beans.binding.Bindings.divide;

public class SidePane extends Pane {
    Button exportButton = new Button("Export project");
    Button closeButton = new Button("Close Side Pane");
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
        ArrayList<TextField> dataTypes = new ArrayList<TextField>();
        ArrayList<TextField> dataValues = new ArrayList<TextField>();
        for(int i=0; i<currPie.getData().size();i++){
            TextField dataType = new TextField();
            dataType.setText(currPie.getData().get(i).getName());
            dataType.prefWidthProperty().bind(divide(widthProperty(),2));
            dataTypes.add(dataType);

            TextField dataValue = new TextField();
            dataValue.setText(String.valueOf(currPie.getData().get(i).getPieValue()));
            dataValue.prefWidthProperty().bind(divide(widthProperty(),2));
            dataValues.add(dataValue);
            holder.add(dataType,0,i);
            holder.add(dataValue,1,i);


            dataType.setPadding(new Insets(10));
            dataValue.setPadding(new Insets(10));
            dataType.setStyle("-fx-background-insets: 5px;");
            dataValue.setStyle("-fx-background-insets: 5px;");
        }

        Button confirmButton = new Button("Confirm");
        Button addNewRowButton = new Button("Add new row");
        Button removeRowButton = new Button("Remove last row");
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

        holder.add(addNewRowButton,0,currPie.getData().size());
        holder.add(removeRowButton,0,currPie.getData().size()+1);
        holder.add(confirmButton,1,currPie.getData().size());
        holder.add(exportButton,1,currPie.getData().size()+1);
        getChildren().add(holder);
    }

    private void setModeBarChart(BarChart currBarChart){

        int currInd = 0;
        GridPane holder = new GridPane();
        ArrayList<TextField> names = new ArrayList<TextField>();
        ArrayList<TextField> datas = new ArrayList<TextField>();

        for (int i=0; i<currBarChart.getData().size();i++) {

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
                name.prefWidthProperty().bind(divide(widthProperty(),2));
                data.prefWidthProperty().bind(divide(widthProperty(),2));
                name.setText(xydata.getXValue().toString());
                data.setText(xydata.getYValue().toString());
                names.add(name);
                datas.add(data);
                holder.add(name, 0, currInd);
                holder.add(data, 1, currInd);
                currInd++;
            }
        }
        getChildren().add(holder);
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
    public Button getExportButton(){
        return exportButton;
    }
    public Button getCloseButton(){
        return closeButton;
    }


}
