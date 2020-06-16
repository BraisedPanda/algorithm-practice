package com.example.niuketest.sword_finger_offer.practice;

import org.junit.Test;

/**
 * @program: niuke-test
 * @description:
 * @author: chenzhen
 * @create: 2020-03-31 15:53
 **/
public class test03 {
    /** 
    * @Description: 给定一个double类型的浮点数base和int类型的整数exponent。
     * 求base的exponent次方。保证base和exponent不同时为0
    * @Param: [base, exponent]
    * @Author: chenzhen       
    * @Date: 2020/3/31 0031 
    */ 
    @Test
    public double Power(double base, int exponent) {

        double result = 1;
        boolean flag = true;
        if(exponent<0){
            flag = false;
            exponent = -1 * exponent;
        }
        if(base == 0){
            return 0;
        }
        if(exponent == 0){
            return 1;
        }

        for(int i=0;i<exponent;i++){
            result = result * base;
        }
        if(flag == false){
            result = 1 / result;
        }
        return result;
    }
}
