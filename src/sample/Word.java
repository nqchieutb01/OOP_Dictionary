package sample;

public class Word {
    private String word_target;
    private String word_explain;
    public Word(){}
    public Word(String wt, String we) {
        word_target = wt;
        word_explain = we;
    }
    public Word(Word w) {
        word_explain = w.word_explain;
        word_target = w.word_target;
    }
    public void copy(Word w) {
        word_target = w.word_target;
        word_explain = w.word_explain;
    }
    public void setWordTarget(String word_target) {
        word_target = word_target;
    }

    public void setWordExplain(String word_explain) {
        word_explain = word_explain;
    }

    public String getWordTarget() {
        return word_target;
    }

    public String getWordExplain() {
        return word_explain;
    }
}
