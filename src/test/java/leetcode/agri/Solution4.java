package leetcode.agri;

import java.util.Arrays;

/**
 * @Description:    根据一棵树的前序遍历与中序遍历构造二叉树。
 * 从中序与后序遍历序列构造二叉树
 * 中序遍历 inorder = [9,3,15,20,7]
 * 后序遍历 postorder = [9,15,7,20,3]
 * 返回如下的二叉树：
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Author:         zhjie.zhang
 * @CreateDate:     2019/6/28$ 19:34$
 * @UpdateUser:     zhjie.zhang
 * @UpdateDate:     2019/6/28$ 19:34$
 * @Version:        1.0
 */
public class Solution4 {
    public static class TreeNode {
        int val;
        Solution4.TreeNode left;
        Solution4.TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length==0 || inorder.length==0) return null;
        TreeNode root = new TreeNode(preorder[0]);
        int leftLength = 0;
        for (int i=0;i<inorder.length;i++) {
            if (root.val==inorder[i]) {
                leftLength = i;  //左子节点长度
            }
        }

        if (leftLength!=0) {
            root.left = buildTree(Arrays.copyOfRange(preorder, 1, leftLength+1),Arrays.copyOfRange(inorder, 0, leftLength));
        }
        if (inorder.length-leftLength-1!=0) {
            root.right = buildTree(Arrays.copyOfRange(preorder, leftLength+1, preorder.length),Arrays.copyOfRange(inorder, leftLength+1, inorder.length));
        }
        if (leftLength==0 && inorder.length-leftLength-1==0) {
            root.left = buildTree(new int[]{},new int[]{});
            root.left = buildTree(new int[]{},new int[]{});
        }


        return root;
    }

    private static int index ;

    public static TreeNode buildTreeAfter(int[] inorder, int[] postorder) {
        if(inorder.length == 0 && postorder.length == 0){
            return null;
        }
       index = postorder.length-1;
        return  help(inorder,postorder,0,inorder.length-1);
    }

    private static TreeNode help( int[] inorder, int[] postorder, int inBegin,int inEnd) {
        if(inBegin > inEnd) return null;
        int var = postorder[index--];
        for(int i =inBegin ; i<=inEnd; i++){

            if(inorder[i] == var ){
                TreeNode root = new TreeNode(var);
                //后续集合里面从后往前遍历都是右子树数据，所以要先建立右子树。
                root.right = help(inorder,postorder,i+1,inEnd);
                root.left = help(inorder,postorder,inBegin,i-1);
                return root;
            }
        }
        return null;

    }

    static TreeNode  helper1(int[] inorder, int[] postorder, int inStart, int inEnd){
        if(inStart > inEnd) return null;
        int val = postorder[index--];
        for(int i = inStart; i <= inEnd; i++){
            if(inorder[i] == val){
                TreeNode root = new TreeNode(val);
                root.right = helper1(inorder, postorder, i + 1, inEnd);
                root.left = helper1(inorder, postorder, inStart, i - 1);
                return root;
            }
        }
        return null;
    }




    public static void main(String[] args) {
        int[] preorder = new int[]{3,9,20,15,7};
        int[]  inorder = new int []{9,3,15,20,7};
  //      TreeNode result = buildTree(preorder,inorder);
    //    System.out.println(result);
        int[] postorder = new int[]{9,15,7,20,3};
        //解题思路：后续遍历最后一个节点是root，那么从后遍历每一个root，从
        //inorder里面找到他的左右子树递归建树即可。
        TreeNode treeNode = buildTreeAfter(inorder,postorder);



    }
}
