package com.young.StringManipulation;

import java.util.HashMap;
import java.util.Map;

public class SpecialStringAgain {
    // A string is said to be a special string if either of two conditions is met:
    //   - All of the characters are the same, e.g. aaa.
    //   - All characters except the middle one are the same, e.g. aadaa.

    // A special substring is any substring of a string which meets one of those criteria.
    // Given a string, determine how many special substrings can be formed from it.
    //
    // For example, given the string s = "mnonopoo", we have the following special substrings:
    //   {m, n, o, n, o, p, o, o, non, ono, opo, oo}

    public static void main(String[] args) {
        System.out.println(substrCount(4,"aaaa"));
    }

    private static long substrCount(int n, String s) {
        long count = (long) n;
        for (int i = 1; i < s.length(); i++) {
            for (int j = 1; j <= s.length()/2; j++) {
                if (i - j < 0 || i + j >= n) break;
                if (passCondition(s.substring(i-j, i+j+1))) count++;
                else break;
            }
            if (passCondition(s.substring(i-1, i+1))) {
                count++;
                for(int j = 1; j <= s.length()/2; j++) {
                    if (i - j - 1 < 0 || i + j >=n) break;
                    if (passCondition(s.substring(i-j-1, i+j+1))) count++;
                    else break;
                }
            }
        }
        return count;
    }

    private static boolean passCondition(String str) {
        char c = str.charAt(0);
        int end = str.length() - 1;
        for (int i = 0; i < str.length() / 2; i++) {
            if (str.charAt(i) != c || str.charAt(end-i)!= c) return false;
        }
        return true;
    }
}
