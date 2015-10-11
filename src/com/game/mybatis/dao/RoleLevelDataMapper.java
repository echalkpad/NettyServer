package com.game.mybatis.dao;

import com.game.mybatis.model.RoleLevelData;

public interface RoleLevelDataMapper {
    int deleteById(Integer levelId);

    int insertRoleLevelData(RoleLevelData level);

    RoleLevelData selectById(Integer levelId);

    int updateRoleLevelData(RoleLevelData level);
    
    void deleteAllData();
}