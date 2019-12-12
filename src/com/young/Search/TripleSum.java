package com.young.Search;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TripleSum {

    public static void main(String[] args) {
        int[] a = {1,2,3,4,5,6,7};
        int[] b = {5,7,9};
        int[] c = {2,3,4,7,9,11,13};
        System.out.println(triplets(a, b, c));
    }

    static long triplets(int[] a, int[] b, int[] c) {
        a = Arrays.stream(a).sorted().distinct().toArray();
        b = Arrays.stream(b).sorted().distinct().toArray();
        c = Arrays.stream(c).sorted().distinct().toArray();
        int x, y, z;
        x = y = z = 0;
        long count = 0;
        while(y < b.length) {
            while(x < a.length && a[x] <= b[y])
                x++;

            while(z < c.length && c[z] <= b[y])
                z++;

            count += x * z;
            y++;
        }
        return count;
    }

    static long triplets1(int[] a, int[] b, int[] c) {
        Arrays.sort(a);
        Arrays.sort(b);
        Arrays.sort(c);
        long count = 0L;
        int j = 0, k = 0, sameA = 0, sameC = 0;
        for (int i = 0; i < b.length; i++) {
            if (i > 0 && b[i] == b[i-1]) continue;
            int x = b[i];
            while (j < a.length) {
                if (j > 0 && a[j] == a[j-1]) sameA++;
                if (x >= a[j]) j++;
                else break;
            }
            while (k < c.length) {
                if (k > 0 && c[k] == c[k-1]) sameC++;
                if (x >= c[k]) k++;
                else break;
            }
            count += (j - sameA) * (k - sameC);
        }
        return count;
    }

    static long triplets2(int[] a, int[] b, int[] c) {
        Arrays.sort(a);
        Arrays.sort(b);
        Arrays.sort(c);
        Map<Integer, Integer> dict = new HashMap<>();
        long count = 0L;
        for (int i = 0; i < a.length; i++) {
            if (i > 0 && a[i] == a[i-1]) continue;
            int x = a[i];
            int cIndex = 0;
            for (int j = 0; j < b.length; j++) {
                int y = b[j];
                if (x > y) continue;
                if (j > 0 && b[j] == b[j-1]) continue;
                if (dict.containsKey(j)) count += dict.get(j);
                else {
                    for (int k = cIndex; k < c.length; k++) {
                        if (k > 0 && c[k] == c[k-1]) continue;
                        if (y >= c[k]) continue;
                        dict.put(j, k);
                        count += k;
                        cIndex = k;
                        break;
                    }
                    if (y >= c[c.length-1]) {
                        count += c.length;
                        dict.put(j, c.length);
                    }
                }
            }
        }
        return count;
    }
}
