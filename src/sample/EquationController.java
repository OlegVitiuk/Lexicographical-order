package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
    @FXML private Button solve;
    private int[][] parameters;
    private String current;


    public EquationController(AppModel model){
        this.model = model;
        parameters = new int[model.getAmountOfRows()][model.getAmountOfVariables()];
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
            constraint.setId(""+i);

            for (int j = 0; j < model.getAmountOfVariables(); j++) {
                Label label = new Label("X"+(j+1));
                TextField tf = new TextField();
                tf.setId(""+i+j);
                constraint.getChildren().addAll(label,tf);
            }
            ComboBox checkConstraint = new ComboBox();
            checkConstraint.getItems().addAll("<=","=",">=");
            TextField tf = new TextField();
            constraint.getChildren().addAll(checkConstraint,tf);
            content.getChildren().add(constraint);
        }

    }

    private void setParameters(){
        HBox currentHBox = new HBox();
        for (int i=0;i<model.getAmountOfRows();i++) {
            int amount = 0;
            currentHBox = (HBox) content.lookup("#" + i);
            for (Node node : currentHBox.getChildren()) {
                if (node instanceof TextField && amount<currentHBox.getChildren().size()-2) {
                    parameters[Character.getNumericValue(node.getId().charAt(0))][Character.getNumericValue(node.getId().charAt(1))]
                            = Integer.parseInt(((TextField) node).getText());
                }
                amount++;

            }
        }
    }

    private void setConditions(){

    }

    private void setZParams(){

    }
    private void setComparingActions(){

    }

    @FXML private void solveIt(){
        setParameters();

    }

}
