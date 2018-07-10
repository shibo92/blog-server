package com.blog.exception;

/**
 * 权限异常
 * @author shibo
 */
public class AuthorizedException extends RuntimeException{
    public AuthorizedException(String msg) {
        super(msg);
    }
}
