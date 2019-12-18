package com.young.LinkedLists;

public class DetectCycle {

    public static void main(String[] args) {

    }

    boolean hasCycle(Node head) {
        Node fast = head;
        Node slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return true;
        }
        return false;
    }

    static class Node {
        int data;
        Node next;
    }
}
