package Main.Graphics;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;

import java.util.InputMismatchException;

public class PieChart extends Pane{
    private Color[] colors;
    private double[] lengths;
    private double d;

    public PieChart(double diameter, Color[] c, double[] lengths){
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
        if(colors.length!=lengths.length){
            throw new InputMismatchException("colors and lengths do not match");
        }
        //draw arcs
        for(int i = 0; i< colors.length; i++){

            //Make arc
            Arc currArc = new Arc();
            currArc.setCenterY(CENTERY);
            currArc.setCenterX(CENTERX);
            currArc.setRadiusX(RADIUS);
            currArc.setRadiusY(RADIUS);
            currArc.setType(ArcType.ROUND);
            currArc.setStartAngle(currAngle);
            currArc.setLength(lengths[i]);
            currArc.setFill(colors[i]);

            //next draw angle
            currAngle+=lengths[i];
            //add to the pane
            this.getChildren().add(currArc);
        }
    }

    public void setColors(Color[] c){
        getChildren().clear();
        this.colors = c;
        drawPie();
    }
}
