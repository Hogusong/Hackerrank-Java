package com.young.LinkedLists;

import java.util.LinkedList;
import java.util.List;

public class FindMergePoint {

    public static void main(String[] args) {

    }

    static int findMergeNode(SinglyLinkedListNode head1, SinglyLinkedListNode head2) {
        List<Integer> list1 = new LinkedList<>();
        while (head1 != null) {
            list1.add(head1.data);
            head1 = head1.next;
        }

        List<Integer> list2 = new LinkedList<>();
        while (head2 != null) {
            list2.add(head2.data);
            head2 = head2.next;
        }

        int i = 0, n1 = list1.size(), n2 = list2.size();
        while (i < n1 && i < n2) {
            if (list1.get(n1-i-1) != list2.get(n2-i-1)) {
                return list1.get(n1-i);
            }
            i++;
        }
        if (n1 == n2) return list1.get(1);
        return n1 > n2 ? list2.get(0) : list1.get(0);
    }

    static class SinglyLinkedListNode {
        public int data;
        public SinglyLinkedListNode next;

        public SinglyLinkedListNode(int nodeData) {
            this.data = nodeData;
            this.next = null;
        }
    }

    static class SinglyLinkedList {
        public SinglyLinkedListNode head;
        public SinglyLinkedListNode tail;

        public SinglyLinkedList() {
            this.head = null;
            this.tail = null;
        }

        public void insertNode(int nodeData) {
            SinglyLinkedListNode node = new SinglyLinkedListNode(nodeData);

            if (this.head == null) {
                this.head = node;
            } else {
                this.tail.next = node;
            }

            this.tail = node;
        }
    }
}
