package com.young;

public class Testing {

    public static void main(String[] args) {
        int[] arr = {2,4,3,5,2,6,4,5};
        candies(arr.length, arr);
    }

    static long candies(int n, int[] arr) {
        int[] memo = new int[n];
        memo[0] = 1;
        for (int i = 1; i < n; i++) {
            if (arr[i] > arr[i-1]) {
                memo[i] = memo[i-1] > 0 ? memo[i-1] + 1 : 2;
            } else if (arr[i] < arr[i-1]) memo[i] = 1;
            else memo[i] = memo[i-1];
        }
        System.out.println(memo);
        return 0;
    }}
