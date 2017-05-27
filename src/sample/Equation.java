package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Created by BusinessPC on 5/7/2017.
 */
public class Equation{
    public void display() throws Exception{
        Stage window = new Stage();

        Parent root = FXMLLoader.load(getClass().getResource("equation.fxml"));
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Equation");
        Scene scene = new Scene(root);
        //scene.getStylesheets().add(getClass().getResource("styleAlert.css").toExternalForm());
        window.setScene(scene);
        window.showAndWait();
    }
}
