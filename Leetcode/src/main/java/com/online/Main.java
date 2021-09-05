package com.online;

import java.io.IOException;
import java.util.*;

public class Main {
  public static void main(String[] args) throws IOException {
    // Scanner sc = new Scanner(new File("/Users/sunyindong/workspace/ForWork/Leetcode/src/main/resources/input.txt"));
    Scanner sc = new Scanner(System.in);
    int N = sc.nextInt();
    List<Integer> arr = new ArrayList<>();
    for (int i = 1; i <= N; i++) {
      arr.add(i);
    }
    dfs(arr, 0);
  }

  public static void dfs(List<Integer> arr, int index) {
    if (arr.size() == 1) {
      System.out.println(arr.get(0));
      return;
    } else {
      int i = (index + 2) % arr.size();
      arr.remove(i);
      dfs(arr, i);
    }
  }
}

