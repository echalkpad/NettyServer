package com.game.mybatis.dao;

import com.game.mybatis.model.Battle_Info;

public interface Battle_InfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Battle_Info record);

    int insertSelective(Battle_Info record);

    Battle_Info selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Battle_Info record);

    int updateByPrimaryKey(Battle_Info record);
}