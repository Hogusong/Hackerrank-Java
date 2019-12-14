package com.young.StacksQueues;

import java.util.Stack;

public class LargestRectangle {

    public static void main(String[] args) {
        int[] h = {1,3,5,2,5,3,5,1,2};
        System.out.println(largestRectangle(h));
    }

    // Dynamic Programming
    static long largestRectangle1(int[] h) {
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

    // using Stack        int[] h = {1,3,5,2,5,3,5,1,2};
    static long largestRectangle(int[] h) {
        Stack<Integer> stk = new Stack<>();
        long maxA = 0;
        int idx = 0, lastIndex, width;
        while (idx < h.length) {
            if (stk.empty() || h[stk.peek()] < h[idx]) {
                stk.push(idx);
                idx++;
            } else {
                lastIndex = stk.pop();
                width = idx;
                if (!stk.empty()) width = idx - stk.peek() - 1;
                maxA = Math.max(maxA, h[lastIndex] * width);
            }
        }
        while (!stk.empty()) {
            lastIndex = stk.pop();
            width = idx;
            if (!stk.empty()) width = idx - stk.peek() - 1;
            maxA = Math.max(maxA, h[lastIndex] * width);
        }
        return maxA;
    }
}
