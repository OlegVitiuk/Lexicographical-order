package sample;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;

import java.util.ArrayList;

/**
 * Created by BusinessPC on 5/27/2017.
 */
public class AppModel {
    private final IntegerProperty amountOfVariables  = new SimpleIntegerProperty();
    private final IntegerProperty amountOfRows  = new SimpleIntegerProperty();
    private final ArrayList<Integer> arrayWithValuesOfX  = new ArrayList<Integer>();


    public IntegerProperty amountOfVariablesProperty(){
        return amountOfVariables;
    }
    public IntegerProperty amountOfRowsProperty(){
        return amountOfRows;
    }


    public final Integer getAmountOfVariables(){
        return amountOfVariablesProperty().get();
    }
    public final void setAmountOfVariables(Integer amountOfVariables){
        amountOfVariablesProperty().set(amountOfVariables);
    }
    public final Integer getAmountOfRows(){
        return amountOfRowsProperty().get();
    }
    public final void setAmountOfRows(Integer amountOfRows){
        amountOfRowsProperty().set(amountOfRows);
    }

    public final ArrayList getArrayWithValuesOfX(){
        return arrayWithValuesOfX;
    }
    public final void setArrayWithValuesOfX(ObservableList<Integer> value){
        this.arrayWithValuesOfX.addAll(value);
    }
}
