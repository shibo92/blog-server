package com.blog.controller;

import com.blog.common.JsonResult;
import com.blog.common.ResultCode;
import com.blog.entity.Blog;
import com.blog.entity.Category;
import com.blog.entity.Code;
import com.blog.entity.User;
import com.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author shibo
 */
@Controller
@RequestMapping(value = "/blogs")
public class BlogController extends BaseController {

    @Autowired
    private BlogService blogServiceImpl;
    // @Autowired
    // private RedisCacheService redisCacheServiceImpl;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult save(Blog blog, HttpServletRequest request) {
        JsonResult jsonResult = new JsonResult();
        try {
            blogServiceImpl.saveBlog(blog, this.getSessionUser());
            // redisCacheServiceImpl.delCache(blog.getCategoryId());
            jsonResult.setInfo(ResultCode.SUCCESS,"request success");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return jsonResult;
    }

    @RequestMapping(value = "/{blogId}", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult get(@PathVariable String blogId) {
        JsonResult jsonResult = new JsonResult();
        try {
            Blog blog = blogServiceImpl.getBlogById(blogId);
            jsonResult.setInfo(ResultCode.SUCCESS,"request success",blog);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return jsonResult;
    }

    @RequestMapping(value = "/leftbar", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getLeftBar() {
        JsonResult jsonResult = new JsonResult();
        try {
            List<Map<String,Object>> leftBar = blogServiceImpl.getLeftBar(this.getSessionUser().getUser());
            jsonResult.setInfo(ResultCode.SUCCESS,"request success",leftBar);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return jsonResult;
    }

    @RequestMapping(value = "/types", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult getBlogTypeList() {
        JsonResult jsonResult = new JsonResult();
        try {
            List<Code> codeList = blogServiceImpl.getBlogTypeList();
            jsonResult.setInfo(ResultCode.SUCCESS,"request success",codeList);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return jsonResult;
    }
}
