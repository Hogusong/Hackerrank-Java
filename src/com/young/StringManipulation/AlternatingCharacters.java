package com.young.StringManipulation;

public class AlternatingCharacters {
    // You are given a string containing characters 'A' and 'B' only. Your task is to change it
    // into a string such that there are no matching adjacent characters.
    // To do this, you are allowed to delete zero or more characters in the string.
    //
    // Your task is to find the minimum number of required deletions.
    //
    // For example,
    //   s = "AABAAB", remove an 'A' at positions 0 and 3 to make "ABAB" in 2 deletions.
    //   s = "ABAAABB", remove 2 'A's and a 'B' at positions 3, 4, and 6 to make "ABAB" in 3 deletions.

    public static void main(String[] args) {
        System.out.println(alternatingCharacters("AAABBABBAAA"));
    }

    private static int alternatingCharacters(String s) {
        int count = 0;
        char previous = s.charAt(0);
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == previous) count++;
            else previous = s.charAt(i);
        }
        return count;
    }
}
