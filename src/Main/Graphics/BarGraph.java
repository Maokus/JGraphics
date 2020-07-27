package Main.Graphics;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

import java.util.InputMismatchException;

public class BarGraph extends Pane implements Graphic{
    private double barWidth;
    private double barHeight;
    private double[] lengths;
    private Color[] colors;



    BarGraph(double width, double height, double barWidth, Color[] colors, double[] lengths){
        this(width,height, barWidth, height/3*2, colors, lengths);
    }

    BarGraph(double width, double height, double barWidth, double barHeight, Color[] colors, double[] lengths){
        this.colors = colors;
        this.lengths = lengths;
        this.barHeight = barHeight;
        this.barWidth = barWidth;
        setMinHeight(height);
        setMinWidth(width);
        setWidth(width);
        setHeight(height);
        drawBar();
    }

    private void drawBar(){
        double currLen = 0;

        if(colors.length!=lengths.length){
            throw new InputMismatchException("colors and lengths do not match");
        }

        for(int i=0; i<colors.length; i++){
            Rectangle segment = new Rectangle();
            segment.setWidth(barWidth*lengths[i]/100);
            segment.setHeight(barHeight);
            segment.setFill(colors[i]);
            getChildren().add(segment);
            segment.setTranslateY((getHeight()-barHeight)/2);
            segment.setTranslateX(currLen);
            currLen += segment.getWidth();
        }
    }

    public void setBorder(Color c,double weight){
        getChildren().clear();
        Rectangle border = new Rectangle();
        border.setStrokeType(StrokeType.OUTSIDE);
        border.setStroke(c);
        border.setStrokeWidth(weight);
        border.setHeight(getBarHeight());
        border.setWidth(getBarWidth());
        border.setTranslateY((getHeight()-barHeight)/2);
        getChildren().add(border);
        drawBar();
    }

    public double getBarWidth() {
        return barWidth;
    }

    public double getBarHeight() {
        return barHeight;
    }

    public void setColors(Color[] colors){
        getChildren().clear();
        this.colors = colors;
        drawBar();
    }
}
