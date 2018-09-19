package thread.exercise;

import java.util.concurrent.atomic.AtomicInteger;

public class PrintNumberByCAS {
    static AtomicInteger index = new AtomicInteger(0);
    static int[] arr = new int[]{1, 2, 3, 4, 5, 6};
    static volatile boolean flag = true;

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            while (index.intValue() < arr.length) {
                if (flag) {
                    System.out.println(Thread.currentThread().getName() + "--" + arr[index.getAndAdd(1)]);
                    flag = false;
                }
            }
        });
        Thread t2 = new Thread(() -> {
            while (index.intValue() < arr.length) {
                if (!flag) {
                    System.out.println(Thread.currentThread().getName() + "--" + arr[index.getAndAdd(1)]);
                    flag = true;
                }
            }
        });

        t1.start();
        t2.start();
    }
}
