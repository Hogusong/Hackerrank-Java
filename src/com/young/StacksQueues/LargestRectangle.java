package com.young.StacksQueues;

public class LargestRectangle {

    public static void main(String[] args) {
        int[] h = {1,2,3,4,5,1};
        System.out.println(largestRectangle(h));
    }

    static long largestRectangle(int[] h) {
        long maxA = 0;
        long area;
        for (int i = 0; i < h.length; i++) {
            int left = i, right = i;
            boolean toLeft = true, toRight = true;
            while (toLeft || toRight) {
                if (left > 0) {
                    if (h[left] >= h[i]) left--;
                    else toLeft = false;
                } else toLeft = false;
                if (right < h.length-1) {
                    if (h[right] >= h[i]) right++;
                    else toRight = false;
                } else toRight = false;
            }
            if (h[i] > h[left]) left++;
            if (h[i] > h[right]) right--;
            area = h[i] * (right - left + 1);
            maxA = Math.max(maxA, area);
        }
        return maxA;
    }
}
