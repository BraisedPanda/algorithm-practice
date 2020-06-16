package com.example.niuketest.sword_finger_offer.practice;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @program: niuke-test
 * @description:

 * @create: 2020-05-06 15:06
 **/
public class test12 {

    /*
       求出1~13的整数中1出现的次数,并算出100~1300的整数中1出现的次数？
     * 为此他特别数了一下1~13中包含1的数字有1、10、11、12、13因此共出现6次,
     * 但是对于后面问题他就没辙了。ACMer希望你们帮帮他,并把问题更加普遍化,
     * 可以很快的求出任意非负整数区间中1出现的次数（从1 到 n 中1出现的次数）。
    */
    public int NumberOf1Between1AndN_Solution(int n) {
        int total = 0;
        for(int i=1;i<=n;i++){
            char[] chars = String.valueOf(i).toCharArray();
            int sum = 0;
            for (char c:
                 chars) {
                if(Integer.parseInt(String.valueOf(c)) == 1){
                    sum++;
                }
            }
            total = total + sum;
        }
        return total;
    }

    @Test
    public void testNumberOf1Between1AndN_Solution(){
        int sum = NumberOf1Between1AndN_Solution(15);
        System.out.println(sum);
    }
    
    
    /* 
       输入一个正整数数组，把数组里所有数字拼接起来排成一个数，
       打印能拼接出的所有数字中最小的一个。例如输入数组{3，32，321}，
       则打印出这三个数字能排成的最小数字为321323。
    */
    public String PrintMinNumber(int [] numbers) {
        if(numbers == null){
            return null;
        }
        List<String> list = new ArrayList<>();
        permutation(numbers,0,list);
        Collections.sort(list);
        return list.get(0);
    }

    public static void permutation(int[] numbers, Integer index, List<String> list){
        if(index == numbers.length-1){
            StringBuilder sb = new StringBuilder();
            for (int i:
                 numbers) {
                sb.append(i);
            }
            String str = sb.toString();
            if(!list.contains(str)){
                list.add(str);
            }
        }else{
            for(int i=index;i<numbers.length;i++){
                swap(numbers,index,i);
                permutation(numbers,index+1,list);
                swap(numbers,index,i);
            }
        }
    }

    private static void swap(int[] numbers, Integer index, int i) {
        int  temp = numbers[index];
        numbers[index] = numbers[i];
        numbers[i] = temp;
    }
    /**
    * @Description:
    * @Param: []
    * @Author: chenzhen
    * @Date: 2020/5/6 0006
    */

        public String PrintMinNumber2(int [] numbers) {
            int n;
            String s="";
            ArrayList<Integer> list= new ArrayList<Integer>();
            n=numbers.length;
            for(int i=0;i<n;i++){
                list.add(numbers[i]);

            }
            Collections.sort(list, new Comparator<Integer>(){

                public int compare(Integer str1,Integer str2){
                    String s1=str1+""+str2;
                    String s2=str2+""+str1;
                    return s1.compareTo(s2);
                }
            });

            for(int j:list){
                s+=j;
            }
            return s;

        }


    @Test
    public void testPrintMinNumber(){
        List<String> list = new ArrayList<>();
        int[] numbers = {12,5,7};
        permutation(numbers,0,list);
        Collections.sort(list);
        System.out.println(list);


    }
    
    /** 
    * @Description: 把只包含质因子2、3和5的数称作丑数（Ugly Number）。
     * 例如6、8都是丑数，但14不是，因为它包含质因子7。 习惯上我们把1当做是第一个丑数。
     * 求按从小到大的顺序的第N个丑数。
    * @Date: 2020/5/9 0009 
    */
    public int GetUglyNumber_Solution(int index) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        int i1 = 0;
        int i2 = 0;
        int i3 = 0;
        while(list.size()<index){
            int min1 = list.get(i1)*2;
            int min2 = list.get(i2)*3;
            int min3 = list.get(i3)*5;
            int min = Math.min(min1,Math.min(min2,min3));
            list.add(min);
            if(min == min1) i1++;
            if(min == min2) i2++;
            if(min == min3) i3++;
        }
        return list.get(list.size()-1);
    }


    /** 
    * @Description: 在一个字符串(0<=字符串长度<=10000，全部由字母组成)中找到第一个只出现一次的字符,
     * 并返回它的位置,如果没有则返回 -1（需要区分大小写）.（从0开始计数）
    * @Date: 2020/5/9 0009 
    */
    public int FirstNotRepeatingChar(String str) {
        char[] chars = str.toCharArray();
        List<Character> list = new ArrayList<>();
        List<Character> repeatList = new ArrayList<>();
        for (char c:
             chars) {
            if(list.contains(c)){
                if(!repeatList.contains(c)){
                    repeatList.add(c);
                }
            }
            if(!repeatList.contains(c)){
                list.add(c);
            }

        }
        list.removeAll(repeatList);
        if(list.size() == 0){
            return -1;
        }
        char c = list.get(0);
        int index = 0;
        for (int i = 0; i < chars.length; i++) {
            if(c == chars[i]){
                index = i;
                break;
            }
        }
        return index;
    }

    @Test
    public void testFirstNotRepeatingChar(){
        String str = "erter";
        FirstNotRepeatingChar(str);
    }


    /**
    * @Description: 在数组中的两个数字，如果前面一个数字大于后面的数字，
     * 则这两个数字组成一个逆序对。
     * 输入一个数组,求出这个数组中的逆序对的总数P。
     * 并将P对1000000007取模的结果输出。 即输出P%1000000007
    */
    public int InversePairs(int [] array) {
        int length = array.length;
        int count = 0;
        for (int i = 0; i <length-1 ; i++) {
            for (int j = i+1; j < length; j++) {
                if(array[i]>array[j]){
                    count++;
                }
            }
        }
        return count%1000000007;
    }

    @Test
    public void testInversePairs(){
        int[] array = {1,2,3,4,5,6,7,9,0,8};
        System.out.println(InversePairs(array));
    }
}
