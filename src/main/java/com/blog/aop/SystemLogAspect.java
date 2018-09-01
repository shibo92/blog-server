package com.blog.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

@Aspect
@Component
public class SystemLogAspect {

    @Pointcut("@annotation(com.blog.aop.SystemLog)")
    public  void systemLogAspect() {}

/*    @Pointcut("@annotation(com.blog.aop.SystemLog)")
    public  void serviceAspect() {}

    @Pointcut("@annotation(com.blog.aop.SystemLog)")
    public  void repositoryAspect() {}*/


    @Before("systemLogAspect()")
    public void doBefore(JoinPoint joinPoint) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String ip = request.getRemoteAddr();
        // 获取注解中的值
        String description = getDescriptionFromAnnotation(joinPoint);
        // do something
        System.out.println("Before : " + description);
    }


    @After("systemLogAspect()")
    public void doAfter(JoinPoint joinPoint) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String ip = request.getRemoteAddr();
        // 获取注解中的值
        String description = getDescriptionFromAnnotation(joinPoint);
        // do something
        System.out.println("After : " + description);
    }

    private static String getDescriptionFromAnnotation(JoinPoint joinPoint){
        String description = "";
        try{
            String targetName = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            Object[] arguments = joinPoint.getArgs();
            Class targetClass = Class.forName(targetName);
            Method[] methods = targetClass.getMethods();
            for (Method method : methods) {
                if (method.getName().equals(methodName)) {
                    Class[] clazzs = method.getParameterTypes();
                    if (clazzs.length == arguments.length) {
                        description = method.getAnnotation(SystemLog.class).description();
                        break;
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return description;
    }
}
