// package Structures;

class BIT { //AKA BIT

    public long[] bit;

    //O(N) build
    public BIT(long[] nums) {
        bit = new long[nums.length + 1]; //one-indexed
        for (int i = 1; i <= nums.length; i++) {
            bit[i + i & -i] += nums[i - 1];
        }
    }

    //sum [l,r]
    public long sum(int l, int r) {
        return sum(r + 1) - sum(l);
    }

    //sum prefix
    private long sum(int r) {
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