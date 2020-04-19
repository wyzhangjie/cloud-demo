package zhjie;

import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

/**
 * @Author jie.zhang
 * @create_time 2019/12/2 16:08
 * @updater
 * @update_time
 **/
public class ConnectAsync {
    public static void main(String[] argv) throws Exception {
        String host = "127.0.0.1";
        int port = 8090;

        InetSocketAddress addr = new InetSocketAddress(host, port);
        SocketChannel sc = SocketChannel.open();
        sc.configureBlocking(false);
        System.out.println("initiating connection");
        sc.connect(addr);
        while (!sc.finishConnect()) {
            doSomethingUseful();
        }
        System.out.println("connection established");
// Do something with the connected socket
// The SocketChannel is still nonblocking
        sc.close();
    }

    private static void doSomethingUseful() {
        System.out.println("doing something useless");
    }
}
