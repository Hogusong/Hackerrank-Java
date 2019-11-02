package com.young.WarmUp;

import java.util.HashSet;
import java.util.Set;

public class SockMerchant {

    public static void main(String[] args) {
        // Given an array of integers representing the color of each sock,
        // determine how many pairs of socks with matching colors there are.
        // Example: 8 socks in array,  3 pairs (1,1), (1,1), (2,2) possible
        int[] socks = {1,2,1,2,1,3,2,1};
        System.out.println(sockMerchant(socks.length, socks));
    }

    private static int sockMerchant(int n, int[] ar) {
        int pairs = 0;
        Set<Integer> colors = new HashSet<>();
        for (Integer i : ar) {
            if (!colors.contains(i)) {
                colors.add(i);
            } else {
                pairs++;
                colors.remove(i);
            }
        }
        return pairs;
    }
}
