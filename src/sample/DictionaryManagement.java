package sample;

import javax.imageio.IIOException;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DictionaryManagement {

    private Dictionary dictionary;

    public DictionaryManagement() {
        dictionary = new Dictionary();
    }

    public DictionaryManagement(Dictionary d) {
        dictionary = d;
    }

    public Dictionary getDictionary() {
        return dictionary;
    }

    public void insertFromFile() throws IOException {
        File myFile = new File("C:\\Users\\Admin\\IdeaProjects\\OOP_Dictionary\\src\\data.txt");
        Scanner scanner = new Scanner(myFile);
        String word_target = "";
        String word_explain = "";
        Integer cnt = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.length() == 0) continue;
            Character c = line.charAt(0);
            if (c.equals('@')) {
                cnt++;
                dictionary.addWordTrie(word_target, word_explain);
                word_target = "";
                word_explain = "";
                String[] part = line.split("/", 2);
                if (part.length < 1) continue;
                if (part[0].length() > 1) word_target = part[0].substring(1, part[0].length() - 1);
                word_explain += "/";
                if (part.length >= 2) word_explain += part[1];
            } else {
                word_explain += '\n';
                word_explain += line;
            }
        }
        int c = 'z' - ' ';
        System.out.println(c);
        System.out.println("Number of words is: " + cnt.toString());
    }
}
