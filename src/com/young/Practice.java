package com.young;

import java.util.*;

public class Practice {

    static List<List<Integer>> result ;

    public static void main(String[] args) {
        Integer[] nums = {1,2,3,4,5};
        List<Integer> arr = Arrays.asList(nums);
        System.out.println(countTriples(5));
        getSubsets(arr, 3);
    }

    private static int countTriples(int n) {
        if (n < 3) return 0;
        if (n == 3) return 1;
        return countRec(n, 1, "");
    }

    private static int countRec(int n, int i, String s) {
        if (s.length() == 3) {
            return 1;
        }
        if (i > n) return 0;
        int included = countRec(n, i+1, s + i);
        int notIncluded = countRec(n, i+1, s);
        return included + notIncluded;
    }

    private static void getSubsets(List<Integer> arr, int n) {
        result = new LinkedList<>();
        if (arr.size() == n) {
            result.add(arr);
        } else {
            subsetRec(arr, 0, n, new LinkedList<Integer>());
            Collections.sort(result, new SortOption());
            result.forEach(L -> System.out.println(L));
        }
        System.out.println(result);
    }

    private static void subsetRec(List<Integer> arr, int i, int n, List<Integer> temp) {
        if (temp.size() == n) {
            result.add(temp);
            return;
        }
        if (i >= arr.size()) return;
        List<Integer> temp2 = new LinkedList<>();
        temp2.addAll(temp);
        subsetRec(arr, i+1, n, temp2);
        temp2.add(arr.get(i));
        subsetRec(arr, i+1, n, temp2);
    }
}

class SortOption implements Comparator<List<Integer>> {
    public int compare(List<Integer> a, List<Integer> b) {
        for (int i = 0; i < a.size() - 1; i++) {
            if (a.get(i) == b.get(i)) continue;
            return a.get(i) - b.get(i);
        }
        return a.get(a.size()-1) - b.get(a.size()-1);
    }
}

