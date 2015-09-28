package com.game.mybatis.dao;

import com.game.mybatis.model.Game_Info;

public interface Game_InfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Game_Info record);

    int insertSelective(Game_Info record);

    Game_Info selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Game_Info record);

    int updateByPrimaryKey(Game_Info record);
}