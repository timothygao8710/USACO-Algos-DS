// package String;

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
public class polyHash {
    //single base
    //literally create 2 classes if you wanna do double base lmao
    static class hash{
        public int mod = (int)(1e9+7);
        public int C = 9973; //C^(k-1) * s(0) + C^(k-2) * s(1) + ...
        
        public long[] pow;
        public long[] hash;
        
        public hash(String str){
            pow[0] = 1;
            for(int i = 1; i<str.length(); i++){
                pow[i] = (pow[i-1]*C)%mod;
            }
            
            hash[0] = str.charAt(0);
            for(int i = 1; i<str.length(); i++){
                hash[i] = (hash[i-1]*C + str.charAt(i))%mod;
            }
        }
        
        public long get(int l, int r){ //inclusive
            if(l == 0) return hash[r];
            return (hash[r]+mod - pow[r-l+1]*hash[l-1]) % mod; //+/% part handles negative
        }
    }
}
