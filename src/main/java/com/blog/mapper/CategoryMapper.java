package com.blog.mapper;

import com.blog.entity.Blog;
import com.blog.entity.Category;
import com.blog.entity.Code;
import com.blog.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author shibo
 */
@Repository
public interface CategoryMapper {
    public void save(Category category) throws Exception;

    public void saveList(List<Category> categories) throws Exception;

    public List<Category> getCategoryList(User user) throws Exception;

    void updateList(List<Category> updateCategories) throws Exception;

    void deleteList(List<Category> delCategories) throws Exception;
}