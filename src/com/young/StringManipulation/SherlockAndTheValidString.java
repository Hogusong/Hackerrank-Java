package com.young.StringManipulation;

import java.util.HashMap;
import java.util.Map;

public class SherlockAndTheValidString {
    // Sherlock considers a string to be valid if all characters of the string appear the same number
    // of times. It is also valid if he can remove just 1 character at 1 index in the string,
    // and the remaining characters will occur the same number of times.
    // Given a string s, determine if it is valid. If so, return YES, otherwise return NO.
    //
    // For example, if s = "abc", it is a valid string because frequencies are {a:1, b:1, c:1}.
    // So is s = "abcc" because we can remove one 'c' and have 1 of each character in the remaining string.
    // If s = "abccc" however, the string is not valid as we can only remove 1 occurrence of 'c'.
    // That would leave character frequencies of {a:1, b:1, c:2}. So return "NO".

    public static void main(String[] args) {

    }

    private static String isValid(String s) {
        Map<Character, Integer> dict = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            dict.put(c, dict.getOrDefault(c, 0)+1);
        }

        Map<Integer, Integer> frequency = new HashMap<>();
        for (char c : dict.keySet()) {
            int k = dict.get(c);
            frequency.put(k, frequency.getOrDefault(k, 0)+1);
            if (frequency.size() > 2) return "NO";
        }

        if (frequency.size() < 2) return "YES";

        int x = 0, countX = 0, y = 0, countY = 0;
        for (int key : frequency.keySet()) {
            if (x == 0) {
                x = key;
                countX = frequency.get(key);
            } else {
                y = key;
                countY = frequency.get(key);

            }
        }

        if (x == 1 && countX == 1) return "YES";
        if (y - x == 1 && countY == 1) return "YES";

        return "NO";
    }
}
