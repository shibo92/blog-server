package com.blog.entity.vo;

import com.blog.entity.Category;

/**
 * 博客分类
 * @author shibo
 */
public class CategoryVo {
    private Category category;

    //更新标记
    private int opsStatus;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getOpsStatus() {
        return opsStatus;
    }

    public void setOpsStatus(int opsStatus) {
        this.opsStatus = opsStatus;
    }
}