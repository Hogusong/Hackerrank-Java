package com.young.DictionariesHashMap;

import java.util.HashSet;
import java.util.Set;

public class TwoStrings {
    // Given two strings, determine if they share a common substring.
    // A substring may be as small as one character.
    //
    // For example, the words "a", "and", "art" share the common substring, then return "YES".
    // The words "be" and "cat" do not share a substring, then return "NO".

    public static void main(String[] args) {
        System.out.println(twoStrings("hello", "world"));
        System.out.println(twoStrings("hi", "world"));
    }

    private static String twoStrings(String s1, String s2) {
        Set<Character> charSet = new HashSet<>();
        for (int i = 0; i < s1.length(); i++) {
            charSet.add(s1.charAt(i));
        }

        for (int j = 0; j < s2.length(); j++) {
            if (charSet.contains(s2.charAt(j))) return "YES";
        }
        return "NO";
    }
}
