package com.young.Search;

public class MakingCandies {

    public static void main(String[] args) {
        System.out.println(minimumPasses(1, 1, 6, 45));
    }

    static long minimumPasses(long m, long w, long p, long n) {
        long minPass = Long.MAX_VALUE;
        long currentPass = 0;
        long production = 0;
        while (true) {
            long remainPass = divideToCeil(n - production, m * w);
            minPass = Math.min(minPass, currentPass + remainPass);

            if (remainPass == 1) {
                break;
            }

            if (production < p) {
                long extraPass = divideToCeil(p - production, m * w);

                currentPass += extraPass;
                production += extraPass * m * w;

                if (production >= n) {
                    return Math.min(minPass, currentPass);
                }
            }

            production -= p;
            if (m <= w) {
                m++;
            } else {
                w++;
            }
        }
        return minPass;
    }

    static long divideToCeil(long x, long y) {
        return x / y + (x % y == 0 ? 0 : 1);
    }

    static long minimumPasses1(long m, long w, long p, long n) {
        long count = 1, product = m * w;
        long minPass = n;
        while (product < n) {
            long remainPass = divideToCeil(n - product, m * w);
            minPass = Math.min(minPass, count + remainPass);

            if (remainPass == 1) {
                return minPass;
            }
            int unit = (int) (product / p);
            product = product % p;
            if (m != w) {
                long diff = Math.abs(m - w);
                if (m > w) {
                    if (unit > diff) {
                        unit -= diff;
                        w += diff;
                    } else {
                        w += unit;
                        unit = 0;
                    }
                } else {
                    if (unit > diff) {
                        unit -= diff;
                        m += diff;
                    } else {
                        m += unit;
                        unit = 0;
                    }
                }
            }
            int half = (int) (unit / 2);
            m += half;
            w += unit - half;
            product += m * w;
            count++;
        }
        return count;
    }
}
