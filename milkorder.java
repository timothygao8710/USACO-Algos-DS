
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * -Take it slow, think- Watch out for: - Long/Int - Edge cases (make test case)
 * - Unexpected behavior?
 *
 * @author timothy
 */
class milkorder {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("milkorder.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("milkorder.out")));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] ordering = new int[M][];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int cN = Integer.parseInt(st.nextToken());
            int[] cA = new int[cN];
            for (int j = 0; j < cN; j++) {
                cA[j] = Integer.parseInt(st.nextToken());
            }
            ordering[i] = cA;
        }

        int l = 1;
        int r = M;

        while (l < r) {
            int mid = (l + r + 1) / 2;
            if (isGood(ordering, mid, N) != null) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }

        pw.println(isGood(ordering, l, N));
        pw.close();
        br.close();
    }

    static String isGood(int[][] order, int r, int N) {
        int[] inDeg = new int[N + 1];
        ArrayList<Integer>[] adj = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList();
        }

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < order[i].length - 1; j++) {
                adj[order[i][j]].add(order[i][j + 1]);
                inDeg[order[i][j + 1]]++;
            }
        }

        StringBuilder sb = new StringBuilder();
        Queue<Integer> pq = new PriorityQueue();

        for (int i = 1; i <= N; i++) {
            if (inDeg[i] == 0) {
                pq.add(i);
            }
        }

        int visited = 0;
        while (!pq.isEmpty()) {
            int curr = pq.poll();
            sb.append(curr + " ");
            visited++;
            for (int n : adj[curr]) {
                inDeg[n]--;
                if (inDeg[n] == 0) {
                    pq.add(n);
                }
            }
        }
        if (visited < N) {
            return null;
        }
        return sb.substring(0, sb.length() - 1);
    }

}
