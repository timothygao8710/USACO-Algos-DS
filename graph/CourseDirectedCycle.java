/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author timothy
 */
public class CourseDirectedCycle {
    static boolean visited[];
    static ArrayList<Integer>[] nodes;
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        visited = new boolean[numCourses];
        nodes = new ArrayList[numCourses];
        for(int i = 0; i<nodes.length; i++){
            nodes[i] = new ArrayList();
        }
        
        for(int i = 0; i<prerequisites.length; i++){
            nodes[prerequisites[i][0]].add(prerequisites[i][1]);
        }
        
        for(int i = 0; i<nodes.length; i++){
            if(!visited[i] && dfs(i, new HashSet())){
                return false;
            }
        }
        return true;
    }
    
    static boolean dfs(int curr, Set<Integer> cycle){ //set has the current path
        //after reading other solutions I realized making visited int array with 0 - not visited
        //1 - visited, not on path and 2 - visited, on path would be better than hashset
        visited[curr] = true;
        cycle.add(curr);
        for(int next : nodes[curr]){
            if(visited[next]){
                continue;
            }
            if(cycle.contains(next) || dfs(next, cycle)){
                return true;
            }
        }
        cycle.remove(curr);
        return false;
    }
}

