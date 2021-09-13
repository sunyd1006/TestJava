package com.ssp;


import java.io.*;
import java.util.*;

public class Main {
//   public static void main(String[] args) throws FileNotFoundException {
//     // Scanner sc = new Scanner(
//     //         new File("/Users/sunyindong/workspace/ForWork/Leetcode/src/main/resources/input.txt"));
//     Scanner sc = new Scanner(System.in);
//     Set<Integer> startSet = new HashSet<>();
//     int n = sc.nextInt();
//     int q = sc.nextInt();
//     sc.nextLine();
//     int oldN = n, oldQ = q;
//     Map<Integer, Set<Integer>> map = new HashMap<>();
//     int[][] board = new int[n+1][n+1];
//
//     int curLin = 1;
//     while (n-- > 0) {
//       String line = sc.nextLine();
//       String[] s = line.split(" ");
//       if (s.length > 1) {
//         for (int i = 1; i < s.length; i++) {
//           int curJ = Integer.parseInt(s[i]);
//           board[curLin][curJ] = 1;  // 正向需要
//           board[curJ][curLin] = 2;  // 反向需要
//         }
//       }
//       curLin++;
//     }
//
//     while (q-- > 0) {
//       String query = sc.nextLine();
//       String[] qArray = query.split(" ");
//
//       boolean isStart = qArray[0].equals("0") ? false : true;
//       Integer needStart = Integer.parseInt(qArray[1]);
//
//       if (isStart) {
//         if (startSet.contains(needStart)) {
//           continue;
//         } else {
//           startSet.add(needStart);
//         }
//
//         Deque<Integer> deque = new LinkedList<>();
//         for (int i = 1; i <=oldN ; i++) {
//          if(board[needStart][i]==1){
//            deque.add(i);
//          }
//         }
//
//         while (!deque.isEmpty()) {
//           Integer need = deque.pollFirst();
//           if (startSet.contains(need)) {
//             continue;
//           }
//           startSet.add(need);
//           for (int i = 0; i < oldN; i++) {
//             if(board[)
//           }
//           List<Integer> orDefault = map.getOrDefault(need, null);
//           if (orDefault != null) {
//             deque.addAll(orDefault);
//           }
//         }
//
//         // List<Integer> integers = map.getOrDefault(needStart, new LinkedList<>());
//         // Deque<Integer> deque = new LinkedList<>(integers);
//         //
//         // while (!deque.isEmpty()) {
//         //   Integer need = deque.pollFirst();
//         //   if (startSet.contains(need)) {
//         //     continue;
//         //   }
//         //   startSet.add(need);
//         //   List<Integer> orDefault = map.getOrDefault(need, null);
//         //   if (orDefault != null) {
//         //     deque.addAll(orDefault);
//         //   }
//         // }
//
//
//       } else {
//         if (startSet.contains(needStart)) {
//           startSet.remove(needStart);
//         } else {
//           continue;
//         }
//
//         // List<Integer> integers = map.getOrDefault(needStart, new LinkedList<>());
//         Deque<Integer> deque = new LinkedList<>();
//
//         // 关闭3
//         for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
//           List<Integer> value = entry.getValue();
//           for (Integer integer : value) {
//             if(integer.equals(needStart)){
//               deque.addLast(entry.getKey());
//               break;
//             }
//           }
//         }
//         while (!deque.isEmpty()) {
//           Integer need = deque.pollFirst();
//           if (!startSet.contains(need)) {
//             continue;
//           }
//           startSet.remove(need);
//           List<Integer> orDefault = map.getOrDefault(need, null);
//           if (orDefault != null) {
//             deque.addAll(orDefault);
//           }
//         }
//       }
//       System.out.println(startSet.size());
//     }
//   }
}
