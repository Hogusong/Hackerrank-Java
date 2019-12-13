package com.young.Search;

import java.util.Arrays;

public class MinTime {

    public static void main(String[] args) {
        long[] d = {4, 5, 6};
        System.out.println(minTime(d, 12));
    }

    static long minTime(long[] machines, long goal) {
        Arrays.sort(machines);
        int n = machines.length;
        int count;
        long maxDays = machines[n-1];
        long days = maxDays;
        while (true) {
            count = 0;
            for (int i = 0; i < n; i++) {
                int x = (int) (days / machines[i]);
                if (x >= 1) count += x;
                else break;
            }
            if (count >= goal) break;
            days += maxDays;
        }

        while (count >= goal) {
            count = 0;
            for (int i = 0; i < n; i++) {
                int x = (int) (days / machines[i]);
                if (x >= 1) count += x;
                else break;
            }
            days--;
        }
        return days + 2;
    }
}