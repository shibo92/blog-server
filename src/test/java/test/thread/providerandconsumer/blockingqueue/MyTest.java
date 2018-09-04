package test.thread.providerandconsumer.blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

class Producer implements Runnable {
    // 库存队列
    private BlockingQueue<String> stock;
    // 生产/消费延迟
    private int timeOut;
    private String name;

    public Producer(BlockingQueue<String> stock, int timeout, String name) {
        this.stock = stock;
        this.timeOut = timeout;
        this.name = name;
    }

    @Override
    public void run() {
        while (true) {
            try {
                stock.put(name);
                System.out.println(name + " 正在生产数据" + " -- 库存剩余：" + stock.size());
                TimeUnit.MILLISECONDS.sleep(timeOut);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Consumer implements Runnable {
    // 库存队列
    private BlockingQueue<String> stock;
    private String consumerName;

    public Consumer(BlockingQueue<String> stock, String name) {
        this.stock = stock;
        this.consumerName = name;
    }

    @Override
    public void run() {
        while (true) {
            try {
                // 从库存消费一台电脑
                String takeName = stock.take();
                System.out.println(consumerName + " 正在消费数据：" + takeName + " -- 库存剩余：" + stock.size());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class MyTest {
    public static void main(String[] args) {
        // 定义库存数为10
        BlockingQueue<String> stock = new ArrayBlockingQueue<>(10);

        Thread p1 = new Thread(new Producer(stock, 500, "Mac"));
        Thread p2 = new Thread(new Producer(stock, 500, "Dell"));

        Thread c1 = new Thread(new Consumer(stock,"zhangsan"));
        Thread c2 = new Thread(new Consumer(stock, "李四"));

        p1.start();
        p2.start();
        c1.start();
        c2.start();

    }
}
