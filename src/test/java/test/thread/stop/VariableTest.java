package test.thread.stop;

class MyThread2 implements Runnable {
    volatile boolean isStop = false;
    public void run(){
        for(int i=0; i<500000; i++){
            if(!isStop)
            System.out.println("i="+(i+1));
        }
    }
}
public class VariableTest {
    public static void main(String[] args) {
        MyThread2 myThread2 = new MyThread2();
        Thread thread = new Thread(myThread2);
        thread.start();
        myThread2.isStop = true;
    }
}
