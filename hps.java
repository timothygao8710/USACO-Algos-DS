
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

// package dp;
/**
 * -Take it slow, think- Watch out for: - Long/Int - Edge cases (make test case)
 * - Unexpected behavior?
 *
 * @author timothy
 */
public class hps {

    static int[][][] dp;
    static int[] fj;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("hps.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("hps.out")));

//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] dp = new int[3][K + 1];
        int[] fj = new int[N];

        for (int i = 0; i < N; i++) {
            String curr = br.readLine();
            if (curr.equals("H")) {
                fj[i] = 0;
            } else if (curr.equals("P")) {
                fj[i] = 1;
            } else {
                fj[i] = 2;
            }
        }

        for (int i : fj) {
            int winAgainst = (i + 1) % 3;
            int[][] temp = new int[dp.length][dp[0].length];

            for (int j = 0; j < 3; j++) {
                int mod = (j == winAgainst) ? 1 : 0;
                for (int k = 0; k < K + 1; k++) {
                    if (k == K) {
                        temp[j][k] = dp[j][k] + mod;
                        continue;
                    }
                    temp[j][k] = Math.max(
                            dp[j][k], Math.max(dp[(j + 1) % 3][k + 1], dp[(j + 2) % 3][k + 1])
                    ) + mod;
                }
            }
            dp = temp;
        }
        int ret = 0;
        for (int[] a : dp) {
            for (int b : a) {
                ret = Math.max(b, ret);
            }
        }
//        System.out.println(ret);
        pw.println(ret);
        pw.close();
        br.close();
    }

}
