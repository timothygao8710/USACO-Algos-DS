/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dp;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author timothy
 */
public class partitionproblem {
    
    public static void main(String[] args){
        int[] arr = new int[]{1, 5, 11, 5};
        
        System.out.println(selfTry(arr));
        //I think my self-try actually worked :o
    }
    
    static boolean selfTry(int[] arr){
        //self-try
        int sum = 0;
        for(int i = 0; i<arr.length; i++){
            sum += arr[i];
        }
        if(sum % 2 == 1){
            return false;
        }
        sum/=2;
        //we want to know if we can make half the array sum with some selection of elements, because the other half
        //will then be guaranteed to be the same
        //Similarly, one can prove that it's not possible for the two partitions to be equal if they are not both equal to half
        //the array sum, because one will be larger and one will be smaller than the sum
        //5 - 5
        //6 - 4
        //7 - 3
        //kinda that logic if you know what I'm saying
        
        boolean[] dp = new boolean[sum+1];
        //array marks the possible sums that can be reached up the the current index we're considering
        dp[0] = true;
        //you can always get a sum of 0
        
        for(int curr : arr){
            if(curr > sum){
                continue;
            }
            
            boolean[] temp = new boolean[sum+1]; //learning a lesson from the knapsack
            //again we can at most pick it one time, so we don't want to "trip" over a value
            
            for(int i = 0; i<=sum - curr; i++){
                if(dp[i]){
                    temp[curr+i] = true;
                }
                if(temp[i]){
                    dp[i] = true;
                }
            }
            
            for(int i = sum-curr+1; i<=sum; i++){
                if(temp[i]){
                    dp[i] = true;
                }  
            }
        }
        return dp[sum];
        //in a way this problem is simpler than knapsack, because we don't have to get the greater int:weight value:
        //as long as it's possible to get to a certain sum than we want to immediately set it as true, not worring about Math.max
        //this also means that as soon as it's possible to reach sum, we can return true
        //this means that if in the end after all the considerations sum has not been reached then sum is not possible 
    }
}
