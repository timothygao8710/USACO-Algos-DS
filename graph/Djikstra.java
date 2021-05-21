package graph;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 *
 * @author timothy
 */

//Idea: The local mininum path is definitly part of the minimum path, so it leads to the global minimum path
public class Djikstra {
    static long[] dijk(ArrayList<int[]>[] adj, int u) {
        long[] dist = new long[adj.length];
        Arrays.fill(dist, -1L);
        Queue<long[]> pq = new PriorityQueue<>((long[] a, long[] b) -> Long.compare(a[1], b[1]));
        pq.add(new long[]{u, 0L});
        while (!pq.isEmpty()) {
            long[] cur = pq.poll();
            if (dist[(int) cur[0]] != -1) {
                continue;
            }
            dist[(int) cur[0]] = cur[1];
            for (int[] n : adj[(int) (cur[0])]) {
                pq.add(new long[]{n[0], n[1] + cur[1]});
            }
        }
        return dist;
    }
}
