package com.blog.exception;

/**
 * 系统异常
 * @author shibo
 */
public class SystemException extends RuntimeException{
    public SystemException(String msg) {
        super(msg);
    }
}
