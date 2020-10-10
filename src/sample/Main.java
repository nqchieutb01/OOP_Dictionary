package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

public class Main extends Application {

    //data
    public static WordsRecently wordsRecently;
    public static DictionaryManagement dictionaryManagement;
    public static Trie trie = new Trie();

    @Override
    public void start(Stage primaryStage) throws Exception {
        // load data

        wordsRecently = new WordsRecently();
        wordsRecently.loadFromFile();

        dictionaryManagement = new DictionaryManagement();
        try {
            dictionaryManagement.insertFromFile1();
        } catch (IOException e) {
            e.printStackTrace();
        }
        trie = dictionaryManagement.getTrie();

        Parent root = FXMLLoader.load(getClass().getResource("FindWord.fxml"));
        primaryStage.setTitle("Dictionary");
        primaryStage.setScene(new Scene(root, 1024, 610));

        //primaryStage.setScene(new Scene(root, 500, 800));
        primaryStage.show();
    }

    public static void main(String[] args) throws IOException {
        launch(args);
        File f = new File("/home/nguyen/IdeaProjects/OOP_Dictionary/src/Trie.txt");
        FileWriter bw = new FileWriter(f);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList = trie.DFSAllTrie(Trie.root);

        System.out.println(arrayList.size());

        for (String x : arrayList) {
            String tmp = trie.search(x);
            if (tmp.length() == 0) continue;
            Character c = tmp.charAt(0);
            bw.write("@" + x + '\n' + tmp);
        }
        bw.flush();
        wordsRecently.exportToFile();

    }
}
