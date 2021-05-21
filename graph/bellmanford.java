
import java.util.Arrays;

// package graph;

/**
 * -Take it slow, think-
 * Watch out for:
 * - Long/Int
 * - Edge cases (make test case)
 * - Unexpected behavior?
 *
 * @author timothy
 */
public class bellmanford {
    //returns -69696969 if negative cycle exists
    public int shortestPath(int n, int[][] edges, int u, int v){
        int[] min = new int[n];
        
        Arrays.fill(min, Integer.MAX_VALUE);
        min[u] = 0;
        
        for(int i = 0; i<n-1; i++){ //longest path is n-1 length
            for(int[] e : edges){
                min[e[1]] = Math.min(min[e[1]], e[3] + min[e[0]]); //relaxation
            }
        }
        
        for(int[] e : edges){
            if(min[e[1]] > min[e[0]] + e[2]){
                return -69696969;
            }
        }
        
        return min[v];
    }
}
