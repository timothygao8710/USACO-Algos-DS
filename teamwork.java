
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
public class teamwork {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("teamwork.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("teamwork.out")));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        
        int[] nums = new int[N];
        int[] dp = new int[N];
        
        for(int i = 0; i<N; i++){
            nums[i] = Integer.parseInt(br.readLine());
        }
        
        for(int i = 0; i<N; i++){
            int max = 0;
            for(int j = 0; i-j >= 0 && j < K; j++){
                max = Math.max(nums[i-j], max);
                int bef = i-j-1 >= 0 ? dp[i-j-1] : 0;
                dp[i] = Math.max(dp[i], max*(j+1) + bef);
            }
        }
        
        pw.println(dp[dp.length-1]);
//        System.out.println(dp[N-1]);
        pw.close();
        br.close();
    }
    
}
