package com.game.mybatis.dao;

import com.game.mybatis.model.Role_Info;

public interface Role_InfoMapper {
    int deleteById(Integer id);

    int insertRoleInfo(Role_Info role);

    Role_Info selectById(Integer id);

    int updateRoleInfo(Role_Info role);
    
    void deleteAllData();
}