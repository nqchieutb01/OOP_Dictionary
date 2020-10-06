package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class Main extends Application {

    // data
    public static DictionaryManagement dictionaryManagement;
    public static Dictionary dictionary ;
    public static ArrayList<Word> Dict ;
    public static Trie trie = new Trie();

    @Override
    public void start(Stage primaryStage) throws Exception{
        // load data
        dictionaryManagement = new DictionaryManagement();
        try {
            dictionaryManagement.insertFromFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dictionary = dictionaryManagement.getDictionary();
        Dict = dictionary.getDict();

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
