package com.young.Recursion;

import java.util.HashMap;
import java.util.Map;

public class DavisStaircase {

    static Map<Integer, Integer> dict = new HashMap<>();

    public static void main(String[] args) {

    }

    static int stepPerms(int n) {
        if (n < 0) return 0;
        if (n < 2) return 1;
        if (!dict.containsKey(n-1)) dict.put(n-1, stepPerms(n-1));
        if (!dict.containsKey(n-2)) dict.put(n-2, stepPerms(n-2));
        if (!dict.containsKey(n-3)) dict.put(n-3, stepPerms(n-3));
        return dict.get(n-1) + dict.get(n-2) + dict.get(n-3);
    }
}
