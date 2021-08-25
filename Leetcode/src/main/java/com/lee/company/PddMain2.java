package com.lee.company;

import java.io.*;
import java.util.*;
// 2021-08-22 pdd 第2次笔试，第2题
public class PddMain2 {
  public static Map<Integer, Integer> minStep;
  public static int[] nextStep;
  public static int res;
  public static void main(String[] args) throws IOException {
    // Scanner sc = new Scanner(new File("/Users/sunyindong/workspace/ForWork/Leetcode/src/main/resources/input.txt"));
    Scanner sc = new Scanner(System.in);
    minStep = new HashMap<>();
    nextStep = new int[]{-2, -1, +1, +100};
    res = Integer.MAX_VALUE;

    int t = sc.nextInt();
    while ((t--) > 0) {
      minStep.clear();
      res = Integer.MAX_VALUE;
      int p = sc.nextInt();
      int q = sc.nextInt();
      int maxValueStep = Math.abs(p - q);
      dfs(p, q, 0, maxValueStep);
      System.out.println(res);
    }
  }

  public static void dfs(int p, int q, int step, int maxValueStep) {
    if(maxValueStep < step) return;
    if(p==q){
      Integer orDefault = minStep.getOrDefault(p, Integer.MAX_VALUE);
      if(orDefault < step) return;
      minStep.put(p, step);  // 记录缓存
      res = step < res? step: res;
    }
    for (int i = 0; i < nextStep.length; i++) {
      int nextp = i == 3 ? p * 2 : p + nextStep[i];
      dfs(nextp, q, step+1, maxValueStep);
    }
  }
}



// 2
// 3 4
// 10 1