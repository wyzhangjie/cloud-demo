package leetcode.tree;

import leetcode.tree.base.Node;

public class PathSum {
    int pathSum(Node<Integer> root, int sum) {
    if(root==null) return 0;
    //从这个点下去有几个？
    int rootNum = getNum(root,sum);
    //从这个点左面那个下去有几个？
    int leftNum = pathSum(root.left,sum);
    //从这个点右面下去有几个
    int rightNum = pathSum(root.right,sum);
    //总共的个数= 这个点下去个数+左面点下去个数+右面点下去个数
    return rootNum + leftNum +rightNum;

    }

    private int getNum(Node<Integer> root, int sum) {
        //求root点下去有几个和为sum的路径
        if(root==null) return 0;
        if(root.data==sum) return 1;
        int leftNum = getNum(root.left,sum-root.data);
        int rightNum = getNum(root.right,sum-root.data);
        return leftNum + rightNum;
    }

    public static void main(String[] args) {
        Node<Integer> a = new Node<>(8);
        Node<Integer> b = new Node<>(3);
        Node<Integer> c = new Node<>(5);
        Node<Integer> d = new Node<>(3);
        a.left=b;
        a.right=c;
        c.left=d;
        PathSum pathSum = new PathSum();
        System.out.println(pathSum.pathSum(a,8));
    }
}
