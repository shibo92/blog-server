package test.thread.providerandconsumer.blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 一个简单的生产者-消费者demo
 *
 * @author KevinJom
 */
public class BlockingQueueDemo {
    public static void main(String[] args) {
        new BlockingQueueDemo().go();
    }

    private void go() {
        // 这里简单的说一下BlockingQueue的实现,它基于生产者-消费者模式，其中有两个重要的阻塞方法
        // put()和take()，而这两个方法的实现用到了Lock和Condition，具体实现请参考API
        BlockingQueue<String> queue = new ArrayBlockingQueue<String>(10);
        // 生产者线程,来，生产一些i can play吧，并且要比nike生产的快
        Thread t1 = new Thread(new Producer(queue, 500, "peak"));
        // 第二个生产者线程
        Thread t2 = new Thread(new Producer(queue, 1000, "nike"));
        // 消费者线程
        Thread t3 = new Thread(new Customer(queue));
        t1.start();
        t2.start();
        t3.start();
    }

    private class Producer implements Runnable {
        private BlockingQueue<String> queue;
        private int timeout; // 生产一个产品后暂停的时间
        private String category; // 仅仅起标记产品作用

        public Producer(BlockingQueue<String> queue, int timeout,
                        String category) {
            super();
            this.queue = queue;
            this.timeout = timeout;
            this.category = category;
        }

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    // put()方法也是一个会阻塞的方法，如果队列已满的时候这个方法会一起阻塞直到
                    // 队列中重新出现空间为止
                    queue.put("product " + category);
                    System.out.println("producer put ..." + category);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                try {
                    TimeUnit.MILLISECONDS.sleep(timeout); // 每生产一个产品就暂停timeout毫秒
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class Customer implements Runnable {
        private BlockingQueue<String> queue;

        public Customer(BlockingQueue<String> queue) {
            super();
            this.queue = queue;
        }

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    System.out.println("comsumer got:" + queue.take());
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                try {
                    // 暂停10毫秒，这里主要是为了证明take()是一个阻塞方法，如果 BlockingQueue中
                    // 没有元素，它会一起阻塞直到队列中有元素为止
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }

    }

}
