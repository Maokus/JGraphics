package Main.Graphics;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class SidePane extends Pane {

    public SidePane(){

    }
    public SidePane(double width){
        this.setWidth(width);
    }
    public void setMode(Node type){
        if(type instanceof PieChart) {
            setModePieChart((PieChart) type);
        }else if(type instanceof BarGraph) {
            setModeBarGraph((BarGraph) type);
        }else if(type instanceof Legend){
                setModeLegend((Legend) type);
        }
    }
    private void setModePieChart(PieChart currPie){

    }
    private void setModeBarGraph(BarGraph currBar){

    }
    private void setModeLegend(Legend currLegend){

    }


}
