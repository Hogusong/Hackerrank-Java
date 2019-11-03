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
}