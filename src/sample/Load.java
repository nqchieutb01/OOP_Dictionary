package sample;

import java.io.IOException;

public class Load {
    public static DictionaryManagement dictionaryManagement;
    public static Dictionary dictionary;
    public static void main(String[] args) {

        dictionary = new Dictionary();
        dictionaryManagement = new DictionaryManagement(dictionary);
        try {
            dictionaryManagement.insertFromFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dictionary.showAllWord();
    }
}
