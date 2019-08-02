package com.edu.model;

import java.util.Date;

public class Comment {
    private String username;
    private String content;
    private int pid;//评价商品ID，整型
    private Date createdTime;
    private int star;//评论的星星数量
    private String commentLabels;//标签集合Json

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public String getCommentLabels() {
        return commentLabels;
    }

    public void setCommentLabels(String commentLabels) {
        this.commentLabels = commentLabels;
    }
}
