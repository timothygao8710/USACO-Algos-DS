
import java.util.ArrayList;

public class lca {

    static int[] heights; //generated via euler tour
    static int[] tour; //needed to return node from tour
    static int[] nodeTo; //needed to find l,r bound for heights
    static ArrayList<Integer>[] adj;
    static SegmentTree sgt;

    static int idx; //helper for euler

    public lca(ArrayList<Integer>[] graph) {
        adj = graph;
        heights = new int[2 * (heights.length - 1)]; //each edge is used twice
        tour = new int[2 * (heights.length - 1)];
        nodeTo = new int[graph.length];

        idx = 0;
        euler(0, 0, -1);

        sgt = new SegmentTree();
    }

    //@return int[]{a,b} a is node, b is height(increasing as we go down)
    public int[] query(int u, int v) {
        int l = Math.min(nodeTo[u], nodeTo[v]);
        int r = Math.max(nodeTo[u], nodeTo[v]);
        return sgt.get(1, 0, tour.length - 1, l, r);
    }

    private void euler(int node, int height, int last) {
        heights[idx] = height;
        tour[idx] = node;
        nodeTo[node] = idx;
        idx++;

        for (int i : adj[node]) {
            if (i == last) {
                continue;
            }

            euler(i, height + 1, node);

            heights[idx] = height;
            tour[idx] = node;
            nodeTo[node] = idx;
            idx++;
        }
    }
    
    static class SegmentTree {

        public int[] sgt; //nodes in sgt representing heights in tour
        public int[] tourNodes; //nodes in the sgt representing nodes in the tour

        public SegmentTree() {
            sgt = new int[heights.length * 4];
            tourNodes = new int[sgt.length];
            build(1, 0, tour.length - 1);
        }

        private int[] build(int idx, int l, int r) {
            if (l == r) {
                tourNodes[idx] = tour[l];
                sgt[idx] = heights[l];
            } else {
                int[] left = build(idx * 2, l, (l + r) / 2);
                int[] right = build(idx * 2 + 1, (l + r) / 2 + 1, r);
                if (left[1] > right[1]) {
                    tourNodes[idx] = left[0];
                    sgt[idx] = left[1];
                } else {
                    tourNodes[idx] = right[0];
                    sgt[idx] = right[1];
                }
            }
            return new int[]{tourNodes[idx], sgt[idx]};
        }

        public int[] get(int idx, int gL, int gR, int l, int r) {
            if (gL > r || gR < l) {
                return new int[]{-1, -1};
            }
            if (l == gL && r == gR) {
                return new int[]{tourNodes[idx], sgt[idx]};
            }

            int[] left = get(idx * 2, gL, (gL + gR) / 2, l, Math.min((gL + gR) / 2, r));
            int[] right = get(idx * 2 + 1, (gL + gR) / 2 + 1, gR, Math.max(l, (gL + gR) / 2 + 1), r);

            if (left[1] > right[1]) {
                return left;
            } else {
                return right;
            }
        }
    }
}
