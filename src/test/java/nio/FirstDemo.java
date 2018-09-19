package nio;

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


            // clear() 方法重设缓冲区，使它可以接受读入的数据。
            // flip() 方法让缓冲区可以将新读入的数据写入另一个通道。
            while (true) {
                // 清空缓冲区
                buffer.clear();
                //从通道读取一个数据到缓冲区
                int r = fcIn.read(buffer);
                //判断是否有从通道读到数据
                if (r == -1) {
                    break;
                }
                //将buffer指针指向头部
                buffer.flip();
                //把缓冲区数据写入通道
                fcOut.write(buffer);
            }

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
