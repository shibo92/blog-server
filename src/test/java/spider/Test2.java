package spider;

import java.util.concurrent.atomic.AtomicInteger;

public class Test2 {
    public static void main(String[] args) throws InterruptedException {
        AtomicInteger i = new AtomicInteger(0);

        Thread threads[]=new Thread[30];
        for(int j=0;j<1;j++){
            threads[j]=new Thread(new Runnable() {
                @Override
                public void run() {
                    while (i.intValue() < 1){
                        String html = ConnectionUtil.Connect("https://segmentfault.com/q/1010000015593182");
                        System.out.println("结束"+i.getAndAdd(1));
                    }
                }
            });
            threads[j].start();
        }
    }
}
