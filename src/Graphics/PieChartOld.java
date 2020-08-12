package Graphics;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;

import java.util.ArrayList;
import java.util.InputMismatchException;

public class PieChartOld extends Pane implements GraphicOld {
    private ArrayList<Color> colors;
    private ArrayList<Double> lengths;
    private double d;

    public PieChartOld(double diameter, ArrayList<Color> c, ArrayList<Double> lengths){
        //c is an array of colors
        //lengths is an array of doubles of angles each color should take up in degrees
        //diameter is diameter
        this.d = diameter;
        this.colors = c;
        this.lengths = lengths;
        setHeight(diameter);
        setWidth(diameter);
        drawPie();
    }

    private void drawPie(){

        double currAngle = 0;
        final double CENTERX = getWidth()/2;
        final double CENTERY = getHeight()/2;
        final double RADIUS = d/2;

        //if insuff colors/lengths
        if(colors.size()!=lengths.size()){
            throw new InputMismatchException("colors and lengths do not match");
        }
        //draw arcs
        for(int i = 0; i< colors.size(); i++){

            //Make arc
            Arc currArc = new Arc();
            currArc.setCenterY(CENTERY);
            currArc.setCenterX(CENTERX);
            currArc.setRadiusX(RADIUS);
            currArc.setRadiusY(RADIUS);
            currArc.setType(ArcType.ROUND);
            currArc.setStartAngle(currAngle);
            currArc.setLength(lengths.get(i));
            currArc.setFill(colors.get(i));

            //next draw angle
            currAngle+=lengths.get(i);
            //add to the pane
            this.getChildren().add(currArc);
        }
    }

    public void setColors(ArrayList<Color> c){
        getChildren().clear();
        this.colors = c;
        drawPie();
    }
}
