package com.example.niuketest.sword_finger_offer.practice;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * @program: niuke-test
 * @create: 2020-07-01 10:10
 **/
public class test17 {
    /**
    * @Description: 给定一个数组和滑动窗口的大小，找出所有滑动窗口里数值的最大值。
     * 例如，如果输入数组{2,3,4,2,6,2,5,1}及滑动窗口的大小3，
     * 那么一共存在6个滑动窗口，他们的最大值分别为{4,4,6,6,6,5}；
     * 针对数组{2,3,4,2,6,2,5,1}的滑动窗口有以下6个：
     * {[2,3,4],2,6,2,5,1}， {2,[3,4,2],6,2,5,1}， {2,3,[4,2,6],2,5,1}，
     * {2,3,4,[2,6,2],5,1}，{2,3,4,2,[6,2,5],1}， {2,3,4,2,6,[2,5,1]}。
    * @Date: 2020/7/1 0001
    */
    public ArrayList<Integer> maxInWindows(int [] num, int size) {
        ArrayList<Integer> resultList = new ArrayList<>();
        if(num == null || size==0){
            return resultList;
        }
        for(int i=0;i<=num.length-size;i++){
            int[] numbers = getWindowsData(num,i,i+size);
            int max = getMax(numbers);
            resultList.add(max);
        }
        return resultList;
    }
    // 截取指定范围内的num数组
    // start 0, end 3 ; start 5, end 8
    public int[] getWindowsData(int[] num, int start, int end){
        int[] numbers = new int[end-start];
        for (int i = 0; i < end-start; i++) {
            numbers[i] = num[i+start];
        }
        return numbers;
    }
    // 获取数组中的最大值
    public int getMax(int[] num){
        int max = num[0];
        for (int i = 0; i < num.length; i++) {
            if(num[i]>max){
                max = num[i];
            }
        }
        return max;
    }

    @Test
    public void testMaxInWindows(){
        int[] num = {2,3,4,2,6,2,5,1};
        int size = 0;
        ArrayList<Integer> resultList = maxInWindows(num,size);
        System.out.println(resultList);
    }
    
    
    /** 
    * @Description: 请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。
     * 路径可以从矩阵中的任意一个格子开始，每一步可以在矩阵中向左，向右，向上，向下移动一个格子。
     * 如果一条路径经过了矩阵中的某一个格子，则该路径不能再进入该格子。
     * 例如：
     * a b c e
     * s f c s
     * a d e e
     *  矩阵中包含一条字符串"bcced"的路径，但是矩阵中不包含"abcb"路径，
     *  因为字符串的第一个字符b占据了矩阵中的第一行第二个格子之后，路径不能再次进入该格子。
    * @Date: 2020/7/1 0001 
    */
    // "ABCESFCSADEE",3,4,"ABCB"
    public boolean hasPath(char[] matrix, int rows, int cols, char[] str) {
        // 判断矩阵
        boolean[] flag = new boolean[matrix.length];
        for(int i=0;i<rows;i++){
            for (int j = 0; j < cols; j++) {
                if(judge(matrix,rows,cols,i,j,flag,str,0)){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean judge(char[] matrix, int rows, int cols, int i, int j, boolean[] flag, char[] str, int k) {
        int index = i*cols+j;
        // 如果越界、标志位走过了、两个数组的字符不相等，返回false
        if(i<0 || j<0 || i>=rows || j>=cols || flag[index] == true || str[k]!=matrix[index]){
            return false;
        }
        // 如果所有的路径都匹配，返回true
        if(k == str.length-1){
            return true;
        }
        //走过的路径标志位true
        flag[index] = true;
        // 回溯法判断匹配到点的四周值
        if(judge(matrix,rows,cols,i+1,j,flag,str,k+1)
        || judge(matrix,rows,cols,i-1,j,flag,str,k+1)
        || judge(matrix,rows,cols,i,j-1,flag,str,k+1)
        || judge(matrix,rows,cols,i,j+1,flag,str,k+1)
        ){
            return true;
        }
        // 走到这一步说明没有匹配到，把当前路径重新置false
        flag[index] = false;
        return false;
    }


    // 该方法没跑通
    public boolean hasPath_fail(char[] matrix, int rows, int cols, char[] str) {
        if(matrix ==null || str==null || (rows==0 && cols==0)){
            return false;
        }
         // 1. 构建矩阵
         int height = cols;
         int width= rows;
         char[][] matrixs = createMatrix(matrix,height,width);
         int count=0;
         int xIndex = 0; // 用于记录位置x
         int yIndex = 0; // 用于记录位置y
         boolean flag = false;
         List<String> path = new ArrayList<>();
         for(int i=0;i<str.length;i++){
             char c = str[i];
             for (int x = xIndex; x < height; x++) {
                 for (int y = yIndex; y < width; y++) {
                     if(count==0){ // 如果是查找第一个字符
                         if(matrixs[x][y] == c){
                             count++;
                             xIndex = x;
                             yIndex = y;
                             path.add(x+""+y);
                             break;
                         }
                     }else{ // 查找相邻的字符
                        //如果上方没越界
                         if(x-1>= 0){
                                if(matrixs[x-1][y] == str[i] && !path.contains((x-1)+""+y)){
                                    count++;
                                    xIndex = x-1;
                                    yIndex = y;
                                    path.add((x-1)+""+y);
                                    flag = true;
                                    break;

                                }
                         }
                         //如果下方没越界
                         if(x+1< width){
                             if(matrixs[x+1][y] == str[i] && !path.contains((x+1)+""+y)){
                                 count++;
                                 xIndex = x+1;
                                 yIndex = y;
                                 path.add((x+1)+""+y);
                                 flag = true;
                                 break;

                             }
                         }
                         //如果左方没越界
                         if(y-1> 0){
                             if(matrixs[x][y-1] == str[i] && !path.contains(x+""+(y-1))){
                                 count++;
                                 xIndex = x;
                                 yIndex = y-1;
                                 path.add(x+""+(y-1));
                                 flag = true;
                                 break;

                             }
                         }
                         //如果右方没越界
                         if(y+1< height){
                             if(matrixs[x][y+1] == str[i] && !path.contains(x+""+(y+1))){
                                 count++;
                                 xIndex = x;
                                 yIndex = y+1;
                                 path.add(x+""+(y+1));
                                 flag = true;
                                 break;
                             }
                         }
                         break;
                     }

                 }

             }
         }
         if(count == str.length){return true;}
         return false;
    }
    // 根据一维数组和宽高构建二维数组矩阵
    public char[][] createMatrix(char[] matrix, int width, int height){
        char[][] matrixs = new char[height][width];
        int length = matrix.length;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                matrixs[i][j] = matrix[i*width+j];
            }
        }
        return matrixs;
    }


    @Test
    public void testHasPath(){
        char[] matrix = "ABCESFCSADEE".toCharArray();
        int width = 3;
        int height = 4;
        char[] chars = {'S','E','E'};
        boolean flag = hasPath(matrix,width,height, chars);
        System.out.println(flag);
    }


    /**
    * @Description: 地上有一个m行和n列的方格。一个机器人从坐标0,0的格子开始移动，
     * 每一次只能向左，右，上，下四个方向移动一格，但是不能进入行坐标和列坐标的数位之和大于k的格子。
     * 例如，当k为18时，机器人能够进入方格（35,37），因为3+5+3+7 = 18。
     * 但是，它不能进入方格（35,38），因为3+5+3+8 = 19。请问该机器人能够达到多少个格子？
    * @Date: 2020/7/2 0002
    */
    public int movingCount(int k, int rows, int cols) {
        boolean[][] flag = new boolean[rows][cols];
        return maxMoving(0,0,rows,cols,k,flag);
    }

    // 获得最大值
    private int maxMoving(int x, int y, int rows, int cols, int k, boolean[][] flag) {
        if(x<0 || y<0 || x>=rows || y>= cols || flag[x][y]==true || calculate(x,y)>k){
            return 0;
        }
        flag[x][y] = true;
        return ( maxMoving(x+1,y,rows,cols,k,flag)+maxMoving(x-1,y,rows,cols,k,flag)
                +maxMoving(x,y+1,rows,cols,k,flag)+ maxMoving(x,y-1,rows,cols,k,flag) +1
        );

    }
    public static int calculate(int x, int y){
        char[] charsA = String.valueOf(x).toCharArray();
        char[] charsB = String.valueOf(y).toCharArray();
        int count = 0;
        for (Character c:
             charsA) {
             count += c-'0';
        }
        for (Character c:
                charsB) {
            count += c-'0';
        }
        return count;
    }
    @Test
    public void testMovingCount(){
        int k = 2;
        int rows = 2;
        int cols = 2;
        System.out.println(movingCount(k,rows,cols));
    }



    /**
    * @Description: 给你一根长度为n的绳子，请把绳子剪成整数长的m段（m、n都是整数，n>1并且m>1，m<=n），
     * 每段绳子的长度记为k[1],...,k[m]。请问k[1]x...xk[m]可能的最大乘积是多少？
     * 例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
    * @Date: 2020/7/2 0002
    */
    // 经过数学论证的，具体看牛客网该题网友的答案解析
    public int cutRope(int target) {
        if(target == 0){
            return 0;
        }
        if(target == 2){
            return 1;
        }
        if(target == 3){
            return 2;
        }

        if(target%3==0){ //如果能被3整除
            return (int)Math.pow(3,target/3);
        }else if(target%3==1){
            return 4*(int)Math.pow(3,target/3-1);
        }else{
            return 2*(int)Math.pow(3,target/3);
        }
    }

}
