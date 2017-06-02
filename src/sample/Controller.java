package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

import java.net.URL;
import java.util.*;

public class Controller implements Initializable{
    private Equation equation;
    private AppModel model;

    @FXML private ComboBox amountOfVariables;
    @FXML private ComboBox amountOfRows;
    @FXML private ComboBox xValues;
    @FXML private ListView valuesForX;

    public Controller(AppModel model) {
        this.model = model;
        equation = new Equation();
    }

    public Controller() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Integer> items = FXCollections.observableArrayList (0,1,2,3,4,5,6,7,8,9);
        amountOfRows.getSelectionModel().selectFirst();
        amountOfVariables.getSelectionModel().selectFirst();
        valuesForX.setItems(items);
        valuesForX.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        valuesForX.getSelectionModel().selectRange(0,2);
    }

    @FXML
    private void makeEquation() {
        model.setArrayWithValuesOfX(valuesForX.getSelectionModel().getSelectedItems());
        model.setAmountOfVariables(Integer.valueOf(amountOfVariables.getSelectionModel().getSelectedItem().toString()));
        model.setAmountOfRows(Integer.valueOf(amountOfRows.getSelectionModel().getSelectedItem().toString()));

        try {
            equation.display(model);
        } catch (Exception e) {
            System.err.print(e);
        }
    }
}
