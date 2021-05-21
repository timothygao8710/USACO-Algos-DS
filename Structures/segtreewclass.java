// package Structures;

import java.io.*;
import java.util.*;

public class segtreewclass {

    //easier to implement
    //easier to store extra info
    //shorter code
    static class segtree {
        private final int none = Integer.MAX_VALUE;
        
        public segtree left;
        public segtree right;
        public int val;
        
        public segtree(int[] nums, int l, int r) {
            if (l == r) {
                val = nums[l];
                return;
            }
            left = new segtree(nums, l, (l+r)/2);
            right = new segtree(nums, (l+r)/2+1, r);
            val = comb(left.val, right.val);
        }

        public int get(int l, int r, int gL, int gR){
            if(gL > r || gR < l) return none;
            if(gL == l && gR == r) return val;
            int mid = (gL + gR)/2;
            return comb(
                    left.get(l, Math.min(r, mid), gL, mid),
                    right.get(Math.max(l, mid+1), r, mid+1, gR)
            );
        }
        
        public int update(int idx, int up, int gL, int gR){
            if(gL == gR){
                val = up;
            }else{
                if(idx <= (gL+gR)/2){
                    left.update(idx, up, gL, (gL+gR)/2);
                }else{
                    right.update(idx, up, (gL+gR)/2+1, gR);
                }
                val = comb(left.val, right.val);
            }
            return val;
        }
        
        private int comb(int a, int b) { //associative
            return Math.min(a, b);
        }
    }
}
