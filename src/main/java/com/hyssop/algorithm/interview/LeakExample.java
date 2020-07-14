package com.hyssop.algorithm.interview;

import com.sun.management.OperatingSystemMXBean;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpServer;
import lombok.Data;

import java.io.*;
import java.lang.management.ManagementFactory;
import java.net.InetSocketAddress;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * @Author jie.zhang
 * @create_time 2020/7/3 14:27
 * @updater
 * @update_time
 **/
@Data
public class LeakExample {

    private String a;
    /**
     * 构造随机的字符串
     */
    public static String randomString(int strLength) {

        Random rnd = ThreadLocalRandom.current();
        StringBuilder ret = new StringBuilder();
        for (int i = 0; i < strLength; i++) {
            boolean isChar = (rnd.nextInt(2) % 2 == 0);
            if (isChar) {
                int choice = rnd.nextInt(2) % 2 == 0 ? 65 : 97;
                ret.append((char) (choice + rnd.nextInt(26)));
            } else {
                ret.append(rnd.nextInt(10));
            }
        }
        return ret.toString();
    }

    public static int copy(InputStream input, OutputStream output) throws IOException {
        long count = copyLarge(input, output);
        return count > 2147483647L ? -1 : (int) count;
    }

    public static long copyLarge(InputStream input, OutputStream output) throws IOException {
        byte[] buffer = new byte[4096];
        long count = 0L;

        int n;
        for (; -1 != (n = input.read(buffer)); count += (long) n) {
            output.write(buffer, 0, n);
        }

        return count;
    }

    public static String decompress(byte[] input) throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        copy(new GZIPInputStream(new ByteArrayInputStream(input)), out);
        return new String(out.toByteArray());
    }

    public static byte[] compress(String str) throws Exception {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        GZIPOutputStream gzip = new GZIPOutputStream(bos);

        try {
            gzip.write(str.getBytes());
            gzip.finish();
            byte[] b = bos.toByteArray();
            return b;
        } finally {
            try {
                gzip.close();
            } catch (Exception ex) {
            }
            try {
                bos.close();
            } catch (Exception ex) {
            }
        }
    }


    private static OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

    public static int memoryLoad() {
        double totalvirtualMemory = osmxb.getTotalPhysicalMemorySize();
        double freePhysicalMemorySize = osmxb.getFreePhysicalMemorySize();

        double value = freePhysicalMemorySize / totalvirtualMemory;
        int percentMemoryLoad = (int) ((1 - value) * 100);
        return percentMemoryLoad;
    }


    private static volatile int RADIO = 60;

    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8888), 0);
        HttpContext context = server.createContext("/");
        context.setHandler(exchange -> {
            try {
                RADIO = 85;
                String response = "OK!";
                exchange.sendResponseHeaders(200, response.getBytes().length);
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            } catch (Exception ex) {
            }
        });
        server.start();


        //1kb
        int BLOCK_SIZE = 1024;
        String str = randomString(BLOCK_SIZE / Byte.SIZE);
        byte[] bytes = compress(str);
        for (; ; ) {
            int percent = memoryLoad();
            if (percent > RADIO) {
                Thread.sleep(1000);
            } else {
                decompress(bytes);
                Thread.sleep(1);
            }

        }
    }
}