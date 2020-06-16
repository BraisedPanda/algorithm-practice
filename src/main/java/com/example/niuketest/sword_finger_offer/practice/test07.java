package com.example.niuketest.sword_finger_offer.practice;

/**
 * @program: niuke-test
 * @description:
 * @author: chenzhen
 * @create: 2020-04-10 13:57
 **/


public class test07 {


    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }

    }

    /** 
    * @Description:  输入两棵二叉树A，B，判断B是不是A的子结构。
     * （ps：我们约定空树不是任意一个树的子结构）
    * @Param: 
    * @Author: chenzhen       
    * @Date: 2020/4/10 0010 
    */

    public boolean HasSubtree(TreeNode root1,TreeNode root2) {
        boolean flag = false;
        if(root1==null || root2 == null){
            return false;
        }
        if(!flag){
            flag = isSubtree(root1,root2);
        }
        if(!flag){
            flag = HasSubtree(root1.left,root2);
        }
        if(!flag){
            flag = HasSubtree(root1.right,root2);
        }
        return flag;

    }
    public boolean isSubtree(TreeNode root,TreeNode child){
        //如果child为空，返回true
        if(child == null){
            return true;
        }
        //如果child不为空，但是root为空了，说明不是子树
        if(root == null){
            return false;
        }
        //如果节点值相同了，接着比较各节点的左右子树
        if(root.val == child.val){
            return isSubtree(root.left,child.left) && isSubtree(root.right,child.right);
        }else{//如果节点值不相等，返回false
            return false;
        }
    }



    /** 
    * @Description: 操作给定的二叉树，将其变换为源二叉树的镜像。
     *      也就是调换二叉树的左右子树
    * @Param: [root]
    * @Author: chenzhen       
    * @Date: 2020/4/10 0010 
    */ 
    public void Mirror(TreeNode root) {

        if(root == null){
            return;
        }else {
            TreeNode node = root.left;
            root.left = root.right;
            root.right = node;
        }
        Mirror(root.left);
        Mirror(root.right);
    }

}
