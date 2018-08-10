package test.proxy;

import java.io.Serializable;
import java.util.Arrays;

/**
 * 验证List，Map，Set中继承了AbstractClass还要implement的作用
 * 结果：为了方便代理（被代理类需要继承接口）
 */
public class Test {

    public static interface MyInterface {
        void foo();
    }

    public static class BaseClass implements MyInterface, Cloneable, Serializable {

        @Override
        public void foo() {
            System.out.println("BaseClass.foo");
        }
    }

    public static class Class1 extends BaseClass {

        @Override
        public void foo() {
            super.foo();
            System.out.println("Class1.foo");
        }
    }

    static class Class2 extends BaseClass implements MyInterface, Cloneable,
            Serializable {

        @Override
        public void foo() {
            super.foo();
            System.out.println("Class2.foo");
        }
    }

    public static void main(String[] args) {

        showInterfacesFor(BaseClass.class);
        showInterfacesFor(Class1.class);
        showInterfacesFor(Class2.class);
    }

    private static void showInterfacesFor(Class<?> clazz) {
        System.out.printf("%s --> %s\n", clazz, Arrays.toString(clazz
                .getInterfaces()));
    }
}
