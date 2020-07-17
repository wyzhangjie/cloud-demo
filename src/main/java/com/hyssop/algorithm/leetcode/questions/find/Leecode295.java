package com.hyssop.algorithm.leetcode.questions.find;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import static org.springframework.core.io.buffer.DefaultDataBufferFactory.DEFAULT_INITIAL_CAPACITY;

/**
 * @Author jie.zhang
 * @create_time 2020/7/17 10:59
 * @updater
 * @update_time 中位数是有序列表中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。
 * <p>
 * 例如，
 * <p>
 * [2,3,4] 的中位数是 3
 * <p>
 * [2,3] 的中位数是 (2 + 3) / 2 = 2.5
 * <p>
 * 设计一个支持以下两种操作的数据结构：
 * <p>
 * void addNum(int num) - 从数据流中添加一个整数到数据结构中。
 * double findMedian() - 返回目前所有元素的中位数。
 * 示例：
 * <p>
 * addNum(1)
 * addNum(2)
 * findMedian() -> 1.5
 * addNum(3)
 * findMedian() -> 2
 * 进阶:
 * <p>
 * 如果数据流中所有整数都在 0 到 100 范围内，你将如何优化你的算法？
 * 如果数据流中 99% 的整数都在 0 到 100 范围内，你将如何优化你的算法？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-median-from-data-stream
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class Leecode295 {
    //大顶堆，放整个数组中偏小的一半数据
    PriorityQueue<Integer> maxHeap = new PriorityQueue();
    //小顶堆，放整个数组中偏大的那一半数据
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();

    /**
     * initialize your data structure here.
     */
    public void MedianFinder() {
        maxHeap = new PriorityQueue<>(DEFAULT_INITIAL_CAPACITY, (Comparator<Integer>) (o1, o2) -> o2-o1);
                minHeap = new PriorityQueue<>();
    }


    public void addNum(int num) {
        maxHeap.add(num);
        minHeap.add(maxHeap.poll());
        if(maxHeap.size()<minHeap.size()){
            maxHeap.add(minHeap.poll());
        }

    }

    public double findMedian() {
       if(maxHeap.size()>minHeap.size()){
           return maxHeap.peek();
       }else {
           return (maxHeap.peek()+minHeap.peek())/2.0;
       }
    }



    public static void main(String[] args) {
        int[] num = new int[]{1, 2, 3};
        Leecode295 leecode295 = new Leecode295();
        leecode295.MedianFinder();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(DEFAULT_INITIAL_CAPACITY, (Comparator<Integer>) (o1, o2) -> o2-o1);
        for (int i = 0; i < num.length; i++) {

            leecode295.addNum(num[i]);
        }
        System.out.println(leecode295.findMedian());
    }
}