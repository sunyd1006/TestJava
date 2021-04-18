package com.lee.leetcode;

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    @Override
    public String toString() {
        String res = val + " ";
        if (this.next != null) {
            res += this.next.toString();
        }
        return res;
    }
}

public class LeetcodeDemo {
    public static void main(String[] args) {
        LeetcodeDemo leetcodeDemo = new LeetcodeDemo();

        
    }

//    ListNode node1 = new ListNode(1);
//    ListNode node2 = new ListNode(1);
//    ListNode node3 = new ListNode(0);
//    ListNode node4 = new ListNode(0);
//    ListNode node5 = new ListNode(1);
//    
//    node1.next = node2;
//    node2.next = node3;
//    node3.next = node4;
//    node4.next = node5;
//    System.out.println(node1.toString());
//    System.out.println(leetcodeDemo.isPalindrome234(node1));

    /**
     * 234，回文链表
     * @param head
     * @return
     */
    public boolean isPalindrome234(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }

//        System.out.println("---------- start =========");
//        System.out.println(head);

        // 双指针找中点
        ListNode low = head, fast = head, rightPre= head;
        while (fast.next != null && fast.next.next != null) {
            low = low.next;
            fast = fast.next.next;
        }

        // 更改链表结构, low.next此时代表下一个指针
        ListNode newHead = reverseLinkedList(low.next);
        // 断开左右连接
        rightPre = low;
        low.next = null;

        // 遍历结构
        ListNode left = head, right = newHead;
        boolean res = true;
        while (left != null && right != null) {
            if (left.val != right.val) {
                res = false;
            }
            left = left.next;
            right = right.next;
        }

        // 恢复链表
        ListNode oldHead = reverseLinkedList(newHead);
        // 连接左右
        rightPre.next = oldHead;

//        System.out.println("---------- end =========");
//        System.out.println(head);
        return res;
    }

    public ListNode reverseLinkedList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverseLinkedList(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    /**
     *  Java 代码LRU
     */

}
