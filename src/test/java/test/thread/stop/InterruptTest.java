package test.thread.stop;

class MyThread extends Thread{
    public void run(){
        super.run();
        try{
            for(int i=0; i<500000; i++){
                if(this.interrupted()) {
                    System.out.println("线程已经终止， for循环不再执行");
                            throw new InterruptedException();
                }
                System.out.println("i="+(i+1));
            }
            System.out.println("hahahaha");
        } catch (InterruptedException e) {
            System.out.println("发生异常，线程终止");
            e.printStackTrace();
        }
    }
}
public class InterruptTest {
    public static void main(String args[]){
        Thread thread = new MyThread();
        thread.start();
        try {
            Thread.sleep(2000);
            thread.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
