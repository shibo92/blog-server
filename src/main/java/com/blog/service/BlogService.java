package com.blog.service;

import com.blog.entity.Blog;
import com.blog.entity.Category;
import com.blog.entity.Code;
import com.blog.entity.User;
import com.blog.entity.vo.UserVo;

import java.util.List;
import java.util.Map;

/**
 * @author shibo
 */
public interface BlogService {
    void saveBlog(Blog blog, UserVo sessionUser) throws Exception;

    List<Code> getBlogTypeList() throws Exception;

    List<Map<String,Object>> getLeftBar(User sessionUser) throws Exception;

    List<Blog> listBlog(String categoryId) throws Exception;

    Blog getBlogById(String blogId) throws Exception;
}
