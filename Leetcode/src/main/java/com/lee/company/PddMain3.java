package com.lee.company;

import java.io.*;
import java.util.*;
// 2021-08-22 pdd 第2次笔试，第3题
public class PddMain3 {
  public static void main(String[] args) throws IOException {
    // Scanner sc = new Scanner(new File("/Users/sunyindong/workspace/ForWork/Leetcode/src/main/resources/input.txt"));
    Scanner sc = new Scanner(System.in);
    int t = sc.nextInt();
    sc.nextLine();
    while ((t--) > 0) {
      int n = sc.nextInt(), m = sc.nextInt();
      int minValue_pdd3 = findMinValue_pdd3(n, m);
      System.out.println(minValue_pdd3);
    }
  }
  public static int findMinValue_pdd3_2(int n, int m) {
    int val = (int)(Math.pow(10, n - 1));
    int remain = val / m;
    while (m * remain < val) {
      remain++;
    }
    int target = remain * m;
    return target;
  }
  public static int findMinValue_pdd3(int n, int m) {
    int mlen = getIntegerLen(m);
    int oldM = m;
    if (n < mlen) return -1;
    if (n == mlen) return m;

    while (n > getIntegerLen(m)) {
      m = m + oldM;
    }
    return m;
  }

  public static int getIntegerLen(int n) {
    return Integer.toString(n).length();
  }
}

//
// 2
//         3 10
//         4 730