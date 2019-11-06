package com.young.Sorting;

import java.util.Arrays;

public class MergeSort {
    // Count how many swaps happen during Merge Sort.

    public static void main(String[] args) {

    }

    private static long countInversions(int[] arr) {
        return mergeSort(arr, 0, arr.length-1);
    }

    private static long mergeSort(int[] arr, int from, int to) {
        long count = 0L;
        if (from < to) {
            int mid = (from + to) /2;
            count += mergeSort(arr, from, mid);
            count += mergeSort(arr,mid+1, to);
            count += mergeAndCount(arr, from, mid, to);
        }
        return count;
    }

    private static long mergeAndCount(int[] arr, int from, int mid, int to) {
        long swaps = 0L;
        int[] left = Arrays.copyOfRange(arr, from, mid+1);
        int[] right = Arrays.copyOfRange(arr, mid+1, to+1);
        int l = 0, r = 0;
        for (int k = from ; k < to+1; k++) {
            if (l < left.length && r < right.length) {
                if (left[l] > right[r]) {
                    arr[k] = right[r++];
                    swaps += (mid + 1) - (from + l);
                } else {
                    arr[k] = left[l++];
                }
            } else if (l < left.length) {
                arr[k] = left[l++];
            } else {
                arr[k] = right[r++];
            }
        }
        return swaps;
    }
}
