
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

// package dp;

/**
 * -Take it slow, think-
 * Watch out for:
 * - Long/Int
 * - Edge cases (make test case)
 * - Unexpected behavior?
 *
 * @author timothy
 */
public class time {
    static int[] gains;
    static ArrayList<Integer>[] adj;
    static Integer[][] dp;
    static final int NEG = -1000000000;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("time.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("time.out")));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        
        gains = new int[N];
        adj = new ArrayList[N];
        dp = new Integer[N][1001];
        
        for(Integer[] a : dp){
            a[1000] = NEG;
        }
        
        dp[0][1000] = -1_000_000;
        
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<N; i++){
            adj[i] = new ArrayList();
            gains[i] = Integer.parseInt(st.nextToken());
        }
        
        for(int i = 0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            adj[Integer.parseInt(st.nextToken())-1].add(Integer.parseInt(st.nextToken())-1);
        }
        
//        System.out.println(memo(0, 0));
        pw.println(memo(0,0,C));
        pw.close();
        br.close();
    }
    
    static int memo(int node, int steps, int C){
        if(dp[node][steps] != null) return dp[node][steps];
        int ret = node == 0 ? 0 : NEG;
        for(int next : adj[node]){
            ret = Math.max(ret, memo(next, steps+1, C) - (C*steps*2 + C));
        }
        dp[node][steps] = ret + gains[node];
        return dp[node][steps];
    }
}
