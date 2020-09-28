/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Implementations;

/**
 *
 * @author timothy
 */
public class QuickFind {
    //not really helpful, since quick union does essentially the same thing
    //but with way lower union
    //Create - O(n)
    //Union - O(n)
    //Find - O(1)
    
    static int[] groups;
    public void initialize(int n){
        for(int i = 0; i< n; i++){
            groups[i] = i;
        }
    }
    
    public void union(int p, int q){
        if(connected(p, q)){
            return;
        }
        int qG = groups[q]; //q group
        //we have to do this because q might be stepped over and changed
        //in our for loop, making it wrong to just use groups[q]
        for(int i = 0; i<groups.length; i++){
            if(groups[i] == qG){
                groups[i] = groups[p];
            }
        }
    }
    
    public boolean connected(int p, int q){
        return groups[p] == groups[q];
    }
}
