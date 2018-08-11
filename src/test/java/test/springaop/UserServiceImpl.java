package test.springaop;

public class UserServiceImpl implements UserService{
    @Override
    public void sayHello(String word) {
        System.out.println("执行dao的删除操作,添加一个用户:" + word);
        System.out.println("执行dao的删除添加,删除一个用户:" + word);
    }
    @Override
    public void sayHello2(String word) {
        System.out.println("222" + word);
    }
}