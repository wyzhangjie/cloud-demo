package java8function;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @Description:    java类作用描述
 * @Author:         zhjie.zhang
 * @CreateDate:     2019/7/14$ 10:13$
 * @UpdateUser:     zhjie.zhang
 * @UpdateDate:     2019/7/14$ 10:13$
 * @Version:        1.0
 */
public class MyTest {

    interface Task{
        public void execute();
    }

    public static void doSomething(Runnable r){ r.run();}
    public static void doSomething(Task a){ a.execute();}
    int a =10;
    public static void myMethod(){
        doSomething((Task) () -> System.out.println("Danger danger!!"));
        Runnable runnable = () -> {
            int a = 2;
            System.out.println(a);
        };
        new Thread(runnable).start();

    }

    public static void main(String[] args) throws Exception{
      //  myMethod();
        String oneLine = processFile((BufferedReader b) ->b.readLine());
        String twoLine = processFile((BufferedReader b) ->b.readLine()+b.readLine());
    }

    public static String processFile(BufferReaderProcessor p) throws Exception{
        try( BufferedReader br = new BufferedReader(new FileReader("java8function/data.txt"))){
            return p.process(br);
        }
    }

    @FunctionalInterface
    public interface BufferReaderProcessor{
        String process(BufferedReader b) throws IOException;
    }
}
