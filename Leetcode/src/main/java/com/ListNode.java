package com;

public class ListNode {
    public int val;
    public ListNode next;
    
    public ListNode() {
    }
    
    public ListNode(int val) {
        this.val = val;
    }
    
    public ListNode(int val, ListNode next) {
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
    
    public static void printListNode(ListNode head) {
        if (head == null) return;
        ListNode p = head;
        while (p != null) {
            System.out.print(p.val + " ");
            p = p.next;
        }
        System.out.println();
    }
    
    public static ListNode buildListNode(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        ListNode fakeHead = new ListNode(-1, null);
        ;
        ListNode p = fakeHead;
        for (int num : nums) {
            p.next = new ListNode(num);
            p = p.next;
        }
        return fakeHead.next;
    }
}