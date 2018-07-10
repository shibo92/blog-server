package com.blog.entity.vo;

import com.blog.entity.Blog;
import com.blog.entity.Category;
import com.blog.entity.Code;

/**
 * @author shibo
 */
public class BlogVo {
    private Blog blog;

    private UserVo user;

    private Category category;

    private Code type;

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public UserVo getUser() {
        return user;
    }

    public void setUser(UserVo user) {
        this.user = user;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Code getType() {
        return type;
    }

    public void setType(Code type) {
        this.type = type;
    }
}