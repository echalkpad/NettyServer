package com.game.mybatis.dao;

import java.util.List;

import com.game.mybatis.model.Base_Role;

public interface Base_RoleMapper {
    int deleteById(Integer baseroleId);

    int insertBaseRole(Base_Role baseRole);

    Base_Role selectById(Integer baseroleId);

    int updateBaseRole(Base_Role baseRole);
    
    List<Base_Role> getBaseRoleList();
    
    void deleteAllData();
}