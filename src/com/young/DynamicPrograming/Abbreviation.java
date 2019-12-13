package com.young.DynamicPrograming;

import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

public class Abbreviation {

    static Set<String> memo;
    static boolean possible;

    public static void main(String[] args) {
        String a = "ABC".toLowerCase();
        System.out.println(a.substring(1,2));
        System.out.println(abbreviation("KXzQ", "K"));
        System.out.println(abbre("AbcaC", "ACC"));
    }

    static String abbreviation(String a, String b) {
        possible = false;
        memo = new HashSet<>();
        rec(a, b);
        return possible ? "YES" : "NO";
    }

    static void rec(String A, String B) {
        if (possible || A.length() < B.length()) return;
        if (B.isEmpty()) {
            if (A.isEmpty() || isStringLowerCase(A)) {
                possible = true;
            }
            return;
        }

        String key = A + ":" + B;
        if (memo.contains(key)) return;
        memo.add(key);

        char fc = A.charAt(0);
        A = A.substring(1);
        if (fc >= 'a' && fc <= 'z') rec(A, B);

        // stop if upper case of A's first character is equal to B's first character.
        if (Character.toUpperCase(fc) != B.charAt(0)) return;
        rec(A, B.substring(1));
    }

    private static boolean isStringLowerCase(String str){

        for(int i = 0; i < str.length(); i++) {
            // if any character is not in lower case, return false
            if (!Character.isLowerCase( str.charAt(i)) ) return false;
        }

        return true;
    }

    private static String abbre(String a, String b) {
        int n = a.length();
        int m = b.length();
        int[][] ans = new int[n+1][m+1];
        ans[0][0] = 1;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= m; j++) {
                if (ans[i][j] != 1) continue;
                if (j < m && Character.toUpperCase(a.charAt(i)) == b.charAt(j))
                    ans[i+1][j+1] = 1;
                if (Character.isLowerCase(a.charAt(i))) ans[i+1][j] = 1;
            }
        }

        return ans[n][m] == 1 ? "YES" : "NO";
    }
}
