package test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import test.Test.BaseClass;
import test.Test.Class1;
import test.Test.Class2;

public class Test2 extends Test {

    public static void main(String[] args) {

        Class1 c1 = new Class1();
        Class2 c2 = new Class2();

        // Note the order...
        Class1 proxy2 = (Class1) createProxy(c2);
        proxy2.foo();

        // This fails with an unchecked exception
        Class2 proxy1 = (Class2) createProxy(c1);
        proxy1.foo();
    }

    private static Object createProxy(final Object obj) {

        final InvocationHandler handler = new InvocationHandler() {

            @Override
            public Object invoke(Object proxy, Method method, Object[] args)
                    throws Throwable {
               /* System.out.printf("About to call %s() on %s\n", method
                        .getName(), obj);*/
                return method.invoke(obj, args);
            }
        };

        return (Object) Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj
                .getClass().getInterfaces(), handler);
    }
}
