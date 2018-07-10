package com.blog.service;

import com.blog.entity.Category;
import com.blog.entity.User;
import com.blog.entity.vo.CategoryVo;
import com.blog.entity.vo.UserVo;

import java.util.List;

/**
 * @author shibo
 */
public interface CategoryService{
    void save(Category category, UserVo sessionUser) throws Exception;

    List<Category> getCategories(User user) throws Exception;

    List<Category> parseJson2Category(String categoryJson);

    void save(List<Category> categories, List<Category> removeCategories, List<Category> categoryList, UserVo sessionUser) throws Exception;

    void delete(List<Category> removeCategories, UserVo userVo) throws Exception;
}
