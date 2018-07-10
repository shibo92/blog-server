package com.blog.utils;

/**
 * @author shibo
 */
public class UUID {

	public static String get32Code() {
		String uuid = java.util.UUID.randomUUID().toString().trim().replaceAll("-", "");
		return uuid;
	}

    public static void main(String[] args) {
        System.out.println(get32Code());
    }
}