package com.young.Search;

import java.util.HashSet;
import java.util.Set;

public class Pairs {

    /*
        You will be given an array of integers and a target value. Determine the number of
        pairs of array elements that have a difference equal to a target value.

        For example, given an array of [1, 2, 3, 4] and a target value of 1,
        we have three values meeting the condition: 2-1=1, 3-2=1, and 4-3=1. answer = 3.

        Function Description:

        Complete the pairs function below. It must return an integer representing the number of
        element pairs having the required difference.

        pairs has the following parameter(s):
            k: an integer, the target difference
            arr: an array of integers

        Input Format

        The first line contains two space-separated integers n and k, the size of arr and the target value.
        The second line contains n space-separated integers of the array arr.
    */

    public static void main(String[] args) {
        System.out.println(pairs(2, new int[]{1,5,3,4,2}));
    }

    static int pairs(int k, int[] arr) {
        Set<Integer> container = new HashSet<>();
        int count = 0;
        for (int x : arr) {
            int t1 = x + k;
            int t2 = x - k;
            if (container.contains(t1)) count++;
            if (container.contains(t2)) count++;
            container.add(x);
        }
        return count;
    }
}
