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
            int index = key.charAt(i) - ' ';
            if (index > MAX_CHAR || index < 0) continue;
            if (it.children[index] == null) {
                it.children[index] = new TrieNode();
            }
            it = it.children[index];
        }
        it.isEndOfWord = numerical_order;
    }
    public int delete(String wt){
        int indexRemove ;
        String key = wt.toUpperCase() ;
        TrieNode it = root ;
        for(int i=0;i<key.length();i++){
            int index = key.charAt(i) - ' ' ;
            if(index > MAX_CHAR || index<0) continue;
            if(it==null) return -1;
            it = it.children[index] ;
        }
        indexRemove = it.isEndOfWord ;
        it.isEndOfWord = -1 ;
        return indexRemove ;
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
        if (it != null) return it.isEndOfWord;
        return -1;
    }

    public ArrayList<Integer> recommendWord(String s) {
        ArrayList<Integer> result = new ArrayList<>();
        String key = s.toUpperCase();
        //debug
       // System.out.println(key);
        TrieNode it = root;
        for (int i = 0; i < key.length(); i++) {
            int index = key.charAt(i) - ' ';
           // System.out.println(index);
            if (index > MAX_CHAR || index < 0) continue;
            if(it.children[index]!=null) {
                it = it.children[index];
            }
            else return result;
        }
        if(it.isEndOfWord!=-1){
            result.add(it.isEndOfWord);
        }
        if(it!=null) result.addAll(DFS(it));
        return result;
    }

    public ArrayList<Integer> DFS(TrieNode it) {
        ArrayList<Integer> result = new ArrayList<>();
        for (int index = 0; index < MAX_CHAR; index++) {
            if(result.size()>20) return result;
            TrieNode tmp = it.children[index];
            if (tmp != null) {
                result.addAll(DFS(tmp));
                if (tmp.isEndOfWord!= -1) {
                    result.add(tmp.isEndOfWord);
                }
            }
        }
        return result;
    }
}
