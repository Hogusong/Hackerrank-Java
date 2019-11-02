package com.young.Arrays;

import java.util.HashMap;
import java.util.Map;

public class NewYearChaos {
    // It's New Year's Day and everyone's in line for the Wonderland rollercoaster ride!
    // There are a number of people queued up, and each person wears a sticker indicating their initial
    // position in the queue. Initial positions increment by 1 from 1 at the front of the line to n at the back.
    //
    //
    // Any person in the queue can bribe the person directly in front of them to swap positions.
    // If two people swap positions, they still wear the same sticker denoting their original places in line.
    // One person can bribe at most two others. For example, if n=8 and Person5 bribes Person4,
    // the queue will look like this: 1, 2, 3, 5, 4, 6, 7, 8.
    //
    // Fascinated by this chaotic queue, you decide you must know the minimum number of bribes
    // that took place to get the queue into its current state!
    //
    // Print an integer representing the minimum number of bribes necessary, or Too chaotic
    // if the line configuration is not possible.

    public static void main(String[] args) {
        int[] a = {5, 1, 2, 3, 7, 8, 6, 4};
        minimumBribes(a);
        int[] b = {1, 2, 5, 3, 7, 8, 6, 4};
        minimumBribes(b);
    }

    private static void minimumBribes(int[] q) {
        Map<Integer,Integer> dict = new HashMap<>();
        int count = 0, index = q.length - 1;
        while (index > 0) {
            int vPre = q[index-1];
            int vCur = q[index];

            // Find they were bribed of not.
            if (vPre > vCur) {
                // Check the previous value was bribed twice already or not.
                if (dict.containsKey(vPre) && dict.get(vPre) > 1) {
                    System.out.println("Too chaotic");
                    return;
                } else {
                    // Add bribed count for the previous value.
                    if (!dict.containsKey(vPre)) dict.put(vPre, 1);
                    else dict.put(vPre, dict.get(vPre) + 1);

                    // Swap two positions because they were bribed.
                    q[index] = vPre;
                    q[index-1] = vCur;
                    count++;

                    // move index back to move comparing position.
                    if (index < q.length - 1) index++;
                }
            } else index--;
        }
        System.out.println(count);
    }
}
