package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.lang.reflect.Parameter;

public class Main extends Application {
    private AppModel model;

    @Override
    public void start(Stage primaryStage) throws Exception{
        model = new AppModel();

        FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("main.fxml"));
        mainLoader.setController(new Controller(model));
        Parent root = mainLoader.load();
        primaryStage.setTitle("Lexicographical permutation algorithm");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
