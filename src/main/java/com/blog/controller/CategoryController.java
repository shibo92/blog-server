package com.blog.controller;

import com.blog.common.JsonResult;
import com.blog.common.ResultCode;
import com.blog.entity.Blog;
import com.blog.entity.Category;
import com.blog.entity.User;
import com.blog.entity.vo.UserVo;
import com.blog.service.BlogService;
import com.blog.service.CategoryService;
import com.blog.service.RedisCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author shibo
 */
@Controller
@RequestMapping(value = "/categories")
public class CategoryController extends BaseController {

    @Resource(name = "categoryServiceImpl")
    private CategoryService categoryServiceImpl;
    @Resource(name = "blogServiceImpl")
    private BlogService blogServiceImpl;
    @Resource(name = "redisCacheService")
    private RedisCacheService redisCacheService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult get() {
        JsonResult jsonResult = new JsonResult();
        try {
            User user = getSessionUser().getUser();
            List<Category> categoryList = categoryServiceImpl.getCategories(user);
            jsonResult.setInfo(ResultCode.SUCCESS, "request success", categoryList);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return jsonResult;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult save(HttpServletRequest request) {
        JsonResult jsonResult = new JsonResult();
        try {
            UserVo userVo = getSessionUser();
            String saveCategoryJson = request.getParameter("saveCategoryJson");
            String removeCategoryJson = request.getParameter("removeCategoryJson");
            String updateCategoryJson = request.getParameter("updateCategoryJson");
            List<Category> saveCategories = categoryServiceImpl.parseJson2Category(saveCategoryJson);
            List<Category> removeCategories = categoryServiceImpl.parseJson2Category(removeCategoryJson);
            // List<Category> updateCategories = categoryServiceImpl.parseJson2Category(updateCategoryJson);
            List<Category> updateCategories = new ArrayList<Category>();

            categoryServiceImpl.save(saveCategories, updateCategories, removeCategories, userVo);
            jsonResult.setInfo(ResultCode.SUCCESS, "request success");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return jsonResult;
    }

    @RequestMapping(value = "{categoryId}/blogs/{blogId}", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult get(@PathVariable String categoryId, @PathVariable String blogId) {
        JsonResult jsonResult = new JsonResult();
        try {
            Blog blog = blogServiceImpl.getBlogById(blogId);
            jsonResult.setInfo(ResultCode.SUCCESS, "request success", blog);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return jsonResult;
    }

    /**
     * 根据类型获取blogs
     *
     * @param categoryId
     * @return
     */
    @RequestMapping(value = "/{categoryId}/blogs", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult list(@PathVariable String categoryId) {
        JsonResult jsonResult = new JsonResult();
        try {
            List<Blog> blogList = redisCacheService.getCacheList(categoryId);
            if(blogList.isEmpty()){
                blogList = blogServiceImpl.listBlog(categoryId);
                redisCacheService.setCacheObject(categoryId,blogList);
            }
            jsonResult.setInfo(ResultCode.SUCCESS, "request success", blogList);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return jsonResult;
    }
}
