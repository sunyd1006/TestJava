package com;


public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    
    public TreeNode() {
    }
    
    public TreeNode(int val) {
        this.val = val;
    }
    
    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
    
    public static void printPreOrder(TreeNode node){
        System.out.print("PreOrder: ");
        preorder(node);
        System.out.println("");
    }
    
    public static void printInOrder(TreeNode node){
        System.out.print("InOrder: ");
        inorder(node);
        System.out.println("");
    }
    
    public static void preorder(TreeNode node){
        if(node==null) return;
        System.out.print(node.val+" ");
        preorder(node.left);
        preorder(node.right);
    }
    
    public static void inorder(TreeNode node){
        if(node==null) return;
        inorder(node.left);
        System.out.print(node.val+" ");
        inorder(node.right);
    }

}