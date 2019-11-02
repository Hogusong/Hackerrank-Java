package com.young.Arrays;

public class LeftRotation {
    // A left rotation operation on an array shifts each of the array's elements 1 unit to the left.
    // For example, if 2 left rotations are performed on array [1,2,3,4,5], then the array would become
    // [3,4,5,1,2]. if 5 left rotations are performed, the array would become [1,2,3,4,5].

    public static void main(String[] args) {
        int[] arr = {0,1,2,3,4,5,6};
        arr = rotLeft(arr, 11);
        for (int x : arr) System.out.print(x + "  ");
    }

    public static int[] rotLeft(int[] a, int d) {
        if (d == 0 || d == a.length) return a;
        int[] result = new int[a.length];
        d = d % a.length;
        for (int i = 0; i < a.length; i++) {
            if (i + d < a.length) result[i] = a[i+d];
            else result[i] = a[i+d-a.length];
        }
        return result;
    }
}
