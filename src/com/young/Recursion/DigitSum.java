package com.young.Recursion;

public class DigitSum {

    public static void main(String[] args) {
        System.out.println(superDigit("3546630947312051453014172159647935984478824945973141333062252613718025688716704470547449723886626736", 100000));
    }

    static int superDigit(String n, int k) {
        long sum = 0;
        for (int i = 0; i < n.length(); i++) {
            sum += Long.parseLong(n.substring(i,i+1));
        }
        sum *= k;
        if (sum < 10) return (int)sum;
        return superDigit(""+sum, 1);
    }
}
