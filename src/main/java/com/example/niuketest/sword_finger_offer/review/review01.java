package com.example.niuketest.sword_finger_offer.review;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

/**
 * @program: 剑指offer题目1~10
 * @description: 温故而知新，可以为师矣
 * @create: 2020-07-02 15:08
 **/
public class review01 {

    /** [1]
    * @Description:   二维数组中的查找
    * 在一个二维数组中（每个一维数组的长度相同），
     * 每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
     * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
    * @Date: 2020/7/2 0002 
    */
    // 由于二维数组的特性，从左下角开始查找比较方便。若对比值大于目标值，向上一行查找，若对比值小于目标值，向同行右方查找
    //方法1 for循环的方式从左下角开始查找，（未通过，原因：方法复杂度过大）
    public boolean Find(int target, int [][] array) {
        if(array == null){
            return false;
        }
        int width = array[0].length;
        int height = array.length;
        for(int i=height-1;i>=0;){
            for (int j = 0; j < width;) {
                if(i<0 || j>=width){
                    return false;
                }
                if(target == array[i][j]){
                    return true;
                }
                if(target >= array[i][j]){
                    j++;
                }else{
                    i--;
                }
            }
        }
        return  false;
    }

    //方法2 while的方式查找
    public boolean Find2(int target, int [][] array) {
        int width = 0;
        int height = array.length-1;
        while(width<array[0].length && height>=0){
            if(target == array[height][width]){
                return true;
            }else if(target>array[height][width]){
                width++;
            }else{
                height--;
            }
        }
        return false;
    }

    @Test
    public void teseArrayWidthAndLength(){
        int[][] array = new int[3][4];
        System.out.println("width:"+array[0].length);
        System.out.println("length:"+array.length);
    }

    @Test
    public void testFind(){
        int[][] array = new int[4][4];
        array[0][0] = 1;
        array[0][1] = 2;
        array[0][2] = 8;
        array[0][3] = 9;

        array[1][0] = 2;
        array[1][1] = 4;
        array[1][2] = 9;
        array[1][3] = 12;

        array[2][0] = 4;
        array[2][1] = 7;
        array[2][2] = 10;
        array[2][3] = 13;

        array[3][0] = 6;
        array[3][1] = 8;
        array[3][2] = 11;
        array[3][3] = 15;

        int k = 5;
        System.out.println(Find(5,array));
    }



    /** [2]
    * @Description: 替换空格
    * 请实现一个函数，将一个字符串中的每个空格替换成“%20”。
     * 例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
    * @Date: 2020/7/2 0002
    */
    public String replaceSpace(StringBuffer str) {
        String replaceStr = str.toString();
        replaceStr = replaceStr.replace(" ","%20");
        return replaceStr;
    }

    @Test
    public void testSeplaceSpace(){
        StringBuffer sb = new StringBuffer("We Are Happy");
        System.out.println(replaceSpace(sb));
    }
    
    
    /** [3]
    * @Description: 从尾到头打印链表
    * 输入一个链表，按链表从尾到头的顺序返回一个ArrayList
    * @Date: 2020/7/2 0002 
    */

         public class ListNode {
             int val;
             ListNode next = null;

             ListNode(int val) {
                 this.val = val;
             }
         }

    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> list = new ArrayList<>();
        ArrayList<Integer> resultList = new ArrayList<>();
        if(listNode == null){
            return resultList;
        }
        while (listNode!=null){
            int val =listNode.val;
            list.add(val);
            listNode = listNode.next;
        }
        for (int i = list.size()-1; i>=0 ; i--) {
            resultList.add(list.get(i));
        }
        return resultList;
    }
    
    /** [4]
    * @Description: 重建二叉树
    * 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。
     * 假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
     * 例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。
    * @Author: braisedPanda
    * @Date: 2020/7/2 0002 
    */

  public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
    public TreeNode reConstructBinaryTree(int [] pre,int [] in) {
        if(pre.length == 0 || in.length ==0){
            return null;
        }
        TreeNode node = new TreeNode(pre[0]);
        int index = 0;
        for(int i=0;i<in.length;i++){
            if(in[i] == pre[0]){
                index = i;
                break;
            }
        }
        // 构建左子树
        node.left = reConstructBinaryTree(Arrays.copyOfRange(pre,1,index+1),Arrays.copyOfRange(in,0,index));
        node.right = reConstructBinaryTree(Arrays.copyOfRange(pre,index+1,pre.length),Arrays.copyOfRange(in,index+1,in.length));
        return node;
  }
  
  
  /** [5]
  * @Description: 用两个栈来实现队列
  * @Param: 用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型。
  * @Author: braisedPanda
  * @Date: 2020/7/2 0002 
  */
  //stack1 进
  Stack<Integer> stack1 = new Stack<Integer>();
  //stack2 出
  Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        stack1.push(node);
    }

    public int pop() {
        if(stack2.isEmpty()){
            while(!stack1.isEmpty()){
                stack2.push(stack1.pop());
            }
        }

        return stack2.pop();
    }

  /** [6]
  * @Description: 旋转数组的最小数字
  * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
   * 输入一个非递减排序的数组的一个旋转，输出旋转数组的最小元素。
   * 例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。
   * NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。
  * @Date: 2020/7/3 0003 
  */
  public int minNumberInRotateArray(int [] array) {
        if(array.length == 0){
            return 0;
        }
        int i=0;
        int j=array.length-1;
        int mid = (i+j)/2;
        while(i!=j){
            if(array[mid]>array[j]){
                i = mid+1;
                mid = (i+j)/2;
            }else{
                j = mid;
                mid = (i+j)/2;
            }

        }
        return array[i];
  }


  @Test
    public void testMinNumberInRotateArray(){
      int[] array = {3,4,5,6,1};
      System.out.println(minNumberInRotateArray(array));
  }


  /** [7]
  * @Description: 斐波那契数列
  * @Param: 大家都知道斐波那契数列，现在要求输入一个整数n，请你输出斐波那契数列的第n项（从0开始，第0项为0，第1项是1）。
   * n<=39
  * @Date: 2020/7/3 0003
  */

  public int Fibonacci(int n) {
      if(n == 0){
          return 0;
      }
      if(n == 1){
          return 1;
      }
      return Fibonacci(n-2)+Fibonacci(n-1);
  }

  /** [8]
  * @Description: 跳台阶
  * @Param: 一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个n级的台阶总共有多少种跳法（先后次序不同算不同的结果）。
  * @Date: 2020/7/3 0003
  */
  public int JumpFloor(int target) {
      if(target == 0){
          return 0;
      }
      if(target == 1){
          return 1;
      }
      if(target == 2){
          return 2;
      }
      return JumpFloor(target-1)+JumpFloor(target-2);
  }

  /** [9] 变态跳台阶
  * @Description:
  * @Param: 一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。求该青蛙跳上一个n级的台阶总共有多少种跳法。
  * @Date: 2020/7/3 0003 
  */
  public int JumpFloorII(int target) {
    if(target == 0){
        return 0;
    }
    if(target == 1){
        return 1;
    }
    if(target == 2){
        return 2;
    }
    return 2*JumpFloorII(target-1);
  }

  /** [10]
  * @Description: 矩阵覆盖
  * @Param: 我们可以用2*1的小矩形横着或者竖着去覆盖更大的矩形。请问用n个2*1的小矩形无重叠地覆盖一个2*n的大矩形，总共有多少种方法？
   * 比如n=3时，2*3的矩形块有3种覆盖方法：
  * @Date: 2020/7/3 0003
  */
  public int RectCover(int target) {
     if(target == 0){
         return 0;
     }
     if(target == 1){
         return 1;
     }
     if(target == 2){
         return 2;
     }
     return RectCover(target-2)+RectCover(target-1);
  }




}
