
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

// package graph;

/**
 * Journel:
 * We cannot do for every cell, but we're looking really at global --> spanning tree intuition
 * Mootube
 */
public class skilevel {
    static int[] xDir = new int[]{0, 0, 1, -1};
    static int[] yDir = new int[]{1, -1, 0, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("skilevel.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("skilevel.out")));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        //note that N and M are swapped in mine
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        
        int[][] heights = new int[N][M];
        for(int i = 0; i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<M; j++){
                heights[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        DSU dsu = new DSU(N*M);
        for(int i = 0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<M; j++){
                dsu.starts[i*M+j] = Integer.parseInt(st.nextToken());
            }
        }
        List<int[]> edges = new ArrayList<>();
        for(int i = 0; i<N; i++){
            for(int j = 0; j<M; j++){
                int mark = i*M + j;
                for(int k = 0; k<4; k++){
                    int dY = i + yDir[k];
                    int dX = j + xDir[k];
                            
                    if(dY >=0 && dX >= 0 && dY < heights.length && dX < heights[0].length){
                        edges.add(new int[]{
                            mark,
                            dY*M + dX,
                            Math.max(heights[dY][dX] - heights[i][j], heights[i][j] - heights[dY][dX])
                        });
                    }
                }
            }
        }
        
        Collections.sort(edges, new Comparator<int[]>(){
            public int compare(int[] a, int[] b){
                return a[2] - b[2];
            }
        });
        
        long ret = 0;
        for(int[] e : edges){
            dsu.union(e[0], e[1]);
            if(dsu.size[dsu.find(e[0])] >= T){
                int r = dsu.find(e[0]);
                ret += (long)e[2]*dsu.starts[r];
                dsu.starts[r] = 0;
                if(dsu.size[dsu.find(r)] == N*M){
                    break;
                }
            }
        }

        pw.println(T == 1 ? 0 : ret);
        pw.close();
        br.close();
    }
    
    static class DSU {

        public int[] parent;
        public int[] size;
        public int[] starts;
        
        public DSU(int n) {
            parent = new int[n];
            size = new int[n];
            starts = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }
        
        public int find(int v) {
            if(v == parent[v]) return v;
            parent[v] = find(parent[v]);
            return parent[v];
        }
        
        public void union(int v1, int v2) {
            int rootv1 = find(v1);
            int rootv2 = find(v2);
            
            if (rootv1 == rootv2) {
                return;
            }
            if (size[rootv1] > size[rootv2]) {
                parent[rootv2] = rootv1;
                size[rootv1] += size[rootv2];
                starts[rootv1] += starts[rootv2];
            } else {
                parent[rootv1] = rootv2;
                size[rootv2] += size[rootv1];
                starts[rootv2] += starts[rootv1];
            }
        }
    }
}
