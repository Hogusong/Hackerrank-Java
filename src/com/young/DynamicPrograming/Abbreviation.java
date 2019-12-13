package com.young.DynamicPrograming;

import java.util.HashSet;
import java.util.Set;

public class Abbreviation {

    static Set<String> memo;
    static boolean possible;

    public static void main(String[] args) {

    }

    static String abbreviationDP(String a, String b) {
        int lenA = a.length();
        int lenB = b.length();
        int[][] memo = new int[lenA+1][lenB+1];
        memo[0][0] = 1;

        for (int i = 0; i < lenA; i++) {
            for (int j = 0; j <= lenB; j++) {
                if (memo[i][j] != 1) continue;
                if (j < lenB && Character.toUpperCase(a.charAt(i)) == b.charAt(j))
                    memo[i+1][j+1] = 1;
                if (Character.isLowerCase(a.charAt(i))) memo[i+1][j] = 1;
            }
        }

        return memo[lenA][lenB] == 1 ? "YES" : "NO";
    }

    static String abbreviation(String a, String b) {
        memo = new HashSet<>();
        possible = false;

        recursion(a, b);

        return possible ? "YES" : "NO";
    }

    static void recursion(String A, String B) {
        if (possible || A.length() < B.length()) return;
        if (B.isEmpty()) {
            if (A.isEmpty() || isLoweCaseString(A)) possible = true;
            return;
        }

        // using memorization.
        String key = A + ":" + B;
        if (memo.contains(key)) return;
        memo.add(key);

        char a_0 = A.charAt(0);
        if (Character.isLowerCase(a_0)) recursion(A.substring(1), B);

        if (Character.toUpperCase(a_0) == B.charAt(0))
            recursion(A.substring(1), B.substring(1));
    }

    static boolean isLoweCaseString(String S) {
        for (int i = 0; i < S.length(); i++) {
            if (!Character.isLowerCase(S.charAt(i))) return false;
        }
        return true;
    }
}
