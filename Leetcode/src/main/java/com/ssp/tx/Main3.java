package com.ssp.tx;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main3 {
// public class Main {
  public static void main(String[] args) throws FileNotFoundException {
    Scanner sc = new Scanner(new File("/Users/sunyindong/workspace/ForWork/Leetcode/src/main/resources/input3.txt"));
    // Scanner sc = new Scanner(System.in);
    // while (sc.hasNext()) {
      int n = sc.nextInt();
      int m = sc.nextInt();
      board = new boolean[n][m];
      visited = new boolean[n][m];
      sc.nextLine();
      for (int i = 0; i < n; i++) {
        String line = sc.nextLine();
        for (int j = 0; j < m; j++) {
          if (line.charAt(j) == 'r') {
            board[i][j] = true; // red = 1
          } else {
            board[i][j] = false; //
          }
        }
      }

      int x = sc.nextInt()-1;
      int y = sc.nextInt()-1;
      boolean isRed = board[x][y] ? true : false;
      res = 1;
      visited[x][y] = true;
      howManyStep(x, y, isRed);
      System.out.println(res);
    // }
  }

  static boolean[][] board;
  static boolean[][] visited;
  static int[][] direction = new int[][]{
          {1, -2}, {2, -1}, {2, 1}, {1, 2},
          {-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}
  };
  static int res = 1;

  public static void howManyStep(int x, int y, boolean isRed) {

    for (int i = 0; i < direction.length; i++) {
      int nextX = x + direction[i][0];
      int nextY = y + direction[i][1];
      if (inBoardAndTarget(nextX, nextY)) {
        boolean nextIsRed = board[nextX][nextX] ? true : false;
        boolean isOk = (isRed && !nextIsRed || !isRed && nextIsRed)?true:false;
        if (visited[nextX][nextY]) continue;  //

        if (!isOk) continue;
        visited[nextX][nextY] = true;
        res++;
        // System.out.println("x " + (x + 1) + " , " + (y + 1));
        howManyStep(nextX, nextY, nextIsRed);
        // visited[x][y] = false;
      }
    }
  }

  public static boolean inBoardAndTarget(int x, int y) {
    if (x < 0 || y < 0 || x >= board.length || y >= board[0].length) {
      return false;
    }
    if (visited[x][y]) return false;
    return true;
  }

}

