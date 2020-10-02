
/**
 * -Take it slow, think-
 * Watch out for:
 * - Long/Int
 * - Edge cases (make test case)
 * - Unexpected behavior?
 *
 * @author timothy
 */
public class SegTree {
    //min
    //to modify for different purposes, refer to the "change" lines

    public long[] nodes;
    public long[] lazy; //lazy propagation: stores modifier for segment
    public int size;
    public long NONE = Long.MAX_VALUE; //change to not affect result

    public SegTree(long[] nums) {
        //https://codeforces.com/blog/entry/49939 - 2n non-null nodes, worst case 4n-1(4n with 1 index) total nodes
        //2n to go to next level(worst case could branch at right)
        //2(2n) - 1 to count all nodes of that level
        nodes = new long[4 * nums.length];
        lazy = new long[4 * nums.length];
        size = nums.length;

        build(nums, 1, 0, size - 1);
    }

    //O(N log(n))
    private long build(long[] nums, int i, int l, int r) {
        if (l == r) {
            nodes[i] = nums[l];
        } else {
            nodes[i] = build(nums, 2 * i, l, (l + r) / 2) + build(nums, 2 * i + 1, (l + r) / 2 + 1, r); //change here
        }
        return nodes[i];
    }

    //O(4 log(n))
    public long get(int l, int r) {
        return get(1, l, r, 0, size - 1, 0);
    }

    //sL: segment left
    //sR: segment right
    private long get(int i, int l, int r, int sL, int sR, long mod) {

        lazy[i] += mod;

        //no overlap
        if (l > sR || r < sL) {
            return NONE;
        }

        //complete overlap
        if (l == sL && r == sR) {
            return nodes[i] + lazy[i]; //change
        }

        //partial overlap: lazy modifier distributed
        //I'm making it so segment is updated on branch, not on touch
        nodes[i] += lazy[i];
        long temp = lazy[i];
        lazy[i] = 0;

        int mid = (sL + sR) / 2; //included in left

        //change
        return Math.min(
                get(2 * i, l, Math.min(r, mid), sL, mid, temp),
                get(2 * i + 1, Math.max(l, mid + 1), r, mid + 1, sR, temp)
        );
    }

    public void update(int l, int r, long amount) {
        update(1, l, r, 0, size - 1, amount);
    }

    private long update(int i, int l, int r, int sL, int sR, long mod) {
        //no overlap
        if (l > sR || r < sL) {
            return NONE;
        }

        //complete overlap
        if (l == sL && r == sR) {
            lazy[i] += mod;
            return nodes[i] + mod; //change
        }

        //partial overlap: lazy not updated
        int mid = (sL + sR) / 2; //inclusive for left

        //change:
        nodes[i] = Math.min(
                update(2 * i, l, Math.min(r, mid), sL, mid, mod),
                update(2 * i + 1, Math.max(l, mid + 1), r, mid + 1, sR, mod));

        return nodes[i];
    }
}
