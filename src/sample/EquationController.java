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
    @FXML private HBox zFunction;
    private int[][] parameters;
    private int[] conditions;
    private int[] zParams;
    private int ZConstraint;
    private String comparingActions;


    public EquationController(AppModel model){
        this.model = model;
        parameters = new int[model.getAmountOfRows()][model.getAmountOfVariables()];
        conditions = new int[model.getAmountOfRows()];
        zParams = new int[model.getAmountOfVariables()];
        comparingActions="";
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

                if(i==0) {
                    //add zFunction blocks
                    Label labelZ = new Label("X" + (j + 1));
                    TextField tfZ = new TextField();
                    tfZ.setId("z" + j);
                    zFunction.setAlignment(Pos.CENTER);
                    zFunction.getChildren().addAll(labelZ,tfZ);
                    if (j >= model.getAmountOfVariables() - 1) {
                        Label labelArrow = new Label("-->");
                        ComboBox checkMinOrMax = new ComboBox();
                        checkMinOrMax.getItems().addAll("max", "min");
                        checkMinOrMax.setId("checkMinMax");
                        zFunction.getChildren().addAll(labelArrow, checkMinOrMax);
                    }
                }
            }
            ComboBox checkConstraint = new ComboBox();
            checkConstraint.getItems().addAll("<=","=",">=");
            checkConstraint.setId("check"+i);
            TextField tf = new TextField();
            tf.setId("c"+i);
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
                    else if(node instanceof TextField && amount>=currentHBox.getChildren().size()-2){
                        conditions[Character.getNumericValue(node.getId().charAt(1))]
                                = Integer.parseInt(((TextField) node).getText());
                    }
                    amount++;
                }
        }
        //set zParams
        for (Node node : zFunction.getChildren()) {
            if (node instanceof TextField) {
                zParams[Character.getNumericValue(node.getId().charAt(1))]
                        = Integer.parseInt(((TextField) node).getText());
            }
        }
        //setToMaxOrMin
        ComboBox checkMaxMin = (ComboBox) zFunction.lookup("#checkMinMax");
        if(checkMaxMin.getSelectionModel().getSelectedIndex()==0) {
            ZConstraint = -9999;
        }
        else ZConstraint = -9999;

    }

    private void setComparingActions(){
        ComboBox checkMaxMin;

        for (int i=0;i<model.getAmountOfRows();i++) {
            checkMaxMin = (ComboBox) content.lookup("#check"+i);
            comparingActions +=checkMaxMin.getSelectionModel().getSelectedItem().toString();
        }
    }

    @FXML private void solveIt(){
        setParameters();
        setComparingActions();

        
    }

}
