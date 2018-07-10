package com.blog.entity;

/**
 * 博客信息
 * @author shibo
 */
public class BlogStatistics {
    private String id;

    private String blogId;

    private String readCount;

    private String thumbUpCount;

    private Integer isDel;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getBlogId() {
        return blogId;
    }

    public void setBlogId(String blogId) {
        this.blogId = blogId == null ? null : blogId.trim();
    }

    public String getReadCount() {
        return readCount;
    }

    public void setReadCount(String readCount) {
        this.readCount = readCount == null ? null : readCount.trim();
    }

    public String getThumbUpCount() {
        return thumbUpCount;
    }

    public void setThumbUpCount(String thumbUpCount) {
        this.thumbUpCount = thumbUpCount == null ? null : thumbUpCount.trim();
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }
}