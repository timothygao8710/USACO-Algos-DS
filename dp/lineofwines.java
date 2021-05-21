/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author timothy
 */
public class lineofwines {
    static int[][] dp;
    
    //view erricho's lecture at https://www.youtube.com/watch?v=pwpOC1dph6U
    public static void main(String[] args){
        int[] wines = new int[]{ 2,4,6,2,5};
        dp = new int[wines.length][wines.length];
        
        
//        memo(wines, 0, wines.length-1);

//        System.out.println(dp[0][wines.length-1]);
        
        //order of states, based on memo solution, is first 1, R
        //then 2, R, then 3, R all the way to wines.length-1, R
        //Then it will go to wines.length-2, R-1, and wines.length -3, R-1
        //In the order of the tree
        // we don't have to go exactly in this order but we can observe something about the states:
        //state l depends on state l+1, and state r depends on state r+1
        //so we want to start with the maximum that l can be and the mininum that r can be(in this case l+1)
        
        //It is important to note here that we are not going in order of years
        //dp[l][r] stores the max num of wines at l, r inclusive        
        
        //when doing bottom-up, always remember how you ddid it with memoization
        for(int l = wines.length-1; l>=0; l--){
            for(int r = l; r<wines.length; r++){
                if(r -1 >= 0){
                    dp[l][r] = dp[l][r-1] + wines[r]*(wines.length-r + l);
                }
                if(l+1 < wines.length){
                    System.out.println(wines[l]*(wines.length-r + l));
                    dp[l][r] = Math.max(dp[l][r], dp[l+1][r] + wines[l]*(wines.length-r + l));
                }
            }
        }
        
        for(int[] a : dp){
            for(int b : a){
                System.out.print(b + " ");
            }
            System.out.println();
        }
        
        System.out.println(dp[0][wines.length-1]);
    }
    //dp[l][r] stores the most we can get from interval l,r inclusive
    static void memo(int[] wines, int l, int r){
        int year = (wines.length-1-r + l) + 1; //starts at year one
        if(l == r){
            //or we can preinitialize all of this
            dp[l][r] = wines[r]*year;
            return;
        }
        int right = 0;
        if(l+1 < wines.length){
            if(dp[l+1][r] == 0){
                memo(wines, l+1, r);
            }
            right = dp[l+1][r] + year*wines[l];
        }
        int left = 0;
        if(r-1 >= 0){
            if(dp[l][r-1] == 0){
                memo(wines, l, r-1);
            }
            left = dp[l][r-1] + year*wines[r];
        }
        dp[l][r] = Math.max(left, right);
    }
}
