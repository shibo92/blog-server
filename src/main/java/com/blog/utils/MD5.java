package com.blog.utils;

import java.security.MessageDigest;

/**
 * @author shibo
 */
public class MD5 {
    public static String getMd5Code(String str) {
        String resultString = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte b[] = md.digest();
            resultString = byteArrayToHexString(b);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultString;
    }

    /**
     * 转换字节数组为16进制字串
     * @param b 字节数组
     * @return 16进制字串
     */
    public static String byteArrayToHexString(byte[] b) {
        int i;
        StringBuffer buf = new StringBuffer("");
        for (int offset = 0; offset < b.length; offset++) {
            i = b[offset];
            if (i < 0){
                i += 256;
            }
            if (i < 16){
                buf.append("0");
            }
            buf.append(Integer.toHexString(i));
        }
        return buf.toString();
    }

}
