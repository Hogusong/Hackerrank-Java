package com.young.WarmUp;

public class RepeatedString {

    // Lilah has a string, s, of lowercase English letters that she repeated infinitely many times.
    // Given an integer, n, find and print the number of letter a's in the first letters of
    // Lilah's infinite string.
    //
    // For example, if the string s="abcac" and n=12, the substring we consider is "abcacabcacab",
    // the first 12 characters of her infinite string. There are 5 occurrences of a in the substring.
    //
    // Constraints
    //   1 <= s.length() <= 100
    //   1 <= n <= 1000000000000
    //   For 25% of the test cases, n <= 1000000.
    // Example   s="abab", n = 10        answer is 5  ("abab-abab-ab")
    // Example   s="abab", n = 11        answer is 6  ("abab-abab-aba")

    public static void main(String[] args) {
        String s = "abcac";
        long n = 10;
        System.out.println(repeatedString(s, n));
    }

    private static long repeatedString(String s, long n) {
        if (n < s.length()) return countA(s, n);
        long repeat = (long) Math.ceil(n / s.length());
        return repeat * countA(s, s.length()) + countA(s, n % s.length());
    }

    private static long countA(String s, long n) {
        long count = 0L;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == 'a') count++;
        }
        return count;
    }
}
