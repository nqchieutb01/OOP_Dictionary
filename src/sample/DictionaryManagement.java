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
                int i = 1;
                for(i=1;i<line.length();i++){
                    Character ch= line.charAt(i);
                    if(ch.equals('/')){
                        word_explain = line.substring(i,line.length());
                        word_target = line.substring(1,i-1);
                        break;
                    }
                }
                if(i>=line.length()-1) word_target = line.substring(1,line.length());

            } else {
                word_explain += '\n';
                word_explain += line;
            }
        }
        System.out.println("Number of words is: " + cnt.toString());
    }
}
