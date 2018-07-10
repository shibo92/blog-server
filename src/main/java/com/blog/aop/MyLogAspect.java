package com.blog.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

//声明这是一个切面
@Aspect
//把这个类交给spring管理
@Component
public class MyLogAspect {
    // 把切面的连接点放在了我们的注解上
    @Pointcut("@annotation(com.blog.aop.MyLog)")
    public void mlAspect() {
    }

    // 在这里定义前置切面
    @Before("mlAspect()")
    public void beforeMethod(JoinPoint joinPoint) {
        // 这里执行保存日志的动作
        System.out.println("方法前.......");
        //得到被切方法的参数
        System.out.println(joinPoint.getArgs()[0]);
    }
}