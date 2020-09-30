package View;

import Graphics.LatoButton;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class UserChooseColor {

    private static int getType(String type){
        switch (type){
            case "Analogous":
                return 0;
            case "Complementary":
                return 1;
            case "Triad":
                return 2;
            case "Tetradic":
                return 3;
            case "Split_Complementary":
                return 4;
            case "Quadrilateral":
                return 5;
            default:
                throw new InputMismatchException("Type does not exist");
        }
    }

    public static void start(){
        Stage secondaryStage = new Stage();
        StackPane root = new StackPane();
        root.setStyle("-fx-background-color: white;");
        Button cancelButton = new LatoButton("Cancel",20);
        Button confirmButton = new LatoButton("Confirm",20);
        cancelButton.setPadding(new Insets(20));
        confirmButton.setPadding(new Insets(20));

        //color circle
        final double RADIUS = 100;
        Pane[] arcs = {null,null,null,null,null,null,null,null,null,null,null,null};
        Color[] colors = {Color.color(1,0,0),
                Color.color(1,0.5,0),
                Color.color(1,1,0),
                Color.color(0.5,1,0),
                Color.color(0,1,0),
                Color.color(0,1,0.5),
                Color.color(0,1,1),
                Color.color(0,0.5,1),
                Color.color(0,0,1),
                Color.color(0.5,0,1),
                Color.color(1,0,1),
                Color.color(1,0,0.5)};
        for(int i=0; i<12; i++){
            arcs[i] = new Pane(new Arc(0,0,RADIUS,RADIUS,75-(30*i),30));
            Arc currArc = (Arc) arcs[i].getChildren().get(0);
            currArc.setType(ArcType.ROUND);
            currArc.setFill(colors[i]);
            root.getChildren().add(arcs[i]);
            arcs[i].setMaxSize(RADIUS*2,RADIUS*2);
            arcs[i].setPrefSize(RADIUS*2,RADIUS*2);
            currArc.setCenterX(arcs[i].getWidth()/2);
            currArc.setCenterY(arcs[i].getHeight()/2);
            currArc.setTranslateX(RADIUS);
            currArc.setTranslateY(RADIUS);
        }

        //Choose colorselect type
        ComboBox typeChoice = new ComboBox();
        typeChoice.getItems().add("Analogous");
        typeChoice.getItems().add("Complementary");
        typeChoice.getItems().add("Triad");
        typeChoice.getItems().add("Tetradic");
        typeChoice.getItems().add("Split_Complementary");
        typeChoice.getItems().add("Quadrilateral");
        typeChoice.setValue("Complementary");
        typeChoice.setPrefSize(150,20);
        typeChoice.setStyle("-fx-padding: 10px;-fx-border-insets: 10px;-fx-background-insets: 10px;");
        root.getChildren().add(typeChoice);
        root.setAlignment(typeChoice,Pos.BOTTOM_CENTER);
        typeChoice.setTranslateY(-20);

        //Colorselect graphics
        final int[] currentRotation = new int[1];
        Slider slider = new Slider();
        slider.setPadding(new Insets(30));
        slider.setPrefWidth(root.getWidth()/2);
        slider.setMin(0);
        slider.setMax(11);
        slider.setValue(0);
        slider.setShowTickLabels(false);
        slider.setShowTickMarks(false);

        //Actual selections for each type
        int[][] selections = {{3,4,5},{0,6},{2,7,10},{0,2,6,8},{1,6,8},{2,5,8,11}};



        root.getChildren().add(slider);
        root.setAlignment(slider, Pos.TOP_CENTER);
        ImageView outline = new ImageView(new Image(Paths.get("src/Assets/ColorSelect/"+typeChoice.getValue()+".png").toUri().toString()));
        outline.setRotate(-15);
        root.getChildren().add(outline);
        root.setAlignment(outline,Pos.CENTER);
        typeChoice.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                outline.setImage(new Image(Paths.get("src/Assets/ColorSelect/"+typeChoice.getValue()+".png").toUri().toString()));
            }
        });

        //smooth dragging
        slider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                outline.setRotate(15+(30*(t1.doubleValue()-1)));
            }
        });
        //snap dragging
        slider.valueChangingProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> source, Boolean oldValue, Boolean newValue) {
                currentRotation[0] = (int)Math.round(slider.getValue());
                outline.setRotate(15+(30*(currentRotation[0]-1)));
                slider.setValue(currentRotation[0]);
            } });

        confirmButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                int[] type = selections[getType((String)typeChoice.getValue())];
                ArrayList<Color> color = new ArrayList<Color>();
                for(int i:type){
                    int currc = i+currentRotation[0];
                    if(currc>11){
                        currc-=12;
                    }
                    color.add(colors[currc]);
                }
                Color color1[] = new Color[color.size()];
                color1 = color.toArray(color1);
                EditProj.setBaseColors(color1);
                secondaryStage.close();
            }
        });
        cancelButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                secondaryStage.close();
            }
        });
        root.getChildren().addAll(cancelButton,confirmButton);
        root.setAlignment(cancelButton, Pos.BOTTOM_LEFT);
        root.setAlignment(confirmButton, Pos.BOTTOM_RIGHT);
        secondaryStage.setScene(new Scene(root, 500,400));
        secondaryStage.show();
    }
}
