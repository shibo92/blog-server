package com.blog.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("daoSupport")
public class DaoSupport implements DAO{
    @Resource
    private SqlSessionTemplate sqlSessionTemplate;
    @Override
    public Object save(String str, Object obj) throws Exception {
        return null;
    }

    @Override
    public Object update(String str, Object obj) throws Exception {
        return null;
    }

    @Override
    public Object findForObject(String str, Object obj) throws Exception {
        return sqlSessionTemplate.selectOne(str,obj);
    }

    @Override
    public Object findForList(String str, Object obj) throws Exception {
        return null;
    }
}
