/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Implementations;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * @author timothy
 */

//Idea: The local mininum path is definitly part of the minimum path, so it leads to the global minimum path
public class Djikstra {
    public static void main(String[] args){
        int start = 2;
        int N = 5;
        int[][] connections = {{2,1,1},{2,3,1},{3,4,3}, {1,0,1}, {4,0,1}};
        Node[] graph = new Node[N];
        for(int i = 0; i<graph.length; i++){
            graph[i] = new Node(i);
        }
        
        for(int i = 0; i<connections.length; i++){
            graph[connections[i][0]].connections.add(graph[connections[i][1]]);
            graph[connections[i][1]].connections.add(graph[connections[i][0]]);
            
            graph[connections[i][0]].costs.add(connections[i][2]);
            graph[connections[i][1]].costs.add(connections[i][2]);
        }
        initialize(graph, start);
        for(int i = 0; i<N; i++){
            if(i == start){
                continue;
            }
            List<Integer> a = path(graph[start], graph[i], graph);
            for(int b : a){
                System.out.print(a);
            }
            System.out.println();
        }
    }
    
    //with path finding, SSSP
    public static void initialize(Node[] nodes, int start){
//        int[] parent = new int[nodes.length]; //Path printing
//don't need, in node class
//        int[] minTo = new int[nodes.length]; 
        //no need for visited array, just check if greater then minTo
        //no negetive cycles, so cycle will always be greater
        //but minTo is stored in Node class already
        int visited = 0; //just so we can maybe end a little early
        nodes[start].upTo = 0;
        nodes[start].parent = nodes[start];
        Queue<Node> pq = new PriorityQueue();        
        pq.add(nodes[start]); //we can go ahead and add the template, but only for the start
        while(visited < nodes.length){
            Node curr = pq.poll();
            System.out.println(curr.value);
            if(curr.upTo > nodes[curr.value].upTo){
                continue; //larger put in before min
            }
            visited++; //every other won't be counted, will be larger
            for(int i = 0; i<curr.costs.size(); i++){
                int costTo = curr.upTo + curr.costs.get(i);
                Node next = curr.connections.get(i);
                if(costTo >= next.upTo){ //it's the template
                    continue;
                }
                next.upTo = costTo;
                next.parent = nodes[curr.value]; //the template
                
                pq.add(new Node(next.connections, next.costs, next.value, next.upTo, next.parent));
            }
        }
        
    }
    
    static List<Integer> path(Node start, Node to, Node[] nodes){ //shortest path is a tree
        List<Integer> ret = new LinkedList();
        while(to != start){
            ret.add(0, to.value);
            to = to.parent;
        }
        ret.add(0, start.value);
        return ret;
    }
    
    static class Node implements Comparable<Node>{
        public List<Node> connections; //they are a template. Only the upto of these will be modified
        public List<Integer> costs;
        public int value;
        public int upTo;
        public Node parent;
        
        public Node(List a, List b, int v, int u, Node p){ //for instance
            connections = a;
            costs = b;
            value = v;
            upTo = u;
            parent = p;
        }
        
        public Node(int a){
            value = a;
            upTo = Integer.MAX_VALUE;
            parent = null;//for debug, if it's still after end, then maybe every node can't be visited
            connections = new ArrayList();
            costs = new ArrayList();
        }
        
        public int compareTo(Node other){
            return upTo - other.upTo;
        }
    }
}
