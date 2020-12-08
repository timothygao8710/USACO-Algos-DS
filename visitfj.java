
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// package graph;
/**
 * - Long/int - Draw stuff - Use comments - Drink Water - Reread Problem
 *
 * @author timothy
 */
public class visitfj {

    static int[] xDir = new int[]{0, 0, 1, -1};
    static int[] yDir = new int[]{1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("visitfj.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("visitfj.out")));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        int[][] grid = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Queue<long[]> dj = new PriorityQueue(new Comparator<long[]>() {

            public int compare(long[] a, long[] b) {
                if (a[0] > b[0]) {
                    return 1;
                }
                return -1;
            }

        });

        dj.add(new long[4]);
        long[][][] visited = new long[N][N][3];
//        for (long[][] a : visited) {
//            for (long[] b : a) {
//                Arrays.fill(b, Long.MAX_VALUE);
//            }
//        }

//        visited[0][0][0] = 1;

        while (!dj.isEmpty()) {
            long steps = dj.peek()[0];
            int y = (int) dj.peek()[1];
            int x = (int) dj.peek()[2];
            int mod = (int) dj.poll()[3];
//            System.out.println(steps + " " + y + " " + x + " " + mod);
            if(visited[y][x][mod] == 1) continue;
            if (y == N - 1 && x == N - 1) {
                pw.print(steps);
//                System.out.println(steps);
                break;
            }
            visited[y][x][mod] = 1;
            mod = (mod + 1) % 3;
            steps += T;

            for (int i = 0; i < 4; i++) {
                int dY = y + yDir[i];
                int dX = x + xDir[i];

                if (dY < N && dX < N && dY >= 0 && dX >= 0) {
                    long temp = steps + ((mod == 0) ? grid[dY][dX] : 0);
                    if (visited[dY][dX][mod] == 1) {
                        continue;
                    }
                    dj.add(new long[]{
                        temp,
                        dY,
                        dX,
                        mod
                    });
                }
            }
        }
        pw.close();
        br.close();
    }
}
