package com.young.Sorting;

import java.util.HashMap;
import java.util.Map;

public class FraudulentActivityNotifications {
    //

    public static void main(String[] args) {
        int a = 3/2;
//        System.out.println(a);
        int[] arr = {2, 3, 4, 2, 3, 6, 8, 4, 5};
        System.out.println(activityNotifications(arr, 5));
        System.out.println();
        System.out.println(activityNotifications(arr, 6));

    }

    private static int activityNotifications(int[] expenditure, int d) {
        if (expenditure.length <= d) return 0;
        int count = 0;
        Map<Integer, Integer> dict = new HashMap<>();
        for (int i = 0; i < d; i++) {
            int key = expenditure[i];
            dict.put(key, dict.getOrDefault(key, 0)+1);
        }


        for (int i = d; i < expenditure.length; i++) {
            printDict(dict);
            double median = getMedian(dict, d);
            System.out.println(median);
            if (expenditure[i] >= 2 * median) count++;
            updateDict(dict, expenditure[i-d], expenditure[i]);
        }
        return count;
    }

    private static double getMedian(Map<Integer, Integer> dict, int d) {
        int mid = d / 2 + 1, preValue = 0;
        for (int k : dict.keySet()) {
            int value = dict.get(k);
            if (value < mid) {
                mid -= value;
                preValue = k;
            } else {
                if (d % 2 == 0 && mid == 1) return (float) (k + preValue) / 2;
                return (double) k;
            }
        }
        return 0.0;
    }

    private static void updateDict(Map<Integer, Integer> dict, int outKey, int inKey) {
        if (dict.get(outKey) < 2) dict.remove(outKey);
        else dict.put(outKey, dict.get(outKey)-1);
        dict.put(inKey, dict.getOrDefault(inKey, 0)+1);
    }

    private static void printDict(Map<Integer, Integer> dict) {
        for (int k : dict.keySet()) System.out.print(k + ":" + dict.get(k) + " ");
        System.out.println();
    }
}
