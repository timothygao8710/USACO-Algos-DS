/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Structures;

/**
 *
 * @author timothy
 */
public class UnionFind {
    //Offers:
    //Initialize: O(N)
    //Find: O(1) (ackerman)
    //Merge: O(1) (ackerman)

    static int[] weights;
    static int[] root;

    public static void initialize(int n) {
        for (int i = 0; i < n; i++) {
            root[i] = i;
            weights[i] = 1;
        }
    }

    //with path compression
    //alternative: path halving, path splitting
    public static int root(int curr) {
        if (root[curr] == curr) {
            return curr;
        }
        root[curr] = root(root[curr]);
        //        int start = curr;
//        while (curr != root[curr]) {
//            curr = root[curr];
//        }
//        while (start != curr) {
//            int next = root[start];
//            root[start] = curr;
//            start = next;
//        }
//        return curr;
        return root[curr];
    }

    //with weighting
    //alternative: by rank
    public static void union(int p, int q) {
        if (connected(p, q)) {
            return;
        }

        int rp = root(p);
        int rq = root(q);
        if (weights[rp] < weights[rq]) {
            weights[rq] += weights[rp];
            root[rp] = root[rq];
        } else {
            weights[rp] += weights[rq];
            root[rq] = root[rp];
        }
    }

    public static boolean connected(int p, int q) {
        return root(p) == root(q);
    }
}
