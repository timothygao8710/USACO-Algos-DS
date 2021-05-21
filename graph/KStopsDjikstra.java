/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 *
 * @author timothy
 */
//from leetcode
public class KStopsDjikstra {

    static Node[] nodes;

    public static int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        //djikstra's
        nodes = new Node[n];
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = new Node();
        }

        for (int[] a : flights) {
            nodes[a[0]].addConnect(a);
        }
        nodes[dst].dest = true;
        Queue<Node> possibles = new PriorityQueue();
        nodes[src].costToPoint = 0;
        nodes[src].steps = 0;
        possibles.add(nodes[src]);
        while (!possibles.isEmpty()) {
            Node curr = possibles.poll();
            if (curr.dest) {
                return curr.costToPoint;
            }
            if (curr.steps == K + 1) {
                continue;
            }

            for (int i = 0; i < curr.connections.size(); i++) {
                Node consider = curr.connections.get(i);
                if (consider.costToPoint > curr.costToPoint + curr.costs.get(i) || curr.steps + 1 < consider.steps) {
                    if (!consider.touched) {
                        consider.costToPoint = curr.costs.get(i) + curr.costToPoint;
                        consider.steps = curr.steps + 1;
                        possibles.add(consider);
                        consider.touched = true;
                    } else {
                        possibles.add(new Node(consider, curr.steps + 1, curr.costToPoint + curr.costs.get(i)));
                    }
                }

            }
        }
        return -1;
    }

    static class Node implements Comparable<Node> {

        public boolean dest = false;
        public List<Node> connections;
        public List<Integer> costs;
        public int steps = Integer.MAX_VALUE;
        public int costToPoint = Integer.MAX_VALUE;
        public boolean touched = false;

        public Node() {
            costs = new ArrayList<>();
            connections = new ArrayList<>();
        }

        public Node(Node copy, int steps, int costToPoint) {
            connections = copy.connections;
            costs = copy.costs;
            this.steps = steps;
            this.costToPoint = costToPoint;
            this.dest = copy.dest;
        }

        public void addConnect(int[] connect) {
            connections.add(nodes[connect[1]]);
            costs.add(connect[2]);
        }

        public int compareTo(Node other) {
            return this.costToPoint - other.costToPoint;
        }
    }
}
