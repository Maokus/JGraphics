package Graphics;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class LatoButton extends Button {

    private int fontSize;

    public LatoButton(String s,int fontSize){

        super(s);

        setStyle("-fx-background-color: transparent; -fx-font-family: Lato; -fx-font-size: "
                +String.valueOf(fontSize)+";");

        this.fontSize = fontSize;

        //Event handlers to track leaving and entering of mouse to highlight the button.

        this.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setStyle("-fx-background-color: transparent; -fx-font-family: Lato; -fx-font-size: "
                        +String.valueOf(fontSize)+"; -fx-text-fill: "
                        +new Color(0,0.3,0.6,1).toString().replace("0x", "#")
                        +";");
            }
        });

        this.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setStyle("-fx-background-color: transparent; -fx-font-family: Lato; -fx-font-size: "
                        +String.valueOf(fontSize)+"; -fx-text-fill: "
                        +Color.BLACK.toString().replace("0x", "#")
                        +";");
            }
        });

    }
}
