package com.young.Sorting;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class CompareTo {
    // Comparators are used to compare two objects. In this challenge, you'll create a comparator and
    // use it to sort an array. The Player class is provided in the editor below. It has two fields:
    //      name: a string.
    //      score: an integer.
    // Given an array of n Player objects, write a comparator that sorts them in order of decreasing score.
    // If 2 or more players have the same score, sort those players alphabetically ascending by name.
    // To do this, you must create a Checker class that implements the Comparator interface,
    // then write an int compare(Player a, Player b) method implementing the Comparator.compare(T o1, T o2)
    // method. In short, when sorting in ascending order, a comparator function returns -1 if a < b,
    // 1 if a > b, and 0 if a = b.

    public static void main(String[] args) {
        List<List<Integer>> result = new LinkedList<>();
        Collections.sort(result, new SortOption());
    }
}


class Player {
    String name;
    int score;

    Player(String name, int score) {
        this.name = name;
        this.score = score;
    }
}

class Checker implements Comparator<Player> {
    // complete this method
    public int compare(Player a, Player b) {
        if (a.score > b.score) return -1;
        if (a.score < b.score) return 1;
        return a.name.compareTo(b.name);
    }
}

class SortOption implements Comparator<List<Integer>> {
    public int compare(List<Integer> a, List<Integer> b) {
        for (int i = 0; i < a.size() - 1; i++) {
            if (a.get(i) == b.get(i)) continue;
            return a.get(i) - b.get(i);
        }
        return a.get(a.size()-1) - b.get(a.size()-1);
    }
}
