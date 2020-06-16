package com.example.niuketest.sword_finger_offer.practice;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Stack;

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
        quickSort(numbers,0,numbers.length-1);

        return false;
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
        int[] numbers = {4,7,1,0,2,9,7};
        quickSort(numbers,0,numbers.length-1);
        System.out.println(666);
    }

}
