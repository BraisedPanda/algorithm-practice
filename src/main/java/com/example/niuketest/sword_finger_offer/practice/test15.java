package com.example.niuketest.sword_finger_offer.practice;

import org.junit.Test;
import org.springframework.util.StringUtils;

import javax.swing.plaf.nimbus.AbstractRegionPainter;
import java.util.*;

/**
 * @program: niuke-test
 * @description:
 * @author: chenzhen
 * @create: 2020-05-22 14:41
 **/
public class test15 {
    /**
    * @Description: 小明很喜欢数学,有一天他在做数学作业时,要求计算出9~16的和,
     * 他马上就写出了正确答案是100。但是他并不满足于此,
     * 他在想究竟有多少种连续的正数序列的和为100(至少包括两个数)。
     * 没多久,他就得到另一组连续正数和为100的序列:18,19,20,21,22。
     * 现在把问题交给你,你能不能也很快的找出所有和为S的连续正数序列? Good Luck!
    * @Date: 2020/5/22 0022
    */

    public ArrayList<ArrayList<Integer>> FindContinuousSequence(int sum) {
        ArrayList<ArrayList<Integer>> resultList = new ArrayList<>();

        for (int i = 1; i <sum ; i++) {
            int total = 0;
            for(int j=i;j<sum;j++){
                total = total+j;
                if(total>sum){
                    break;
                }
                if(total == sum){
                    ArrayList<Integer> list = new ArrayList<>();
                    for (int k = i; k <=j; k++) {
                        list.add(k);
                    }
                    resultList.add(list);
                }
            }
        }
        return resultList;
    }

    @Test
    public void testFindContinuousSequence(){
        FindContinuousSequence(3);
    }

    /**
    * @Description: 输入一个递增排序的数组和一个数字S，在数组中查找两个数，
     * 使得他们的和正好是S，如果有多对数字的和等于S，输出两个数的乘积最小的。
    * @Date: 2020/5/22 0022
    */
    public ArrayList<Integer> FindNumbersWithSum(int [] array,int sum) {
        ArrayList<ArrayList<Integer>> resultList = new ArrayList<>();
        for (int i = 0; i <array.length-2; i++) {
            for (int j = i+1; j <= array.length-1; j++) {
                int total = array[i] + array[j];
                if(total == sum){
                    ArrayList<Integer> list = new ArrayList<>();
                    if(array[i]<=array[j]){
                        list.add(array[i]);
                        list.add(array[j]);
                    }else {
                        list.add(array[j]);
                        list.add(array[i]);
                    }
                    resultList.add(list);
                }
                if(total>sum){
                    break;
                }
            }
        }
        if(resultList.size() == 0){
            return null;
        }
        if(resultList.size() == 1){
            return resultList.get(0);
        }else{
            int min = resultList.get(0).get(0)*resultList.get(0).get(1);
            ArrayList<Integer> mintList = resultList.get(0);
            for (ArrayList<Integer> list:
                    resultList) {
                 int a = list.get(0);
                 int b = list.get(1);
                 if(min >= a*b){
                    min = a*b;
                    mintList = list;
                 }
            }
            return mintList;
        }

    }



    @Test
    public void testFindNumbersWithSum(){
        int[] array = {1,2,4,7,11,16};
        ArrayList<Integer> mintList = FindNumbersWithSum(array,10);
        System.out.println(mintList.toString());
    }


    /**
    * @Description: 汇编语言中有一种移位指令叫做循环左移（ROL），现在有个简单的任务，
     * 就是用字符串模拟这个指令的运算结果。对于一个给定的字符序列S，
     * 请你把其循环左移K位后的序列输出。例如，字符序列S=”abcXYZdef”,
     * 要求输出循环左移3位后的结果，即“XYZdefabc”。是不是很简单？OK，搞定它！
    * @Date: 2020/5/23 0023
    */
    public String LeftRotateString(String str,int n) {
        if(n>str.length()){
            return null;
        }
        StringBuilder sb = new StringBuilder(str);
        String end = str.substring(0,n);
        sb.delete(0,n);
        sb.append(end);
        return sb.toString();
    }

    @Test
    public void testLeftRotateString(){
        String str = "abcXYZdef";
        String result = LeftRotateString(str,3);
        System.out.println(result);
    }


    /**
    * @Description: 牛客最近来了一个新员工Fish，每天早晨总是会拿着一本英文杂志，
     * 写些句子在本子上。同事Cat对Fish写的内容颇感兴趣，有一天他向Fish借来翻看，
     * 但却读不懂它的意思。例如，“student. a am I”。后来才意识到，
     * 这家伙原来把句子单词的顺序翻转了，正确的句子应该是“I am a student.”。
     * Cat对一一的翻转这些单词顺序可不在行，你能帮助他么？
    * @Date: 2020/5/23 0023
    */

    public String ReverseSentence(String str) {
        if(str.trim().isEmpty()){
            return str;
        }
        String[] strings = str.split(" ");
        Stack<String> stack = new Stack<>();
        for (String s:
                strings) {
            stack.push(String.valueOf(s));
        }
        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()){
            sb.append(stack.pop());
            sb.append(" ");
        }
        sb.delete(sb.length()-1,sb.length());
        return sb.toString();
    }

    @Test
    public void testReverseSentence(){
        String str = " ";
        System.out.println(ReverseSentence(str));
    }
    
    
    /** 
    * @Description: LL今天心情特别好,因为他去买了一副扑克牌,发现里面居然有2个大王,2个小王(一副牌原本是54张^_^)
     * ...他随机从中抽出了5张牌,想测测自己的手气,看看能不能抽到顺子,如果抽到的话,
     * 他决定去买体育彩票,嘿嘿！！“红心A,黑桃3,小王,大王,方片5”,“Oh My God!”不是顺子.....
     * LL不高兴了,他想了想,决定大\小 王可以看成任何数字,并且A看作1,J为11,Q为12,K为13。
     * 上面的5张牌就可以变成“1,2,3,4,5”(大小王分别看作2和4),“So Lucky!”。
     * LL决定去买体育彩票啦。 现在,要求你使用这幅牌模拟上面的过程,然后告诉我们LL的运气如何， 如果牌能组成顺子就输出true，否则就输出false。为了方便起见,你可以认为大小王是0。
    * @Date: 2020/5/23 0023 
    */

    public boolean isContinuous(int [] numbers) {
        if(numbers.length == 0){
            return false;
        }
        quickSort(numbers,0,numbers.length-1);
        int count = 0;
        int zeroCount=0;
        for(int i=1;i<5;i++){
            if(numbers[i-1]==0){
                zeroCount++;
            }else if(numbers[i]-numbers[i-1]!=1){
                count += numbers[i]-numbers[i-1]-1;
            }
        }
        return count<=zeroCount && count>=0;
    }

    private void quickSort(int[] numbers, int left, int right) {
        if(left>right){
            return;
        }
        int i = left;
        int j = right;
        int temp = numbers[left];
        while(i<j){
            while(i<j && numbers[j]>=temp){
                j--;
            }
            while(i<j && numbers[i]<=temp){
                i++;
            }
            int k = numbers[i];
            numbers[i] = numbers[j];
            numbers[j] = k;
        }
        numbers[left] = numbers[i];
        numbers[i] = temp;
        quickSort(numbers,left,i-1);
        quickSort(numbers,j+1,right);
    }


    @Test
    public void testQuickSort(){
        int[] numbers = {1,0,0,1,0};
        System.out.println(isContinuous(numbers));

    }

    
    /**
    * @Description: 每年六一儿童节, 牛客都会准备一些小礼物去看望孤儿院的小朋友, 今年亦是如此。
     * HF作为牛客的资深元老,自然也准备了一些小游戏。其中,有个游戏是这样的:首先,让小朋友们围成一个大圈。
     * 然后,他随机指定一个数m,让编号为0的小朋友开始报数。每次喊到m-1的那个小朋友要出列唱首歌,
     * 然后可以在礼品箱中任意的挑选礼物,并且不再回到圈中,从他的下一个小朋友开始,继续0...m-1报数....
     * 这样下去....直到剩下最后一个小朋友,可以不用表演,并且拿到牛客名贵的“名侦探柯南”典藏版(名额有限哦!!^_^)。
     * 请你试着想下,哪个小朋友会得到这份礼品呢？(注：小朋友的编号是从0到n-1)
     * 如果没有小朋友，请返回-1
    * @Date: 2020/6/16 0016
    */

    public  int LastRemaining_Solution(int n,int m){
       if(n<1 || m<1) return -1;
       int count=n, step=0, i=-1;
       int[] array = new int[n];
       while(count>0){
           i++;
           if(i >= n) i=0;  // 作循环环
           if(array[i] ==-1)  continue;
           step++;
           if(step == m){
                count--;
                step=0;
                array[i] = -1;
           }
       }
       return i;
    }

    /** 
    * @Description: 此方法作废，每次报数只能从零开始，并不能继续
    * @Param: [n, m]
    * @Author: chenzhen      
    * @Date: 2020/6/16 0016 
    */ 
    public int LastRemaining_Solution2(int n, int m) {
        if(n==0){
            return -1;
        }
        int[][] array = new int[n][2];
        for(int i=0; i<n; i++){
            array[i][0] = 0;
        }
        // int[i][0] 未出局，int[i][1] 已出局
        int count=0;
        while(true){
            if(n<m){
                int a = m % n-1;
                count = remark(array,n,count,a);
                if(count == -1){
                    break;
                }
            }else{
                count = remark(array,n,count,m-1);
                if(count == -1){
                    break;
                }
            }

        }
        for (int i = 0; i <n ; i++) {
            if(array[i][0] == 0){
                return i+1;
            }
        }
        return -1;
    }


    /**
     * 标记
     *
     * @param array 数组
     * @param n     总共的人数
     * @param count 已出局人数
     * @param index 索引
     * @return int
     */
    public int remark(int[][] array,int n,int count,int index){
        if(count == n-1){
            return -1;
        }else{
            if(array[index][0]!=1){
                array[index][0] = 1;
            }else{ //顺延下一位，标志位置1
                int a = index;
                while(array[a][0] == 1){
                    if(a>=n-1){
                        a = (n-1) % a;
                    }else{
                        a++;
                    }
                }
                array[a][0] = 1;
            }

            return count+1;
        }

    }



    @Test
    public void testLastRemainingSolution(){
        System.out.println(LastRemaining_Solution(3,2));
    }
    
    /** 
     @Description: 求1+2+3+...+n，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
     @Date: 2020/6/19 0019
    */

    public int Sum_Solution(int n) {
        int sum = n ;
        boolean flag =  (n>0) && ((sum+=Sum_Solution(n-1))>0);
        return sum;
    }

    @Test
    public void testSum_Solution(){
        System.out.println(6 & 12 <<1);
    }

    /**
    * @Description: 写一个函数，求两个整数之和，要求在函数体内不得使用+、-、*、/四则运算符号。
    * @Date: 2020/6/19 0019
    */
    public int Add(int num1,int num2) {
        while(num2!=0){
            int sum = num1 ^ num2;
            int carry = (num1 & num2)<<1;
            num2 = carry;
            num1 = sum;
        }
        return num1;
    }

    /**
    * @Description: 将一个字符串转换成一个整数，要求不能使用字符串转换整数的库函数。 数值为0或者字符串不是一个合法的数值则返回0
    * @Date: 2020/6/19 0019
    */
    public int StrToInt(String str) {
        if(str == null || str.trim().isEmpty()) return 0;
        char[] array = str.toCharArray();
        Character[] numbers = {'-','+','0','1','2','3','4','5','6','7','8','9'};
        List<Character> characterList = Arrays.asList(numbers);
        int sum = 0;
        if(!characterList.contains(numbers[0])){
            return 0;
        }
        // i为1代表符号位开头，i为0代表是非符号位开头
        int i=0;
        int digit = 1;
        // 作为符号位判断
        int sign =1;
        if(array[0] == '-'){
            sign = -1;
            i=1;
        }
        if(array[0] == '+'){
            sign = 1;
            i = 1;
        }
        for(;i<array.length;i++){
            if(!characterList.contains(array[i])) return 0; //中间碰到任意非numbers内容，直接返回0
            int beishu = (int)Math.pow(10,(array.length-digit-i));
            int shengshu = array[i]-'0';
            sum = beishu*shengshu  + sum;
        }
        return sum*sign;
    }
    @Test
    public void  testStrToInt(){
        char abc = '7';

        System.out.println((abc-'0')*5);
    }



}
