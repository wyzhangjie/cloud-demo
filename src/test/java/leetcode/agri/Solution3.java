package leetcode.agri;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Description:    给定一个二叉树，找出其最大深度
 * @Author:         zhjie.zhang
 * @CreateDate:     2019/6/28$ 10:09$
 * @UpdateUser:     zhjie.zhang
 * @UpdateDate:     2019/6/28$ 10:09$
 * @Version:        1.0
 */
public class Solution3 {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    //递归实现
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root != null && root.left == null && root.right == null) {
            return 1;
        }

        int left = maxDepth(root.left) + 1;
        int right = maxDepth(root.right) + 1;
        return left > right ? left : right;
    }



    public static int maxDepthOthers(TreeNode root) {
        int level = 0;
        if(root == null){
            return level;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            level++;
            //当前层的节点个数
            int tmp = queue.size();
            //遍历每层节点入队
            while(tmp > 0){
                TreeNode treeNode = queue.poll();
                if(treeNode.left != null){
                    queue.add(treeNode.left);
                }
                if(treeNode.right != null){
                    queue.add(treeNode.right);
                }
                tmp--;
            }
        }
        return level;
    }


    //非递归实现
    public static int maxDepthNotRecureve(TreeNode root) {
        int left = 1;
        int right = 1;
        if(root==null){
            return 0;
        }
        Queue<TreeNode> q = new LinkedList<>();
        ((LinkedList<TreeNode>) q).push(root);
        while(!q.isEmpty()){
            TreeNode tep= ((LinkedList<TreeNode>) q).pop();
            left--;
            right--;
            if(tep.left!=null){
                left+=1;
                ((LinkedList<TreeNode>) q).push(tep.left);
            }
            if(tep.right!=null){
                right+=1;
                ((LinkedList<TreeNode>) q).push(tep.right);
            }
        }
        return left > right ? left : right;
    }

    public static void main(String[] args) {

        TreeNode list1 = new TreeNode(3);
        TreeNode list2 = new TreeNode(9);
        TreeNode list3 = new TreeNode(20);
        TreeNode list4 = new TreeNode(15);
        TreeNode list5 = new TreeNode(7);
        TreeNode list6 = new TreeNode(7);
        list1.left = list2;
        list1.right = list3;
        list3.left = list4;
        list3.right = list5;
        System.out.println(maxDepthOthers(list1));


    }
}
