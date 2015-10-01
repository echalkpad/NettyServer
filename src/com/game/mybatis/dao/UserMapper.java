package com.game.mybatis.dao;

import com.game.mybatis.model.User;

public interface UserMapper {
	
    int deleteById(Integer id);

    int createUser(User user);

    User selectById(Integer id);
    
    int updateUser(User user);
}