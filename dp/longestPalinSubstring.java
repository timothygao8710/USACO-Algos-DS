/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dp;

import java.util.Arrays;

/**
 *
 * @author timothy
 */
public class longestPalinSubstring {

    public static void main(String[] args) {
        System.out.println(longestPalindrome("babadada"));
    }

    //The idea is that:
    /*
    if we are generating all possible substrings anyways, why don't we do it in a smartway?
    Instead of checking wether each substring is possible, we use the property of paldinromes and 
    check for left+1 right-1
    
    This effectively reduces each substring "check" to only O(1) since the left+1 right-1 will be done
    anyways
     */
    static int[][] dp;
    static String longest;

    public static String longestPalindrome(String s) {
        longest = "";
        if (s.length() <= 1) {
            return s;
        }
        dp = new int[s.length()][s.length()];
        for (int[] a : dp) {
            Arrays.fill(a, -1);
        }
        for (int i = 0; i < s.length(); i++) {
            dp[i][i] = 1;
        }
        memo(s, 0, s.length() - 1);
        if (longest.length() == 0) {
            return s.substring(0, 1);
        }
        for(int[] a : dp){
            for(int b : a){
                System.out.print(b + " ");
            }
            System.out.println();
        }
        return longest;
    }

    static void memo(String s, int left, int right) {
        if (left > right) {
            return;
        }
        if (s.charAt(left) == s.charAt(right)) {
            if (dp[left + 1][right - 1] == -1) {
                memo(s, left + 1, right - 1);
            }
            if (dp[left + 1][right - 1] > 0 || dp[left + 1][right - 1] == -1) {
                dp[left][right] = 100;
                System.out.println(left + " " + right);
                if (right - left + 1 > longest.length()) {
                    longest = s.substring(left, right + 1);
                }
            } else {
                dp[left][right] = 0;
            }
        } else {
            if (dp[left + 1][right] == -1) {
            memo(s, left + 1, right);
        }
        if (dp[left][right - 1] == -1) {
            memo(s, left, right - 1);
        }
            dp[left][right] = 0;
        }
        
    }
}
