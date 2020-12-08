
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * -Take it slow, think- Watch out for: - Long/Int - Edge cases (make test case)
 * - Unexpected behavior?
 *
 * @author timothy
 */
public class snakes {

    static final long INF = 10000000000L;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("snakes.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("snakes.out")));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] nums = new int[N];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        long[][] dp = new long[N][K + 1]; //visualize as line segs
        for(int i = 0; i<dp.length-1; i++){
            Arrays.fill(dp[i], INF);
        }
        
        for (int i = N - 1; i >= 0; i--) {
            int max = nums[i];//Greedy Property: line should be as close to ground as possible
            int tot = 0;
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = 0; k < K; k++) {
                    dp[i][k] = Math.min(
                            dp[i][k],
                            tot + dp[j][k + 1]
                    );
                }
                if (nums[j] > max) {
                    tot += (j - i) * (nums[j] - max);
                    max = nums[j];
                } else {
                    tot += max - nums[j];
                }
            }
            dp[i][K] = tot;
        }

        pw.println(dp[0][0]);
        pw.close();
        br.close();
    }
}
