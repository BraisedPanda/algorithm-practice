package com.example.niuketest.sword_finger_offer.practice;

import org.junit.Test;

import java.util.Stack;

/**
 * @program: niuke-test
 * @description:
 * @author: chenzhen
 * @create: 2020-04-13 11:33
 **/
public class test09 {
    
    /** 
    * @Description:
     * 定义栈的数据结构，请在该类型中实现一个能够得到栈中所含最小元素的min函数（时间复杂度应为O（1））。
         注意：保证测试中不会当栈为空的时候，对栈调用pop()或者min()或者top()方法。
    * @Param:
    * @Author: chenzhen       
    * @Date: 2020/4/13 0013 
    */
    Stack<Integer> stack = new Stack();

    public void push(int node) {
        stack.push(node);
    }

    public void pop() {
        stack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int min() {
        int minine = top();
        pop();
        while(!stack.isEmpty()){
            if(minine>top()){
                minine = top();
            }
            pop();
        }
        return minine;
    }


    @Test
    public void test(){
        push(3);
        push(2);
        push(1);
        pop();
        System.out.println(min());
    }

}
