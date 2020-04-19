package zhjie.entity.jiasi;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;

/**
 * @Author jie.zhang
 * @create_time 2020/1/6 16:56
 * @updater
 * @update_time
 **/
public class DuplicateTest {

    public void test(){

        CharBuffer buffer = CharBuffer.allocate(10);
        char[] chars =  {'A','B','C','D','E','F','G','H','I','J'};
        buffer.put(chars,0,10);
        buffer.position(2).limit(7);
        CharBuffer newBuffer = buffer.slice(); // @1
        buffer.clear();
        System.out.println(newBuffer.get());
        System.out.println(newBuffer.get());
        System.out.println(newBuffer.get());
        System.out.println(newBuffer.get());
        System.out.println(newBuffer.get());
        System.out.println(newBuffer.get());
    }
    public void test1(){

        CharBuffer buffer = CharBuffer.allocate(10);
        char[] chars =  {'A','B','C','D','E','F','G','H','I','J'};
        buffer.put(chars,0,10);
        buffer.position(2).limit(7);
        CharBuffer newBuffer = buffer.duplicate(); // @1
        buffer.clear(); // @2
        System.out.println(newBuffer.get());
        System.out.println(newBuffer.get());
    }

    public void test2(){

        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        System.out.println(byteBuffer.order()); // @1
        System.out.println(ByteOrder.nativeOrder()); // @2
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN); // @3
        System.out.println(byteBuffer.order()); // @4
    }
    public static void main(String[] args) {
        DuplicateTest duplicateTest = new DuplicateTest();
        duplicateTest.test2();
    }
}