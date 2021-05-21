// package Structures;

import java.io.*;
import java.util.*;

public class lazysegtree {
    static class segtree {
        private final int none = Integer.MAX_VALUE;
        private final int updnone = 0;
        
        private int gL; private int gR; private int mid;
        public segtree left;
        public segtree right;
        public int val;
        public int lazy = updnone;
        
        public segtree(int[] nums, int l, int r) {
            gL = l; gR = r; mid = (l + r)/2;
            if (l == r) {
                val = nums[l];
                return;
            }
            left = new segtree(nums, l, mid);
            right = new segtree(nums, mid+1, r);
            val = comb(left.val, right.val);
        }

        public int get(int l, int r){
            if(l > gR || r < gL) return none;
            push();
            if(gL == l && gR == r) return val;
            return comb(
                    left.get(l, Math.min(r, mid)),
                    right.get(Math.max(l, mid+1), r)
            );
        }
        
        public int update(int l, int r, int v){
            push();
            if(l > gR || r < gL) return val;
            
            if(gL == l && gR == r){
                compose(this, v);
                push();
            }else{
                val = comb(
                        left.update(l, Math.min(r, mid), v),
                        right.update(Math.max(l, mid+1), r, v)
                );
            }
            return val;
        }
        
        private int comb(int a, int b) { //associative
            return Math.min(a, b);
        }
        
        //push when you need the updated value
        public void push(){ 
            if(lazy == updnone) return;
            if(gL != gR){
                compose(left, lazy); compose(right, lazy);
            }
            val += lazy*(gR - gL + 1); //modify: effect of lazy
            lazy = updnone;
        }
        
        public void compose(segtree t, long v){
            t.lazy += v;
        }
        
        public String toString(){
            return gL + " " + gR + " " + val + " " + lazy;
        }
    }
}
