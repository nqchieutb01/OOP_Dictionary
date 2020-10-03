package sample;

import java.util.ArrayList;

public class Dictionary {
    private ArrayList<Word> Dict;

    public ArrayList<Word> getDict() {
        return Dict;
    }

    public Dictionary() {
        Dict = new ArrayList<Word>();
    }

    public void addWord(String word_target, String word_explain) {
        Word word = new Word();
        word.setWordExplain(word_explain);
        word.setWordTarget(word_target);
        Dict.add(new Word(word_target, word_explain));
    }

    public void removeWord(String word_target) {
        Dict.remove(word_target);
    }

    public String search(String word_target) {
        for (Word o : Dict) {
            if (o.getWordTarget().equals(word_target)) {
                return o.getWordExplain();
            }
        }
        return new String("Not Found");
    }
    public void showAllWord(){
        for(Word word: Dict){
            System.out.println(word.getWordTarget());
        }
    }
}
