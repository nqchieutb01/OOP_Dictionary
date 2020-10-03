package sample;

import java.util.ArrayList;

public class Trie {
    static final int MAX_CHAR = 'Z' - ' ' + 2;

    static class TrieNode {
        TrieNode[] children = new TrieNode[MAX_CHAR];
        int isEndOfWord;

        TrieNode() {
            isEndOfWord = -1;
            for (int i = 0; i < MAX_CHAR; i++) {
                children[i] = null;
            }
        }
    }

    static TrieNode root = new TrieNode();

    public void insert(String s, int numerical_order) {
        String key = s.toUpperCase();
        TrieNode it = root;
        for (int i = 0; i < key.length(); i++) {
            //System.out.print(key.charAt(i));
            int index = key.charAt(i) - ' ';
            if (index > MAX_CHAR || index < 0) continue;
            if (it.children[index] == null)
                it.children[index] = new TrieNode();
            it = it.children[index];
        }
        it.isEndOfWord = numerical_order;
    }

    public int search(String s) {
        String key = s.toUpperCase();
        TrieNode it = root;
        for (int i = 0; i < key.length(); i++) {
            int index = key.charAt(i) - ' ';
            if (index > MAX_CHAR || index < 0) continue;
            if (it.children[index] == null) return -1;
            it = it.children[index];
        }
        if (it != null && it.isEndOfWord != -1) return it.isEndOfWord;
        return -1;
    }

    public ArrayList<Integer> recommendWord(String s) {
        ArrayList<Integer> result = new ArrayList<>();
        String key = s.toUpperCase();
        TrieNode it = root;
        for (int i = 0; i < key.length(); i++) {
            int index = key.charAt(i) - ' ';
            if (index > MAX_CHAR || index < 0) continue;
            if (it.children[index] == null) return result;
            it = it.children[index];
        }
        if(it.isEndOfWord!=-1){
            result.add(it.isEndOfWord);
        }
        if(it!=null) result.addAll(backTrack(it));
        return result;
    }

    public ArrayList<Integer> backTrack(TrieNode it) {
        ArrayList<Integer> result = new ArrayList<>();
        for (int index = 0; index < MAX_CHAR; index++) {
            TrieNode tmp = it.children[index];
            if (tmp != null) {
                if (tmp.isEndOfWord != -1) {
                    result.add(tmp.isEndOfWord);
                    result.addAll(backTrack(tmp));
                }
            }
        }
        return result;
    }
}
