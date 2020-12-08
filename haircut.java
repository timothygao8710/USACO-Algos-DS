import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;


public class haircut {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("haircut.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("haircut.out")));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[][] sorted = new int[N][2];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            sorted[i] = new int[]{i, Integer.parseInt(st.nextToken())};
        }
        long[] prefix = new long[N+1];
        BIT bit = new BIT(N);

        Arrays.sort(sorted, (int[] a, int[] b) -> a[1] == b[1] ? a[0] - b[0] : a[1] - b[1]);
        for (int[] a : sorted) {
            prefix[a[1]] += bit.sum(a[0]);
            bit.update(a[0], -1);
        }
        
        pw.println(0);
        for (int i = 1; i < N; i++) {
            pw.println(prefix[i-1]);
            prefix[i] += prefix[i - 1];
        }
        
        br.close();
        pw.close();
    }

    static class BIT {

        public long[] bit;

        public BIT(int N) {
            bit = new long[N + 1];
            for (int i = 1; i <= N; i++) {
                bit[i]++; //update node
                if (i + (i & -i) <= N) {
                    bit[i + (i & -i)] += bit[i]; //update parent
                }

            }
        }

        public long sum(int r) {
            long ret = 0;
            while (r > 0) {
                ret += bit[r];
                r -= r & -r;
            }
            return ret;
        }

        //updates # at pos nums[idx]
        //does not reset nums[idx], but updates it (+/-)
        public void update(int idx, long v) {
            idx++;
            while (idx < bit.length) {
                bit[idx] += v;
                idx += idx & -idx;
            }
        }
    }
}
