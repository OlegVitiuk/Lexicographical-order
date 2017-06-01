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
    private int []correctStreamling;
    private int amountOfVar;
    private boolean toMin;
    private String values;

    @FXML
    private ComboBox amountOfVariables;
    @FXML
    private ComboBox amountOfRows;
    @FXML
    private ComboBox xValues;

    public Controller(AppModel model) {
        this.model = model;
        equation = new Equation();

        toMin = true;

        if (toMin) {
            ZConstraint = 9999;
        } else {
            ZConstraint = -9999;
        }
        amountOfVar=4;
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
        System.out.println(getZConstraint());
        System.out.println(getSolvedValues());
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

        //int amountOfVariables = 4;
        int[] zParams = {1, -5, 4, -2};
        int rowsAmount = 2;

        //massive of condition
        //int []condition = new int [rowsAmount];
        //set conditions
//        int []condition = {2,1};
//        boolean toMin = true;
//        boolean [] correctConstraints= new boolean[rowsAmount];


        //get correct streamling
            correctStreamling = new int[zParams.length];
                correctStreamling=findStreamlining(zParams);

            boolean flagCondition = true;
            int rez = 0;

            if (toMin && ZConstraint == 9999 || !toMin && ZConstraint == -9999) {
                ZConstraint=0;
                if(checkConstraints(current)){
                    for (int i = 0; i < amountOfVar; i++) {
                        ZConstraint+= zParams[i] * Character.getNumericValue(current.charAt(findNeedlessIndex(i)));
                    }
                }
            }else {
                int temp=0;

                for (int i = 0; i < amountOfVar; i++) {
                    temp += zParams[i] * Character.getNumericValue(current.charAt(findNeedlessIndex(i)));
                }
                if (toMin && temp < ZConstraint || !toMin && temp > ZConstraint) {
                    if (checkConstraints(current)) {
                        ZConstraint = 0;
                        for (int i = 0; i < amountOfVar; i++) {
                            ZConstraint += zParams[i] * Character.getNumericValue(current.charAt(findNeedlessIndex(i)));
                        }
                        values = current;
                    }
                }
            }
    }

    private boolean checkConstraints(String current) {

        int[][] parameters = {
                {3, -4, 1, -2},
                {-2, 3, -1, -1}
        };
        int[] condition = {2, 1};
        int rowsAmount = 2;
        int rez;
        boolean[] correctConstraints = new boolean[rowsAmount];

        for (int i=0;i<rowsAmount;i++) {
            rez=0;
            for (int j = 0; j < amountOfVar; j++) {
                rez += parameters[i][j] * Character.getNumericValue(current.charAt(findNeedlessIndex(j)));
            }
            if (rez <= condition[i]) {
                correctConstraints[i] = true;
            } else return false;
        }
        return true;
    }

    private int findNeedlessIndex(int currentVariable){
        for (int i=0;i<amountOfVar;i++){
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
        for(int i=0;i<amountOfVar;i++) {
            sortedRez += values.charAt(findNeedlessIndex(i));
        }
        return sortedRez;
    }
}
