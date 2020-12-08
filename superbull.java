
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

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
public class superbull {
    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("superbull.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("superbull.out")));
        //Only in a tree is the winner condition(as stated in problem) satisfied
        int N = Integer.parseInt(br.readLine());
        
        long ret = 0;
        
        int[] nums = new int[N];
        for(int i = 0; i<N; i++) nums[i] = Integer.parseInt(br.readLine());
        int[] prims = new int[N]; //for dense graphs: N edges each. Loop thru edges anyways, so selection sort remove log factor
        Arrays.fill(prims, Integer.MIN_VALUE);
        prims[0] = -1;
        int curr = 0;
        
        for(int i = 0; i<N-1; i++){
            int max = Integer.MIN_VALUE;
            int next = -1;
            for(int j = 0; j<N; j++){
                if(prims[j] == -1) continue;
                
                int xor = nums[curr]^nums[j];
                prims[j] = Math.max(prims[j], xor);
                
                if(prims[j] > max){
                    next = j;
                    max = prims[j];
                }
            }
            ret += max;
            prims[next] = -1;
            curr = next;
        }
        pw.println(ret);
        pw.close();
        br.close();
    }
    
}
