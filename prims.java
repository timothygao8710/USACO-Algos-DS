
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
// package Implementations;
/**
 * -Take it slow, think- Watch out for: - Long/Int - Edge cases (make test case)
 * - Unexpected behavior?
 *
 * @author timothy
 */
public class prims {
    static int prims(int no, int[][] edges, int start) {
        ArrayList<Integer>[] al = new ArrayList[no];
        ArrayList<Integer>[] weights = new ArrayList[no];

        for (int i = 0; i < no; i++) {
            al[i] = new ArrayList();
            weights[i] = new ArrayList();
        }

        for (int[] n : edges) {
            al[n[0] - 1].add(n[1] - 1);
            al[n[1] - 1].add(n[0] - 1);
            weights[n[1] - 1].add(n[2]);
            weights[n[0] - 1].add(n[2]);
        }

        Queue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {

            public int compare(int[] a, int[] b) {
                return a[1] - b[1];
            }

        });
        
        boolean[] visited = new boolean[no];
        int ret = 0;
        
        pq.add(new int[]{start-1, 0});
        while(no > 0){
            int[] curr = pq.poll();
            int node = curr[0];
            int cost = curr[1];
            
            if(visited[node]){
                continue;
            }
            
            ret += cost;
            no--;
            visited[node] = true;
            
            for(int i = 0; i<weights[node].size(); i++){
                pq.add(new int[]{al[node].get(i), weights[node].get(i)});
            }
        }
        return ret;
    }
}
