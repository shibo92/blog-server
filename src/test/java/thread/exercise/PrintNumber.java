package thread.exercise;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 两个线程交替输出数组奇数偶数位置的数字
 */
class Resource {
    boolean t1IsRun = false;
    int[] arr = new int[]{1, 2, 3, 4, 5, 6};
    Lock lock = new ReentrantLock();
    Condition p1Condition = lock.newCondition();
    Condition p2Condition = lock.newCondition();

    public void print1() {
        lock.lock();
        try {
            for (int i = 0; i < arr.length; i += 2) {
                System.out.println(Thread.currentThread().getName() + " -- "+arr[i]);
                t1IsRun = true;
                p2Condition.signal();
                p1Condition.await();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print2() {
        lock.lock();
        try {
            if(!t1IsRun) {
                p1Condition.signal();
                p2Condition.await();
            }
            for (int i = 1; i < arr.length; i += 2) {
                System.out.println(Thread.currentThread().getName() + " -- "+arr[i]);
                p1Condition.signal();
                p2Condition.await();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

public class PrintNumber {
    public static void main(String[] args) {
        final Resource r = new Resource();
        Thread t1 = new Thread(r::print1);
        Thread t2 = new Thread(r::print2);
        t1.start();
        t2.start();
    }
}
