
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author timothy
 */
public class mootube {
    //Key Insight:
    /*
    If an edge is too low, then it is not visited
    If an edge is high, then it is visited
    
    We are given query of K, Node
    And all the edges that K+1 can work with, K can definitly work with
    
    From that, we kind of have a greedy relationship. If we sort all the edges from high to low,
    we can union the edges with K, since the next ones are def gonna be lower
    
    We don't sort from low to hgih since Node will stop at to small edge immediatly anyways
    
    New idea: To think about graphs in terms of edges rather then nodes. So we don't actually need to 
    store the connections of each componenent, we just have to worry about the connections of it
    */
    public static void main(String[] args) throws IOException {
        for (int i = 1; i <= 10; i++) {
            String inFile = "test/mootube_silver_jan18/" + i + ".in";
            String outFile = "test/mootube_silver_jan18/" + i + ".out";
            String testFile = "test/mootube_silver_jan18/" + i + ".out.test";
            
            run(inFile, testFile);
            compare(outFile, testFile, i);
        }
    }
    
    static void compare(String outFile, String testFile, int i) throws IOException {
        BufferedReader br1 = new BufferedReader(new FileReader(outFile));
        BufferedReader br2 = new BufferedReader(new FileReader(testFile));
        
        boolean result = true;
        
        String content1 = br1.readLine();
        String content2 = br2.readLine();
        
        while (content1 != null) {
            if (!content1.equals(content2)) {
                result = false;
                break;
            }
            content1 = br1.readLine();
            content2 = br2.readLine();
        }
        
        System.out.print(i + ": ");
        System.out.println(result ? "Success" : "Failure");
    }
 
    static void run(String inFile, String outFile) throws IOException {
        BufferedReader br
                = new BufferedReader(
                        new FileReader(inFile)
                );
        PrintWriter pw
                = new PrintWriter(
                        new BufferedWriter(
                                new FileWriter(outFile)
                        )
                );
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());
        
        roots = new int[N+1];
        weights = new int[N+1];
        for(int i = 0; i<N; i++){
            roots[i] = i;
            weights[i] = 1;
        }
        
        Edge[] edges = new Edge[N-1];
        Query[] qs = new Query[Q];
        for(int i = 0; i<N-1; i++){
            st = new StringTokenizer(br.readLine());
            edges[i] = new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        
        Arrays.sort(edges);
        
        for(int i = 0; i<Q; i++){
            st = new StringTokenizer(br.readLine());
            qs[i] = new Query(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), i);
        }
        
        int[] ret = new int[Q];
        Arrays.sort(qs);
        int curr = 0;
        for(Query q : qs){
            for(; curr < edges.length && edges[curr].w >= q.w ; curr++){
                union(edges[curr].from, edges[curr].to); //join the nodes boia
            }
            ret[q.index] = weights[find(q.node)]-1;
        }
        
        for(int i : ret){
            pw.println(i);
        }
        pw.close();
        br.close();
    }
    static int[] weights;
    static int[] roots;
    static void union(int p, int q){
        if(connected(p, q)){
            return;
        }
        int pR = find(p);
        int qR = find(q);
        
        if(weights[pR] > weights[qR]){
            roots[qR] = pR; //smaller tree under bigger
            weights[pR] += weights[qR]; //bigger tree is bigger
        }else{
            roots[pR] = qR;
            weights[qR] += weights[pR];
        }
    }
    
    static int find(int p){
        if(roots[p] == p){
            return p; //this is the root
        }
        roots[p] = find(roots[p]);
        return roots[p];
    }
    static boolean connected(int p, int q){
        return find(p) == find(q);
    }
    
    static class Edge implements Comparable<Edge>{
        public int w;
        public int from;
        public int to;
        public Edge(int f, int t, int w){
            this.w = w;
            from = f;
            to = t;
        }
        public int compareTo(Edge other){
            return other.w - w;
        }
        //we want to sort from high to low
        //so if one doesn't work then all the lower ones don't work
        //the relationship doesn't work vice versa
        
        //this works cuz al the previous queries are greater
    }
    
    static class Query implements Comparable<Query>{
        //we want query from high to low since 
        //we don't have to consider all those higher
        //but we do have to consider those lower
        
        //this makes all the previous queiries greater
        public int index;
        public int w;
        public int node;
        public Query(int a , int b, int i){
            w = a;
            node = b;
            index = i;
        }
        
        public int compareTo(Query other){
            return other.w - this.w;
        }
    }
    
   
}
