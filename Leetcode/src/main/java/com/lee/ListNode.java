package com.lee;

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
}