package com.lee;

import com.sun.org.apache.bcel.internal.generic.LNEG;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // Scanner sc = new Scanner(new File("/Users/sunyindong/workspace/ForWork/Leetcode/src/main/resources/input.txt"));
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        String s = sc.next();
        char[] ops = s.toCharArray();
        LinkedList<Character> stack = new LinkedList<>();
        int op = 0, pre = 0;
        for (int i = 0; i < N; i++) {
            if (ops[i] == '(') {
                stack.add(op++, '(');
                pre = count(stack);
                System.out.print(pre + " ");
            } else if (ops[i] == ')') {
                stack.add(op++, ')');
                pre = count(stack);
                System.out.print(pre + " ");
            } else if (ops[i] == 'L') {
                op--;
                op = Math.max(0, op);
                System.out.print(pre + " ");
            } else if (ops[i] == 'R') {
                op++;
                op = Math.min(op, stack.size());
                System.out.print(pre + " ");
            } else {
                stack.remove(--op);
                pre = count(stack);
                System.out.print(pre + " ");
            }
        }
    }

    private static int count(LinkedList<Character> s) {
        int depth = 0, max = 0, cha = 0;
        Deque<Character> stack = new LinkedList<>();
        for (int i = 0; i < s.size(); i++) {
            if (s.get(i) == '(') {
                stack.push('(');
                depth++;
                max = Math.max(max, depth);
            } else {
                if (stack.isEmpty()) {
                    cha--;
                } else {
                    stack.poll();
                    depth--;
                }
            }
        }
        if (!stack.isEmpty() || cha < 0) return cha - stack.size();
        return max;
    }
}


// // 不带缓冲的
// Scanner sc = new Scanner(System.in);
// // 带缓冲的
// BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
// BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

// int N = sc.nextInt(), M = sc.nextInt();
// int [] problem = new int[N];
//     for (int i = 0; i < N; i++) {
//     problem[i] = sc.nextInt();
//     }
//
//     Arrays.sort(problem);
//     long[] dp = new long[N];
//     dp[0] = 1;
//     for (int i = 1; i < N; i++) {
//     long count = 1;
//     for (int j = 0; j < i; j++) {
//     if(problem[i] - problem[j] <=M){
//     count++;
//     }
//     }
//     dp[i] += count*dp[i-1];
//     dp[i] %= 1000000007;
//     }
//     System.out.println(dp[N-1]);


// public static int howMany(int x, int y, int r) {
//     int realx = x, realy = y;
//     int xZero = parse(0, y, realx, realy, r), yZero = parse(x, 0, realx, realy, r);
//     int isZero = parse(0, 0, realx, realy, r);
//
//     if (x == 0 || y == 0) {
//         if (x == 0) {
//             if (yZero >= 0) return 2;
//             else return 4;
//         }
//         if (y == 0) {
//             if (xZero >= 0) return 2;
//             else return 4;
//         }
//     }
//
//     if (xZero > 0 && yZero > 0) return 0;
//     if (xZero == 0 && yZero > 0 || xZero > 0 && yZero == 0) return 1;
//     if (xZero == 0 && yZero == 0 || xZero < 0 && yZero > 0 || xZero > 0 && yZero < 0) return 2;
//     if (xZero < 0 && yZero == 0 || xZero == 0 && yZero < 0) return 3;
//     if (xZero < 0 && yZero < 0) {
//         if (isZero == 0) return 3;
//         return 4;
//     }
//     return 100;
// }
//
// public static int parse(int x, int y, int realx, int realy, int r) {
//     int val = x * x + y * y - 2 * (realx * x + realy * y) + realy * realy + realx * realx - r * r;
//     return val;
// }

// 2
// 1 0 1
// 1 0 2