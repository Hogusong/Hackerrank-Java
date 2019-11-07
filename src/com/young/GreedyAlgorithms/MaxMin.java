package com.young.GreedyAlgorithms;

import java.util.Arrays;

public class MaxMin {
    // You will be given a list of integers, arr, and a single integer k. You must create an array of length
    // k from elements of arr such that its unfairness is minimized. Call that array subarr.
    // Unfairness of an array is calculated as  max(subarr) - min(subarr)
    //
    // Where:
    //      - max denotes the largest integer in subarr.
    //      - min denotes the smallest integer in subarr.
    //
    // As an example, consider the array [1,4,7,2] with a k of 2. Pick any two elements, test subarr = [4,7].
    //      unfairness = max(4,7) - min(4,7) = 3
    //
    // Testing for all pairs, the solution [1,2] provides the minimum unfairness.
    //
    // Note: Integers in  may not be unique.

    public static void main(String[] args) {
        int[] arr = {5,3,7,1,8,1,2,3,9,6,11};
        System.out.println(maxMin(4, arr));
    }

    private static int maxMin(int k, int[] arr) {
        Arrays.sort(arr);
        int unfairness = Integer.MAX_VALUE;
        for (int i = k-1; i < arr.length; i++) {
            unfairness = Math.min(unfairness, arr[i] - arr[i-k+1]);
        }
        return unfairness;
    }
}
