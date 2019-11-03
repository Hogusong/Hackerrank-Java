package com.young.DictionariesHashMap;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Anagrams {
    // Two strings are anagrams of each other if the letters of one string can be rearranged to form
    // the other string. Given a string, find the number of pairs of substrings of the string
    // that are anagrams of each other.
    //
    // For example s = "mom", the list of all anagrammatic pairs is [m, m] and [mo, om] at positions
    // [0]:[2] and [0,1]:[1,2] respectively. So then answer is 2.

    public static void main(String[] args) {
        System.out.println(sherlockAndAnagrams("abba"));
    }

    private static int sherlockAndAnagrams(String s) {
        int count = 0;
        int len = 1;

        while (len < s.length()) {
            for (int i = 0; i < s.length()-len; i++) {
                String str = s.substring(i, i+len);
                for (int j = i+1; j < s.length()-len+1; j++) {
                    if (checkAnagrams(str, s.substring(j, j+len))) count++;
                }
            }
            len++;
        }
        return count;
    }

    private static boolean checkAnagrams(String A, String B) {
        if (A.length() != B.length()) return false;


        Map<Character, Integer> dict = new HashMap<>();
        for (int i = 0; i < A.length(); i++) {
            char a = A.charAt(i);
            if (!dict.containsKey(a)) dict.put(a, 1);
            else dict.put(a, dict.get(a) + 1);
        }

        for (int j = 0; j < B.length(); j++) {
            char b = B.charAt(j);
            if (!dict.containsKey(b)) return false;
            if (dict.get(b) < 1) return false;
            dict.put(b, dict.get(b) - 1);
        }
        return true;
    }
}
