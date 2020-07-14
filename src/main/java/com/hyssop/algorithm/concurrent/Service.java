package com.hyssop.algorithm.concurrent;

/**
 * @Description:    ThreadLocal无法在父子线程中间传递参数
 * @Author:         zhjie.zhang
 * @CreateDate:     2019/7/29$ 13:25$
 * @UpdateUser:     zhjie.zhang
 * @UpdateDate:     2019/7/29$ 13:25$
 * @Version:        1.0
 */
public class Service {
    private static ThreadLocal<Integer> requestIdThreadLocal = new ThreadLocal<>();
    public static void main(String[] args) {
        Integer reqId = new Integer(5);
        Service a = new Service();
        a.setRequestId(reqId);
    }

    public void setRequestId(Integer requestId) {
        requestIdThreadLocal.set(requestId);
        doBussiness();
    }

    public void doBussiness() {
        System.out.println("首先打印requestId:" + requestIdThreadLocal.get());
        (new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("子线程启动");
                System.out.println("在子线程中访问requestId:" + requestIdThreadLocal.get());
            }
        })).start();
    }
}
