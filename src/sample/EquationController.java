package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by BusinessPC on 5/27/2017.
 */
public class EquationController{
    private AppModel model;

    public EquationController(AppModel model){
        this.model = model;
        System.out.print(model.getAmountOfVariables());
    }
    public EquationController(){

    }



}
