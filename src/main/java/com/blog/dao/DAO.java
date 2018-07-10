package com.blog.dao;

public interface DAO {
    public Object save(String str, Object obj) throws Exception;

    public Object update(String str, Object obj) throws Exception;

    public Object findForObject(String str, Object obj) throws Exception;

    public Object findForList(String str, Object obj) throws Exception;
}
