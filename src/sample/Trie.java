package sample;

import java.util.ArrayList;

public class Trie {
    private static final int MAX_CHAR = 'Z' - ' ' + 2;

    public static class TrieNode {
        public TrieNode[] children = new TrieNode[MAX_CHAR];
        public int isEndOfWord;
        public String wort_target;
        public String word_explain;

        public TrieNode() {
            isEndOfWord = -1;
            for (int i = 0; i < MAX_CHAR; i++) {
                children[i] = null;
            }
        }
    }

    public static TrieNode root = new TrieNode();

    public void insert(String s, String we) {
        String key = s.toUpperCase();
        TrieNode it = root;
        for (int i = 0; i < key.length(); i++) {
            int index = key.charAt(i) - ' ';
            if (index > MAX_CHAR || index < 0) continue;
            if (it.children[index] == null) {
                it.children[index] = new TrieNode();
            }
            it = it.children[index];
        }
        it.isEndOfWord = 1;
        it.word_explain = we;
        it.wort_target = s;
    }

    public String delete(String wt) {
        int indexRemove;
        String key = wt.toUpperCase();
        TrieNode it = root;
        for (int i = 0; i < key.length(); i++) {
            int index = key.charAt(i) - ' ';
            if (index > MAX_CHAR || index < 0) continue;
            if (it == null) return "not_found";
            it = it.children[index];
        }
        indexRemove = it.isEndOfWord;
        it.isEndOfWord = -1;
        String res = it.word_explain;
        it.word_explain = "";
        return res;
    }

    public String search(String s) {
        String key = s.toUpperCase();
        TrieNode it = root;
        for (int i = 0; i < key.length(); i++) {
            int index = key.charAt(i) - ' ';
            if (index > MAX_CHAR || index < 0) continue;
            if (it.children[index] == null) return new String("not_found");
            it = it.children[index];
        }
        if (it.isEndOfWord != -1) return it.word_explain;
        return new String("not_found");
    }

    public ArrayList<String> recommendWord(String s) {
        ArrayList<String> result = new ArrayList<>();
        String key = s.toUpperCase();

        TrieNode it = root;
        for (int i = 0; i < key.length(); i++) {
            int index = key.charAt(i) - ' ';
            // System.out.println(index);
            if (index > MAX_CHAR || index < 0) continue;
            if (it.children[index] != null) {
                it = it.children[index];
            } else return result;
        }
        if (it.isEndOfWord != -1) {
            result.add(it.wort_target);
        }
        if (it != null) result.addAll(DFS(it));
        return result;
    }

    public ArrayList<String> DFS(TrieNode it) {
        ArrayList<String> result = new ArrayList<>();
        for (int index = 0; index < MAX_CHAR; index++) {
            if (result.size() > 20) return result;
            TrieNode tmp = it.children[index];
            if (tmp != null) {
                if (tmp.isEndOfWord != -1) {
                    result.add(tmp.wort_target);
                }
                result.addAll(DFS(tmp));
            }
        }
        return result;
    }

    public ArrayList<String> DFSAllTrie(TrieNode it) {
        ArrayList<String> result = new ArrayList<>();
        for (int index = 0; index < MAX_CHAR; index++)
            if (it.children[index] != null) {
                TrieNode tmp = it.children[index];
                if (tmp.isEndOfWord != -1) {
                    result.add(tmp.wort_target);
                }
                result.addAll(DFSAllTrie(tmp));
            }
        return result;
    }

}
