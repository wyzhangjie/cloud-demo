package base;

import java.util.HashMap;
import java.util.Map;

/**一共有哪 3 种典型的线程安全问题呢？

 运行结果错误；
 发布和初始化导致线程安全问题；
 活跃性问题。
 * @Author jie.zhang
 * @create_time 2020/7/7 16:26
 * @updater
 * @update_time
 **/
public class WrongInit {
    private Map<Integer, String> students;

    public WrongInit() {
        new Thread(() -> {
            students = new HashMap<>();
            students.put(1, "王小美");
            students.put(2, "钱二宝");
            students.put(3, "周三");
            students.put(4, "赵四");
        }).start();
    }

    public Map<Integer, String> getStudents() {
        return students;
    }

    public static void main(String[] args) throws InterruptedException {
        WrongInit multiThreadsError6 = new WrongInit();
        System.out.println(multiThreadsError6.getStudents().get(1));

    }
}