package com.young.WarmUp;

public class CountingValleys {
    public static void main(String[] args) {

        // During his hike he took exactly n steps. For every step he took, he noted if it was an uphill, U, or a downhill, D step.
        // Gary's hikes start and end at sea level and each step up or down represents a 1 unit change in altitude.
        // We define the following terms:
        //   - A mountain is a sequence of consecutive steps above sea level, starting with a step up from sea level and ending with a step down to sea level.
        //   - A valley is a sequence of consecutive steps below sea level, starting with a step down from sea level and ending with a step up to sea level.
        // Given Gary's sequence of up and down steps during his last hike, find and print the number of valleys he walked through.
        //
        // For example, if Gary's path is , s = ["DDUUUUDD] he first enters a valley 2 units deep.
        // Then he climbs out an up onto a mountain 2 units high. Finally, he returns to sea level and ends his hike.
        // So he passed 1 valley.

        String s = "DDUUUDDUUDDD";
        System.out.println(countingValleys(s.length(), s));
    }

    private static int countingValleys(int n, String s) {
        int step = 0;
        int level = 0;

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == 'D') {
                if (level == 0) {
                    step++;
                }
                level--;
            } else {
                level++;
            }
        }
        return step;
    }
}
