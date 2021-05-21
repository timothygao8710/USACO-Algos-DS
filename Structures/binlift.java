// package Structures;

public class binlift {

    public int[][] up;
    public int pow;

    //Note: Root's parent should be -1
    public binlift(int[] pars) {
        pow = pow(pars.length);
        up = new int[pow + 1][pars.length];
        up[0] = pars.clone();

        for (int i = 1; i <= pow; i++) {
            for (int j = 0; j < pars.length; j++) {
                up[i][j] = up[i - 1][j] == -1 ? -1 : up[i - 1][up[i - 1][j]];
            }
        }
    }
    //returns -1 if out of bounds

    public int getUp(int u, int k) {
        for (int i = 0; i <= pow; i++) {
            if ((k & (1 << i)) != 0) {
                u = up[i][u];
            }
            if (u == -1) {
                return -1;
            }
        }
        return u;
    }

    private int pow(int num) {
        return num == 1 ? 0 : pow(num / 2) + 1;
    }
}
