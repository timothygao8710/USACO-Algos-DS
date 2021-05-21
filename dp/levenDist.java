// package dp;

/**
 * -Take it slow, think-
 * Watch out for:
 * - Long/Int
 * - Edge cases (make test case)
 * - Unexpected behavior?
 *
 * @author timothy
 */
public class levenDist {
    //"abczbdc"
    public static void main(String[] args){
        String a = "abcz";
        String b = "bdc";
        System.out.println(calculate(a, b));
    }
    
    static int calculate(String a, String b){
        int dp[][] = new int[a.length() + 1][b.length() + 1]; 
  
        // Fill d[][] in bottom up manner 
        for (int i = 0; i <= a.length(); i++) { 
            for (int j = 0; j <= b.length(); j++) { 
                // If first string is empty, only option is to 
                // insert all characters of second string 
                if (i == 0) 
                    dp[i][j] = j; // Min. operations = j 
  
                // If second string is empty, only option is to 
                // remove all characters of second string 
                else if (j == 0) 
                    dp[i][j] = i; // Min. operations = i 
  
                // If last characters are same, ignore last char 
                // and recur for remaining string 
                else if (a.charAt(i - 1) == b.charAt(j - 1)) 
                    dp[i][j] = dp[i - 1][j - 1]; 
  
                // If the last character is different, consider all 
                // possibilities and find the minimum 
                else
                    dp[i][j] = 1 + Math.min(dp[i][j - 1], // Insert 
                                       Math.min(dp[i - 1][j], // Remove 
                                       dp[i - 1][j - 1])); // Replace 
            } 
        } 
  
        return dp[a.length()][b.length()]; 
    }
}
