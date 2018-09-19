package springaop;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyProxyManager implements InvocationHandler {
    private Object target;
    public Object bind(Object target){
        this.target = target;
        /*
         * Proxy.newProxyInstance 会返回一个代理对象
         * target.getClass() --> 被代理对象的ClassLoader，这里实际对应UserServiceImpl类
         * target.getClass().getInterfaces() --> 被代理对象实现的接口列表（UserService），指定之后，代理对象就可以使用其接口中的方法了
         * this -- 表示的是当这个动态代理对象在调用方法的时候，会通过哪个InvocationHandler，这里实际是MyProxyManager这个类
         */
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    /**
     * 我们通过MyProxyManager创建出来的代理对象在调用自己的方法时，
     * 会通过invoke这个方法进行调用，而不是直接调用
     * @param proxy 被代理对象
     * @param method 被代理对象执行的方法
     * @param args 被代理对象执行的方法所需要的参数
     *
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        System.out.println("我是动态代理对象");
        System.out.println("准备进入事务..");
        Object result = null;
        try{
            // 执行被代理对象自己的方法
            result = method.invoke(target, args);
        }catch(Exception e){
            System.out.println("执行rollBack");
        }
        System.out.println("执行commit");
        System.out.println("-----------------------");
        return result;
    }
}