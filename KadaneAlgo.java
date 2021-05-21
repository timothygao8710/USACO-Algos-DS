/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author tim
 */
public class KadaneAlgo {
    //Given an array with positive and negetive numbers, find the largest contiguous subarray(subarray with largest sum)
    //https://www.geeksforgeeks.org/largest-sum-contiguous-subarray/
    //https://leetcode.com/explore/challenge/card/30-day-leetcoding-challenge/528/week-1/3285/discuss/561639/Simplest-O(n)-time-java-solution-beats-100.
    public static void main(String[] args){
        int N = 69; //N is arbitrary
        int[] testcase = new int[N];
        
        //Algo:
        int max = 0; //to keep track of max as you work your way through array
        int currwindow = 0; //to keep track of the sum of the current window that you're considering
        
        for(int i = 0; i<testcase.length; i++){
            //at each point let's make a decision: to include or exclude the current one 
            currwindow += testcase[i]; //we try including the current in the currwindow.
            //note the previous windows have already been considered in max
            if(currwindow < 0){
                //then we don't want to include it
                //but since the sum has to be contiguous we must start a new window:
                currwindow = 0; 
                //this step is key
                //essentially what it's saying is that if the current window is negetive, we want to start a new one
                //proof of correctness:
                //the "negetive window" will not be part of any solution, because that solution can exclude the negetive window for a new optimal
            }
            //we always want to update the max
            max = Math.max(currwindow, max);
        }
        
        //return max
        //max has kept track of the maximum positive subarray
    }
    
    //for 2D
    
    //Observation: when in 2d, it's often helpful to reduce to a 1d problem
    //if we were already given 2 points, thus completely satisfying a dimension, then we will only have
    //1 dimension left!
    
    //In other words, it we fix the left/right column, then finding the max will only be in y
    //fixing one dimension -> reduces dimension
    //or, we can fix the up/down(y) rows, and finding the max will only be in x
    
    //Prefix sum for 2d there are 2 ways
    //both are O(n^2)'
    //the 2nd(non standard way is like this)
    public int[][] prefix(int[][] grid){
        int[][] prefix = new int[grid.length+1][grid[0].length+1]; //for out of bounds
        for(int i = 1; i<prefix.length; i++){
            int[] temp = new int[prefix[0].length];
            //but for this version we can't set it immediatly asit will ruin the ones after
            for(int j =1;j<prefix[0].length; j++){
                temp[j] += grid[i-1][j-1];
            }
            //we first have the row prefx sum then add each point with the lsat
            
            for(int j = 1; j<prefix[0].length; j++){
                prefix[i][j] = temp[j] + prefix[i-1][j];
            }
        }
        return prefix;
    }
    //but if we already fixed the left and right cols then we don't have to 
    //prefix to anything, thus we don't need to worry about ruining the later ones
    
    public int maxRect(int[][] grid){
        for(int i = 0; i<grid.length; i++){
            for(int j = 1; j<grid[0].length; j++){
                grid[i][j] += grid[i][j-1];
            }
        }
        //grid is now prefix(though not the regular kind)
        int ret = 0;
        //edge-case for x1 = 0 since out of bounds will happen if we do regular
        for(int x2 = 0; x2 < grid[0].length; x2++){
            int currSum = 0;
            for(int y = 0; y<grid.length; y++){
                currSum += grid[y][x2];
                ret = Math.max(ret,currSum);
                currSum = Math.max(currSum, 0);
            }
        }
        
        for(int x1 = 1; x1 < grid[0].length; x1++){
            for(int x2 = x1; x2 < grid[0].length; x2++){
                int currSum = 0;
                for(int y = 0; y<grid.length; y++){
                    currSum += grid[y][x2] - grid[y][x1-1]; //again, we are mesasuring increases
                    ret = Math.max(currSum, ret);
                    currSum = Math.max(currSum, 0);
                }
            }
        }
        return ret;
    }
}
