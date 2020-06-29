package com.example.niuketest.sword_finger_offer.practice;

import org.junit.Test;

import java.util.*;

/**
 * @program: niuke-test
 * @description:
 * @author:
 * @create: 2020-04-28 10:59
 **/
public class test11 {
    
    /** 
    * @Description:输入一个字符串,按字典序打印出该字符串中字符的所有排列。
     * 例如输入字符串abc,则打印出由字符a,b,c所能排列出来的所有字符串abc,acb,bac,bca,cab和cba。
    */
    public ArrayList<String> Permutation(String str) {
        System.out.println(str.charAt(0));
        return null;
    }
    @Test
    public void testPermutation(){
        String str = "hello word";
        System.out.println(str.charAt(0));
    }



    /**
    * @Description:求一串数字的全排列
    */ 
    //递归方法
    public  ArrayList<String> permutationRe(String str){
        ArrayList<String> list = new ArrayList<>();
         if(str ==null || str.length() == 0){
                return list;
          }
        permutationStr(str.toCharArray(),0,list);
        Collections.sort(list);
        return list;
    }

    private static void permutationStr(char[] chars, Integer index,ArrayList<String> list) {
        if(index == chars.length-1){ //当index为最后一个字符时，结束排列
            if(!list.contains(String.valueOf(chars))){
                list.add(String.valueOf(chars));
            }
        }else{
            for(int i=index;i<chars.length;i++){
                swap(chars,index,i); //交换chars的index 和 i 位置的字符
                permutationStr(chars,index+1,list);
                swap(chars,index,i); //交换回来
            }
        }

    }

    private static void swap(char[] chars, Integer index, int i) {
        char temp = chars[index];
        chars[index] = chars[i];
        chars[i] = temp;
    }


    @Test
    public  void testPermutationRe(){
        String str = "abc";
        ArrayList<String> list = permutationRe(str);
        System.out.println(list.size());
    }
    
    /** 
    * @Description: 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
     * 例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。由于数字2在数组中出现了5次，
     * 超过数组长度的一半，因此输出2。如果不存在则输出0。
    * @Param:
    * @Author:
    * @Date: 2020/5/6 0006 
    */
    public int MoreThanHalfNum_Solution(int [] array) {
        int length = array.length/2;
        Map<Integer,Integer> map = new HashMap<>();
        for (int a:
             array) {
            if(!map.containsKey(a)){
                map.put(a,1);
            }else{
                if(map.get(a)+1>length){
                    return a;
                }
                map.put(a,map.get(a)+1);
            }
        }
        return 0;

    }
    @Test
    public void testMoreThanHalfNum_Solution (){
        int[] arr = {1,2,3,2,4,2,5,2,3};
        System.out.println(MoreThanHalfNum_Solution(arr));
    }
    
    
    /** 
    * @Description: 输入n个整数，找出其中最小的K个数。
     * 例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4,。
    * @Param:
    * @Author:
    * @Date: 2020/5/6 0006 
    */
    public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        ArrayList<Integer> list = new ArrayList<>();
        if(input == null || k>input.length ){
            return list;
        }

        quickSort(input,0,input.length-1);

        for(int i=0;i<k;i++){
            list.add(input[i]);
        }
        return list;
    }

    private void quickSort(int[] array, int low, int height) {
        if(low>height){
            return;
        }
        int i = low;
        int j = height;
        int temp = array[low];
        while(i<j){
            while(i<j && array[j] >= temp){
                j-- ;
            }
            while(i<j && array[i] <= temp){
                i++;
            }
            //交换i j的位置
            if(i<j){
                int k = array[i];
                array[i] = array[j];
                array[j] = k;
            }
        }
        //把基准位置值与i交换
        array[low] = array[i];
        array[i] = temp;
        quickSort(array,low,i-1);
        quickSort(array,j+1,height);
    }

    @Test
    public void testQuickSort(){
        int[] arr = {1,6,2,9,3,7,5,0};
        quickSort(arr,0,arr.length-1);
        System.out.println(arr);
    }

    /*
    HZ偶尔会拿些专业问题来忽悠那些非计算机专业的同学。
    今天测试组开完会后,他又发话了:在古老的一维模式识别中,常常需要计算连续子向量的最大和,
    当向量全为正数的时候,问题很好解决。但是,如果向量中包含负数,是否应该包含某个负数,
    并期望旁边的正数会弥补它呢？例如:{6,-3,-2,7,-15,1,2,2},连续子向量的最大和为8
    (从第0个开始,到第3个为止)。给一个数组，返回它的最大连续子序列的和，你会不会被他忽悠住？
    (子向量的长度至少是1)
    */
    public int FindGreatestSumOfSubArray(int[] array) {
        List<Integer> list = new ArrayList<>();
        for(int i=0;i<array.length;i++){
            int sum = 0;
            for(int j=i;j<array.length;j++){
                sum = sum+array[j];
                list.add(sum);
            }
        }
        Collections.sort(list);
        return list.get(list.size()-1);

    }
    @Test
    public void testFindGreatestSumOfSubArray(){
        int[] array= {6,-3,-2,7,-15,1,2,2};
        int max = FindGreatestSumOfSubArray(array);
        System.out.println(max);
    }
}
