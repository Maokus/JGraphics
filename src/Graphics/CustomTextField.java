package Graphics;

import javafx.geometry.Insets;
import javafx.scene.control.TextField;

public class CustomTextField extends TextField {
    //Custom text field to ensure consistency
    public CustomTextField(){
        setPadding(new Insets(5));
        setStyle("-fx-background-insets: 5px;");
    }
}
