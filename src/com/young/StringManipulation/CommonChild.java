package com.young.StringManipulation;

public class CommonChild {
    // A string is said to be a child of a another string if it can be formed by deleting 0 or
    // more characters from the other string. Given two strings of equal length,
    // what's the longest string that can be constructed such that it is a child of both?
    //
    // For example, ABCD and ABDC have two children with maximum length 3, ABC and ABD.
    // They can be formed by eliminating either the D or C from both strings.
    // Note that we will not consider ABCD as a common child because we can't rearrange characters
    // and ABCD != ABDC.

    private static int maxLen = 0;

    public static void main(String[] args) {

        String s1 = "HARRY", s2 = "SALLY";
        System.out.println(commonChild(s1, s2));
        System.out.println(findMaxCommonLength(s1, s2));
    }

    private static int commonChild(String s1, String s2) {
        int index = s2.indexOf(s1.substring(0,1));
        if (index >= 0) findCommon(s1, s2, 1, index+1, 1);
        findCommon(s1, s2, 1, 0, 0);
        return maxLen;
    }

    private static void findCommon(String s1, String s2, int i, int searchFrom, int count) {
        if (i >= s1.length()) {
            maxLen = Math.max(maxLen, count);
            return;
        }
        int index = s2.indexOf(s1.substring(i, i+1), searchFrom);
        if (index >= searchFrom) findCommon(s1, s2, i+1, index+1, count+1);
        findCommon(s1, s2, i+1, searchFrom, count);
    }

    private static int findMaxCommonLength(String s1, String s2) {
        int len1 = s1.length();
        int len2 = s2.length();
        int[][] commonLengths = new int[len1 + 1][len2 + 1];
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    commonLengths[i][j] = commonLengths[i - 1][j - 1] + 1;
                } else {
                    commonLengths[i][j] = Math.max(commonLengths[i - 1][j], commonLengths[i][j - 1]);
                }
            }
        }
        return commonLengths[len1][len2];
    }
}
