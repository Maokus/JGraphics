package Model;

import javafx.scene.paint.Color;

import java.util.ArrayList;

public class PickColor {
    private ArrayList<Color> colors = new ArrayList<Color>();
    public PickColor(Color[] colors){
        for(Color c: colors){
            this.colors.add(c.darker());
            this.colors.add(c);
            this.colors.add(c.desaturate());
        }
    }
    public ArrayList<Color> getColors(){
        return colors;
    }
}
