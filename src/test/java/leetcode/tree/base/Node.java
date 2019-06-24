package leetcode.tree.base;

/**
 * @Description:    树型节点
 * @Author:         zhjie.zhang
 * @CreateDate:     2019/6/24$ 15:42$
 * @UpdateUser:     zhjie.zhang
 * @UpdateDate:     2019/6/24$ 15:42$
 * @Version:        1.0
 */
public class Node<T> {

    T data;
    Node left;
    Node right;

    public Node(T i) {
        this.data = i;
    }


}
