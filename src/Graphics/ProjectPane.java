package Graphics;

//A pane class that can be persisted across program usages. Undone.

import javafx.scene.Node;
import javafx.scene.layout.Pane;

import java.io.Serializable;
import java.util.ArrayList;

public class ProjectPane extends Pane {
    public ProjectPane(){
        super();
    }
    public ProjectPane(int w, int h){
        super();
        this.setMaxHeight(h);
        this.setMaxWidth(w);
        this.setMinHeight(h);
        this.setMinWidth(w);
    }
}
