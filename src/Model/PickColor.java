package Model;

import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class PickColor {
    private ArrayList<Color> colors = new ArrayList<Color>();

    private int nextColorInd = 0;

    public PickColor(Color[] colors){
        for(Color c: colors){
            this.colors.add(c.darker());
            this.colors.add(c);
            this.colors.add(c.desaturate());
        }
        nextColorInd = 0;
    }

    public void applyPieChartColorSequence(
            ObservableList<PieChart.Data> pieChartData) {
        for (int i=0; i<pieChartData.size();i++) {
            pieChartData.get(i).getNode().setStyle(
                    "-fx-pie-color: " + getNextColor().toString().replace("0x", "#") + ";" + ";"
            );

        }

    }
    public void applyBarChartColorSequence(
            ObservableList<PieChart.Data> pieChartData) {
        for (int i=0; i<pieChartData.size();i++) {
            pieChartData.get(i).getNode().setStyle(
                    "-fx-pie-color: " + getNextColor().toString().replace("0x", "#") + ";" + ";"
            );

        }

    }

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
