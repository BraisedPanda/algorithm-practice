package com.example.niuketest.sword_finger_offer.practice;

/**
 * @program: niuke-test
 * @description:
 * @author: chenzhen
 * @create: 2020-04-07 17:33
 **/
public class test06 {
    public class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }
    /**
    * @Description: 输入两个单调递增的链表，输出两个链表合成后的链表，
     * 当然我们需要合成后的链表满足单调不减规则。
    * @Param:
    * @Author: chenzhen
    * @Date: 2020/4/7 0007
    */


        public ListNode Merge(ListNode list1,ListNode list2) {

            ListNode head = new ListNode(0);
            head.next = null;
            ListNode root = head;
            while(list1!=null && list2!=null){


                if(list1.val>=list2.val){
                    head.next = list2;
                    head = list2;
                    list2 = list2.next;
                }else{
                    head.next = list1;
                    head = list1;
                    list1 = list1.next;
                }

            }
            if(list1 == null){
                head.next = list2;
            }
            if(list2 == null){
                head.next = list1;
            }
            return  root.next;
        }



}
