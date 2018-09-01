package test.thread.providerandconsumer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ResourceByLock {
    private String name;
    private int count = 1;
    private boolean hasChicken = false;

    Lock lock = new ReentrantLock();
    // jdk1.5之后加入了Lock类，
    // 一个lock对象可以有多个Condition类
    // Condition类负责对lock进行wait,notify,notifyall
    Condition providerCondition = lock.newCondition();
    Condition consumerCondition = lock.newCondition();

    public void set(String name) {
        lock.lock(); // lock代替synchronized
        try{
            // 用while代替if，解决了线程获取执行权后，是否继续判断标记
            while (hasChicken) {
                try {
                    // this.wait();
                    providerCondition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.name = name;
            System.out.println("生产者...生产---" + name + ++count);
            this.hasChicken = true;
            // 这里用notify的话，t0执行完毕，唤醒另一个provider:t1，t1判断标记后休眠，造成所有线程都wait的局面，即死锁
            // notifyAll解决了死锁问题
            // this.notifyAll();
            consumerCondition.signal();
        } finally {
            lock.unlock();
        }
    }

    public void out() {
        lock.lock();
        try{
            while (!hasChicken) {
                try {
                    // this.wait();
                    consumerCondition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("消费者...消费>>>" + name + count);
            this.hasChicken = false;
            // this.notifyAll();
            providerCondition.signal();
        } finally {
            lock.unlock();
        }
    }
}

class ProviderByLock implements Runnable {
    Resource chicken;

    public ProviderByLock(Resource chicken) {
        this.chicken = chicken;
    }

    @Override
    public void run() {
        while (true) {
            this.chicken.set("烤鸭");
        }
    }

}

class ConsumerByLock implements Runnable {
    Resource chicken;

    public ConsumerByLock(Resource chicken) {
        this.chicken = chicken;
    }

    @Override
    public void run() {
        while (true)
            this.chicken.out();
    }
}

public class ProviderAndConsumerByLock {
    public static void main(String[] args) {
        Resource chicken = new Resource();
        Provider provider = new Provider(chicken);
        Consumer consumer = new Consumer(chicken);
        Thread p0 = new Thread(provider);
        Thread p1 = new Thread(provider);
        Thread c0 = new Thread(consumer);
        Thread c1 = new Thread(consumer);

        p0.start();
        p1.start();
        c0.start();
        c1.start();
    }
}
