/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dp;

/**
 *
 * @author timothy
 */
public class lcs {

    static int[][] dp;
    
    public static void main(String[] args) {
        String one = "GXTXAYB";
        String two = "AGGTAB";
        dp = new int[one.length()][two.length()];
//        lcs(one, two, 0, 0);

        //the code below works because it's generating the state in bottom-up order,
        //in an order where each state takes from previously generated states, and the first state
        //doesn't take from anyone
        //There are two versions of this bottom up, just like how there are two versions of top down
        //The first version is the inital state(smallest state) is at 0,0, and we consider building a substring up like that
        //The code is shown below:
//        for(int i = 0; i<one.length(); i++){
//            for(int j = 0; j<two.length(); j++){
//                if(one.charAt(i) == two.charAt(j)){
//                    if(i -1 >= 0 && j-1 >= 0){
//                        dp[i][j] = dp[i-1][j-1];
//                    }
//                    dp[i][j]++;
//                }else{
//                    if(i - 1 >= 0){
//                        dp[i][j] = dp[i-1][j];
//                    }
//                    if(j-1 >=0){
//                        dp[i][j] = Math.max(dp[i][j], dp[i][j-1]);
//                    }
//                }
//            }
//        }
//        
//        System.out.println(dp[one.length()-1][two.length()-1]);
        //This is opposite to our top-down version since our top down version goes the opposite way:
        //The smallest state for our top-version is one.length()-1, two.length()-1
        //so the below is how it would be implemented in bottom-up
        for (int i = one.length() - 1; i >= 0; i--) {
            for (int j = two.length() - 1; j >= 0; j--) {
                if (one.charAt(i) == two.charAt(j)) {
                    if (i + 1 < one.length() && j + 1 < two.length()) {
                        dp[i][j] = dp[i + 1][j + 1];
                    }
                    dp[i][j]++;
                } else {
                    if (i + 1 < one.length()) {
                        dp[i][j] = dp[i + 1][j];
                    }
                    if (j + 1 < two.length()) {
                        dp[i][j] = Math.max(dp[i][j], dp[i][j + 1]);
                    }
                }
            }
        }
        System.out.println(dp[0][0]);

        //but how to generate the actual substring?
        //well imo there are 2 ways: 
        //we can keep track of the current longest substring while we run, and when we select max
        //we also select max of substring
        //but this takes a lot of memory:
        System.out.println(generateOnString(one, two));

        //instead we can take advantage of the dp table to generate the substrings
        //here's how the table looks like for intuition
        //look where where x+1 y+1 is 1 smaller than x, y
        for (int[] a : dp) {
            for (int b : a) {
                System.out.print(b + " ");
            }
            System.out.println();
        }

        System.out.println(generateFromTable(one, two, dp));

        //Summary:
        /*
        The idea behind lcs is that we can use subproblems to construct a solution to the entire problem.
        If you look at the graph here: http://www.cs.cmu.edu/afs/cs/academic/class/15451-f17/www/lectures/lec12-dp1.pdf
        Then you will see that the lines never cross, so you can take substring sections over the graph.
        
        There are two ways to do so:
        either you go from left to right, or right to left
        
        and since the subproblems can overlap(l-1 r then l-1 r-1 versus l r-1 then 1-1 r-1)
        
        For the memoized version, we are going from right to left, as 0,0 is considered to be the "end product"
        and one.length-1, two.length-1 is the beggining state
        
        So from this we can generate a bottom-up approach. From the observation that each state only takes from i-1 and j-1,
        we realize that if we generate from 0 - one.length-1 for i and 0 - two.length-1 for j then each state will always
        be able to access a previous state. Same logic for if we do left to right
        
        Lastly, how would we be able to find the actual string?
        Well I thought of 2 ways.
        The first way is through keeping a dp array of strings rather than number, indicating the current
        longest substring, and when char(i) != char(j) we take max length string of i-1 j and j-1 i.
        So it's basically the bottom up approach but instead of keeping only the lengths we keep the whole string
        
        Another way of doing it is through backwards reasoning on the dp array(int) itself.
        For a given dp table like such:
        4 4 4 3 2 1 
        3 3 3 3 2 1 
        3 3 3 3 2 1 
        2 2 2 2 2 1 
        2 2 2 2 2 1 
        1 1 1 1 1 1 
        1 1 1 1 1 1 
        
        the right-bottom edge of each rectangle is where we did i+1, j+1 +1 so that's where the strings are the same
        and we can write an algorithm based on that fact
        
        4 4 4 3 2 1 
        3 3 3 3 2 1 
        3 3 3 3 2 1 
        2 2 2 2 2 1 
        2 2 2 2 2 1 
        1 1 1 1 1 1 
        1 1 1 1 1 1
         */
        //Follow-up challenge:
        //Implement lcs with O(N) space
        //Intuition: Each state only access i-1, j-1, not the entire table
        //but we still need the array at current row and previous row
    }

    static String generateFromTable(String one, String two, int[][] table) {
        String res = "";
        //we can use deductive reasoning to figure out the actual string
        //since we know how it's generated
        int x = 0;
        int y = 0;

        while (x < two.length() && y < one.length()) {
            if (table[y][x] == 0) {
                break;
            }

            if (x + 1 < two.length() && dp[y][x + 1] == dp[y][x]) {
                x++;
            } else if (y + 1 < one.length() && dp[y + 1][x] == dp[y][x]) {
                y++;
            } else {
                //we know I must have used [i+1][j+1]+1
                res += two.charAt(x);
                x++;
                y++;
            }
        }
        // the case where one.length -1 == two.length -1 is considered as it will be the case
        // where x+1 !< two.length and y+1 !<one.length() so the 3rd else would consider that

        //however we don't have to worry about that bit being 0 and considered since we already had a check
        //for that
        return res;
    }

    static String generateOnString(String one, String two) {
        String[][] dp = new String[one.length()][two.length()];

        for (int i = one.length() - 1; i >= 0; i--) {
            for (int j = two.length() - 1; j >= 0; j--) {
                dp[i][j] = "";

                if (one.charAt(i) == two.charAt(j)) {
                    if (i + 1 < one.length() && j + 1 < two.length()) {
                        dp[i][j] = dp[i + 1][j + 1];
                    }
                    dp[i][j] = one.charAt(i) + dp[i][j];
                } else {
                    if (i + 1 < one.length()) {
                        dp[i][j] = dp[i + 1][j];
                    }
                    if (j + 1 < two.length()) {
                        if (dp[i][j + 1].length() > dp[i][j].length()) {
                            dp[i][j] = dp[i][j + 1];
                        }
                    }
                }
            }
        }
        return dp[0][0];
    }

    static void lcs(String one, String two, int i, int j) {
        System.out.println(i + " " + j);
        if (i == one.length() || j == two.length()) {
            return;
        }

        if (one.charAt(i) == two.charAt(j)) {
            if (dp[i + 1][j + 1] == 0) {
                lcs(one, two, i + 1, j + 1);
            }
            dp[i][j] = dp[i + 1][j + 1] + 1;
            //because this is the soonest match in the current substring, so 
            //we def want to include it due bcause there is no better match before
            //for the current substring we are considering of course

            //Proof that we want to take the earliest match:
            //let's suppose we have a later match, and we have N characters availabe for match after that match
            //well the match in this method will be better than that match because that much is strictly after the current match, which means that the characters 
            //avalialbe for match after this match will include the N mathes of the other match,
            //and if the number of matches for this includes the N of the other, then we know for sure 
            //that the lcs of taking this current match must be greater of equal to the lcs of the other match
            //now, you might be suspicious, as you might be unsure as to why this is the soonest match.
            //How do we know there is not an earlier match?
            //See (~*~) below
            //Note: all the the proof above is wrong.
            //Why?
            //Well we don't even need to consider all the previous possibible substrings to use the current match
            //In fact, with the way I'm doing it, when we reach 2 - 2, 0-1 has  not been considered yet..
            //In fact, 0 -1 is considered last
            //so how the hail does this happen?
            //Well we have to think about it from a local perspective, rather than a global perspectve.
            //After all, we wil generate all substrings
            //so we know that for the one(i, end) and two(j, end), if i and j match than it is the earliest match
            //that's all we care about. We don't care about before
        } else {
            if (dp[i + 1][j] == 0) {
                lcs(one, two, i + 1, j); // - 好
            }
            if (dp[i][j + 1] == 0) {
                lcs(one, two, i, j + 1);// - 人
            }
            //we know that i and j and not the same, thus niether of them are in the lcs.
            //So we can't use them, we can only the substrings not including both of them
            dp[i][j] = Math.max(dp[i + 1][j], dp[i][j + 1]);
            // (~*~):
            //By the way the function above is written if there is not a match, we know that if we visit
            //i and j, we know that all possible substrings of 0 - i and 0 - j have been tried
            //like for example if i == 2 && j == 2,
            //we know for sure that 0 - 0, 1 - 0, 2 - 0, 0 - 1, 1 -1, 2 - 1 have all been tried
            //why?
            //Well think about the recursion tree with 好 and 人. In particular, think about what happens
            //each iteration, and draw a recursion tree out
        }

    }
}
