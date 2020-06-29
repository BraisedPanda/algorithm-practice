package com.example.niuketest.sword_finger_offer.practice;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @program: niuke-test
 * @description:
 * @author:
 * @create: 2020-05-11 11:01
 **/
public class test14 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.next();
        StringBuilder sb = new StringBuilder(str);
        int count = scanner.nextInt();
        for(int i=0;i<count;i++){
            int operation = scanner.nextInt();
            if(operation == 1){//替换操作
                int index = scanner.nextInt();
                String s = scanner.next();
                sb.replace(index-1,index,s);
            }
            if(operation == 2){//查询操作
                int start = scanner.nextInt()-1;
                int end = scanner.nextInt();
                find(sb,start,end);
            }
        }
    }

    private static void find(StringBuilder sb, int start, int end) {
        List list = new ArrayList();
        String str = sb.toString();
        str = str.substring(start,end);
        for (char s:
             str.toCharArray()) {
             if(!list.contains(s)){
                 list.add(s);
             }
        }
        System.out.println(list.size());
    }
    
    /** 
    * @Description: 输入两个链表，找出它们的第一个公共结点。
     * （注意因为传入数据是链表，所以错误测试数据的提示是用其他方式显示的，保证传入数据是正确的）
    * @Date: 2020/5/19 0019 
    */

    public class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        if(pHead1 == null || pHead2 == null){
            return null;
        }
        ListNode head1 = pHead1;
        ListNode selectNode = null;
        ListNode head2;
        Boolean flag = false;
        while(head1!=null){
            selectNode = head1;
            head2 = pHead2;
            while(head2!=null){
                if(head2.val == selectNode.val){
                    flag = true;
                    return selectNode;
                }
                head2 = head2.next;
            }
            head1 = head1.next;
        }
        if(flag){
            return selectNode;
        }else{
            return null;
        }

    }

    @Test
    public void testFindFirstCommonNode(){
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);

        ListNode nodea = new ListNode(5);
        ListNode nodeb = new ListNode(6);
        ListNode nodec = new ListNode(7);

        node1.next = node2;
        node2.next = node3;

        nodea.next = nodeb;
        nodeb.next = nodec;

        ListNode selectNode =   FindFirstCommonNode(node1,nodea);
        System.out.println();
    }
    
    /** 
    * @Description: 统计一个数字在排序数组中出现的次数。
    * @Date: 2020/5/19 0019 
    */
    public int GetNumberOfK(int [] array , int k) {
        int count = 0;
        Boolean flag = true;
        for(int i=0;i<array.length;i++){
            if(k == array[i]){
                flag = false;
                count ++;
            }else if(flag == false && count>0){
                break;
            }
        }
        return count;
    }

    @Test
    public void testGetNumberOfK(){
        int[] array = {1,2,3,3,3,3,4,5};
        int a = GetNumberOfK(array,3);
        System.out.println(a);
    }

    /**
    * @Description:输入一棵二叉树，求该树的深度。
     * 从根结点到叶结点依次经过的结点（含根、叶结点）形成树的一条路径，最长路径的长度为树的深度。
    * @Date: 2020/5/19 0019
    */
    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }

    }
    //解法1
    public int TreeDepth(TreeNode root) {
        ArrayList<ArrayList<Integer>> allPathList = new ArrayList<>();
        ArrayList<Integer> pathList = new ArrayList<>();
        ArrayList<ArrayList<Integer>>  resultList =  getAllPathList(root,allPathList,pathList);
        int max = 0;
        for (ArrayList<Integer> list:
             resultList) {
            if(max<list.size()){
                max = list.size();
            }
        }
        return max;
    }
    //解法2
    public int TreeDepth2(TreeNode root) {
        if(root == null){
            return 0;
        }
        int left = TreeDepth2(root.left);
        int right = TreeDepth2(root.right);
        return Math.max(left,right)+1;
    }


    private ArrayList<ArrayList<Integer>> getAllPathList(TreeNode root, ArrayList<ArrayList<Integer>> allPathList, ArrayList<Integer> pathList) {
        if(root == null){
            return allPathList;
        }
        pathList.add(root.val);
        if(root.left == null && root.right == null){
            allPathList.add(new ArrayList<>(pathList));
        }
        getAllPathList(root.left,allPathList,pathList);
        getAllPathList(root.right,allPathList,pathList);
        pathList.remove(pathList.size()-1);
        return allPathList;
    }


    @Test
    public void testTreeDepth(){
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(7);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);

        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node3.right = node4;
        int max = TreeDepth(root);
        System.out.println(max);
    }



    /**
    * @Description: 输入一棵二叉树，判断该二叉树是否是平衡二叉树。
    在这里，我们只需要考虑其平衡性，不需要考虑其是不是排序二叉树
    * @Date: 2020/5/22 0022
    */
    public boolean IsBalanced_Solution(TreeNode root) {
        return getDept(root)!=-1;
    }

    private int getDept(TreeNode root) {
        if(root == null) return 0;
        int left = getDept(root.left);
        if(left == -1) return -1;
        int right = getDept(root.right);
        if(right == -1) return -1;
        return Math.abs(left-right)>1?-1 : Math.max(left,right)+1;
    }

    /**
    * @Description: 一个整型数组里除了两个数字之外，其他的数字都出现了两次。
     * 请写程序找出这两个只出现一次的数字。
    * @Date: 2020/5/22 0022
    */
    public void FindNumsAppearOnce(int [] array,int num1[] , int num2[]) {
        quickSort(array,0,array.length-1);
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < array.length-1; i++) {
            if(array[i]!=array[i+1]){
                if(i==0){
                    list.add(array[i]);
                }else{
                    if(array[i]!=array[i-1]){
                        list.add(array[i]);
                    }
                }
            }
        }
        if(list.size()==1){
            list.add(array[array.length-1]);
        }
        num1[0] = list.get(0);
        num2[0] = list.get(1);
    }

    private void quickSort(int[] array,int left,int right) {
        if(left>right){
            return;
        }
        int i = left;
        int j = right;
        int temp = array[left];
        while(i<j){
            while(i<j && array[j]>=temp){
                j--;
            }
            while(i<j && array[i]<=temp){
                i++;
            }

            int k = array[i];
            array[i] = array[j];
            array[j] = k;


        }
        array[left] = array[i];
        array[i] = temp;
        quickSort(array,left,i-1);
        quickSort(array,j+1,right);

    }

    @Test
    public void testQuickSort(){
        int[] array = {1,6,2,9,3,5,0,5,9,12};
        quickSort(array,0,array.length-1);
        for (int i:
             array) {
            System.out.print(i+" ");
        }
    }
    @Test
    public void testFindNumsAppearOnce(){
       int i=1;
       int j=0;
        System.out.println(i^j);

    }
}
