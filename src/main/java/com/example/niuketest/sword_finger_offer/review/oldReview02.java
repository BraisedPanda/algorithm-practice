package com.example.niuketest.sword_finger_offer.review;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

/**
 * @program: niuke-test
 * @description: 剑指Offer 11~20题目

 * @create: 2020-04-17 15:21
 **/
public class oldReview02 {

      /* ----------11---------- */

      /*
      * @Description:  输入一个整数，输出该数二进制表示中1的个数。其中负数用补码表示。
      */

    public int NumberOf1(int n) {

        int count = 0;
        while(n !=0){
            n = n & n-1;
            count++;
        }
        return count;

    }

    @Test
    public void testNumberOf1(){
        System.out.println(NumberOf1(9));
    }

     /* ----------12---------- */

      /*
      * @Description:  给定一个double类型的浮点数base和int类型的整数exponent。
      * 求base的exponent次方。保证base和exponent不同时为0。
      */

    public double Power(double base, int exponent) {
        if(base == 0){
            return 0;
        }
        if(exponent == 0){
            return 1;
        }
        double result = 1;
        if(exponent>0){
            while(exponent>0){
                result = result*base;
                exponent--;
            }
        }else {
            while(exponent<0){
                result = result*(1/base);
                exponent++;
            }
        }

        return result;
    }

    @Test
    public void testPower(){
        System.out.println(Power(2.0,-5));
    }

     /* ----------13---------- */

      /*
      * @Description:  输入一个整数数组，实现一个函数来调整该数组中数字的顺序，
      * 使得所有的奇数位于数组的前半部分，所有的偶数位于数组的后半部分，
      * 并保证奇数和奇数，偶数和偶数之间的相对位置不变。
      */

    public void reOrderArray(int [] array) {
        int length = array.length;
        for(int i=0;i<length;i++){
            for(int j=0;j<length-1;j++){
                if(array[j]%2 ==0 && array[j+1]%2==1){
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }
        }
        System.out.println();
    }

    @Test
    public void testReOrderArray(){
        int[] array = {1,2,3,4,5,6,7,8,9};
        reOrderArray(array);
        System.out.println(" ");
    }

     /* ----------14---------- */
     public class ListNode {
         int val;
         ListNode next = null;

         ListNode(int val) {
             this.val = val;
         }
     }
      /*
      * @Description:  输入一个链表，输出该链表中倒数第k个结点。
      */
      public ListNode FindKthToTail1(ListNode head,int k) {
          if(head == null){
              return null;
          }
          int a=0;
          ListNode pre = head;
          while(a!=k){
              if(pre!=null){
                  pre = pre.next;
                  a++;
              }else {
                  return null;
              }


          }
          while (pre!=null){
                pre = pre.next;
                head = head.next;
          }
            return head;
      }

    public ListNode FindKthToTail2(ListNode head,int k) {
        if(head == null){
            return null;
        }
        int count = 0;
         ListNode node = head;
         while(node!=null){
            count++;
            if(count>k){
                head = head.next;

            }
            node = node.next;


       }
       if(k>count){
             return null;
       }else return head;
    }

     /* ----------15---------- */
     /*
      * @Description:输入一个链表，反转链表后，输出新链表的表头。
      */
     public ListNode ReverseList(ListNode head) {
        ListNode root = new ListNode(0);

        while(head!=null){
            ListNode node = head;
            head = head.next;
            node.next = root.next;
            root.next = node;
        }
        return root.next;
     }

     /* ----------16---------- */
     /*
      * @Description:输入两个单调递增的链表，
      * 输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不减规则。
      */
     public ListNode Merge(ListNode list1,ListNode list2) {
            ListNode root = new ListNode(0);
            ListNode node = root;
            while(list1!=null && list2!=null){
                if(list1.val>list2.val){
                    node.next = list2;
                    list2 = list2.next;
                    node = node.next;
                }else {
                    node.next = list1;
                    list1 = list1.next;
                    node = node.next;
                }
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
         ListNode node1 = new ListNode(1);
         ListNode node2 = new ListNode(3);
         ListNode node3 = new ListNode(5);
         ListNode node4 = new ListNode(2);
         ListNode node5 = new ListNode(4);
         ListNode node6 = new ListNode(6);
         node1.next = node2;
         node2.next = node3;
         node4.next = node5;
         node5.next = node6;
         Merge(node1,node4);
     }


      /* ----------17---------- */
     /*
      * @Description:输入两棵二叉树A，B，判断B是不是A的子结构。
      * （ps：我们约定空树不是任意一个树的子结构）
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
          boolean flag = false;
          if(root1 == null || root2 == null){
            return false;
          }
          if(!flag){
            flag = isSubTree(root1,root2);
          }
          if(!flag){
            flag = HasSubtree(root1.left,root2);
          }
          if(!flag){
              flag = HasSubtree(root1.right,root2);
          }
          return flag;
      }
    //判断node是否是root的子树
    private boolean isSubTree(TreeNode root, TreeNode node) {
        if(node == null){
            return true;
        }
        if(root == null){
            return false;
        }
        if(root.val == node.val){
            return isSubTree(root.left,node.left) && isSubTree(root.right,node.right);
        }else {
            return false;
        }


    }


      /* ----------18---------- */
     /*
      * @Description:操作给定的二叉树，将其变换为源二叉树的镜像。
      */
      public void Mirror(TreeNode root) {
          if(root == null){
              return;
          }else{
          //如果左子树或者右子树不为空的话，调换两边

                TreeNode node;
                node = root.left;
                root.left = root.right;
                root.right = node;
           }
          Mirror(root.left);
          Mirror(root.right);

      }

    @Test
    public void testMirror(){
        TreeNode root = new TreeNode(0);
        TreeNode left = new TreeNode(1);
        TreeNode right = new TreeNode(2);
        root.left = left;
        root.right = right;
        Mirror(root);

    }


     /* ----------19---------- */
     /*
      * @Description:输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字，
      * 例如，如果输入如下4 X 4矩阵： 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16
      * 则依次打印出数字1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10.
      */
     public ArrayList<Integer> printMatrix(int [][] matrix) {
        int lineLength = matrix.length;
        int rowLength = matrix[0].length;
        int width = 0;
        int height = 0;
        ArrayList<Integer> list = new ArrayList<>();
        int count = (lineLength) * (rowLength);
        while(count>0){
            if(count>0){
                for(int i=width;i<rowLength-width;i++){
                    list.add(matrix[height][i]);
                    count--;
                }
                height++;
            }



            if(count>0) {
                for (int j = height; j < lineLength - height+1; j++) {
                    list.add(matrix[j][rowLength - width - 1]);
                    count--;
                }
                width++;
            }


            if(count>0) {
                for (int m = rowLength - width - 1; m >= width - 1; m--) {
                    list.add(matrix[lineLength - height][m]);
                    count--;
                }
            }

            if(count>0) {
                for (int n = lineLength - height - 1; n >= height; n--) {
                    list.add(matrix[n][width - 1]);
                    count--;
                }
            }

        }
        return list;
     }
     @Test
     public void test(){
         int [][] matrix = {{1,2},{3,4},{5,6},{7,8},{9,10}};
         printMatrix(matrix);
     }


      /* ----------20---------- */
     /*
      * @Description:定义栈的数据结构，请在该类型中实现一个能够得到栈中所含最小元素的min函数
      * （时间复杂度应为O（1））。
        注意：保证测试中不会当栈为空的时候，对栈调用pop()或者min()或者top()方法。
      */

      public class Solution {

          Stack<Integer> stack = new Stack<>();

          public void push(int node) {
              stack.push(node);
          }

          public void pop() {
              stack.pop();
          }

          public int top() {
              return stack.peek();
          }

          public int min() {
            int result = top();
              Iterator<Integer> it = stack.iterator();
              while(it.hasNext()){
                  int temp = it.next();
                    if(result>temp){
                        result = temp;
                    }
              }
              return result;
          }


      }
    @Test
    public void testMin(){
        Solution s = new Solution();
        s.push(3);
        s.min();
        s.push(4);
        s.min();
    }

}
