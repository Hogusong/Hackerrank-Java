package com.young.DictionariesHashMap;

import java.util.HashMap;
import java.util.Map;

public class RansomNote {
    // Harold is a kidnapper who wrote a ransom note, but now he is worried it will be traced back to him
    // through his handwriting. He found a magazine and wants to know if he can cut out whole words from it
    // and use them to create an untraceable replica of his ransom note.
    // The words in his note are case-sensitive and he must use only whole words available in the magazine.
    // He cannot use substrings or concatenation to create the words he needs.
    //
    // Given the words in the magazine and the words in the ransom note,
    // print Yes if he can replicate his ransom note exactly using whole words from the magazine;
    // otherwise, print No.
    //
    // For example, the note is "Attack at dawn". The magazine contains only "attack at dawn".
    // The magazine has all the right words, but there's a case mismatch. The answer is NO.
    //
    // He need "give one grand today" and the magazine contains "give me one grand today night", then YES.
    // He need "two times two is four" and the magazine contains "two times three is not four", then NO.

    public static void main(String[] args) {

    }

    private static void checkMagazine(String[] magazine, String[] note) {
        Map<String, Integer> dict = new HashMap<>();

        for (String s : magazine) {
            if (!dict.containsKey(s)) dict.put(s, 1);
            else dict.put(s, dict.get(s) + 1);
        }

        for (String n : note) {
            if (!dict.containsKey(n)) {
                System.out.println("No");
                return;
            } else {
                if (dict.get(n) < 1) {
                    System.out.println("No");
                    return;
                } else dict.put(n, dict.get(n) - 1);
            }
        }
        System.out.println("Yes");
    }

    private static void checkMagazine2(String[] magazine, String[] note) {
        if (note.length > magazine.length) {
            System.out.println("No");
            return;
        }
        Map<String, Integer> dict = new HashMap<>();

        for (String w : note) {
            int indexFrom = 0;
            if (dict.containsKey(w)) indexFrom = dict.get(w) + 1;
            int index = findIndex(magazine, indexFrom, w);
            if (index >= indexFrom) {
                dict.put(w, index);
            } else {
                System.out.println("No");
                return;
            }
        }
        System.out.println("Yes");
    }

    private static int findIndex(String[] arr, int from, String w) {
        for (int i = from; i < arr.length; i++) {
            if (arr[i].equals(w)) return i;
        }
        return -1;
    }
}
