
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// package graph;

/**
 * - Long/int
 * - Draw stuff
 * - Use comments
 * - Drink Water
 * - Reread Problem
 *
 * - package/pw.close
 *
 * @author timothy
 */
public class walk {
    static int mod = 2019201997;
    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        BufferedReader br = new BufferedReader(new FileReader("walk.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("walk.out")));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        
        Queue<Long> pq = new PriorityQueue();
        for(long i = 1; i < N; i++){
            for(long j = i+1; j<=N; j++){
//                long a = 2019201913L -(i-1)*(84L);
//                long b = 2019201949L -(j-1)*(48L);
                long a = 2019201913L*i;
                a %= mod;
                long b = 2019201949L*j;
                b %= mod;
                pq.add((a+b)%mod);
                
                if(pq.size() > K){
                    pq.remove();
                }
            }
        }
//        System.out.println(pq.peek());
/*
The reason why this doesn't work is if we have cycles/connected components in our graph.
In that case, we already counted the max edge, so one of them would be redundunt if you know what I'm saying
*/

//the answer is the kth smallest edge on the max spanning tree
        pw.println(pq.peek());
        pw.close();
        br.close();
    }
}
