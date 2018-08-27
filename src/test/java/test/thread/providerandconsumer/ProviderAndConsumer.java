package test.thread.providerandconsumer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Resource {
    private String name;
    private int count = 1;
    private boolean hasChicken = false;

    public synchronized void set(String name) {
        while (hasChicken) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.name = name;
        // this.count ++ ;
        System.out.println("生产者...生产---" + name + ++count);
        this.hasChicken = true;
        this.notifyAll();
    }

    public synchronized void out() {
        while (!hasChicken) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("消费者...消费>>>" + name + count);
        this.hasChicken = false;
        this.notifyAll();
    }
}

class Provider implements Runnable {
    Resource chicken;

    public Provider(Resource chicken) {
        this.chicken = chicken;
    }

    @Override
    public void run() {
        while (true) {
            this.chicken.set("烤鸭");
        }
    }

}

class Consumer implements Runnable {
    Resource chicken;

    public Consumer(Resource chicken) {
        this.chicken = chicken;
    }

    @Override
    public void run() {
        while (true)
            this.chicken.out();
    }
}

public class ProviderAndConsumer {
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
