package com.young.Sorting;

public class BubbleSort {
    // Swap elements to sort and print the result like
    //      Array is sorted in 0 swaps.
    //      First Element: 1
    //      Last Element: 3

    public static void main(String[] args) {
        int[] arr = {7, 5, 9, 2, 10, 1, 3, 8, 4, 6};
        bubbleSort(arr);
    }

    private static void bubbleSort(int[] arr) {
        int x = 0, count = 0;
        while (x < arr.length) {
            for (int i = 1; i < arr.length - x; i++) {
                if (arr[i-1] > arr[i]) {
                    int temp = arr[i];
                    arr[i] = arr[i-1];
                    arr[i-1] = temp;
                    count++;
                }
            }
            x++;
        }
        System.out.println("Array is sorted in " + count + " steps.");
        System.out.println("First Element: " + arr[0]);
        System.out.println("Last Element: " + arr[arr.length-1]);
        for (int i : arr) System.out.print(i + " ");
    }
}
