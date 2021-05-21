// package Structures;

public class rangeBIT {
    //because range, we need to use pref sum for updates too
    //all shadow lefts
    public BIT updates;
    public BIT pref;
    
    public rangeBIT(int N){
        updates = new BIT(N);
        pref = new BIT(N);
    }
    
    public long sum(int r){
        return (
            updates.sum(r)*r -
            pref.sum(r)
        );
    }
    //+/-
    public void update(int l, int r, int x){
        updates.update(l, x);
        updates.update(r+1, -x);
        pref.update(l, -(l-1)*x);
        pref.update(r+1, r*x);
    }
    
    static class BIT {

        public long[] bit;

        public BIT(int N) {
            bit = new long[N + 1];
        }
        
        public long sum(int r) {
            r++;
            long ret = 0;
            while (r > 0) {
                ret += bit[r];
                r -= r & -r;
            }
            return ret;
        }

        public void update(int idx, long v) {
            idx++;
            while (idx < bit.length) {
                bit[idx] += v;
                idx += idx & -idx;
            }
        }
    }
}
