package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by BusinessPC on 5/27/2017.
 */
public class EquationController implements Initializable{
    private AppModel model;
    @FXML private VBox content;

    public EquationController(AppModel model){
        this.model = model;
    }
    public EquationController(){

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        for(int i=0;i<model.getAmountOfRows();i++){
            HBox constraint = new HBox();
            constraint.setSpacing(5);
            constraint.setAlignment(Pos.CENTER);
            constraint.setPadding(new Insets(10,0,10,0));

            for (int j = 0; j < model.getAmountOfVariables(); j++) {
                Label label = new Label("X"+(j+1));
                TextField tf = new TextField();
                constraint.getChildren().addAll(label,tf);
            }
            content.getChildren().add(constraint);

        }

    }
}
