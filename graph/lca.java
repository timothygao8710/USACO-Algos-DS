
import java.util.ArrayList;

public class lca {

    public int idx;
    public int[] tins;
    public int[] deps;
    public int[] tour;
    public sparse table;
    
    public lca(ArrayList<Integer>[] adj, int N) {
        idx = 0;
        tins = new int[N];
        tour = new int[4 * N]; //more than enough
        deps = new int[tour.length];
        euler(adj, 0, -1, 0);
        table = new sparse(deps);
    }

    public int get(int u, int v) {
        return tour[table.get(
                Math.min(tins[u], tins[v]),
                Math.max(tins[u], tins[v])
        )];
    }

    public void euler(ArrayList<Integer>[] adj, int curr, int last, int dep) {
        tins[curr] = idx;
        tour[idx] = curr;
        deps[idx] = dep;
        idx++;

        for (int n : adj[curr]) {
            if (n == last) {
                continue;
            }
            euler(adj, n, curr, dep + 1);
            tour[idx] = curr;
            deps[idx] = dep;
            idx++;
        }
    }

    class sparse {

        public int[][][] table; //also stores info on index

        public sparse(int[] nums) {
            int maxFit = maxPow(nums.length);
            table = new int[maxFit][nums.length][2];

            for (int i = 0; i < nums.length; i++) {
                table[0][i] = new int[]{i, nums[i]};
            }

            for (int i = 1; i < maxFit; i++) {
                for (int j = 0; j + (1 << i) - 1 < nums.length; j++) {
                    int[] left = table[i - 1][j];
                    int[] right = table[i - 1][j + (1 << (i - 1))];
                    if (left[1] < right[1]) {
                        table[i][j] = left;
                    } else {
                        table[i][j] = right;
                    }
                }
            }
        }

        public int get(int l, int r) {
            int max = maxPow(r - l + 1) - 1; //maxpow includes 0th
            int[] left = table[max][l];
            int[] right = table[max][r - (1 << max) + 1];
            if (left[1] < right[1]) {
                return left[0];
            } else {
                return right[0];
            }
        }

        //returns the power not the number
        private int maxPow(int num) {
            int ret = 0;
            while (num > 0) {
                num >>= 1;
                ret++;
            }
            return ret;
        }
    }
}
