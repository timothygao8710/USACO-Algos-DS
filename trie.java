
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * -Take it slow, think-
 * Watch out for:
 * - Long/Int
 * - Edge cases (make test case)
 * - Unexpected behavior?
 *
 * @author timothy
 */
public class trie {
    //M is length of string
    //O(M) insert
    //O(M) contains    
    public HashMap<Character, trie> ch = new HashMap();
    public boolean end;
    
    //for brand new string always use index 0
    
    public boolean contains(int index, String str){
        if(index == str.length()){
            return end;
        }
        if(ch.containsKey(str.charAt(index))){
            return ch.get(str.charAt(index)).contains(index+1, str);
        }
        return false;
    }
    
    public void insert(int index, String str){
        if(index == str.length()){
            end = true;
            return;
        }
        trie t = ch.get(str.charAt(index));
        if(t == null){
            t = new trie();
            ch.put(str.charAt(index), t);
        }
        t.insert(index+1, str);
    }
}
