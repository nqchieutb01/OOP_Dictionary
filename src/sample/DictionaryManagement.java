package sample;

import javax.imageio.IIOException;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class DictionaryManagement {

    public Trie trie = new Trie();

    public Trie getTrie() {
        return this.trie;
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
                if (cnt > 0) {
                    String check = trie.search(word_target);
                    if (check.equals("not_found")) {
                    } else {
                        word_target += "_detailed";
                    }
                    check = trie.search(word_target);
                    if (check.equals("not_found")) {
                    } else {
                        word_target += "1";
                    }

                    check = trie.search(word_target);
                    if (check.equals("not_found")) {
                    } else {
                        word_target += "2";
                    }

                    check = trie.search(word_target);
                    if (check.equals("not_found")) {
                    } else {
                        word_target += "3";
                    }
                    trie.insert(word_target, word_explain);
                }
                word_target = "";
                word_explain = "";
                cnt++;
                int dem = 0;
                for (int j = 1; j < line.length(); j++) {
                    Character ch = line.charAt(j);
                    if (ch.equals('/')) dem += 1;
                }
                if (dem >= 2) {
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
                    word_target = line.substring(1, line.length());
                }

            } else {
                word_explain += '\n';
                word_explain += line;
            }
        }
        trie.insert(word_target, word_explain);
        System.out.println("Number of words is: " + cnt.toString());
    }

    public void insertFromFile1() throws IOException {
        File myFile = new File("/home/nguyen/IdeaProjects/OOP_Dictionary/src/Trie.txt");
        Scanner scanner = new Scanner(myFile);
        String word_target = "";
        String word_explain = "";
        String line = "";
        Integer cnt = 0;
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            if (line.length() == 0) continue;
            Character c = line.charAt(0);
            if (c.equals('@')) {
                if (cnt > 0) {
                    trie.insert(word_target, word_explain);
                }
                word_target = "";
                word_explain = "";
                word_target = line.substring(1, line.length());
                cnt++;
            } else {
                word_explain += line + '\n';
            }
        }
        trie.insert(word_target, word_explain);
        System.out.println("Number of words is: " + cnt.toString());
    }
}
