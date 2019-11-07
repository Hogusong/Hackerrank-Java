package com.young.GreedyAlgorithms;

import java.util.Arrays;

public class MinimumAbsoluteDifference {
    // Consider an array of integers, a = [a[0], a[1], ..., a[n-1]]. We define the absolute difference
    // between two elements, a[i] and a[j] (where i != j), to be the absolute value of a[i] - a[j].
    //
    // Given an array of integers, find and print the minimum absolute difference between any two elements
    // in the array. For example, given the array a = [-2, 2, 4] we can create 3 pairs of numbers [-2,2],
    // [2,4] and [-2,4]. The absolute differences for these pairs are 4, 2 and 6.
    // The minimum absolute difference is 2.

    public static void main(String[] args) {

    }

    private static int minimumAbsoluteDifference(int[] arr) {
        int minDiff = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length-1; i++) {
            for (int j = i+1; j < arr.length; j++) {
                int value = Math.abs(arr[i] - arr[j]);
                minDiff = Math.min(minDiff, value);
            }
        }
        return minDiff;
    }

    private static int minimumAbsoluteDiff(int[] arr) {
        int minDiff = Integer.MAX_VALUE;
        arr = mergeSort(arr, 0, arr.length-1);
        for (int i = 1; i < arr.length; i++) {
            minDiff = Math.min(minDiff, arr[i] - arr[i-1]);
        }
        return minDiff;
    }

    private static int[] mergeSort(int[] arr, int from, int to) {
        if (from >= to) return Arrays.copyOfRange(arr, from, from+1);
        int mid = (from+to)/2;
        int[] left = mergeSort(arr, from, mid);
        int[] right = mergeSort(arr, mid+1, to);
        return merge(left, right);
    }

    private static int[] merge(int[] A, int[] B) {
        int[] arr = new int[A.length + B.length];
        int a = 0, b = 0;
        for (int i = 0; i < arr.length; i++) {
            if (a < A.length && b < B.length) {
                if (A[a] > B[b]) arr[i] = B[b++];
                else arr[i] = A[a++];
            } else if (a < A.length) arr[i] = A[a++];
            else arr[i] = B[b++];
        }
        return arr;
    }
}
