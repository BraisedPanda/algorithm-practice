package com.example.niuketest.sword_finger_offer.review;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

/**
 * @program: 剑指offer题目11~20
 * @description: 温故而知新，可以为师矣
 * @create: 2020-07-03 10:04
 **/
public class review02 {

    /** [11]
     * @Description: 二进制数中1的个数
     * @Detail: 输入一个整数，输出该数32位二进制表示中1的个数。其中负数用补码表示。
     * @Date: 2020/7/3 0003
     */
    public int NumberOf1(int n) {
        int count = 0;
        while(n!=0){
            n = n & (n-1);
            count++;
        }
        return count;
    }

    @Test
    public void testAnd(){

        int b = 5;
        System.out.println(Integer.toBinaryString(5).toCharArray());
    }

    /** [12]
    * @Description: 数值的整数次方
    * @Detail: 给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方。
     * 保证base和exponent不同时为0
    * @Date: 2020/7/3 0003
    */
    public double Power(double base, int exponent) {
        if(base == 0){
            return 0d;
        }
        if(exponent == 0){
            return 1d;
        }
        double result = 1;
        for (int i = 0; i < Math.abs(exponent); i++) {
            result = (exponent>0?base:1/base) * result;
        }
        return result;
    }
    @Test
    public void  testPower(){
        double base = 2.0;
        int exponent = -3;
        System.out.println(Power(base,exponent));
    }


    /** [13]
    * @Description: 调整数组顺序，使奇数位于偶数前面
    * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有的奇数位于数组的前半部分，
     * 所有的偶数位于数组的后半部分，并保证奇数和奇数，偶数和偶数之间的相对位置不变。
    * @Author: braisedPanda
    * @Date: 2020/7/6 0006
    */
    public void reOrderArray(int [] array) {
        if(array.length == 0){
            return;
        }
        for (int i = 0; i < array.length-1; i++) {
            for (int j = 0; j < array.length-1; j++) {
                if(array[j]%2==0 && array[j+1]%2!=0){ // 如果是偶、奇 交换前后两个数
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }
        }
    }

    @Test
    public void testReOrderArray(){
        int[] array = {2,4,6,1,3,5,7,8};
        reOrderArray(array);
        System.out.println();
    }


    public class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;

        }
    }
    /** [14]
    * @Description: 链表中倒数第K个结点
    * 输入一个链表，输出该链表中倒数第k个结点。
    * @Author: braisedPanda
    * @Date: 2020/7/6 0006
    */

    public ListNode FindKthToTail(ListNode head,int k) {
        if(head == null){
            return null;
        }
        int i = 0;
        int length = 0;
        ListNode node = head;
        while(node!=null){
            node = node.next;
            length++;
        }
        if(k>length){
            return null;
        }
        node = head;
        while(i<k){
            node = node.next;
            i++;
        }

        while(node!=null){
            head = head.next;
            node = node.next;
        }
        return head;

    }

    public ListNode FindKthToTail2(ListNode head,int k) {
        ListNode p = head;
        ListNode pre = head;
        int i =0;
        for(; pre!=null;i++){
            if(i>=k){
                p = p.next; // pre跑了k个结点，p再开始跑
            }
            pre = pre.next; // pre先跑
        }
        return i<k?null:p;
    }



    @Test
    public void testFindKthToTail(){
        ListNode root = new ListNode(1);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(3);
        ListNode node3 = new ListNode(4);
        ListNode node4 = new ListNode(5);

        root.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        int k = 1;
        FindKthToTail(root,k);

    }


    /** [15]
    * @Description: 反转链表
    * 输入一个链表，反转链表后，输出新链表的表头。
    * @Date: 2020/7/6 0006
    */
    public ListNode ReverseList(ListNode head) {
        ListNode root = new ListNode(0);
        ListNode nextRoot = root;
        while(head!=null){
            ListNode node = new ListNode(head.val);
            node.next = nextRoot.next;
            nextRoot.next = node;
            head = head.next;

        }
        return root.next;
    }


    @Test
    public void testReverseList(){
        ListNode root = new ListNode(1);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(3);
        ListNode node3 = new ListNode(4);
        ListNode node4 = new ListNode(5);

        root.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        ReverseList(root);

    }


    /** [16]
    * @Description: 合并两个排序的列表
    * @Detail:输入两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不减规则。
    * @Date: 2020/7/9 0009
    */
    public ListNode Merge(ListNode list1,ListNode list2) {
       ListNode root = new ListNode(0);
       ListNode node = root;
       while(list1!=null && list2!=null){
           ListNode node1;
            if(list1.val<=list2.val){
                node1 = new ListNode(list1.val);
                list1 = list1.next;
            }else{
                node1 =new ListNode(list2.val);
                list2 = list2.next;
            }
            node.next = node1;
            node = node.next;


       }
       if(list1 == null){
           node.next = list2;
       }
       if(list2 == null){
           node.next = list1;
       }
       return root.next;
    }
    @Test
    public void testMerge(){
        ListNode fList1 = new ListNode(1);
        ListNode fList2 = new ListNode(2);
        ListNode fList3 = new ListNode(3);
        fList1.next = fList2;
        fList2.next = fList3;

        ListNode lList1 = new ListNode(2);
        ListNode lList2 = new ListNode(3);
        ListNode lList3 = new ListNode(4);
        lList1.next = lList2;
        lList2.next = lList3;

        ListNode node = Merge(fList1,lList1);
        System.out.println();
    }


    /** [17]
    * @Description: 树的子结构
    * @Detail: 输入两棵二叉树A，B，判断B是不是A的子结构。（ps：我们约定空树不是任意一个树的子结构）
    * @Date: 2020/7/9 0009
    */
    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }

    }
    public boolean HasSubtree(TreeNode root1,TreeNode root2) {
        // 只要其中一个匹配上了，就说明是root2是root1的子树
        boolean flag = false;
        if(root1 ==null || root2==null){
           return false;
       }
       if(!flag){
           flag = isSubTree(root1,root1);
       }
       if(!flag){ // 匹配左节点
            flag = HasSubtree(root1.left,root2);
       }
        if(!flag){ // 匹配右节点
            flag = HasSubtree(root1.right,root2);
        }
        return flag;
    }

    public boolean isSubTree(TreeNode root, TreeNode node){ // 判断一个节点是否是树的子节点
        if(node ==null){
            return true;
        }
        if(root ==null){
            return false;
        }
        if(root.val == node.val){
             return isSubTree(root.left,node.left) && isSubTree(root.right,node.right);
        }
        return false;
    }

    /** [18]
    * @Description: 二叉树的镜像
    * @Detail: 操作给定的二叉树，将其变换为源二叉树的镜像。
    * @Date: 2020/7/9 0009 
    */
    public void Mirror(TreeNode root) {
        if(root == null){
            return;
        }
        if(root.left!=null || root.right!=null){
            TreeNode temp = root.left;
            root.left = root.right;
            root.right = temp;
        }
        if(root.left!=null){
            Mirror(root.left);
        }
        if(root.right!=null){
            Mirror(root.right);
        }
    }


    /** [19]
    * @Description: 顺时针打印矩阵
    * @Detail: 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字，
     * 例如，如果输入如下4 X 4矩阵： 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 
     * 则依次打印出数字1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10.
    * @Author: braisedPanda
    * @Date: 2020/7/9 0009
    */
    public ArrayList<Integer> printMatrix(int [][] matrix) {
        ArrayList<Integer> list = new ArrayList<>();
        int maxWidth = matrix[0].length;
        int maxHeight = matrix.length;
        int width = 0;
        int height = 0;
        int count = 0;
        while(count<maxWidth*maxHeight){
            if(count<maxWidth*maxHeight){  // 从左上到右上
                for(int i=width;i<maxWidth-width;i++){
                    list.add(matrix[height][i]);
                    count++;
                }
                width++;
            }
            if(count<maxWidth*maxHeight){  // 从右上到右下
                for(int i=height+1;i<maxHeight-height;i++){
                    list.add(matrix[i][maxWidth-width]);
                    count++;
                }
                height++;
            }
            if(count<maxWidth*maxHeight){  // 从右下到左下
                for(int i=maxWidth-width-1;i>=width-1;i--){
                    list.add(matrix[maxHeight-height][i]);
                    count++;
                }

            }
            if(count<maxWidth*maxHeight){  // 从左下到左上
                for(int i=maxHeight-height-1;i>=height;i--){
                    list.add(matrix[i][width-1]);
                    count++;
                }
            }
        }
        return list;
    }

    @Test
    public void testPrintMatrix(){
        int [][] matrix = new int[4][4];
        matrix[0][0] = 1;
        matrix[0][1] = 2;
        matrix[0][2] = 3;
        matrix[0][3] = 4;

        matrix[1][0] = 5;
        matrix[1][1] = 6;
        matrix[1][2] = 7;
        matrix[1][3] = 8;

        matrix[2][0] = 9;
        matrix[2][1] = 10;
        matrix[2][2] = 11;
        matrix[2][3] = 12;

        matrix[3][0] = 13;
        matrix[3][1] = 14;
        matrix[3][2] = 15;
        matrix[3][3] = 16;

        System.out.println(printMatrix(matrix));

    }
    
    
    
    /** [20]
    * @Description: 包含min函数的栈
    * @Param: 定义栈的数据结构，请在该类型中实现一个能够得到栈中所含最小元素的min函数（时间复杂度应为O（1））。
     * 注意：保证测试中不会当栈为空的时候，对栈调用pop()或者min()或者top()方法。
    * @Date: 2020/7/9 0009 
    */
    Stack<Integer> stack = new Stack<>();
    public void push(int node) {
        stack.push(node);
    }

    public void pop() {
        stack.pop();
    }

    public int top() {
       return stack.get(0);
    }

    public int min() {
        int min = top();
        for (int j = 0; j < stack.size(); j++) {
                if(min>stack.get(j)){
                    min = stack.get(j);
                }
        }
        return min;
    }

    @Test
    public void testStack(){
        push(3);
        System.out.println(min());
    }


}
