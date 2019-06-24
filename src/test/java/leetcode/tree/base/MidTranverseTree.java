package leetcode.tree.base;

/**
 * @Description:    java类作用描述
 * @Author:         zhjie.zhang
 * @CreateDate:     2019/6/24$ 15:44$
 * @UpdateUser:     zhjie.zhang
 * @UpdateDate:     2019/6/24$ 15:44$
 * @Version:        1.0
 */
public class MidTranverseTree {

    /**
    * 中序遍历
    * */
    public static void getMidTranversTree(Node head){
        if (head.left!=null){
            getMidTranversTree(head.left);
        }
        System.out.println(head.data);
        if(head.right!=null){
            getMidTranversTree(head.right);
        }

    }
    /**
    * 后续变量的递归实现
    * */
    public static void getAfterTranversTree(Node head){
        if(head!=null){

            getAfterTranversTree(head.left);
            getAfterTranversTree(head.right);
            System.out.println(head.data);
        }

    }

    /**
     * 前序变量的递归实现
     * */
    public static void getBeforeTranversTree(Node head){
        if(head!=null){
            System.out.println(head.data);
            getBeforeTranversTree(head.left);
            getBeforeTranversTree(head.right);
        }


    }
    public static void main(String[] args) {
        Node t = new Node(1);
        Node left1 = new Node(2);
        Node right1 = new Node(3);
        Node left2 = new Node(4);
        Node right2 = new Node(5);
        t.left = left1;
        t.right = right1;
        left1.left = left2;
        left1.right = right2;
        right1.left = null;
        right1.right = null;
        left2.left = null;
        left2.right = null;
        right2.left = null;
        right2.right = null;
     //   getMidTranversTree(t);
       // getBeforeTranversTree(t);
        getAfterTranversTree(t);

    }
}
