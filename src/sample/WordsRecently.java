package sample;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class WordsRecently {

    private ObservableList listWord;

    WordsRecently() {
        listWord = FXCollections.observableArrayList();
    }

    public ObservableList getReverseWordRecently() {
        ObservableList res = FXCollections.observableArrayList();
        for (int i = listWord.size() - 1; i >= 0; i--) res.add(listWord.get(i));
        return res;
    }

    public ObservableList getWordRecently() {
        return listWord;
    }

    public void addWord(String word) {
        this.listWord.add(word);
    }

    public void loadFromFile() throws FileNotFoundException {

        File f = new File("/home/nguyen/IdeaProjects/OOP_Dictionary/src/recently.txt");
        Scanner scanner = new Scanner(f);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            listWord.add(line);
        }
    }

    public void exportToFile() throws IOException {
        File f = new File("/home/nguyen/IdeaProjects/OOP_Dictionary/src/recently.txt");
        FileWriter fl = new FileWriter(f);
        System.out.println(listWord.size());
        for (int i = Math.max(0,listWord.size()-20); i < listWord.size(); i++) {
            fl.write((String) listWord.get(i)+"\n");
        }
        fl.flush();
    }
}
