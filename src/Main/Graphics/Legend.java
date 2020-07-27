package Main.Graphics;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.InputMismatchException;

public class Legend extends Pane implements Graphic{
    private ArrayList<Color> colors;
    private ArrayList<String> datatypes;
    private double size;


    Legend(ArrayList<Color> colors, ArrayList<String> datatypes, double size){
        this.colors = colors;
        this.datatypes = datatypes;
        this.size = size;
        drawLegend();
    }
    private void drawLegend(){
        if(colors.size()!=datatypes.size()){
            throw new InputMismatchException("Datatypes and lengths are of different sizes");
        }
        VBox vBox = new VBox();
        for(int i=0;i<colors.size();i++){
            HBox hBox = new HBox();
            Rectangle color = new Rectangle();
            color.setWidth(size);
            color.setHeight(size);
            Label label = new Label(datatypes.get(i));
            label.setFont(new Font("Arial",size));
            color.setFill(colors.get(i));
            hBox.getChildren().add(color);
            hBox.getChildren().add(label);
            vBox.getChildren().add(hBox);
        }
        getChildren().add(vBox);
    }

    @Override
    public void setColors(ArrayList<Color> colors) {
        this.colors = colors;
        drawLegend();
    }

    public void addType(Color color, double length){
    }
}
