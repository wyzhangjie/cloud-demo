package com.hyssop.algorithm.leetcode.questions.dpproblem;

/**
 *
 * Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).
 *
 * Range Sum Query 2D
 * The above rectangle (with the red border) is defined by (row1, col1) = (2, 1) and (row2, col2) = (4, 3), which contains sum = 8.
 *
 * Example:
 * Given matrix = [
 *   [3, 0, 1, 4, 2],
 *   [5, 6, 3, 2, 1],
 *   [1, 2, 0, 1, 5],
 *   [4, 1, 0, 1, 7],
 *   [1, 0, 3, 0, 5]
 * ]
 *
 * sumRegion(2, 1, 4, 3) -> 8
 * sumRegion(1, 1, 2, 2) -> 11
 * sumRegion(1, 2, 2, 4) -> 12
 * */
public class Leecode304 {
    private int[][] dp;
    public void NumMatrix(int[][] matrix) {
        int len = matrix.length+1;
         dp = new int[len][len];
        dp[0][0]=0;
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix.length;j++){
                dp[i+1][j+1]= dp[i+1][j]+dp[i][j+1]+matrix[i][j]-dp[i][j];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
    return dp[row2+1][col2+1] -dp[row2+1][col1] -dp[row1][col2+1]+dp[row1][col1];
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{3, 0, 1, 4, 2},{5, 6, 3, 2, 1},{1, 2, 0, 1, 5},{4, 1, 0, 1, 7},{1, 0, 3, 0, 5}};
        Leecode304 leecode304 = new Leecode304();
        leecode304.NumMatrix(matrix);
        System.out.println(leecode304.sumRegion(2, 1, 4, 3));
    }
}
