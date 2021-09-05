package com.ssp.tx;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

// public class Main {
public class Main2 {
  public static void main(String[] args) throws FileNotFoundException {
    Scanner sc = new Scanner(new File("/Users/sunyindong/workspace/ForWork/Leetcode/src/main/resources/input2.txt"));
    // Scanner sc = new Scanner(System.in);
    while (sc.hasNext()){
      int k = sc.nextInt();
      int b = sc.nextInt();
      double y = findY(k, b);
      // System.out.println(k + "   " + b);
      // System.out.println(" y: " + y);
      double x = b - y;
      double res = (Math.pow(x, k+1) -1 ) /(k+1)  + 0.5 * (b*b + x*x) - b * x;
      System.out.println(res);
    }
  }

  public static double findY(int k, int b) {
    double left = 0, right = b;
    while (left < right) {
      double preY = (right - left) / 2 + left;
      double res = Math.pow(b - preY, k);
      double target = Math.abs(res - preY);
      if (target < 0.000000001) {   // del 0
        return preY;
      } else if (res > 0) {
        right = preY;
        // left = preY;
      } else {
        left = preY;
        // right = preY;
      }
    }
    return b;
  }
}

