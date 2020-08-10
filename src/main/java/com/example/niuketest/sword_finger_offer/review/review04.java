package com.example.niuketest.sword_finger_offer.review;

import org.junit.Test;

import java.util.*;

import static java.lang.Math.min;

/**
 * @program: niuke-test
 * @description: 剑指offer联系题31~40
 * @author: braisedPanda
 * @create: 2020-08-07 15:09
 **/
public class review04 {
    /** [31]
    * @Description: 整数1出现的次数
    * @Param: 求出1~13的整数中1出现的次数,并算出100~1300的整数中1出现的次数？
     * 为此他特别数了一下1~13中包含1的数字有1、10、11、12、13因此共出现6次,但是对于后面问题他就没辙了。
     * ACMer希望你们帮帮他,并把问题更加普遍化,可以很快的求出任意非负整数区间中1出现的次数（从1 到 n 中1出现的次数）。
    * @Author: braisedPanda
    * @Date: 2020/8/7 0007 
    */
    public int NumberOf1Between1AndN_Solution(int n) {
        int count = 0;
        for (int i = 0; i <=n; i++) {
            char[] charArray = String.valueOf(i).toCharArray();
            for (Character c:
                 charArray) {
                if(c.toString().equals("1")){
                    count++;
                }
            }
        }
        return count;
    }
    @Test
    public void testNumberOf1Between1AndN_Solution(){
        int sum = NumberOf1Between1AndN_Solution(13);
        System.out.println(sum);
    }

    /** 
    * @No : [32]
    * @Title : 把数组排成最小的数
    * @Description : 输入一个正整数数组，把数组里所有数字拼接起来排成一个数，
     * 打印能拼接出的所有数字中最小的一个。例如输入数组{3，32，321}，则打印出这三个数字能排成的最小数字为321323。
    * @Author : braisedPanda      
    * @Date : 2020/8/7 0007 
    */
    // 解法1：数组的比较方法
    public String PrintMinNumber(int [] numbers) {
        List<String> list = new ArrayList<>();
        for (int num: numbers) {
            list.add(num+"");
        }
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String st1, String st2) {
                String compare1 = st1+st2;
                String compare2 = st2+st1;
                return compare1.compareTo(compare2);
            }
        });
        String result = "";
        for (String str:
        list) {
            result = result+str;
        }

        return result;
    }

    // 解法2：全排列方法
    public String PrintMinNumber2(int [] numbers) {
        List<String> list = new ArrayList<>();
        FullArray(numbers, 0, list);
        Collections.sort(list);
        return list.get(0);
    }

    private void FullArray(int[] numbers, int index, List<String> list) {
        if(index == numbers.length-1){
            String numbersArray = arrayNumbers(numbers);
            if(!list.contains(numbersArray)){
                list.add(numbersArray);
            }
        }else {
            for(int i=index;i<numbers.length;i++){
                swap(numbers,index,i);
                FullArray(numbers,index+1,list);
                swap(numbers,index,i);
            }
        }


    }
    // 交换数组的两个位置
    private void swap(int[] numbers, int index, int i) {
        int temp = numbers[index];
        numbers[index] = numbers[i];
        numbers[i] = temp;
    }

    private String arrayNumbers(int[] numbers) {
        StringBuffer sb = new StringBuffer();
        for (int num:
             numbers) {
            sb.append(num+"");
        }
        return sb.toString();
    }


    @Test
    public void testPrintMinNumber(){
        int[] numbers = {3,32,321};
        String str = PrintMinNumber2(numbers);
        System.out.println(str);
    }
    
    
    
    /** 
    * @No : [33]
    * @Title : 丑数
    * @Description : 把只包含质因子2、3和5的数称作丑数（Ugly Number）。
     * 例如6、8都是丑数，但14不是，因为它包含质因子7。 习惯上我们把1当做是第一个丑数。求按从小到大的顺序的第N个丑数
    * @Author : braisedPanda      
    * @Date : 2020/8/10 0010 
    */
    public int GetUglyNumber_Solution(int index) {
        if(index <= 0){
            return 0;
        }
        int[] result = new int[index];
        int count = 0;
        int i2 = 0;
        int i3 = 0;
        int i5 = 0;
        int temp = 0;
        result[0] = 1;
        while(count<index-1){
            temp = min(result[i2]*2,min(result[i3]*3,result[i5]*5));
            if(temp == result[i2]*2){
                i2++;
            }
            if(temp == result[i3]*3){
                i3++;
            }
            if(temp == result[i5]*5){
                i5++;
            }
            count++;
            result[count] = temp;

        }
        return result[index-1];
    }
    
    
    /** 
    * @No : [34]
    * @Title : 第一个只出现一次的字符
    * @Description : 在一个字符串(0<=字符串长度<=10000，全部由字母组成)中找到第一个只出现一次的字符,并返回它的位置,
     * 如果没有则返回 -1（需要区分大小写）.（从0开始计数）
    * @Author : braisedPanda      
    * @Date : 2020/8/10 0010 
    */
    public int FirstNotRepeatingChar(String str) {
        char[] charArray = str.toCharArray();
        List<String> characterList = new ArrayList<>();
        List<String> duplicateList = new ArrayList<>();
        for (int i = 0; i < charArray.length; i++) {
            String s = String.valueOf(charArray[i]);
            if(!characterList.contains(s)){
                characterList.add(s);
            }else{
                duplicateList.add(s);
            }
        }
        characterList.removeAll(duplicateList);
        if(characterList.size()>0){
            String c = characterList.get(0);
            int result = -1;
            for (int i = 0; i < charArray.length; i++) {
                if(c.equals(String.valueOf(charArray[i]))){
                    result = i;
                    break;
                }
            }
            return result;
        }else{
            return -1;
        }

    }

    @Test
    public void testFirstNotRepeatingChar(){
        String str = "google";
        int index = FirstNotRepeatingChar(str);
        System.out.println(index);
    }
    
    
    /** 
    * @No : [35]
    * @Title : 数组中的逆序对
    * @Description : （暂时看不懂代码，跳过）
    * @Author : braisedPanda      
    * @Date : 2020/8/10 0010 
    */ 

    /** 
    * @No : [36]
    * @Title : 两个链表的公共节点
    * @Description :输入两个链表，找出它们的第一个公共结点。
     * （注意因为传入数据是链表，所以错误测试数据的提示是用其他方式显示的，保证传入数据是正确的）
    * @Author : braisedPanda      
    * @Date : 2020/8/10 0010 
    */

    public class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }
    // 解法1：暴力遍历，时间复杂度大
    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        ListNode node1 = pHead1;
        ListNode node2;
        ListNode selectedNode = null;
        while(node1 != null){
            node2 = pHead2;
            while(node2 != null){
                if(node2.val == node1.val){
                    selectedNode = node1;
                    return selectedNode;
                }
                node2 = node2.next;
            }
            node1 = node1.next;
        }
        return selectedNode;
    }
    // 解法2：两个节点同时走，走到相同的节点；返回，其中一个走到末，返回
    public ListNode FindFirstCommonNode2(ListNode pHead1, ListNode pHead2) {
       ListNode node1 = pHead1;
       ListNode node2 = pHead2;
       while(node1 != node2){
            if(node1 != null) node1 = node1.next;
            if(node2 != null) node2 = node2.next;
            if(node1 != node2){
                if(node1 == null) node1 = pHead2;
                if(node2 == null) node2 = pHead1;
            }
       }
       return node1;

    }

    /**
    * @No : [37]
    * @Title : 统计一个数字在排序数组中出现的次数
    * @Description : 统计一个数字在排序数组中出现的次数。
    * @Author : braisedPanda
    * @Date : 2020/8/10 0010
    */
    public int GetNumberOfK(int [] array , int k) {
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            if(array[i] > k){
                break;
            }
            if(array[i] == k){
                count++;
            }
        }
        return count;
    }


    /**
    * @No : [38]
    * @Title : 二叉树的深度
    * @Description : 输入一棵二叉树，求该树的深度。
     * 从根结点到叶结点依次经过的结点（含根、叶结点）形成树的一条路径，最长路径的长度为树的深度。
    * @Author : braisedPanda
    * @Date : 2020/8/10 0010
    */
    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }

    }
    // 解法1：递归实现
    public int TreeDepth1(TreeNode root) {
        if(root == null){
            return 0;
        }
        int left = TreeDepth1(root.left);
        int right = TreeDepth1(root.right);
        return left > right ? left+1 : right+1;

    }
    // 解法2：层次遍历实现
    public int TreeDepth2(TreeNode root) {
        if(root == null){
            return 0;
        }
        // 用List模拟列队
        List<TreeNode> queue = new ArrayList<>();
        queue.add(root);
        int nextCount = 1;
        int count = 0;
        int depth = 0;
        while(queue.size() > 0){
            TreeNode node = queue.remove(0);
            count++;
            if(node.left != null){
                queue.add(node.left);
            }
            if(node.right != null){
                queue.add(node.right);
            }
            if(nextCount == count){
                nextCount = queue.size(); // queue.size()是下一层的总节点数
                count = 0;
                depth++; //如果这一层全部加载完，深度加一
            }

        }
        return depth;
    }
    
    /** 
    * @No : [39]
    * @Title : 平衡二叉树
    * @Description : 输入一棵二叉树，判断该二叉树是否是平衡二叉树。
     * 在这里，我们只需要考虑其平衡性，不需要考虑其是不是排序二叉树
    * @Author : braisedPanda      
    * @Date : 2020/8/10 0010 
    */

    public boolean IsBalanced_Solution(TreeNode root) {
        return judgmentIsBalancedSolution(root)!=-1;
    }

    private int judgmentIsBalancedSolution(TreeNode root) {
        if(root == null) return 0;
        int left = judgmentIsBalancedSolution(root.left);
        if(left == -1) return -1;
        int right = judgmentIsBalancedSolution(root.right);
        if(right == -1) return -1;
        return  Math.abs(left - right)>1?-1:Math.max(left,right)+1;

    }
    
    
    /** 
    * @No : [40]
    * @Title : 数组中只出现一次的数字
    * @Description : 一个整型数组里除了两个数字之外，其他的数字都出现了两次。
     * 请写程序找出这两个只出现一次的数字
    * @Author : braisedPanda      
    * @Date : 2020/8/10 0010 
    */
    public void FindNumsAppearOnce(int [] array,int num1[] , int num2[]) {
        List<Integer> list = new ArrayList<>();
        List<Integer> repeatList = new ArrayList<>();
        for (int num:
             array) {
            if(!list.contains(num)){
                list.add(num);
            }else{
                repeatList.add(num);
            }
        }
        list.removeAll(repeatList);
        num1[0] = list.get(0);
        num2[0] = list.get(1);
    }
    
    
    

}
