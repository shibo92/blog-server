package com.blog.service.impl;

import com.blog.common.CodeConstants;
import com.blog.entity.Blog;
import com.blog.entity.Category;
import com.blog.entity.Code;
import com.blog.entity.User;
import com.blog.entity.vo.UserVo;
import com.blog.mapper.BlogMapper;
import com.blog.service.BlogService;
import com.blog.utils.DateUtil;
import com.blog.utils.UUID;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author shibo
 */
@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogMapper blogMapper;

    @Override
    public void saveBlog(Blog blog, UserVo user) throws Exception {
        blog.setId(UUID.get32Code());
        blog.setCreateTime(DateUtil.getDateTime());
        blog.setIsDel(0);
        blog.setUserId(user.getUser().getId());
        blog.setAuthor(user.getUserInfo().getName());
        blogMapper.saveBlog(blog);
    }

    @Override
    public List<Code> getBlogTypeList() throws Exception {
        Map<String, Object> codeMap = new HashMap();
        codeMap.put("CODE", CodeConstants.BLOG_TYPE_CODE);
        return blogMapper.getBlogTypeList(codeMap);
    }

    @Override
    public List<Map<String, Object>> getLeftBar(User sessionUser) throws Exception {
        List<Map<String, Object>> categoryList = blogMapper.getLeftBar(sessionUser);
        List<Map<String, Object>> leftBarList = this.groupCategory(categoryList, "0");
        return leftBarList;
    }

    @Override
    public List<Blog> listBlog(String categoryId) throws Exception {
        return blogMapper.listBlog(categoryId);
    }

    @Override
    public Blog getBlogById(String blogId) throws Exception {
        return blogMapper.getBlogById(blogId);
    }

    private List<Map<String, Object>> groupCategory(List<Map<String, Object>> categoryList, String pid) {
        List<Map<String, Object>> childCategoryList = new ArrayList();
        for (Map category : categoryList) {
            String categoryId = String.valueOf(category.get("ID"));
            String categoryPid = String.valueOf(category.get("PID"));
            String categoryContent = String.valueOf(category.get("CONTENT"));
            // 如果本次循环的PID = 本级的PID，把此次循环的内容放入上一级的child
            if (pid.equals(categoryPid)) {
                Map<String, Object> childCategory = new HashMap();
                childCategory.put("id", categoryId);
                childCategory.put("label", categoryContent);
                childCategory.put("children", groupCategory(categoryList, categoryId));
                childCategoryList.add(childCategory);
            }
        }
        return childCategoryList;
    }
}
