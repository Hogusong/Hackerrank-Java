package com.young.StacksQueues;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PoisonousPlants {

    public static void main(String[] args) {
        int[] p = {4,3,7,5,6,4,2};
        System.out.println(poisonousPlants(p));
    }

    // Solved but terminated due to timeout
    static int poisonousPlants1(int[] p) {
        List<Integer> a = new LinkedList<>();
        List<Integer> b = new LinkedList<>();
        a.add(p[0]);
        for (int i = 1; i < p.length; i++) {
            if (p[i] <= p[i - 1]) a.add(p[i]);
        }

        if (p.length == a.size()) return 0;

        int count = 0;
        while (a.size() != b.size()) {
            if (count % 2 == 0) checkPlants(a, b);
            else checkPlants(b, a);
            count++;
        }
        return count;
    }

    static void checkPlants(List<Integer> x, List<Integer> y) {
        y.clear();
        y.add(x.get(0));
        for (int i = 1; i < x.size(); i++) {
            if (x.get(i) <= x.get(i - 1)) y.add(x.get(i));
        }
    }

/*
    // Good Solution but I didn't understand yet.
    static int poisonousPlants(int[] p) {
        List<LinkList> parts = new ArrayList<LinkList>();
        LinkList lastPart = null;
        for (int i = 0; i < p.length; i++) {
            if (i > 0 && p[i] <= p[i - 1]) {
                lastPart.append(p[i]);
            } else {
                LinkList part = new LinkList();
                part.append(p[i]);

                parts.add(part);
                lastPart = part;
            }
        }

        int day = 0;
        while (parts.size() > 1) {
            for (int i = 1; i < parts.size(); i++) {
                parts.get(i).removeFirst();
            }

            List<LinkList> nextParts = new ArrayList<LinkList>();
            for (LinkList part : parts) {
                if (part.head == null) {
                    continue;
                }

                if (!nextParts.isEmpty() && nextParts.get(nextParts.size() - 1).tail.value >= part.head.value) {
                    nextParts.get(nextParts.size() - 1).append(part);
                } else {
                    nextParts.add(part);
                }
            }
            parts = nextParts;

            day++;
        }
        return day;
    }

    static class Node {
        int value;
        Node next;

        Node(int value) {
            this.value = value;
        }
    }

    static class LinkList {
        Node head;
        Node tail;

        void append(int value) {
            Node node = new Node(value);
            if (tail == null) {
                head = node;
            } else {
                tail.next = node;
            }
            tail = node;
        }

        void append(LinkList list) {
            tail.next = list.head;
            tail = list.tail;
        }

        void removeFirst() {
            head = head.next;
            if (head == null) {
                tail = null;
            }
        }
    }
 */
    // Solved but Timeout.
    static int poisonousPlants(int[] p) {
        int n = p.length, days = 0;
        LinkList root = new LinkList(p[n-1]);
        LinkList temp;
        LinkList curr = root;
        for (int i = n-2; i >= 0; i--) {
            temp = new LinkList(p[i]);
            curr.append(temp);
            curr = temp;
        }

        boolean removed = true;
        while (removed) {
            removed = false;
            curr = root;
            while (curr != null && curr.tail != null) {
                if (curr.value > curr.tail.value) {
                    temp = curr.tail;
                    if (curr.head == null) root = temp;
                    else curr.removeList();
                    curr = temp;
                    removed = true;
                } else {
                    curr = curr.tail;
                }
            }
            if (removed) days++;
        }

        return days;
    }

    static class LinkList {
        int value;
        LinkList head;
        LinkList tail;

        LinkList(int value) {
            this.value = value;
            this.head = null;
            this.tail = null;
        }

        void append(LinkList list) {
            this.tail = list;
            list.head = this;
        }

        void removeList() {
            if (this.tail == null) this.head.tail = null;
            else {
                this.tail.head = this.head;
                this.head.tail = this.tail;
            }
        }
    }

    static void printList(LinkList curr) {
        while (curr != null) {
            System.out.print(curr.value + " ");
            curr = curr.tail;
        }
        System.out.println();
    }
}
