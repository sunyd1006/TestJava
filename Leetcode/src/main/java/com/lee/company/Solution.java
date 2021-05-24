package com.lee.company;

class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x){val=x;}
    public static void dfs(TreeNode node){
        if(node==null) return;
        dfs(node.left);
        System.out.println(node.val);
        dfs(node.right);
    }
}

// hulu interview
class Solution {
    public static void main(String[] args) {
        int[] pre = {1,2,3};
        int[] in = {2,1,3};
        TreeNode root = buildTree(pre, in);
        TreeNode.dfs(root);
        System.out.println("hahaha");
    }
    
    public static TreeNode buildTree(int[] preorder, int[] inorder){
        if(preorder==null || inorder==null || preorder.length!=preorder.length || preorder.length==0) return null;
        int sz=preorder.length;
        return makeTree(preorder, 0, sz-1, inorder, 0, sz-1);
    }
    
    public static TreeNode makeTree(int[] preorder, int preleft, int preright, int[] inorder, int inleft, int inright){
        // 异常排掉
        if(preleft > preright || inleft>inright) return null;
        int val=preorder[preleft];
        int idx = find_val(inorder, val);
        TreeNode root = new TreeNode(val);
        root.left=makeTree(preorder, preleft+1, preleft+idx-inleft, inorder, inleft, idx-1);
        root.right=makeTree(preorder, preleft+idx-inleft+1, preright, inorder, idx+1, inright);
        return root;
    }
    
    
    public static int find_val(int[] arrays, int val){
        for(int i=0; i<arrays.length; i++){
            if(arrays[i]==val)return i;
        }
        return -1;
    }
    
    // 超时了吗
    //有可能
    // 那我在检查一下哈，目前没看出来啥问题，太感谢您了，我看代码去了
    //可以，我也看看有没有明显的错误
}
