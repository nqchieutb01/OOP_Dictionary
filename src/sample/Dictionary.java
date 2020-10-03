package sample;

import java.util.ArrayList;

public class Dictionary {
    private ArrayList<Word> Dict;

    public Trie trie  = new Trie();

    public ArrayList<Word> getDict() {
        return Dict;
    }

    public Dictionary() {
        Dict = new ArrayList<Word>();
    }

    /**
     * Add, show, remove ,search with array
     */
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
        return new String("Your word doesn't exist in My Dictionary. Please, try searching with Google");
    }
    public void showAllWord(){
        for(Word word: Dict){
            System.out.println(word.getWordTarget());
        }
    }
    /**
     * Add, show, dedete, search with Trie
     */
    public void addWordTrie(String wt,String we){
        int appear = trie.search(wt);
        if(appear ==-1){
            Word word = new Word(wt,we);
            Dict.add(word);
            trie.insert(wt,Dict.size()-1);
        }
    }
    public String searchWordTrie(String wt){
        int appear = trie.search(wt);
        if(appear==-1) {
            return new String("Your word doesn't exist in My Dictionary. Please, try searching with Google");
        }else{
            return Dict.get(appear).getWordExplain();
        }
    }
}
