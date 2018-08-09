package test.mytest;

public class Test2 {
    public static void main(String[] args) throws InterruptedException {
        for(int i = 0;i<50; i++){
            String html = ConnectionUtil.Connect("https://segmentfault.com/a/1190000015949423");
            Thread.sleep(3000);
            System.out.println("结束"+i);
        }
    }
}
