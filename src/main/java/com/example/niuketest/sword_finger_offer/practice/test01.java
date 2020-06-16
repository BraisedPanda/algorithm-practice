package com.example.niuketest.sword_finger_offer.practice;

import org.junit.Test;

/**
 * @program: niuke-test
 * @description:
 * @author: chenzhen
 * @create: 2020-03-26 11:25
 **/

public class test01 {
    /** 
    * @Description: 一只青蛙一次可以跳上1级台阶，
     * 也可以跳上2级……它也可以跳上n级。
     * 求该青蛙跳上一个n级的台阶总共有多少种跳法。
    * @Param: [number]
    * @Author: chenzhen       
    * @Date: 2020/3/26 0026 
    */ 
//    @Test
//    public int jumpFloorII(int number) {
//        if(number==1){
//            return 1;
//        }
//        if(number==2){
//            return 2;
//        }
//        return 2*jumpFloorII(number-1);
//
//    }
    
    /** 
    * @Description: 我们可以用2*1的小矩形横着或者竖着去覆盖更大的矩形。
     * 请问用n个2*1的小矩形无重叠地覆盖一个2*n的大矩形，总共有多少种方法？
     比如n=3时，2*3的矩形块有3种覆盖方法：
    * @Param:
    * @Author: chenzhen       
    * @Date: 2020/3/31 0031 
    */

    public static int rectCover(int number) {
        if(number==1){
            return 1;
        }
        if(number==2){
            return 2;
        }
        return rectCover(number-2)+rectCover(number-1);
    }

    @Test
    public void test(){
        int i = rectCover(2);
        System.out.println(i);
    }
}
