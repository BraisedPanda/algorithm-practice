package com.example.niuketest.sword_finger_offer.practice;

import org.junit.Test;

import java.util.Stack;

/**
 * @program: niuke-test
 * @description:
 * @author:
 * @create: 2020-03-31 11:41
 **/

public class test02 {
    /** 
    * @Description: 求正整数的原码,如果是负数的话，求其补码
    * @Param: []
    * @Author:
    * @Date: 2020/3/31 0031 
    */ 
    @Test
    public void yuanMa(){
        Integer n=12;
        Boolean flag = true;
        if(n<0){
            flag = false;
            n = -1 * n;
        }
        Integer i = n % 2;
        Stack<Integer> stack = new Stack();
        while(n!=0){
            stack.push(i);
            n = n/2;
            i = n%2;
        }
        //首位符号位为0
        int add = stack.size()%4;
        for (int j=0;j<add;j++) {
            stack.push(0);
        }



        Integer.toBinaryString(n);
        StringBuffer sb = new StringBuffer();
        while(stack.size()>0){
            //如果是整数，直接添加
            if(flag == true){
                sb.append(stack.pop());
            }if(flag == false){ //如果是负数，取反
                if(stack.pop() == 0){
                    sb.append(1);
                }else{
                    sb.append(0);
                }
            }

        }
        //负数的话，最后需要加1
        if(flag == false){
           sb =  addOne(sb);
        }
        int count = 0;
        char[] a = sb.toString().toCharArray();
        for (char c:
             a) {
            if(Integer.valueOf(String.valueOf(c))==1){
                count++;
            }
        }
        System.out.println(count);
        System.out.println(sb.toString());
    }

    private StringBuffer addOne(StringBuffer sb) {
        char[] a = sb.toString().toCharArray(); //00110
        int length = a.length;                  //00001
        int[] b = new int[length];
        for (int i=0;i<length-1;i++) {
            b[i] = 0;
        }
        b[length-1] = 1;
        StringBuffer result = new StringBuffer();
        int jia = 0;//加位
        int sum;
        for(int i=length-1;i>=0;i--){
            sum = Integer.valueOf(String.valueOf(a[i]))+b[i]+jia;
            if(sum>=2){
                jia=1;
                result.append(sum%2);
            }else{
                result.append(sum%2);
                jia=0;
            }
        }
        result.reverse();

        return result;
    }

    @Test
    public void NumberOf2(){
        int n =12;
        int count = 0;
        while(n!=0){
            n = n & (n-1);
            count++;
        }
        System.out.println(count);
    }

}
