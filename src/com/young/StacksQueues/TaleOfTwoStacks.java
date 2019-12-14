package com.young.StacksQueues;

import java.util.Scanner;
import java.util.Stack;

public class TaleOfTwoStacks {
    public static void main(String[] args) {
        MyQueue<Integer> queue = new MyQueue<Integer>();

        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();

        for (int i = 0; i < n; i++) {
            int operation = scan.nextInt();
            if (operation == 1) { // enqueue
                queue.enqueue(scan.nextInt());
            } else if (operation == 2) { // dequeue
                queue.dequeue();
            } else if (operation == 3) { // print/peek
                System.out.println(queue.peek());
            }
        }
        scan.close();
    }

    static class MyQueue<T> {
        Stack<T> eqStack = new Stack<>();
        Stack<T> dqStack = new Stack<>();

        void enqueue(T t) {
            eqStack.push(t);
        }

        T dequeue() {
            if (dqStack.empty()) shiftStack();
            return dqStack.pop();
        }

        T peek() {
            if (dqStack.empty()) shiftStack();
            return dqStack.peek();
        }

        void shiftStack() {
            while (!eqStack.empty()) {
                dqStack.push(eqStack.pop());
            }
        }
    }
}
