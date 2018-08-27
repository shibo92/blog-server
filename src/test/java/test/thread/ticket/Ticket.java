package test.thread.ticket;

public class Ticket implements Runnable {
    int ticket = 100;
    Object lock = new Object();
    @Override
    public void run() {
        this.sale();
    }

    private void sale() {
        while (true) {
            synchronized (lock){
                if (ticket > 0) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " saled ..." + ticket);
                    ticket--;
                }else {
                    break;
                }
            }
        }
    }
}
