package com.young.LinkedLists;

public class InsertNodeIntoSortedDoublyLinkedList {

    public static void main(String[] args) {

    }

    static DoublyLinkedListNode sortedInsert(DoublyLinkedListNode head, int data) {
        DoublyLinkedListNode newNode = new DoublyLinkedListNode(data);
        if (newNode.data < head.data) {
            newNode.next = head;
            head.prev = newNode;
            return newNode;
        }
        DoublyLinkedListNode previous = head;
        DoublyLinkedListNode curr = head;
        while (curr != null && curr.data < data) {
            previous = curr;
            curr = curr.next;
        }
        previous.next = newNode;
        newNode.prev = previous;
        newNode.next = curr;
        if (curr != null) curr.prev = newNode;
        return head;
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