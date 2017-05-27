package sample;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller{
    private Equation equation;
    private AppModel model=null;

    @FXML private static ComboBox amountOfVariables;

    public Controller(AppModel model){
        this.model = model;
        equation = new Equation();
    }
    public Controller(){

    }

    @FXML private void  makeEquation(){
        try {
            equation.display();
        }
        catch(Exception e){
            System.err.print(e);
        }
    }
    @FXML private void sentAmountOfVariables(){
        //model.se
    }
}
