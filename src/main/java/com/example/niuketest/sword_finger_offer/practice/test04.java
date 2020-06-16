package com.example.niuketest.sword_finger_offer.practice;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: niuke-test
 * @description:
 * @author: chenzhen
 * @create: 2020-03-31 16:20
 **/
public class test04 {
    /**
    * @Description: 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，
     * 使得所有的奇数位于数组的前半部分，所有的偶数位于数组的后半部分，
     * 并保证奇数和奇数，偶数和偶数之间的相对位置不变。
    * @Param:
    * @Author: chenzhen
    * @Date: 2020/3/31 0031
    */
    @Test
    public void reOrderArray() {
        int [] array = {1,2,3,4,5,6,7};
        List<Integer> event = new ArrayList<>();
        List<Integer> odd = new ArrayList<>();
        for (Integer a:
                array) {
            if(a%2 == 0){
                event.add(a);
            }else{
                odd.add(a);
            }
        }
        odd.addAll(event);
        for(int i=0;i<odd.size();i++){
            array[i] = odd.get(i);
            System.out.print(array[i]);
        }

    }
    /** 
    * @Description: 方法2,从头开始，相邻两个数检测，
    * @Param: []
    * @Author: chenzhen       
    * @Date: 2020/3/31 0031 
    */ 
    @Test
    public void reOrderArray2() {
        int [] array = {1,2,3,4,5,6,7};
        for(int i=0;i<array.length;i++){
            for(int j=0;j<array.length-1;j++){
                if( array[j]%2==0 && array[j+1]%2==1 ){
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }
        }
        for (int a:
             array) {
            System.out.print(a);
        }

    }
}
