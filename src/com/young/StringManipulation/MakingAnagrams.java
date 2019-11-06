package com.young.StringManipulation;

import java.util.HashMap;
import java.util.Map;

public class MakingAnagrams {
    // Alice is taking a cryptography class and finding anagrams to be very useful. We consider two strings
    // to be anagrams of each other if the first string's letters can be rearranged to form the second string.
    // In other words, both strings must contain the same exact letters in the same exact frequency.
    // For example, 'bacdc' and 'dcbac' are anagrams, but 'bacdc' and 'dcbad' are not.
    //
    // Alice decides on an encryption scheme involving two large strings where encryption is dependent on
    // the minimum number of character deletions required to make the two strings anagrams.
    // Can you help her find this number?
    //
    // Given two strings, s1 and s2, that may or may not be of the same length, determine the minimum number
    // of character deletions required to make s1 and s2 anagrams.
    // Any characters can be deleted from either of the strings.
    //
    // For example, if s1 = 'cde' and s2 = 'dcf', we can delete 'e' from string s1 and 'f' from string s2
    // so that both remaining strings are 'cd' and 'dc' which are anagrams.
    // Find how many characters should be deleted from both strings to make them anagrams.

    public static void main(String[] args) {

    }

    private static int makeAnagram(String a, String b) {
        int count = 0;
        Map<Character, Integer> dictA = createMap(a);
        Map<Character, Integer> dictB = createMap(b);

        for (char c : dictA.keySet()) {
            if (dictB.containsKey(c)) {
                count += Math.abs(dictA.get(c) - dictB.get(c));
                dictB.remove(c);
            } else {
                count += dictA.get(c);
            }
        }

        for (char c : dictB.keySet()) {
            count += dictB.get(c);
        }

        return count;
    }

    private static Map<Character, Integer> createMap(String s) {
        Map<Character, Integer> dict = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            dict.put(c, dict.getOrDefault(c, 0)+1);
        }
        return dict;
    }
}
