package com.young.Trees;

import java.util.LinkedList;
import java.util.List;

public class HeightOfBinaryTree {

    public static void main(String[] args) {
        int[] nums = {5,3,8,7,1,4,9,2,6};
        Node root = new Node(nums[0]);
        for (int i = 1; i < nums.length; i++) {
            addNode(root, nums[i]);
//            root = insert(root, nums[i]);
        }

        System.out.println(getHeight(root, 0, 0) - 1);

//        List<Integer> result = new LinkedList<>();
//        convertToList(root, result);
//        result.forEach(d -> System.out.print(d + " "));

    }

    static int getHeight(Node node, int depth, int maxDepth) {
        if (node == null) {
            maxDepth = Math.max(depth, maxDepth);
            return maxDepth;
        }
        int left = getHeight(node.left, depth+1, maxDepth);
        int right = getHeight(node.right, depth+1, maxDepth);

        return Math.max(maxDepth, Math.max(left, right));
    }

    static void convertToList(Node node, List<Integer> result) {
        if (node == null) return;

        convertToList(node.left, result);
        result.add(node.data);
        convertToList(node.right, result);
    }

    static void addNode(Node node, int data) {
        if (node.data > data) {
            if (node.left == null) node.left = new Node(data);
            else addNode(node.left, data);
        } else {
            if (node.right == null) node.right = new Node(data);
            else addNode(node.right, data);
        }
    }

    public static Node insert(Node curr, int data) {
        if(curr == null) {
            return new Node(data);
        } else {
            Node temp;
            if(data <= curr.data) {
                temp = insert(curr.left, data);
                curr.left = temp;
            } else {
                temp = insert(curr.right, data);
                curr.right = temp;
            }
            return curr;
        }
    }

    static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
            left = null;
            right = null;
        }
    }
}
