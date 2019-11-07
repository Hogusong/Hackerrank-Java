package com.young.GreedyAlgorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LuckBalance {
    // Lena is preparing for an important coding competition that is preceded by a number of sequential
    // preliminary contests. Initially, her luck balance is 0. She believes in "saving luck", and wants
    // to check her theory. Each contest is described by two integers, L[i] and T[i]:
    //
    //  - L[i] is the amount of luck associated with a contest. If Lena wins the contest, her luck balance
    //    will decrease by L[i]; if she loses it, her luck balance will increase by L[i].
    //  - T[i] denotes the contest's importance rating. It's equal to 1 if the contest is important,
    //    and it's equal to 0 if it's unimportant.
    //
    // If Lena loses no more than k important contests, what is the maximum amount of luck she can have
    // after competing in all the preliminary contests? This value may be negative.
    //
    //      k = 3 and arr = [[5, 1], [2, 1], [1, 1], [8, 1], [10, 0], [5, 0]]
    //      answer = 29 = 5 + 2 + 8 + 10 + 5 - 1

    public static void main(String[] args) {

    }

    private     static int luckBalance(int k, int[][] contests) {
        List<Integer> arr = new ArrayList<>();
        int bal = 0;
        for (int i = 0; i < contests.length; i++) {
            bal += contests[i][0];
            if (contests[i][1] == 1) arr.add(contests[i][0]);
        }
        Collections.sort(arr);
        int diff = arr.size() - k;
        for (int i = 0; i < diff; i++) {
            bal -= arr.get(i) * 2;
        }
        return bal;
    }
}
