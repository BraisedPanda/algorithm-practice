package com.example.niuketest.sword_finger_offer.review;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Stack;

/**
 * @program: niuke-test
 * @description: 复习之前的题目
 * @author:
 * @create: 2020-04-15 08:50
 **/
public class review01 {

    /* ----------1---------- */
    /* 
    * @Description:
    在一个二维数组中（每个一维数组的长度相同），
    每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
    请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
    1 2 8 9             
    2 4 9 12
    4 7 10 13     9
    6 8 11 15
    */
    //方法1.全部遍历
    public boolean Find1(int target, int [][] array) {
        int length = array.length;
        int width = array[0].length;
        for (int i=0;i<width;i++){
            for(int j=0;j<length;j++){
                if(target == array[i][j]){
                    return true;
                }
            }
        }
        return false;
    }
    //方法2.按照数组特点来查找,从左下角开始找
    public boolean Find2(int target, int [][] array) {
       int line = array.length-1;
       int row = 0;
       while(line>=0 && row<=array[0].length-1){
            if(target == array[line][row]){
                return true;
            }else if(target > array[line][row]){
                row++;
            }else {
                line--;
            }
       }
       return false;

    }

    @Test
    public void testFind2(){
        int targer = 15;
        int[][] array = {{1,2,8,9},{2,4,9,12},{4,7,10,13},{6,8,11,15}};
        Find2(targer,array);
    }

    /* ----------2---------- */

    /*
    * @Description: 请实现一个函数，将一个字符串中的每个空格替换成“%20”。
    * 例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
    */

    public String replaceSpace1(StringBuffer str) {
        String string = str.toString();
        return string.replaceAll(" ","%20");
    }

    public String replaceSpace2(StringBuffer str) {
        StringBuffer sb = new StringBuffer();
        for(int i=0;i<str.length();i++){
            if(str.charAt(i)!=' '){
                sb.append(str.charAt(i));
            }else{
                sb.append("%20");
            }
        }
        return sb.toString();
    }

    @Test
    public void testReplaceSpace(){
        StringBuffer sb =  new StringBuffer("We Are Happy");
        System.out.println(replaceSpace2(sb));
    }

    /* ----------3---------- */

    /*
    * @Description:  输入一个链表，按链表从尾到头的顺序返回一个ArrayList。
    */
    public class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }
    //方法1.利用栈的特点输出
    public ArrayList<Integer> printListFromTailToHead1(ListNode listNode) {
        Stack<Integer> stack = new Stack<>();
        ArrayList<Integer> list = new ArrayList<>();
        if(listNode == null){
            return list;
        }
        while(listNode!=null){
            stack.push(listNode.val);
            listNode = listNode.next;
        }
        while(!stack.isEmpty()){
            list.add(stack.pop());
        }
        return list;
    }
    //方法2.头插法
    public ArrayList<Integer> printListFromTailToHead2(ListNode listNode) {
            ListNode head  =  new ListNode(0);
            ListNode pre ;
            ArrayList<Integer> list = new ArrayList<>();
            while(listNode!=null){
                pre = listNode;
                listNode = listNode.next;
                pre.next = head.next;
                head.next = pre;

            }
            head = head.next;
            while(head!=null){
                list.add(head.val);
                head = head.next;
            }
            return list;
    }

    @Test
    public void testPrintListFromTailToHead(){
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3= new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5= new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        ArrayList<Integer> list = printListFromTailToHead2(node1);
        System.out.println(list.toString());
    }

    /* ----------4---------- */

    /*
    * @Description: 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。
    * 假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
    * 例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，
    * 则重建二叉树并返回。
    */
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public TreeNode reConstructBinaryTree(int [] pre,int [] in) {
        if(pre.length == 0 || in.length==0){
            return null;
        }
        TreeNode node = new TreeNode(pre[0]);
        int index = 0;
        for(int i=0;i<in.length;i++){
             if(pre[0] == in[i]){
                    index = i;
                    break;
                }
        }


        node.left = reConstructBinaryTree(Arrays.copyOfRange(pre,1,index+1),Arrays.copyOfRange(in,0,index));
        node.right = reConstructBinaryTree(Arrays.copyOfRange(pre,index+1,pre.length),Arrays.copyOfRange(in,index+1,in.length));
        return node;

    }

    @Test
    public void testReConstructBinaryTree(){
        int [] pre = {1,2,4,7,3,5,6,8};
        int [] in = {4,7,2,1,5,3,8,6};
        TreeNode node = reConstructBinaryTree(pre,in);
        System.out.println(node.toString());
    }

    /* ----------5---------- */

    /*
    * @Description: 用两个栈来实现一个队列，
    * 完成队列的Push和Pop操作。 队列中的元素为int类型。
    */

        Stack<Integer> stack1 = new Stack<Integer>();
        Stack<Integer> stack2 = new Stack<Integer>();

        public void push(int node) {
            stack1.push(node);
        }

        public int pop() {
            if(stack2.isEmpty()){
                while(stack1.size()>0){
                    stack2.push(stack1.pop());
                }
            }


            return stack2.pop();
        }

        @Test
        public void tets(){
            push(1);
            push(2);
            System.out.println(pop());
            push(3);
            System.out.println(pop());
            System.out.println(pop());
        }

     /* ----------6---------- */

    /*
    * @Description: 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
        输入一个非递减排序的数组的一个旋转，输出旋转数组的最小元素。
        例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。
        NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。

    */
    public int minNumberInRotateArray(int [] array) {
        if(array.length == 0){
            return 0;
        }
        int letf = 0;
        int right = array.length-1;
        int mid=0;
        while(letf<right){

            if(array[letf]<array[right]){
                mid = (letf+right)/2;
                right = mid;
            }else{
                mid = (letf+right)/2;
                letf = mid;
            }
        }
        return array[mid];

    }

    @Test
    public void testMinNumberInRotateArray(){
        int[] array = {3,4,5,6,7,0,1,2};
        Objects.equals("a","b");
        System.out.println(minNumberInRotateArray(array));
    }


     /* ----------7---------- */
    /*
    * @Description: 大家都知道斐波那契数列，现在要求输入一个整数n，
    * 请你输出斐波那契数列的第n项（从0开始，第0项为0）。n<=39
    * 斐波那契数列：从第三项开始，后一项是前两项的和
    */
     public int Fibonacci(int n) {
        if(n == 0){
            return 0;
        }
        if(n == 2 || n == 1){
            return 1;
        }
        return Fibonacci(n-1)+Fibonacci(n-2);
     }

     @Test
     public void testFibonacci(){
         System.out.println(Fibonacci(39));
     }

     /* ----------8---------- */
     /*
     * @Description: 一只青蛙一次可以跳上1级台阶，也可以跳上2级。
     * 求该青蛙跳上一个n级的台阶总共有多少种跳法（先后次序不同算不同的结果）。
     * 一阶：1
     * 二阶：2
     * 三阶：3
     * 四阶：5
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

     /* ----------9---------- */
     /*
     * @Description: 一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。
     * 求该青蛙跳上一个n级的台阶总共有多少种跳法。
     * 一阶：1
     * 二阶：2
     * 三阶：4
     * 四阶：8
     */
     public int JumpFloorII(int target) {
        if(target == 0){
            return 0;
        }
        if(target == 1){
            return 1;
        }
        return 2*JumpFloorII(target-1);
     }

     /* ----------10---------- */
     /*
     * @Description:我们可以用2*1的小矩形横着或者竖着去覆盖更大的矩形。
     * 请问用n个2*1的小矩形无重叠地覆盖一个2*n的大矩形，总共有多少种方法？
        比如n=3时，2*3的矩形块有3种覆盖方法：
        n=1: 1
        n=2: 2
        n=3: 3
        n=4: 5
     */
     public int RectCover(int target) {
        if(target == 0){
            return 0;
        }
        if(target ==1){
            return 1;
        }
        if(target ==2){
            return 2;
        }
        return RectCover(target-1)+RectCover(target-2);
     }






}
