package zhjie;

import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * @Author jie.zhang
 * @create_time 2019/11/30 10:50
 * @updater
 * @update_time
 **/
public class TestFile {
    public static void main(String[] args) throws Exception {
        RandomAccessFile randomAccessFile = new RandomAccessFile("filename", "r");
// Set the file position
        randomAccessFile.seek(1000);
// Create a channel from the file
        FileChannel fileChannel = randomAccessFile.getChannel();
// This will print "1000"
        System.out.println("file pos: " + fileChannel.position());
// Change the position using the RandomAccessFile object
        randomAccessFile.seek(500);
        // This will print "500"
        System.out.println("file pos: " + fileChannel.position());
// Change the position using the FileChannel object
        fileChannel.position(200);
// This will print "200"
        System.out.println("file pos: " + randomAccessFile.getFilePointer());
    }
}