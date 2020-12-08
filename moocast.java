
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeSet;

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

public class moocast {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("moocast.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("moocast.out")));
        
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[] dists = new long[N];
        int[][] coords = new int[N][];
        for(int i = 0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            coords[i] = new int[]{Integer.parseInt(st.nextToken()), 
                Integer.parseInt(st.nextToken())};
        }
        Arrays.fill(dists, Long.MAX_VALUE);
        dists[0] = 0;
        long max = 0;
        for(int i = 0; i<N; i++){
            long min = Long.MAX_VALUE;
            int node = 0;
            for(int j = 0; j<N; j++){
                if(dists[j] == -1) continue;
                if(dists[j] < min){
                    node = j;
                    min = dists[j];
                }
            }
            max = Math.max(max, min);
            dists[node] = -1;
            for(int j = 0; j<N; j++){
                if(dists[j] == -1) continue;
                dists[j] = Math.min(dists[j], dist(coords[node], coords[j]));
            }
        }
        pw.println(max);
        pw.close();
        br.close();
    }
    
    static long dist(int[] a, int[] b){
        return (a[0]-b[0])*(a[0]-b[0]) + (a[1]-b[1])*(a[1]-b[1]);
    }
}
