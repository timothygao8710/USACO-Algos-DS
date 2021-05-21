/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author timothy
 */
public class ShortestPathDAG {
    //two approaches:
    /*
    Lazy: this is the topo sort way. As you topo sort, also keep track of the current shortest path up to that point,
    and go top down. 
    
   In other words, we want to go in order of the topo sort, because if we have visited a node that means
    all the nodes going up to that point has been consdiered, and their edges. Conceptually, it's kind of top down,
    with the top-most node(root(s)) having 0 as the beggining min
    
    If there are two indegrees, we set a "tentative" value to the node, and take min/max for the other indegs
    as we encouter those edges
    
    Runtime: O(2V+E) as we visit all the nodes once in the beggining to search for starting root nodes,
    and also in total we go through the entire tree once, which is V+E.
    
    Eager: In the approach, we take advantage of the tree property. So we can acutally do a little dp.
    FOr the first method, when we encouter a node, we only "run" it if it's a root node(inDeg 0), but for this
    approach, when we encouter a node, we immediatly run it. However, this run is a bit different.
    
    For the lazy run, what we did was we went in order and relaxed vertex values, but for this run,
    we actulaly recursively find child tree nodes. Because of the tree property, we can say that
    the min path assuming the current is root is min of(x1, x2, x3...),
    where x1 is (the result of same function called on child 1) + (the edge cost from root to child 1)
    and x2 and x3 are the same except not child 1. Then we store that result in dp, and when another 
    root wants to know the function called on this node, we can just return the result
    
    actually this does not work lmaoo. Because a DAG is not stricly a tree. This is a possiblility that 
    a node has 2 inDegs
    */
    
    public static void main(String[] args){
            System.out.println((int)'`' + " " + (int)'a');
        
    }
    
    static void shortest(int[] inDegs, ArrayList<Integer>connects[], ArrayList<Integer>ws[]){ //Note: we cannot just use this for all pair shortest path
        Queue<Integer> bfs = new LinkedList();
        int[] tentative = new int[inDegs.length];
        Arrays.fill(tentative, Integer.MAX_VALUE);
        for(int i = 0; i<inDegs.length; i++){
            if(inDegs[i] == 0){
                bfs.add(i);
                tentative[i] = 0;
            }
        }
        int ret = Integer.MAX_VALUE;
        while(!bfs.isEmpty()){
            int curr = bfs.poll();
            if(connects[curr].isEmpty()){
                ret = Math.min(ret,  tentative[curr]);
                continue;
            }
            
            for(int i = 0; i<connects.length; i++){
                int next = connects[curr].get(i);
                inDegs[next]--;
                if(inDegs[next]== 0){
                    bfs.add(next);
                }
                tentative[next] = Math.min(tentative[next], ws[curr].get(i));
            }
        }
        
        
    }
}
