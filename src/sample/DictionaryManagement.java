package sample;

import javax.imageio.IIOException;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DictionaryManagement {

    private Dictionary dictionary;

    public DictionaryManagement(){
        dictionary = new Dictionary();
    }
    public DictionaryManagement(Dictionary d){
        dictionary = d ;
    }
    public Dictionary getDictionary(){
        return dictionary;
    }
    /**
     *
     * @throws IOException
     */
    public void insertFromFile() throws IOException
    {
        dictionary = new Dictionary();
        File fileDir = new File("C:\\Users\\Admin\\IdeaProjects\\OOP_Dictionary\\src\\test.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileDir), "UTF8"));
        while (br.ready()) {
            String lineWord = br.readLine();
            String[] parts = lineWord.split("\\t");
            if(parts.length==2) {
                dictionary.addWord(parts[0], parts[1]);
            }
        }
        br.close();
    }
}
