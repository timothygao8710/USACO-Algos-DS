
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

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
public class cownomics {
    static int mod = (int)(1e9+7);
    static int C = 9973;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("cownomics.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cownomics.out")));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        String[][] strs = new String[2][N];
        for(int i = 0; i<N; i++) strs[0][i] = br.readLine();
        for(int i = 0; i<N; i++) strs[1][i] = br.readLine();
        
        long[] pows = new long[M];
        pows[0] = 1;
        for(int i = 1; i<M; i++) pows[i] = (pows[i-1]*C)%mod;
        
        long[][] hash = new long[2][N];
        
        int l = 0;
        int r = 0;
        
        int ret = Integer.MAX_VALUE;
        
        boolean dup = true;
        
        rolling:
        while(r<M){
            if(dup){
                //left large to preserve modularity*(so we don't use division)
                //* = made up term
                for(int i = 0; i<2; i++){
                    for(int j = 0; j<N; j++){
                        hash[i][j] = hash[i][j]*C + strs[i][j].charAt(r);
                        hash[i][j] %= mod;
                    }
                }
                r++;
            }else{
                ret = Math.min(ret, r-l);
                
                for(int i = 0; i<2; i++){
                    for(int j = 0; j<N; j++){
                        long currPow = pows[r-l-1];
                        hash[i][j] = (hash[i][j] - currPow*strs[i][j].charAt(l) + mod)%mod;
                    }
                }
                l++;
            }
            
            for(long i : hash[0]){
                for(long j : hash[1]){
                    if(i == j){
                        dup = true;
                        continue rolling;
                    }
                }
            }
            dup = false;
            ret = Math.min(ret, r-l);
        }
        
        pw.println(ret);
//        System.out.println(ret);
        pw.close();
        br.close();
    }
}
