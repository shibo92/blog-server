package com.blog.utils;

import org.apache.commons.lang.StringUtils;

/**
 * @author shibo
 */
public class SqlUtil {
    //字段转换成 #{xxx} 格式
    public static StringBuffer key2Parameter(String keyStr){
        keyStr = StringUtils.deleteWhitespace(keyStr);
        String[] keyArr = keyStr.split(",");
        StringBuffer paramsStr = new StringBuffer();
        for(String key : keyArr){
            String param = "#{KEY}";
            key = StringUtil.camelCaseName(key.toLowerCase());
            param = param.replace("KEY",key);
            paramsStr.append(param);
            paramsStr.append(",\n");
        }
        paramsStr = paramsStr.replace(paramsStr.length()-2,paramsStr.length()-1,"");
        return paramsStr;
    }
    //字段转换成 #{item.xxx} 格式
    public static StringBuffer key2ParameterItem(String keyStr){
        keyStr = StringUtils.deleteWhitespace(keyStr);
        String[] keyArr = keyStr.split(",");
        StringBuffer paramsStr = new StringBuffer();
        for(String key : keyArr){
            String param = "#{item.KEY}";
            param = param.replace("KEY",key);
            paramsStr.append(param);
            paramsStr.append(",\n");
        }
        paramsStr = paramsStr.replace(paramsStr.length()-2,paramsStr.length()-1,"");
        return paramsStr;
    }


    //字段转换成 <if xx!= null>xxx = #{xxx}</if> 格式
    public static StringBuffer key2Update(String keyStr){
        keyStr = StringUtils.deleteWhitespace(keyStr);
        String[] keyArr = keyStr.split(",");
        StringBuffer paramsStr = new StringBuffer();
        for(String key : keyArr){
            String param = "<if test=\"KEY != null\">\n\tKEY = #{KEY},\n</if>";
            param = param.replace("KEY",key);
            paramsStr.append(param);
            paramsStr.append("\n");
        }
        paramsStr.append("WHERE ID = #{ID}");
        return paramsStr;
    }


    public static void main(String[] args) {
        String str = "ID, USER_ID, CLASSIFICATION_ID, TYPE, LOAD_URL, AUTHOR, TITLE, LABEL, CONTENT, CREATE_TIME, ALTER_TIME, STATE, IS_DEL,";
        StringBuffer params = SqlUtil.key2Parameter(str);
        // StringBuffer params = SqlUtil.key2ParameterItem(str);
        // StringBuffer params = SqlUtil.key2Update(str);
        System.out.println(params);
    }
}
