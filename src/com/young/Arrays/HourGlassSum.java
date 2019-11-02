package com.young.Arrays;

public class HourGlassSum {

    //  There are 16 hourglasses in Array, and an hourglass sum is the sum of an hourglass' values.
    //  Calculate the hourglass sum for every hourglass in Array, then print the maximum hourglass sum.
    //
    //        -9 -9 -9  1  1  1         -9 -9 -9    -9 -9  1
    //         0 -9  0  4  3  2            -9           0
    //        -9 -9 -9  1  2  3         -9 -9 -9    -9 -9  1
    //         0  0  8  6  6  0         sum: -63    sum: -34
    //         0  0  0 -2  0  0
    //         0  0  1  2  4  0

    public static void main(String[] args) {

    }

    private static int hourGlassSum(int[][] arr) {
        int maxSum = Integer.MIN_VALUE;
        for (int r = 1; r < arr.length-1; r++) {
            for (int c = 1; c < arr[0].length-1; c++) {
                int sum = arr[r-1][c-1] + arr[r-1][c] + arr[r-1][c+1] + arr[r][c] +
                        arr[r+1][c-1] + arr[r+1][c] + arr[r+1][c+1];
                maxSum = Math.max(maxSum, sum);
            }
        }
        return maxSum;
    }
}
