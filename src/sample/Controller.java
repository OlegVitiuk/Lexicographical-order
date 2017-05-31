package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
//import controlsfx.control.CheckComboBox;

import java.net.URL;
import java.util.*;

public class Controller {
    private Equation equation;
    private AppModel model;
    private int ZConstraint;

    @FXML
    private ComboBox amountOfVariables;
    @FXML
    private ComboBox amountOfRows;
    @FXML
    private ComboBox xValues;

    public Controller(AppModel model) {
        this.model = model;
        equation = new Equation();

    }

    public Controller() {

    }

    @FXML
    private void makeEquation() {
//        try {
//            equation.display(model);
//        }
//        catch(Exception e){
//            System.err.print(e);
//        }
//        int [][] limitations = new int[5][2];


        int amountOfValuesX = 3;
        int amountOfVariables = 3;


        int[] zParameters = {1, -5, 4, -2, -2};


        int[][] val = new int[amountOfVariables * 9][amountOfValuesX];

        String vals = "01";
        char[] chars = vals.toCharArray();

        //findStreamlining(zParameters);
        generate(chars, "", 4);
        System.out.println(ZConstraint);
    }

    private void generate(char[] alphabet, String current, int length) {
        if (length == 0) {

            //System.out.println(current);
            checkConstraint(current);
        } else {
            for (int i = 0; i < alphabet.length; i++) {
                generate(alphabet, current + alphabet[i], length - 1);
            }
        }
    }

    private ArrayList<Integer> findStreamlining(int[] arr) {

        ArrayList<Integer> rez = new ArrayList<>();
        int[] copy = Arrays.copyOf(arr, arr.length);
        Arrays.sort(copy);

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (copy[i] == arr[j] && !rez.contains(j + 1)) {
                    rez.add(j + 1);
                }
            }
        }
        return rez;
    }

    @FXML
    private void sentAmountOfVariables() {
        model.setAmountOfVariables(Integer.valueOf(amountOfVariables.getSelectionModel().getSelectedItem().toString()));
    }

    @FXML
    private void sentAmountOfRows() {
        model.setAmountOfRows(Integer.valueOf(amountOfRows.getSelectionModel().getSelectedItem().toString()));
    }

    @FXML
    private void pushXValues() {
        //model.setArrayWithValuesOfX(xValues.getSelectionModel().get);
    }

    boolean nextPermutation(int[] array) {
        // Find longest non-increasing suffix
        int i = array.length - 1;
        while (i > 0 && array[i - 1] >= array[i])
            i--;
        // Now i is the head index of the suffix

        // Are we at the last permutation already?
        if (i <= 0)
            return false;

        // Let array[i - 1] be the pivot
        // Find rightmost element that exceeds the pivot
        int j = array.length - 1;
        while (array[j] <= array[i - 1])
            j--;
        // Now the value array[j] will become the new pivot
        // Assertion: j >= i

        // Swap the pivot with j
        int temp = array[i - 1];
        array[i - 1] = array[j];
        array[j] = temp;

        // Reverse the suffix
        j = array.length - 1;
        while (i < j) {
            temp = array[i];
            array[i] = array[j];
            array[j] = temp;
            i++;
            j--;
        }

        // Successfully computed the next permutation
        return true;
    }

    private void checkConstraint(String current) {

        int amountOfVariables = 4;
        int[] zParams = {1, -5, 4, -2};
        int rowsAmount = 2;

        //massive of condition
        //int []condition = new int [rowsAmount];
        //set conditions
//        int []condition = {2,1};
        boolean toMin = true;
//        boolean [] correctConstraints= new boolean[rowsAmount];


        if (toMin) {
            ZConstraint = 9999;
        } else {
            ZConstraint = -9999;
        }

            boolean flagCondition = true;
            int rez = 0;

            if (toMin && ZConstraint == 9999 || !toMin && ZConstraint == -9999) {
                ZConstraint=0;
                if(checkConstraints(current)){
                    for (int i = 0; i < amountOfVariables; i++) {
                        ZConstraint+= zParams[i] * Character.getNumericValue(current.charAt(i));
                    }
                }
                return;
            }

            for (int i = 0; i < amountOfVariables; i++) {
                int temp = zParams[i] * Character.getNumericValue(current.charAt(i));
                if (toMin && temp < ZConstraint || !toMin && temp > ZConstraint) {
                    if (checkConstraints(current)) {
                        ZConstraint += zParams[i] * Character.getNumericValue(current.charAt(i));
                    }
                }
            }
        System.out.println(ZConstraint);
    }

    private boolean checkConstraints(String current) {

        int[][] parameters = {
                {3, -4, 1, -2},
                {-2, 3, -1, -1}
        };
        int[] condition = {2, 1};
        int rowsAmount = 2;
        int amountOfVariables = 4;
        int rez=0;
        boolean[] correctConstraints = new boolean[rowsAmount];

        for (int i=0;i<rowsAmount;i++) {
            for (int j = 0; j < amountOfVariables; j++) {
                rez += parameters[i][j] * Character.getNumericValue(current.charAt(j));
                if (rez <= condition[i]) {
                    correctConstraints[i] = true;
                } else return false;
            }
        }
        return true;
    }
}
