package test.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FirstDemo {
    public static void main(String[] args) {
        FileInputStream fin = null;
        try {
            File file = new File("/Users/shibo/tmp/niotest.txt");
            fin = new FileInputStream(file);
            FileChannel fcIn = fin.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);

            File outFile = new File("/Users/shibo/tmp/niotest_out.txt");
            FileOutputStream fout = new FileOutputStream(outFile);
            FileChannel fcOut = fout.getChannel();
            fcIn.read(buffer);
            // clear() 方法重设缓冲区，使它可以接受读入的数据。
            // flip() 方法让缓冲区可以将新读入的数据写入另一个通道。
            buffer.flip();
            fcOut.write(buffer);

            fcOut.close();
            fout.close();
            fcIn.close();
            fin.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
