package thread.semaphor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Example1 {
    public static int count = 0;
    public static int clientTotal = 5000;
    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < clientTotal ; i++) {
            executorService.execute(() -> {
                try {
                    add();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        // 输出可能比5000少
        System.out.println(count);
    }
    private static void add() {
        count++;
    }
}