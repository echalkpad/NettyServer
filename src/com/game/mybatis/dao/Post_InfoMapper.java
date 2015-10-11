package com.game.mybatis.dao;

import com.game.mybatis.model.Post_Info;

public interface Post_InfoMapper {
    int deleteById(Integer postId);

    int insertPostInfo(Post_Info post);

    Post_Info selectById(Integer postId);

    int updatePostInfoe(Post_Info post);
    
    void deleteAllData();
}