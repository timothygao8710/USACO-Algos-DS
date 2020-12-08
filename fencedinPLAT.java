
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

// package graph;
/**
 * - Long/int
 * - Draw stuff
 * - Use comments
 * - Drink Water
 * - Reread Problem
 *
 * - package/pw.close
 *
 * @author timothy
 */
public class fencedinPLAT {
    //key idea: 
    //we're doing kruskals: min doesn't change for row/col
    //we're doing prims: min once reached min will go for entire row/col
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("fencedin.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("fencedin.out")));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int maxX = Integer.parseInt(st.nextToken());
        int maxY = Integer.parseInt(st.nextToken());
        
        int V = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());
        
        long ret = 0;
        
        List<Integer> Vgates = new ArrayList();
        List<Integer> Hgates = new ArrayList();
        Vgates.add(0);
        Vgates.add(maxX);
        Hgates.add(0);
        Hgates.add(maxY);
        
        for(int i = 0; i<V; i++){
            int curr = Integer.parseInt(br.readLine());
            if(curr == 0 || curr == maxX) continue;
            Vgates.add(curr);
        }
        
        for(int i = 0; i<H; i++){
            int curr = Integer.parseInt(br.readLine());
            if(curr == 0 || curr == maxY) continue;
            Hgates.add(curr);
        }
        Collections.sort(Vgates);
        Collections.sort(Hgates);
        
        int[] max = new int[]{V,H};
        boolean[] connected = new boolean[2]; //the first time is an exception
        //there is proof that we always wanna connect all the first time(it's the smallest of type)
        
        List<int[]> gaps = new ArrayList();
        for(int i = 1; i<Vgates.size(); i++){
            gaps.add(new int[]{0,Vgates.get(i)-Vgates.get(i-1)});
        }
        
        for(int i = 1; i<Hgates.size(); i++){
            gaps.add(new int[]{1, Hgates.get(i)-Hgates.get(i-1)});
        }
        
        Collections.sort(gaps, (int[] a, int[] b) -> a[1] - b[1]);
        
        //We save for "number of inbetweens"-1. This is always true. Draw diagram
        //except for the first time - because they need to be connected at first
        for(int[] a : gaps){
            if(!connected[a[0]]){
                connected[a[0]] = true;
                ret += (long)a[1]*(a[0] == 0 ? H : V);
            }else{
                max[a[0]]--;
                ret += (long)a[1]*max[(a[0]+1)%2];
            }
            
        }
//        System.out.println(ret);
        pw.print(ret);
        pw.close();
        br.close();
    }
    
}

/*
9 5 3 1
1
3
7
2
*/


