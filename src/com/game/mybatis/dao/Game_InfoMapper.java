package com.game.mybatis.dao;

import com.game.mybatis.model.Game_Info;

public interface Game_InfoMapper {
    int deleteById(Integer id);

    int insertGameInfo(Game_Info game);

    Game_Info selectById(Integer id);

    int updateGameInfo(Game_Info game);
}