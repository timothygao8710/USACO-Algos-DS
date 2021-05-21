// package graph;

import java.util.ArrayList;
import java.util.List;

public class bridgefind { //see https://codeforces.com/blog/entry/68138
    static List<Integer> ret;
    static void findbridge(ArrayList<Integer>[] adj){
        ret = new ArrayList(); //each int means up-edge from node is a bridge
        build(adj, new int[adj.length], new int[adj.length], 0, -1); //feel free to change to 1, 0
    }
    
    //0 - untouched. 1 - parent. 2 - children.
    static void build(ArrayList<Integer>[] adj, int[] dp, int[] vis, int cur, int par){
        vis[cur] = 1;
        for(int n : adj[cur]){
            if(n == par || vis[n] == 2) continue;
            if(vis[n] == 1){
                dp[cur]++;
                dp[n]--;
            }else{
                build(adj, dp, vis, n, cur);
                dp[cur] += dp[n]; 
            }
        }
        vis[cur] = 2;
        if(dp[cur] == 0 && cur != 0) ret.add(cur); //cur != 0 because each edge is identified by children. change this is 1 is root.
    }
}
