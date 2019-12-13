package com.young.DynamicPrograming;

public class MaxArraySum {

    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) {
        int[] arr = {-1, -1, 1, -1, 3, 5};
        System.out.println(maxSubsetSum(arr));
    }

    static int maxSubsetSum(int[] arr) {
        if (arr.length < 2) return arr[0];
        int incl = arr[0], excl = 0, preIncl;
        for (int i = 1; i < arr.length; i++) {
            preIncl = incl;
            incl = excl + arr[i];
            excl = Math.max(preIncl, excl);
        }
        return Math.max(incl, excl);
    }

    static int maxSubsetSum1(int[] arr) {
        if (arr.length < 2) return arr[0];
        getMaxSum(arr, 0, true, 0);
        return max;
    }

    static void getMaxSum(int[] arr, int i, boolean skiped, int sum) {
        if (i >= arr.length) {
            max = Math.max(max, sum);
            return;
        }

        if (skiped && arr[i] > 0) getMaxSum(arr, i+1, false, sum + arr[i]);
        getMaxSum(arr, i+1, true, sum);
    }
}
