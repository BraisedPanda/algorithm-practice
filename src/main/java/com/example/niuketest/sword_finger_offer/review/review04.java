package com.example.niuketest.sword_finger_offer.review;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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
//    public String PrintMinNumber(int [] numbers) {
//        List<String> list = new ArrayList<>();
//        FullArray(numbers, 0, list);
//        return null;
//    }

//    private void FullArray(int[] numbers, int index, List<String> list) {
//        String numbersArray = arrayNumbers(numbers);
//        if(index == numbers.length-1){
//            if(!list.contains(numbers.toString())){
//                list.add(numbers.toString());
//            }
//        }
//    }



}
