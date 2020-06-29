package com.example.niuketest.sword_finger_offer.practice;

import org.junit.Test;

import java.util.ArrayList;

/**
 * @program: niuke-test
 * @description:
 * @author:
 * @create: 2020-04-10 15:55
 **/
public class test08 {

    /**
    * @Description: 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字，
     * 例如，如果输入如下4 X 4矩阵：1   2  3  4
     *                          5   6  7  8
     *                          9  10 11 12
     *                          13 14 15 16
     * 则依次打印出数字1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10.
    * @Param:
    * @Author:
    * @Date: 2020/4/10 0010
    */
    @Test
    public ArrayList<Integer> printMatrix() {
        int[][] matrix = new int[3][1];
        matrix[0][0] = 1;
        matrix[1][0] = 2;
        matrix[2][0] = 3;


        ArrayList<Integer> list = new ArrayList<>();

        int length = matrix[0].length;
        int width = matrix.length;

        int w = width;
        int l = length;
        int count = width*length;
        int total=0;
        while(total!=count){
            //输出上面
            if(total!=count){
                for(int i=length-l;i<=(l-1);i++){
                    System.out.println(matrix[width-w][i]);
                    list.add(matrix[width-w][i]);
                    total++;
                }
            }


            //输出右面
            if(total!=count){
                for(int i=width-w+1;i<=(w-1);i++){
                    System.out.println(matrix[i][l-1]);
                    list.add(matrix[i][l-1]);
                    total++;
                }
            }

            l--;
            //输出下面
            if(total!=count){
                for(int i=l-1;i>=length-l-1;i--){
                    System.out.println(matrix[w-1][i]);
                    list.add(matrix[w-1][i]);
                    total++;
                }
            }

            //输出左面
            if(total!=count){
                for(int i=w-2;i>=width-w+1;i--){
                    System.out.println(matrix[i][length-l-1]);
                    list.add(matrix[i][length-l-1]);
                    total++;
                }
            }

            w--;
        }

        return list;

    }



}
