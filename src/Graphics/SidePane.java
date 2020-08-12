package Graphics;

import Model.PickColor;
import View.EditProj;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

import static javafx.beans.binding.Bindings.divide;

public class SidePane extends Pane {
    Button exportButton = new Button("Export project");
    Button closeButton = new Button("Close Side Pane");
    public SidePane(){
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
        }

        Button confirmButton = new Button("Confirm");
        Button addNewRowButton = new Button("Add new row");
        Button removeRowButton = new Button("Remove last row");

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
        });

        holder.add(addNewRowButton,0,currPie.getData().size());
        holder.add(removeRowButton,0,currPie.getData().size()+1);
        holder.add(confirmButton,1,currPie.getData().size());
        holder.add(exportButton,1,currPie.getData().size()+1);
        getChildren().add(holder);
    }

    private void setModeBarChart(BarChart currBarChart){
        GridPane holder = new GridPane();
        ArrayList<TextField> names = new ArrayList<TextField>();
        ArrayList<TextField> datas = new ArrayList<TextField>();
        for (int i=0; i<currBarChart.getData().size();i++) {
            TextField name = new TextField();
            TextField data = new TextField();
            names.add(name);
            datas.add(data);
            holder.add(name,0,i);
            holder.add(data,0,i);
        }
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
