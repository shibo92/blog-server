package thread.semaphor.demo1;


import java.util.concurrent.Semaphore;

class Driver {
    // 控制线程的数目为1，也就是单线程
    private Semaphore semaphore = new Semaphore(1);

    public void driveCar() {
        try {
            // 从信号量中获取一个允许机会
            semaphore.acquire();
            System.out.println(Thread.currentThread().getName() + " start at " + System.currentTimeMillis());
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + " stop at " + System.currentTimeMillis());
            // 释放允许，将占有的信号量归还
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
class Car extends Thread{
    private Driver driver;

    public Car(Driver driver) {
        super();
        this.driver = driver;
    }

    public void run() {
        driver.driveCar();
    }
}
public class Demo1 {
    public static void main(String[] args) {
        Driver driver = new Driver();
        for (int i = 0; i < 5; i++) {
            (new Car(driver)).start();
        }
    }
}
