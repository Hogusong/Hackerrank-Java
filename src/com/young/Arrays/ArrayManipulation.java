package com.young.Arrays;

public class ArrayManipulation {
    // Starting with a 1-indexed array of zeros and a list of operations, for each operation
    // add a value to each of the array element between two given indices, inclusive.
    // Once all operations have been performed, return the maximum value in your array.
    //
    // For example, the length of your array of zeros n = 10. Your list of queries is as follows:
    //      queries = [ [1, 5, 3]
    //                  [4, 8, 5]
    //                  [5, 9, 1] ]
    //
    //      n = 10 : 1 2 3 4 5 6 7 8 9 10
    //              [0,0,0,0,0,0,0,0,0,0]
    //              [3,3,3,3,3,0,0,0,0,0]  1 -> 5  add 3
    //              [3,3,3,8,8,5,5,5,0,0]  4 -> 8  add 5
    //              [3,3,3,8,9,6,6,6,1,0]  5 -> 9  add 1     Max value is 9

    public static void main(String[] args) {

    }

    private static long arrayManipulation(int n, int[][] queries) {
        long[] result = new long[n+1];
        for (int i = 0; i < n+1; i++) result[i] = 0L;

        for (int[] q : queries) {
            result[q[0]-1] += q[2];
            result[q[1]] -= q[2];
        }

        long maxValue = result[0];
        for (int i = 1; i < n; i++) {
            result[i] += result[i-1];
            maxValue = Math.max(maxValue, result[i]);
        }
        return maxValue;
    }
}
