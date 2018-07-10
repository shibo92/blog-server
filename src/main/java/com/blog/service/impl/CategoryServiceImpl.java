package com.blog.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.blog.entity.Category;
import com.blog.entity.User;
import com.blog.entity.vo.UserVo;
import com.blog.mapper.CategoryMapper;
import com.blog.service.BaseService;
import com.blog.service.CategoryService;
import com.blog.utils.DateUtil;
import com.blog.utils.StringUtil;
import com.blog.utils.UUID;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shibo
 */
@Service
public class CategoryServiceImpl extends BaseService implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public void save(Category category, UserVo sessionUser) throws Exception {
        System.out.println(category);
        this.initCategoryCommonParam(category, sessionUser);
        categoryMapper.save(category);
    }

    private void initCategoryCommonParam(Category category, UserVo sessionUser) {
        category.setId(UUID.get32Code());
        category.setUserId(sessionUser.getUser().getId());
        category.setIsDel(0);
        category.setCreateDate(DateUtil.getDay());
    }

    @Override
    public List<Category> getCategories(User user) throws Exception {
        return categoryMapper.getCategoryList(user);
    }

    @Override
    public List<Category> parseJson2Category(String categoryJson) {
        List<Category> resultCategory = new ArrayList();
        JSONArray categoryJsonArr = (JSONArray) JSON.parse(categoryJson);
        this.parseJsonArr2Category("0", categoryJsonArr, resultCategory);
        return resultCategory;
    }

    @Override
    public void save(List<Category> saveCategories, List<Category> updateCategories, List<Category> removeCategories, UserVo sessionUser) throws Exception {

        if (!saveCategories.isEmpty()) {
            for (Category category : saveCategories) {
                this.initCategoryCommonParam(category, sessionUser);
            }
            categoryMapper.saveList(saveCategories);
        }
        if (!updateCategories.isEmpty()) {
            categoryMapper.updateList(updateCategories);
        }
        if (!removeCategories.isEmpty()) {
            categoryMapper.deleteList(removeCategories);
        }
    }

    @Override
    public void delete(List<Category> removeCategories, UserVo userVo) throws Exception {
        categoryMapper.deleteList(removeCategories);
    }

    private void parseJsonArr2Category(String pId, JSONArray categoryJsonArr, List<Category> resultCategory) {
        for (Object categoryObj : categoryJsonArr) {
            JSONObject categoryJsonObj = (JSONObject) categoryObj;
            String categoryId = categoryJsonObj.getString("id");
            String categoryLabel = categoryJsonObj.getString("label");
            String categoryPid = categoryJsonObj.getString("pid");
            Category category = new Category();
            if(StringUtils.isBlank(categoryPid)){
                category.setPid(pId);
            }else{
                category.setPid(categoryPid);
            }
            category.setId(categoryId);
            category.setContent(categoryLabel);
            resultCategory.add(category);
            JSONArray childJsonArr = (JSONArray) categoryJsonObj.get("children");
            if (null != childJsonArr && !childJsonArr.isEmpty()) {
                parseJsonArr2Category(categoryId, childJsonArr, resultCategory);
            }
        }
    }
}
