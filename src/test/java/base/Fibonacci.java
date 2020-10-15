package base;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**最后，我们用一张全景图来描述 ForkJoinPool 线程池的内部结构，你可以看到 ForkJoinPool 线程池和其他线程池很多地方都是一样的，但重点区别在于它每个线程都有一个自己的双端队列来存储分裂出来的子任务。ForkJoinPool 非常适合用于递归的场景，例如树的遍历、最优路径搜索等场景。
 * @Author jie.zhang
 * @create_time 2020/7/7 16:39
 * @updater
 * @update_time
 **/
public class Fibonacci extends RecursiveTask<Integer> {

    int n;

    public Fibonacci(int n) {
        this.n = n;
    }

    @Override
    public Integer compute() {
        if (n <= 1) {
            return n;
        }
        Fibonacci f1 = new Fibonacci(n - 1);
        f1.fork();
        Fibonacci f2 = new Fibonacci(n - 2);
        f2.fork();
        return f1.join() + f2.join();
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        for (int i = 0; i < 10; i++) {
            ForkJoinTask task = forkJoinPool.submit(new Fibonacci(i));
            System.out.println(task.get());
        }
    }
}