package algorithm.wrong100;

import com.hyssop.framework.exception.ArgumentsException;
import jodd.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
public class Wrong15 {
    //异常处理
    //统一异常处理（不考虑业务逻辑的异常处理手段）

    /**
     * Repository 层出现异常或许可以忽略，或许可以降级，或许需要转化为一个友好的异常。如果一律捕获异常仅记录日志，很可能业务逻辑已经出错，而用户和程序本身完全感知不到。Service 层往往涉及数据库事务，出现异常同样不适合捕获，否则事务无法自动回滚。此外 Service 层涉及业务逻辑，有些业务逻辑执行中遇到业务异常，可能需要在异常后转入分支业务流程。如果业务异常都被框架捕获了，业务功能就会不正常。如果下层异常上升到 Controller 层还是无法处理的话，Controller 层往往会给予用户友好提示，或是根据每一个 API 的异常表返回指定的异常类型，同样无法对所有异常一视同仁。
     * */
    /**
     * Service 层往往涉及数据库事务，出现异常同样不适合捕获，否则事务无法自动回滚。此外 Service 层涉及业务逻辑，有些业务逻辑执行中遇到业务异常，可能需要在异常后转入分支业务流程。如果业务异常都被框架捕获了，业务功能就会不正常。
     * */
    /**
     * 如果下层异常上升到 Controller 层还是无法处理的话，Controller 层往往会给予用户友好提示，或是根据每一个 API 的异常表返回指定的异常类型，同样无法对所有异常一视同仁。
     * */

    /**
     * 第二个错，捕获了异常后直接生吞。
     * */

    /**
     * 第三个错，丢弃异常的原始信息。一定要打印打印
     * */

    /**
     * 第四个错，抛出异常时不指定任何消息。
     * */
    /**
     * 总之，如果你捕获了异常打算处理的话，除了通过日志正确记录异常原始信息外，通常还有三种处理模式：转换，即转换新的异常抛出。对于新抛出的异常，最好具有特定的分类和明确的异常消息，而不是随便抛一个无关或没有任何信息的异常，并最好通过 cause 关联老异常。重试，即重试之前的操作。比如远程调用服务端过载超时的情况，盲目重试会让问题更严重，需要考虑当前情况是否适合重试。恢复，即尝试进行降级处理，或使用默认值来替代原始数据。以上，就是通过 catch 捕获处理异常的一些最佳实践。小心 finally 中的异常
     * */
    /**
     * 不要讲finally的错误覆盖try中的异常。一个方法只能抛出一个异常，如果finally里面的异常没有将try中的异常提现出来，就会出现偶现问题难以排查
     * */

    @GetMapping("right2")
    public void right2() throws Exception {
        Exception e = null;
        try {
            log.info("try");
            throw new RuntimeException("try");
        } catch (Exception ex) {
            e = ex;
        } finally {
            log.info("finally");
            try {
                throw new RuntimeException("finally");
            } catch (Exception ex) {
                if (e!= null) {
                    e.addSuppressed(ex);
                } else {
                    e = ex;
                }
            }
        }
        throw e;
    }

    /**
     * 文件方面一定要用try-with-resource这种方式，不会让finnally里面的异常覆盖了try里面的异常
     * */

    public class TestResource implements AutoCloseable {
        public void read() throws Exception{
            throw new Exception("read error");
        }
        @Override
        public void close() throws Exception {
            throw new Exception("close error");
        }
    }

    @GetMapping("useresourcewrong")
    public void useresourcewrong() throws Exception {
        TestResource testResource = new TestResource();
        try {
            testResource.read();
        } finally {
            testResource.close();
        }
    }


    @GetMapping("useresourceright")
    public void useresourceright() throws Exception {
        try (TestResource testResource = new TestResource()){
            testResource.read();
        }
    }

    /**
     * 千万别把异常定义为静态变量
     * */

    /**
     * 提交线程池的任务出了异常会怎么样？
     * 默认的线程池异常处理器是要将这个抛异常的线程结束。如果很多异常被抛出，线程池的池化就会变得没有意义。
     * @param   t   the thread that is about to exit.
     *      * @param   e   the uncaught exception.
     *      * @since   JDK1.0
     * public void uncaughtException(Thread t, Throwable e)
     *  修复方式有 2 步：以 execute 方法提交到线程池的异步任务，最好在任务内部做好异常处理；设置自定义的异常处理程序作为保底，比如在声明线程池时自定义线程池的未捕获异常处理程序：
     *              或者设置全局的默认未捕获异常处理程序：
     *
     * */

    static {
        Thread.setDefaultUncaughtExceptionHandler((thread, throwable)-> log.error("Thread {} got exception", thread, throwable));
    }
    public void theadExceptionRight(){

        new ThreadFactoryBuilder()
                .setNameFormat("prefix"+"%d")
                .setUncaughtExceptionHandler((thread, throwable)-> log.error("ThreadPool {} got exception", thread, throwable))
                .get();
    }
    /**
     * 对于future任务，错误只有在get结果的时候获得。
     * */

}
