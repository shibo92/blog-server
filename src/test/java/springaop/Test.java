package springaop;

public class Test {
    public static void main(String[] args) {
        MyProxyManager manager = new MyProxyManager();
        UserService userService = (UserService) manager.bind(new UserServiceImpl());
        userService.sayHello("lalala");
    }
}
