package com.young.Trees;

import java.util.LinkedList;
import java.util.List;

public class HuffmanDecoding {

    public static void main(String[] args) {

    }

    void decode(String s, Node root) {
        String output = "";
        int i = 0;
        Node curr = root;
        while (i < s.length()) {
            curr = (s.charAt(i++) == '0') ? curr.left : curr.right;
            if (curr.left == null && curr.right == null) {
                output += curr.data;
                curr = root;
            }
        }
        System.out.println(output);
    }

    void decodeRec(String s, Node root) {
        List<Character> result = new LinkedList<>();
        int i = 0;
        String output = "";
        while (i < s.length()) {
            i = decoding(s, i, root, result);
        }

        for (char c : result) {
            output += Character.toString(c);
        }
        System.out.println(output);
    }

    int decoding(String s, int i, Node node, List<Character> result) {
        node = (s.charAt(i++) == '0') ? node.left : node.right;
        if (node.left == null && node.right == null) {
            result.add(node.data);
            return i;
        }
        return decoding(s, i, node, result);
    }

    int decoding2(String s, int i, Node node, List<Character> result) {
        if (node.left == null && node.right == null) {
            result.add(node.data);
            return i;
        }
        if (s.charAt(i) == '0') return decoding2(s, i + 1, node.left, result);
        return decoding2(s, i + 1, node.right, result);
    }

    abstract class Node implements Comparable<Node> {
        public int frequency; // the frequency of this tree
        public char data;
        public Node left, right;

        public Node(int freq) {
            frequency = freq;
        }

        // compares on the frequency
        public int compareTo(Node tree) {
            return frequency - tree.frequency;
        }
    }
}
