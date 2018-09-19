package thread.deadlock;

public class Ticket implements Runnable {
    int ticket = 100;
    Object lock = new Object();
    Object lock2 = new Object();
    boolean flag = true;

    @Override
    public void run() {
        if (flag) {
            flag = false;
            System.out.println("flag === " + flag);
            this.sale();
        } else {
            flag = true;
            System.out.println("flag === " + flag);
            this.sale2();
        }
    }

    private void sale() {
        while (true) {
            synchronized (lock) {
                if (ticket > 0) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (lock2) {
                        System.out.println(Thread.currentThread().getName() + " saled ...." + ticket);
                        ticket--;
                    }
                } else {
                    break;
                }
            }
        }
    }

    private void sale2() {
        while (true) {
            synchronized (lock2) {
                if (ticket > 0) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (lock){
                        System.out.println(Thread.currentThread().getName() + " saleddd ...." + ticket);
                        ticket--;
                    }
                } else {
                    break;
                }
            }
        }
    }
}
