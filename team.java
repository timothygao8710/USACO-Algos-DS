
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

// package dp;

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
public class team {//this really shouldn't be a plat problem...
    static int mod = (int)(1e9+9);
    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("team.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("team.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] FJ = new int[N];
        int[] FP = new int[M];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<N; i++){
            FJ[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<M; i++){
            FP[i] = Integer.parseInt(st.nextToken());
        }
        
        int[][] dp;
        int[][] last = new int[N+1][M+1];
        for(int[] a : last) Arrays.fill(a, 1);
        
        for(int k = 1; k<=K; k++){
            dp = new int[N+1][M+1];
            for(int i = N-1; i>=0; i--){
                for(int j = M-1; j>=0; j--){
                    dp[i][j] = dp[i][j+1] + dp[i+1][j];
                    dp[i][j] %= mod;
                    dp[i][j] = (dp[i][j] + mod - dp[i+1][j+1])%mod;
                    if(FJ[i] > FP[j]){
                        dp[i][j] += last[i+1][j+1];
                        dp[i][j] %= mod;
                    }
                }
            }
            last = dp;
        }
//        System.out.println(last[0][0]);
        pw.println(last[0][0]);
        pw.close();
        br.close();
    }
}
