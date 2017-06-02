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
import java.util.ArrayList;
import java.util.Arrays;
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
    private int []correctStreamling;
    private boolean toMin;
    private String values;


    public EquationController(AppModel model){
        this.model = model;
        parameters = new int[model.getAmountOfRows()][model.getAmountOfVariables()];
        conditions = new int[model.getAmountOfRows()];
        zParams = new int[model.getAmountOfVariables()];
        comparingActions="";
        values ="";
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
            toMin=false;
        }
        else {
            ZConstraint = 9999;
            toMin=  true;
        }

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
        //setComparingActions();

        char[] chars = new char[model.getAmountOfX()];
        for(int i = 0; i < model.getAmountOfX(); i++) {
            chars[i] = model.getArrayWithValuesOfX().get(i).toString().charAt(0);
        }
        generate(chars, "", model.getAmountOfVariables());

        //get rezults
        System.out.println(values+" "+ZConstraint);
    }

    private void generate(char[] alphabet, String current, int length) {
        if (length == 0) {
            checkConstraint(current);
        } else {
            for (int i = 0; i < alphabet.length; i++) {
                generate(alphabet, current + alphabet[i], length - 1);
            }
        }

    }

    private int[] findStreamlining(int[] arr) {

        ArrayList<Integer> rez = new ArrayList<>();
        int[] copy = Arrays.copyOf(arr, arr.length);
        Arrays.sort(copy);

        for (int i = arr.length-1; i >=0; i--) {
            for (int j =arr.length-1; j>=0; j--) {
                if (copy[i] == arr[j] && !rez.contains(j + 1)) {
                    rez.add(j + 1);
                }
            }
        }

        Integer [] rezultArray = new Integer [rez.size()];
        rezultArray = rez.toArray(rezultArray);
        return Arrays.stream(rezultArray).mapToInt(Integer::intValue).toArray();
    }

    private void checkConstraint(String current) {
        //get correct streamling
        correctStreamling = new int[zParams.length];
        correctStreamling=findStreamlining(zParams);

        if (toMin && ZConstraint == 9999 || !toMin && ZConstraint == -9999) {
            ZConstraint=0;
            if(checkConstraints(current)){
                for (int i = 0; i < model.getAmountOfVariables(); i++) {
                    ZConstraint+= zParams[i] * Character.getNumericValue(current.charAt(findNeedlessIndex(i)));
                }
            }
        }else {
            int temp=0;

            for (int i = 0; i < model.getAmountOfVariables(); i++) {
                temp += zParams[i] * Character.getNumericValue(current.charAt(findNeedlessIndex(i)));
            }
            if (toMin && temp < ZConstraint || !toMin && temp > ZConstraint) {
                if (checkConstraints(current)) {
                    ZConstraint = 0;
                    for (int i = 0; i < model.getAmountOfVariables(); i++) {
                        ZConstraint += zParams[i] * Character.getNumericValue(current.charAt(findNeedlessIndex(i)));
                    }
                    values = current;
                }
            }
        }
    }

    private boolean checkConstraints(String current) {
        int rez;
        boolean[] correctConstraints = new boolean[model.getAmountOfRows()];

        for (int i=0;i<model.getAmountOfRows();i++) {
            rez=0;
            for (int j = 0; j < model.getAmountOfVariables(); j++) {
                rez += parameters[i][j] * Character.getNumericValue(current.charAt(findNeedlessIndex(j)));
            }
            if (rez <= conditions[i]) {
                correctConstraints[i] = true;
            } else return false;
        }
        return true;
    }

    private int findNeedlessIndex(int currentVariable){
        for (int i=0;i<model.getAmountOfVariables();i++){
            if(correctStreamling[i]==currentVariable+1)
                return i;
        }
        return 0;
    }

    public int getZConstraint() {
        return ZConstraint;
    }
    public String getSolvedValues(){

        String sortedRez="";
        for(int i=0;i<model.getAmountOfVariables();i++) {
            sortedRez += values.charAt(findNeedlessIndex(i));
        }
        return sortedRez;
    }

}
