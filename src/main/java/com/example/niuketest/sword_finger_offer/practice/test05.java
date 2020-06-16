package com.example.niuketest.sword_finger_offer.practice;

import org.junit.Test;

import java.util.Scanner;

/**
 * @program: niuke-test
 * @description:
 * @author: chenzhen
 * @create: 2020-04-07 14:51
 **/
public class test05 {
    /** 
    * @Description: 小易有一个长度为N的正整数数列A = {A[1], A[2], A[3]..., A[N]}。
    牛博士给小易出了一个难题:
    对数列A进行重新排列,使数列A满足所有的A[i] * A[i + 1](1 ≤ i ≤ N - 1)都是4的倍数。
    小易现在需要判断一个数列是否可以重排之后满足牛博士的要求。
    * @Param: 
    * @Author: chenzhen       
    * @Date: 2020/4/7 0007 
    */
  
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int arrCount = scanner.nextInt();
        String[] result = new String[arrCount];
        for(int i=0;i<arrCount;i++){
            int account = scanner.nextInt();
            int four_times = 0;
            int two_times = 0;
            for(int j=0;j<account;j++){
                int num = scanner.nextInt();
                if(num%4 == 0){
                    four_times++;
                }else if(num%2 == 0){
                    two_times++;
                }

            }
            if(2*four_times+1+two_times>=account){
                result[i] = "Yes";
            }else{
                result[i] = "No";
            }
        }
        for(int i=0;i<result.length;i++){
            System.out.println(result[i]);
        }

    }


    @Test
    public void test01(){
        int i ;

    }
}
