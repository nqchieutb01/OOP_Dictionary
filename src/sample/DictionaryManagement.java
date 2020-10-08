package sample;

import javax.imageio.IIOException;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import static sample.Main.dictionary;

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

        File myFile = new File("/home/nguyen/IdeaProjects/OOP_Dictionary/src/data.txt");
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

                int i = 1;
                for (i = 1; i < line.length(); i++) {
                    Character ch = line.charAt(i);
                    if (ch.equals('/')) {
                        word_explain = line.substring(i, line.length());
                        word_target = line.substring(1, i - 1);
                        break;
                    }
                }

                if (i >= line.length() - 1) {
                    word_target = line.substring(1, line.length());
                }

            } else {
                word_explain += '\n';
                word_explain += line;
            }
        }
        System.out.println("Number of words is: " + cnt.toString());
    }

    public void insertFromAddFile() throws FileNotFoundException {
        File myFile = new File("/home/nguyen/IdeaProjects/OOP_Dictionary/src/addWord.txt");
        Scanner scanner = new Scanner(myFile);
        String ws = "";
        String wt = "";
        while (scanner.hasNextLine()){
            String line = scanner.nextLine();
            String[] parts = line.split("-");
            System.out.println(parts.length);
            if(parts.length>1) {
             //   System.out.println(parts[0] + " " + parts[1]);
                dictionary.addWordTrie(parts[0], parts[1]);
            }
        }
    }
    public void insertFromDeleteFile() throws FileNotFoundException {
        File myFile = new File("/home/nguyen/IdeaProjects/OOP_Dictionary/src/delete.txt");
        Scanner scanner = new Scanner(myFile);
        String ws = "";
        String wt = "";
        while (scanner.hasNextLine()){
            String line = scanner.nextLine();
            dictionary.deleteWordTrie(line);
        }
    }
    public void insertFromReplaceFile() throws FileNotFoundException {
        File myFile = new File("/home/nguyen/IdeaProjects/OOP_Dictionary/src/replace.txt");
        Scanner scanner = new Scanner(myFile);
        String ws = "";
        String wt = "";
        while (scanner.hasNextLine()){
            String line = scanner.nextLine();
            String[] parts = line.split("-");
            System.out.println(parts.length);
            if(parts.length>1) {
                String wordExplain = dictionary.deleteWordTrie(ws);
                dictionary.addWordTrie(wt,wordExplain);
            }
        }
    }

}
