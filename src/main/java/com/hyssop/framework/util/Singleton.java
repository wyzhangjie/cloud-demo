package com.hyssop.framework.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:    单利模式
 * @Author:         zhjie.zhang
 * @CreateDate:     2019/7/29$ 11:26$
 * @UpdateUser:     zhjie.zhang
 * @UpdateDate:     2019/7/29$ 11:26$
 * @Version:        1.0
 */
public class Singleton {
    public List<String> list = null;// list 属性

    private Singleton() {// 构造函数
        list = new ArrayList<String>();
    }

    // 内部类实现
    public static class InnerSingleton {
        private static Singleton instance=new Singleton();// 自行创建实例
    }

    public static Singleton getInstance() {
        return InnerSingleton.instance;// 返回内部类中的静态变量
    }
}
