package com.example.niuketest.sword_finger_offer.review;

import org.junit.Test;

/**
 * @program: 剑指offer题目11~20
 * @description: 温故而知新，可以为师矣
 * @create: 2020-07-03 10:04
 **/
public class review02 {

    /** [11]
     * @Description: 二进制数中1的个数
     * @Param: 输入一个整数，输出该数32位二进制表示中1的个数。其中负数用补码表示。
     * @Date: 2020/7/3 0003
     */
    public int NumberOf1(int n) {
        int count = 0;
        while(n!=0){
            n = n & (n-1);
            count++;
        }
        return count;
    }

    @Test
    public void testAnd(){

        int b = 5;
        System.out.println(Integer.toBinaryString(5).toCharArray());
    }

    /** [12]
    * @Description: 数值的整数次方
    * @Param: 给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方。
     * 保证base和exponent不同时为0
    * @Date: 2020/7/3 0003
    */
    public double Power(double base, int exponent) {
        if(base == 0){
            return 0d;
        }
        if(exponent == 0){
            return 1d;
        }
        double result = 1;
        for (int i = 0; i < Math.abs(exponent); i++) {
            result = (exponent>0?base:1/base) * result;
        }
        return result;
    }
    @Test
    public void  testPower(){
        double base = 2.0;
        int exponent = -3;
        System.out.println(Power(base,exponent));
    }
}
