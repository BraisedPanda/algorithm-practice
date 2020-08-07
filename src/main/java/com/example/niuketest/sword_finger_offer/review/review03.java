package com.example.niuketest.sword_finger_offer.review;

import org.junit.Test;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * @program: niuke-test
 * @description: 剑指offer复习题21~30
 * @author: braisedPanda
 * @create: 2020-07-14 17:29
 **/
public class review03 {

    /** [21]
    * @Description: 栈的压入，弹出序列
    * @Param: 输入两个整数序列，第一个序列表示栈的压入顺序，
     *      * 请判断第二个序列是否可能为该栈的弹出顺序.假设压入栈的所有数字均不相等。
     *      * 例如序列1,2,3,4,5是某栈的压入顺序，序列4,5,3,2,1是该压栈序列对应的一个弹出序列，
     *      * 但4,3,5,1,2就不可能是该压栈序列的弹出序列。（注意：这两个序列的长度是相等的）
    * @Date: 2020/7/14 0014
    */
    public boolean IsPopOrder(int [] pushA,int [] popA) {
        Stack<Integer> stack = new Stack<>();
        int index = 0;
        for(int i = 0; i < pushA.length; i++){
            int temp = pushA[i];
            stack.push(temp);
            if(popA[index] == temp){
                while(!stack.isEmpty() && popA[index] == stack.peek()){
                    stack.pop();
                    index++;
                }
            }
        }
        return stack.isEmpty();
    }
    @Test
    public void testIsPopOrder(){
        int[] pushA = {1,2,3,4,5};
        int[] popA = {4,5,3,2,1};
        System.out.println(IsPopOrder(pushA,popA));
    }


    /** [22]
    * @Description: 从上到下打印二叉树
    * @Param: 从上往下打印出二叉树的每个节点，同层节点从左至右打印。
    * @Date: 2020/7/14 0014
    */
    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }

    }
    // 层次遍历
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        ArrayList<Integer> resultList = new ArrayList<>();
        if(root == null){
            return resultList;
        }
        ArrayList<TreeNode> list = new ArrayList<>();
        list.add(root);
        while(list.size() > 0){
            TreeNode node = list.get(0);
            resultList.add(node.val);
            if(node.left != null){
                list.add(node.left);
            }
            if(node.right != null){
                list.add(node.right);
            }
            list.remove(0);
        }
        return resultList;
    }

    @Test
    public void testPrintFromTopToBottom(){
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(4);
        TreeNode node4 = new TreeNode(5);

        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node3.right = node4;
        System.out.println(PrintFromTopToBottom(root));
    }
    
    /** [23]
    * @Description: 二叉树的后序遍历
    * @Param: 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。
     * 如果是则输出Yes,否则输出No。假设输入的数组的任意两个数字都互不相同。
    * @Author: braisedPanda
    * @Date: 2020/7/15 0015 
    */

    public boolean VerifySquenceOfBST(int [] sequence) {
        if(sequence.length == 0){
            return false;
        }
        int start = 0;
        int end = sequence.length-1;
        return verifyBST(sequence, start, end);
    }

    private boolean verifyBST(int[] sequence, int start, int end) {
        if(start >= end){
            return true;
        }
        int i = start;
        for(;i<end;i++){
            if(sequence[i] > sequence[end]){
                break;
            }
        }
        for(int j=i; j<end; j++){
            if(sequence[j]<sequence[end]){
                return false;
            }
        }
        return verifyBST(sequence,start,i-1) &&verifyBST(sequence,i,end-1);
    }
    
    /** [24]
    * @Description: 二叉树中合为某一值的路径
    * @Param: 输入一颗二叉树的根节点和一个整数，按字典序打印出二叉树中结点值的和为输入整数的所有路径。
     * 路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。
    * @Author: braisedPanda
    * @Date: 2020/7/16 0016 
    */
    ArrayList<ArrayList<Integer>> resultList = new ArrayList<>();
    ArrayList<Integer> pathList = new ArrayList<>();
    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root,int target) {
        if(root == null){
            return resultList;
        }
        pathList.add(root.val);
        target = target - root.val;
        if(target == 0){
            resultList.add(new ArrayList<>(pathList));
        }
        FindPath(root.left,target);
        FindPath(root.right,target);
        pathList.remove(pathList.size()-1);
        return resultList;
    }

    // 没走通
    public ArrayList<ArrayList<Integer>> FindPath2(TreeNode root,int target) {
        ArrayList<ArrayList<Integer>> resultList = new ArrayList<>();
        if(root == null){
            return resultList;
        }
        ArrayList<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        int count = 0; // 步数
        while(!stack.isEmpty()){
            TreeNode node = stack.pop();
            list.add(node.val);
            if(node.left!=null && node.right!=null){
                count++;
            }
            if(node.right!=null){
                stack.add(node.right);
            }
            if(node.left!=null){
                stack.add(node.left);
            }

            if(node.left == null && node.right == null ){
                if(target == countTotal(list)){
                    resultList.add(new ArrayList<>(list));
                }
                list.remove(list.size()-1);
            }

        }
        return resultList;
    }

    private int countTotal(ArrayList<Integer> list) {
        int count = 0;
        for (int i:
             list) {
            count = count+i;
        }
        return count;
    }


    @Test
    public void testFindPath(){
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(4);
        TreeNode node4 = new TreeNode(5);

        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node3.right = node4;
        System.out.println(FindPath(root,4));
    }


    /** [25]
    * @Description:  复杂链表的复制
    * @Param: 输入一个复杂链表（每个节点中有节点值，以及两个指针，一个指向下一个节点，另一个特殊指针random指向一个随机节点），
     * 请对此链表进行深拷贝，并返回拷贝后的头结点。（注意，输出结果中请不要返回参数中的节点引用，否则判题程序会直接返回空）
    * @Author: braisedPanda
    * @Date: 2020/7/17 0017
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
        RandomListNode root = pHead;
        // 复制链表，呈现1->(1)->2->(2)->3->(3)的形式
        while(root != null){
            RandomListNode node = new RandomListNode(root.label);
            node.next = root.next;
            root.next = node;
            root = root.next.next;
        }
        // 复制random指针
        root = pHead;
        while(root!=null){
            if(root.random!=null){
                RandomListNode nextRoot = root.next;
                nextRoot.random = root.random.next;
            }
            root = root.next.next;
        }
        // 剥离复制的node结点
        root = pHead;
        RandomListNode newRoot = new RandomListNode(0);
        RandomListNode node = pHead.next;
        newRoot.next = root.next;
        while(root != null){
            root.next = root.next.next;
            if(node.next!=null){
                node.next = node.next.next;
                node = node.next;
            }
            root = root.next;
        }
        return newRoot.next;
    }


    @Test
    public void testClone(){
        RandomListNode root = new RandomListNode(0);
        RandomListNode node1 = new RandomListNode(1);
        RandomListNode node2 = new RandomListNode(2);
        root.next = node1;
        node1.next = node2;
        root.random = node2;
        node1.random = root;
        Clone(root);
    }


    /** [26]
    * @Description: 二叉搜索树与双向链表
    * @Param: 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。
     * 要求不能创建任何新的结点，只能调整树中结点指针的指向。
    * @Author: braisedPanda
    * @Date: 2020/7/17 0017
    */

    TreeNode head = null; // 头
    TreeNode pointer = null; // 指针，改变指针来转成链表

    public TreeNode Convert(TreeNode pRootOfTree){
        ConvertSub(pRootOfTree);
        return head;
    }
    // 中序遍历
    private void ConvertSub(TreeNode pRootOfTree) {
        if(pRootOfTree == null) return ;
        ConvertSub(pRootOfTree.left);
        if(pointer == null){
            head = pRootOfTree;
            pointer = pRootOfTree;
        }else{
            pointer.right = pRootOfTree;
            pRootOfTree.left = pointer;
            pointer = pRootOfTree;
        }
        ConvertSub(pRootOfTree.right);
    }

    @Test
    public void testList(){
        TreeNode root = new TreeNode(2);
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(3);
        root.left = node1;
        root.right = node2;
        Convert(root);
    }
    
    
    /** [27]
    * @Description: 字符串的排列
    * @Param: 输入一个字符串,按字典序打印出该字符串中字符的所有排列。
     * 例如输入字符串abc,则打印出由字符a,b,c所能排列出来的所有字符串abc,acb,bac,bca,cab和cba。
    * @Author: braisedPanda
    * @Date: 2020/7/20 0020 
    */
    public ArrayList<String> Permutation(String str) {
        ArrayList<String> list = new ArrayList<>();
        if(StringUtils.isEmpty(str) || str.length() == 0){
            return list;
        }
        permutationHelper(str.toCharArray(),0,list);
        Collections.sort(list);
        return list;

    }

    private void permutationHelper(char[] str, int index, ArrayList<String> list) {
        if(index == str.length-1){
            if(!list.contains(String.valueOf(str))){
                list.add(String.valueOf(str));
            }
        }else {
            for (int i = index; i < str.length; i++) {
                swap(str, index, i);
                permutationHelper(str, index + 1, list);
                swap(str, index, i);
            }
        }
    }

    private void swap(char[] str, int index, int i) {
        char temp = str[index];
        str[index] = str[i];
        str[i] = temp;
    }

    @Test
    public void testPermutation(){
        String str = "abc";
        System.out.println(Permutation(str));
    }
    
    
    /** [28]
    * @Description: 数组中，出现次数超过一半的数字
    * @Param: 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
     * 例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。由于数字2在数组中出现了5次，
     * 超过数组长度的一半，因此输出2。如果不存在则输出0。
    * @Author: braisedPanda
    * @Date: 2020/7/21 0021 
    */
    public int MoreThanHalfNum_Solution(int [] array) {
        if(array.length == 1){
            return array[0];
        }
        Map<Integer,Integer> map = new HashMap<>();
        for (int number:array) {
            if(map.containsKey(number)){
                int count = map.get(number)+1;
                if(count>array.length/2){
                    return number;
                }
                map.replace(number,count);
            }else {
                map.put(number,1);
            }
        }
        return 0;
    }

    @Test
    public void testMoreThanHalfNum_Solution(){
        int[] array = {1};
        System.out.println(MoreThanHalfNum_Solution(array));
    }


    /** [29]
    * @Description: 最小k的个数
    * @Param: 输入n个整数，找出其中最小的K个数。例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4,。
    * @Author: braisedPanda
    * @Date: 2020/7/21 0021
    */
    // 使用冒泡排序和快速排序两种方法来完成
    public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        ArrayList<Integer> list = new ArrayList<>();
        if(input == null || input.length == 0){
            return list;
        }
        bubbleSort(input);
        for (int i = 0; i <k ; i++) {
            list.add(input[i]);
        }
        return list;
    }

    // 冒泡排序，大的数字后移
    public void bubbleSort(int[] array){
        for (int i = 0; i < array.length; i++) {
            for (int j = i+1; j < array.length-1; j++) {
                if(array[i]>array[j]){
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
    }
    // 快速排序
    public void quickSort(int[] array, int left, int right){
        if(left > right){
            return;
        }
        int i = left;
        int j = right;
        int temp = array[left];
        while(i < j){
            while (array[j] >= temp && i < j){
                j--;
            }
            while (array[i] <= temp && i < j){
                i++;
            }

            // 交换i j指针对应值的位置
            int number = array[i];
            array[i] = array[j];
            array[j] = number;

        }
        // 交换基准位的值
        array[left] = array[i];
        array[i] = temp;
        quickSort(array, left, i-1);
        quickSort(array,j+1, right);
    }

    @Test
    public void testBubbleSort(){
        int[] array = {4,5,1,6,2,7,3,8};
        quickSort(array,0,array.length-1);
        for (int number:
             array) {
            System.out.println(number);
        }
    }


    /** [30]
    * @Description:  连续子数组的最大和
    * @Param: HZ偶尔会拿些专业问题来忽悠那些非计算机专业的同学。今天测试组开完会后,
     * 他又发话了:在古老的一维模式识别中,
     * 常常需要计算连续子向量的最大和,当向量全为正数的时候,问题很好解决。
     * 但是,如果向量中包含负数,是否应该包含某个负数,并期望旁边的正数会弥补它呢？
     * 例如:{6,-3,-2,7,-15,1,2,2},连续子向量的最大和为8(从第0个开始,到第3个为止)。
     * 给一个数组，返回它的最大连续子序列的和，你会不会被他忽悠住？(子向量的长度至少是1)
    * @Author: braisedPanda
    * @Date: 2020/7/21 0021
    */
    public int FindGreatestSumOfSubArray(int[] array) {
        List<Integer> list = new ArrayList<>();
        for(int i=0;i<array.length;i++){
            int sum = 0;
            for (int j = i; j < array.length; j++) {
                sum = sum + array[j];
                list.add(sum);
            }
        }
        Collections.sort(list);
        return list.get(list.size()-1);
    }


    @Test
    public void testFindGreatestSumOfSubArray(){
        int[] array = {6,-3,-2,7,-15,1,2,2};
        int maxSum = FindGreatestSumOfSubArray(array);
        System.out.println(maxSum);
    }




}
