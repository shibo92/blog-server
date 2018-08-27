package com.blog.common;

import java.io.Serializable;

public class ObjectMessage implements Serializable {

    private String method; // 调用方法
    private Object parameter; // 方法参数

    public ObjectMessage() {
        super();
    }

    public ObjectMessage(String method, Object parameter) {
        super();
        this.method = method;
        this.parameter = parameter;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Object getParameter() {
        return parameter;
    }

    public void setParameter(Object parameter) {
        this.parameter = parameter;
    }

}
