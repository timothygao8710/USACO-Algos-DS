/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dp;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/**
 *
 * @author timothy
 */
public class LIS {
    //https://www.topcoder.com/thrive/articles/Dynamic%20Programming:%20From%20Novice%20to%20Advanced
    public static void main(String[] args){
        TreeSet<Integer> ts = new TreeSet();
        System.out.println(ts.floor(2));

        int[] nums = new int[]{1,2,3,0,0,0,8};
        
        //the above works but it only has n^2 time complexity
        //you might initally find the O(n) time for find disgusting, and it is. We can do better
        
        //indeed, we can. At first, we think of optimizing the dp solution. since all the elements must be visited
        //at least once, because we never know if it can help, the complexity must be at least O(N)
        //so maybe a better find method?
        //well the best runtime complexity for find is log(N). We can have element - longest so far or vice versa pairing,
        //but longest-so-far to element makes no sense cuz we have to check each element individually anyways so it's still
        //O(N) worst case. If it's element - longest so far it still doesn't work, because there's no guarantee that it will be sorted,
        //meaning it's not nessesarily true that a larger element must have a longer so far than smaller elements
        //and vice versa. So that doesn't work either... So what can we do?
        //when stuck, it's usaully good to look back at the problem and see if we can come up with any good observations
        //one observation we make is that for any decreasing subsequence,
        //we can regard it as the latest(smallest) num in that sequence.
        //So how does that help us?
        //well can essentailly view the entire array as just a bunch of decreasing subsequences(but one num can't be used multiple times, and
        //something like this: 543432 would result in 5432, 43 as the decreasing subsequences, as they are calculated
        //on the fly, which makes most sense when we are looking back after finding the answer)
        //and each time we encouter an element larger than the smallest element in the greatest stack, we create a new stack
        //eventually the answer is calculated by the number of stacks - or the number of times a "greater"operation is performed
        //and a new stack is added
        //reference 1: https://leetcode.com/problems/longest-increasing-subsequence/discuss/74824/JavaPython-Binary-search-O(nlogn)-time-with-explanation
        //reference 2: https://www.cs.princeton.edu/courses/archive/spring13/cos423/lectures/LongestIncreasingSubsequence.pdf
        
        List<Integer> stacks = new ArrayList();
        //we only care about the last one(smallest one) cuz we use that for comparison
        //but note if we want to find the acutlay substring we need the entrei stak beacsuse it keeps track of when
        //the new stack of created(see the diagram for ref 2)
        stacks.add(Integer.MIN_VALUE); // so we don't run into error for the first one
        //or we can just add the index 0 element and start at 1 but I'm too lazy to change lmfao
        for(int i : nums){
            if(i > stacks.get(stacks.size()-1)){
                stacks.add(i);
                //see if we have to create a new stack
                //we have to check this later for bs anyways
            }
            
            int left = 0; //not sure can
            int right = stacks.size()-1;  //for sure can
            while(left < right){
                int mid = (left + right)/2; //we want it to be the change maker, which is left in this case,so it dosnt get stuck
                if(stacks.get(mid) < i){
                    left = mid+1;
                }else{
                    right = mid;
                }
            }
            stacks.set(right, i);
        }
        System.out.println(stacks.size()-1); //the exception thing we made in the begging caused -1
        
        //result: accepted
        //runtime: N Log(N)
    }
    
    public int nSquaredSol(int[] nums){
        int[] dp = new int[nums.length];
        int global = 0;
        
        for(int i = 0; i<nums.length; i++){
            for(int j = 0; j<i; j++){
                //non-strictly increasing
                if(nums[j] <= nums[i]){
                    dp[i] = Math.max(dp[i], dp[j]);
                }
                //strictly increasing just change to <
            }
            dp[i]++;
            global = Math.max(global, dp[i]);
        }
        
        return global;
    }
}
