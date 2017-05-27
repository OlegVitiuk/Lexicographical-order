package sample;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * Created by BusinessPC on 5/27/2017.
 */
public class AppModel {
    private final IntegerProperty amountOfVariables  = new SimpleIntegerProperty();

    public IntegerProperty amountOfVariablesProperty(){
        return amountOfVariables;
    }
    public final Integer getAmountOfVariables(){
        return amountOfVariablesProperty().get();
    }
    public final void setAmountOfVariables(Integer amountOfVariables){
        amountOfVariablesProperty().set(amountOfVariables);
    }

}
