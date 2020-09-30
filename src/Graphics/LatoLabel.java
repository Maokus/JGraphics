package Graphics;


import javafx.scene.control.Label;

public class LatoLabel extends Label {
    public LatoLabel(String s, int fontSize){
        super(s);
        setStyle(" -fx-font-family: Lato; -fx-font-size: "+fontSize+";");
    }
}
