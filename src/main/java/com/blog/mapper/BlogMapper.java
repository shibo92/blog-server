package com.blog.mapper;

import com.blog.entity.Blog;
import com.blog.entity.Category;
import com.blog.entity.Code;
import com.blog.entity.User;
import com.blog.entity.vo.UserVo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author shibo
 */
@Repository
public interface BlogMapper {
    public void saveBlog(Blog blog) throws Exception;

    public List<Code> getBlogTypeList(Map<String, Object> codeMap) throws Exception;

    List<Map<String, Object>> getLeftBar(User sessionUser) throws Exception;

    List<Blog> listBlog(String categoryId) throws Exception;

    Blog getBlogById(String blogId) throws Exception;
}