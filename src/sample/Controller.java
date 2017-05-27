package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
//import controlsfx.control.CheckComboBox;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller{
    private Equation equation;
    private AppModel model;

    @FXML private ComboBox amountOfVariables;
    @FXML private ComboBox amountOfRows;
    @FXML private ComboBox xValues;

    public Controller(AppModel model){
        this.model = model;
        equation = new Equation();
    }
    public Controller(){

    }

    @FXML private void  makeEquation(){
        try {
            equation.display(model);
        }
        catch(Exception e){
            System.err.print(e);
        }
    }
    @FXML private void sentAmountOfVariables(){
        model.setAmountOfVariables(Integer.valueOf(amountOfVariables.getSelectionModel().getSelectedItem().toString()));
    }
    @FXML private void sentAmountOfRows(){
        model.setAmountOfRows(Integer.valueOf(amountOfRows.getSelectionModel().getSelectedItem().toString()));
    }
    @FXML private void pushXValues(){
        //model.setArrayWithValuesOfX(xValues.getSelectionModel().get);
    }
}
