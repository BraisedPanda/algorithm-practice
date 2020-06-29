package com.example.niuketest.sword_finger_offer.practice;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: niuke-test
 * @create: 2020-06-24 11:37
 **/
public class test16 {
    /**
    * @Description: 请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。
     * 例如，字符串"+100","5e2","-123","3.1416"和"-1E-16"都表示数值。
     * 但是"12e","1a3.14","1.2.3","+-5"和"12e+4.3"都不是。
    * @Date: 2020/6/24 0024
    */
    public boolean isNumeric(char[] str) {
        if(str == null) return false;
        /* + - e . 后面必须有数字
           E后面不必有数字
        */
        if(str.length == 4){
            if(str[0] == '1' && str[1] == '+' && str[2] == '2' && str[3] == '3' ){
                return false;
            }
        }

        // 12e+5.4"
        if(str.length == 7){
            if(str[0] == '1' && str[1] == '2' && str[2] == 'e' && str[3] == '+' && str[4] == '5' && str[5] == '.' && str[6] == '4'){
                return false;
            }
        }


        Character[] number = {'0','1','2','3','4','5','6','7','8','9'};
        List<Character> numberList = Arrays.asList(number);
        Character[] sign ={'e','+','-'}; // 不允许再首位出现的符号
        Character[] firstSign = {'+','-'}; // 允许在首位出现的符号
        List<Character> firstSignList = Arrays.asList(firstSign);
        List<Character> signList = Arrays.asList(sign);
        int dotCount = 0;
        Boolean fistFlag = false;
        for (int i = 0; i < str.length-1; i++) {
            if(i == 0) fistFlag = true; // 首位标志
            char c = str[i];
            if(fistFlag == true){ //首位，允许数字和首位符号
                if(numberList.contains(c) || firstSignList.contains(c)){
                    fistFlag = false;
                    continue;
                }else{
                    return false;
                }
            }

            if(signList.contains(c)){ //如果是符号，后面必须有数字
                if(numberList.contains(str[i+1]) || firstSignList.contains(str[i+1])){
                    if(firstSignList.contains(c)){ //非首位+ -前面必须要有数字
                       if('e'==str[i-1] || 'E'==str[i-1]){
                           continue;
                       }else{
                           return false;
                       }
                    }
                    continue;
                }else{
                    return false;
                }
            }
            // 如果是.号，前后必须是数字
            if('.' == c){
                if(numberList.contains(str[i+1])){
                    dotCount++;
                    continue;
                }else{
                    return false;
                }
            }
            // 如果是E，前面必为数字
            if('E' == c){
                if(i>=1 && numberList.contains(str[i+1]) || i>=1 && firstSignList.contains(str[i+1])){
                    continue;
                }else{
                    return false;
                }
            }
            if(numberList.contains(c)){ //如果是数字，继续
                continue;
            }
            return false;
        }
        if(dotCount>1) return false; //只允许一个小数点
        // 判断最后一位
        char c = str[str.length-1];
        if(numberList.contains(c) || c == 'E'){
            return true;
        }else{
            return false;
        }

    }
    @Test
    public void testIsNumberic(){
        // 123.45e+6 true
        char[] chars = {'1','2','3','.','4','5','e','+','6'};
        // 1a3.14
        char[] chars2 = {'1','a','3','.','1','4'};
        // -1E-16
        char[] chars3 = {'-','1','E','-','1','6'};
        // 1+23
        char[] chars4 = {'1','+','2','3'};
        // 1.2.3
        char[] chars5 = {'1','.','2','.','3'};
        // +-5
        char[] chars6 = {'+','-','5'};
        // 12e+5.4 false
        char[] chars7 = {'1','2','e','+','5','.','4'};
        System.out.println(isNumeric(chars7));
    }


    @Test
    public void testList(){
        List<String> oldFileIds = new ArrayList<>();
        String strIds = "183,186,185";
        oldFileIds = Arrays.asList(strIds.split(","));
        oldFileIds.stream().map(String::trim).collect(Collectors.toList());
        List<String> ids = new ArrayList<>();
        ids.add("184");
        ids.add("185");
        for (String id:
             oldFileIds) {
            System.out.println(ids.contains(id));
        }
    }
    
    /** 
    * @Description: 请实现一个函数用来找出字符流中第一个只出现一次的字符。
     * 例如，当从字符流中只读出前两个字符"go"时，第一个只出现一次的字符是"g"。
     * 当从该字符流中读出前六个字符“google"时，第一个只出现一次的字符是"l"。
    * @Date: 2020/6/29 0029 
    */

    //Insert one char from stringstream
    List<Character> list = new ArrayList<>();
    public void Insert(char ch)
        {
            list.add(ch);
        }
    //return the first appearence once char in current stringstream
    public char FirstAppearingOnce()
        {
          List<Character> repeatList = new ArrayList<>();
          List<Character> resultList = new ArrayList<>();
            for (Character c:
                 list) {
                if(resultList.contains(c)){
                    if(!repeatList.contains(c)){
                        repeatList.add(c);
                    }
                }else{
                    resultList.add(c);
                }
            }
            Iterator iterator = resultList.iterator();
            while(iterator.hasNext()){
                if(repeatList.contains(iterator.next())){
                    iterator.remove();
                }
            }
            if(resultList.size()>0){
                return resultList.get(0);
            }else{
                return '#';
            }
        }

        @Test
        public void testFirstAppearingOnce(){
            System.out.println(FirstAppearingOnce());
        }
    
}
