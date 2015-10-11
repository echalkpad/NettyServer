package com.game.mybatis.model;

public class Post_Info {
    private Integer postId;

    private String postTitle;

    private String postComment;

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle == null ? null : postTitle.trim();
    }

    public String getPostComment() {
        return postComment;
    }

    public void setPostComment(String postComment) {
        this.postComment = postComment == null ? null : postComment.trim();
    }
}