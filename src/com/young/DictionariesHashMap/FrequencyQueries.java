package com.young.DictionariesHashMap;

import java.util.*;

public class FrequencyQueries {
    // You are given  queries. Each query is of the form two integers described below:
    //   (1,x) : Insert x in your data structure.
    //   (2,y) : Delete one occurence of y from your data structure, if present.
    //   (3,z) : Check if any integer is present whose frequency is exactly z. If yes, print 1 else 0.
    //
    // The queries are given in the form of a 2-D array queries of size q where queries[i][0] contains
    // the operation, and queries[i][1] contains the data element.
    // For example, you are given array queries = [(1,4), (2,2), (3,2), (1,1), (1,4), (3,4)].
    // The results of each operation are:
    // Operation   Array   Output
    // (1,4)       [4]
    // (2,2)       [4]
    // (3,2)                   0    : because of no 2 same elements
    // (1,1)       [4,1]
    // (1,4)       [4,1,4]
    // (3,2)                   1    : because there are 2 element 4s.

    public static void main(String[] args) {

    }

    static List<Integer> freqQuery(List<List<Integer>> queries) {
        Map<Integer, Integer> dict = new HashMap<>();
        List<Integer> result = new ArrayList<>();
        for (List<Integer> q : queries) {
            int option = q.get(0), key = q.get(1);
            if (option == 1) {
                if (!dict.containsKey(key)) dict.put(key, 1);
                else dict.put(key, dict.get(key) + 1);
            } else if (option == 2) {
                if (dict.containsKey(key)) {
                    if (dict.get(key) == 1) dict.remove(key);
                    else dict.put(key, dict.get(key) - 1);
                }
            } else {
                int ans = dict.values().contains(key) ? 1 : 0;
                result.add(ans);
            }
        }
        return result;
    }

    private static List<Integer> freqQueries(List<List<Integer>> queries) {
        Map<Integer, Integer> container = new HashMap<>();
        Map<Integer, Set<Integer>> freq = new HashMap<>();
        List<Integer> result = new ArrayList<>();
        for (List<Integer> q : queries) {
            int option = q.get(0), key = q.get(1);
            if (option == 1) {
                if (!container.containsKey(key)) {
                    container.put(key, 1);
                    addFreq(freq, 1, key);
                } else {
                    container.put(key, container.get(key)+1);
                    int freqKey = container.get(key);
                    addFreq(freq, freqKey, key);
                    removeFreq(freq, freqKey-1, key);
                }
            } else if (option == 2) {
                if (container.containsKey(key)) {
                    int freqKey = container.get(key);
                    if (freqKey == 1) container.remove(key);
                    else {
                        container.put(key, freqKey-1);
                        addFreq(freq, freqKey-1, key);
                    }
                    removeFreq(freq, freqKey, key);
                }
            } else {
                if (freq.containsKey(key)) result.add(1);
                else result.add(0);
            }
        }
        return result;
    }

    private static void addFreq(Map<Integer, Set<Integer>> freq, int freqKey, int value) {
        Set<Integer> freqSet;
        if (!freq.containsKey(freqKey)) freq.put(freqKey, new HashSet<Integer>());
        freqSet = freq.get(freqKey);
        freqSet.add(value);
        freq.put(freqKey, freqSet);
    }

    private static void removeFreq(Map<Integer, Set<Integer>> freq, int freqKey, int key) {
        Set<Integer> freqSet = freq.get(freqKey);
        freqSet.remove(key);
        if (freqSet.size() > 0) freq.put(freqKey, freqSet);
        else freq.remove(freqKey);
    }
}