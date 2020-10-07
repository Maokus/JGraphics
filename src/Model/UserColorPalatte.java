package Model;

import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.paint.Color;

import java.util.ArrayList;

//Class to save color data
public class UserColorPalatte {

    //Colors
    private ArrayList<Color> colors = new ArrayList<Color>();

    //Save the last used color so that repeated colors dont occur.
    private int nextColorInd = 0;

    //Populate the colors list based on the selected colors.
    public UserColorPalatte(Color[] colors){
        for(Color c: colors){
            //Add darker color
            this.colors.add(c.darker());
            //add color
            this.colors.add(c);
            //add lighter color
            this.colors.add(c.desaturate());
        }
        nextColorInd = 0;
    }

    //Apply the colors to a pie chart.
    public void applyPieChartColorSequence(
            ObservableList<PieChart.Data> pieChartData) {
        for (int i=0; i<pieChartData.size();i++) {
            pieChartData.get(i).getNode().setStyle(
                    "-fx-pie-color: " + getNextColor().toString().replace("0x", "#") + ";" + ";"
            );

        }

    }

    /* UNIMPLEMENTED
    public void applyBarChartColorSequence(
            ObservableList<PieChart.Data> pieChartData) {
        for (int i=0; i<pieChartData.size();i++) {
            pieChartData.get(i).getNode().setStyle(
                    "-fx-pie-color: " + getNextColor().toString().replace("0x", "#") + ";" + ";"
            );

        }

    }
    */

    //Self explanatory
    private Color getNextColor(){
        System.out.println(nextColorInd);
        Color nextColor = colors.get(nextColorInd);
        nextColorInd += 3;
        if(nextColorInd >= colors.size()){
            nextColorInd++;
            nextColorInd %= 3;
        }
        return  nextColor;
    }

    public ArrayList<Color> getColors(){
        return colors;
    }


}
