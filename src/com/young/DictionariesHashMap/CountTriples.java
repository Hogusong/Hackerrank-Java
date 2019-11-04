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

    // Get the result of an array(as it is).
    private static long countTriplets(List<Long> arr, long r) {
        long ans = 0;
        Map<Long, Long> right = new HashMap<>();
        Map<Long, Long> left = new HashMap<>();

        arr.forEach(key -> {
            right.put(key, right.getOrDefault(key,0L)+1);
        });

        for (Long key : arr) {
            if (key % r == 0) {
                Long once = key / r;
                Long triple = key * r;

                if (left.containsKey(once) && right.containsKey(triple)) {
                    ans += (left.get(once) * right.get(triple));
                }
            }

            right.put(key, right.get(key) - 1);
            left.put(key, left.getOrDefault(key, 0L) + 1);
        }
        return ans;
    }

    // Get the result of a sorted array.
    private static long countTriplet(List<Long> arr, long r) {
        if (arr.size() < 3) return 0L;
        long count = 0L;
        Map<Long, Long> dict = new HashMap<>();
        for (long k : arr) {
            if (!dict.containsKey(k)) dict.put(k, 1L);
            else dict.put(k, dict.get(k)+1);
        }
        if (r == 1) {
            for (long k : dict.keySet()) {
                if (dict.get(k) > 2) count += countTriple(dict.get(k));
            }
            return count;
        }

        for (long x : dict.keySet()) {
            if (x % r == 0L) {
                long i = dict.getOrDefault(x/r, 0L);
                long j = dict.getOrDefault(x, 0L);
                long k = dict.getOrDefault(x*r,0L);
                count += i * j * k;
            }
        }
        return count;
    }

    private static long countTriple(long n) {
        long count = 1L;
        long fact = 1L;
        for (long i = 4L; i < n+1; i++) {
            fact += (i-2);
            count += fact;
        }
        return count;
    }
}
