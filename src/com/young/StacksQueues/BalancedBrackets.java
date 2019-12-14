package com.young.StacksQueues;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class BalancedBrackets {

    public static void main(String[] args) {
        System.out.println(isBalanced("{[()]}"));
        System.out.println(isBalanced("{[(])}"));
        System.out.println(isBalanced("{{[[(())]]}}"));
    }

    static String isBalanced(String s) {
        Stack<Character> sk = new Stack<>();
        Map<Character, Character> dict = new HashMap<>();
        dict.put(')', '(');
        dict.put(']', '[');
        dict.put('}', '{');
        sk.push(s.charAt(0));
        int i = 1;
        while (i < s.length()) {
            char key = s.charAt(i);
            if (dict.containsKey(key)) {
                if (sk.empty()) return "NO";
                char c = sk.pop();
                if (c != dict.get(key)) return "NO";

            } else sk.push(s.charAt(i));
            i++;
        }
        return sk.empty() ? "YES" : "NO";
    }
}
