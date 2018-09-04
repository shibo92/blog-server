package test.thread.providerandconsumer.synchronize;

class Stock {
    private String name;
    // 标记库存是否有内容
    private boolean hasComputer = false;

    public synchronized void putOne(String name) {
        // 若库存中已有内容，则生产线程阻塞等待
        while (hasComputer) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.name = name;
        System.out.println("生产者...生产了 " + name);
        // 更新标记
        this.hasComputer = true;
        // 这里用notify的话，假设p0执行完毕，此时c0,c1都在wait, 同时唤醒另一个provider:p1，
        // p1判断标记后休眠，造成所有线程都wait的局面，即死锁；
        // 因此使用notifyAll解决死锁问题
        this.notifyAll();
    }

    public synchronized void takeOne() {
        // 若库存中没有内容，则消费线程阻塞等待生产完毕后继续
        while (!hasComputer) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("消费者...消费了 " + name);
        this.hasComputer = false;
        this.notifyAll();
    }
}

public class SynchronizedDemo {
    public static void main(String[] args) {
        // 用于通信的库存类
        Stock computer = new Stock();
        // 定义两个生产者和两个消费者
        Thread p1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    computer.putOne("Dell");
                }
            }
        });
        Thread p2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    computer.putOne("Mac");
                }
            }
        });

        Thread c1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    computer.takeOne();
                }
            }
        });
        Thread c2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    computer.takeOne();
                }
            }
        });

        p1.start();
        p2.start();
        c1.start();
        c2.start();
    }
}
