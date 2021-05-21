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
public class knapsack {

    static int[][] dp;
//http://www.cs.cmu.edu/afs/cs/academic/class/15451-f17/www/lectures/lec12-dp1.pdf

    public static void main(String[] args) {
        int[] values = new int[]{60, 100, 120,60, 100, 120};
        int[] weights = new int[]{10, 20, 30, 60, 100, 120};
        int max = 100;
        dp = new int[values.length][max + 1];
        for (int[] a : dp) {
            Arrays.fill(a, 0);
        }

        memo(0, values, weights, max, 0);

        for (int[] a : dp) {
            for (int b : a) {
                System.out.print(b + " ");
                if (b < 100) {
                    System.out.print("  ");
                }

            }
            System.out.println();
        }

        System.out.println(dp[0][max]);
        //It might be hard to find what the time complexity is here, and it seems realy large
        //However it's really not that bad
        //One good way to think abuot the time complexity of dp is to use the table
        //in this case the table is max by the number of items,
        //and those are all the possible scenarios
        //In fact it is less than that, if thinking about the recusion tree
        //So the time complexity is really not that bad!
        //O(Sn) n = object #, S = max

        //looking at the dp table can give us some ideas on how to do bottom-up
        //The following bottom up is in opposite order compared to the memo method:
        //Again we notice that each take depends on before(again remember this is opposite order from memo)
        //It depends on dp[index-1][weights] and dp[index-1][weights - weights[index]]
        //Since we can't really know exactly weights - weights[index], we can't skip checking any, so we must
        //check from 0 - max
        //but there's really no point of keeping a 2d array because we are going to have to copy all the elements from the previous row anyways
        int[] dp = new int[max + 1];
        for (int i = 0; i < weights.length; i++) {
            if (weights[i] > max) {
                continue;
                //later we'll do Math.max(dp[weights[i]] and that cant be done if weigths i is not even possible
            }
            //it should be able to go from 0
            int[] temp = new int[max+1];
            temp[weights[i]] = Math.max(dp[weights[i]], values[i]);

            for (int j = 0; j < dp.length - weights[i]; j++) {
                if (dp[j] != 0) {
//                    dp[j+weights[i]] = Math.max(dp[j+weights[i]], dp[j] + values[i]);
//Lmao the above won't work cuz it will run into previously considered dp index j + weight[i]
                    //finding the best option for the wegith - wether to choose it or not
//                    dp[j] = Math.max(dp[j - weights[i]] + values[i], dp[j]);
                    //there now we look back instaed of forward
                    //Wait boia you insane tho that wouldn't work either cuz you might trip other something you've just done again
                    //so we actually need another temp array, or just 2 arrays
                    
                    temp[j+weights[i]] = Math.max(dp[j] + values[i], temp[j + weights[i]]);
                }
                temp[j] = Math.max(dp[j], temp[j]);
            }
            dp = temp;
        }
        System.out.println(max(dp));
    }

    static int max(int[] arr) {
        int ret = 0;
        for (int i : arr) {
            System.out.print(i + " ");
            ret = Math.max(ret, i);
        }
        System.out.println();
        return ret;
    }

    public static void memo(int index, int[] values, int[] weights, int left, int val) {
        //choosing or not choosing depends on the next one
        if (index + 1 < values.length) {
            //choosing curr
            if (left - weights[index] > 0) {
                if (dp[index + 1][left - weights[index]] == 0) {
                    memo(index + 1, values, weights, left - weights[index], val + values[index]);
                }

                dp[index][left] = dp[index + 1][left - weights[index]];
            } else if (left - weights[index] == 0) {
                dp[index][left] = val + values[index];
//then we know for sure the rest will be negetive so we don't memo
            }

            //not choosing curr
            if (dp[index + 1][left] == 0) {
                memo(index + 1, values, weights, left, val);
            }

            dp[index][left] = Math.max(dp[index][left], dp[index + 1][left]);
        } else {
            if (left - weights[index] >= 0) {
                dp[index][left] = val + values[index];
            }
        }
    }
}
