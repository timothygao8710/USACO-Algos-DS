/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;
//it's not just simple sort from least indegree to least outdegree
//or least in-out degree to in - our deg

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author timothy
 */
public class CourseIITopoSort {
    /*
    We do not need a visited array. Note that topological sort alwys finds inDeg of 0,
    and in a cycle, every node will have indree of at least 1, so it's not possible for any of them 
    to be visited in the first place. This means that we simply have to check if every node is visited, whihc can be done
    if we just check if i is at the end.
    
    This also means that we can choose dfs or bfs, as what "path" we are on doesn't actully matter
    
    Similarly, it's not possible to visit an already possible node, because us to be able to visit taht node,
    it must have at least 1 inDeg left
    The only time when we need to be concerned about the visited array at all is when we decide whihch
    node to bfs/dfs on, whihc we can do simply by jsut setting those nodes as
    inDeg of -1 or some arbitrary number that's not 0.
    */
    
    static int i;
    static int[] res;
    static int[] inDeg;
    static ArrayList<Integer>[] connec;

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        res = new int[numCourses];
        connec = new ArrayList[numCourses];
        
        for(int i = 0; i<connec.length; i++){
            connec[i] = new ArrayList();
        }
        
        for(int i = 0; i<prerequisites.length; i++){
            connec[prerequisites[i][0]].add(prerequisites[i][1]);
            inDeg[prerequisites[i][1]]++;
        }
        
        for(int i = 0; i<numCourses; i++){
            if(inDeg[i] == 0){
                dfs(i);
            }
        }
        if(i == numCourses){
            return res;
        }
        return new int[]{};
    }
    
    //somethign like this
//    static void bfs(){
//        int index = 0;
//        //or we can do bfs
//        //the fact that previous comes before still applies
//        Queue<Integer> NoDeg = new LinkedList();
//        for(int i = 0; i<inDeg.length; i++){
//            if(inDeg[i] == 0){
//                NoDeg.add(i);
//            }
//        }
//        
//        while(!NoDeg.isEmpty()){
//            res[index] = NoDeg.poll();
//            for(int next : connec[res[index]]){
//                inDeg[next]--;
//                if(inDeg[next] == 0){
//                    NoDeg.add(next);
//                }
//            }
//            index++;
//        }
//        
//        if(index == inDeg.length){
//            return new int[]{};
//        }else{
//            return res;
//        }
//    }
//the order will be different but the topological ordering will stay the same
}
