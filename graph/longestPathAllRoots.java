
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// package graph;
/**
 * -Take it slow, think- Watch out for: - Long/Int - Edge cases (make test case)
 * - Unexpected behavior?
 *
 * @author timothy
 */
//https://cses.fi/problemset/task/1132/
public class longestPathAllRoots {

    static int[][] gPaths;
    static int[] ret;
    static ArrayList<Integer>[] nodes;
    static int[] gNode;
    //we need this because when passing down top info to children,
    //we pass down max(maxofchildren, maxfromcurrtop) but if maxofchildren is the
    //currnode then we want to pass the 2ndmaxfrmo top instead and so we use
    //gNode to indicate where each node got its maxfromcurr from

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        nodes = new ArrayList[N + 1];
        gPaths = new int[N + 1][2];
        ret = new int[N + 1];
        gNode = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            nodes[i] = new ArrayList();
        }

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            nodes[u].add(v);
            nodes[v].add(u);
        }

        dfs(1, -1);
        findPaths(1, -1, -1);

        for (int i = 1; i<ret.length; i++) {
            System.out.print(ret[i] + " ");
        }
    }

    static void findPaths(int u, int last, int above) {
        ret[u] = Math.max(gPaths[u][0], above+1);
        
        for (int i : nodes[u]) {
            if (i == last) {
                continue;
            }
            findPaths(i, u, Math.max(above+1,
                    i == gNode[u] ? gPaths[u][1] : gPaths[u][0]
            )
            );
        }
    }

    static int dfs(int u, int last) { //first pass: subtrees
        int first = 0;
        int second = 0;

        for (int i : nodes[u]) {
            if (i == last) {
                continue;
            }
            int great = dfs(i, u) + 1;
            if (great > first) {
                gNode[u] = i;
                second = first;
                first = great;
            } else if (great > second) {
                second = great;
            }
        }

        gPaths[u] = new int[]{first, second};
        return first;
    }

}
