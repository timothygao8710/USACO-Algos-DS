
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Queue;

class korsaraju {

    static Deque<Integer> dag;
    static boolean[] visited;

    static int findCC(ArrayList<Integer>[] graph) {
        dag = new ArrayDeque<>();

        ArrayList<Integer>[] rev = new ArrayList[graph.length];
        
        for(int i = 0; i<rev.length; i++) rev[i] = new ArrayList<>();
        
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[i].size(); j++) {
                rev[graph[i].get(j)].add(i);
            }
        }

        visited = new boolean[graph.length];
        for (int i = 0; i < graph.length; i++) {
            if (!visited[i]) {
                dfs(i, graph);
            }
        }

        int ret = 0;

        visited = new boolean[graph.length];
        while (!dag.isEmpty()) {
            int i = dag.pop();
            if (!visited[i]) {
                visited[i] = true;
                Queue<Integer> bfs = new ArrayDeque<>();
                //modify here if you wanna actually see the ccs
                ret++;
                bfs.add(i);
                while (!bfs.isEmpty()) {
                    int c = bfs.poll();
                    for (int j : rev[c]) {
                        if (!visited[j]) {
                            bfs.add(j);
                        }
                        visited[j] = true;
                    }
                }
            }
        }

        return ret;
    }

    static void dfs(int u, ArrayList<Integer>[] adj) {
        visited[u] = true;
        for (int j : adj[u]) {
            if (!visited[j]) {
                dfs(j, adj);
            }
        }
        dag.add(u);
    }
}
