package com.young.LinkedLists;

public class ReverseDoublyLinkedList {

    public static void main(String[] args) {

    }

    static DoublyLinkedListNode reverse(DoublyLinkedListNode head) {
        if (head == null || head.next == null) return head;

        DoublyLinkedListNode curr = head.next;
        DoublyLinkedListNode prev = head;
        DoublyLinkedListNode next = curr.next;
        prev.next = null;
        while (curr != null && curr.next != null) {
            prev.prev = curr;
            curr.next = prev;
            curr.prev = next;

            prev = curr;
            curr = next;
            next = curr.next;
        }
        curr.next = prev;
        return curr;

//        DoublyLinkedListNode curr = head;
//        DoublyLinkedListNode temp = null;
//        while (curr != null) {
//            temp = curr.prev;
//            curr.prev = curr.next;
//            curr.next = temp;
//            curr = curr.prev;
//        }
//        return temp.prev;

    }

    static class DoublyLinkedListNode {
        public int data;
        public DoublyLinkedListNode next;
        public DoublyLinkedListNode prev;

        public DoublyLinkedListNode(int nodeData) {
            this.data = nodeData;
            this.next = null;
            this.prev = null;
        }
    }

    static class DoublyLinkedList {
        public DoublyLinkedListNode head;
        public DoublyLinkedListNode tail;

        public DoublyLinkedList() {
            this.head = null;
            this.tail = null;
        }

        public void insertNode(int nodeData) {
            DoublyLinkedListNode node = new DoublyLinkedListNode(nodeData);

            if (this.head == null) {
                this.head = node;
            } else {
                this.tail.next = node;
                node.prev = this.tail;
            }

            this.tail = node;
        }
    }
}
