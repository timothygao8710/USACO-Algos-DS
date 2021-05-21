/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author timothy
 */
public class matrixPrefix {
    static int[][] prefix;
    public void NumMatrix(int[][] matrix) {
        if(matrix.length == 0){
            return;
        }
        prefix = new int[matrix.length+1][matrix[0].length+1];
        for(int i = 1; i<prefix.length; i++){
            for(int j = 1; j<prefix[0].length; j++){
                prefix[i][j] = matrix[i-1][j-1] + prefix[i-1][j] + prefix[i][j-1] - prefix[i-1][j-1];
            }
        }
        // for(int[] a : prefix){
        //     for(int b : a){
        //         System.out.print(b + " ");
        //     }
        //     System.out.println();
        // }
    }
    
    public int sum(int row1, int col1, int row2, int col2) {
        // System.out.println(prefix[row2+1][col2+1] + "  " + prefix[row1][col2+1] + " " + prefix[row2+1][col1] + " " +  prefix[row1][col1]);
        return (
            prefix[row2+1][col2+1] - prefix[row1][col2+1] - prefix[row2+1][col1] + prefix[row1][col1]
        );
    }
} 
