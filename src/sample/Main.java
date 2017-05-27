package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    private AppModel model;

    @Override
    public void start(Stage primaryStage) throws Exception{
        model = new AppModel();
        FXMLLoader loader = new FXMLLoader();
        loader.setControllerFactory(clazz->{
            if(clazz == Controller.class){
                return new Controller(model);
            }else{
                try{
                    return clazz.newInstance();
                }catch (Exception exc){
                    throw  new RuntimeException(exc);
                }
            }
        });
        Parent root = loader.load(getClass().getResource("/sample/main.fxml"));
        primaryStage.setTitle("Lexicographical permutation algorithm");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
