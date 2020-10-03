package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("FindWord.fxml"));
        primaryStage.setTitle("Dictionary");
        primaryStage.setScene(new Scene(root, 1024, 604));
        //primaryStage.setScene(new Scene(root, 500, 800));
        primaryStage.show();
    }

    public static void main(String[] args) throws IOException {

        launch(args);
    }
}
