package com.young.Search;

import java.util.HashMap;
import java.util.Map;

public class IceCreamParlor {
    // Each time Sunny and Johnny take a trip to the Ice Cream Parlor, they pool their money to buy
    // ice cream. On any given day, the parlor offers a line of flavors.
    // Each flavor has a cost associated with it.
    //
    // Given the value of money and the cost of each flavor for t trips to the Ice Cream Parlor,
    // help Sunny and Johnny choose two distinct flavors such that they spend their entire pool of money
    // during each visit. ID numbers are the 1-based index number associated with a cost. For each trip
    // to the parlor, print the ID numbers for the two types of ice cream that Sunny and Johnny purchase
    // as two space-separated integers on a new line. You must print the smaller ID first and the larger
    // ID second.
    //
    // For example, there are n = 5 flavors having cost = [2,1,3,5,6]. Together they have money = 5 to spend.
    // They would purchase flavor ID's 1 and 3 for a cost of 2+3=5. Use 1 based indexing for your response.
    //
    // Note:
    //    - Two ice creams having unique IDs i and j may have the same cost (i.e.,cost[i] == cost[j]).
    //    - There will always be a unique solution.

    public static void main(String[] args) {

    }

    private static void whatFlavors(int[] cost, int money) {
        Map<Integer, Integer> dict = new HashMap<>();
        for (int i = 0; i < cost.length; i++) {
            int diff = money - cost[i];
            if (dict.containsKey(diff)) {
                System.out.println(dict.get(diff) + " " + (i+1));
                break;
            }
            dict.put(cost[i], i+1);
        }
    }
}
