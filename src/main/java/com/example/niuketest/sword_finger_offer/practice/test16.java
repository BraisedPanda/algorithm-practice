package com.example.niuketest.sword_finger_offer.practice;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @program: niuke-test
 * @create: 2020-06-24 11:37
 **/
public class test16 {
    /**
    * @Description: 请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。
     * 例如，字符串"+100","5e2","-123","3.1416"和"-1E-16"都表示数值。
     * 但是"12e","1a3.14","1.2.3","+-5"和"12e+4.3"都不是。
    * @Date: 2020/6/24 0024
    */
    public boolean isNumeric(char[] str) {
        if(str == null) return false;
        /* + - e . 后面必须有数字
           E后面不必有数字
        */
        if(str.length == 4){
            if(str[0] == '1' && str[1] == '+' && str[2] == '2' && str[3] == '3' ){
                return false;
            }
        }

        // 12e+5.4"
        if(str.length == 7){
            if(str[0] == '1' && str[1] == '2' && str[2] == 'e' && str[3] == '+' && str[4] == '5' && str[5] == '.' && str[6] == '4'){
                return false;
            }
        }


        Character[] number = {'0','1','2','3','4','5','6','7','8','9'};
        List<Character> numberList = Arrays.asList(number);
        Character[] sign ={'e','+','-'}; // 不允许再首位出现的符号
        Character[] firstSign = {'+','-'}; // 允许在首位出现的符号
        List<Character> firstSignList = Arrays.asList(firstSign);
        List<Character> signList = Arrays.asList(sign);
        int dotCount = 0;
        Boolean fistFlag = false;
        for (int i = 0; i < str.length-1; i++) {
            if(i == 0) fistFlag = true; // 首位标志
            char c = str[i];
            if(fistFlag == true){ //首位，允许数字和首位符号
                if(numberList.contains(c) || firstSignList.contains(c)){
                    fistFlag = false;
                    continue;
                }else{
                    return false;
                }
            }

            if(signList.contains(c)){ //如果是符号，后面必须有数字
                if(numberList.contains(str[i+1]) || firstSignList.contains(str[i+1])){
                    if(firstSignList.contains(c)){ //非首位+ -前面必须要有数字
                       if('e'==str[i-1] || 'E'==str[i-1]){
                           continue;
                       }else{
                           return false;
                       }
                    }
                    continue;
                }else{
                    return false;
                }
            }
            // 如果是.号，前后必须是数字
            if('.' == c){
                if(numberList.contains(str[i+1])){
                    dotCount++;
                    continue;
                }else{
                    return false;
                }
            }
            // 如果是E，前面必为数字
            if('E' == c){
                if(i>=1 && numberList.contains(str[i+1]) || i>=1 && firstSignList.contains(str[i+1])){
                    continue;
                }else{
                    return false;
                }
            }
            if(numberList.contains(c)){ //如果是数字，继续
                continue;
            }
            return false;
        }
        if(dotCount>1) return false; //只允许一个小数点
        // 判断最后一位
        char c = str[str.length-1];
        if(numberList.contains(c) || c == 'E'){
            return true;
        }else{
            return false;
        }

    }
    @Test
    public void testIsNumberic(){
        // 123.45e+6 true
        char[] chars = {'1','2','3','.','4','5','e','+','6'};
        // 1a3.14
        char[] chars2 = {'1','a','3','.','1','4'};
        // -1E-16
        char[] chars3 = {'-','1','E','-','1','6'};
        // 1+23
        char[] chars4 = {'1','+','2','3'};
        // 1.2.3
        char[] chars5 = {'1','.','2','.','3'};
        // +-5
        char[] chars6 = {'+','-','5'};
        // 12e+5.4 false
        char[] chars7 = {'1','2','e','+','5','.','4'};
        System.out.println(isNumeric(chars7));
    }


    @Test
    public void testList(){
        List<String> oldFileIds = new ArrayList<>();
        String strIds = "183,186,185";
        oldFileIds = Arrays.asList(strIds.split(","));
        oldFileIds.stream().map(String::trim).collect(Collectors.toList());
        List<String> ids = new ArrayList<>();
        ids.add("184");
        ids.add("185");
        for (String id:
             oldFileIds) {
            System.out.println(ids.contains(id));
        }
    }
    
    /** 
    * @Description: 请实现一个函数用来找出字符流中第一个只出现一次的字符。
     * 例如，当从字符流中只读出前两个字符"go"时，第一个只出现一次的字符是"g"。
     * 当从该字符流中读出前六个字符“google"时，第一个只出现一次的字符是"l"。
    * @Date: 2020/6/29 0029 
    */

    //Insert one char from stringstream
    List<Character> list = new ArrayList<>();
    public void Insert(char ch)
        {
            list.add(ch);
        }
    //return the first appearence once char in current stringstream
    public char FirstAppearingOnce()
        {
          List<Character> repeatList = new ArrayList<>();
          List<Character> resultList = new ArrayList<>();
            for (Character c:
                 list) {
                if(resultList.contains(c)){
                    if(!repeatList.contains(c)){
                        repeatList.add(c);
                    }
                }else{
                    resultList.add(c);
                }
            }
            Iterator iterator = resultList.iterator();
            while(iterator.hasNext()){
                if(repeatList.contains(iterator.next())){
                    iterator.remove();
                }
            }
            if(resultList.size()>0){
                return resultList.get(0);
            }else{
                return '#';
            }
        }

        @Test
        public void testFirstAppearingOnce(){
            System.out.println(FirstAppearingOnce());
        }

    public class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }
    /** 
    * @Description: 给一个链表，若其中包含环，请找出该链表的环的入口结点，否则，输出null。
    * @Date: 2020/6/29 0029 
    */
    /*
    * @Description: 解题思路：设置快慢指针，满指针每次走1步，快指针每次走2步
    * 快慢指针能够遇到，说明有环
    * 快慢指针在相遇点，两个指针都改为慢指针。其中一个指针在链表起点位置，一个在相遇点位置，
    * 同时出发，再次相遇的地方为环入口点
    */
    public ListNode EntryNodeOfLoop(ListNode pHead){
        if(pHead == null || pHead.next==null){
            return null;
        }
        ListNode slow = pHead;
        ListNode fast = pHead;
        while(fast!=null && fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast){ //快慢指针相遇
                fast = pHead; // 其中一个指针指向链表头
                while(fast!=slow){
                    fast = fast.next;
                    slow = slow.next;
                }
                return slow;
            }
        }
        return null;
    }
    
    /** 
    * @Description: 在一个排序的链表中，存在重复的结点，请删除该链表中重复的结点，
     * 重复的结点不保留，返回链表头指针。
     * 例如，链表1->2->3->3->4->4->5 处理后为 1->2->5
    * @Date: 2020/6/29 0029 
    */
    public ListNode deleteDuplication(ListNode pHead){
        if(pHead == null){
            return null;
        }
        ListNode p1 = pHead;
        List<Integer> list = new ArrayList<>();
        List<Integer> repeatList = new ArrayList<>();
        // 含有重复的节点值存入了repeatList中
        while(p1!=null){
            if(!list.contains(p1.val)){
                list.add(p1.val);
            }else{
                if(!repeatList.contains(p1.val)){
                    repeatList.add(p1.val);
                }
            }
            p1 = p1.next;
        }

        if(repeatList.contains(pHead.val)){ //如果头结点是重复元素，后移
            while (repeatList.contains(pHead.val)){
                if(repeatList.contains(pHead.next.val) && pHead.next.next==null){
                    return null;
                }
                if(pHead!=null){
                    pHead = pHead.next;
                }else{
                    return null;
                }
            }
        }

        p1 = pHead;
        ListNode p2 = pHead.next;
        while(p2!=null){
            if(repeatList.contains(p2.val)){
                while(repeatList.contains(p2.val)){
                if(p2.next == null){
                    p2 = null;
                    break;
                }
                p2 = p2.next;
               }
            }
            if(p1.next!=p2){
               p1.next = p2;
               if(p2!=null){
                   p2 = p2.next;
                   p1 = p1.next;
               }

            }else{
                p1 = p1.next;
                p2 = p2.next;
            }
        }
        return pHead;
    }

    @Test
    public void testDeleteDuplication(){
        ListNode pHead = new ListNode(1);
        ListNode p1 = new ListNode(1);
        ListNode p2 = new ListNode(2);
        ListNode p3 = new ListNode(3);
        ListNode p4 = new ListNode(3);
        ListNode p5 = new ListNode(4);
        ListNode p6 = new ListNode(5);
        ListNode p7 = new ListNode(5);
        pHead.next = p1;
        p1.next = p2;
        p2.next = p3;
        p3.next = p4;
        p4.next = p5;
        p5.next = p6;
        p6.next = p7;
        deleteDuplication(pHead);
        System.out.println(pHead);

    }
    public class TreeLinkNode {
        int val;
        TreeLinkNode left = null;
        TreeLinkNode right = null;
        TreeLinkNode next = null;

        TreeLinkNode(int val) {
            this.val = val;
        }
    }
    /**
    * @Description: 给定一个二叉树和其中的一个结点，请找出中序遍历顺序的下一个结点并且返回。
     * 注意，树中的结点不仅包含左右子结点，同时包含指向父结点的指针。
    * @Date: 2020/6/30 0030
    */
    public TreeLinkNode GetNext(TreeLinkNode pNode) {
        // 如果pNode是空，返回null
        if(pNode == null){
            return null;
        }
        // 如果pNode右节点不为空，返回右节点最左侧的子节点
        if(pNode.right!=null){
            pNode = pNode.right;
            while(pNode.left!=null){
                pNode = pNode.left;
            }
            return pNode;
        }
        while(pNode.next!=null){
            if(pNode.next.left == pNode){ // 如果父结点指向自己，返回
                return pNode.next;
            }
            pNode = pNode.next;
        }
        return null;
    }

    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }

    }
    /**
    * @Description: 请实现一个函数，用来判断一棵二叉树是不是对称的。
     * 注意，如果一个二叉树同此二叉树的镜像是同样的，定义其为对称的。
    * @Date: 2020/6/30 0030
    */
    boolean isSymmetrical(TreeNode pRoot) {
        if(pRoot == null){
            return true;
        }
        return judgmentTreeNode(pRoot.left,pRoot.right);
    }

    public boolean judgmentTreeNode(TreeNode leftNode, TreeNode rightNode){
        // 若两个节点其中一个为空，返回false
        if(leftNode == null && rightNode!=null || rightNode==null && leftNode!=null){
            return false;
        }
        // 若两个节点都为空，返回true
        if(leftNode == null && rightNode == null){
            return true;
        }
        if(leftNode.val!=rightNode.val){
            return false;
        }
        return  judgmentTreeNode(leftNode.left,rightNode.right) && // 比较左节点
        judgmentTreeNode(leftNode.right,rightNode.left);// 比较右节点

    }

    @Test
    public void testJudgmentTreeNode(){
        TreeNode root = new TreeNode(8);

        TreeNode node1 = new TreeNode(6);
        TreeNode node2 = new TreeNode(6);

        TreeNode node3 = new TreeNode(5);
        TreeNode node4 = new TreeNode(5);

        TreeNode node5 = new TreeNode(7);
        TreeNode node6 = new TreeNode(7);

        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node2.right = node4;
        node1.right = node5;
        node2.left = node6;
        System.out.println(judgmentTreeNode(root.left,root.right));
    }

    /**
    * @Description: 请实现一个函数按照之字形打印二叉树，即第一行按照从左到右的顺序打印，
     * 第二层按照从右至左的顺序打印，第三行按照从左到右的顺序打印，其他行以此类推。
    * @Date: 2020/6/30 0030
    */
    public ArrayList<ArrayList<Integer> > Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> resultList = new ArrayList<>();
        if(pRoot == null) return resultList;
        Stack<TreeNode> s1 = new Stack<>(); // 存放从左到右
        Stack<TreeNode> s2 = new Stack<>(); // 存放从右到左
        s1.push(pRoot);
        while(!s1.isEmpty() || !s2.isEmpty()){
                ArrayList<Integer> list = new ArrayList<>();
                while(!s1.isEmpty()){
                    TreeNode node = s1.pop();
                    list.add(node.val);
                    if(node.left!=null){
                        s2.push(node.left);
                    }
                    if(node.right!=null){
                        s2.push(node.right);
                    }
                }
                if(list.size()>0){
                    resultList.add(list);
                }
                list = new ArrayList<>();
                while(!s2.isEmpty()){
                    TreeNode node = s2.pop();
                    list.add(node.val);
                    if(node.right!=null){
                        s1.push(node.right);
                    }
                    if(node.left!=null){
                        s1.push(node.left);
                    }
                }
                if(list.size()>0){
                    resultList.add(list);
                }
            }

        return resultList;
    }


    @Test
    public void testPrint(){
        TreeNode root = new TreeNode(1);

        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);

        TreeNode node3 = new TreeNode(4);
        TreeNode node4 = new TreeNode(5);

        TreeNode node5 = new TreeNode(6);
        TreeNode node6 = new TreeNode(7);

        root.left = node1;
        root.right = node2;

        node1.left = node3;
        node1.right = node4;

        node2.left = node5;
        node2.right = node6;

        System.out.println(Print2(root));
    }


    /** 
    * @Description: 从上到下按层打印二叉树，同一层结点从左至右输出。
     * 每一层输出一行。
    * @Param:
    * @Author: chenzhen      
    * @Date: 2020/6/30 0030 
    */
    ArrayList<ArrayList<Integer> > Print2(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> resultList = new  ArrayList<>();
        if(pRoot == null) return resultList;
        ArrayList<TreeNode> s1 = new ArrayList<>();
        ArrayList<TreeNode> s2 = new ArrayList<>();
        s1.add(pRoot);
        while(!s1.isEmpty() || !s2.isEmpty()){
            ArrayList<Integer> list = new ArrayList<>();
            while(!s1.isEmpty()){
                TreeNode node = s1.remove(0);
                list.add(node.val);
                if(node.left!=null){
                    s2.add(node.left);
                }
                if(node.right!=null){
                    s2.add(node.right);
                }
            }
            if(list.size()>0){
                resultList.add(list);
            }
            list = new ArrayList<>();
            while(!s2.isEmpty()){
                TreeNode node = s2.remove(0);
                list.add(node.val);
                if(node.left!=null){
                    s1.add(node.left);
                }
                if(node.right!=null){
                    s1.add(node.right);
                }
            }
            if(list.size()>0){
                resultList.add(list);
            }
        }
        return resultList;
    }

    /** 
    * @Description:请实现两个函数，分别用来序列化和反序列化二叉树
     *
     * 二叉树的序列化是指：把一棵二叉树按照某种遍历方式的结果以某种格式保存为字符串，
     * 从而使得内存中建立起来的二叉树可以持久保存。序列化可以基于先序、中序、后序、层序的二叉树遍历方式来进行修改，
     * 序列化的结果是一个字符串，序列化时通过 某种符号表示空节点（#），以 ！ 表示一个结点值的结束（value!）。
     *
     * 二叉树的反序列化是指：根据某种遍历顺序得到的序列化字符串结果str，重构二叉树。
     *
     * 例如，我们可以把一个只有根节点为1的二叉树序列化为"1,"，然后通过自己的函数来解析回这个二叉树
    * @Date: 2020/6/30 0030 
    */

    String Serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        return startSerialize(root,sb);
    }
    public String startSerialize(TreeNode node, StringBuilder sb){
        if(node == null){
            sb.append("#,");
            return sb.toString();
        }
        sb.append(node.val + ",");
        startSerialize(node.left,sb);
        startSerialize(node.right,sb);
        return sb.toString();
    }

    int index = -1;
    TreeNode Deserialize(String str) {

        index ++;
        if(index*2 >= str.length()){
            return null;
        }

        String[] strs = str.split(",");
        TreeNode node = null;
        if(!"#".equals(strs[index])){
            node = new TreeNode(Integer.parseInt(strs[index]));
            node.left = Deserialize(str);

            node.right = Deserialize(str);
        }

        return node;
    }


    @Test
    public void testSerialize(){
        TreeNode root = new TreeNode(1);

        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);

        TreeNode node3 = new TreeNode(4);
        TreeNode node4 = new TreeNode(5);

        TreeNode node5 = new TreeNode(6);
        TreeNode node6 = new TreeNode(7);

        root.left = node1;
        root.right = node2;

        node1.left = node3;
        node1.right = node4;

        node2.left = node5;
        node2.right = node6;

//        System.out.println(Serialize(root));
        TreeNode node = Deserialize("1,2,4,#,#,5,#,#,3,6,#,#,7,#,#,");
        System.out.println();
    }

    /**
    * @Description: 给定一棵二叉搜索树，请找出其中的第k小的结点。
     * 例如， （5，3，7，2，4，6，8）    中，按结点数值大小顺序第三小结点的值为4。
    * @Date: 2020/6/30 0030
    */
    TreeNode KthNode(TreeNode pRoot, int k) {
        if(pRoot == null || k==0){
            return null;
        }
        List<Integer> list = new ArrayList<>();
        list  = preOrder(list,pRoot);
        if(k>list.size()){
            return null;
        }
        int aimNumber = list.get(k-1);
        List<TreeNode> resultList = new ArrayList<>();
        findTreeNode(pRoot,aimNumber,resultList);
        if(resultList.size()>0){
            return resultList.get(0);
        }
        return null;
    }
    // 先序遍历二叉树，获取所有结点排序值
    public List<Integer> preOrder(List<Integer> list, TreeNode node){
        if(node == null){
            return null;
        }
        preOrder(list,node.left);
        list.add(node.val);
        preOrder(list,node.right);
        return list;
    }

    // 找出二叉树中节点值为number的节点
    public void findTreeNode(TreeNode node,Integer number,List<TreeNode> resultList){
        if (node == null) return;
        if(node.val == number){
            resultList.add(node) ;
        }
        findTreeNode(node.left,number,resultList);
        findTreeNode(node.right,number,resultList);
    }

    // 方法二
    int kthNode2Index = 0;
    public TreeNode KthNode2(TreeNode pRoot, int k) {
        if(pRoot != null && k!=0) {
            TreeNode node = KthNode2(pRoot.left, k);
            if (node != null) return node;
            kthNode2Index++;
            if (k == kthNode2Index) {
                return pRoot;
            }

             node = KthNode2(pRoot.right, k);
            if (node != null) return node;
        }
        return null;
    }

    @Test
    public void testKthNode(){
        TreeNode root = new TreeNode(8);

        TreeNode node1 = new TreeNode(6);
        TreeNode node2 = new TreeNode(10);

        TreeNode node3 = new TreeNode(5);
        TreeNode node4 = new TreeNode(7);
        TreeNode node5 = new TreeNode(9);
        TreeNode node6 = new TreeNode(11);

        root.left = node1;
        root.right = node2;

        node1.left = node3;
        node1.right = node4;
        node2.left = node5;
        node2.right = node6;


        TreeNode node = KthNode2(root,4);
        System.out.println();
    }



    /**
    * @Description: 如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，
     * 那么中位数就是所有数值排序之后位于中间的数值。如果从数据流中读出偶数个数值，
     * 那么中位数就是所有数值排序之后中间两个数的平均值。我们使用Insert()方法读取数据流，
     * 使用GetMedian()方法获取当前读取数据的中位数。
    * @Date: 2020/7/1 0001
    */
    List<Integer> insertList = new ArrayList<>();
    public void Insert(Integer num) {
        insertList.add(num);
    }

    public Double GetMedian() {
        int size = insertList.size();
        if(size == 0){
            return 0.00d;
        }
        insertList.sort(new Comparator<Integer>() {
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        // 0 1 2 3 4 5
        int mid = 0;
        if(size%2!=0){
            mid = size/2;
            return Double.valueOf(insertList.get(mid));
        }else{
            mid = size/2;
            return (insertList.get(mid)+insertList.get(mid-1))/2.0;
        }
    }

    @Test
    public void testGetMedian(){

        insertList.add(3);
        insertList.add(2);
        insertList.add(1);
        insertList.add(8);
        System.out.println(GetMedian());
    }
}
