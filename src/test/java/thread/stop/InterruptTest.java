package thread.stop;

class MyThread implements Runnable {
    public boolean flag = true;

    public synchronized void run() {
        while (flag){
            try {
                System.out.println("进入线程啦啦啦");
                // 使用wait后，单纯用flag标记无法将线程停止
                // 此时需要interrupt()方法将阻塞状态清除，并返回一个InterruptedException.
                wait();
            } catch (InterruptedException e) {
                System.out.println("线程即将终止");
                flag = false;
            }
        }
        System.out.println("线程终止...");
    }
}

public class InterruptTest {
    public static void main(String args[]) {
        MyThread myThread = new MyThread();
        Thread thread = new Thread(myThread);
        thread.start();
        try {
            Thread.sleep(3000);
            thread.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
