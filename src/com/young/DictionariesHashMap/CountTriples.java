package com.young.DictionariesHashMap;

import java.util.*;

public class CountTriples {
    // Return the number of triplets of indices (j,k,l) from a given array such that
    // the elements at those indices are in geometric progression for a given common ratio r,
    // k = j * r, and l = k * r = j * r * r.
    //
    // For example, arr = [1, 4, 16, 32, 64]. If r = 4, we have [1, 4, 14] and [4, 16, 64].
    //      1 <= arr[i] <== 10**9

    public static void main(String[] args) {
        Long[] nums = {2L, 4L, 1L, 1L, 8L, 4L, 2L, 2L, 3L, 2L, 4L, 8L};
        List<Long> arr = Arrays.asList(nums);
        long ratio = 2L;
        System.out.println(countTriplets(arr, ratio));
    }

    // Get the result of a sorted array.
    private static long countTriplets(List<Long> arr, long r) {
        if (arr.size() < 3) return 0;

        Map<Long, Integer> right = new HashMap<>();
        Map<Long, Integer> left = new HashMap<>();

        arr.forEach(key -> right.put(key, right.getOrDefault(key, 0) + 1));

        long count = 0L;
        if (r == 1) {
            Map<Integer, Long> dict = new HashMap<>();
            for (long k : right.keySet()) {
                int n = right.get(k);
                if (n > 2) {
                    if (!dict.containsKey(n)) dict.put(n, countTriple(n));
                    count += dict.get(n);
                }
            }
            return count;
        }

        for (int i = 0; i < arr.size(); i++) {
            long key = arr.get(i);
            if (key % r == 0) {
                long once =  key / r;
                long triple = key * r;
                if (left.containsKey(once) && right.containsKey(triple)) {
                    count += left.get(once) * right.get(triple);
                }
            }
            left.put(key, left.getOrDefault(key, 0)+1);
            if (right.get(key) < 2) right.remove(key);
            else right.put(key, right.get(key)-1);
        }

        return count;
    }

    private static long countTriple(int n) {
        long count = 1L;
        long fact = 1L;
        for (int i = 4; i <= n; i++) {
            fact += (i-2);
            count += fact;
        }
        return count;
    }
}
