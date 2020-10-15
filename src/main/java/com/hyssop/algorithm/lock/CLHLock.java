package com.hyssop.algorithm.lock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 2.1、加锁逻辑
 * <p>
 *  1、获取当前线程的锁节点，如果为空，则进行初始化；
 * <p>
 *  2、同步方法获取链表的尾节点，并将当前节点置为尾节点，此时原来的尾节点为当前节点的前置节点。
 * <p>
 *  3、如果尾节点为空，表示当前节点是第一个节点，直接加锁成功。
 * <p>
 *  4、如果尾节点不为空，则基于前置节点的锁值（locked==true）进行自旋，直到前置节点的锁值变为false。
 * <p>
 * 2.2、解锁逻辑
 * <p>
 *  1、获取当前线程对应的锁节点，如果节点为空或者锁值为false，则无需解锁，直接返回；
 * <p>
 *  2、同步方法为尾节点赋空值，赋值不成功表示当前节点不是尾节点，则需要将当前节点的locked=false解锁节点。如果当前节点是尾节点，则无需为该节点设置。
 * ————————————————
 * 版权声明：本文为CSDN博主「wei906」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/chenwiehuang/article/details/86082606
 *
 * @Author jie.zhang
 * @create_time 2019/12/20 10:55
 * @updater
 * @update_time
 **/
public class CLHLock {
    private final AtomicReference<Node> tail;
    private final ThreadLocal<Node> myNode;
    private final ThreadLocal<Node> myPred;

    public CLHLock() {
        tail = new AtomicReference<>();
        myNode = ThreadLocal.withInitial(() -> new Node());
        myPred = ThreadLocal.withInitial(() -> null);
    }

    public void lock() {
        Node node = myNode.get();
        node.locked = true;
        //返回旧的节点
        Node preNode = tail.getAndSet(node);
        myNode.set(preNode);
        while (preNode.locked) {
        }
    }

    public void unLock() {
        Node node = myNode.get();
        node.locked = false;
        myNode.set(myPred.get());
    }

    static class Node {
        volatile boolean locked = false;
    }
}
