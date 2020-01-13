package zhjie;

import java.net.ServerSocket;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @Author jie.zhang
 * @create_time 2019/12/2 15:33
 * @updater
 * @update_time
 **/
public class NioTest {
    public static void main(String[] args) throws Exception {

        SocketChannel sc = SocketChannel.open();
        sc.configureBlocking(false); // nonblocking
        if (!sc.isBlocking()) {
            System.out.println("一个不阻塞的通道~");
        }
        sc.configureBlocking(true); // nonblocking
        if (sc.isBlocking()) {
            System.out.println("一个阻塞的通道~");
        }
        test2();
    }

    private static void test2() throws Exception {
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        ServerSocket socket = null;
        Object lockObj = serverChannel.blockingLock();
// have a handle to the lock object, but haven't locked it yet
// may block here until lock is acquired
        synchronized (lockObj){
// This thread now owns the lock; mode can't be changed
            boolean prevState = serverChannel.isBlocking();
            serverChannel.configureBlocking(false);
            serverChannel.accept();
            serverChannel.configureBlocking(prevState);
        }
// lock is now released, mode is allowed to change
        if (socket != null) {
           // doSomethingWithTheSocket(socket);
        }
    }

}