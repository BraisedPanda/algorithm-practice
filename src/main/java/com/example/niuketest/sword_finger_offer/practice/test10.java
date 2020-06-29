package com.example.niuketest.sword_finger_offer.practice;

import org.junit.Test;

import java.util.*;


/**
 * @program: niuke-test
 * @description:
 * @author:
 * @create: 2020-04-13 13:41
 **/
public class test10 {

    /*
    输入两个整数序列，第一个序列表示栈的压入顺序，
    请判断第二个序列是否可能为该栈的弹出顺序。
    假设压入栈的所有数字均不相等。例如序列1,2,3,4,5是某栈的压入顺序，
    序列4,5,3,2,1是该压栈序列对应的一个弹出序列，
    但4,3,5,1,2就不可能是该压栈序列的弹出序列。4,3,5,2,1
    （注意：这两个序列的长度是相等的）
    */
    public boolean IsPopOrder(int [] pushA,int [] popA) {
        Stack<Integer> stack = new Stack();
        int index = 0;
        for(int i=0;i<pushA.length;i++){
            stack.push(pushA[i]);
            while(stack.peek()==popA[index]){
                stack.pop();
                index++;
                if(stack.isEmpty()){
                    break;
                }
            }
        }
       return stack.isEmpty();

    }
    @Test
    public void Solution(){
        int [] pushA = {1};
        int [] popA = {1};
        System.out.println(IsPopOrder(pushA,popA));
    }
    
    
    /* 
    * 从上往下打印出二叉树的每个节点，同层节点从左至右打印
    * ps:二叉树层次遍历
    */
    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }


    }
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        ArrayList<TreeNode> nodeList = new ArrayList<>();
        if(root == null){
            return list;
        }
        nodeList.add(root);
        while(nodeList.size()>0){
            TreeNode node = nodeList.remove(0);

            if(node.left!=null){
                nodeList.add(node.left);

            }
            if(node.right!=null){
                nodeList.add(node.right);

            }
            list.add(node.val);

        }
        return list;
    }
    @Test
    public void test(){
        int i = 0;
        i= i++ + i;
        System.out.println(i);
    }


    /**
    * @Description: 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。
     * 如果是则输出Yes,否则输出No。假设输入的数组的任意两个数字都互不相同。
    * @Param:后序:左右根，左边的小于根节点，右边大于根节点
     * 递归方法，去除最后一个根节点，则前面的节点可以分为两半，前半部分节点小于后半部分节点
    * @Author:
    * @Date: 2020/4/23 0023
    */
    public boolean VerifySquenceOfBST(int [] sequence) {
        if(sequence.length == 0){
            return false;
        }else{
            return verifyBST(sequence,0,sequence.length-1);
        }
    }
    public boolean verifyBST(int[] sequence,int start,int end){
        if(start >= end){
            return true;
        }
        int i = start;
        for(;i<end;i++){//找出中间的分界点,循环停止时，i位于右分界点第一个
            if(sequence[i]>sequence[end]){
                break;
            }
        }
        //验证右边的是否都比根节点大
        for(int j=i;j<end;j++){
            if(sequence[end]>sequence[j]){ //如果出现右边节点比根节点小，返回false
                return false;
            }
        }
        return verifyBST(sequence,start,i-1) && verifyBST(sequence,i,end -1);
    }

    @Test
    public void testVerifySquenceOfBST(){
        int[] sequence = {4,8,6,12,16,13,9,10};
        System.out.println(VerifySquenceOfBST(sequence));
    }


    /** 
    * @Description: 输入一颗二叉树的根节点和一个整数，打印出二叉树中结点值的和为输入整数的所有路径。
     * 路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。
    */
    ArrayList<ArrayList<Integer>> allPathList = new ArrayList<>();
    ArrayList<Integer> pathList = new ArrayList<>();
    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root,int target) {
        if(root == null){
            return allPathList;
        }
        pathList.add(root.val);
        target = target - root.val;
        //如果到叶子节点时，正好和等于target时
        if(target == 0 && root.left==null && root.right==null){
            allPathList.add(new ArrayList<>(pathList));

        }

        FindPath(root.left,target);
        FindPath(root.right,target);
        pathList.remove(pathList.size()-1);
        return allPathList;
    }


    @Test
    public void testFindPath() {
        TreeNode root = new TreeNode(10);
        TreeNode node1 = new TreeNode(12);
        TreeNode node2 = new TreeNode(5);
        TreeNode node3 = new TreeNode(4);
        TreeNode node4 = new TreeNode(7);
        root.left = node1;
        root.right = node2;
        node2.right = node3;
        node3.left = node4;
        System.out.println(FindPath(root, 22));
    }
    
    /** 
    * @Description:打印树的根节点到所有子节点的路径
    */
    public ArrayList<ArrayList<Integer>> printAllPath(TreeNode root){
        ArrayList<ArrayList<Integer>> allPathList = new ArrayList<>();
        ArrayList<Integer> pathList = new ArrayList<>();
        if(root == null){
            return allPathList;
        }
        return allTreeNodePath(root,allPathList,pathList);
    }

    private ArrayList<ArrayList<Integer>> allTreeNodePath(TreeNode root, ArrayList<ArrayList<Integer>> allPathList, ArrayList<Integer> pathList) {
        if(root == null){
            return allPathList;
        }
        pathList.add(root.val);
         if(root.left == null && root.right == null){
            allPathList.add(new ArrayList<>(pathList));
         }
        allTreeNodePath(root.left,allPathList,pathList);
        allTreeNodePath(root.right,allPathList,pathList);
        pathList.remove(pathList.size()-1);
        return allPathList;
    }

    @Test
    public void testPrintAllPath() {
        TreeNode root = new TreeNode(10);
        TreeNode node1 = new TreeNode(12);
        TreeNode node2 = new TreeNode(5);
        TreeNode node3 = new TreeNode(4);
        TreeNode node4 = new TreeNode(7);
        root.left = node1;
        root.right = node2;
        node2.right = node3;
        node3.left = node4;
        System.out.println(printAllPath(root));
    }
    
    
    /** 
    * @Description:输入一个复杂链表（每个节点中有节点值，以及两个指针，
     * 一个指向下一个节点，另一个特殊指针random指向一个随机节点），
     * 请对此链表进行深拷贝，并返回拷贝后的头结点。（
     * 注意，输出结果中请不要返回参数中的节点引用，否则判题程序会直接返回空）
    */
    public class RandomListNode {
        int label;
        RandomListNode next = null;
        RandomListNode random = null;

        RandomListNode(int label) {
            this.label = label;
        }
    }
    public RandomListNode Clone(RandomListNode pHead) {
        if(pHead == null){
            return null;
        }
        RandomListNode currentNode = pHead;
        while(currentNode!=null){
            RandomListNode cloneNode = new RandomListNode(currentNode.label);
            RandomListNode nextNode = currentNode.next;
            cloneNode.next = nextNode;
            currentNode.next  = cloneNode;
            currentNode = nextNode;
        }

        RandomListNode nowNode = pHead;
        while(nowNode!=null){
            nowNode.next.random = nowNode.random == null?null:nowNode.random.next;
            nowNode = nowNode.next.next;

        }
        RandomListNode finalNode = pHead;
        RandomListNode resultNoded = pHead.next;
        RandomListNode finalNextNode = resultNoded;
        while(finalNode!=null){
            finalNode.next = finalNextNode.next;
            finalNode = finalNextNode.next;
            if(finalNode!=null){
                finalNextNode.next = finalNode.next;
                finalNextNode = finalNode.next;
            }
        }
        return resultNoded;
    }


    @Test
    public void testClone(){
        RandomListNode root = new RandomListNode(0);
        RandomListNode node1 = new RandomListNode(1);
        RandomListNode node2 = new RandomListNode(2);
        RandomListNode node3 = new RandomListNode(3);
        root.next = node1;
        node1.next = node2;
        node2.next = node3;
        root.random = node3;
        node1.random = node2;
        RandomListNode result = Clone(root);
    }
    
    /** 
    * @Description:输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。
     * 要求不能创建任何新的结点，只能调整树中结点指针的指向。
     * left --->  right<---
    */
    public TreeNode Convert(TreeNode pRootOfTree) {
        if(pRootOfTree == null){
            return null;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode p = pRootOfTree;
        TreeNode pre = null;
        Boolean flag = true;
        while(!stack.isEmpty() || p!=null){
            while(p!=null){
                stack.push(p);
                p = p.left;
            }
            if(!stack.isEmpty()){
                p = stack.pop();
                if(flag == true){ //找到左下角的节点赋予值
                    pRootOfTree = p;
                    pre = pRootOfTree;
                    flag = false;
                }else{
                    pre.right = p;
                    p.left = pre;
                    pre = p;
                }
                p = p.right;
            }


        }
        return pRootOfTree;
    }

    /**
    * @Description:先序遍历二叉树(根左右)
    */
    //递归
    public ArrayList<Integer> preOrderRe(TreeNode root,ArrayList<Integer> list){
        if(root == null){
            return list;
        }
        list.add(root.val);
        if(root.left!=null){
            preOrderRe(root.left,list);
        }

        if(root.right!=null){
            preOrderRe(root.right,list);
        }
        return list;
    }
    //非递归
    public ArrayList<Integer> preOrder(TreeNode root,ArrayList<Integer> list){
        if(root == null){
            return null;
        }
        Stack<TreeNode> stack = new Stack<>();
        while(root!=null || !stack.isEmpty()){
            while(root!=null){
                list.add(root.val);
                stack.push(root);
                root = root.left;
            }
            if(!stack.isEmpty()){
                root = stack.pop();
                root = root.right;
            }
        }
        return list;
    }

    /**
     * @Description:中序遍历二叉树(左根右)
     */
    //递归
    public ArrayList<Integer> midOrderRe(TreeNode root,ArrayList<Integer> list){
        if(root == null){
            return list;
        }
        if(root.left!=null){
            midOrderRe(root.left,list);
        }
        list.add(root.val);
        if(root.right!=null){
            midOrderRe(root.right,list);
        }



        return list;
    }
    //非递归
    public ArrayList<Integer> midOrder(TreeNode root,ArrayList<Integer> list){
        if(root == null){
            return list;
        }
        Stack<TreeNode> stack = new Stack<>();
        while(!stack.isEmpty() || root!=null){
            while(root!=null){
                stack.push(root);
                root = root.left;
            }
            if(!stack.isEmpty()){
                root = stack.pop();
                list.add(root.val);
                root = root.right;
            }
        }
        return list;
    }

    /**
     * @Description:后序遍历二叉树(左右根)
     */
    //递归
    public ArrayList<Integer> postOrderRe(TreeNode root,ArrayList<Integer> list){
        if(root == null){
            return list;
        }
        if(root.left!=null){
            postOrderRe(root.left,list);
        }

        if(root.right!=null){
            postOrderRe(root.right,list);
        }
        list.add(root.val);


        return list;
    }
    //非递归  后序遍历非递归方法需要一个中间栈来实现
    public ArrayList<Integer> postOrder(TreeNode root,ArrayList<Integer> list){
        if(root == null){
            return list;
        }
        Stack<TreeNode> stack = new Stack<>();
        Stack<TreeNode> midStack = new Stack<>();
        while(root != null || !stack.isEmpty()){
            if(root!=null){
                stack.push(root);
                midStack.push(root);
                root = root.right;
            }else{
                root = stack.pop();
                root = root.left;
            }
        }
        while(!midStack.isEmpty()){
            list.add(midStack.pop().val);
        }
        return list;
    }
    /**
     * @Description:层次遍历二叉树
     */
    public ArrayList<Integer> levelOrder(TreeNode root,ArrayList<Integer> list){

        ArrayList<TreeNode> nodeList =  new ArrayList<>();
        if(root == null){
            return list;
        }
        nodeList.add(root);
        while(nodeList.size()>0){
            //把最先的弹出
            TreeNode node = nodeList.remove(0);
            System.out.println();
            if(node.left!=null){
                nodeList.add(node.left);
            }
            if(node.right!=null){
                nodeList.add(node.right);
            }
            list.add(node.val);
        }
        return list;
    }

    @Test
    public void testpreOrderRe(){
        TreeNode root = new TreeNode(0);
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;
        node2.left = node5;
        node2.right = node6;
        node5.left = node7;
        ArrayList<Integer> list = new ArrayList<>();
        System.out.println(levelOrder(root,list));
    }

    @Test
    public void testBoolean(){
        int nums[] = {1,2,3,4,5};
        int[] arr = nums.clone();
        
    }

}

