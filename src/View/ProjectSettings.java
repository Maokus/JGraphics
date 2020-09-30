package View;

import Graphics.CustomTextField;
import Graphics.LatoButton;
import Graphics.LatoLabel;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import javax.print.DocFlavor;

public class ProjectSettings {
    public static void start(Stage primary){
        Stage secStage = new Stage();
        StackPane root = new StackPane();
        Scene scene = new Scene(root,500,400);
        GridPane holder = new GridPane();
        root.getChildren().add(holder);
        CustomTextField widthField = new CustomTextField();
        CustomTextField heightField = new CustomTextField();
        holder.add(new LatoLabel("Width: ",15),0,0);
        holder.add(widthField,1,0);
        holder.add(new LatoLabel("Height: ",15),0,1);
        holder.add(heightField,1,1);

        LatoButton cancelButton = new LatoButton("Cancel",20);
        LatoButton confirmButton = new LatoButton("Confirm",20);
        root.getChildren().add(cancelButton);
        root.getChildren().add(confirmButton);
        root.setAlignment(cancelButton,Pos.BOTTOM_LEFT);
        root.setAlignment(confirmButton,Pos.BOTTOM_RIGHT);
        root.setAlignment(holder,Pos.CENTER);
        holder.setAlignment(Pos.CENTER);
        cancelButton.setOnMouseClicked(e->{
            secStage.close();
        });
        confirmButton.setOnMouseClicked(e->{
            EditProj.start(primary,Integer.valueOf(widthField.getText()), Integer.valueOf(heightField.getText()));
            secStage.close();
        });
        secStage.setScene(scene);
        secStage.show();

    }
}
