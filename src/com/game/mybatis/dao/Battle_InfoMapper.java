package com.game.mybatis.dao;

import com.game.mybatis.model.Battle_Info;

public interface Battle_InfoMapper {
    int deleteById(Integer id);

    int insertBattleInfo(Battle_Info battle);

    Battle_Info selectById(Integer id);

    int updateBattleInfo(Battle_Info battle);
}