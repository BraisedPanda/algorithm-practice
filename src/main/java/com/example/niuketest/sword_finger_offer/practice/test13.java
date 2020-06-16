package com.example.niuketest.sword_finger_offer.practice;

import java.util.Scanner;
import java.util.Stack;

/**
 * @program: niuke-test
 * @description:
 * @author: chenzhen
 * @create: 2020-05-11 10:21
 **/
public class test13 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int count = scanner.nextInt();
        StringBuilder sb = new StringBuilder();
        Stack<StringBuilder> stack = new Stack();
        for(int i=0;i<count;i++){
            int operation = scanner.nextInt();
            if(operation == 1){ //追加字符
                StringBuilder sb1 = new StringBuilder(sb);
                stack.push(sb1);
                sb.append(scanner.next());
            }
            if(operation ==2){ //删除字符
                int del = scanner.nextInt();
                StringBuilder sb2 = new StringBuilder(sb);
                stack.push(sb2);
                sb.delete(sb.length()-del,sb.length());
            }
            if(operation == 3){//打印操作
                int print = scanner.nextInt()-1;
                System.out.println(sb.charAt(print));
            }
            if(operation ==4){//回滚操作
                sb = stack.pop();
            }
        }
    }
}
