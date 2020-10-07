package Graphics;

//A pane class that can be persisted across program usages. Undone.

import javafx.scene.Node;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.io.Serializable;
import java.util.ArrayList;

public class ProjectPane extends Pane {
    //Project class for simplification.
    public ProjectPane(){
        super();
        this.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
    }
    public ProjectPane(int w, int h){
        super();
        this.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        this.setMaxHeight(h);
        this.setMaxWidth(w);
        this.setMinHeight(h);
        this.setMinWidth(w);
    }
}
