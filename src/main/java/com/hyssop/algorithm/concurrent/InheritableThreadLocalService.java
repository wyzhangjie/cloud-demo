package com.hyssop.algorithm.concurrent;

/**
 * @Description:    java类作用描述
 * @Author:         zhjie.zhang
 * @CreateDate:     2019/7/29$ 13:31$
 * @UpdateUser:     zhjie.zhang
 * @UpdateDate:     2019/7/29$ 13:31$
 * @Version:        1.0
 */
public class InheritableThreadLocalService {
    private static InheritableThreadLocal<Integer> requestIdThreadLocal = new InheritableThreadLocal<>();
    public static void main(String[] args) {
        Integer reqId = new Integer(5);
        InheritableThreadLocalService a = new InheritableThreadLocalService();
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
