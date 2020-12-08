
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

 

/**
 * - Long/int
 * - Draw stuff
 * - Use comments
 * - Drink Water
 * - Reread Problem
 *
 * @author timothy
 */
public class shortcut {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("shortcut.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("shortcut.out")));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        
        ArrayList<Integer>[] adj = new ArrayList[N];
        ArrayList<Integer>[] weights = new ArrayList[N];
        ArrayList<Integer>[] tree = new ArrayList[N];
        int[] sizes = new int[N];
        
        for(int i = 0; i<N; i++){
            adj[i] = new ArrayList();
            tree[i] = new ArrayList();
            weights[i] = new ArrayList();
        }
        
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<N; i++) sizes[i] = Integer.parseInt(st.nextToken());
        
        for(int i = 0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken())-1;
            int v = Integer.parseInt(st.nextToken())-1;
            int w = Integer.parseInt(st.nextToken());
            adj[v].add(u);
            adj[u].add(v);
            weights[v].add(w);
            weights[u].add(w);
        }
        
        
        long[] visited = new long[N];
        Arrays.fill(visited, Integer.MAX_VALUE);
        visited[0] = 0;
        
        Queue<long[]> dj = new PriorityQueue(new Comparator<long[]>(){
           //curr, last, weight
            @Override
            public int compare(long[] a, long[] b){
                return a[2] == b[2] ? Long.compare(a[1],b[1]) : Long.compare(a[2],b[2]);
            }
            
        });
        
        dj.add(new long[]{0,0,0});
        
        while(!dj.isEmpty()){
            int curr = (int)dj.peek()[0];
            int last = (int)dj.peek()[1];
            long weight = dj.poll()[2];

            
//            if(weight < visited[curr]) System.out.println("YELLOW");
            if(weight > visited[curr]) continue; //check bef
            visited[curr]--; //multiple w same weight
            tree[last].add(curr); //tree
            
            for(int i = 0; i<adj[curr].size(); i++){
                int next = adj[curr].get(i);
                long nW = weights[curr].get(i)+weight;
                
                if(visited[next] >= nW){ //check af(optional)
                    visited[next] = nW;
                    dj.add(new long[]{next, curr, nW});
                }
            }
        }
        
//        for(int i = 0; i<tree.length; i++){
//            System.out.println(i+1);
//            for(int j : tree[i]) System.out.print(sizes[j]+ " ");
//            System.out.println();
//        }
        
        dfs(tree,0, sizes);
        
        long save = 0;
        for(int i = 0; i<sizes.length; i++){
            save = Math.max(save, (visited[i]+1-T)*sizes[i]);
        }
        
//        System.out.println(save);
        pw.println(save);
        pw.close();
        br.close();
    }
    
    static int dfs(ArrayList<Integer>[] graph, int curr, int[] size){
        for(int n : graph[curr]){
            size[curr] += n==curr?0:dfs(graph, n, size);
        }        
        return size[curr];
    }
}
