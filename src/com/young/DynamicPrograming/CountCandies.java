package com.young.DynamicPrograming;

public class CountCandies {


    public static void main(String[] args) {
        int[] arr = {1,2,2,4,4,4};
        System.out.println(candies(arr.length, arr));
        int[] arr2 = {2,4,2,6,1,7,6,5,2,3,5,4};
        System.out.println(candies(arr2.length, arr2));
    }

    static long candies(int n, int[] arr) {
        int[] cache = new int[n];
        cache[0] = 1;

        for (int i = 1; i < n; i++) {
            if (arr[i-1] >= arr[i]) cache[i] = 1;
            else cache[i] = cache[i-1] + 1;
        }

        long sum = cache[n-1];

        for (int i = n - 2; i >= 0; i--) {
            if (arr[i] > arr[i+1]) {
                if (cache[i] <= cache[i+1]) {
                    cache[i] = cache[i+1] + 1;
                }
            }
            sum += cache[i];
        }
        return sum;
    }

    static void printArray(int[] A) {
        for (int i = 0; i < A.length; i++) {
            System.out.print(A[i] + ", ");
        }
        System.out.println();
    }
}
