// package Structures;

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
public class sparseTable {

    static class sparse { //max

        public int[][] table;

        public sparse(int[] nums) {
            int maxFit = maxPow(nums.length);
            table = new int[maxFit][nums.length];

            table[0] = nums;

            for (int i = 1; i < maxFit; i++) {
                for (int j = 0; j + (1 << i) - 1 < nums.length; j++) {
                    table[i][j] = Math.min(table[i - 1][j], table[i - 1][j + (1 << (i - 1))]);
                }
            }
        }

        public int get(int l, int r) {
            int max = maxPow(r - l + 1) - 1;
            return Math.min(table[max][l], table[max][r - (1 << max) + 1]);
        }

        //returns the power not the number
        private int maxPow(int num) {
            int ret = 0;
            while (num > 0) {
                num >>= 1;
                ret++;
            }
            return ret;
        }
    }
}
