package com.young.Sorting;

public class MaximumToys {
    // Mark and Jane are very happy after having their first child. Their son loves toys,
    // so Mark wants to buy some. There are a number of different toys lying in front of him,
    // tagged with their prices. Mark has only a certain amount to spend,
    // and he wants to maximize the number of toys he buys with this money.
    //
    // Given a list of prices and an amount to spend, what is the maximum number of toys Mark can buy?
    //
    // For example, if prices = [1,2,3,4] and Mark has k = 6 to spend, he can buy items [1,2,3] for 6,
    // or [2,4] for 6 units of currency. He would choose the first group of 3 items.

    private static int maxCount = 0;

    public static void main(String[] args) {
        int[] arr = {7, 5, 9, 2, 10, 1, 3, 8, 4, 6};
        System.out.println(maximumToys(arr, 20));
    }

    private static int maximumToys(int[] prices, int k) {
        prices = mergeSort(prices.clone(), 0, prices.length-1);
        int count = 0, amount = 0;
        for (int i = 0; i < prices.length; i++) {
            if (amount + prices[i] <= k) {
                amount += prices[i];
                count++;
            } else return count;
        }
        return count;
    }

    private static int[] mergeSort(int[] arr, int from, int to) {
        if (from >= to) {
            int[] a = {arr[to]};
            return a;
        }
        int mid = (from + to) / 2;
        int[] A = mergeSort(arr, from, mid);
        int[] B = mergeSort(arr, mid+1, to);
        return merge(A, B);
    }

    private static int[] merge(int[] A, int[] B) {
        int[] arr = new int[A.length + B.length];
        int i = 0, j = 0, k = 0;
        while(j < A.length || k < B.length) {
            if (j < A.length && k < B.length) {
                if (A[j] < B[k]) arr[i++] = A[j++];
                else arr[i++] = B[k++];
            } else if (j < A.length) arr[i++] = A[j++];
            else arr[i++] = B[k++];
        }
        return arr;
    }
}
